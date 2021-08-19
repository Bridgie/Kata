package Kata;

import Kata.Classes.Post;
import Kata.Classes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    void testViewUserTimeline() {
        boolean actual = testKata.viewUserTimeline(user1);
        assertTrue(actual);
    }

    @Test
    void testViewUserTimelineUserNull() {
        boolean actual = testKata.viewUserTimeline(null);
        assertFalse(actual);
    }

    @Test
    void testViewUserTimelineUserPostsNull() {
        User testUser = new User(null, null, "");
        boolean actual = testKata.viewUserTimeline(testUser);
        assertFalse(actual);
    }

    @Test
    void testViewUserTimelineUserPostsEmpty() {
        User testUser = new User(null, new ArrayList<>(), "");
        boolean actual = testKata.viewUserTimeline(testUser);
        assertFalse(actual);
    }

    @Test
    void testViewCurrentUserTimeLine() {
        Post testPost = new Post(currentUser, new Date(), "test");
        currentUser.Posts.add(testPost);
        boolean actual = testKata.viewCurrentUserTimeLine();
        assertTrue(actual);
    }

    @Test
    void testViewCurrentUserTimeLineEmpty() {
        boolean actual = testKata.viewCurrentUserTimeLine();
        assertFalse(actual);
    }

    @Test
    void testPrintTimeLine() {
        boolean actual = testKata.printTimeLine(user1.Posts);
        assertTrue(actual);
    }

    @Test
    void testPrintTimeLinePostsNull() {
        boolean actual = testKata.printTimeLine(null);
        assertFalse(actual);
    }

    @Test
    void testPrintTimeLinePostsEmpty() {
        boolean actual = testKata.printTimeLine(new ArrayList<>());
        assertFalse(actual);
    }

    @Test
    void testGetDateDifference() {
        long actual = Kata.getDateDifference(new Date(), user1.Posts.get(0).PostedDate, TimeUnit.MINUTES);
        assertNotEquals(-1, actual);
        assertNotEquals(-1 * actual, actual);
    }

    @Test
    void testGetDateDifferenceNull() {
        long actual = Kata.getDateDifference(null, user1.Posts.get(0).PostedDate, TimeUnit.MINUTES);
        assertEquals(-1, actual);
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

    @Test
    void testUnfollowWillRemoveUserFromFollowers() {
        User testUser = new User(null, null, "testUser");
        boolean followed = testKata.follow(testUser);
        assertTrue(followed);
        assertTrue(currentUser.Followers.contains(testUser));
        boolean actual = testKata.unfollow(testUser);
        assertTrue(actual);
        assertFalse(currentUser.Followers.contains(testUser));
    }

    @Test
    void testUnfollowWillNotRemoveUserFromFollowersIfNotFollowed() {
        User testUser = new User(null, null, "testUser");
        boolean actual = testKata.unfollow(testUser);
        assertFalse(actual);
    }

    @Test
    void testUnfollowUserIsNullReturnsFalse() {
        User testUser = null;
        boolean actual = testKata.unfollow(testUser);
        assertFalse(actual);
    }

    @Test
    void testUnfollowUserAndViewCurrentUserTimeLineNoLongerContainsUnfollowedUsersPost() {
        User testUser = new User(null, new ArrayList<Post>(10), "testUser");
        Post testPost = new Post(testUser, new Date(), "test");
        testUser.Posts.add(testPost);

        boolean followed = testKata.follow(testUser);
        assertTrue(followed);
        assertTrue(currentUser.Followers.contains(testUser));

        ArrayList<Post> timelinePosts = testKata.currentUserTimeLinePosts();
        assertTrue(timelinePosts.contains(testPost));

        boolean unfollowed = testKata.unfollow(testUser);
        assertTrue(unfollowed);
        assertFalse(currentUser.Followers.contains(testUser));

        timelinePosts = testKata.currentUserTimeLinePosts();
        assertFalse(timelinePosts.contains(testPost));
    }
}
