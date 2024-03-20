package com;

import java.math.BigInteger;
import java.util.Random;

// mvn exec:java
public final class App {
    
    public static void main(final String[] args) {

        Utils.displayParameters();
        
        Keygen keys = new Keygen();
        keys.display();
        Utils.saveFileTab(keys.getPublicKey(), "./save/pk.txt");

        Encrypt encrypt = new Encrypt(keys.getPublicKey(), BigInteger.valueOf(1), keys.getPrivateKey());
        Encrypt encrypt2 = new Encrypt(keys.getPublicKey(), BigInteger.valueOf(0), keys.getPrivateKey());

        encrypt.display("c1");
        encrypt2.display("c2");

        Utils.saveFileArray(encrypt.getValueBinaryArray(), "./save/c1.txt");
        Utils.saveFileArray(encrypt2.getValueBinaryArray(), "./save/c2.txt");

        Decrypt decrypt = new Decrypt(keys.getPrivateKey(), encrypt.getValueBinaryArray());
        Decrypt decrypt2 = new Decrypt(keys.getPrivateKey(), encrypt2.getValueBinaryArray());

        decrypt.display("m1");
        decrypt2.display("m2");

        
        //Evaluate eval = new Evaluate(encrypt.getValueBinaryArray(), encrypt2.getValueBinaryArray());
        //eval.display();

        //Decrypt decrypt3 = new Decrypt(keys.getPrivateKey(), eval.getValueBinaryArray());
        //decrypt3.display("m3");
        //System.out.println("Decrypted BinaryArrayOperation:\n" + decrypt3.getValue() + "\n");
    }
}
