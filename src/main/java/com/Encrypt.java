package com;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Encrypt {

    private Integer[] publicKey;
    private Integer privateKey; 

    private BigInteger c;
    
    public Encrypt(Integer[] publicKey, Integer message, Integer privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        List<Integer> encryptedMessage = new ArrayList<>();

        for (int i = 0; i < Integer.SIZE; i++) {
            int bit = (message >> i) & 1;
            //System.out.println("bit [" + i + "] = " + bit);
            Integer encryptedBit = EncryptBit(bit);
            System.out.println("bit encrypted [" + i + "] = " + encryptedBit);
            encryptedMessage.add(encryptedBit);
        }

        c = listToInteger(encryptedMessage);
        
        System.out.println(getC());
    }

    public Integer EncryptBit(Integer bit) {

        Random random = new Random();
            
        List<Integer> randomSubstet = randomSubset();

        Integer lowerBound = -(int) Math.pow(2, Parameters.NOISE_LENGTH);
        Integer upperBound = (int) Math.pow(2, Parameters.NOISE_LENGTH);

        Integer r = random.nextInt(upperBound - lowerBound) + lowerBound;

        Integer tmp = bit + 2*r + 2*sumRandomSubsetPublicKey(randomSubstet) % publicKey[0];
        if (tmp < 0) {
            tmp += publicKey[0];
        }
        return tmp;
    }

    public BigInteger getC() {
        return c;
    }

    private Integer sumRandomSubsetPublicKey(List<Integer> randomSubset) {
        Integer sum = 0;
        for (Integer index: randomSubset) {
            sum += publicKey[index];
            //System.out.println("publicKey["+index+"] = " + publicKey[index]);

        }
        //System.out.println("SUM = "+sum);
        return sum;
    }

    private List<Integer> randomSubset() {

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < Parameters.PUBLIC_KEY_INTEGER_NUMBER; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        Random random = new Random();
        int subsetSize = random.nextInt(Parameters.PUBLIC_KEY_INTEGER_NUMBER + 1);
        List<Integer> subset = numbers.subList(0, subsetSize);

        /* DEBUG
        System.out.println("----SUBSET---");
        for (Integer elem: subset) {
            System.out.println(elem);
        }*/

        return subset;
    }

    private BigInteger listToInteger(List<Integer> encryptedMessage) {
        StringBuilder builder = new StringBuilder();

        for (Integer chiffre : encryptedMessage) {
            System.out.println(chiffre);
            builder.append(chiffre);
        }

        String listString = builder.toString();

        BigInteger listInt = new BigInteger(listString);

        System.out.println(listInt);

        BigInteger res = listInt.remainder(BigInteger.valueOf(privateKey));

        System.out.println(res);

        return listInt;
    }
}
