package com.example.javaadv1.thread.volatile1;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("changed runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {
        boolean runFlag = true;
        // volatile boolean runFlag = true;
        @Override
        public void run() {
            log("task 시작");
            while(runFlag) {
                // runFlag가 false 되면 종료
            }
            // runFlag가 false가 되어도 무한 반복되어 해당 로그 안찍힘
            log("task 종료");
        }
    }
}
