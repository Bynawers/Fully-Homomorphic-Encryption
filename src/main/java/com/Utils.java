package com;

import java.util.ArrayList;

import java.io.*;
import java.math.BigInteger;

public final class Utils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Utils() {}

    public static void warning(String message) {
        System.out.println(ANSI_YELLOW + "[WARNING] : " + ANSI_RESET + message);
    }

    public static BigInteger binaryArrayToDecimal(ArrayList<BigInteger> binaryArray) {
        try {
            StringBuilder builder = new StringBuilder();

            for(BigInteger elem: binaryArray) {
                builder.append(elem);
            }

            String binary = builder.toString();

            BigInteger decimal = new BigInteger(binary, 2);

            return decimal;
        } catch (Exception e) {
            System.out.println("ERROR NOT HANDLE (BinaryArrayToDecimal)");
            return(BigInteger.ZERO);
        }
    }

    public static String binaryArrayToString(ArrayList<BigInteger> binaryArray) {
        try {
            StringBuilder builder = new StringBuilder();

            for(BigInteger elem: binaryArray) {
                builder.append(elem);
            }

            String binary = builder.toString();

            return binary;
        } catch (Exception e) {
            System.out.println("ERROR NOT HANDLE (BinaryArrayToString)");
            return "0";
        }
    }

    public static void displayArray(ArrayList<BigInteger> array) {

        for (BigInteger elem: array) {
            System.out.print(elem + " ");
        }
        System.out.println("");
    }

    public static void displayPublicKey(BigInteger[] x) {

        System.out.print("(" + x[0] + ")");
        for (int i = 1; i < x.length; i++) {
            System.out.print(x[i] + "|");
        }
        System.out.println();
    }

    public static void saveFileTab(BigInteger[] value, String filename) {
        int NEW_LINE = 5; 
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("[ ");
            for (int i = 0; i < value.length; i++) {
                buffer.write(String.valueOf(value[i]) + " ");
                if (i % NEW_LINE == 0 && i != 0) {
                    buffer.newLine();
                }
            }
            buffer.write(" ]");
            buffer.close();
            //System.out.println("Save in file " + filename);
        } catch (Exception e) {
            System.err.println("Error save file");
        }
    }

    public static void saveFileArray(ArrayList<BigInteger> array, String filename) {
        int NEW_LINE = 5; 
        int i = 0;
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("[ ");
            for (BigInteger value : array) {
                buffer.write(String.valueOf(value) + " ");
                if (i % NEW_LINE == 0 && i != 0) {
                    buffer.newLine();
                }
                i++;
            }
            buffer.write(" ]");
            buffer.close();
            //System.out.println("Save in file " + filename);
        } catch (Exception e) {
            System.err.println("Error save file");
        }
    }

    public static void displayParameters() {
        System.out.println("==== Parameters ====");
        System.out.println("λ (SECURITY_PARAMETERS) : " + Parameters.VA_SECURITY);
        System.out.println("γ (PUBLIC_KEY_INTEGER_LENGTH) : " + Parameters.PUBLIC_KEY_INTEGER_LENGTH);
        System.out.println("η (PRIVATE_KEY_LENGTH) : " + Parameters.PRIVATE_KEY_LENGTH);
        System.out.println("ρ (NOISE_LENGTH) : " + Parameters.NOISE_LENGTH);
        System.out.println("ρ' (NOISE_LENGTH_PRIME) : " + Parameters.NOISE_LENGTH_PRIME);
        System.out.println("τ' (PUBLIC_KEY_INTEGER_NUMBER) : " + Parameters.PUBLIC_KEY_INTEGER_NUMBER);
        System.out.println("====================");
    }
}
