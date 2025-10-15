// src/main/java/com/todolist/controller/auth/filter/LoginFilter.java
package com.todolist.controller.auth.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String loginPath = httpRequest.getContextPath() + "/login";
        String loginPageURI = httpRequest.getContextPath() + "/views/login.jsp";
        String cssURI = httpRequest.getContextPath() + "/css";
        String loginCSSURI = httpRequest.getContextPath() + "/css/login.css";

        boolean loggedIn = session != null && session.getAttribute("authUser") != null;
        boolean loginRequest = httpRequest.getRequestURI().equals(loginPath);
        boolean cssRequest = httpRequest.getRequestURI().startsWith(cssURI);
        boolean loginPageRequest = httpRequest.getRequestURI().equals(loginPageURI);
        boolean loginCSSRequest = httpRequest.getRequestURI().equals(loginCSSURI);

        if (loggedIn || loginRequest || cssRequest || loginPageRequest || loginCSSRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginPath);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
