package com.example.javaadv1.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.javaadv1.thread.executor.ExecutorUtils.printState;
import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        log("== 초기 상태 ==");
        printState(es);
        // 작업이 들어와야 스레드를 만듦
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");
        printState(es);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
