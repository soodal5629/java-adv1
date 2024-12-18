package com.example.javaadv1.thread.control.interrupt;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * 특정 스레드의 작업을 중단하는 방법: 2. interrupt() 사용
 * - sleep()과 같은 메소드와 같은 메소들르 사용하면 바로 중단 불가
 */
public class ThreadStopMainV2 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();
        sleep(4000);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt(); // sleep() 상태여도 바로 중단됨
        log("work thread interrupt state1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true){
                    log("작업 중");
                    Thread.sleep(3000);
                }
            } catch(InterruptedException e) {
                // 여기서는 false가 나옴
                // - sleep() 부분에서 InterruptedException이 발생하고 정상 RUNNABLE 상태로 바뀌며 코드를 정상 수행하기 때문
                // - interrupt 걸었다가 sleep() 같은 메소드를 만나면 예외가 발생하고 다시 깨어났으므로 interrupt 상태가 아니게 됨
                log("work thread interrupted state2 = " + Thread.currentThread().isInterrupted());
                log("interrupted message = " + e.getMessage());
                log("state = " + Thread.currentThread().getState()); // RUNNABLE
            }
            log("자원 정리");
            log("자원 종료");
        }
    }
}
