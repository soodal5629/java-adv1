package com.example.javaadv1.thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.example.javaadv1.util.MyLogger.log;
import static com.example.javaadv1.util.ThreadUtils.sleep;

/**
 * printer 예제 - 변수를 이용해서 interrupt
 */
public class MyPrinterV1 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while(true) {
            log("Please enter the document you want to print. Exit (q): ");
            String input = userInput.nextLine();
            if(input.equals("q")) {
                printer.work = false;
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while(work) {
                if(jobQueue.isEmpty()) {
                    continue;
                }
                String job = jobQueue.poll();
                log("print start : " + job + ", Waiting Queue: " + jobQueue);
                sleep(3000);
                log("print completed");
            }
            log(">> printer finished");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }
    }
}
