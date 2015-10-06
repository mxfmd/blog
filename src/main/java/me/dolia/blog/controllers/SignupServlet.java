package me.dolia.blog.controllers;

import me.dolia.blog.dao.UserDAO;
import me.dolia.blog.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verify = request.getParameter("verify");
        String email = request.getParameter("email");

        User user = new User(username, password, verify, email);

        if (user.validateSignup() && userDAO.createUser(user)) {

            //good user
            request.getSession().setAttribute("username", username);
            response.sendRedirect(response.encodeRedirectURL("/"));

        } else {

            //bad sign up and duplicate users
            request.setAttribute("error_message", user.getMessage());
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("signup.jsp");
        view.forward(request, response);
    }

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

}
