package com.example.javaadv1.thread.control;

import com.example.javaadv1.thread.start.HelloRunnable;

import static com.example.javaadv1.util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {
        // main 스레드 정보
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        // 스레드 id는 절대 중복되지 않음 java 19 이상에서는 threadId() 메소드 써야됨 - getId()는 deprecated 됨
        log("mainThread.threadId()= " + mainThread.getId());
        // 스레드 이름은 중복될 수 있음
        log("mainThread.getName()= " + mainThread.getName());
        // 우선순위는 default가 5이며 우선순위를 바꿀 수 있음 - 우선순위가 높을수록 cpu가 더 자주 실행함
        // -> priority 설정은 힐트일 뿐 항상 그런 것은 아니며 JVM 구현과 os에 따라 달라질 수 있음
        log("mainThread.getPriority()= " + mainThread.getPriority());
        log("mainThread.getThreadGroup()= " + mainThread.getThreadGroup());
        log("mainThread.getState()= " + mainThread.getState());

        // myThread 스레드 정보
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("myThread = " + myThread);
        // 스레드 id는 절대 중복되지 않음 java 19 이상에서는 threadId() 메소드 써야됨 - getId()는 deprecated 됨
        log("myThread.threadId()= " + myThread.getId());
        // 스레드 이름은 중복될 수 있음
        log("myThread.getName()= " + myThread.getName());
        // 우선순위는 default가 5이며 우선순위를 바꿀 수 있음 - 우선순위가 높을수록 cpu가 더 자주 실행함(항상 그런 것은 아니며 os가 알아서 함)
        log("myThread.getPriority()= " + myThread.getPriority());
        log("myThread.getThreadGroup()= " + myThread.getThreadGroup());
        // 스레드 생성하고 실행을 안하면 state가 NEW 임
        log("myThread.getState()= " + myThread.getState());
    }
}
