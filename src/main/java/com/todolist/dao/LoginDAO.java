package com.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.cj.protocol.Resultset;
import com.todolist.connection.BDConnection;

public class LoginDAO {
    BDConnection bdConn = new BDConnection();

    public boolean Login(String Email, String Password) {

        try {
            return VerifyUserExistence(Email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private boolean VerifyUserExistence(String Email) {
        try {
            Connection conn = bdConn.connect();
            PreparedStatement ps;
            String sql = "SELECT * FROM users WHERE `email` LIKE (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Email);
            java.sql.ResultSet resp = ps.executeQuery();
            System.out.println();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
