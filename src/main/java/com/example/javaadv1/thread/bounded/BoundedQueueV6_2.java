package com.example.javaadv1.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.example.javaadv1.util.MyLogger.log;

/**
 * BlockingQueue 사용 - 스레드 대기 없이 즉시 반환(생산자/소비자 문제 제대로 해결하지 못함)
 */
public class BoundedQueueV6_2 implements BoundedQueue {
    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data);
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}