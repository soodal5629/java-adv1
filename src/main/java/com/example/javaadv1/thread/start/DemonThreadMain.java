package com.example.javaadv1.thread.start;

public class DemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(false); // 데몬 스레드 여부
        daemonThread.start();
        System.out.println(Thread.currentThread().getName() + ": main() end");

    }

    static class DaemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");
            try {
                // 10초간 실행(user 스레드가 모두 종료되면 그냥 데몬 스레드의 작업과는 상관없이 그냥 JVM이 끝나버림. 즉, 소용 없음)
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": run() end");
        }
    }
}
