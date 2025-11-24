<div class="main">
  <h1 class="titulo">Editar Tarefa</h1>

  <div class="content">
    <form id="form" name="frm" method="post" action="tarefa">

      <div class="fContainer">
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" />

        <div class="form-group">
          <label for="title">Título</label>
          <input type="text" id="title" name="title" required />
        </div>

        <div class="form-group">
          <label for="plannedDate">Data Planejada</label>
          <input type="date" id="plannedDate" name="plannedDate" required />
        </div>

        <div class="form-group">
          <label for="description">Descrição</label>
          <textarea id="description" name="description" rows="4"></textarea>
        </div>
      </div>

      <div class="btns">
        <button class="form-button save" type="submit">Gravar</button>
        <%--
          Botão personalizado de terceiros, site: https://uiverse.io/
        --%>
        <button class="noselect" type="button" onclick="fecharForm()">
          <span class="text">Fechar</span>
          <span class="icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" 
                 viewBox="0 0 24 24">
              <path
                d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"
              ></path>
            </svg>
          </span>
        </button>
      </div>

    </form>
  </div>
</div>

<script>
  function fecharForm() {
    document.getElementById("form").reset();
    document.getElementById("editTarefa").style.display = "none";
    document.getElementById("formContainer").style.display = "none";
  }
</script>
