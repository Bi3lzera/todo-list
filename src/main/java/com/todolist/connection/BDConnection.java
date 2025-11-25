package com.todolist.connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class BDConnection {

    private final String bd;
    private final String usuario;
    private final String senha;
    private Connection con;

    // Construtor com os dados da conexão.
    public BDConnection() {
        bd = "progweb";
        usuario = "root";
        senha = "biel123";
        con = null;
    }

    // Realiza a conexão do BD para realização das transações com o BD.
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

    // Realiza a pre conexão, função exclusiva para a função que tenta criar a
    // database.
    private Connection preConnect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/",
                    usuario, senha);
            return con;
        } catch (ClassNotFoundException erro) {
            System.out.println("Erro na conexão: " + erro);
            return null;
        }
    }

    // Fecha a conexão com o BD.
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao encerrar a conexão: " + erro);
        }
    }

    // Criei essas funções pois estive desenvolvendo isso em diversos computadores,
    // e estava chato ter que ficar importando backup.

    // Cria a database, caso ela não exista.
    public void createDatabase() {
        System.out.println("Tentando criar database...");

        String sql = null;
        try {
            Connection conn = preConnect();
            if (conn != null) {
                sql = "CREATE DATABASE IF NOT EXISTS `" + bd
                        + "` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();

                System.out.println("Tentativa de criação da database " + bd + " realizada.");
            }
            disconnect();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(sql);
        }
    }

    // Cria as tabelas no BD, caso elas não existam.
    public void createTables() {
        System.out.println("Tentando criar as tabelas...");
        String sql = null;

        try {
            Connection conn = preConnect();
            if (conn != null) {
                // Cria a tabela users.
                sql = "CREATE TABLE IF NOT EXISTS `" + bd + "`.`users` (\r" + //
                        "  `pkuser` int NOT NULL AUTO_INCREMENT,\r" + //
                        "  `name` varchar(16) NOT NULL,\r" + //
                        "  `email` varchar(100) NOT NULL,\r" + //
                        "  `password` varchar(255) NOT NULL,\r" + //
                        "  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,\r" + //
                        "  PRIMARY KEY (`pkuser`))\r" + //
                        "  ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r" + //
                        "";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();

                System.out.println("Tentativa de criação da tabela USERS realizada.");
            }
            disconnect();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(sql);
        }

        try {
            Connection conn = connect();
            if (conn != null) {
                // Cria a tabela tarefas.
                sql = "CREATE TABLE IF NOT EXISTS `" + bd + "`.`tarefas` (\r" + //
                        "  `pktarefa` int NOT NULL AUTO_INCREMENT,\r" + //
                        "  `title` varchar(50) DEFAULT NULL,\r" + //
                        "  `description` varchar(250) DEFAULT NULL,\r" + //
                        "  `planneddate` datetime DEFAULT NULL,\r" + //
                        "  `status` varchar(45) NOT NULL DEFAULT 'Pendente',\r" + //
                        "  `creationdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\r" + //
                        "  `fkuser` int DEFAULT NULL,\r" + //
                        "  PRIMARY KEY (`pktarefa`),\r" + //
                        "  KEY `fkuser_idx` (`fkuser`),\r" + //
                        "  CONSTRAINT `fkuser` FOREIGN KEY (`fkuser`) REFERENCES `users` (`pkuser`) \r" + //
                        "  ON DELETE CASCADE ON UPDATE CASCADE\r" + //
                        ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r" + //
                        "";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();

                System.out.println("Tentativa de criação da tabela TAREFAS realizada.");
            }
            disconnect();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(sql);
        }
    }
}
