package com.todolist.controller.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todolist.dao.LoginDAO;
import com.todolist.vo.UserVO;

@WebServlet("/signup")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Realizando registro de usuário: " + name);

        LoginDAO dao = new LoginDAO();
        UserVO user = new UserVO();
        try {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password, false); // hash internamente
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=1");
            return;
        }

        boolean created = dao.create(user);
        if (created) {
            System.out.println("Usuário registrado: " + user.getName());
            handleLoginSuccess(request, response, user);
        } else {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp?signup=failed");
        }
    }

    private void handleLoginSuccess(HttpServletRequest request, HttpServletResponse response, UserVO user) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("authUser", user);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void handleLoginFailure(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=1");
    }
}
