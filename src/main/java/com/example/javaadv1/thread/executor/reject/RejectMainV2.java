package com.example.javaadv1.thread.executor.reject;

import com.example.javaadv1.thread.executor.RunnableTask;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.javaadv1.util.MyLogger.log;

public class RejectMainV2 {
    public static void main(String[] args) {
        // SynchronousQueue : 큐 사이즈가 0

        // 2. DiscardPolicy 전략 - 초과된 작업은 조용히 버림
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS
                , new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2"));
        executor.submit(new RunnableTask("task3"));

        executor.close();
    }
}
