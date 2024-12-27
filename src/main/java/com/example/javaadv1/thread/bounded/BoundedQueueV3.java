package com.example.javaadv1.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) { // 스레드가 while 문 돌면서 대기
            log("[put] 큐가 가득 참, 생산자 대기");
            try {
                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        // 대기하는 소비자가 있을 경우 알려줌
        log("[put] 생산자 데이터 저장, notify() 호출");
        notify(); // 대기 스레드, WAIT -> BLOCKED

    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) { // 스레드가 while 문 돌면서 대기
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            try {
                wait();
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] 소비자 데이터 획득, notify() 호출");
        // 대기하는 생산자가 있을 경우 알려줌 (WAIT -> BLOCKED)
        notify();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}