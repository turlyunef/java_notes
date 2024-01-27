package com.turlyunef.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация как передается в метод аргумент в случае примитива, объекта и коллекции
 */
public class MethodArgsDemo {
    private static final Logger logger = LoggerFactory.getLogger(MethodArgsDemo.class);

    public static void main(String[] args) {
        int primitive = 123;

        logger.debug("Out of method: Primitive value = {}", primitive);
        primitiveChange(primitive);
        logger.debug("Out of method: Primitive value = {}", primitive);

        Long object = 123L;
        logger.debug("Out of method: Object value = {}", object);
        objectChange(object);
        logger.debug("Out of method: Object value = {}", object);
        objecttryToDelete(object);
        logger.debug("Out of method: Object value = {}", object);

        List<Integer> collection = new ArrayList<>();
        collection.add(123);
        collection.add(234);
        collection.add(345);

        logger.debug("Out of method: Collection contains:");
        collection.forEach(System.out::println);
        collectionChange(collection);
        logger.debug("Out of method: Collection contains:");
        collection.forEach(System.out::println);
    }

    private static int primitiveChange(int value) {
        logger.debug("In method: Primitive is changing to 456");
        value = 456;
        logger.debug("In method: local value = {}", value);
        return value;
    }

    private static Long objectChange(Long value) {
        logger.debug("In method: Object Long is changing to 789");
        value = 789L;
        logger.debug("In method: local value = {}", value);
        return value;
    }

    private static Long objecttryToDelete(Long value) {
        logger.debug("In method: Object Long is changing to null");
        value = null;
        logger.debug("In method: local value = {}", value);
        return value;
    }

    private static List<Integer> collectionChange(List<Integer> collection) {

        logger.debug("In method: Collection is changing - first element changed to 666");
        Integer first = collection.getFirst();
        logger.debug("In method: local first = {}", first);
        first = 666; // меняет ссылку first, а не элемент коллекции
        logger.debug("In method: local first = {}", first);
        logger.debug("In method: Collection is changing - last element deleted");
        collection.removeLast();
        return collection;
    }
}
