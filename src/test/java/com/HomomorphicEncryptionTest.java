package com;

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class HomomorphicEncryptionTest 
{

    
    public void testKeys()
    {
        Keygen keys = new Keygen();
        assert( (keys.getPrivateKey().mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1))) );
        assert( (keys.getPublicKey()[0].mod(keys.getPrivateKey())).mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0)) );
    }

    public void testEncryption()
    {
        Keygen keys = new Keygen();

        Encrypt encrypt = new Encrypt(keys.getPublicKey(), BigInteger.valueOf(Parameters.m1), keys.getPrivateKey());
        Encrypt encrypt2 = new Encrypt(keys.getPublicKey(), BigInteger.valueOf(Parameters.m2), keys.getPrivateKey());

        Decrypt decrypt = new Decrypt(keys.getPrivateKey(), encrypt.getValueBinaryArray());
        Decrypt decrypt2 = new Decrypt(keys.getPrivateKey(), encrypt2.getValueBinaryArray());

        //System.out.println(decrypt.getValue() + " == " + BigInteger.valueOf(Parameters.m1));
        //System.out.println(decrypt2.getValue() + " == " + BigInteger.valueOf(Parameters.m2));

        assert( decrypt.getValue().equals(BigInteger.valueOf(Parameters.m1)) );
        assert( decrypt2.getValue().equals(BigInteger.valueOf(Parameters.m2)) );

    }
    
}