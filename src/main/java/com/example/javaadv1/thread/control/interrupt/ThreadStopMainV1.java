package com.example.javaadv1.thread.control.interrupt;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * 특정 스레드의 작업을 중단하는 방법: 1. 변수를 사용 (가장 쉬운 방법)
 * - sleep()과 같은 메소드와 같은 메소들르 사용하면 바로 중단 불가
 */
public class ThreadStopMainV1 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();
        sleep(4000);
        log("작업 중단 지시 runFlag = false");
        task.runFlag = false;

    }
    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag){
                log("작업 중");
                sleep(3000);
            }
            log("자원 정리");
            log("자원 종료");
        }
    }
}
