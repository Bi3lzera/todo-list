<%@ page contentType="text/html; charset=UTF-8" %>
<%
	// Redireciona o usuário para a página principal (tarefas).
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("/tarefa");
	rd.forward(request, response);
	return;
%>