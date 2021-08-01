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

        InitUserData(users);
        Kata kata = new Kata(users, currentUser);

        System.out.println("Hello, Welcome to your timeline " + kata.CurrentUser.UserName);
        kata.viewCurrentUserTimeLine();
        kata.StartAction();
    }

    public static void InitUserData(ArrayList<User> users) {
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

    public void StartAction() {

    }

    public void searchAndFollow() {

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

    public boolean viewCurrentUserTimeLine() {
        ArrayList<Post> timeLinePosts = new  ArrayList<>(10);
        ArrayList<User> followers = this.CurrentUser.Followers;
        for (User follower:
                followers) {
            timeLinePosts.addAll(follower.Posts);
        }
        timeLinePosts.addAll(this.CurrentUser.Posts);
        Collections.sort(timeLinePosts);
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
            if (Objects.equals(user.UserName.toLowerCase(Locale.ROOT), username)) {
                return user;
            }
        }
        System.out.println("User " + username +" could not be found...");
        return null;
    }
    public boolean follow(User user) {
        if (user == null || user.UserName == null || user.UserName.isEmpty()) return false;
        if(this.CurrentUser.Followers.contains(user)) {
            System.out.println("You are already following this user");
            return false;
        }
        this.CurrentUser.Followers.add(user);
        return true;
    }
}
