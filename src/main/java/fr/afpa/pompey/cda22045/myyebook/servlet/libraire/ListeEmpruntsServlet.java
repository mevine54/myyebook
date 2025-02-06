package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;
import fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao.EmprunterDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.reservationdao.ReservationDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Emprunter;
import fr.afpa.pompey.cda22045.myyebook.model.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListeEmpruntsServlet", value = "/ListeEmprunts")
public class ListeEmpruntsServlet extends HttpServlet {
    EmprunterDAOImpl emprunterDAOImpl = new EmprunterDAOImpl();

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession(false);
//        session.setAttribute("csrfToken", CSRFTokenUtil.generateCSRFToken());

        try {
            List<Emprunter> emprunterList = emprunterDAOImpl.getAll();
            request.setAttribute("emprunter", emprunterList);
            //Récupérer l'url du site
            String currentURL = request.getRequestURL().toString();
            //Enregistre l'url dans la variable et envoye à la page JSP
            request.setAttribute("currentURL", currentURL);
            this.getServletContext().getRequestDispatcher("/JSP/page/listeEmprunts.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Impossible d'obtenir les informations dans la base de données", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String info = request.getParameter("info");
        Integer id = Integer.valueOf(request.getParameter("id"));
        //Verifie si les valeurs ne sont pas nulles
        try{
            if (!(info == null) && !(id == null)) {
                if (info.equals("valider")) {
                    validerEmprunts(id);
                    response.sendRedirect(request.getContextPath() + "/ListeEmprunts?info=valider");
                } else if (info.equals("rendre")) {
                    rendreEmprunts(id);
                    response.sendRedirect(request.getContextPath() + "/ListeEmprunts?info=rendre");
                } else {
                    response.sendRedirect(request.getContextPath() + "/accueil");
                }
            }else {
            response.sendRedirect(request.getContextPath() + "/accueil");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy () {

    }

    private void validerEmprunts (Integer id){
        try {
            Emprunter emprunter = verifieSiLEmpruntExiste(id);
            if(emprunter.getDatetimeRetour() == null) {
                emprunter.setDatetimeRetour(java.time.LocalDateTime.now());
                emprunterDAOImpl.update(emprunter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void rendreEmprunts (Integer id) {
        ReservationDAOImp reservationDAOImp = new ReservationDAOImp();
        try {
            Emprunter emprunter = verifieSiLEmpruntExiste(id);
            if(emprunter.getDatetimeRetour() != null) {
                emprunterDAOImpl.delete(id);
                reservationDAOImp.delete(emprunter.getReservation().getResId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Emprunter verifieSiLEmpruntExiste (Integer id) {
        try {
            return emprunterDAOImpl.get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}