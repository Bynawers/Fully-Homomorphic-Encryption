package com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// mvn exec:java
public final class App {
    
    public static void main(final String[] args) {

        Utils.displayParameters();
        
        Keygen keys = new Keygen();

        keys.display();
        Utils.saveFileTab(keys.publicKey, "./save/pk.txt");
        
        if (Parameters.MULTIPLE == false) {
            Encrypt encrypt = new Encrypt(keys.publicKey, BigInteger.valueOf(Parameters.m1), keys.privateKey, keys.noise);
            Encrypt encrypt2 = new Encrypt(keys.publicKey, BigInteger.valueOf(Parameters.m2), keys.privateKey, keys.noise);

            Utils.saveFileArray(encrypt.value, "./save/c1.txt");
            Utils.saveFileArray(encrypt2.value, "./save/c2.txt");

            BigInteger[][] noise = new BigInteger[2][];
            noise[0] = encrypt.noise;
            noise[1] = encrypt2.noise;
        
            Evaluate eval = new Evaluate(encrypt.value, encrypt2.value, keys.privateKey, noise);
            Utils.displayNoise(eval.noise);

            Decrypt decryptOperation = new Decrypt(keys.privateKey, eval.value);
            decryptOperation.display("m3");
            return;
        }

        int success = 0;
        int fail = 0;

        long m1 = 2;
        long m2 = 16;

        List<BigInteger> allValues = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Encrypt encrypt = new Encrypt(keys.publicKey, BigInteger.valueOf(m1), keys.privateKey, keys.noise);
            Encrypt encrypt2 = new Encrypt(keys.publicKey, BigInteger.valueOf(m2), keys.privateKey, keys.noise);
       
            BigInteger[][] noise = new BigInteger[2][];
            noise[0] = encrypt.noise;
            noise[1] = encrypt2.noise;

            Evaluate eval = new Evaluate(encrypt.value, encrypt2.value, keys.privateKey, noise);
            Decrypt decrypt = new Decrypt(keys.privateKey, eval.value);
            BigInteger decryptedValue = decrypt.value;
            
            if (!allValues.contains(decryptedValue)) {
                allValues.add(decryptedValue);
            }
            if (decrypt.value.compareTo(BigInteger.valueOf(m1 + m2)) == 0) {
                success++;
            } else {
                fail++;
            }
        }

        if (Parameters.DEBUG) {
            System.out.println("========== All Values (seulement une valeur sinon problème) ============");
            for (BigInteger value : allValues) {
                System.out.println(value + " : ");
                System.out.println(Utils.decimalToBinary(value.intValue()));
            }
        }
 
        System.out.println("================ RESULT MULTIPLE ================");
        System.out.println("> success : " + success);
        System.out.println("> fail : " + fail);
    }
}
