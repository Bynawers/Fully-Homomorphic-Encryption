package com;

public class Parameters {
    static final Integer VA_SECURITY = 2;

    static final Integer PUBLIC_KEY_INTEGER_LENGTH = (int) Math.pow(VA_SECURITY, 5); // γ 10
    static final Integer PRIVATE_KEY_LENGTH = (int) Math.pow(VA_SECURITY, 4); // η
    static final Integer NOISE_LENGTH = (int) Math.pow(VA_SECURITY, 1); // ρ
    static final Integer NOISE_LENGTH_PRIME = 2*(int) Math.pow(VA_SECURITY, 2); // ρ'
    static final Integer PUBLIC_KEY_INTEGER_NUMBER = PUBLIC_KEY_INTEGER_LENGTH + VA_SECURITY; // τ

    static final Integer KEPPA = (int) Math.floor((PUBLIC_KEY_INTEGER_LENGTH * PRIVATE_KEY_LENGTH )/ NOISE_LENGTH_PRIME); // κ = γη/ρ′  ou bien κ = γ + 2
    static final Integer theta = VA_SECURITY;
    static final Integer Theta = KEPPA * VA_SECURITY;   // Θ=ω(κlogλ)

}
