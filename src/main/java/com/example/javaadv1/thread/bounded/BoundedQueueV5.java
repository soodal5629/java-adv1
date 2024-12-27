package com.example.javaadv1.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.javaadv1.util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    private final Lock lock = new ReentrantLock();
    private final Condition producerCond = lock.newCondition(); // 생산자 스레드 대기 집합
    private final Condition consumerCond = lock.newCondition(); // 소비자 스레드 대기 집합

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() == max) { // 스레드가 while 문 돌면서 대기
                log("[put] 큐가 가득 참, 생산자 대기");
                try {
                    producerCond.await();
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            // 대기하는 소비자가 있을 경우 알려줌
            log("[put] 생산자 데이터 저장, consumerCond.signal() 호출");
            consumerCond.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) { // 스레드가 while 문 돌면서 대기
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    consumerCond.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            log("[take] 소비자 데이터 획득, producerCond.signal() 호출");
            // 대기하는 생산자가 있을 경우 알려줌 (WAIT -> BLOCKED)
            producerCond.signal();
            return data;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return queue.toString();
    }
}