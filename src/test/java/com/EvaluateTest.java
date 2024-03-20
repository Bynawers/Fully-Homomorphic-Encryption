package com;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EvaluateTest 
{

    public void testAddition()
    {
        Keygen keys = new Keygen();

        Encrypt encrypt = new Encrypt(keys.getPublicKey(), 1, keys.getPrivateKey());
        Encrypt encrypt2 = new Encrypt(keys.getPublicKey(), 0, keys.getPrivateKey());
        Evaluate eval = new Evaluate(encrypt.getValueBinaryArray(), encrypt2.getValueBinaryArray());
        Decrypt decrypt = new Decrypt(keys.getPrivateKey(), eval.getValueBinaryArray());

        assert( decrypt.getValue() == 2);
    }
    
}