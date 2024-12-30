package com.example.javaadv1.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.example.javaadv1.util.MyLogger.log;

/**
 * BlockingQueue 사용 - 스레드 대기 없이 예외 던짐(생산자/소비자 문제 제대로 해결 x)
 */
public class BoundedQueueV6_4 implements BoundedQueue {
    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // IllegalStateException: Queue full
    }

    @Override
    public String take() {
        return queue.remove(); // NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}