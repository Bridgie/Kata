package Kata.Classes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KataClassesTest {
    @Test
    void testUserConstructor() {
        User user = new User(new ArrayList<>(), new ArrayList<>(), "test");
        assertNotNull(user.Followers);
        assertNotNull(user.Posts);
        assertEquals("test", user.UserName);
    }

    @Test
    void testPostConstructor() {
        User user = new User(new ArrayList<>(), new ArrayList<>(), "test");
        Post post = new Post(user, new Date(), "test");
        assertNotNull(post.Author);
        assertNotNull(post.PostedDate);
        assertEquals("test", post.PostDetail);
    }
}
