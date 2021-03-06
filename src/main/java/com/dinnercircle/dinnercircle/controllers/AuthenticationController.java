package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.User;
import com.dinnercircle.dinnercircle.models.data.UserRepository;
import com.dinnercircle.dinnercircle.models.dto.LoginFormDTO;
import com.dinnercircle.dinnercircle.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {


        @Autowired
        UserRepository userRepository;

        private static final String userSessionKey = "user";

        private static Integer currentUserId;

        public User getUserFromSession(HttpSession session) {
            Integer userId = (Integer) session.getAttribute(userSessionKey);
            if (userId == null) {
                return null;
            }

            Optional<User> user = userRepository.findById(userId);

            if (user.isEmpty()) {
                return null;
            }
            currentUserId = user.get().getId();
            return user.get();
        }

        private static void setUserInSession(HttpSession session, User user) {
            session.setAttribute(userSessionKey, user.getId());
        }

        @GetMapping("/register")
        public String displayRegistrationForm(Model model) {
            model.addAttribute(new RegisterFormDTO());
            model.addAttribute("title", "Register");
            return "register";
        }

        @PostMapping("/register")
        public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                              Errors errors, HttpServletRequest request,
                                              Model model) {

            if (errors.hasErrors()) {
                model.addAttribute("title", "Register");
                return "register";
            }

            User existingUsername = userRepository.findByUsername(registerFormDTO.getUsername());
            User existingEmail = userRepository.findByEmail(registerFormDTO.getEmail());
            User existingPhoneNum = userRepository.findByPhoneNum(registerFormDTO.getPhoneNum());

            if (existingUsername != null) {
                errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
                model.addAttribute("title", "Register");
                return "register";
            }

            if (existingEmail != null) {
                errors.rejectValue("email", "email.alreadyexists", "A user with that email address already exists");
                model.addAttribute("title", "Register");
                return "register";
            }

            if (existingPhoneNum != null) {
                errors.rejectValue("phoneNum", "phoneNum.alreadyexists", "A user with that phone number already exists");
                model.addAttribute("title", "Register");
                return "register";
            }

            String password = registerFormDTO.getPassword();
            String verifyPassword = registerFormDTO.getVerifyPassword();
            if (!password.equals(verifyPassword)) {
                errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
                model.addAttribute("title", "Register");
                return "register";
            }

            User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword(), registerFormDTO.getEmail(), registerFormDTO.getPhoneNum(),
                                    registerFormDTO.getFirstName(), registerFormDTO.getLastName());
            userRepository.save(newUser);
            setUserInSession(request.getSession(), newUser);

            return "redirect:/home";
        }

        @GetMapping("/login")
        public String displayLoginForm(Model model) {
            model.addAttribute(new LoginFormDTO());
            model.addAttribute("title", "Log In");
            return "login";
        }

        @PostMapping("/login")
        public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                       Errors errors, HttpServletRequest request,
                                       Model model) {

            if (errors.hasErrors()) {
                model.addAttribute("title", "Log In");
                return "login";
            }

            User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

            if (theUser == null) {
                errors.rejectValue("username", "user.invalid", "The given username does not exist");
                model.addAttribute("title", "Log In");
                return "login";
            }

            String password = loginFormDTO.getPassword();

            if (!theUser.isMatchingPassword(password)) {
                errors.rejectValue("password", "password.invalid", "Invalid password");
                model.addAttribute("title", "Log In");
                return "login";
            }

            setUserInSession(request.getSession(), theUser);

            return "redirect:/home";
        }

        @GetMapping("/logout")
        public String logout(HttpServletRequest request){
            request.getSession().invalidate();
            return "redirect:";
        }

        public static Integer getCurrentUserId() {
            return currentUserId;
        }
}
