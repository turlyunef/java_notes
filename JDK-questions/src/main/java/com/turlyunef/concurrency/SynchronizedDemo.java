package com.turlyunef.concurrency;

public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        // демонстрирует состояние гонки двух потоков за один ресурс
        demoSequence(new UnsafeSequence());
        Thread.sleep(1000);
        demoSequence(new SafeSequence());
    }

    private static void demoSequence(Sequence sequence) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sequence.increment();
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sequence.increment();
            }
            System.out.println("Counting was finished!");
        });
        one.start();
        second.start();
        one.join();
        second.join(); // требуются чтобы main поток дождался результатов перед их выводом
        System.out.println(sequence.getCount());
    }


    private static class UnsafeSequence implements Sequence {
        private int count = 0;

        public void increment() {
            count++;
        }

        @Override
        public int getCount() {
            return this.count;
        }
    }

    private static class SafeSequence implements Sequence {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        @Override
        public int getCount() {
            return this.count;
        }

    }

    private interface Sequence {
        void increment();

        int getCount();
    }
}

