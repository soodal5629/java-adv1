package com.example.javaadv1.thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * printer 예제 - isInterrupted() 이용하여 반응성 향상
 */
public class MyPrinterV2 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while(true) {
            log("Please enter the document you want to print. Exit (q): ");
            String input = userInput.nextLine();
            if(input.equals("q")) {
                printerThread.interrupt();
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                if(jobQueue.isEmpty()) {
                    continue;
                }
                try {
                    String job = jobQueue.poll();
                    log("print start : " + job + ", Waiting Queue: " + jobQueue);
                    Thread.sleep(3000);
                    log("print completed");
                } catch (InterruptedException e) {
                    log("interrupted!!");
                    break;
                }
            }
            log(">> printer finished");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }
    }
}
