package com;

import java.util.List;

import java.math.BigInteger;
public class Decrypt {

    private Integer plain;
    private Integer privateKey;
    //private BigInteger cipher;

    //private List<Integer> encryptedMessage = Encrypt.getencryptedMessage();
    
    public Decrypt(Integer privateKey, List<Integer> encryptedMessage, Integer Nbrfirstmod) {
        for(Integer cipher : encryptedMessage) {
            System.out.println ("le cipher est: " + cipher);
            this.plain = ((cipher % Nbrfirstmod ) % 2);
            System.out.println("le message est :" + plain);
        }

        /*
        for (Integer chiffre : encryptedMessage) {
            System.out.println(chiffre);
            builder.append(chiffre);
        }*/

        
    }

    public Integer getPlain() {
        return plain;
    }

}
