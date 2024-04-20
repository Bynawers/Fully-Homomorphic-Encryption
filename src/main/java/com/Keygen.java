package com;

import java.util.Random;
import java.math.BigInteger;

public class Keygen {

    public BigInteger[] publicKey;
    public BigInteger privateKey;

    public BigInteger[] noise = new BigInteger[Parameters.PUBLIC_KEY_INTEGER_NUMBER];

    public Keygen() {
        privateKey = privateKeyGen();
        publicKey = publicKeyGen(0);
    }

    private BigInteger privateKeyGen() {
        Random random = new Random();
        BigInteger lowerBound = BigInteger.valueOf(2).pow(Parameters.PRIVATE_KEY_LENGTH - 1);
        BigInteger upperBound = BigInteger.valueOf(2).pow(Parameters.PRIVATE_KEY_LENGTH);

        BigInteger randomNumber;
        do {
            randomNumber = new BigInteger(upperBound.subtract(lowerBound).bitLength(), random);
        } while (randomNumber.compareTo(lowerBound) < 0 || randomNumber.compareTo(upperBound) >= 0 || randomNumber.mod(BigInteger.TWO).equals(BigInteger.ZERO));

        return randomNumber;
    }

    private BigInteger[] publicKeyGen(Integer loop) {
        BigInteger[] x = new BigInteger[Parameters.PUBLIC_KEY_INTEGER_NUMBER];
        BigInteger tmp = BigInteger.ZERO;
        BigInteger[] result = new BigInteger[2];
        int indexX0 = 0;

        for (int i = 0; i < Parameters.PUBLIC_KEY_INTEGER_NUMBER; i++) {
            result = distribution();
            x[i] = result[0];
            noise[i] = result[1];
            if (x[indexX0].compareTo(x[i]) < 0) {
                indexX0 = i;
            }
        }
        tmp = x[indexX0];
        x[indexX0] = x[0];
        x[0] = tmp;

        tmp = noise[indexX0];
        noise[indexX0] = noise[0];
        noise[0] = tmp;

        BigInteger rpx = x[0].mod(privateKey);

        if (rpx.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
            return publicKeyGen(loop + 1);
        }
        if (x[0].mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return publicKeyGen(loop + 1);
        }

        return x;
    }

    public BigInteger[] distribution() {
        Random random = new Random();

        BigInteger lowerBound = BigInteger.ZERO;
        BigInteger upperBound = BigInteger.valueOf(2).pow(Parameters.PUBLIC_KEY_INTEGER_LENGTH).divide(privateKey);

        BigInteger q;
        do {
            q = new BigInteger(upperBound.subtract(lowerBound).bitLength(), random);
        } while (q.compareTo(lowerBound) < 0 || q.compareTo(upperBound) >= 0);

        lowerBound = BigInteger.valueOf(-1).multiply(BigInteger.valueOf(2).pow(Parameters.NOISE_LENGTH));
        upperBound = BigInteger.valueOf(2).pow(Parameters.NOISE_LENGTH);

        BigInteger r;
        do {
            r = new BigInteger(upperBound.subtract(lowerBound).bitLength(), random);
        } while (r.compareTo(lowerBound) < 0 || r.compareTo(upperBound) >= 0);

        if (Parameters.RANDOM) {
            BigInteger[] result = new BigInteger[2];
            result[0] = privateKey.multiply(q).add(r);
            result[1] = r;
            return result;
        } else {
            BigInteger[] result = new BigInteger[2];
            result[0] = privateKey.multiply(q);
            result[1] = BigInteger.valueOf(0);
            return result;
        }
    }


    public void display() {
        System.out.println("================== KEYGEN ==================");
        System.out.println("PUBLIC KEY : ");
        System.out.println("> rp(x0) = "+ publicKey[0].mod(privateKey));
        System.out.println("> x0 = "+ publicKey[0] );
        System.out.println("PRIVATE KEY : ");
        System.out.println("> " + privateKey);
    }
}