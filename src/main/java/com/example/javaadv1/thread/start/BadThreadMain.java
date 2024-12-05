package com.example.javaadv1.thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": before start()");
        helloThread.run();
        System.out.println(Thread.currentThread().getName() + ": after start()");

        System.out.println(Thread.currentThread().getName() + " main() end");
    }
}
