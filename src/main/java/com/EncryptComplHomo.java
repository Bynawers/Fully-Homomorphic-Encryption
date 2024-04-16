package com;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class EncryptComplHomo {

    BigDecimal[] vectz = new BigDecimal[Parameters.Theta];

    public EncryptComplHomo(BigDecimal[] publickey, BigDecimal ciphertext ) {

        Integer precision = (int) Math.ceil(Math.log(Parameters.theta)) + 3;
            
        for (int i = 0; i < publickey.length; i++) {
            this.vectz[i] = (publickey[i].multiply(ciphertext)).remainder(BigDecimal.valueOf(2));
            this.vectz[i] = this.vectz[i].setScale(precision - 1 , BigDecimal.ROUND_DOWN);
            //System.out.println(vectz[i]);
        }
    }

    public BigDecimal[] getCiphertext() {
        return this.vectz;
    }
}