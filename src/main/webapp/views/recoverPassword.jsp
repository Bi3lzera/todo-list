<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/recover.css" />
    <title>Recuperar senha - TO-DO</title>
  </head>
  <body>
    <main class="wrapper">
      <section class="brand">
        <h1 class="titlesite">TO DO</h1>
      </section>

      <section class="auth-card">
        <div class="flip-card__inner">
          <div class="flip-card__front">
            <div class="title">Recuperar Senha</div>

            <form class="flip-card__form" method="post" action="recover">
              <input
                class="flip-card__input"
                name="email"
                placeholder="Email"
                type="email"
                autocomplete="email"
                required
              />

              <div style="display:flex; gap:8px; align-items:center;">
                <input
                  class="flip-card__input"
                  name="code"
                  placeholder="Código"
                  type="text"
                  style="flex:1;"
                  autocomplete="off"
                  required
                />
                <button type="button" class="flip-card__btn" style="height:40px; padding:0 12px;">Solicitar Código</button>
              </div>

              <input
                class="flip-card__input"
                name="newpassword"
                placeholder="Nova senha"
                type="password"
                autocomplete="new-password"
                required
              />
              
              <button class="flip-card__btn" type="submit">Alterar senha</button>
            </form>

            <div class="meta">
              <a href="login.jsp" class="muted-link">Voltar ao login</a>
            </div>
          </div>
        </div>
      </section>
    </main>
  </body>
</html>
