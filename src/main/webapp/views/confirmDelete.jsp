<%@ page contentType="text/html; charset=UTF-8" %>
  <!DOCTYPE html>
  <html lang="pt-BR">

  <head>
    <meta charset="UTF-8" />
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/confirmDelete.css" rel="stylesheet" />
    <title>Confirmar Exclusão</title>
  </head>

  <body>
    <div id="confirmDeleteContainer">
      <div class="confirm-box">
        <h2>Confirmar exclusão</h2>
        <p>Tem certeza que deseja excluir esta tarefa? Esta ação não poderá ser desfeita.</p>

        <div class="confirm-actions">
          <form method="get" action="<%= request.getContextPath() %>/tarefa">
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="id" value="" />
            <button class="confirm-button primary" type="submit">Sim, excluir</button>
          </form>

          <a class="confirm-link" href="<%= request.getContextPath() %>/tarefa">Cancelar</a>
        </div>
      </div>
    </div>
  </body>

  </html>