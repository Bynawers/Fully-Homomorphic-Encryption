package com;

public class Parameters {
    static final Integer VA_SECURITY = 10;

    static final Integer PUBLIC_KEY_INTEGER_LENGTH = VA_SECURITY^5;
    static final Integer PRIVATE_KEY_LENGTH = VA_SECURITY^5;
    static final Integer NOISE_LENGTH = VA_SECURITY;
    static final Integer PUBLIC_KEY_INTEGER_NUMBER = PUBLIC_KEY_INTEGER_LENGTH + VA_SECURITY;
}
