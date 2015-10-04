package me.dolia.blog.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import me.dolia.blog.dto.Comment;
import me.dolia.blog.dto.Post;
import me.dolia.blog.utils.MongoUtil;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PostDAO {

    private MongoCollection<Document> collection;

    public PostDAO() {
        collection = MongoUtil.getMongoDB().getCollection("posts");
    }

    public List<Post> find(int limit) {

        FindIterable<Document> documents = collection.find().sort(Sorts.descending("date")).limit(limit);

        return mapDocsToPosts(documents);
    }

    public List<Post> findByTagDateDescending(String tag) {

        Bson filter = Filters.in("tags", tag);
        Bson sort = Sorts.descending("date");

        FindIterable<Document> documents = collection.find(filter).sort(sort);

        return mapDocsToPosts(documents);
    }


    public Post findByPermalink(String permalink) {

        Document doc = collection.find(new Document("permalink", permalink)).first();

        if (doc == null)
            return null;

        return mapDocToPost(doc);
    }

    public void addCommentToPost(Comment comment, Post post) {

        String author = comment.getAuthor();
        String commentBody = comment.getBody();
        String email = comment.getEmail();
        String permalink = post.getPermalink();

        Document doc = new Document("author", author).append("body", commentBody);

        if (email != null && !email.equals(""))
            doc.append("email", email);

        collection.findOneAndUpdate(new Document("permalink", permalink), new Document("$push", new Document("comments", doc)));
    }

    public String addPost(Post post) {

        String title = post.getTitle();
        String author = post.getAuthor();
        String body = post.getBody();
        List<String> tags = post.getTags();

        String permalink = title.toLowerCase().replaceAll("\\W", "_");

        Document doc = new Document("title", title)
                .append("body", body)
                .append("author", author)
                .append("permalink", permalink)
                .append("tags", tags)
                .append("comments", new ArrayList())
                .append("date", new Date());

        try {
            collection.insertOne(doc);
        } catch (Exception e) {

            //error occurred
            return null;
        }

        return permalink;
    }

    private List<Post> mapDocsToPosts(FindIterable<Document> documents) {
        List<Post> posts = new ArrayList<Post>();

        for (Document doc : documents)
            posts.add(mapDocToPost(doc));

        return posts;
    }


    private Post mapDocToPost(Document doc) {

        if (doc == null)
            return null;

        String body = doc.getString("body");
        String permalink = doc.getString("permalink");
        String author = doc.getString("author");
        String title = doc.getString("title");

        List<String> tags = null;

        if (doc.containsKey("tags")) {
            tags = new ArrayList<String>();
            ArrayList<String> tagsList = (ArrayList<String>) doc.get("tags");

            for (String tag : tagsList) {
                tags.add(tag);
            }
        }

        List<Comment> comments = null;

        if (doc.containsKey("comments")) {
            comments = new ArrayList<Comment>();
            ArrayList<Document> commentsList = (ArrayList<Document>) doc.get("comments");

            for (Document comment : commentsList) {
                String commentBody = comment.getString("body");
                String commentEmail = comment.getString("email");
                String commentAuthor = comment.getString("author");

                comments.add(new Comment(commentBody, commentEmail, commentAuthor));
            }
        }
        Date date = doc.getDate("date");

        return new Post(body, permalink, author, title, tags, comments, date);
    }
}
