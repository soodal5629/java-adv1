package com.example.javaadv1.thread.collection.simple.list;

import java.util.Arrays;

import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * 멀티스레드 환경에서 thread safe하도록 BasicList 수정(synchronized 사용)
 */
public class SyncList implements SimpleList {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elementData;
    private int size = 0;

    public SyncList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized void add(Object e) {
        elementData[size] = e;
        sleep(100); // 멀티스레드 문제를 쉽게 확인하는 코드
        size++;
    }

    @Override
    public synchronized Object get(int index) {
        return elementData[index];
    }

    @Override
    public synchronized String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size))
                + " size = " + size + ", capacity = " + elementData.length;
    }
}
