package com.todolist.controller.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todolist.dao.LoginDAO;
import com.todolist.vo.UserVO;

//
//Algortimo dedicado para realizar a recuperação da senha do usuário.
//

@WebServlet("/recover")
public class RecoverController extends HttpServlet {

    private final LoginDAO loginDAO = new LoginDAO();

    // Handle das requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/recoverPassword.jsp").forward(request, response);
    }

    // Handle das requisições POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        recoverPassword(request, response);
    }

    // Função para recuperar a senha do usuário.
    private void recoverPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtem e armazena em variáveis os valores de email e a nova senha fornecida.
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newpassword");

        // Válida se as informações foram preenchidas, pra não dar caca no BD, mesmo que
        // no html ele exija o preenchimento dos campos.
        if (email == null || email.trim().isEmpty() || newPassword == null || newPassword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/views/recoverPassword.jsp?error=1");
            return;
        }

        // Tenta recuperar a senha alterando no BD.
        try {

            // Primeiro verifica se o email existe e se não existir retorna um erro.
            UserVO user = loginDAO.getUserByEmail(email);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/views/recoverPassword.jsp?error=2");
                return;
            }

            // Altera a senha do usuário no BD.
            user.setPassword(newPassword, false);
            boolean ok = loginDAO.updateUser(user); // Executa a função SQL e retorn um bool se houve sucesso.

            // Verifica se a operação foi bem sucedida e redireciona como sucesso para
            // realizar o login ou um erro.
            if (ok) {
                response.sendRedirect(request.getContextPath() + "/login?recover=1");
            } else {
                response.sendRedirect(request.getContextPath() + "/views/recoverPassword.jsp?error=3");
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServletException("Erro ao processar senha", e);
        }
    }
}
