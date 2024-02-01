package com.turlyunef.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Изучение поведения реализаций List при простейших действиях с ними
 */
public class ListOperations {
    public static void main(String[] args) {
        testLoopWithDeletedElement();
    }

    /**
     * Что произойдет с обходом цикла, если внутри него произойдет удаление элемента
     * полезная статья https://javarush.com/groups/posts/1935-udalenie-ehlementa-iz-spiska-arraylist
     */
    private static void testLoopWithDeletedElement() {
        List<Integer> list = new ArrayList<>();
        Integer first = 0;
        list.add(first);
        Integer second = 1;
        list.add(second);
        for (int i = 2; i < 5; i++) {
            list.add(i);
        }
        Integer five = 5;
        list.add(five);
        list.add(6);

        System.out.println("Исходный лист:");
        list.forEach(System.out::println);

        System.out.println("Тестируем цикл fori - удаление объекта, который еще не прочитан");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Читаем элемент %d: %d\n", i, list.get(i));
            if (i == 3) {
                System.out.println("Удаляем элемент 5 как объект");
                list.remove(five);
                // цикл не упадет, тк условие выполнение цикла i < list.size()
                // каждый раз высчитывается заново и цикл обойдет на 1 элемент меньше
            }
        }

        System.out.println("Результирующий лист:");
        list.forEach(System.out::println);

        System.out.println("Тестируем цикл fori - удаление прочитанного объекта");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Читаем элемент %d: %d\n", i, list.get(i));
            if (i == 1) {
                System.out.println("Удаляем первый элемент как объект");
                list.remove(first);
                // Цикл не упадет, но мы пропускаем обработку элемента
                // в итоге число 2 пропустится, т.к. элементы сдвинутся и
                // под индексом окажется элемент через 1
            }
        }
        System.out.println("Результирующий лист:");
        list.forEach(System.out::println);

        System.out.println("Тестируем цикл fori - удаление прочитанного объекта");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Читаем элемент %d: %d\n", i, list.get(i));
            if (i == 1) {
                System.out.println("Удаляем элемент с i = 1");
                list.remove(0);
// здесь аналогично, цикл не падает, так как механика удаления та же что и объектом
// пропустится обработка числа 3!!!
            }
        }
        System.out.println("Результирующий лист:");
        list.forEach(System.out::println);
    }
}
