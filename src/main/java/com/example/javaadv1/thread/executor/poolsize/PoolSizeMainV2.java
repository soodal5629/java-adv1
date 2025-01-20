package com.example.javaadv1.thread.executor.poolsize;

import com.example.javaadv1.thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.javaadv1.thread.executor.ExecutorUtils.printState;
import static com.example.javaadv1.util.MyLogger.log;

/**
 * 고정 스레드 풀 전략
 */
public class PoolSizeMainV2 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("== shutdown 완료 ==");
    }
}
