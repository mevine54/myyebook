package fr.afpa.pompey.cda22045.myyebook.servlet.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet(name = "enregistrerClientServlet", value = "/client-enregistrer")
@Slf4j
public class ClientEnregistrerServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session != null) {
            String role = (String) session.getAttribute("role");
            log.info("ROLE:{}" ,role);
            if ("ROLE_CLIENT".equals(role)) {
                response.sendRedirect("accueil");
            } else if ("ROLE_LIBRAIRE".equals(role)) {
                response.sendRedirect("libraireinfo.jsp");
            }
            this.getServletContext().getRequestDispatcher("/JSP/page/clientenregistrer.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/JSP/page/clientenregistrer.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String utilisateur = request.getParameter("utilisateur");
        String mdp1 = request.getParameter("mdp1");
        String mdp2 = request.getParameter("mdp2");

        System.out.println("nom: " + nom + ", prenom: " + prenom + ", utilisateur: " + utilisateur + ", email: " + email + ", mdp: " + mdp1 + ", mdp2: " + mdp2);

        // Vérifier les informations d'inscription (à implémenter)
        boolean isRegistered = registerUser(nom, prenom, email, utilisateur, mdp1, mdp2);

        if (isRegistered) {
            response.sendRedirect("connexion.jsp");
        } else {
            response.sendRedirect("clientenregistrer.jsp?error=true");
        }
    }

    private boolean registerUser(String nom, String prenom, String email, String utilisateur, String mdp1, String mdp2) {
        // Implémentez la logique d'enregistrement ici
        return true; // Exemple de retour
    }

    @Override
    public void destroy() {
    }
}
