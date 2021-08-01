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

    @Test
    void testPostComparatorBefore() {
        User user1 = new User(new ArrayList<>(), new ArrayList<>(), "test1");
        Post post1 = new Post(user1, new Date(), "test1");

        User user2 = new User(new ArrayList<>(), new ArrayList<>(), "test2");
        Post post2 = new Post(user2, new Date(new Date().getTime() - 10000), "test2");

        assertEquals(-1,post1.compareTo(post2));
    }

    @Test
    void testPostComparatorAfter() {
        User user1 = new User(new ArrayList<>(), new ArrayList<>(), "test1");
        Post post1 = new Post(user1, new Date(new Date().getTime() - 10000), "test1");

        User user2 = new User(new ArrayList<>(), new ArrayList<>(), "test2");
        Post post2 = new Post(user2, new Date(), "test2");

        assertEquals(1,post1.compareTo(post2));
    }

    @Test
    void testPostComparatorEquals() {
        User user1 = new User(new ArrayList<>(), new ArrayList<>(), "test1");
        Post post1 = new Post(user1, new Date(), "test1");

        User user2 = new User(new ArrayList<>(), new ArrayList<>(), "test2");
        Post post2 = new Post(user2, new Date(), "test2");

        assertEquals(0,post1.compareTo(post2));
    }
}
