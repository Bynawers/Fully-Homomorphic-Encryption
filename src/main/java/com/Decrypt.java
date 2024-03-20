package com;

import java.math.BigInteger;
import java.util.ArrayList;

public class Decrypt {

    private ArrayList<BigInteger> valueBinaryArray;
    private String valueBinary;
    private BigInteger value;

    public Decrypt(BigInteger privateKey, ArrayList<BigInteger> encryptedMessageBinary) {
        ArrayList<BigInteger> messageBinaryBuilder = new ArrayList<>();
        BigInteger messageBit;

        for (BigInteger cipherBit : encryptedMessageBinary) {
            messageBit = cipherBit.mod(privateKey).mod(BigInteger.valueOf(2));
            messageBinaryBuilder.add(messageBit);
        }
        valueBinaryArray = messageBinaryBuilder;
        value = Utils.binaryArrayToDecimal(messageBinaryBuilder);
    }

    public BigInteger getValue() {
        return value;
    }

    public String getValueBinary() {
        return valueBinary;
    }

    public ArrayList<BigInteger> getValueBinaryArray() {
        return valueBinaryArray;
    }

    public void display(String name) {
        System.out.println("====== "+ name +" DECRYPTION ======");
        System.out.print("> BINARY ARRAY : ");
        for(BigInteger element : valueBinaryArray) {
            System.out.print(element);
        }
        System.out.println();
        System.out.println("> DECIMAL : " + value);
        System.out.println("========================");
    }

}
