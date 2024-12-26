package com.example.javaadv1.thread.bounded;

/**
 * 버퍼 역할을 하는 인터페이스
 */
public interface BoundedQueue {
    void put(String data);

    String take();
}
