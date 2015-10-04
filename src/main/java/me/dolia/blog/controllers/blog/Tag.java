package me.dolia.blog.controllers.blog;

import me.dolia.blog.dao.PostDAO;
import me.dolia.blog.dto.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class Tag extends HttpServlet {

    private PostDAO postDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tag = request.getPathInfo().substring(1);

        List<Post> posts = postDAO.findByTagDateDescending(tag);

        request.setAttribute("posts", posts);

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void init() throws ServletException {
        postDAO = new PostDAO();
    }
}
