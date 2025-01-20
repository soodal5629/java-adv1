package com.example.javaadv1.thread.executor.reject;

import com.example.javaadv1.thread.executor.RunnableTask;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {
    public static void main(String[] args) {
        // SynchronousQueue : 큐 사이즈가 0

        // 3. CallerRunsPolicy 전략 - 초과된 작업을 작업을 요청한 스레드에게 시켜버림
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS
                , new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        executor.submit(new RunnableTask("task1"));
        // 얘네부터 초과된 작업을 요청한 main 스레드가 작업을 수행함
        executor.submit(new RunnableTask("task2"));
        executor.submit(new RunnableTask("task3"));
        executor.submit(new RunnableTask("task4"));

        executor.close();
    }
}
