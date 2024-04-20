package com;

public class Parameters {
    static final Integer VA_SECURITY = 2;

    static final Integer PUBLIC_KEY_INTEGER_LENGTH = (int) Math.pow(VA_SECURITY, 7); // γ 10
    static final Integer PRIVATE_KEY_LENGTH = (int) Math.pow(VA_SECURITY, 6); // η
    static final Integer NOISE_LENGTH = (int) Math.pow(VA_SECURITY, 1); // ρ
    static final Integer NOISE_LENGTH_PRIME = 2*(int) Math.pow(VA_SECURITY, 1); // ρ'
    static final Integer PUBLIC_KEY_INTEGER_NUMBER = PUBLIC_KEY_INTEGER_LENGTH + VA_SECURITY; // τ

    static final Boolean DEBUG = false;
    static final Boolean RANDOM = true;
    static final Boolean MULTIPLE = false;

    static final Integer m1 = 16;
    static final Integer m2 = 1;

}