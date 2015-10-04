package me.dolia.blog.controllers.blog;

import me.dolia.blog.dao.PostDAO;
import me.dolia.blog.dto.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BlogPostServlet extends HttpServlet {

    private PostDAO postDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String permalink = req.getPathInfo().substring(1);

        Post post = postDAO.findByPermalink(permalink);

        req.setAttribute("post", post);

        req.getRequestDispatcher("/post.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {

        postDAO = new PostDAO();

    }
}
