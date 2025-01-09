package com.example.javaadv1.thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 자바에서 제공하는 동기화 컬렉션
 */
public class SynchronizedListMain {
    public static void main(String[] args) {
        // synchronized 동기화가 적용됨
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("data1");
        list.add("data2");
        list.add("data3");
        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}
