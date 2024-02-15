package com;

public final class App {
    
    public static void main(final String[] args) {
        Keygen keys = new Keygen();
        Encrypt encrypt = new Encrypt(keys.getPublicKey(), 24, keys.getPrivateKey());
        Decrypt decrypt = new Decrypt(keys.getPrivateKey(), encrypt.encryptedMessageBinary());
        Encrypt encrypt2 = new Encrypt(keys.getPublicKey(), 2, keys.getPrivateKey());
        Decrypt decrypt2 = new Decrypt(keys.getPrivateKey(), encrypt2.encryptedMessageBinary());
        Evaluate eval = new Evaluate(encrypt.encryptedMessageBinary(), encrypt2.encryptedMessageBinary());
        eval.display();

        Decrypt decrypt3 = new Decrypt(keys.getPrivateKey(), eval.addition);
    }

}
