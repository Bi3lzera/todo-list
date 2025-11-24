<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.todolist.vo.UserVO" %>
<%
  UserVO user = (UserVO) session.getAttribute("authUser");
  if (user == null) {
    response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <link href="../css/style.css" rel="stylesheet" />
    <link href="../css/account.css" rel="stylesheet" />
    <link rel="icon" href="icons/titleicon.png" type="image/png">
    <title>Minha Conta</title>
  </head>
  <body>
  <img class="userIcon" src="icons/user.png" width="10%" height="10%">
    <div class="account-container">
      <div class="account-card">
        <h2>Editar Conta</h2>
        <% if (request.getParameter("updated") != null) { %>
          <div class="msg-success">Dados atualizados com sucesso.</div>
        <% } else if (request.getParameter("error") != null) { %>
          <div class="msg-error">Ocorreu um erro ao atualizar os dados.</div>
        <% } %>

        <form method="post" action="<%= request.getContextPath() %>/account">
          <div class="form-row">
            <label>Nome</label>
            <input type="text" name="name" value="<%= user.getName() != null ? user.getName() : "" %>" required />
          </div>

          <div class="form-row">
            <label>Email</label>
            <input type="email" name="email" value="<%= user.getEmail() != null ? user.getEmail() : "" %>" required />
          </div>

          <div class="form-row">
            <label>Nova senha (deixe em branco para manter a atual)</label>
            <input type="password" name="password" />
          </div>
          
          <div class="form-actions">
            <button type="submit" class="button">Salvar</button>
            <a href="<%= request.getContextPath() %>/index.jsp" class="btn-secondary">Voltar</a>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
