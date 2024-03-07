package com;

import java.util.ArrayList;

public class Decrypt {

    private ArrayList<Long> valueBinaryArray;
    private String valueBinary;
    private Long value;
    
    public Decrypt(Integer privateKey, ArrayList<Long> encryptedMessageBinary) {
        ArrayList<Long> messageBinaryBuilder = new ArrayList<>();
        Long messageBit;

        for (Long cipherBit: encryptedMessageBinary) {
            messageBit = (cipherBit % privateKey) % 2;
            messageBinaryBuilder.add(messageBit);
        }
        valueBinaryArray = messageBinaryBuilder;
        valueBinary = Utils.binaryArrayToString(messageBinaryBuilder);
        value = Utils.binaryArrayToDecimal(messageBinaryBuilder);
    }

    public Long getValue() {
        return value;
    }

    public String getValueBinary() {
        return valueBinary;
    }

    public ArrayList<Long> getValueBinaryArray() {
        return valueBinaryArray;
    }

    public void display(String name) {
        System.out.println("====== "+ name +" DECRYPTION ======");
        System.out.print("> BINARY ARRAY : ");
        System.out.println(valueBinary);
        System.out.println("> DECIMAL : " + value);
        System.out.println("========================");
    }

}
