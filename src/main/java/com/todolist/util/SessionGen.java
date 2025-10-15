package com.todolist.util;

import java.util.Random;

public class SessionGen {

    public String genSessionHash() {
        String hash = new Random().ints(48, 122)
                .filter(i -> (i < 58 || i > 64) && (i < 91 || i > 96))
                .limit(32)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return Hasher.hashString(hash);
    }
}
