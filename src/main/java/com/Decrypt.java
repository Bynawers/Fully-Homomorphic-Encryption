package com;

import java.util.ArrayList;

public class Decrypt {

    private Integer message;
    private Integer privateKey;
    private Integer cipher;
    
    public Decrypt(Integer privateKey, ArrayList<Integer> encryptedMessageBinary) {
        ArrayList<Integer> messageBinaryBuilder = new ArrayList<>();
        Integer messageBit;

        System.out.println("Decrypt message binary");
        for (Integer cipherBit: encryptedMessageBinary) {
            messageBit = (cipherBit % privateKey) % 2;
            System.out.print(messageBit);
            messageBinaryBuilder.add(messageBit);
        }
        this.message = (cipher%privateKey) % 2;
        System.out.println(message);
    }

    public Integer getMessage() {
        return message;
    }

}
