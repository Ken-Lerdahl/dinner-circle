CONTENTS
-------------

 - Introduction
 - Requirements
 - Recommended Modules
 - Installation
 - Configuration
 - Troubleshooting
 - FAQ
 - Maintainers

-------------


 ## Introduction

  	- Dinner Circle is a social meal planning app with shopping list functionality. The primary component of the app is a 7-day meal plan which users can add meals to. The app will be set to clear the meal plan weekly, and send a reminder to users to update the plan for the upcoming week.

  	- Pantry Check Function:
  	As users add meals to their plan, the app will calculate totals of each ingredient needed so that the user can check their own inventory. Any items needed can be added to the "Shopping List".

  	- Shopping List Function:
  	Users may add items from their "Pantry Check" or add new items to their shopping list. As the user shops for their items, they may "cross them off" their shopping list.

  	- Social Function:
  	Users may add "friends" by email, phone number or username. Users will be able to "post" updates about meals they are cooking, ask for meal recommendations, and react to friend's meals.

-------------


-------------

## Motivation

	- Dinner Circle was conceived initially through a conversation with my wife, frustrated by the lack of integration between her grocery and meal apps. One app had one feature she liked, another had a few, but nothing was the silver bullet for her needs. After a bit of target market survey, features were planned based on feedback.

-------------


-------------

 ## Requirements / Framework

 	- Dinner Circle Implements the following:
 		- SpringBoot
 		- Gradle 2.3.2
 		- Spring Security
 		- Hibernate
 		- MySQL 8 
 		- Spring JPA & MySQL Connector
 		- ThymeLeaf
 		- JUnit (Testing)
 		- Complete build was created with IntelliJ

-------------


## Recommended Modules

-------------

-------------


## Installation

-------------

	-	Dinner Circle was created completely with IntelliJ Idea using the following steps:
		- Java JDK 14 installed (remember to set JDK 14 in Gradle and IntelliJ properties).
		- MySQL & MySQL Workbench (or equivalent application) installed
		- Begin by downloading dinner-circle folder, or checkout from Github at https://github.com/Ken-Lerdahl/dinner-circle.git
		- Implement all SpringBoot, ThymeLeaf, and Hibernate / MySQL connectors.
		- Application.properties: Remember to swap database user & pass for whatever you create in your own database.

-------------


## How to Use

-------------
	- Once app is running in your environment, create a new user.
	- Click "Meals --> Add a Meal" in nav bar, and create a few meals. You will need to enter ingredients the first time you do this.
	- Now you are ready to create your first meal plan! Select "Meal plan" from nav bar, and then click on one of the "Add a Meal" buttons under the day of the week you'd like.
	- Once your meal plan is complete, select "Pantry Check" from nav bar, and check your personal ingredient inventory at home. Any items needed can be added to your Shopping list from this page.
	- When you are ready to shop, simply select "Shopping List" from nav bar. You are able to add items to your shopping list not needed for your meal plan, and as you shop, just check things off as they make their way into your cart!
-------------


## Troubleshooting

-------------
	
	- Found a bug not listed here? Open an issue on GitHub!
-------------


## FAQ

-------------

-------------


## Maintainers

-------------

	- Dinner Circle is protected under a GNU GPL v3 license (https://github.com/Ken-Lerdahl/dinner-circle/blob/master/LICENSE.txt)

	- Dinner Circle was created and is currently fully maintained by Ken Lerdahl (ken.lerdahl@gmail.com).

-------------