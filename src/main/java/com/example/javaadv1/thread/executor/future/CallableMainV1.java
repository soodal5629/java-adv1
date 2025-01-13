package com.example.javaadv1.thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class CallableMainV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // pool 1개만
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable());
        // Callable 결과 받기
        Integer res = future.get();
        // join() 하지 않아도 결과값 잘 받아옴
        log("result value = " + res);
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
