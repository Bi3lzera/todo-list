<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <title>Login - TO-DO</title>
  </head>
  <body>
    <main class="wrapper">
      <section class="brand">
        <h1 class="titlesite">TO DO</h1>
      </section>

      <section class="auth-card">
        <%-- Mostra erro ao digitar e-mail ou senha de forma incorreta. --%>
        <div class="flip-card__inner">
          <div class="flip-card__front">
            <div class="title">Acessar conta</div>
            <form class="flip-card__form" method="post" action="login">
              <input
                class="flip-card__input"
                name="email"
                placeholder="Email"
                type="email"
                autocomplete="email"
                required
              />

              <input
                class="flip-card__input"
                name="password"
                placeholder="Senha"
                type="password"
                autocomplete="current-password"
                required
              />

              <%-- Sessão dedicada para mostrar as mensagems de sucessos e erros do login, registro e recuperação de senha e qualquer outro. --%>
              <% if (request.getParameter("error") != null &&
              "1".equals(request.getParameter("error"))) { %>
              <div class="error-message">Email ou senha incorreto(s).</div>
              <% } %> <% if (request.getParameter("error") != null &&
              "2".equals(request.getParameter("error"))) { %>
              <div class="error-message">
                Erro ao criar conta, tente novamente.
              </div>
              <% } %> <% if (request.getParameter("signup") != null &&
              "failed".equals(request.getParameter("signup"))) { %>
              <div class="error-message">Erro ao criar conta.</div>
              <% } %> <% if (request.getParameter("signup") != null &&
              "success".equals(request.getParameter("signup"))) { %>
              <div class="success-message">
                Conta registrada com sucesso! Realize o login.
              </div>
              <% } %> <% if (request.getParameter("recover") != null &&
              "1".equals(request.getParameter("recover"))) { %>
              <div class="success-message">
                Senha recuperada com sucesso! Realize o login.
              </div>
              <% } %>

              <button class="flip-card__btn" type="submit">Entrar</button>
            </form>

            <div class="meta">
              <a href="<%=request.getContextPath()%>/recover" class="muted-link"
                >Esqueceu a senha?</a
              >
              <span class="muted-link">•</span>
              <a href="#" class="muted-link" id="to-register">Criar conta</a>
            </div>
          </div>

          <div class="flip-card__back">
            <div class="title">Criar conta</div>
            <form class="flip-card__form" method="post" action="signup">
              <input
                class="flip-card__input"
                name="name"
                placeholder="Nome"
                type="text"
                required
              />
              <input
                class="flip-card__input"
                name="email"
                placeholder="Email"
                type="email"
                autocomplete="email"
                required
              />
              <input
                class="flip-card__input"
                name="password"
                placeholder="Senha"
                type="password"
                autocomplete="new-password"
                required
              />
              <button class="flip-card__btn" type="submit">Criar conta</button>
            </form>
            <div class="meta">
              <a href="#" class="muted-link" id="to-login"
                >Já tem conta? Entre</a
              >
            </div>
          </div>
        </div>
      </section>
    </main>
    <%-- Script que faz o efeitinho "bonito" de "girar" a div de login para a div de registro. --%>
    <script>
      const authCard = document.querySelector(".auth-card");
      function showRegister() {
        authCard.classList.add("show-register");
      }
      function showLogin() {
        authCard.classList.remove("show-register");
      }
      document
        .getElementById("to-register")
        .addEventListener("click", function (e) {
          e.preventDefault();
          showRegister();
        });
      document
        .getElementById("to-login")
        .addEventListener("click", function (e) {
          e.preventDefault();
          showLogin();
        });

      document.addEventListener("keydown", function (e) {
        if (e.key === "Escape") showLogin();
      });
    </script>
  </body>
</html>
