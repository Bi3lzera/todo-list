package com.todolist.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.todolist.dao.TarefaDAO;
import com.todolist.vo.TarefaVO;

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
        TarefaVO tm = new TarefaVO();
        tm.setTitle(request.getParameter("title"));
        tm.setDescription(request.getParameter("description"));

        try {
            String dataTexto = request.getParameter("plannedDate");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date data = formato.parse(dataTexto);

            tm.setPlannedDate(data);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        TarefaDAO tDao = new TarefaDAO();
        tDao.insert(tm);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}