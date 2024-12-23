package com.example.javaadv1.thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {
    // volatile 사용해도 동시성 문제 발생함..
    private int balance; // 잔고

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int initialBalance) {
        this.balance = initialBalance;
    }

    // synchronized 코드 블럭 단위로 사용해서 동시성 문제 해결
    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        if(!lock.tryLock()) { // lock 못얻으면 즉시 false 리턴
            log("[진입 실패] 이미 처리중인 작업이 있습니다.");
            return false;
        }

        try {
            // == 임계 영역 시작 ==
            // 잔고가 출금액보다 적으면 진행하면 안됨
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if(balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }
            // 잔고가 출금액보다 많으면 진행
            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance -= amount;
            log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
            // == 임계 영역 종료 ==
        } finally {
            lock.unlock(); // 반드시 lock 풀어야 함
        }
        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }

    }
}
