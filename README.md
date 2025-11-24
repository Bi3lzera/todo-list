# To-Do Application — Java + JSP/Servlets

Aplicação de lista de tarefas desenvolvida em Java, utilizando JSP/Servlets e JDBC, construída como trabalho acadêmico da disciplina de Programação Web em Java/JSP do curso de Análise e Desenvolvimento de Sistemas — Fatec Ourinhos.

A aplicação oferece um CRUD completo de tarefas com autenticação de usuário e interface simples/responsiva baseada em JSP.

## Objetivo

Implementar um sistema web funcional que permita:

- Controle de usuários autenticados.
- Criação e gerenciamento de tarefas pessoais.
- Estrutura MVC utilizando Servlets, JSP e DAO.
- Persistência via JDBC com conexão configurável.

## Tecnologias Utilizadas

- Java (JDK 8+)
- Maven
- JSP e Servlets
- JDBC
- SHA-512 para hashing de senhas
- CSS para layout e responsividade

## Funcionalidades

### Autenticação
- Cadastro de usuários
- Login
- Senhas armazenadas com SHA-512
- Recuperação de senha (sem envio de e-mail)

### Tarefas
- Criar tarefa
- Listar tarefas
- Atualizar dados
- Marcar como concluída
- Excluir tarefa

### Front-end
- Layout simples utilizando CSS
- Páginas JSP com estrutura consistente

## Estrutura do Projeto

```
/src
 ├── controller/ → Servlets principais (rotas /login, /tarefa, /recover…)
 ├── auth/ → Servlets de autenticação
 ├── dao/ → DAO (acesso a dados)
 ├── vo/ → Value Objects
 ├── utils/
 │     ├── BDConnection.java → Configuração do banco
 │     └── Hasher.java → SHA-512
 └── views/ → JSP (login.jsp, mainPage.jsp, recoverPassword.jsp…)
```

## Configuração e Execução

### 1. Configurar Banco de Dados
Editar:
```
src/utils/BDConnection.java
```

### 2. Criar Banco e Tabelas
Criar tabelas conforme scripts SQL.

### 3. Build com Maven
```
mvn clean package
```

### 4. Deploy
Executar o arquivo `.war` em Tomcat 8+, Jetty ou similar.

## Observações
- Recuperação de senha é simplificada.
- Senhas hashadas com SHA-512 (Hasher.java).
