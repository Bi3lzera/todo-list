<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="css/form.css" />
  </head>
  <body>
    <div class="main">
    <div>
    <div class="content">
    <form name="frm" method="post" action="tarefa">
            Título <input type="text" name="title">
            <br>
            Data <input type="date" name="plannedDate">
            <br>
            Descrição <input type="text" name="description">
            <br>
            <input type="submit" value="Gravar">
        </form>
    </div>
      <div class="buttons">
        <button class="noselect" onClick="fecharForm()">
          <span class="text">Fechar</span
          ><span class="icon"
            ><svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
            >
              <path
                d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"
              ></path></svg
          ></span>
        </button>
      </div>
    </div>
    <script>
      function fecharForm() {
        document.getElementById("formContainer").style.display = "none";
      }
    </script>
  </body>
</html>
