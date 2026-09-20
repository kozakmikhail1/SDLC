package com.example.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {
    
    private static final String DIGITS = "0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String SPECIAL = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    private static final SecureRandom random = new SecureRandom();
    
    public List<String> generatePasswords(int length, boolean useDigits, boolean useLetters, boolean useSpecial, int count) {
        if (length < 4 || length > 50) {
            throw new IllegalArgumentException("Длина пароля должна быть от 4 до 50 символов");
        }
        
        if (count < 1 || count > 100) {
            throw new IllegalArgumentException("Количество паролей должно быть от 1 до 100");
        }
        
        if (!useDigits && !useLetters && !useSpecial) {
            throw new IllegalArgumentException("Выберите хотя бы один набор символов");
        }
        
        StringBuilder chars = new StringBuilder();
        if (useDigits) chars.append(DIGITS);
        if (useLetters) chars.append(LETTERS);
        if (useSpecial) chars.append(SPECIAL);
        
        String characterSet = chars.toString();
        
        List<String> passwords = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            passwords.add(generatePassword(length, characterSet));
        }
        
        return passwords;
    }
    
    private String generatePassword(int length, String characterSet) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }
        return password.toString();
    }
}