import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculation {
    public static void main(String[] args) throws IOException {
        System.out.println("Для работы с калькулятором введите строку в следующем формате:");
        System.out.println("Пример: 2*4. Доступны операторы: / , *, -, +");
        System.out.println("Числа не должны быть меньше единицы и больше десяти");
        System.out.println("Для работы с калькулятором доступны римские и арабские цифры");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String calculationInput = bufferedReader.readLine();


        if (isRomanNumeral((String.valueOf(calculationInput.charAt(0))))) {
            if (romanToInt(String.valueOf(calculationInput.charAt(0))) < 1 && romanToInt(String.valueOf(calculationInput.charAt(1))) > 10) {
                throw new IOException("Число больше 10 или 1, введите корректно число (от 1 до 10)");
            } else {
                if (romanToInt(calculationInput.split("[+-/*]")[0]) < 0 || romanToInt(calculationInput.split("[+-/*]")[0]) > 10
                        || romanToInt(calculationInput.split("[+-/*]")[0]) > 10 || romanToInt(calculationInput.split("[+-/*]")[0]) < 0)
                    throw new IOException("Число больше 10 или 1, введите корректно число (от 1 до 10)");
                else System.out.println(calculationRoman(calculationInput));
            }

        } else {
            try {
                if (Integer.parseInt(calculationInput.split("[+-/*]")[0]) > 10 || Integer.parseInt(calculationInput.split("[+-/*]")[0]) < 0) {
                    throw new IOException("Введено некорректно значение");
                } else System.out.println(calculationArabic(calculationInput));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Введено некорректно значение");
            }
        }
    }

    public static int calculationArabic(String input) {

        int result = 0;
        char operator = input.charAt(1);


        switch (operator) {
            case '+' -> result = Integer.parseInt(String.valueOf(input.charAt(0))) + Integer.parseInt(String.valueOf(input.charAt(2)));
            case '-' -> result = Integer.parseInt(String.valueOf(input.charAt(0))) - Integer.parseInt(String.valueOf(input.charAt(2)));
            case '*' -> result = Integer.parseInt(String.valueOf(input.charAt(0))) * Integer.parseInt(String.valueOf(input.charAt(2)));
            case '/' -> result = Integer.parseInt(String.valueOf(input.charAt(0))) / Integer.parseInt(String.valueOf(input.charAt(2)));
            default -> System.out.println("Введён некорректно оператор действия");
        }

        return result;
    }

    public static String calculationRoman(String input) throws NullPointerException {

        int result = 0;
        char operator = findOperationSymbol(input);


        switch (operator) {
            case '+' -> result = romanToInt(input.split("[+-/*]")[0]) + romanToInt(input.split("[+-/*]")[1]);
            case '-' -> result = romanToInt(input.split("[+-/*]")[0]) - romanToInt(input.split("[+-/*]")[1]);
            case '*' -> result = romanToInt(input.split("[+-/*]")[0]) * romanToInt(input.split("[+-/*]")[1]);
            case '/' -> result = romanToInt(input.split("[+-/*]")[0]) / romanToInt(input.split("[+-/*]")[1]);
            default -> System.out.println("Введён некорректно оператор действия");
        }

        return arabicToRoman(result);
    }

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) return -1;

        int res = 0;
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < strs.length; i++) {
            while (s.startsWith(strs[i])) {
                res += values[i];
                s = s.substring(strs[i].length());
            }
        }

        return res;
    }

    public static String arabicToRoman(int number) {
        if (number < 1 || number > 3999)
            return "Некорректное римское число (отрицательное)";

        StringBuilder sb = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                sb.append(strs[i]);
            }
        }

        return sb.toString();
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isRomanNumeral(String numeral) {
        String[] validSymbols = {"I", "V", "X", "L", "C", "D", "M"};
        int[] values = {1, 5, 10, 50, 100, 500, 1000};
        int previousValue = 0;

        for (int i = numeral.length() - 1; i >= 0; i--) {
            String symbol = numeral.substring(i, i + 1);
            int value = 0;
            boolean symbolFound = false;
            for (int j = 0; j < validSymbols.length; j++) {
                if (symbol.equals(validSymbols[j])) {
                    value = values[j];
                    symbolFound = true;
                    break;
                }
            }

            if (!symbolFound) {
                return false;
            }

            if (value < previousValue) {
                return false;
            }

            previousValue = value;
        }

        return true;
    }

    public static Character findOperationSymbol(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                return currentChar;
            }
        }

        return null;
    }
}
