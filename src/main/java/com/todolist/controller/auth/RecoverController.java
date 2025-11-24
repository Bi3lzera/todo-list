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

@WebServlet("/recover")
public class RecoverController extends HttpServlet {

    private final LoginDAO loginDAO = new LoginDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/recoverPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newpassword");

        if (email == null || email.trim().isEmpty() || newPassword == null || newPassword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/views/recoverPassword.jsp?error=1");
            return;
        }

        try {
            UserVO user = loginDAO.getUserByEmail(email);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/views/recoverPassword.jsp?error=2");
                return;
            }

            user.setPassword(newPassword, false);
            boolean ok = loginDAO.updateUser(user);
            if (ok) {
                response.sendRedirect(request.getContextPath() + "/login?reset=1");
            } else {
                response.sendRedirect(request.getContextPath() + "/views/recoverPassword.jsp?error=3");
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServletException("Erro ao processar senha", e);
        }
    }
}
