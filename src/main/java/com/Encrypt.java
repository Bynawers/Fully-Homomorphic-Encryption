package com;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Random;

public class Encrypt {

    private Integer[] publicKey;
    private Integer privateKey; 
    private Integer message;
    private ArrayList<Integer> encryptedMessageBinary = new ArrayList<>();

    private BigInteger encryptedMessage;
    
    public Encrypt(Integer[] publicKey, Integer message, Integer privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.message = message;
        ArrayList<Integer> encryptedMessageBuilder = new ArrayList<>();
        Integer encryptedBit;

        System.out.println("\nMessage : \n"+ message);
        System.out.println("\nMessage Binary : ");
        for (int i = Integer.SIZE; i >= 0 ; i--) {
            int bit = (message >> i) & 1;
            System.out.print(bit);
            encryptedBit = EncryptBit(bit);
            encryptedMessageBuilder.add(encryptedBit);
        }
        System.out.println();

        encryptedMessage = listToInteger(encryptedMessageBuilder);
        this.encryptedMessageBinary = encryptedMessageBuilder;
        System.out.println("\nCipher Components: ");
        displayEncrypted();
    }

    public Integer EncryptBit(Integer bit) {

        Random random = new Random();

        Integer lambda = random.nextInt(Parameters.VA_SECURITY);
        Integer r = random.nextInt(Math.abs(privateKey/4 - 1));

        Integer encryptedBit = bit + 2*r + lambda*privateKey;

        encryptedMessageBinary.add(encryptedBit);

        return encryptedBit;
    }

    public ArrayList<Integer> encryptedMessageBinary() {
        return encryptedMessageBinary;
    }

    private BigInteger listToInteger(ArrayList<Integer> encryptedMessageBit) {
        StringBuilder builder = new StringBuilder();

        for (Integer chiffre : encryptedMessageBit) {
            builder.append(chiffre);
        }

        String listString = builder.toString();

        BigInteger listInt = new BigInteger(listString);

        BigInteger res = listInt.remainder(BigInteger.valueOf(privateKey));

        return listInt;
    }

    public void displayEncrypted() {
        for (Integer bit: this.encryptedMessageBinary) {
            System.out.print(bit + " ");
        }
        System.out.println();
    }
}