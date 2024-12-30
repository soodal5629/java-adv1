package com.example.javaadv1.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.example.javaadv1.util.MyLogger.log;

/**
 * BlockingQueue 사용 - 일정 시간 동안 스레드 대기 후 결과 반환(생산자/소비자 문제 제대로 해결하지 못할 수도 있음)
 */
public class BoundedQueueV6_3 implements BoundedQueue {
    private BlockingQueue<String> queue;

    public BoundedQueueV6_3(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        try {
            // 대기 시간 설정
            boolean result = queue.offer(data,1 , TimeUnit.NANOSECONDS);
            log("저장 시도 결과 = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            // 대기 시간 설정
            return queue.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}