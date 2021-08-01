package Kata;

import Kata.Classes.Post;
import Kata.Classes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KataTest {
    Kata testKata;
    ArrayList<User> users;
    User currentUser;
    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>(10);
        currentUser = new User(new ArrayList<>(10), new ArrayList<>(10), "Alice");

        user1 = new User(new ArrayList<>(10), new  ArrayList<>(10), "Bob");
        user2 = new User(new ArrayList<>(10), new  ArrayList<>(10), "Charlie");


        Post post1 = new Post(user1, new Date(new Date().getTime() - 100000), "Darn! We lost!");
        Post post2 = new Post(user1, new Date(new Date().getTime() - 500000), "Good game though.");
        Post post3 = new Post(user2, new Date(new Date().getTime() - 100000), "I'm in New York today! Anyone wants to have a coffee?");

        user1.Posts.add(post1);
        user1.Posts.add(post2);
        user2.Posts.add(post3);

        users.add(user1);
        users.add(user2);

        testKata = new Kata(users, currentUser);
    }
    @Test
    void testPublish() {
        String post = "I love the weather today";
        boolean actual = testKata.publish(post);
        assertTrue(actual);
        assertEquals(currentUser.Posts.get(0).PostDetail, post);
    }

    @Test
    void testPublishEmpty() {
        boolean actual = testKata.publish("");
        assertFalse(actual);
    }

    @Test
    void testPublishNull() {
        boolean actual = testKata.publish(null);
        assertFalse(actual);
    }

    @Test
    void testSearchFound() {
        User actual = testKata.search("bob");
        assertNotNull(actual);
    }

    @Test
    void testSearchNotFound() {
        User actual = testKata.search("john");
        assertNull(actual);
    }

    @Test
    void testSearchNull() {
        User actual = testKata.search(null);
        assertNull(actual);
    }

    @Test
    void testSearchEmpty() {
        User actual = testKata.search("");
        assertNull(actual);
    }

    @Test
    void testFollowTrue() {
        User testUser = new User(null, null, "Charlie");
        boolean actual = testKata.follow(testUser);
        assertTrue(actual);
        assertTrue(currentUser.Followers.contains(testUser));
    }

    @Test
    void testFollowFalse() {
        User testUser = new User(null, null, "Charlie");
        currentUser.Followers.add(testUser);

        boolean actual = testKata.follow(testUser);
        assertFalse(actual);
    }

    @Test
    void testFollowUserNull() {
        boolean actual = testKata.follow(null);
        assertFalse(actual);
        assertFalse(currentUser.Followers.contains(null));
    }

    @Test
    void testFollowUserNameNull() {
        User testUser = new User(null, null, null);
        boolean actual = testKata.follow(testUser);
        assertFalse(actual);
        assertFalse(currentUser.Followers.contains(testUser));
    }

    @Test
    void testFollowUserNameEmpty() {
        User testUser = new User(null, null, "");
        boolean actual = testKata.follow(testUser);
        assertFalse(actual);
        assertFalse(currentUser.Followers.contains(testUser));
    }
}
