package com;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

// mvn exec:java
public final class App {
    
    public static void main(final String[] args) {

        Utils.displayParameters();
        
        Keygen keys = new Keygen();
        keys.display();
        Utils.saveFileTab(keys.getPublicKey(), "./save/pk.txt");

        KeygenComplHomo keygenComplHomo1 = new KeygenComplHomo(keys.getPrivateKey());

        Encrypt encrypt = new Encrypt(keys.getPublicKey(), BigInteger.valueOf(1), keys.getPrivateKey(),keygenComplHomo1.getPublickey());
        //Encrypt encrypt2 = new Encrypt(keys.getPublicKey(), BigInteger.valueOf(0), keys.getPrivateKey());

        encrypt.display("c1");
        //encrypt2.display("c2");

        

        //keygenComplHomo1.displayPrivateKey();

        //keygenComplHomo1.publicKeyGen();

        //String ciphertext = Utils.binaryArrayToString(encrypt.getValueBinaryArray());

        //BigInteger ciphertextBigInteger = new BigInteger(ciphertext);

        //BigDecimal ciphertextBigDecimal = new BigDecimal(ciphertextBigInteger);

        //EncryptComplHomo encryptcompl = new EncryptComplHomo(keygenComplHomo1.getPublickey(),ciphertextBigDecimal);

        //DecryptComplHomo dicrypt = new DecryptComplHomo(keygenComplHomo1.getPrivateKey(),encryptcompl.getCiphertext(),ciphertextBigDecimal);

        //System.out.println( "le dichiffré = " + dicrypt.getvalue());

        //Utils.saveFileArray(encrypt.getValueBinaryArray(), "./save/c1.txt");
        //Utils.saveFileArray(encrypt2.getValueBinaryArray(), "./save/c2.txt");

        //KeygenComplHomo keysi = new KeygenComplHomo();
        //keysi.privateKeyGen();
        //keys.displayPrivateKey();
        //keysi.publicKeyGen();
        
        ArrayList<BigDecimal> encryptedMessageBinaryComplHomo = new ArrayList<>();
        for (BigInteger bi : encrypt.getValueBinaryArray()) {
            encryptedMessageBinaryComplHomo.add(new BigDecimal(bi));
        }

        Decrypt decrypt = new Decrypt(keygenComplHomo1.getPrivateKey(), encryptedMessageBinaryComplHomo ,encrypt.getvalueBinaryArrayComplHomo());

        //Decrypt decrypt = new Decrypt(keys.getPrivateKey(), encrypt.getValueBinaryArray(),encrypt.getvalueBinaryArrayComplHomo());
        //Decrypt decrypt2 = new Decrypt(keys.getPrivateKey(), encrypt2.getValueBinaryArray());

        decrypt.display("m1");
        //decrypt2.display("m2");

        
        //Evaluate eval = new Evaluate(encrypt.getValueBinaryArray(), encrypt2.getValueBinaryArray());
        //eval.display();

        //Decrypt decrypt3 = new Decrypt(keys.getPrivateKey(), eval.getValueBinaryArray());
        //decrypt3.display("m3");
        //System.out.println("Decrypted BinaryArrayOperation:\n" + decrypt3.getValue() + "\n");

    }
}
