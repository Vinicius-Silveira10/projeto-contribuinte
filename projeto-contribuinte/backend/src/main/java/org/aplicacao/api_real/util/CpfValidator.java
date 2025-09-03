package org.aplicacao.api_real.util;

public class CpfValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        String cleanedCpf = cpf.replaceAll("[^0-9]", "");
        if (cleanedCpf.length() != 11) {
            return false;
        }

        if (cleanedCpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int d1 = calculateDigit(cleanedCpf.substring(0, 9), 10);
            int d2 = calculateDigit(cleanedCpf.substring(0, 9) + d1, 11);

            return cleanedCpf.charAt(9) == Character.forDigit(d1, 10) &&
                    cleanedCpf.charAt(10) == Character.forDigit(d2, 10);
        } catch (Exception e) {
            return false;
        }
    }

    private static int calculateDigit(String str, int weight) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += Character.getNumericValue(str.charAt(i)) * weight--;
        }
        int remainder = 11 - (sum % 11);
        return remainder > 9 ? 0 : remainder;
    }
}