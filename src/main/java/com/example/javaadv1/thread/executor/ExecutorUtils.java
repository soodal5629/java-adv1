package com.example.javaadv1.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static com.example.javaadv1.util.MyLogger.log;

public abstract class ExecutorUtils {
    public static void printState(ExecutorService executorService) {
        if(executorService instanceof ThreadPoolExecutor poolExecutor) {
            int poolSize = poolExecutor.getPoolSize(); // pool 내 스레드 개수
            int activeSize = poolExecutor.getActiveCount(); // 작업 수행중인 스레드 개수

            // 생산자/소비자 구조의 큐가 존재 - 큐에 대기하는 작업의 개수
            int queuedTasks = poolExecutor.getQueue().size();
            // 완료된 작업의 개수
            long completedTaskCount = poolExecutor.getCompletedTaskCount();
            log("[pool= " + poolSize + ", activeSize = " + activeSize + ", queuedTasks = " + queuedTasks + ", completedTaskCount = " + completedTaskCount + "]");
        } else {
            log(executorService);
        }
    }
}
