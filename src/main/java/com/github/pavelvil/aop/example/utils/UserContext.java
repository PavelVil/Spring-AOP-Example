package com.github.pavelvil.aop.example.utils;

public final class UserContext {

    private static final ThreadLocal<String> USERNAME = new ThreadLocal<>();

    private UserContext() {

    }

    public static String getUsername() {
        return USERNAME.get();
    }

    public static void setUsername(String username) {
        USERNAME.set(username);
    }
}
