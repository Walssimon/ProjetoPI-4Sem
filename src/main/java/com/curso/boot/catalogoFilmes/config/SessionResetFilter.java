package com.curso.boot.catalogoFilmes.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SessionResetFilter implements Filter {

    private boolean sessionCleared = false;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (!sessionCleared) {
            HttpSession session = ((jakarta.servlet.http.HttpServletRequest) request).getSession(false);
            if (session != null) {
                session.invalidate();
            }
            sessionCleared = true;
        }

        chain.doFilter(request, response);
    }
}
