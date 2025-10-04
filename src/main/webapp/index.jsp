<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="css/syle.css"
  </head>
  <body>
    <div class="superiorDiv">
      <div class="left">
        <p>Bem Vindo Gabriel Lima</p>
      </div>
      <div class="center">
        <p>04/10/2025</p>
      </div>
      <div class="right">
        <p>Account</p>
      </div>
    </div>

    <div class="mainDiv">
      <div class="todolist">
        <h1>To-Do</h1>

        <% for (int i = 0; i < 150; i++){ %>
        <div class="todoitem">
          <%-- Div main do todo --%>
          <div class="todoitem-title">
            <%-- Div que irá compor outras duas divs, responsáveis pelo titulo e data e hora do evento --%>
            <div class="title"><p>Organizar a casa</p></div>
            <%-- Titulo do todo --%>
            <div class="data">
              <%-- Data do todo --%>
              <p>21/05/2026 14:56</p>
              <%-- DADOS --%>
            </div>
          </div>
          <div class="todoitem-desc">
            <%-- Div responsável pela descrição detalhada do todo --%>
            <p>Organizar toda casa, do começo ao começo até chegar ao fim.</p>
            <%-- DADOS --%>
          </div>
          <div class="todoitem-status">
            <%-- Div resposnável pelos status do todo --%>
            <p>Status:</p>
            <%-- DADOS --%>
            <p>Pendente</p>
            <%-- DADOS --%>
          </div>
        </div>
        <% } %>

        <div class="todoitem">
          <div class="todoitem-title">
            <div class="title"><p>Viajar até mato grosso</p></div>
            <div class="data">
              <p>21/05/2026 14:56</p>
            </div>
          </div>
          <div class="todoitem-desc">
            <p>Organizar toda casa, do começo ao começo até chegar ao fim.</p>
          </div>
          <div class="todoitem-status">
            <p>Status:</p>
            <p>Pendente</p>
          </div>
        </div>
      </div>
    </div>
    <footer class="footerDiv">
      <p>Footer Div</p>
    </footer>
  </body>
</html>
