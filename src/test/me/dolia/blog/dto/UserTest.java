package me.dolia.blog.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class UserTest {

    private static ResourceBundle resTest = ResourceBundle.getBundle("test_user");
    private static User user;

    @Before
    public void createNewUser() {
        user = new User();
    }

    @Test
    public void validationShouldFailedWhenUsernameIsEmpty() throws Exception {
        assertFalse("Empty user should not be validated.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("name.empty"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenUsernameTooShort() throws Exception {
        user.setName(resTest.getString("string.short"));
        assertFalse("Name with less than 3 characters is valid, should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("name.wrong.length"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenUsernameTooLong() throws Exception {
        user.setName(resTest.getString("string.long"));
        assertFalse("Name with more than 20 characters is valid, should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("name.wrong.length"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenUsernameHasWrongChars() throws Exception {
        user.setName(resTest.getString("name.wrong.characters"));
        assertFalse("Username with wrong chars is valid, should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("name.not.match"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenPasswordIsEmpty() throws Exception {
        user.setName(resTest.getString("name.valid"));
        assertFalse("Empty password field is valid, should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("password.empty"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenPasswordTooShort() throws Exception {
        user.setName(resTest.getString("name.valid"));
        user.setPassword(resTest.getString("string.short"));
        assertFalse("Password with less than 3 characters is valid, should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("password.wrong.length"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenPasswordTooLong() throws Exception {
        user.setName(resTest.getString("name.valid"));
        user.setPassword(resTest.getString("string.long"));
        assertFalse("Password with more than 20 characters is valid, should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("password.wrong.length"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenSecondPasswordFieldIsEmpty() throws Exception {
        user.setName(resTest.getString("name.valid"));
        user.setPassword(resTest.getString("password.valid"));
        assertFalse("Validation passed successful when passwords do not match, should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("verify.empty"), user.getMessage());
    }


    @Test
    public void validationShouldFailedWhenPasswordsNotEqual() throws Exception {
        user.setName(resTest.getString("name.valid"));
        user.setPassword(resTest.getString("password.valid"));
        user.setVerify(resTest.getString("name.valid"));
        assertFalse("Validation passed successful when password are not equal. Should not.", user.validateSignup());
        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("verify.not.equal"), user.getMessage());
    }


    @Test
    public void ifEmailIsPresentItShouldMatch() throws Exception {
        user.setName(resTest.getString("name.valid"));
        user.setPassword(resTest.getString("password.valid"));
        user.setVerify(resTest.getString("password.valid"));
        String[] emails = resTest.getString("email.wrong").split(",");

        for (String email : emails) {
            user.setEmail(email);
            assertFalse("Not valid email passed validation. Should not.", user.validateSignup());
        }

        assertNotNull("Message empty.", user.getMessage());
        Assert.assertEquals("Message is wrong.", User.res.getString("email.not.match"), user.getMessage());
    }
}