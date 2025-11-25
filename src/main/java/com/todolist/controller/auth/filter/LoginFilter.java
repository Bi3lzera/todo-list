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

import com.todolist.connection.BDConnection;

//
//Algortimo para realizar o filtro de páginas.
//Tem a função de evitar que um usuário acesse qualquer página sem um login,
//ou pelo menos, sem uma sessão ativa.
//
@WebFilter("/*")
public class LoginFilter implements Filter {

    // Chama o handle específico do filter (doFilter)
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Coleta os dados request e reponse.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Busca a sessão ativa.
        HttpSession session = httpRequest.getSession(false);

        // Aqui será setado as páginas e recursos em que o usuário sem sessão ativa
        // poderá acessar.
        // No caso, será as routes de login e signup, responsáveis pelo login e
        // registro.
        // Também é necessário colocar de alguns recursos da página login, por exemplo o
        // arquivo CSS.
        String loginPath = httpRequest.getContextPath() + "/login";
        String loginPageURI = httpRequest.getContextPath() + "/views/login.jsp";
        String registerPath = httpRequest.getContextPath() + "/signup";
        String recoverPath = httpRequest.getContextPath() + "/recover";
        String cssURI = httpRequest.getContextPath() + "/css";
        String loginCSSURI = httpRequest.getContextPath() + "/css/login.css";

        boolean loggedIn = session != null && session.getAttribute("authUser") != null; // Especificamente essa boolean
                                                                                        // verifica se há sessão ativa e
                                                                                        // se houver, busca ela para
                                                                                        // liberar o acesso do usuário.
        boolean loginRequest = httpRequest.getRequestURI().equals(loginPath);
        boolean cssRequest = httpRequest.getRequestURI().startsWith(cssURI);
        boolean loginPageRequest = httpRequest.getRequestURI().equals(loginPageURI);
        boolean loginCSSRequest = httpRequest.getRequestURI().equals(loginCSSURI);
        boolean registerRequest = httpRequest.getRequestURI().equals(registerPath);
        boolean recoverRequest = httpRequest.getRequestURI().equals(recoverPath);

        // Verifica se qualquer uma das booleans acima é true.
        // Somente o que for true poderá passar e acessar a requisição.
        // No caso da bool loggedIn, se ela for true, irá liberar o acesso de todo o
        // sistema.
        if (loggedIn || loginRequest || cssRequest || loginPageRequest || loginCSSRequest || registerRequest
                || recoverRequest) {
            chain.doFilter(request, response);
        } else {
            // Caso nenhuma das bool acima seja true, ou seja, o usuário está tentando
            // acessar algo não permitido para ele, faz o redirecionamento dele para a tela
            // de login.
            httpResponse.sendRedirect(loginPath);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        BDConnection bdConn = new BDConnection();
        bdConn.createDatabase();
        bdConn.createTables();
    }

    @Override
    public void destroy() {
    }
}
