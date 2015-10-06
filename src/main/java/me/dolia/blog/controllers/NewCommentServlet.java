package me.dolia.blog.controllers;

import me.dolia.blog.dao.PostDAO;
import me.dolia.blog.dto.Comment;
import me.dolia.blog.dto.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NewCommentServlet extends HttpServlet {

    private PostDAO postDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isJSEnabled = req.getParameter("isJSEnabled") == null;
        String permalink = req.getParameter("permalink");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String commentBody = req.getParameter("commentBody");

        Post post = postDAO.findByPermalink(permalink);
        Comment comment = new Comment(commentBody, email, name);

        if (post == null) {
            resp.sendRedirect(resp.encodeRedirectURL("/"));
        }

        if (comment.validate()) {
            postDAO.addCommentToPost(comment, post);
        }

        if (isJSEnabled) {

            String json = comment.toJson();

            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();

            writer.write(json);
            writer.flush();
        } else {

            if (comment.getMessage() != null && !comment.getMessage().isEmpty()) {
                req.setAttribute("error_message", comment.getMessage());
            }

            req.getRequestDispatcher("/post/" + permalink).forward(req, resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("username") == null)
            resp.sendRedirect(resp.encodeRedirectURL("/login"));
        else
            doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        postDAO = new PostDAO();
    }
}
