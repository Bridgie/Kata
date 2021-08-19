package Kata;

import Kata.Classes.Post;
import Kata.Classes.User;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Kata {
    private final ArrayList<User> Users;
    private final User CurrentUser;

    public Scanner scanner;

    public Kata(ArrayList<User> users, User currentUser) {
        Users = users;
        CurrentUser = currentUser;
        scanner = new Scanner(System.in).useDelimiter("\\n");
    }

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>(10);
        User currentUser = new User(new ArrayList<>(10), new  ArrayList<>(10), "Alice");

        initUserData(users);
        Kata kata = new Kata(users, currentUser);

        System.out.println("Hello, Welcome to your timeline " + kata.CurrentUser.UserName);
        kata.viewCurrentUserTimeLine();
        kata.startAction();
    }

    public static void initUserData(ArrayList<User> users) {
        if (users == null) return;
        User user1 = new User(new ArrayList<>(10), new  ArrayList<>(10), "Bob");
        User user2 = new User(new ArrayList<>(10), new  ArrayList<>(10), "Charlie");

        Post post1 = new Post(user1, new Date(new Date().getTime() - 100000), "Darn! We lost!");
        Post post2 = new Post(user1, new Date(new Date().getTime() - 500000), "Good game though.");
        Post post3 = new Post(user2, new Date(new Date().getTime() - 100000), "I'm in New York today! Anyone wants to have a coffee?");

        user1.Posts.add(post1);
        user1.Posts.add(post2);
        user2.Posts.add(post3);

        users.add(user1);
        users.add(user2);
    }

    public void startAction() {
        System.out.println("Please select your next action, " + this.CurrentUser.UserName);
        System.out.println("1) View timeline");
        System.out.println("2) Publish post");
        System.out.println("3) Search for a user to follow");
        System.out.println("4) Quit");
        int input = scanner.nextInt();
        String consoleInput;
        if (input == 1) {
            if(!viewCurrentUserTimeLine()) {
                System.out.println("There are no posts in your timeline");
            }
        }
        else if (input == 2) {
            System.out.println("Please input your post, " + this.CurrentUser.UserName);
            consoleInput = scanner.next();
            if(publish(consoleInput)) {
                if(!viewCurrentUserTimeLine()) {
                    System.out.println("There are no posts in your timeline");
                }
            }
        } else if (input == 3) {
            searchAndFollow();
        } else if (input == 4) {
            System.exit(0);
        }
        startAction();
    }

    public void searchAndFollow() {
        System.out.println("Please input your search, " + this.CurrentUser.UserName);
        String consoleInput = scanner.next().toLowerCase(Locale.ROOT);

        User foundUser = search(consoleInput);
        if (foundUser == null) {
            searchAndFollow();
        }
        System.out.println("User found: " + foundUser.UserName);
        System.out.println("Would you like to see users timeline? Y/N");
        consoleInput = scanner.next().toLowerCase(Locale.ROOT);
        if (Objects.equals(consoleInput, "y")) {
            if(!viewUserTimeline(foundUser)){
                System.out.println("There are no posts in user timeline");
            }
        }

        System.out.println("Would you like to follow? Y/N");
        consoleInput = scanner.next().toLowerCase(Locale.ROOT);
        if (Objects.equals(consoleInput, "y")) {
            if (follow(foundUser)) {
                System.out.println("Successfully followed user");
            } else {
                System.out.println("Could not follow user");
            }
        }
    }

    public boolean publish(String post) {
        if (post == null || post.isEmpty()) {
            System.out.println("unable to publish empty post...");
            return false;
        }
        Post postToAdd = new Post(this.CurrentUser, new Date(), post);
        this.CurrentUser.Posts.add(postToAdd);
        return true;
    }

    public boolean viewUserTimeline(User user) {
        if (user == null || user.Posts == null || user.Posts.isEmpty()) return false;
        ArrayList<Post> userPosts = user.Posts;
        Collections.sort(userPosts);
        return printTimeLine(userPosts);
    }

    public ArrayList<Post> currentUserTimeLinePosts() {
        ArrayList<Post> timeLinePosts = new  ArrayList<>(10);
        ArrayList<User> followers = this.CurrentUser.Followers;
        for (User follower:
                followers) {
            timeLinePosts.addAll(follower.Posts);
        }
        timeLinePosts.addAll(this.CurrentUser.Posts);
        Collections.sort(timeLinePosts);
        return timeLinePosts;
    }

    public boolean viewCurrentUserTimeLine() {
        ArrayList<Post> timeLinePosts = currentUserTimeLinePosts();
        return printTimeLine(timeLinePosts);
    }

    public boolean printTimeLine(ArrayList<Post> posts) {
        if (posts == null || posts.isEmpty()){
            return false;
        }
        for (Post post:
                posts) {
            long minutesAgo = getDateDifference(new Date(), post.PostedDate, TimeUnit.MINUTES);
            if (minutesAgo == -1 || post.Author == null|| post.Author.UserName == null ||
                    post.Author.UserName.isEmpty() ||
                    post.PostDetail == null || post.PostDetail.isEmpty()) {
                continue;
            }
            String timelinePost = post.Author.UserName + " - " + post.PostDetail +
                    " (" + minutesAgo + " minutes ago)";
            System.out.println(timelinePost);
        }
        return true;
    }

    public static long getDateDifference(Date date1, Date date2, TimeUnit timeUnit) {
        if (date1 == null || date2 == null || timeUnit == null) return -1;
        long differenceInMillies =  date1.getTime() - date2.getTime();
        return timeUnit.convert(differenceInMillies,TimeUnit.MILLISECONDS);
    }

    public User search(String username) {
        if (username == null || username.isEmpty()){
            System.out.println("username cannot be empty...");
            return null;
        }
        for (User user:
                Users) {
            if ( !this.CurrentUser.Blocked.contains(username) &&
                    Objects.equals(user.UserName.toLowerCase(Locale.ROOT), username)) {
                return user;
            }
        }
        System.out.println("User " + username +" could not be found...");
        return null;
    }

    public boolean isUserNullOrEmpty(User user) {
        return user == null || user.UserName == null || user.UserName.isEmpty();
    }

    public boolean follow(User user) {
        if (isUserNullOrEmpty(user)) return false;
        if(this.CurrentUser.Followers.contains(user)) {
            System.out.println("You are already following this user");
            return false;
        }
        this.CurrentUser.Followers.add(user);
        return true;
    }

    public boolean unfollow(User user) {
        if (this.CurrentUser.Followers.contains(user)) {
            this.CurrentUser.Followers.remove(user);
            return true;
        }
        return false;
    }

    public boolean block(User user) {
        this.CurrentUser.Blocked.add(user.UserName);
        return unfollow(user);
    }

    public void addUser(User user) {
        if(isUserNullOrEmpty(user)) return;
        this.Users.add(user);
    }

    public ArrayList<User> getUsers() {
        return this.Users;
    }
}
