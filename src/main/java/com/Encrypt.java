package com;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Encrypt {

    private ArrayList<BigInteger> valueBinaryArray = new ArrayList<>();
    private ArrayList<BigDecimal[]> valueBinaryArrayComplHomo = new ArrayList<>();

    public Encrypt(BigInteger[] publicKey, BigInteger message, BigInteger privateKey) {
        ArrayList<BigInteger> encryptedMessageBuilder = new ArrayList<>();

        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            int bit = message.testBit(i) ? 1 : 0;
            BigInteger encryptedBit = encryptBit(bit, privateKey, publicKey);
            encryptedMessageBuilder.add(encryptedBit);
        }

        this.valueBinaryArray = encryptedMessageBuilder;
    }

    public Encrypt(BigInteger[] publicKey, BigInteger message, BigInteger privateKey,BigDecimal[] publickeyComplHomo) {
        ArrayList<BigInteger> encryptedMessageBuilder = new ArrayList<>();
        ArrayList<BigDecimal[]> encryptedMessageBuilder1 = new ArrayList<>();

        //Integer precision = (int) Math.ceil(Math.log(Parameters.theta)) + 3;
        Integer precision = (int) Math.ceil(Math.log(Parameters.theta + 1 )/ (Math.log(2)) +3 ) ;
        BigDecimal[] vectz = new BigDecimal[Parameters.Theta];

        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            int bit = message.testBit(i) ? 1 : 0;
            BigInteger encryptedBit = encryptBit(bit, privateKey, publicKey);
            //System.out.println(encryptedBit);
            encryptedMessageBuilder.add(encryptedBit);

            for (int j = 0; j < publickeyComplHomo.length; j++) {
                vectz[j] = (publickeyComplHomo[j].multiply(new BigDecimal(encryptedBit))).remainder(BigDecimal.valueOf(2));
                vectz[j] = vectz[j].setScale(precision, BigDecimal.ROUND_DOWN); 
                //System.out.println("z = " + vectz[j]);
            }
            
            encryptedMessageBuilder1.add(vectz.clone());
        }

        this.valueBinaryArray = encryptedMessageBuilder;
        this.valueBinaryArrayComplHomo = encryptedMessageBuilder1;

    }

    

    public BigInteger encryptBit(int bit, BigInteger privateKey, BigInteger[] publicKey) {
        Random random = new Random();
        BigInteger lowerBound = BigInteger.valueOf(-1).multiply(BigInteger.valueOf(2).pow(Parameters.NOISE_LENGTH_PRIME));
        BigInteger upperBound = BigInteger.valueOf(2).pow(Parameters.NOISE_LENGTH_PRIME);

        BigInteger r;
        do {
            r = new BigInteger(upperBound.subtract(lowerBound).bitLength(), random);
        } while (r.compareTo(lowerBound) < 0 || r.compareTo(upperBound) >= 0);

        BigInteger sumRandomX = sumRandomSubset(publicKey);

        if (r.compareTo(privateKey) > 0) {
            Utils.warning("Random bigger than private key (Encryption)");
        }

        BigInteger encryptedBit = BigInteger.valueOf(bit)
                .add(r.multiply(BigInteger.TWO))
                .add(sumRandomX.multiply(BigInteger.TWO))
                .mod(publicKey[0]);

        System.out.println("( " + bit + " + " + r.multiply(BigInteger.TWO) + " + " + sumRandomX.multiply(BigInteger.TWO) + " ) mod " + publicKey[0]);

        if (encryptedBit.compareTo(BigInteger.ZERO) < 0) {
            encryptedBit = encryptedBit.add(publicKey[0]);
        }

        valueBinaryArray.add(encryptedBit);

        return encryptedBit;
    }

    public ArrayList<BigInteger> getValueBinaryArray() {
        return valueBinaryArray;
    }

    public ArrayList<BigDecimal[]> getvalueBinaryArrayComplHomo() {
        return valueBinaryArrayComplHomo;
    }

    private BigInteger sumRandomSubset(BigInteger[] publicKey) {
        Random random = new Random();
        BigInteger sum = BigInteger.ZERO;

        for (BigInteger value : publicKey) {
            int r = random.nextInt(2);
            if (r == 1) {
                sum = sum.add(value);
                if (sum.compareTo(BigInteger.ZERO) < 0) {
                    Utils.warning("Dépassement de variable (Sum x[i])");
                }
            }
        }
        return sum;
    }

    public void display(String name) {
        System.out.println("====== " + name + " ENCRYPTION ======");
        System.out.print("> BINARY ARRAY : ");
        Utils.displayArray(valueBinaryArray);
        //Utils.displayArrayComplHomo(valueBinaryArrayComplHomo);
        System.out.println("========================");
    }

}

