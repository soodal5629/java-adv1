package com.example.javaadv1.thread.sync;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {
    // volatile 사용해도 동시성 문제 발생함..
    private int balance; // 잔고

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    // 메소드 전체에 synchronized 사용해서 동시성 문제 해결
    @Override
    public synchronized boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());
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
        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
