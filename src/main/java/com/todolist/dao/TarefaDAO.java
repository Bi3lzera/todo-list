package com.todolist.dao;

import java.sql.SQLException;

import com.todolist.TarefaModel;
import com.todolist.connection.BDConnection;

public class TarefaDAO {
    public boolean insert(TarefaModel Tarefa) {
        try {
            BDConnection conn = new BDConnection();
            conn.connect();
            if (conn != null) {
                System.out.println("DEU BÃO");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
