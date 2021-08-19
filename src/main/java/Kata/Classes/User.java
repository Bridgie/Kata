package Kata.Classes;

import java.util.ArrayList;

public class User {
    public ArrayList<User> Followers;
    public ArrayList<String> Blocked;
    public ArrayList<Post> Posts;
    public String UserName;

    public User(ArrayList<User> followers,  ArrayList<Post> posts, String userName) {
        Followers = followers;
        Blocked = new ArrayList<>();
        Posts = posts;
        UserName = userName;
    }
}
