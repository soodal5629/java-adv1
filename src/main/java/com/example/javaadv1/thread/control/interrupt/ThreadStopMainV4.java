package com.example.javaadv1.thread.control.interrupt;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * 특정 스레드의 작업을 중단하는 방법: 4. interrupt() 사용 + while 조건문에서 Thread.interrupted() 사용
 * - sleep()과 같은 메소드와 같은 메소들르 사용하면 바로 중단 불가
 */
public class ThreadStopMainV4 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();
        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt(); // sleep() 상태여도 바로 중단됨
        log("work thread interrupt state1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            // while 문에서 interrupt 상태 확인하여 바로 빠져나올 수 있도록
            while (!Thread.interrupted()) { // 인터럽트 상태 확인 + true 일 경우 false로 바꿔줌
                log("작업 중");
            }
            // ThreadStopMain2와 달리 여긴 true 가 나옴
            // 이는 InterruptException 걸리는 메소드 만나는 순간, 예외 터지고 상태가 interrupt 가 아니게 되는 것
            log("work thread interrupted state2 = " + Thread.currentThread().isInterrupted());
            try {
                log("자원 정리 시도");
                Thread.sleep(100);
                log("자원 정리 완료");
            }
            catch (InterruptedException e) { // 해당 예외도 발생하지 않음
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                // 여긴 false
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }
            log("자원 종료");
        }
    }
}
