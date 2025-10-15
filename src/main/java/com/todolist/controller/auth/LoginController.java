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

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse respose)
            throws ServletException, IOException {
        LoginDAO login = new LoginDAO();
        UserVO loggedUser = login.Login(request.getParameter("email"), request.getParameter("password"));

        if (loggedUser != null) {
            System.out.println("Usuário logado: " + loggedUser.getName());
            handleLoginSuccess(request, respose, loggedUser);
        } else {
            handleLoginFailure(request, respose);
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
