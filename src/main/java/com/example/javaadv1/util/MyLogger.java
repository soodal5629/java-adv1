package com.example.javaadv1.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class MyLogger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object obj) {
        String time = LocalDateTime.now().format(formatter);
        // printf가 Object를 toString 알아서 호출해서 문자열로 출력해줌 - 따라서 Object가 훨씬 유연함
        System.out.printf("%s [%9s] %s\n", time, Thread.currentThread().getName(), obj);
    }
}
