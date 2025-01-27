package fr.afpa.pompey.cda22045.myyebook.securite;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//@WebFilter("*")
public class CSRFTokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Convertir en HttpServletRequest/HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("filtre csrf appele");
        HttpSession session = httpRequest.getSession(false);

        // Vérifier uniquement pour les requêtes sensibles (POST, PUT, DELETE)
        String method = httpRequest.getMethod();
        if ( session!=null && (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("DELETE"))) {
            // Récupérer le token CSRF envoyé par le client
            String csrfTokenFromClient = httpRequest.getParameter("csrfToken");

            // Récupérer le token CSRF stocké dans la session
            String csrfTokenFromServer = (String) session.getAttribute("csrfToken");
            System.out.println("filtre csrf verification");

            // Validation
            if (csrfTokenFromClient == null || !csrfTokenFromClient.equals(csrfTokenFromServer)) {
                // Rejet si le token est invalide ou absent
                System.out.println("filtre csrf invalide ou absent");
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
            }
        }
        chain.doFilter(request, response);

    }
}