package com.todolist.VO;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.todolist.util.Hasher;

public class UserVO {
    int userId;
    String name;
    String email;
    byte hashedPassword[];

    public int getUserId() {
        return userId;
    }

    public void setName(String Name) {
        name = Name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String Password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException  {
        hashedPassword = Hasher.HashString(Password);
    }

    public void setEmail(String Email) {
        email = Email;
    }

    public String getEmail() {
        return email;
    }
}