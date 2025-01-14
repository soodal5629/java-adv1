package com.example.javaadv1.thread.executor.future;

import java.util.concurrent.*;

import static com.example.javaadv1.util.MyLogger.log;

public class SumTaskMainV2_Bad {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        ExecutorService es = Executors.newFixedThreadPool(2);
        // Future 사용 안좋은 예시
        Future<Integer> future1 = es.submit(task1); // 블로킹 x
        Integer sum1 = future1.get(); // 블로킹 - 2초 지연
        // 이것도 코드를 합친 것 뿐이므로 안좋은 예시임
        //Integer res1 = es.submit(task1).get();

        Future<Integer> future2 = es.submit(task2); // 블로킹 x
        Integer sum2 = future2.get(); // 블로킹 - 2초 지연
        //Integer res2 = es.submit(task2).get();

        log("task1.result = " + sum1);
        log("task2.result = " + sum2);
        log("task1 + task2 = " + (sum1+sum2));

        es.close();
    }

    static class SumTask implements Callable<Integer> {
        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws Exception {
            log("작업 시작");
            Thread.sleep(2000); // Callable은 예외 던질 수 있음
            int sum = 0;
            for (int i = startValue; i <=endValue ; i++) {
                sum+=i;
            }
            log("작업 완료 result = " + sum);
            return sum;
        }
    }
}