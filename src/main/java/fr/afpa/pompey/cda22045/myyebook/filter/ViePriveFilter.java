package fr.afpa.pompey.cda22045.myyebook.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter("*")
@Slf4j
public class ViePriveFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        HttpServletResponse resp = (HttpServletResponse) response;
        if (session != null) {

            request.setAttribute("role", session.getAttribute("role"));
            if (session.getAttribute("role") == null || session.getAttribute("role").equals("")) {
                for (Cookie cookie : req.getCookies()) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
                session.invalidate();
            }
            log.info("ROLE VIE PRIVEE : SESSION NOT NULL");
        }
//        log.info("ROLE VIE PRIVEE :{}", req.getAttribute("role"));
        chain.doFilter(req, resp);
//        chain.doFilter(request, response);


    }
}
