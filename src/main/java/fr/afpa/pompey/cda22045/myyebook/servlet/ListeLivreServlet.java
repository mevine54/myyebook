package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ListeLivreServlet", value = "/ListeLivre-servlet")
public class ListeLivreServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/JSP/page/list_livres.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
    }

}

