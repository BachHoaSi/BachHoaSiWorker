package com.swd391.bachhoasi_worker.util;

import java.security.SecureRandom;


public class BaseUtils {
    private BaseUtils(){}

    public static String generateProductCode() {
        // Prefix
        String prefix = "BHS";
        // Generate a sequence of 10 random digits
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom randomized = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int randomDigit = randomized.nextInt(10); // Generates a random digit between 0 and 9
            stringBuilder.append(randomDigit);
        }
        return prefix + stringBuilder;
    }
}
