package com.todolist.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todolist.dao.TarefaDAO;
import com.todolist.vo.TarefaVO;

@WebServlet("/tarefa")
public class TarefaController extends HttpServlet {

    private final TarefaDAO tarefaDAO = new TarefaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "create"; // Ação padrão para POST se não especificada
        }

        try {
            switch (action) {
                case "update":
                    updateTarefa(request, response);
                    break;
                case "delete":
                    deleteTarefa(request, response);
                    break;
                case "create":
                    createTarefa(request, response);
                    break;
                default:
                    break;
            }
        } catch (ParseException e) {
            // Idealmente, redirecionar para uma página de erro com uma mensagem amigável
            throw new ServletException("Erro ao converter a data", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Ação padrão para GET
        }

        switch (action) {
            case "list":
            default:
                try {
                    listTarefas(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Erro de banco de dados ao buscar tarefas", e);
                }
                break;
        }
    }

    private void listTarefas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<TarefaVO> listaDeTarefas = tarefaDAO.getAll();
        request.setAttribute("tarefas", listaDeTarefas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void createTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        TarefaVO tarefa = new TarefaVO();
        tarefa.setTitle(request.getParameter("title"));
        tarefa.setDescription(request.getParameter("description"));

        String dataTexto = request.getParameter("plannedDate");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date data = formato.parse(dataTexto);
        tarefa.setPlannedDate(data);

        tarefaDAO.insert(tarefa);

        // Redireciona de volta para a lista de tarefas para mostrar a lista atualizada
        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    private void updateTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        TarefaVO tarefa = new TarefaVO();
        // Para atualizar, precisamos do ID da tarefa
        int id = Integer.parseInt(request.getParameter("id"));
        tarefa.setId(id);
        tarefa.setTitle(request.getParameter("title"));
        tarefa.setDescription(request.getParameter("description"));

        String dataTexto = request.getParameter("plannedDate");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date data = formato.parse(dataTexto);
        tarefa.setPlannedDate(data);

        //tarefaDAO.update(tarefa); // Supondo que você tenha um método update no DAO
        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    private void deleteTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //tarefaDAO.delete(id); // Supondo que você tenha um método delete no DAO

        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
