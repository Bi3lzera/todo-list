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
import com.todolist.vo.UserVO;

@WebServlet("/tarefa")
public class TarefaController extends HttpServlet {

    private final TarefaDAO tarefaDAO = new TarefaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        System.out.println("Recebido solicitação GET no TarefaController, action: " + action);

        if (action == null) {
            action = "list"; // padrão
        }

        try {
            switch (action) {

                case "delete":
                    deleteTarefa(request, response);
                    break;

                case "confirmDelete":
                    showConfirmDelete(request, response);
                    break;

                case "list":
                default:
                    listTarefas(request, response);
                    break;
            }

        } catch (Exception e) {
            throw new ServletException("Erro ao processar requisição GET", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        System.out.println("Recebido solicitação POST no TarefaController, action: " + action);

        if (action == null) {
            action = "list"; // fallback
        }

        try {
            switch (action) {

                case "create":
                    createTarefa(request, response);
                    break;

                case "update":
                    updateTarefa(request, response);
                    break;

                case "complete":
                    completeTarefa(request, response);
                    break;

                case "undone":
                    undoneTarefa(request, response);
                    break;
                default:
                    listTarefas(request, response);
                    break;
            }

        } catch (Exception e) {
            throw new ServletException("Erro ao processar requisição POST", e);
        }
    }

    private void listTarefas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        UserVO authUser = (UserVO) request.getSession().getAttribute("authUser");
        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        List<TarefaVO> listaDeTarefas = tarefaDAO.getAll(authUser.getUserId());
        request.setAttribute("tarefas", listaDeTarefas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mainPage.jsp");
        dispatcher.forward(request, response);
    }

    private void showConfirmDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("deleteId", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/confirmDelete.jsp");
        dispatcher.forward(request, response);
    }

    private void createTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {

        TarefaVO tarefa = new TarefaVO();
        tarefa.setTitle(request.getParameter("title"));
        tarefa.setDescription(request.getParameter("description"));

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        tarefa.setPlannedDate(formato.parse(request.getParameter("plannedDate")));

        UserVO authUser = (UserVO) request.getSession().getAttribute("authUser");
        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        tarefaDAO.insert(tarefa, authUser.getUserId());

        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    private void updateTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {

        TarefaVO tarefa = new TarefaVO();

        tarefa.setId(Integer.parseInt(request.getParameter("id")));
        tarefa.setTitle(request.getParameter("title"));
        tarefa.setDescription(request.getParameter("description"));

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        tarefa.setPlannedDate(formato.parse(request.getParameter("plannedDate")));

        tarefaDAO.update(tarefa);

        System.out.println("Passou");
        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    private void deleteTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        tarefaDAO.delete(id);

        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    private void completeTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        tarefaDAO.setStatus(id, "Concluído");

        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    private void undoneTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        tarefaDAO.setStatus(id, "Pendente");

        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    @Override
    public String getServletInfo() {
        return "Controller de tarefas";
    }
}
