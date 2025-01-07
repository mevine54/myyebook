package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "connexionServlet", value = "/connexion")
public class ConnexionServlet extends HttpServlet {


    @Override
    public void init() {
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // TODO : CHECK IF USER IS CONNECTED
        // if user connected redirect to user-acccount



        // TODO:
        this.getServletContext().getRequestDispatcher("/JSP/page/connexion.jsp").forward(request, response );


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
    }

}