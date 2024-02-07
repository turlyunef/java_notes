package com.turlyunef.concurrency;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Something> somethingAtomicReference = new AtomicReference<>();
        somethingAtomicReference.set(new Something(0));
        changeInTwoThreads(somethingAtomicReference);
        System.out.println(somethingAtomicReference.get().index());
    }

    private static void changeInTwoThreads(AtomicReference<Something> somethingAtomicReference) throws InterruptedException {
        Thread first = new Thread(() -> incrementSomething(somethingAtomicReference));
        Thread second = new Thread(() -> incrementSomething(somethingAtomicReference));
        first.start();
        second.start();
        first.join();
        second.join();
    }

    private static void incrementSomething(AtomicReference<Something> somethingAtomicReference) {
        System.out.printf("%s started\n", Thread.currentThread().threadId());
        for (int i = 0; i < 100; i++) {
            Something initial = somethingAtomicReference.get();
            int index = initial.index();
            index++;
            Something newValue = new Something(index);
            boolean isSuccess = somethingAtomicReference.compareAndSet(initial, newValue);
            if (!isSuccess) {
                i--;
            }
        }
        System.out.printf("%s finished\n", Thread.currentThread().threadId());
    }

    private record Something(int index) {
    }
}
