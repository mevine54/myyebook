package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createAdminServlet", value = "/create-admin")
public class AdminEnregistrerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Enregistrer l'admin (à implémenter)
        boolean isAdminCreated = createAdmin(username, password);

        if (isAdminCreated) {
            response.sendRedirect("adminCreated.jsp");
        } else {
            response.sendRedirect("createAdmin.jsp?error=true");
        }
    }

    private boolean createAdmin(String username, String password) {
        // Implémentez la logique de création de l'admin ici
        // Par exemple, enregistrez l'admin dans la base de données
        return true; // Exemple de retour
    }
}
