package Kata.Classes;

import java.util.Date;

public class Post implements Comparable<Post> {
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

    @Override
    public int compareTo(Post o) {
        return PostedDate.before(o.PostedDate) ? 1 : PostedDate.after(o.PostedDate) ? -1 : 0;
    }
}
