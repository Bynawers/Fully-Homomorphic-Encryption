package com;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class EncryptTest 
{

    public void testEncryption()
    {
        Keygen keys = new Keygen();

        Encrypt encrypt = new Encrypt(keys.getPublicKey(), 15, keys.getPrivateKey());
        Encrypt encrypt2 = new Encrypt(keys.getPublicKey(), 1, keys.getPrivateKey());
        Encrypt encrypt3 = new Encrypt(keys.getPublicKey(), 0, keys.getPrivateKey());
        Encrypt encrypt4 = new Encrypt(keys.getPublicKey(), 100000, keys.getPrivateKey());

        Decrypt decrypt = new Decrypt(keys.getPrivateKey(), encrypt.getValueBinaryArray());
        Decrypt decrypt2 = new Decrypt(keys.getPrivateKey(), encrypt2.getValueBinaryArray());
        Decrypt decrypt3 = new Decrypt(keys.getPrivateKey(), encrypt3.getValueBinaryArray());
        Decrypt decrypt4 = new Decrypt(keys.getPrivateKey(), encrypt4.getValueBinaryArray());

        assert( decrypt.getValue() == 15 );
        assert( decrypt2.getValue() == 1 );
        assert( decrypt3.getValue() == 0 );
        assert( decrypt4.getValue() == 100000 );
    }
    
}