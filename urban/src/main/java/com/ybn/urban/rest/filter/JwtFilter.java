package com.ybn.urban.rest.filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ybn.urban.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@Order(1)
@Component
public class JwtFilter implements Filter {
    @Autowired
    private JWTVerifier verifier;
    @Autowired
    private AntPathMatcher matcher;
    @Autowired
    private AppConfig config;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (this.shouldVerifyJwt(request)) {
            String authorization = request.getHeader("Authorization");
            if (authorization == null || authorization.equals("")) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                return;
            }

            String [] tab = authorization.split(" ");
            if (tab.length != 2) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                return;
            }

            try {
                this.verifier.verify(tab[1]);
            } catch(JWTVerificationException e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                return;
            }
        }
        // access granted chain to next filter
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean shouldVerifyJwt(HttpServletRequest request) {
        return !Arrays.stream(config.getUnsecuredPath()).anyMatch(p -> this.matcher.match(p, request.getRequestURI()));
    }
}
