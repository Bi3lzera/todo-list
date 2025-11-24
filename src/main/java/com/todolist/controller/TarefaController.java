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

    //Handle das requisições com method GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        System.out.println("Recebido solicitação GET no TarefaController, action: " + action);

        processRequest(request, response);
    }

    //Handle das requisições com method POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        System.out.println("Recebido solicitação POST no TarefaController, action: " + action);

        processRequest(request, response);
    }

    //Faz o processamento de ambas requests (GET e POST)
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        //Faz com que por padrão a action seja list, caso nula.
        //Tá funcionando assim, tentei mexer e quebrou, então vou deixar assim :).
        if (action == null) {
            action = "list";
        }

        //Realiza o try catch para executar o que foi requisitado.
        try {
            switch (action) {

                //Cria tarefa
                case "create":
                    createTarefa(request, response);
                    break;

                //Altera uma tarefa por determinado ID fornecido.
                case "update":
                    updateTarefa(request, response);
                    break;

                //"Marca" uma tarefa com Concluída, usando determinado ID fornecido.
                case "complete":
                    completeTarefa(request, response);
                    break;

                //"Desmarca" uma tarefa Concluída, tornando-a pendente novamente.
                //Usando determinado ID fornecido.
                case "undone":
                    undoneTarefa(request, response);
                    break;

                //Deleta uma tarefa, usando determinado ID.
                case "delete":
                    deleteTarefa(request, response);
                    break;

                //Not Using: Função que encaminha para a página de confirmação de exclusão.
                //Desativei pois não consegui fazer funcionar legal e eu queria fazer de uma forma
                //em que usuário não fosse encaminhado para uma outra tela só pra confirmar uma exclusão. Tive que usar javascript :|
                case "confirmDelete":
                    showConfirmDelete(request, response);
                    break;

                //Sim, por default ele lista as tarefas, tá funcionando assim, não irei mexer no momento.
                default:
                    listTarefas(request, response);
                    break;
            }
        } catch (IOException | SQLException | ParseException | ServletException e) {
            throw new ServletException("Erro ao processar requisição POST", e);
        }
    }

    //
    //Função para listar as tarefas, devolve uma list de objetos TarefaVO.
    //
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

    //
    //Not Using: Função não estão sendo usada, tinha a função de encaminhar o usuário para a tela de confirmação de exclusão.
    //Mantive somente se caso no futuro eu resolva tentar utiliza-la novamente.
    //
    private void showConfirmDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("deleteId", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/confirmDelete.jsp");
        dispatcher.forward(request, response);
    }

    //
    //Função para criar a tarefa.
    //
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

    //
    //Função para atualizar uma determinada tarefa.
    //
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

    //
    //Função para deletar uma determinada tarefa.
    //
    private void deleteTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        tarefaDAO.delete(id);

        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    //
    //Função para marcar uma tarefa como Concluída.
    //
    private void completeTarefa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        tarefaDAO.setStatus(id, "Concluído");

        response.sendRedirect(request.getContextPath() + "/tarefa");
    }

    //
    //Função para marcar uma tarefa como pendente novamente.
    //
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
