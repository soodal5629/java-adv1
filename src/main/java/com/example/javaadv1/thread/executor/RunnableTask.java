package com.example.javaadv1.thread.executor;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class RunnableTask implements Runnable {
    private final String name;
    private  int sleepMs = 1000;

    public RunnableTask(String name) {
        this.name = name;
    }

    public RunnableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public void run() {
        log(name + "시작");
        sleep(sleepMs); // 작업 시간 시뮬레이션(1초)
        log(name + "완료");
    }
}
