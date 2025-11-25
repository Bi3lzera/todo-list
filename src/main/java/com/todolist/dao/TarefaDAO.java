package com.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todolist.connection.BDConnection;
import com.todolist.vo.TarefaVO;

//
// Algoritmo para gerenciar a tarefa no BD.
//

public class TarefaDAO {

    //
    // TO DO: Seria interessante que houvesse a validação da id do usuário para
    // executar qualquer uma dessas funções, já que facilmente é possivel excluir,
    // alterar, inserir ou listar a tarefa dos amiguinhos, a partir do momento em
    // que se tem uma sessão criada. Porém, no momento, dispensável lenvado em
    // consideração a intenção deste projeto.
    //

    private final BDConnection bdConn;

    // Construtor para iniciar a conexão com o banco de dados assim que instanciar
    // esta classe.
    public TarefaDAO() {
        this.bdConn = new BDConnection();
    }

    // Função para inserir uma tarefa, usando um objeto TarefaVO e um ID do usuário.
    public boolean insert(TarefaVO Tarefa, int UserId) {
        try {
            Connection conn = bdConn.connect();
            if (conn != null) {
                PreparedStatement ps;
                String sql = "insert into tarefas (title, description, planneddate, fkuser) values (?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, Tarefa.getTitle());
                ps.setString(2, Tarefa.getDescription());
                ps.setDate(3, new java.sql.Date(Tarefa.getPlannedDate().getTime()));
                ps.setInt(4, UserId);
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

    // Função para atualizar uma tarefa, usando um objeto TarefaVO.
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

    // Função para deletar uma tarefa, usando uma ID.
    public boolean delete(int Id) {
        System.out.println("Excluindo id: " + Id);

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

    public List<TarefaVO> getAll(int Id) throws SQLException {
        List<TarefaVO> tarefas = new ArrayList<>();
        String sql = "SELECT pktarefa, title, description, creationdate, planneddate, status FROM tarefas WHERE fkuser LIKE '"
                + Id + "' ORDER BY planneddate ASC ";

        // Usei um método diferente que encontrei. Aqui o try já inicia com a
        // connection e o prepareStatement, vantagem que não precisar usar o
        // bdConn.disconnect e talvez alguma outra que eu desconheça,
        // já que isso é realizado de forma automática. Talvez um TODO seria alterar as
        // outras para essa forma, sendo ela bem mais clean.
        // Também utilizei o ResultSet para executar um loop e preencher todos os
        // objetos de Tarefas.
        try (Connection conn = bdConn.connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

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

        System.out.println("Tarefas encontradas:" + tarefas.size());

        return tarefas;
    }

    // Função para alterar o status de uma tarefa.
    public boolean setStatus(int Id, String status) {
        try {
            Connection conn = bdConn.connect();
            if (conn != null) {
                PreparedStatement ps;
                String sql = "update tarefas set status = ? where pktarefa = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, status);
                ps.setInt(2, Id);
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
}
