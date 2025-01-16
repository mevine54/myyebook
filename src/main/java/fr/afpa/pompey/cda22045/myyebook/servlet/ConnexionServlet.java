package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "connexionServlet", value = "/connexion")
public class ConnexionServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
        this.getServletContext().getRequestDispatcher("/JSP/page/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String utilisateur = request.getParameter("utilisateur");
        String mdp = request.getParameter("mdp");
        System.out.println("utilisateur: " + utilisateur + " mdp: " + mdp);

        // Vérifier les informations d'identification (à implémenter)
        boolean isValid = validateCredentials(utilisateur, mdp);

        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("username", utilisateur);

            // Définir le rôle de l'utilisateur
            if ("admin".equals(utilisateur)) {
                session.setAttribute("role", "ROLE_ADMIN");
                response.sendRedirect("libraireinfo.jsp");
            } else {
                session.setAttribute("role", "ROLE_CLIENT");
                response.sendRedirect("moncompteclient.jsp");
            }
        } else {
            // Rediriger vers la page de connexion avec un message d'erreur
            response.sendRedirect("connexion.jsp?error=true");
        }
    }

    private boolean validateCredentials(String username, String password) {
        // Implémentez la logique de validation ici
        return "admin".equals(username) && "adminpass".equals(password) ||
                "client".equals(username) && "clientpass".equals(password);
    }

    @Override
    public void destroy() {
    }
}