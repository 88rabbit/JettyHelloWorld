package io.github.mezk.main;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserProfileTest {

    private static UserProfile profile;

    @BeforeClass
    public static void createUserProfile() {
        profile = new UserProfile("User", "12345", "user@gmail.com");
    }

    @Test
    public void testGetLogin() {
        Assert.assertEquals("User", profile.getLogin());
    }

    @Test
    public void testGetPassword() {
        Assert.assertEquals("12345", profile.getPassword());
    }

    @Test
    public void testGetEmail() {
        Assert.assertEquals("user@gmail.com", profile.getEmail());
    }
}
