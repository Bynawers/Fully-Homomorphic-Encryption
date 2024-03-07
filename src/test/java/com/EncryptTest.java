package com;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class EncryptTest 
{

    public void testEncrypt()
    {
        Keygen keys = new Keygen();
        Integer sum = 0;
        for (int i = 0; i < keys.getPublicKey().length; i++) {
            sum = sum + keys.getPublicKey()[i];
        }
        int c = ((1 + 0 + 2*sum) % keys.getPublicKey()[0]);
        int m = (c % keys.getPrivateKey()) % 2;
        assert( m == 1 );
    }
}