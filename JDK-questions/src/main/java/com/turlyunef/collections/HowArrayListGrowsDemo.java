package com.turlyunef.collections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Needed to add VM option to run configuration:
 * --add-opens java.base/java.util=ALL-UNNAMED
 */
public class HowArrayListGrowsDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field elementDataField = ArrayList.class.getDeclaredField("elementData");
        elementDataField.setAccessible(true);

        List<Integer> list = new ArrayList<>();
        printArrayListCapacity(list, elementDataField);

        list.add(1);
        printArrayListCapacity(list, elementDataField);

        list.add(1);
        printArrayListCapacity(list, elementDataField);
        for (int i = 0; i < 8; i++) {
            list.add(1);
        }
        printArrayListCapacity(list, elementDataField);

        list.add(1);
        printArrayListCapacity(list, elementDataField);
        System.out.println(10 + (10 >> 1));

        for (int i = 0; i < 5; i++) {
            list.add(1);
        }
        printArrayListCapacity(list, elementDataField);

        System.out.println(15 + (15 >> 1));
    }

    private static void printArrayListCapacity(List<Integer> list,
                                               Field elementDataField) throws IllegalAccessException {
        Object[] elementData = (Object[]) elementDataField.get(list);

        System.out.printf("size = %d, capacity = %d\n", list.size(), elementData.length);
    }
}
