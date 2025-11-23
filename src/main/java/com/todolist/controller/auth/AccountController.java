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

@WebServlet("/account")
public class AccountController extends HttpServlet {

    private final LoginDAO loginDAO = new LoginDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authUser") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        UserVO authUser = (UserVO) session.getAttribute("authUser");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserVO user = new UserVO();
        user.setUserId(authUser.getUserId());
        user.setName(name != null ? name : authUser.getName());
        user.setEmail(email != null ? email : authUser.getEmail());

        try {
            if (password != null && !password.trim().isEmpty()) {
                user.setPassword(password, false); // will hash
            } else {
                // keep existing hashed password
                user.setPassword(authUser.getHashedPassword(), true);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServletException("Erro ao processar senha", e);
        }

        boolean ok = loginDAO.updateUser(user);
        if (ok) {
            // refresh session user (do not include raw password)
            session.setAttribute("authUser", user);
            response.sendRedirect(request.getContextPath() + "/views/account.jsp?updated=1");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/account.jsp?error=1");
        }
    }
}
