package com;

public class Decrypt {

    private Integer plain;
    private Integer privateKey;
    private Integer cipher;
    
    public Decrypt(Integer privateKey, Integer cipher) {
        this.plain = (cipher%privateKey) % 2;
        System.out.println(plain);
    }

    public Integer getPlain() {
        return plain;
    }

}
