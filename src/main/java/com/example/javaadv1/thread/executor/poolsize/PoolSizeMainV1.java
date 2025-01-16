package com.example.javaadv1.thread.executor.poolsize;

import com.example.javaadv1.thread.executor.RunnableTask;

import java.util.concurrent.*;

import static com.example.javaadv1.thread.executor.ExecutorUtils.printState;
import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService es = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
        printState(es);

        es.execute(new RunnableTask("task1"));
        printState(es, "task1");

        es.execute(new RunnableTask("task2"));
        printState(es, "task2");

        es.execute(new RunnableTask("task3"));
        printState(es, "task3");

        es.execute(new RunnableTask("task4"));
        printState(es, "task4");

        es.execute(new RunnableTask("task5"));
        printState(es, "task5"); // 이때부터 corePoolSize가 최대 4개까지 1개씩 늘어남

        es.execute(new RunnableTask("task6"));
        printState(es, "task6"); // maximum pool size 다 참

        try {
            es.execute(new RunnableTask("task7"));
            printState(es, "task7"); // 예외 발생
        } catch (RejectedExecutionException e){
            log("task7 실행 거절 예외 발생: " + e);
        }
        sleep(3000);
        log("== 작업 수행 완료 ==");

        // [pool= 4, activeSize = 0, queuedTasks = 0, completedTaskCount = 6]
        printState(es);

        sleep(3000);
        // [pool= 2, activeSize = 0, queuedTasks = 0, completedTaskCount = 6]
        log("== maximumSizePoolSize 대기 시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }

}
