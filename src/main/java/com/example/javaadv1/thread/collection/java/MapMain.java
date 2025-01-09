package com.example.javaadv1.thread.collection.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapMain {
    public static void main(String[] args) {
        // 순서 보장 x
        Map<Integer, String> map1 = new ConcurrentHashMap<>();
        map1.put(3, "data3");
        map1.put(2, "data2");
        map1.put(1, "data1");
        System.out.println("map1 = " + map1);

        // 데이터 정렬 자동으로 해줌 ex) 3, 2, 1 순으로 put해도 1, 2, 3으로 나옴
        Map<Integer, String> map2 = new ConcurrentSkipListMap<>();
        map2.put(3, "data3");
        map2.put(2, "data2");
        map2.put(1, "data1");
        System.out.println("map2 = " + map2);
    }
}
