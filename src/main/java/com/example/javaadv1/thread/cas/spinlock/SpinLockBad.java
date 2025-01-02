package com.example.javaadv1.thread.cas.spinlock;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * 락을 획득/반납하는 것을 직접 구현 - 멀티스레드 환경에서 제대로 동작하지 않는다
 * 스레드들이 동시에 락 획득 시도했을 때 lock 초기값은 false 이므로 둘다 sleep() 을 하고 lock을 true로 바꿔 break한다.
 * 즉, 스레드들이 while문 내, if 문을 동시에 진입하는 것 - 락 획득/반납이 제대로 안되고 있음
 */
public class SpinLockBad {
    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");
        while(true) {
            if(!lock) { // 1. 락 사용 여부 확인
                sleep(100); // 문제 상황 확인용, 스레드 대기
                lock = true; // 2. 락의 값 변경
                break;
            } else {
                // 락을 획득할 때까지 스핀 대기(바쁜 대기)한다.
                log("락 획득 실해 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
