package com.turlyunef.simple;

/**
 * Demonstrations of working with object references
 */
public class ObjectRefDemo {

    public static void main(String[] args) {
        demoWhatAssignToReference();
        demoStringReference();
    }

    /**
     * Demo: when we assign some link to another link and change it
     */
    private static void demoWhatAssignToReference() {
        System.out.println("demoWhatAssignToLink");
        Integer one = 13;
        Integer two = one;
        two = null;
        System.out.printf("one = %s\n", one);
    }

    /**
     * Demo: when we assign String link to another link and then change it
     */
    private static void demoStringReference() {
        System.out.println("\ndemoStringLinks");
        String firstLink = "FIRST";
        String secondLink = firstLink;
        secondLink = "SECOND";
        System.out.printf("first Link = %s\n", firstLink);
        System.out.printf("second Link = %s\n", secondLink);
    }
}
