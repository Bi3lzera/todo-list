package com.todolist.controller.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todolist.dao.LoginDAO;
import com.todolist.vo.UserVO;

//
//Algoritmo para gerenciar uma conta já criada.
//Contém funções para alterar dados da conta.
//
@WebServlet("/account")
public class AccountController extends HttpServlet {

    private final LoginDAO loginDAO = new LoginDAO();

    // Handle das requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/account.jsp").forward(request, response);
    }

    // Handle das requisições POST
    // Não foi criada uma função "processRequest", pois quis separar o Post do Get.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica se há sessão ativa com o usuário atual.
        // Se não houver, redireciona para a página de login.
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Variáveis
        UserVO authUser = (UserVO) session.getAttribute("authUser");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserVO user = new UserVO();
        user.setUserId(authUser.getUserId());
        user.setName(name != null ? name : authUser.getName());
        user.setEmail(email != null ? email : authUser.getEmail());

        // Verifica se precisar realizar a alteração da senha.
        // Caso a senha seja nula, ou seja, não foi digitado na tela uma outra senha,
        // ele não irá executar a alteração,
        // caso sim, a nova senha será alterada no BD.
        try {
            if (password != null && !password.trim().isEmpty()) {
                // Altera a senha para a nova digitada.
                user.setPassword(password, false);
            } else {
                // Mantém a senha atual
                user.setPassword(authUser.getHashedPassword(), true);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServletException("Erro ao processar senha", e);
        }

        // Executa a função de updateUser do DAO.
        boolean ok = loginDAO.updateUser(user);
        if (ok) {
            // Se tudo der certo, atualiza o Attribute do usuário no navegador e direciona
            // uma mensagem para usuário de sucesso.
            session.setAttribute("authUser", user);
            response.sendRedirect(request.getContextPath() + "/account?updated=1");
        } else {
            // Se tudo der errado, não altera o atributo do navegado e envia mensagem de
            // erro ao usuário.
            response.sendRedirect(request.getContextPath() + "/account?error=1");
        }
    }
}
