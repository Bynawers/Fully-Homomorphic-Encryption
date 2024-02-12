package com;

public final class App {
    
    public static void main(final String[] args) {
        Keygen keys = new Keygen();
        Encrypt encrypt = new Encrypt(keys.getPublicKey(), 24, keys.getPrivateKey());
        Decrypt decrypt = new Decrypt(keys.getPrivateKey(), encrypt.getencryptedMessage(),encrypt.getNbrFirstmod());

        //System.out.print(keys);

    }

}
