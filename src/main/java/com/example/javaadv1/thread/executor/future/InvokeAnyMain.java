package com.example.javaadv1.thread.executor.future;

import com.example.javaadv1.thread.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.javaadv1.util.MyLogger.log;

public class InvokeAnyMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        // 여러 작업을 한번에 처리(작업 하나라도 끝나면 해당 결과값만 리턴하고 나머지는 다 취소됨)
        Integer value = es.invokeAny(List.of(task1, task2, task3));
        log("value = " + value);

        es.close();
    }
}
