package fr.afpa.pompey.cda22045.myyebook.servlet.client;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "enregistrerClientServlet", value = "/client-enregistrer")
public class ClientEnregistrerServlet extends HttpServlet {
    private ClientDAOImp clientDAOImp;

    @Override
    public void init() {
        clientDAOImp = new ClientDAOImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Client client;

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                client = clientDAOImp.get(id);
                if (client != null) {
                    request.setAttribute("client", client);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/JSP/page/clientenregistrer.jsp?error=invalid_id");
                return;
            }
        }
        request.getRequestDispatcher("/JSP/page/clientenregistrer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter("login");
//        String role = request.getParameter("role");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String utilisateur = request.getParameter("utilisateur");
        String mdp1 = request.getParameter("mdp1");
        String mdp2 = request.getParameter("mdp2");
        String adresse = request.getParameter("adresse");
        String ville = request.getParameter("ville");
        String cp = request.getParameter("cp");
        String role = request.getParameter("ROLE_CLIENT");


        // Validation des champs requis
        if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() ||
                email == null || email.isEmpty() || utilisateur == null || utilisateur.isEmpty() ||
                mdp1 == null || mdp1.isEmpty() || !mdp1.equals(mdp2)) {
            response.sendRedirect(request.getContextPath() + "/JSP/page/clientenregistrer.jsp?error=validation_failed");
            return;
        }

        try {
//            if (clientDAOImp.emailExiste(email)) {
//                request.setAttribute("errorMessage", "email exist deja");
//                request.getRequestDispatcher("/JSP/page/clientenregistrer.jsp").forward(request, response);
//            } else {}
            Client client = new Client();
            client.setLogin(utilisateur);
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(email);
            client.setPassword(mdp1);
            client.setAdresse(adresse);
            client.setVille(ville);
            client.setCodePostal(cp);



            clientDAOImp.insert(client);

            response.sendRedirect(request.getContextPath() + "/connexion?info=success");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/JSP/page/clientenregistrer.jsp?error=sql_exception");
        }
    }
}