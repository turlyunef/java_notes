package com.turlyunef.concurrency.waitnotify;

public class WaitNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        TrafficLight trafficLight = new TrafficLight();
        Thread green = new Thread(() -> {
            while (true) {
                try {
                    trafficLight.waitForGreen();
                    System.out.println("GREEN");
                    trafficLight.changeColor();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread yellow = new Thread(() -> {
            while (true) {
                try {
                    trafficLight.waitForYellow();
                    System.out.println("YELLOW");
                    trafficLight.changeColor();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread red = new Thread(() -> {
            while (true) {
                try {
                    trafficLight.waitForRed();
                    System.out.println("RED");
                    trafficLight.changeColor();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        green.start();
        yellow.start();
        red.start();
        System.out.println("main");
    }
}
