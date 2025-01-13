package com.example.javaadv1.thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class CallableMainV2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // pool 1개만
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("submit() 호출");
        Future<Integer> future = es.submit(new MyCallable());
        log("future 즉시 반환, future = " + future);
        // Callable 결과 받기
        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING");
        Integer res = future.get(); // main 스레드가 작업 결과 받을때까지 기다림
        log("future.get() [블로킹] 메서드 호출 완료 -> main 스레드 RUNNABLE");
        // join() 하지 않아도 결과값 잘 받아옴
        log("result value = " + res);
        log("future 완료, future = " + future);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value = "+ value);
            log("Callable 완료");
            return value;
        }
    }
}