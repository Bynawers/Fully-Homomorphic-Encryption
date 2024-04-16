package com;

import java.math.BigDecimal;
import java.math.BigInteger;

class DecryptComplHomo {

    private BigInteger value;
    
    public DecryptComplHomo(int[] privateKey, BigDecimal[] ciphertextZ, BigDecimal ciphertext) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < ciphertextZ.length; i++) {
            if(privateKey[i] == 1) {
                System.out.println("chhh = " + ciphertextZ[i]);
            }
            sum = sum.add(BigDecimal.valueOf(privateKey[i]).multiply(ciphertextZ[i]));
        }

        System.out.println(sum);

        BigDecimal floorSum = sum.setScale(0, BigDecimal.ROUND_FLOOR);
        System.out.println(floorSum);
        BigDecimal decryptedValue = ciphertext.subtract(floorSum);
        System.out.println(decryptedValue);
        this.value = decryptedValue.remainder(BigDecimal.valueOf(2)).toBigInteger();
    }

    public BigInteger getvalue() {
        return this.value;
    }
}