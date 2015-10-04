package me.dolia.blog.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ResourceBundle;


public class Comment {

    public static ResourceBundle res = ResourceBundle.getBundle("comment");

    private String author;
    private String email;
    private String body;
    private String message;


    public Comment() {
        //NOP
    }


    public Comment(String body, String email, String author) {
        this.body = body;
        this.email = email;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean validate() {

        if (author == null || author.isEmpty()) {
            message = res.getString("author.empty");
            return false;
        }

        if (author.length() < 3 || author.length() > 20) {
            message = res.getString("author.wrong.length");
            return false;
        }

        if (!author.matches("^[a-zA-Z0-9_\\-]{3,20}$")) {
            message = res.getString("author.wrong.chars");
            return false;
        }

        if (email != null && !email.isEmpty() && !email.matches("^[\\S]+@[\\S]+\\.[\\S]+$")) {
            message = res.getString("email.wrong");
            return false;
        }

        if (body == null || body.isEmpty()) {
            message = res.getString("body.empty");
            return false;
        }

        return true;
    }

    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
