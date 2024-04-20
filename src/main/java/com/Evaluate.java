package com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Evaluate {

    public BigInteger[] value;
    public BigInteger[] noise;
    public BigInteger privateKey;

    public Evaluate(BigInteger[] c1, BigInteger[] c2, BigInteger privateKeyTmp, BigInteger[][] noiseBaseTab) {
        this.privateKey = privateKeyTmp;
        value = additionCrypted(c1, c2, noiseBaseTab);
    }

    public BigInteger[] additionCrypted(BigInteger[] c1, BigInteger[] c2, BigInteger[][] noiseBaseTab) {
        ArrayList<BigInteger> valueBinaryArrayBuilder = new ArrayList<>();
        ArrayList<BigInteger> noiseBuilder = new ArrayList<>();
        BigInteger noiseCarry = BigInteger.ZERO;
        BigInteger noiseSum = BigInteger.ZERO;
        
        BigInteger carry = BigInteger.ZERO;


        Integer c1Size = c1.length;
        Integer c2Size = c2.length;

        Integer biggerSize = c1Size > c2Size ? c1Size : c2Size;
        Integer smallerSize = c1Size < c2Size ? c1Size : c2Size;
        Boolean c1IsBigger = c1Size > c2Size;

        BigInteger[] currentAdder = new BigInteger[2];
        currentAdder[0] = BigInteger.ZERO;
        currentAdder[1] = BigInteger.ZERO;

        BigInteger[] previousAdder = new BigInteger[2];
        previousAdder[0] = BigInteger.ZERO;
        previousAdder[1] = BigInteger.ZERO;

        for (int i = 1; biggerSize - i > -1; i++) { // Parcours le plus grand binaire
            if (i == 1) { // Le premier est forcément un halfAdder
                currentAdder = halfAdder(c1[c1Size - i], c2[c2Size - i]);
                noiseSum = noiseBaseTab[0][c1Size - i].add(noiseBaseTab[1][c2Size - i]);
                noiseCarry = noiseBaseTab[0][c1Size - i].multiply(noiseBaseTab[1][c2Size - i]);
            }
            else if (smallerSize - i >= 0) { // Si le plus petit binaire n'est pas dépassé
                currentAdder = fullAdder(c1[c1Size - i], c2[c2Size - i], previousAdder[1]);
                noiseSum = noiseBaseTab[0][c1Size - i].add(noiseBaseTab[1][c2Size - i]).add(noiseCarry);

                BigInteger ha1_1 = noiseBaseTab[0][c1Size - i].multiply(noiseBaseTab[1][c2Size - i]); // ha1[1] (a * b)
                BigInteger ha2_1 = (noiseBaseTab[0][c1Size - i].add(noiseBaseTab[1][c2Size - i]).multiply(noiseCarry)); // ha2[1] ((a*b)*carryIn)

                noiseCarry = ha1_1.add(ha2_1).add(ha1_1.multiply(ha2_1));
            } else {
                currentAdder = fullAdder(c1IsBigger ? c1[biggerSize - i] : c2[biggerSize - i], BigInteger.ZERO, previousAdder[1]); // ha1[1]
                noiseSum = c1IsBigger ? (noiseBaseTab[0][c1Size - i]).add(noiseCarry) : (noiseBaseTab[1][c2Size - i]).add(noiseCarry);

                BigInteger ha1_1 = c1IsBigger ? noiseBaseTab[0][c1Size - i].multiply(BigInteger.valueOf(0))
                : noiseBaseTab[1][c2Size - i].multiply(BigInteger.valueOf(0));

                BigInteger ha2_1 = c1IsBigger ? (noiseBaseTab[0][c1Size - i].add(BigInteger.valueOf(0)).multiply(noiseCarry))
                : (noiseBaseTab[1][c2Size - i].add(BigInteger.valueOf(0)).multiply(noiseCarry));

                noiseCarry = ha1_1.add(ha2_1).add(ha1_1.multiply(ha2_1));
            }

            carry = currentAdder[0];
            valueBinaryArrayBuilder.add(carry);
            noiseBuilder.add(noiseSum);
            previousAdder = currentAdder;
        }
        valueBinaryArrayBuilder.add(currentAdder[1]);

        Collections.reverse(valueBinaryArrayBuilder);
        Collections.reverse(noiseBuilder);
        BigInteger[] result = valueBinaryArrayBuilder.toArray(new BigInteger[0]);
        this.noise = noiseBuilder.toArray(new BigInteger[0]);
        return result;
    }

    public BigInteger[] halfAdder(BigInteger a, BigInteger b) { // [0] sum [1] carry
        BigInteger[] sumAndCarry = new BigInteger[2];
        sumAndCarry[0] = a.add(b);
        sumAndCarry[1] = a.multiply(b);
        return sumAndCarry;
    }

    public BigInteger[] fullAdder(BigInteger a, BigInteger b, BigInteger carryIn) { // [0] sum [1] carry [2] noise
        BigInteger[] sumAndCarry = new BigInteger[2];

        BigInteger[] ha1 = halfAdder(a, b);
        BigInteger[] ha2 = halfAdder(ha1[0], carryIn); // ha2[1] = (a + b) * carryIn 

        sumAndCarry[0] = ha2[0];

        // La retenue finale est soit la retenue du demi-additionneur précédent ou celle du demi-additionneur actuel
        // a OU b = (a XOR b) XOR (a AND b)
        sumAndCarry[1] = (ha1[1].add(ha2[1])).add((ha1[1].multiply(ha2[1])));
        return sumAndCarry;
    }

    public BigInteger EncryptToPlain(BigInteger value) {
        return (value.mod(privateKey).mod(BigInteger.TWO));
    }
}

class Instruction {
    OperationType operation;
    Integer bit;
    Integer operand;

    public Instruction(OperationType operation, Integer bit, Integer operand) {
        this.operation = operation;
        this.operand = operand;
        this.bit = bit;
        Operation(bit, operand, operation);
    }

    public Integer Operation(Integer bit, Integer value, OperationType operation) {
        switch (operation) {
            case ADD:
                return bit + value;
            case MULT:
                return bit * value;
            default:
                throw new IllegalArgumentException("Type d'opération non supporté: " + this);
        }
    }
}

enum OperationType {
    ADD,
    MULT
}

/*
       
if (Parameters.DEBUG) {
    System.out.println("--- HalfAdder ---");
    System.out.println("> Private key : " + privateKey );
    System.out.println("> Somme détaillé encrypté : " + a + " + " + b);
    System.out.println("> Somme détaillé : " + EncryptToPlain(a) + " + " + EncryptToPlain(b));
    System.out.println("> Somme : " + EncryptToPlain(sumAndCarry[0]));
    System.out.println("> Retenue détaillé : " + a.mod(privateKey) + " x " + b.mod(privateKey) );
    System.out.println("> Retenue détaillé résultat : " + sumAndCarry[1].mod(privateKey) );
    System.out.println("> Retenue : " + EncryptToPlain(sumAndCarry[1]));
}
    System.out.println("--- FullAdder ---");
    System.out.println("> Retenue Entrée : " + EncryptToPlain(carryIn));
    System.out.println("> Somme : " + EncryptToPlain(sumAndCarry[0]));
    System.out.println("> Retenue : " + EncryptToPlain(sumAndCarry[1]));

    System.out.println("Smaller Binary pas dépassé");
    System.out.println("FullAdder INPUT");
    System.out.println("Values Encrypted : " + c1[c1Size - i] + " / " + c2[c2Size - i]);
    System.out.println("Values : " + EncryptToPlain(c1[c1Size - i]) + " / " + EncryptToPlain(c2[c2Size - i]));
    System.out.println("Retenue : " + EncryptToPlain(previousAdder[1]));
}
if (Parameters.DEBUG) {
    System.out.println("Smaller Binary dépassé");
    System.out.println("FullAdder INPUT");
    System.out.println("Values Encrypted : " + (c1IsBigger ? c1[biggerSize - i] : c2[biggerSize - i]) + " / " + BigInteger.ZERO);
    System.out.println("Values : " + (c1IsBigger ? EncryptToPlain(c1[biggerSize - i]) : EncryptToPlain(c2[biggerSize - i])) + " / " + BigInteger.ZERO);
    System.out.println("Retenue : " + EncryptToPlain(previousAdder[1]));
}
 */