package com.example.javaadv1.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) { // 스레드가 while 문 돌면서 대기
            log("[put] 큐가 가득 참, 생산자 대기");
            sleep(1000); // 로그 너무 많이 찍히니까 1초동안 일단 기다림
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) { // 스레드가 while 문 돌면서 대기
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            sleep(1000); // 로그 너무 많이 찍히니까 1초동안 일단 기다림
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}