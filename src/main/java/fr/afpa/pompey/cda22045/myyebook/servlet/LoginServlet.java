package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    public void init() {
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // TODO : CHECK IF USER IS CONNECTED
        // if user connected redirect to user-acccount


        // TODO:
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response );


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
    }

}