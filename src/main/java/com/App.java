package com;

import java.util.Random;

// mvn exec:java
public final class App {
    
    public static void main(final String[] args) {
        
        Keygen keys = new Keygen();
        keys.display();
        /*

        Integer sum = 0;
        for (int i = 0; i < keys.getPublicKey().length; i++) {
            sum = sum + keys.getPublicKey()[i];
        }
        int randomNumber = random.nextInt(keys.getPrivateKey()/2);
        System.out.println("Random =>>> " + 2*randomNumber + " p =>>>>> " + keys.getPrivateKey());
        int c = ((0 + 2*randomNumber + 2*sum) % keys.getPublicKey()[0]);
        System.out.println("Size to modulate : " + c);
        int m = (c % keys.getPrivateKey()) % 2;
        System.out.println("Message : " + m);
        */
        
        Encrypt encrypt = new Encrypt(keys.getPublicKey(), 15, keys.getPrivateKey());
        Encrypt encrypt2 = new Encrypt(keys.getPublicKey(), 1, keys.getPrivateKey());

        encrypt.display("c1");
        encrypt2.display("c2");

        Decrypt decrypt = new Decrypt(keys.getPrivateKey(), encrypt.getValueBinaryArray());
        Decrypt decrypt2 = new Decrypt(keys.getPrivateKey(), encrypt2.getValueBinaryArray());

        decrypt.display("m1");
        decrypt2.display("m2");

        /*
        Evaluate eval = new Evaluate(encrypt.getValueBinaryArray(), encrypt2.getValueBinaryArray());
        eval.display();

        Decrypt decrypt3 = new Decrypt(keys.getPrivateKey(), eval.getValueBinaryArray());
        decrypt3.display("m3");
        System.out.println("Decrypted BinaryArrayOperation:\n" + decrypt3.getValue() + "\n");*/
    }
}
