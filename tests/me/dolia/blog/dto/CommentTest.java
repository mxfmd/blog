package me.dolia.blog.dto;

import org.junit.Test;

import java.util.ResourceBundle;

import static me.dolia.blog.dto.Comment.res;
import static org.junit.Assert.*;


public class CommentTest {

    private ResourceBundle resTest = ResourceBundle.getBundle("test_comment");


    @Test
    public void validate() {
        Comment comment = new Comment();
        assertFalse("Validation with empty comment was passed successfully.", comment.validate());
        assertNotNull("Should have error message", comment.getMessage());
        assertEquals(res.getString("author.empty"), comment.getMessage());

        comment = new Comment(resTest.getString("body.long"), null, "SomeAuthor");
        assertTrue("Validation with right input failed.", comment.validate());

        comment = new Comment(null, null, resTest.getString("author.long"));
        assertFalse("Validation with empty body was passed successfully.", comment.validate());
        assertNotNull("Should have error message", comment.getMessage());
        assertEquals(res.getString("author.wrong.length"), comment.getMessage());

        comment = new Comment("some body", null, resTest.getString("author.not.allowed.characters"));
        assertFalse("Validation with wrong characters in name was passed successfuly.", comment.validate());
        assertNotNull("Should have error message", comment.getMessage());
        assertEquals(res.getString("author.wrong.chars"), comment.getMessage());

    }


    @Test
    public void testJson() throws Exception {
        Comment comment = new Comment("some body", null, resTest.getString("author.not.allowed.characters"));
        assertEquals(resTest.getString("json.valid"), comment.toJson());
    }
}