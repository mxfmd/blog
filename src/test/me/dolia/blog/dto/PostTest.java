package me.dolia.blog.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class PostTest {

    private static ResourceBundle resTest = ResourceBundle.getBundle("test_post");

    private Post post;

    @Before
    public void setUp() throws Exception {
        post = new Post();
    }

    @Test
    public void shouldFailWhenTitleIsEmpty() throws Exception {
        assertFalse("Empty post validated successfully. Should not", post.validate());
        assertNotNull("Message is empty.", post.getMessage());
        Assert.assertEquals("Message is wrong.", Post.res.getString("title.empty"), post.getMessage());
    }


    @Test
    public void shouldFailWhenTitleHasMoreThan255Characters() throws Exception {
        post.setTitle(resTest.getString("title.long"));
        assertFalse("Title with more than 255 chars validated successfully. Should not", post.validate());
        assertNotNull("Message is empty.", post.getMessage());
        Assert.assertEquals("Message is wrong.", Post.res.getString("title.too.long"), post.getMessage());
    }


    @Test
    public void shouldFailWhenBodyIsEmpty() throws Exception {
        post.setTitle(resTest.getString("title.valid"));
        assertFalse("Post with empty body validated successfully. Should not", post.validate());
        assertNotNull("Message is empty.", post.getMessage());
        Assert.assertEquals("Message is wrong.", Post.res.getString("body.empty"), post.getMessage());
    }
}