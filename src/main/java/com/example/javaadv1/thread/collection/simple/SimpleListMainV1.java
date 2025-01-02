package com.example.javaadv1.thread.collection.simple;

import com.example.javaadv1.thread.collection.simple.list.BasicList;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV1 {
    public static void main(String[] args) {
        BasicList list = new BasicList();
        list.add("A");
        list.add("B");
        System.out.println("list = " + list);
    }
}
