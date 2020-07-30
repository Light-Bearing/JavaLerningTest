import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("Введите команду для калькулятора вида \"a + b\",\n" +
                "где a и b натуральные числа в римской или арабской записи не превышающие 10 \n" +
                "допустимые символы операции [+, -, *, /]:");
        Scanner scanner = new Scanner(System.in);
        String formula = scanner.nextLine().replaceAll("\\s+", "");

        Calculate calculate;
        try {
            calculate = Converter.parseString(formula);

            calculate.checkNumbers();
            int result = calculate.calc();
            System.out.println(Converter.isRoman ? Converter.arabic2Roman(result) : result);
        } catch (Converter.NotSignOperationInFormula notSignOperationInFormula) {
            notSignOperationInFormula.printStackTrace();
        }

    }

}
