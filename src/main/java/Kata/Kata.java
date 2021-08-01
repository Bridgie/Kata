package Kata;

import Kata.Classes.Post;
import Kata.Classes.User;

import java.util.ArrayList;
import java.util.Scanner;

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
        return true;
    }
    public boolean viewUserTimeline(User user) {
        return true;
    }
    public boolean viewCurrentUserTimeLine() {
        return true;
    }
    public boolean printTimeLine(ArrayList<Post> posts) {
        return true;
    }
    public User search(String username) {
        return null;
    }
    public boolean follow(User user) {
        return true;
    }
}
