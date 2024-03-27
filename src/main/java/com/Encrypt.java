package com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Encrypt {

    private ArrayList<BigInteger> valueBinaryArray = new ArrayList<>();

<<<<<<< HEAD
    public Encrypt(BigInteger[] publicKey, BigInteger message, BigInteger privateKey) {
        ArrayList<BigInteger> encryptedMessageBuilder = new ArrayList<>();

        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            int bit = message.testBit(i) ? 1 : 0;
            System.out.println(bit);
            BigInteger encryptedBit = encryptBit(bit, privateKey, publicKey);
            encryptedMessageBuilder.add(encryptedBit);
        }

        this.valueBinaryArray = encryptedMessageBuilder;
=======
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
>>>>>>> fa7760b89cc833bef5fdb83e9e29dce5128e1db1
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

<<<<<<< HEAD
        if (r.compareTo(privateKey) > 0) {
            Utils.warning("Random bigger than private key (Encryption)");
=======
        this.nbrFirstmod = 2*sumRandomSubsetPublicKey(randomSubstet);     //  Attention il y a un problème ici
        System.out.println("Nbrfirstmod = " + this.nbrFirstmod);
        Integer tmp = bit + 2*r + this.nbrFirstmod;
        if (tmp < 0) {
            tmp += publicKey[0];
>>>>>>> fa7760b89cc833bef5fdb83e9e29dce5128e1db1
        }

        BigInteger encryptedBit = BigInteger.valueOf(bit)
                .add(r.multiply(BigInteger.TWO))
                .add(sumRandomX.multiply(BigInteger.TWO))
                .mod(publicKey[0]);

        System.out.println("( " + bit + " + " + r.multiply(BigInteger.TWO) + " + " + sumRandomX.multiply(BigInteger.TWO) + " ) mod " + publicKey[0]);

        if (encryptedBit.compareTo(BigInteger.ZERO) < 0) {
            encryptedBit = encryptedBit.add(publicKey[0]);
        }

        //valueBinaryArray.add(encryptedBit);

        return encryptedBit;
    }

    public ArrayList<BigInteger> getValueBinaryArray() {
        return valueBinaryArray;
    }

<<<<<<< HEAD
    private BigInteger sumRandomSubset(BigInteger[] publicKey) {
        Random random = new Random();
        BigInteger sum = BigInteger.ZERO;
=======
    public List<Integer> getencryptedMessage(){
        return this.encryptedMessage;
    }

    private Integer sumRandomSubsetPublicKey(List<Integer> randomSubset) {
        Integer sum = 0;
        for (Integer index: randomSubset) {
            sum += publicKey[index];
            //System.out.println("publicKey["+index+"] = " + publicKey[index]);
>>>>>>> fa7760b89cc833bef5fdb83e9e29dce5128e1db1

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

<<<<<<< HEAD
    public void display(String name) {
        System.out.println("====== " + name + " ENCRYPTION ======");
        System.out.print("> BINARY ARRAY : ");
        Utils.displayArray(valueBinaryArray);
        System.out.println("========================");
    }

}
=======
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
>>>>>>> fa7760b89cc833bef5fdb83e9e29dce5128e1db1
