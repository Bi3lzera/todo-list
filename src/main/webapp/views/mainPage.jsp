<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.todolist.vo.TarefaVO"%>

<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <link href="css/style.css" rel="stylesheet" />
    <title>TO-DO</title>
    <link rel="icon" href="icons/titleicon.png" type="image/png">
  </head>
  <body>
    <%--
      Constrói a parte superior da página (cabeçalho) da página.
    --%>
    <div class="superiorDiv">
      <div class="left">
        <p>Bem Vindo ${authUser.name}</p>
      </div>
      <div class="center">
        <p>TAREFAS</p>
      </div>
      <div class="right">
        <a style="cursor:pointer;" href="/account" class="contabtn">Conta <img src="icons/user.png" width="3.5%" height="3.5%"></a>
        <a href="<%= request.getContextPath() %>/logout" class="sairlink">Sair</a>
      </div>
    </div>
    <%--
      Constrói o corpo principal da página.
    --%>
    <div class="mainDiv">
      <div class="todolist">
        <%
          //
          //Script para gerar todos os itens de tarefa da página.
          //
          List trfs = (List) request.getAttribute("tarefas");

          //Verifica se a List trfs não é nula.
          if(trfs != null && trfs.size() > 0){
            //Realiza a criação dos coponentes (itens) de tarefas.
            //Nesse 'for' sua função é inserir os itens que foram marcados como "concluído"
            for (int i = 0; i < trfs.size(); i++) {
              TarefaVO t = new TarefaVO();
              t = (TarefaVO) trfs.get(i);
              boolean isCompleted = "Concluído".equals(t.getStatus());
              if (!isCompleted){
                String classes = "todoitem" + (isCompleted ? " completed" : "");
                out.print("<div class='" + classes + "'>");
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
                      out.print("<form method='post' action='" + request.getContextPath() + "/tarefa'>");
                      out.print("<input type='hidden' name='action' value='complete' />");
                      out.print("<input type='hidden' name='id' value='" + t.getId() + "' />");
                      out.print("<button type='submit' class='statusbtn'>Concluir</button>");
                      out.print("</form>");
                      out.print("<img src='icons/editbtn.png' ");
                      out.print("onClick='abrirDetalhes(\"" 
                          + t.getId() + "\", \"" 
                          + t.getTitle() + "\", \"" 
                          + t.getDescription() + "\", \"" 
                          + t.getPlannedDate() + "\")'");
                      out.print("width='50%' height='50%'/>");
                      out.print("<img onClick='excluirTarefa(" + t.getId() + ")' src='icons/excluir.png' alt='Excluir' width='50%' height='50%'/>");                    out.print("</div>");
                  out.print("</div>");
                out.print("</div>");
              }
            }

            //Realiza a criação dos coponentes (itens) de tarefas.
            //Nesse 'for' sua função é inserir os itens que NÃO foram marcados como "concluído" ou seja, estão "pendentes";
            for (int i = 0; i < trfs.size(); i++) {
              TarefaVO t = new TarefaVO();
              t = (TarefaVO) trfs.get(i);
              boolean isCompleted = "Concluído".equals(t.getStatus());
              if (isCompleted){
                String classes = "todoitem" + (isCompleted ? " completed" : "");
                out.print("<div class='" + classes + "'>");
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
                      out.print("<form method='post' action='" + request.getContextPath() + "/tarefa' style='display:inline'>");
                      out.print("<input type='hidden' name='action' value='undone' />");
                      out.print("<input type='hidden' name='id' value='" + t.getId() + "' />");
                      out.print("<button type='submit' class='statusbtn'>Desconcluir</button>");
                      out.print("</form>");
                      out.print("<img src='icons/editbtn.png' ");
                      out.print("onClick='abrirDetalhes(\"" 
                          + t.getId() + "\", \"" 
                          + t.getTitle() + "\", \"" 
                          + t.getDescription() + "\", \"" 
                          + t.getPlannedDate() + "\")'");
                      out.print("width='50%' height='50%'/>");
                      out.print("<img onClick='excluirTarefa(" + t.getId() + ")' src='icons/excluir.png' alt='Excluir' width='50%' height='50%'/>");                    out.print("</div>");
                  out.print("</div>");
                out.print("</div>");
              }
            }
          }else{
            out.print("<div class='no-tasks'> <p>Não há tarefas adicionadas.</p> <p>Adicione clicando no botão \"Add TO-DO\".</p> </div>");
          }
        %>
      </div>
    </div>
    <%--
      Conjunto de instruções que constrói o rodapé da página.
    --%>
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
          <div id="confirmDelete" style="display: none">
            <%@ include file="confirmDelete.jsp" %>
          </div>
        </div>
      </div>
    </footer>
    <%-- 
        Algun scripts em javascript, evitei ao máximo usar, mas não achei uma forma de construir
        esses scripts usando JSP, pode existir, mas num soube fazer. E como foi algo bem simples,
        acredito que não será um problema. 
    --%> 
    <script>
      //Função para abrir o form para adicionar uma tarefa
      function abrirForm() {
        //Garante que todos os elementos de formulário estejam ocultos
        document.getElementById("editTarefa").style.display = "none";
        document.getElementById("confirmDelete").style.display = "none";

        //"Mostra" o formulário de adicionar tarefa
        document.getElementById("formContainer").style.display = "flex";
        document.getElementById("addTarefa").style.display = "flex";
      }

      //Função para abrir os detalhes
      function abrirDetalhes(id, title, description, plannedDate) {
        //Garante que todos os elementos de formulário estejam ocultos
        document.getElementById("addTarefa").style.display = "none";
        document.getElementById("confirmDelete").style.display = "none";

        // Seleciona o formulário e seus campos
        const editTarefa = document.getElementById("editTarefa");
        const form = editTarefa.querySelector("form");

        // Faz o preenchimento dos campos do formulário com os dados da tarefa
        form.querySelector('[name="id"]').value = id;
        form.querySelector('[name="title"]').value = title;
        form.querySelector('[name="description"]').value = description;
        form.querySelector('[name="plannedDate"]').value = plannedDate;
        document.getElementById("formContainer").style.display = "flex";
        editTarefa.style.display = "flex";
      }

      //Função para abrir o componente que solicita a cofirmação do usuário para exclusão
      function excluirTarefa(id) {
        //Garante que todos os elementos de formulário estejam ocultos
        document.getElementById("addTarefa").style.display = "none";
        document.getElementById("editTarefa").style.display = "none";

        const confirmDelete = document.getElementById("confirmDelete");
        const form = confirmDelete.querySelector("form");

        // Faz o preenchimento dos campos do formulário com os dados da tarefa
        form.querySelector('[name="id"]').value = id;

        //"Mostra" o componente de confirmação de exclusão
        document.getElementById("formContainer").style.display = "flex";
        document.getElementById("confirmDelete").style.display = "flex";
      }
    </script>
  </body>
</html>
