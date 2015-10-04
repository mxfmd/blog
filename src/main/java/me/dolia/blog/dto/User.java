package me.dolia.blog.dto;

import java.util.ResourceBundle;

public class User {

    public static ResourceBundle res = ResourceBundle.getBundle("user_en");

    private String name;
    private String password;
    private String verify;
    private String email;
    private String message;

    public User() {
        //NOP
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public User(String name, String password, String verify, String email) {
        this.name = name;
        this.password = password;
        this.verify = verify;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean validateLogin() {
        return validateName() && validatePassword();
    }


    public boolean validateSignup() {

        if (!validateLogin()) return false;

        if (verify == null || verify.isEmpty()) {
            message = res.getString("verify.empty");
            return false;
        }

        if (!password.equals(verify)) {
            message = res.getString("verify.not.equal");
            return false;
        }

        if (email != null && !email.isEmpty() && !email.matches("^[\\S]+@[\\S]+\\.[\\S]+$")) {
            message = res.getString("email.not.match");
            return false;
        }

        return true;
    }


    private boolean validateName() {
        if (name == null || name.isEmpty()) {
            message = res.getString("name.empty");
            return false;
        }

        if (name.length() < 3 || name.length() > 20) {
            message = res.getString("name.wrong.length");
            return false;
        }

        if (!name.matches("[a-zA-Z0-9_\\-]+")) {
            message = res.getString("name.not.match");
            return false;
        }
        return true;
    }


    private boolean validatePassword() {
        if (password == null || password.isEmpty()) {
            message = res.getString("password.empty");
            return false;
        }

        if (password.length() < 3 || password.length() > 20) {
            message = res.getString("password.wrong.length");
            return false;
        }
        return true;
    }
}
