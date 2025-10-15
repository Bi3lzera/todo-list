package com.todolist.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class BDConnection {

    private final String bd;
    private final String usuario;
    private final String senha;
    private Connection con;

    public BDConnection() {
        bd = "progweb";
        usuario = "root";
        senha = "biel123";
        con = null;
    }

    public Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/" + bd,
                    usuario, senha);
            return con;
        } catch (ClassNotFoundException erro) {
            System.out.println("Erro na conexão: " + erro);
            return null;
        }
    }

    public void disconnect() {
        try {
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao encerrar a conexão: " + erro);
        }
    }

}
