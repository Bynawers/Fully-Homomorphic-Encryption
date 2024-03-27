package com;

<<<<<<< HEAD
import java.math.BigInteger;
import java.util.ArrayList;

public class Decrypt {

    private ArrayList<BigInteger> valueBinaryArray;
    private String valueBinary;
    private BigInteger value;

    public Decrypt(BigInteger privateKey, ArrayList<BigInteger> encryptedMessageBinary) {
        ArrayList<BigInteger> messageBinaryBuilder = new ArrayList<>();
        BigInteger messageBit;

        for (BigInteger cipherBit : encryptedMessageBinary) {
            messageBit = cipherBit.mod(privateKey).mod(BigInteger.valueOf(2));
            messageBinaryBuilder.add(messageBit);
        }
        valueBinaryArray = messageBinaryBuilder;
        value = Utils.binaryArrayToDecimal(messageBinaryBuilder);
=======
import java.util.List;

import java.math.BigInteger;
public class Decrypt {

    private Integer plain;
    private Integer privateKey;
    //private BigInteger cipher;

    //private List<Integer> encryptedMessage = Encrypt.getencryptedMessage();
    
    public Decrypt(Integer privateKey, List<Integer> encryptedMessage, Integer Nbrfirstmod) {
        for(Integer cipher : encryptedMessage) {
            System.out.println ("le cipher est: " + cipher);
            this.plain = ((cipher % Nbrfirstmod ) % 2);
            System.out.println("le message est :" + plain);
        }

        /*
        for (Integer chiffre : encryptedMessage) {
            System.out.println(chiffre);
            builder.append(chiffre);
        }*/

        
>>>>>>> fa7760b89cc833bef5fdb83e9e29dce5128e1db1
    }

    public BigInteger getValue() {
        return value;
    }

    public String getValueBinary() {
        return valueBinary;
    }

    public ArrayList<BigInteger> getValueBinaryArray() {
        return valueBinaryArray;
    }

    public void display(String name) {
        System.out.println("====== "+ name +" DECRYPTION ======");
        System.out.print("> BINARY ARRAY : ");
        for(BigInteger element : valueBinaryArray) {
            System.out.print(element);
        }
        System.out.println();
        System.out.println("> DECIMAL : " + value);
        System.out.println("========================");
    }

}
