package com.todolist.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.todolist.connection.BDConnection;
import com.todolist.util.Hasher;
import com.todolist.vo.UserVO;

public class LoginDAO {

    BDConnection bdConn = new BDConnection();

    public UserVO Login(String Email, String Password) {
        UserVO tUser = new UserVO();
        try {
            tUser = GetUserData(Email);
            if (tUser != null) {
                String hashedUserPassword = tUser.getHashedPassword();
                String hashedParmPassword = Hasher.hashString(Password);

                if (hashedUserPassword.toUpperCase().equals(hashedParmPassword.toUpperCase())) {
                    return tUser;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tUser;
    }

    private UserVO GetUserData(String Email) {
        try {
            Connection conn = bdConn.connect();
            PreparedStatement ps;
            String sql = "SELECT * FROM users WHERE `email` LIKE (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Email);
            ResultSet resp = ps.executeQuery();

            if (resp.next()) {
                UserVO user = new UserVO();
                user.setUserId(resp.getInt("pkuser"));
                user.setName(resp.getString("name"));
                user.setEmail(resp.getString("email"));
                user.setPassword(resp.getString("password"), true);
                bdConn.disconnect();
                return user;
            } else {
                bdConn.disconnect();
                return null;
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | SQLException e) {
            System.out.println(e);
            bdConn.disconnect();
            return null;
        }
    }
}
