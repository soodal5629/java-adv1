package com.example.javaadv1.thread.executor.poolsize;

import com.example.javaadv1.thread.executor.RunnableTask;

import java.util.concurrent.*;

import static com.example.javaadv1.thread.executor.ExecutorUtils.printState;
import static com.example.javaadv1.util.MyLogger.log;

/**
 * 사용자 정의 풀 전략(세분화된 전략)
 */
public class PoolSizeMainV4 {
    // 1. 일반 상황
    //static final int TASK_SIZE = 1100;
    // 2. 긴급 상황
    //static final int TASK_SIZE = 1200;
    // 3. 거절 상황
    static final int TASK_SIZE = 1201;

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
        printState(es);

        long startMs = System.currentTimeMillis();
        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch(RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }
        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));
    }
}
