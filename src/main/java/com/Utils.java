package com;

import java.util.ArrayList;

public final class Utils {

    private Utils() {}

    public static Long binaryArrayToDecimal(ArrayList<Long> binaryArray) {
        StringBuilder builder = new StringBuilder();

        for(Long elem: binaryArray) {
            builder.append(elem);
        }

        String binary = builder.toString();

        long decimal = Long.parseLong(binary, 2);

        return decimal;
    }

    public static String binaryArrayToString(ArrayList<Long> binaryArray) {
        StringBuilder builder = new StringBuilder();

        for(Long elem: binaryArray) {
            builder.append(elem);
        }

        String binary = builder.toString();

        return binary;
    }

    public static void displayArray(ArrayList<Long> array) {

        for (Long elem: array) {
            System.out.print(elem + " ");
        }
        System.out.println("");
    }

    public static void displayPublicKey(Integer[] x) {

        System.out.print("(" + x[0] + ")");
        for (int i = 1; i < x.length; i++) {
            System.out.print(x[i] + "|");
        }
        System.out.println();
    }
}
