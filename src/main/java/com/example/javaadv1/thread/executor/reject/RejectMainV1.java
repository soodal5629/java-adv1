package com.example.javaadv1.thread.executor.reject;

import com.example.javaadv1.thread.executor.RunnableTask;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.javaadv1.util.MyLogger.log;

public class RejectMainV1 {
    public static void main(String[] args) {
        // SynchronousQueue : 큐 사이즈가 0

        // 1. 기본 전략
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS
                , new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        executor.submit(new RunnableTask("task1"));
        try{
            executor.submit(new RunnableTask("task2"));
        } catch (RejectedExecutionException e) {
            log("요청 초과" + e);
            // 포기, 다시 시도 등 다양한 고민을 하면 됨
        }
        executor.close();
    }
}
