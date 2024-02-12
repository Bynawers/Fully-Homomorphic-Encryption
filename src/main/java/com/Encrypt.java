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

    private List<Integer> encryptedMessage;

    private Integer nbrFirstmod;
    
    public Encrypt(Integer[] publicKey, Integer message, Integer privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.encryptedMessage = new ArrayList<>();

        String binaryMessage = Integer.toBinaryString(message);

        System.out.println("Le message est :" + binaryMessage);
        int j = binaryMessage.length()-1;
        for (int i = 0; i < binaryMessage.length(); i++) {
            int bit = Character.getNumericValue(binaryMessage.charAt(i));
            //System.out.println("bit [" + i + "] = " + bit);
            Integer encryptedBit = EncryptBit(bit);
            System.out.println("bit encrypted [" + j + "] = " + encryptedBit);
            encryptedMessage.add(encryptedBit);
            j--;
        }

        c = listToInteger(encryptedMessage);
        
        System.out.println("Le chiffré est : " + getC());
    }

    public Integer EncryptBit(Integer bit) {

        Random random = new Random();
            
        List<Integer> randomSubstet = randomSubset();

        Integer lowerBound = -(int) Math.pow(2, Parameters.NOISE_LENGTH);
        Integer upperBound = (int) Math.pow(2, Parameters.NOISE_LENGTH);

        Integer r = random.nextInt(upperBound - lowerBound) + lowerBound;

        this.nbrFirstmod = 2*sumRandomSubsetPublicKey(randomSubstet);     //  Attention il y a un problème ici
        System.out.println("Nbrfirstmod = " + this.nbrFirstmod);
        Integer tmp = bit + 2*r + this.nbrFirstmod;
        if (tmp < 0) {
            tmp += publicKey[0];
        }
        return tmp;
    }

    public BigInteger getC() {
        return c;
    }

    public List<Integer> getencryptedMessage(){
        return this.encryptedMessage;
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

        for (int i = 1; i < Parameters.PUBLIC_KEY_INTEGER_NUMBER + 1; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        Random random = new Random();
        int subsetSize = random.nextInt(Parameters.PUBLIC_KEY_INTEGER_NUMBER) + 1 ;
        List<Integer> subset = numbers.subList(1, subsetSize);

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

    public Integer getNbrFirstmod () {
        return this.nbrFirstmod;
    }
}
