package com.github.pavelvil.aop.example.utils;

public final class ThreadUtils {
    private ThreadUtils() {

    }

    public static void waitTime(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
