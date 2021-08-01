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

    }
    public static void InitUserData(ArrayList<User> users) {

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
        return true;
    }
    public boolean viewCurrentUserTimeLine() {
        return true;
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
        return 0;
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
