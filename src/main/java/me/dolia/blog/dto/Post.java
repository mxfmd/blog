package me.dolia.blog.dto;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class Post {

    public static ResourceBundle res = ResourceBundle.getBundle("post");

    private String body;
    private String permalink;
    private String author;
    private String title;
    private List<String> tags;
    private List<Comment> comments;
    private Date date;
    private String message;

    public Post() {
        //NOP
    }

    public Post(String body, String permalink, String author, String title, List<String> tags, List<Comment> comments, Date date) {
        this.body = body;
        this.permalink = permalink;
        this.author = author;
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.date = date;
    }

    public Post(String title, String body, List<String> tags, String author) {
        this.body = body;
        this.author = author;
        this.title = title;
        this.tags = tags;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean validate() {

        if (title == null || title.isEmpty()) {
            message = res.getString("title.empty");
            return false;
        }

        if (title.length() > 255) {
            message = res.getString("title.too.long");
            return false;
        }

        if (body == null || body.isEmpty()) {
            message = res.getString("body.empty");
            return false;
        }

        return true;
    }
}
