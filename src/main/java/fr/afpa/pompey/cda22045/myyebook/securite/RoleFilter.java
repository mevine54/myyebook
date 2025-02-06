package fr.afpa.pompey.cda22045.myyebook.securite;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebFilter("/*")
@Slf4j
public class RoleFilter implements Filter {

    private Map<String, Set<String>> roleToUriMap = new HashMap<>();
    Set<String> publicUris = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Définir les URI autorisées pour chaque rôle
        Set<String> libraireUris = new HashSet<>();
        libraireUris.add("/CreeUnAuteur");
        libraireUris.add("/CreeUneCategorie");
        libraireUris.add("/CreeUnLivre");
        libraireUris.add("/libraire-enregistrer");
        libraireUris.add("/ModifLibraire");
        libraireUris.add("/ListeAuteur");
        libraireUris.add("/ListeCategorie");
        libraireUris.add("/ListeClient");
        libraireUris.add("/ListeLibraire");
        libraireUris.add("/ListeLivreEmprunter");
        libraireUris.add("/ListeLivre");
        libraireUris.add("/LivreModification");
        libraireUris.add("/ModifAuteur");
        libraireUris.add("/ModifCategorie");
        libraireUris.add("/ModifClient");
        libraireUris.add("/monCompteLibraire");
        libraireUris.add("/ListeEmprunts");
        libraireUris.add("/deconnexion");

        Set<String> libraireAttenteUris = new HashSet<>();
        libraireAttenteUris.add("/LibraireAttente");
        libraireAttenteUris.add("/deconnexion");

        Set<String> clientUris = new HashSet<>();
        clientUris.add("/mesemprunts");
        clientUris.add("/monCompteClient");
        clientUris.add("/deconnexion");
        clientUris.add("/accueil");
        clientUris.add("/index.jsp");
        clientUris.add("/");
        clientUris.add("/livre");

        roleToUriMap.put("ROLE_LIBRAIRE", libraireUris);
        roleToUriMap.put("ROLE_LIBRAIRE_ATTENTE", libraireAttenteUris);
        roleToUriMap.put("ROLE_CLIENT", clientUris);

        publicUris.add("/");
        publicUris.add("/accueil");
        publicUris.add("/index.jsp");
        publicUris.add("/livre");
        publicUris.add("/connexion");
        publicUris.add("/client-enregistrer");



//        /accueil , index.jsp , /livre

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String role =   null;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String contextPath = httpRequest.getContextPath();

        String uri = httpRequest.getRequestURI().replace(contextPath, "") ;
        HttpSession session = httpRequest.getSession(false);

        if (session!=null){
            role = (String) session.getAttribute("role");
        }

        log.info("ROLE: {}",role);
        log.info("URI: {}", uri );
        log.info("context: {}", contextPath);
        log.info( "pathinfo: {}", httpRequest.getPathInfo() );
        request.getParameterMap().forEach((key, value) -> log.info("REQUETE PARAMETER key: {} value: {}", key, value));

        if (  (role == null && !publicUris.contains( uri))  &&  !uri.startsWith("/assets/")) {
            log.info("URI 401: {}", uri );
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "401 Unauthorized");
        }
        else if (  (role != null && !isUriAllowedForRole(role, uri))  &&  !uri.startsWith("/assets/") ) {
            log.info("URI 403: {}", uri );
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "403 Forbidden");
        }
        else{
            chain.doFilter(request, response);
        }

    }

    private boolean isUriAllowedForRole(String role, String uri) {
        Set<String> allowedUris = roleToUriMap.get(role);
        return allowedUris != null && allowedUris.contains(uri);
    }




    @Override
    public void destroy() {
        // Nettoyage si nécessaire
    }
}