package tech.ada.localizada.util;

public class Util {

    public static String formatPhone (String phone) {

        String cleanPhone = phone.replaceAll("\\D", "");

        return cleanPhone.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }

    public static String formatCpf (String cpf) {

        String cleanCpf = cpf.replaceAll("\\D", "");

        return cleanCpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String formatCnpj (String cnpj) {

        String cleanCnpj = cnpj.replaceAll("\\D", "");

        return cleanCnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    public static String validateCnpjAndCpf(String input) {

        if (input == null) {
            return null;
        }

        return input.replaceAll("[^0-9]", "");
    }
}
