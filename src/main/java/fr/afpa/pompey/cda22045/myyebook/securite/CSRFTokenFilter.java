package fr.afpa.pompey.cda22045.myyebook.securite;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebFilter("*")
@Slf4j
public class CSRFTokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String contextPath = ((HttpServletRequest) request).getContextPath();
        List<String> routesAProtege = List.of(
                contextPath + "/CreeUnAuteur",
                contextPath + "/CreeUneCategorie",
                contextPath + "/CreeUnLivre",
                contextPath + "/libraire-enregistrer",
                contextPath + "/ModifLibraire",
                contextPath + "/ListeAuteur",
                contextPath + "/ListeCategorie",
                contextPath + "/ListeClient",
                contextPath + "/ListeLibraire",
                contextPath + "/ListeLivreEmprunter",
                contextPath + "/ListeLivre",
                contextPath + "/LivreModification",
                contextPath + "/ModifAuteur",
                contextPath + "/modifAuteur",
                contextPath + "/ModifCategorie",
                contextPath + "/ModifLibraire",
                contextPath + "/ModifClient",
                contextPath + "/monCompteLibraire",
                contextPath + "/mesemprunts",
                contextPath + "/monCompteClient"
        );
        // Convertir en HttpServletRequest/HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = ((HttpServletRequest) request).getRequestURI();


        System.out.println("filtre csrf appele");
        // TODO: Mettre session a false
//        HttpSession session = httpRequest.getSession(false);
        HttpSession session = httpRequest.getSession(true);

        // Vérifier uniquement pour les requêtes sensibles (POST, PUT, DELETE)
        String method = httpRequest.getMethod();
        log.info(method);
        if (session != null && (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("DELETE"))  && routesAProtege.contains(requestURI) )  {
            // Récupérer le token CSRF envoyé par le client
            log.info("REQUETE: {} ",String.valueOf(request));
            String csrfTokenFromClient = httpRequest.getParameter("csrf");

            // Récupérer le token CSRF stocké dans la session
            String csrfTokenFromServer = (String) session.getAttribute("csrfToken");
            System.out.println("filtre csrf verification");
            log.info(csrfTokenFromClient);
            log.info(csrfTokenFromServer);
            // Validation
            if (csrfTokenFromClient == null || !csrfTokenFromClient.equals(csrfTokenFromServer)) {
                // Rejet si le token est invalide ou absent
                System.out.println("filtre csrf invalide ou absent");
                if (!httpResponse.isCommitted()) {
                    // Envoyer une réponse d'erreur 403 Forbidden
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
                } else {
                    // Si la réponse est déjà engagée, réinitialisez la réponse
                    httpResponse.reset();
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
                }
                return;
            }
        } else if (session != null && (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("HEAD")) && routesAProtege.contains(requestURI)) {
            log.info("request uri : " + requestURI);
            String uuidStr = UUID.randomUUID().toString();
            session.setAttribute("csrfToken", uuidStr);
        }
        chain.doFilter(request, response);

    }
}