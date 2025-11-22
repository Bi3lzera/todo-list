<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <% if
(request.getAttribute("tarefas") == null) { String redirectTo =
request.getContextPath() + "/tarefa"; String qs = request.getQueryString(); if
(qs != null && !qs.isEmpty()) { redirectTo += "?" + qs; }
response.sendRedirect(redirectTo); return; } %>
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <link href="css/syle.css" rel="stylesheet" />
  </head>
  <body>
    <div class="superiorDiv">
      <div class="left">
        <p>Bem Vindo ${authUser.name}</p>
      </div>
      <div class="center">
        <p>TAREFAS</p>
      </div>
      <div class="right">
        <p>Account</p>
      </div>
    </div>
    <div class="mainDiv">
      <div class="todolist">
        <c:forEach items="${tarefas}" var="tarefa">
          <%-- Div main do todo --%>
          <div class="todoitem">
            <div class="todoitem-title">
              <div class="title">
                <p><c:out value="${tarefa.title}" /></p>
              </div>
              <div class="data">
                <p>
                  <fmt:formatDate
                    pattern="dd/MM/yyyy"
                    value="${tarefa.plannedDate}"
                  />
                </p>
              </div>
            </div>
            <div class="todoitem-desc">
              <p><c:out value="${tarefa.description}" /></p>
            </div>
            <div class="todoitem-bottom">
              <div class="todoitem-status">
                <p>Status:</p>
                <p><c:out value="${tarefa.status}" /></p>
              </div>
              <div class="todoitem-status">
                <img onClick="abrirDetalhes(
                  '<c:out value="${tarefa.title}" />',
                  '<c:out value="${tarefa.description}" />',
                  '<fmt:formatDate pattern="yyyy-MM-dd" value="${tarefa.plannedDate}" />')"
                  img src="icons/editbtn.png" alt="Editar" width="50%" height="50%"/>
                <img src="icons/excluir.png" alt="Editar" width="50%" height="50%"/>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <footer class="footerDiv">
      <div class="buttons">
        <button class="button" onClick="abrirForm()" type="button">
          <span class="button__text">Add TO-DO</span>
          <span class="button__icon">
            <svg
              class="svg"
              fill="none"
              height="24"
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              viewBox="0 0 24 24"
              width="24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <line x1="12" x2="12" y1="5" y2="19"></line>
              <line x1="5" x2="19" y1="12" y2="12"></line>
            </svg>
          </span>
        </button>
        <div id="formContainer" style="display: none">
          <%@ include file="views/addToDoForm.jsp" %>
        </div>
      </div>
    </footer>
    <script>
      function abrirForm() {
        document.getElementById("formContainer").style.display = "flex";
      }
      function abrirDetalhes(title, description, plannedDate) {
        // Seleciona o formulário e seus campos
        const formContainer = document.getElementById("formContainer");
        const form = formContainer.querySelector("form");
        // Preenche os campos do formulário com os dados da tarefa
        form.querySelector('[name="title"]').value = title;
        form.querySelector('[name="description"]').value = description;
        form.querySelector('[name="plannedDate"]').value = plannedDate;
        formContainer.style.display = "flex";
      }
    </script>
  </body>
</html>
