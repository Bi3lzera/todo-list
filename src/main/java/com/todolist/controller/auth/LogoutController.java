package com.todolist.controller.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    //Handle das requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proccessRequest(request, response);
    }

    //Handle das requisições POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proccessRequest(request, response);
    }

    //Processa as requisições de Get e Post
    private void proccessRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logout(request, response);
    }

    //Função para realizar o deslogamento de um usuário.
    //O logout é realizado removendo o atributo salvo authUser salvo no navegador e invalidando a sessão.
    //Após isso o usuário é redirecionado a página de login. 
    //Com o authUser removido e a sessão invalidada, não é mais possível acessar qualquer página que não seja login e register.
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Busca pela sessão ativa.
        HttpSession session = request.getSession(false);

        //Verifica se a sessão não é nula, para evitar lançamento de exceção.
        if (session != null) {
            //Remove o atributo da página.
            session.removeAttribute("authUser");
            try {
                //Invalida a sessão.
                session.invalidate();
            } catch (IllegalStateException e) {

            }
        }

        //Redireciona para o login.
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
