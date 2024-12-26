package com.example.javaadv1.thread.bounded;

import static com.example.javaadv1.util.MyLogger.log;

/**
 * 생산자 역할
 */
public class ProducerTask implements Runnable {
    private BoundedQueue queue;
    private String request;

    public ProducerTask(BoundedQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("[생산 시도] " + request + "->" + queue);
        queue.put(request);
        log("[생산 완료] " + request + "->" + queue);
    }
}
