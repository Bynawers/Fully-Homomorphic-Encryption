package com;

import java.util.Random;
import java.security.SecureRandom;

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

        while(randomNumber % 2 == 0 || randomNumber < lowerBound || randomNumber >= upperBound ) {

            StringBuilder privateKeyBinary = new StringBuilder();
            
            //pour vérifier la taille de la clé privé
            for(int i = 0; i < Parameters.PRIVATE_KEY_LENGTH; i++) {
                int bit = random.nextInt(2);
                privateKeyBinary.append(bit);
            }

            String privateKeyBinaryString = privateKeyBinary.toString ();
            //System.out.println("private key string = " + privateKeyBinaryString);
            randomNumber = Integer.parseInt(privateKeyBinaryString, 2 );
            
        }

        System.out.println("private key = " + randomNumber +"\n");
        return randomNumber;
    }

    private Integer[] publicKeyGen() {
        Integer[] x = new Integer[Parameters.PUBLIC_KEY_INTEGER_NUMBER+1];
        int indexX0 = 0;
        int tmp = 0;

        for(int i = 0; i < Parameters.PUBLIC_KEY_INTEGER_NUMBER + 1; i++) {
            x[i] = distribution(this.privateKey);
            if (x[indexX0] < x[i]) {
                indexX0 = i;
            }
        }
        tmp = x[indexX0];
        x[indexX0] = x[0];
        x[0] = tmp;

        
        if (x[indexX0] % 2 == 0) {
            System.out.println("x0 paire");
            return publicKeyGen();
        }

        /*
            le cas ou rp(x0) est impaire
        */

        displayPublicKey(x);

        System.out.print("public key = ");
        System.out.println(publicKeyToInt(x));

        return x;     
    }

    public Integer distribution(Integer privateKey) {
      
      while(true) { 
        Random random = new Random();
      
        int lowerBound = 0;
        int upperBound = (int) Math.floor((Math.pow(2, Parameters.PUBLIC_KEY_INTEGER_LENGTH)) / privateKey);

     
        Integer q = random.nextInt(upperBound - lowerBound) + lowerBound;

        lowerBound = -(int) Math.pow(2, Parameters.NOISE_LENGTH);
        upperBound = (int) Math.pow(2, Parameters.NOISE_LENGTH);

        Integer r = random.nextInt(upperBound - lowerBound) + lowerBound;
        
        int nb = privateKey*q+r;

        String binaryDistribution = Integer.toBinaryString(nb);

        if (binaryDistribution.length() == Parameters.PUBLIC_KEY_INTEGER_LENGTH) {
            return privateKey * q + r;
        }    
      } 
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