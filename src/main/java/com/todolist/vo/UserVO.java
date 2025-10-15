package com.todolist.vo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.todolist.util.Hasher;

public class UserVO {

    int userId;
    String name;
    String email;
    String hashedPassword;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int Id) {
        userId = Id;
    }

    public void setName(String Name) {
        name = Name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String Password, boolean isHashed)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (!isHashed) {
            hashedPassword = Hasher.hashString(Password);
        } else {
            hashedPassword = Password;
        }
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setEmail(String Email) {
        email = Email;
    }

    public String getEmail() {
        return email;
    }
}
