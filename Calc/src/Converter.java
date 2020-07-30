import java.util.List;

public class Converter {
    public static final String ROMAN = "[IVX]+";
    public static boolean isRoman;

    public static String arabic2Roman(int number) {
        boolean isNegative = number < 0;
        number = Math.abs(number);
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return (isNegative ? "-" : "") + sb.toString();
    }

    public static int roman2Arabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " не может быть конвертировано в Римское число");
        }
        return result;
    }

    static Calculate parseString(String value) throws NotSignOperationInFormula {
        if (!value.matches(".*"+Calculate.OPERATIONS+".*")) throw new NotSignOperationInFormula();
        if (value.matches("[\\d]+" + Calculate.OPERATIONS + "[\\d]+")) {
            isRoman = false;
        } else if (
                value.matches(Converter.ROMAN + Calculate.OPERATIONS + Converter.ROMAN)) {
            isRoman = true;
        } else
            throw new NumberFormatException("Не корректно введена формула");
        String[] parts = value.split(Calculate.OPERATIONS);
        Character operation = value.charAt(parts[0].length());
        Integer num1 = isRoman ? Converter.roman2Arabic(parts[0]) : Integer.parseInt(parts[0]);
        Integer num2 = isRoman ? Converter.roman2Arabic(parts[1]) : Integer.parseInt(parts[1]);
        return new Calculate(num1, num2, operation);
    }

    static class NotSignOperationInFormula extends Exception {
    }
}
