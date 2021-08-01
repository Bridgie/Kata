package Kata.Classes;

import java.util.Date;

public class Post {
    public int Likes;
    public int Views;
    public User Author;
    public Date PostedDate;
    public String PostDetail;

    public Post(User author, Date postedDate, String postDetail) {
        Author = author;
        PostedDate = postedDate;
        PostDetail = postDetail;
    }
}
