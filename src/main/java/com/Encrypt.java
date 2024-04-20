package com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Encrypt {

    public BigInteger[] value;
    public BigInteger[] noise;

    public Encrypt(BigInteger[] publicKey, BigInteger message, BigInteger privateKey, BigInteger[] noisePk) {
        ArrayList<BigInteger> encryptedMessageBuilder = new ArrayList<>();
        ArrayList<BigInteger> noiseBuilder = new ArrayList<>();
        
        Integer bitLength = message.equals(BigInteger.ZERO) ? 1 : message.bitLength();
        for (int i = bitLength - 1; i >= 0; i--) {
            int bit = message.testBit(i) ? 1 : 0;
            BigInteger[] result = encryptBit(bit, privateKey, publicKey, noisePk);
            encryptedMessageBuilder.add(result[0]);
            noiseBuilder.add(result[1]);
        }

        this.noise = noiseBuilder.toArray(new BigInteger[0]);
        this.value = encryptedMessageBuilder.toArray(new BigInteger[0]);
    }

    public BigInteger[] encryptBit(int bit, BigInteger privateKey, BigInteger[] publicKey, BigInteger[] noisePk) {

        BigInteger[] result = new BigInteger[2];
        Random random = new Random();
        BigInteger lowerBound = BigInteger.valueOf(-1).multiply(BigInteger.valueOf(2).pow(Parameters.NOISE_LENGTH_PRIME));
        BigInteger upperBound = BigInteger.valueOf(2).pow(Parameters.NOISE_LENGTH_PRIME);

        BigInteger r;
        do {
            r = new BigInteger(upperBound.subtract(lowerBound).bitLength(), random);
        } while (r.compareTo(lowerBound) < 0 || r.compareTo(upperBound) >= 0);

        BigInteger[] sumRes = sumRandomSubset(publicKey, noisePk);
        BigInteger sumRandomX = sumRes[0];
        BigInteger sumNoise = sumRes[1].multiply(BigInteger.TWO);
        
        if (r.compareTo(privateKey) > 0) {
            Utils.warning("Random bigger than private key (Encryption)");
        }

        BigInteger encryptedBit;

        if (!Parameters.RANDOM) {
            encryptedBit = BigInteger.valueOf(bit)
                .add(sumRandomX.multiply(BigInteger.TWO))
                .mod(publicKey[0]);
        } else {
            encryptedBit = BigInteger.valueOf(bit)
                .add(r.multiply(BigInteger.TWO))
                .add(sumRandomX.multiply(BigInteger.TWO))
                .mod(publicKey[0]);
            result[1] = sumNoise.add(r.multiply(BigInteger.TWO));
        }

        if (encryptedBit.compareTo(BigInteger.ZERO) < 0) {
            encryptedBit = encryptedBit.add(publicKey[0]);
        }

        result[0] = encryptedBit;
        return result;
    }

    private BigInteger[] sumRandomSubset(BigInteger[] publicKey, BigInteger[] noisePk) {
    
        Random random = new Random();
        BigInteger sum = BigInteger.ZERO;
        BigInteger[] result = new BigInteger[2];
        result[0] = BigInteger.ZERO;
        result[1] = BigInteger.ZERO;
        Integer i = 0;

        for (BigInteger value : publicKey) {
            int r = random.nextInt(2);
            if (r == 1) {
                sum = sum.add(value);
                result[1] = result[1].add(noisePk[i]);
                if (sum.compareTo(BigInteger.ZERO) < 0) {
                    Utils.warning("Dépassement de variable (Sum x[i])");
                }
            }
            i++;
        }
        result[0] = sum;
        return result;
    }
}