package com;

import java.util.ArrayList;
import java.util.Random;

public class Encrypt {

    private ArrayList<Long> valueBinaryArray = new ArrayList<>();
    
    public Encrypt(Integer[] publicKey, Integer message, Integer privateKey) {
        ArrayList<Long> encryptedMessageBuilder = new ArrayList<>();
        Long encryptedBit;

        for (int i = Integer.SIZE - 1; i >= 0 ; i--) {
            int bit = (message >> i) & 1;
            encryptedBit = EncryptBit(bit, privateKey, publicKey);
            encryptedMessageBuilder.add(encryptedBit);
        }

        this.valueBinaryArray = encryptedMessageBuilder;
    }

    public Long EncryptBit(Integer bit, Integer privateKey, Integer[] publicKey) {

        Random random = new Random();

        Integer r = random.nextInt(Math.abs(privateKey/2 - 1));

        Integer sumRandomX = SumRandomSubset(publicKey);
        
        Long encryptedBit = (bit + 2*r.longValue() + 2 * sumRandomX) % publicKey[0];

        valueBinaryArray.add(encryptedBit);

        return encryptedBit;
    }

    public ArrayList<Long> getValueBinaryArray() {
        return valueBinaryArray;
    }

    private Integer SumRandomSubset(Integer[] publicKey) {

        Random random = new Random();

        Integer r = 0;
        Integer sum = 0;
        
        for (Integer value: publicKey) {
            r = random.nextInt(2);
            if (r == 1) {
                sum = sum + value;
            }
        }
        return sum;
    }

    public void display(String name) {
        System.out.println("====== "+ name +" ENCRYPTION ======");
        System.out.print("> BINARY ARRAY : ");
        Utils.displayArray(valueBinaryArray);
        System.out.println("========================");
    }
}