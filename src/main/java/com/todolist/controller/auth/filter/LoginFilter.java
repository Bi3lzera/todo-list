package com.todolist.controller.auth.filter;

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

//@WebFilter("/*")
public class LoginFilter {

    public void doFilter(ServletRequest request, ServletResponse response)
            throws ServletException {

    }

    public void init(FilterConfig filterConfig) {

    }

    public void destroy() {

    }
}
