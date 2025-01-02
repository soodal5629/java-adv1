package com.example.javaadv1.thread.collection.simple.list;

/**
 * 대부분의 컬렉션이 스레드 세이프하지 않다는 것을 보여줄 예시
 */
public interface SimpleList {
    int size();

    void add(Object e);

    Object get(int index);
}
