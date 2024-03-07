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

        if (((x[0] % privateKey) % 2) == 1) {
            return publicKeyGen();
        }
        if ((x[0] % 2 == 0)) {
            //return publicKeyGen();
        }

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

        return  Math.abs(privateKey * q + r);
    }


    public void display() {
        System.out.println("====== KEYGEN ======");
        System.out.println("> PUBLIC KEY : ");
        System.out.println("> rp(x0) = "+ publicKey[0] % privateKey );
        Utils.displayPublicKey(publicKey);
        System.out.print("> PRIVATE KEY : ");
        System.out.print(privateKey + "\n");
        System.out.println("====================");
    }
}