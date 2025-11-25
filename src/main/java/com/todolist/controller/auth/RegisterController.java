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
//Algoritmo para registrar um usuário.
//
@WebServlet("/signup")
public class RegisterController extends HttpServlet {

    //Handle das requisições GET.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    //Handle das requisições POST.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    //Processa as requisições dos métodos POST e GET.
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        registerUser(request, response);
    }

    //Função para realizar o registro de um usuário.
    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //Coleta os dados e armazena em variáveis.
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Realizando registro de usuário: " + name);

        LoginDAO dao = new LoginDAO();
        UserVO user = new UserVO();

        //Tenta criar o objeto do usuário.
        try {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password, false); // hash internamente
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.println(e);
            response.sendRedirect(request.getContextPath() + "/login?error=2");
            return;
        }

        //Chama a função para criar o usuário da DAO.
        boolean created = dao.create(user);

        //Se a criação for um sucesso, chamada a função handleLogin para já realizar o login do usuário.
        if (created) {
            System.out.println("Usuário registrado: " + user.getName());
            response.sendRedirect(request.getContextPath() + "/login?signup=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/login?signup=failed");
        }
    }

    //Realiza o login do usuário de acordo com o registro feito na função registerUser.
    //DESATIVADA, pois achei não tão interessante, também verifiquei eventual problema com a não criação correta da sessão.
    //Além de que isso garante que o usuário digitou o e-mail e a senha corretamente.
    private void handleLoginSuccess(HttpServletRequest request, HttpServletResponse response, UserVO user) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("authUser", user);

        response.sendRedirect(request.getContextPath() + "/index");
    }
}
