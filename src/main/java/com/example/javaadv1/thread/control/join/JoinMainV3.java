package com.example.javaadv1.thread.control.join;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * main 메소드가 join을 통해 각 스레드의 작업 완료를 기다리기 때문에 작업 결과를 제대로 받을 수 있음
 * - 스레드가 끝날 때까지 무기한 대기
 */
public class JoinMainV3 {
    public static void main(String[] args) throws InterruptedException {
        log("main start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();

        // join을 사용하여 스레드가 종료될 때까지 대기 - 타이밍이 정확함
        log("join() - main 스레드가 종료까지 대기");
        thread1.join();
        thread2.join();
        log("main 스레드 대기 완료");

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);
        log("main end " + (task1.result + task2.result));
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
