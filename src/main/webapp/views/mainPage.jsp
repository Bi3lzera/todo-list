<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.todolist.vo.TarefaVO"%>

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
      <div class="right" style="cursor:pointer;" onclick="window.location.href='<%= request.getContextPath() %>/account'">
        <p>Conta</p>
        <img src="icons/user.png" width="3.5%" height="3.5%">
      </div>
    </div>
    <div class="mainDiv">
      <div class="todolist">
        <%
          List trfs = (List) request.getAttribute("tarefas");

          if(trfs != null){
            for (int i = 0; i < trfs.size(); i++) {
              TarefaVO t = new TarefaVO();
              t = (TarefaVO) trfs.get(i);
              out.print("<div class='todoitem'>");
                out.print("<div class='todoitem-title'>");
                  out.print("<div class='title'>");
                    out.print("<p>" + t.getTitle() + "</p>");
                  out.print("</div>");
                  out.print("<div class='data'>");
                    out.print("<p>" + t.getPlannedDate() + "</p>");
                  out.print("</div>");
                out.print("</div>");
                out.print("<div class='todoitem-desc'>");
                  out.print("<p>" + t.getDescription() + "</p>");
                out.print("</div>");
                out.print("<div class='todoitem-bottom'>");
                  out.print("<div class='todoitem-status'>");
                    out.print("<p>Status:</p>");
                    out.print("<p>" + t.getStatus() +  "</p>");
                  out.print("</div>");
                  out.print("<div class='todoitem-status'>");
                    out.print("<img src='icons/editbtn.png' ");
                    out.print("<img src='icons/editbtn.png' ");
                    out.print("onClick='abrirDetalhes(\"" 
                        + t.getId() + "\", \"" 
                        + t.getTitle() + "\", \"" 
                        + t.getDescription() + "\", \"" 
                        + t.getPlannedDate() + "\")'");
                    out.print("width='50%' height='50%'/>");
                    out.print("<img onClick='excluirTarefa(" + t.getId() + ")' src='icons/excluir.png' alt='Excluir' width='50%' height='50%'/>");
                  out.print("</div>");
                out.print("</div>");
              out.print("</div>");
            }
          }
        %>
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
          <div id="addTarefa" style="display: none">
            <%@ include file="addTarefa.jsp" %>
          </div>
          </div>
          <div id="editTarefa" style="display: none">
            <%@ include file="editTarefa.jsp" %>
          </div>
        </div>
      </div>
    </footer>
    <script>
      function abrirForm() {
        document.getElementById("formContainer").style.display = "flex";
        document.getElementById("addTarefa").style.display = "flex";
      }

      function abrirDetalhes(id, title, description, plannedDate) {
        document.getElementById("addTarefa").style.display = "none";
        // Seleciona o formulário e seus campos
        const editTarefa = document.getElementById("editTarefa");
        const form = editTarefa.querySelector("form");
        // Preenche os campos do formulário com os dados da tarefa
        form.querySelector('[name="id"]').value = id;
        form.querySelector('[name="title"]').value = title;
        form.querySelector('[name="description"]').value = description;
        form.querySelector('[name="plannedDate"]').value = plannedDate;
        document.getElementById("formContainer").style.display = "flex";
        editTarefa.style.display = "flex";
      }

      function excluirTarefa(id) {
        if (confirm("Tem certeza que deseja excluir esta tarefa?")) {
          window.location.href = "tarefa?action=delete&id=" + id;
        }
      }
    </script>
  </body>
</html>
