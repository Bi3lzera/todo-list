<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
  </head>
  <body>
    <div>
      <h1 class="title">TO-DO</h1>
      <% if (request.getParameter("error") != null && request.getParameter("error") == "1") { 
        out.print("<p>Email ou Senha incorreto</p>"); 
      } %>
    </div>
    
    <div class="wrapper">
      <div class="card-switch">
        <label class="switch">
          <input type="checkbox" class="toggle" />
          <span class="slider"></span>
          <span class="card-side"></span>
          <div class="flip-card__inner">
            <div class="flip-card__front">
              <div class="title">Log in</div>
              <form class="flip-card__form" method="post" action="login">
                <input
                  class="flip-card__input"
                  name="email"
                  placeholder="Email"
                  type="email"
                />
                <input
                  class="flip-card__input"
                  name="password"
                  placeholder="Password"
                  type="password"
                />
                <button class="flip-card__btn">Let`s go!</button>
              </form>
            </div>
            <div class="flip-card__back">
              <div class="title">Sign up</div>
              <form class="flip-card__form" action="">
                <input
                  class="flip-card__input"
                  placeholder="Name"
                  type="name"
                />
                <input
                  class="flip-card__input"
                  name="email"
                  placeholder="Email"
                  type="email"
                />
                <input
                  class="flip-card__input"
                  name="password"
                  placeholder="Password"
                  type="password"
                />
                <button class="flip-card__btn">Confirm!</button>
              </form>
            </div>
          </div>
        </label>
      </div>
    </div>
  </body>
</html>
