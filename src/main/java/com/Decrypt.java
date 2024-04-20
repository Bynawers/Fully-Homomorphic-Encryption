package com;

import java.math.BigInteger;
import java.util.ArrayList;

public class Decrypt {

    public BigInteger[] binaryValue;
    public BigInteger value;
    public String valueBinary;

    public Decrypt(BigInteger privateKey, BigInteger[] encrypted) {
        ArrayList<BigInteger> messageBinaryBuilder = new ArrayList<>();
        BigInteger messageBit;

        for (BigInteger cipherBit : encrypted) {
            messageBit = cipherBit.mod(privateKey).mod(BigInteger.valueOf(2));
            messageBinaryBuilder.add(messageBit);
        }
        this.binaryValue = messageBinaryBuilder.toArray(new BigInteger[0]);;
        this.value = Utils.binaryArrayToDecimal(messageBinaryBuilder);
    }

    public void display(String name) {
        System.out.println("================== "+ name +" DECRYPTION ==================");
        System.out.print("> BINARY ARRAY : ");
        for(BigInteger element : binaryValue) {
            System.out.print(element);
        }
        System.out.println();
        System.out.println("> DECIMAL : " + value);
    }

}
