package com.todolist.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

import com.todolist.VO.TarefaVO;
import com.todolist.connection.BDConnection;

public class TarefaDAO {
    public boolean insert(TarefaVO Tarefa) {
        try {
            BDConnection bdConn = new BDConnection();
            Connection conn = bdConn.connect();
            if (conn != null) {
                PreparedStatement ps;
                String sql = "insert into tarefas (title, description, planneddate) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, Tarefa.getTitle());
                ps.setString(2, Tarefa.getDescription());
                ps.setDate(3, new java.sql.Date(Tarefa.getPlannedDate().getTime()));
                int resp = ps.executeUpdate();
                bdConn.disconnect();
                if (resp != 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
