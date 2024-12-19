package com.example.javaadv1.thread.volatile1;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "wordk");
        t.start();

        sleep(1000);
        task.flag = false;
        log("### flag = " + task.flag + ", cnt = " + task.cnt + " in main");
    }

    static class MyTask implements Runnable {
//        boolean flag = true;
//        long cnt;

        volatile boolean flag = true;
        volatile long cnt;

        @Override
        public void run() {
            while(flag) {
                cnt++;
                // 1억번에 한번씩 출력 (자바에서 _ 지원)
                if(cnt % 100_000_000 == 0) {
                    log("flag = " + flag + ", cnt = " + cnt + " in while()");
                }
            }
            log("flag = " + flag + ", cnt = " + cnt + " 종료");
        }
    }
}
