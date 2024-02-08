package com.turlyunef.concurrency.waitnotify;

import java.util.List;

public class TrafficLight {
    private volatile Color color = Color.GREEN;

    public synchronized void changeColor() throws InterruptedException {
        switch (color) {
            case GREEN:
                color = Color.YELLOW_RED;
                Thread.sleep(3000);
                break;
            case YELLOW_RED:
                color = Color.RED;
                Thread.sleep(1000);
                break;
            case RED:
                color = Color.YELLOW_GREEN;
                Thread.sleep(2000);
                break;
            case YELLOW_GREEN:
                color = Color.GREEN;
                Thread.sleep(1000);
                break;
        }
        notifyAll();
    }

    public synchronized void waitForGreen() throws InterruptedException {
        while (color != Color.GREEN) {
            wait();
        }
    }

    public synchronized void waitForYellow() throws InterruptedException {
        while (!List.of(Color.YELLOW_GREEN, Color.YELLOW_RED).contains(color)) {
            wait();
        }
    }

    public synchronized void waitForRed() throws InterruptedException {
        while (color != Color.RED) {
            wait();
        }
    }
}
