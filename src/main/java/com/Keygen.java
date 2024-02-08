package com;

import java.util.Random;

public class Keygen {

    private Integer[] publicKey;
    private Integer privateKey;

    public Keygen() {
        privateKey = privateKeyGen();
        publicKey = publicKeyGen();
    }

    public Integer[] getPublicKey() {
        return publicKey;
    }
    
    public Integer getPrivateKey() {
        return privateKey;
    }

    private Integer privateKeyGen() {

        Random random = new Random();
        
        int lowerBound = (int) Math.pow(2, Parameters.PRIVATE_KEY_LENGTH - 1);
        int upperBound = (int) Math.pow(2, Parameters.PRIVATE_KEY_LENGTH);

        int randomNumber = 0;

        while(randomNumber % 2 == 0) {
            randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
        }

        System.out.println("private key = " + randomNumber);
        return randomNumber;
    }

    private Integer[] publicKeyGen() {
        Integer[] x = new Integer[Parameters.PUBLIC_KEY_INTEGER_NUMBER];
        int indexX0 = 0;
        int tmp = 0;

        for(int i = 0; i < Parameters.PUBLIC_KEY_INTEGER_NUMBER; i++) {
            x[i] = distribution(this.privateKey);
            if (x[indexX0] < x[i]) {
                indexX0 = i;
            }
        }
        tmp = x[indexX0];
        x[indexX0] = x[0];
        x[0] = tmp;

        if (x[indexX0] % 2 == 0) {
            return publicKeyGen();
        }

        displayPublicKey(x);

        System.out.print("public key = ");
        System.out.println(publicKeyToInt(x));

        return x;
    }

    public Integer distribution(Integer privateKey) {
        Random random = new Random();

        int lowerBound = 0;
        int upperBound = (int) Math.floor((Math.pow(2, Parameters.PUBLIC_KEY_INTEGER_LENGTH)) / privateKey);

        Integer q = random.nextInt(upperBound - lowerBound) + lowerBound;

        lowerBound = -(int) Math.pow(2, Parameters.NOISE_LENGTH);
        upperBound = (int) Math.pow(2, Parameters.NOISE_LENGTH);

        Integer r = random.nextInt(upperBound - lowerBound) + lowerBound;

        return privateKey * q + r;
    }

    private void displayPublicKey(Integer[] x) {
        System.out.print("Integer Public key = ");
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " | ");
        }
        System.out.println();
    }

    private Long publicKeyToInt(Integer[] publicKey) {
        StringBuilder publicKeyBuilder = new StringBuilder();

        for (int i = 0; i < publicKey.length; i++) {
            publicKeyBuilder.append(publicKey[i]);
        }

        String publicKeyStr = publicKeyBuilder.toString();

        long publicKeyInteger = Long.parseLong(publicKeyStr);

        return publicKeyInteger;
    }
}