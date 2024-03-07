package com;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class KeygenTest 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public KeygenTest(  )
    {
        super( );
    }

    public void testCondition()
    {
        Keygen keys = new Keygen();
        assert( (keys.getPrivateKey() % 2) == 1 );
        assert( ((keys.getPublicKey()[0] % keys.getPrivateKey()) % 2) == 0 );
    }
}
