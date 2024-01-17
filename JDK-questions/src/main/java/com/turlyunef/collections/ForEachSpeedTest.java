package com.turlyunef.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Эксперимент на вопрос: Какую коллекцию целых чисел (ArrayList, LinkedList, HashSet, TreeSet)
 * быстрее будет обойти путем вызова у коллекции метода collection.forEach(Consumer<> action) и почему?
 * <p>
 * Для исключения влияния работы GC желательные настройки запуска:
 * -Xlog:gc -XX:+UseParallelGC -Xms16000m -Xmx16000m -XX:+DisableExplicitGC
 */
public class ForEachSpeedTest {
    /**
     * Количество повторов тестирования каждой коллекции
     */
    public static final int REPEATS = 20;
    /**
     * Размер элементов в каждой коллекции
     */
    public static final int COLLECTION_SIZE = 400_000;
    /**
     * Делитель временного результата для перевода в мс
     */
    public static final int TIME_DIVISOR_FOR_MS = 1_000_000;

    public static void main(String[] args) {
        runRepeatedTests(ArrayList.class.getSimpleName(), ArrayList::new);
        runRepeatedTests(LinkedList.class.getSimpleName(), LinkedList::new);
        runRepeatedTests(HashSet.class.getSimpleName(), HashSet::new);
        runRepeatedTests(TreeSet.class.getSimpleName(), TreeSet::new);

    }

    private static void runRepeatedTests(String collectionName,
                                         Supplier<Collection<Integer>> collectionSupplier) {
        System.out.println("\n\nStart testing " + collectionName);
        long[] times = new long[REPEATS];
        for (int i = 0; i < REPEATS; i++) {
            Collection<Integer> collection = collectionSupplier.get();
            fillCollectionWithRandomNumbers(collection);
            testCollectionAndPrintResult(collection, times, i);
        }
        countAverageTimeAndPrint(times, collectionName);
    }

    private static void testCollectionAndPrintResult(Collection<Integer> collection,
                                                     long[] arrayListTimes, int repeatIndex) {
        long startTime = System.nanoTime();
        StringBuilder collector = new StringBuilder();
        collection.forEach(item -> collector.append(item).append(" "));
        long endTime = System.nanoTime();
        long timePeriod = (endTime - startTime) / TIME_DIVISOR_FOR_MS;
        arrayListTimes[repeatIndex] = timePeriod;
        System.out.printf("%d |", timePeriod);
    }

    private static void countAverageTimeAndPrint(long[] treeSetTimes, String collectionName) {
        long treeSetSumTime = 0;
        for (int i = 0; i < REPEATS; i++) {
            treeSetSumTime += treeSetTimes[i];
        }
        System.out.printf("\n %s forEach average time: %d ms\n", collectionName, treeSetSumTime / REPEATS);
    }


    private static void fillCollectionWithRandomNumbers(Collection<Integer> collection) {
        for (int i = 0; i < COLLECTION_SIZE; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt();
            collection.add(randomNum);
        }
    }
}
