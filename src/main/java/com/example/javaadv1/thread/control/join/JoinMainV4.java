package com.example.javaadv1.thread.control.join;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * main 메소드가 join을 통해 각 스레드의 작업 완료를 기다리기 때문에 작업 결과를 제대로 받을 수 있음
 * - 특정 시간만큼만 대기
 */
public class JoinMainV4 {
    public static void main(String[] args) throws InterruptedException {
        log("main start");
        SumTask task1 = new SumTask(1, 50);
        Thread thread1 = new Thread(task1, "thread-1");
        thread1.start();

        // join을 사용하여 스레드가 종료될 때까지 대기 - 타이밍이 정확함
        log("join() - main 스레드가 thread1 종료까지 1초만 대기");
        thread1.join(1000);
        log("main 스레드 대기 완료");

        log("task1.result = " + task1.result);
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("SumTask start");
            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum+=i;
            }
            result = sum;
            log("SumTask end result = " + result);
        }
    }
}
