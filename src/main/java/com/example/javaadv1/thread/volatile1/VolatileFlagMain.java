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
        //boolean runFlag = true;
        // 캐시 메모리가 아닌 메인 메모리에 직접 접근하여 값을 read
        volatile boolean runFlag = true;
        @Override
        public void run() {
            log("task 시작");
            while(runFlag) {
                // runFlag가 false 되면 종료
                // 만약 여기서 뭔가 sout 혹은 log과 같은 동작을 하면 컨텍스트 스위칭이 일어나서 volatile을 사용안해도 제대로 반영될 수는 있음
                // -> but, 100% 보장되는 것은 아니므로 volatile 사용 권장
            }
            // volatile 생략하면 main 에서 runFlag가 false가 되어도 무한 반복되어 해당 로그 안찍힘
            log("task 종료");
        }
    }
}
