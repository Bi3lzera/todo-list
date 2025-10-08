package com.todolist.controller;

import java.io.IOException;

import com.todolist.TarefaModel;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.todolist.dao.TarefaDAO;

@WebServlet("/tarefa")
public class TarefaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        TarefaModel tm = new TarefaModel();
        tm.setTitle("Arrumar a residência");
        tm.setDescription("Arrumar toda a residência antes que a mãe chegue.");
        tm.setCreationDate("06/10/2025");
        tm.setPlannedDate("10/11/2025");

        TarefaDAO tDao = new TarefaDAO();
        tDao.insert(tm);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}