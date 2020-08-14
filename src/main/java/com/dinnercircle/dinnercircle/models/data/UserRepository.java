package com.dinnercircle.dinnercircle.models.data;

import com.dinnercircle.dinnercircle.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByPhoneNum(String phoneNum);

    User findByEmail(String email);
}
