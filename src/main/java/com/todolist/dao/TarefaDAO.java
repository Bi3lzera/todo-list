package com.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todolist.connection.BDConnection;
import com.todolist.vo.TarefaVO;

public class TarefaDAO {

    private final BDConnection bdConn;

    public TarefaDAO() {
        this.bdConn = new BDConnection();
    }

    public boolean insert(TarefaVO Tarefa) {
        try {
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

    public boolean update(TarefaVO Tarefa) {
        try {
            Connection conn = bdConn.connect();
            if (conn != null) {
                PreparedStatement ps;
                String sql = "update tarefas set title = ?, description = ?, planneddate = ? where pktarefa = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, Tarefa.getTitle());
                ps.setString(2, Tarefa.getDescription());
                ps.setDate(3, new java.sql.Date(Tarefa.getPlannedDate().getTime()));
                ps.setInt(4, Tarefa.getId());
                ps.executeUpdate();
                bdConn.disconnect();
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    public boolean delete(int Id) {
        try {
            Connection conn = bdConn.connect();
            if (conn != null) {
                PreparedStatement ps;
                String sql = "delete from tarefas where pktarefa = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Id);
                ps.executeUpdate();
                bdConn.disconnect();
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    public List<TarefaVO> getAll() throws SQLException {
        List<TarefaVO> tarefas = new ArrayList<>();
        String sql = "SELECT pktarefa, title, description, creationdate, planneddate, status FROM tarefas ORDER BY planneddate ASC";

        try (Connection conn = bdConn.connect(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TarefaVO tarefa = new TarefaVO();
                tarefa.setId(rs.getInt("pktarefa"));
                tarefa.setTitle(rs.getString("title"));
                tarefa.setDescription(rs.getString("description"));
                tarefa.setPlannedDate(rs.getDate("planneddate"));
                tarefa.setStatus(rs.getString("status"));

                tarefas.add(tarefa);
            }

        }
        // A conexão é fechada automaticamente pelo try-with-resources
        System.out.println("Tarefas encontradas:" + tarefas.size());
        for (TarefaVO tarefa : tarefas) {
            System.out.println("ID: " + tarefa.getId() + ", Título: " + tarefa.getTitle() + ", Descrição: " + tarefa.getDescription() + ", Data Planejada: " + tarefa.getPlannedDate() + ", Status: " + tarefa.getStatus());
        }

        return tarefas;
    }
}
