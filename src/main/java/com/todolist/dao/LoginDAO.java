package com.todolist.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public boolean updateUser(UserVO user) {
        try {
            Connection conn = bdConn.connect();
            if (conn != null) {
                PreparedStatement ps;
                String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE pkuser = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getHashedPassword());
                ps.setInt(4, user.getUserId());
                int updated = ps.executeUpdate();
                bdConn.disconnect();
                return updated > 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
            bdConn.disconnect();
            return false;
        }
        return false;
    }

    public boolean create(UserVO user) {
        try {
            // não permite duplicar email
            if (GetUserData(user.getEmail()) != null) {
                return false;
            }

            Connection conn = bdConn.connect();
            if (conn != null) {
                PreparedStatement ps;
                String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getHashedPassword());
                int inserted = ps.executeUpdate();
                if (inserted > 0) {
                    ResultSet keys = ps.getGeneratedKeys();
                    if (keys.next()) {
                        user.setUserId(keys.getInt(1));
                    }
                }
                bdConn.disconnect();
                return inserted > 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
            bdConn.disconnect();
            return false;
        }
        return false;
    }

    public UserVO getUserByEmail(String Email) {
        return GetUserData(Email);
    }
}
