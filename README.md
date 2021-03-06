# Kata

This project is built using openjdk-16. Built using TDD and Simple Design principles in Java.

## How to build

Ideally it is easier to build this project using the Jetbrains IntelliJ IDEA.

Jetbrains IntelliJ IDE:

Option 1:

* Under the project explorer, uncollapse: out/artifacts/Kata_main_jar and then right click Kata.main.jar and press run

Option 2:

* Top right corner select "Edit configurations"
* Press the plus sign in the top left corner -> then press Application -> enter name -> enter module and "Kata.main" under -cp
* For Main class select the notepad symbol and then navigate over to Project tab, main method is under the directory: src/main/java/Kata (Package)/Kata (Class)
* Close the Main class selection window and then press apply and ok
* Press run next to the configurations dropdown
* Application should display available actions in the terminal window

CMD:

* navigate to root project directory, then navigate to  out/artifacts/Kata_main_jar
* run the following command "java -jar Kata.main.jar"

## Code details

This is a simple command line application. When the user first launches the application, they are met with a prompt that will direct them to select one of the available options.
Functionality includes: searching, following, publishing and viewing both other users timeline and your own timeline.

All functions are self explanatory for the most part and the entire application is built using OOP principles in mind.

Application starts with 2 users, Bob and Charlie. Bob has 2 posts and Charlie has only one. You log in as a hard coded user, Alice.

## User Class
Contains: Followers list, Posts list and the username of the user

## Post Class
Contains: Likes (TODO), Views (TODO), Author (User) of the post, PostedDate when the post was created, PostDetail which contains the body of the post.

This class comes with a  compateTo method so that the application can effectively sort its users post collections.

## Search
This is a basic search so the username of the user you are seacrhing for needs to match exactly (capitalization not required). 
Search was implemented so that the logged in user can find new people to follow.

## Viewing your timeline
This option should display your posts + any posts of the users you are following, if you haven't made any posts or you are not following anyone, then this will be empty.

## Viewing someone elses timeline
Alternatively you can view someone elses timeline by searching for that user first and then the application will ask you if you would like to view their timeline.

## Following
You can follow another user by searching for them using their username and if they exist then the application will ask if you would like to view their timeline, if you accept/decline then you will be asked if you would like to follow them. After following, if you view your timeline you should see both yours and that users posts.

## Thanks!
For reading and enjoy, let me know if there are any questions or if I missed something.


## Additional Features
Feature: Unfollow
	Scenario: Alice unfollows Bob.
	Given Alice has followed Bob,
		And Bob has published "Good game though."
		And Alice can see Bob's message on her wall.
	When Alice unfollows Bob,
	Then Alice should not see Bob's message on her wall anymore.

Feature: Block
	Scenario: Alice blocks bob.
	Given Alice has followed Bob,
		And Bob has followed Alice,
		And Bob has published "I really don't like this Alice person"
		And Alice can see Bob's hateful message mentioning her.
	When Alice blocks Bob,
	Then Bob can no longer see Alice's posts,
		And Alice can no longer see Bob's posts, 
		And Bob can not follow Alice again.

??? should this include search?