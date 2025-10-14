package com.todolist.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public static byte[] HashString(String Text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA512");
        return algorithm.digest(Text.getBytes("UTF-8"));
    }
}
