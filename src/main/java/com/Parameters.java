package com;

public class Parameters {
    static final Integer VA_SECURITY = 2;

    static final Integer PUBLIC_KEY_INTEGER_LENGTH = (int) Math.pow(VA_SECURITY, 5); // γ 10
    static final Integer PRIVATE_KEY_LENGTH = (int) Math.pow(VA_SECURITY, 4); // η
    static final Integer NOISE_LENGTH = (int) Math.pow(VA_SECURITY, 1); // ρ
    static final Integer NOISE_LENGTH_PRIME = 2*(int) Math.pow(VA_SECURITY, 2); // ρ'
    static final Integer PUBLIC_KEY_INTEGER_NUMBER = PUBLIC_KEY_INTEGER_LENGTH + VA_SECURITY; // τ

}
