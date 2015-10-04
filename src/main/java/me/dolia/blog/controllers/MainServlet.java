package me.dolia.blog.controllers;

import me.dolia.blog.dao.PostDAO;
import me.dolia.blog.dto.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MainServlet extends HttpServlet {

    private PostDAO postDAO;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postDAO.find(Integer.parseInt(getInitParameter("postsCount")));
        request.setAttribute("posts", posts);

        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        postDAO = new PostDAO();
    }
}
