package com;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Decrypt {

    private ArrayList<BigInteger> valueBinaryArray;
    private ArrayList<BigInteger> valueBinaryArrayComplHomo;
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
    }

    public Decrypt(int[] privateKey, ArrayList<BigDecimal> encryptedMessageBinaryComplHomo, ArrayList<BigDecimal[]> ciphertextZ) {

        ArrayList<BigInteger> messageBinaryBuilder = new ArrayList<>();
        BigDecimal messageBit;
        BigDecimal sum = BigDecimal.ZERO;

        int index = 0; // Indice pour suivre les éléments traités

        while (index < ciphertextZ.size() && index <  encryptedMessageBinaryComplHomo.size()) {
            BigDecimal[] vectz = ciphertextZ.get(index);
            BigDecimal cipherBit = encryptedMessageBinaryComplHomo.get(index);
            //System.out.println(cipherBit);

            sum = BigDecimal.ZERO; // Réinitialiser la somme pour chaque itération de vectz

    
            // Calculer la somme des produits de chaque élément de vectz par le correspondant de privateKey
            for (int i = 0; i < vectz.length; i++) {
                sum = sum.add(BigDecimal.valueOf(privateKey[i]).multiply(vectz[i]));
            }

            System.out.println(sum);

            BigDecimal floorSum = sum.setScale(0, BigDecimal.ROUND_FLOOR);

            // Soustraire floorSum à cipherBit et ajouter le résultat dans messageBinaryBuilder
            messageBit = cipherBit.subtract(floorSum);
            messageBit = messageBit.remainder(BigDecimal.valueOf(2));
            messageBinaryBuilder.add(messageBit.toBigInteger());

            index++; // Passer au prochain élément
        }

        valueBinaryArrayComplHomo = messageBinaryBuilder;
        value = Utils.binaryArrayToDecimal(messageBinaryBuilder);

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
        /*for(BigInteger element : valueBinaryArray) {
            System.out.print(element);
        }*/
        for(BigInteger element : valueBinaryArrayComplHomo) {
            System.out.print(element);
        }
        System.out.println();
        System.out.println("> DECIMAL : " + value);
        System.out.println("========================");
    }

}
