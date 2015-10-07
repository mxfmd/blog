package me.dolia.blog.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

public class MongoUtil {

    private static final boolean LOCALHOST = false;

    private static MongoDatabase db;

    public static MongoDatabase getMongoDB() {

        if (db == null) {
            Properties properties = new Properties();
            String host = "";
            int port = 0;
            String username = "";
            String password = "";
            String dbname = "";
            if (LOCALHOST) {
                try {
                    properties.load(MongoUtil.class.getClassLoader().getResourceAsStream("localhost.properties"));
                    host = properties.getProperty("host");
                    port = Integer.parseInt(properties.getProperty("port"));
                    username = properties.getProperty("username");
                    password = properties.getProperty("password");
                    dbname = properties.getProperty("dbname");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    properties.load(MongoUtil.class.getClassLoader().getResourceAsStream("mongodb.properties"));

                    host = System.getenv(properties.getProperty("host"));
                    port = Integer.parseInt(System.getenv(properties.getProperty("port")));
                    username = System.getenv(properties.getProperty("username"));
                    password = System.getenv(properties.getProperty("password"));
                    dbname = System.getenv(properties.getProperty("dbname"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            MongoCredential credential = MongoCredential.createCredential(username, dbname, password.toCharArray());
            ServerAddress address = new ServerAddress(host, port);

            MongoClient mongoClient = new MongoClient(address, Collections.singletonList(credential));
            db = mongoClient.getDatabase(dbname);
        }
        return db;
    }
}