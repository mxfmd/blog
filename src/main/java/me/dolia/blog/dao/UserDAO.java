package me.dolia.blog.dao;


import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import me.dolia.blog.dto.User;
import me.dolia.blog.utils.MongoUtil;
import org.bson.Document;

import java.util.ResourceBundle;


public class UserDAO {

    public static ResourceBundle res = ResourceBundle.getBundle("userdao_en");

    private MongoCollection<Document> collection;

    public UserDAO() {
        init();
    }

    private void init() {
        collection = MongoUtil.getMongoDB().getCollection("users");
    }

    public boolean validateLogin(User user) {

        String username = user.getName();
        String password = user.getPassword();

        Document filter = new Document("_id", username).append("password", password);

        Document doc = collection.find(filter).first();

        if (doc == null) {

            //invalid login
            user.setMessage(res.getString("message.invalid.login"));
            return false;
        }

        return true;
    }


    public boolean createUser(User user) {

        String name = user.getName();
        String password = user.getPassword();
        String email = user.getEmail();

        Document doc = new Document("_id", name).append("password", password);

        if (email != null && !email.isEmpty())
            doc.append("email", email);

        try {

            collection.insertOne(doc);
            return true;

        } catch (MongoException e) {

            //username already in use
            user.setMessage(res.getString("message.duplicate.users"));
            return false;
        }
    }


    public boolean deleteUser(String username) {

        Document userMatch = new Document("_id", username);

        try {
            collection.findOneAndDelete(userMatch);
            return true;
        } catch (MongoException e) {
            return false;
        }

    }

}
