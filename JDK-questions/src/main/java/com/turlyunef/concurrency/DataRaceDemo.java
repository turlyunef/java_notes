package com.turlyunef.concurrency;

public class DataRaceDemo {
    private static int x = 0; // need volatile
    private static int y = 0; // need volatile

    public static void main(String[] args) throws InterruptedException {
        Thread changingThread = new Thread(() -> {
            x = 1;
            y = 1;
        });

        Thread checkingThread = new Thread(() -> {
            if (y > 0) {
                System.out.println(x);
            }
        });
            changingThread.start();
            checkingThread.start();
    }
}
