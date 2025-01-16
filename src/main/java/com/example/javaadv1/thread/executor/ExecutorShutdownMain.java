package com.example.javaadv1.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.example.javaadv1.thread.executor.ExecutorUtils.printState;
import static com.example.javaadv1.util.MyLogger.log;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("longTask", 100_000)); // 100초 대기
        printState(es);

        log("== shutdown 시작");
        shutdownAndAwaitTermination(es);
        log("== shutdown 완료");

        printState(es);
    }

    private static void shutdownAndAwaitTermination(ExecutorService es){
        es.shutdown(); // non-blocking, 새로운 작업을 받지 않는다. 처리 중이거나 큐에 이미 대기중인 작업은 처리한다. 이후에 풀의 스레드 종료

        try {
            // 이미 대기중인 작업들을 모두 완료할 때까지 10초 기다림
            if(!es.awaitTermination(5, TimeUnit.SECONDS)){
                // 정상 종료가 너무 오래 걸리면..
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow(); // 강제 종료
                // 작업이 취소될 때까지 대기
                if(!es.awaitTermination(5, TimeUnit.SECONDS)){
                    log("서비스가 종료되지 않았습니다."); // shutdownNow() 했는데도 종료 안되면 자바를 종료하든가 해야 함
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination()으로 대기중인 현재 스레드가 인터럽트 될 수 있음
            es.shutdownNow();
        }
    }
}
