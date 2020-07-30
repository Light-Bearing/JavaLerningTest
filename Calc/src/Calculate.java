import java.util.Objects;

public class Calculate {
    public static final String OPERATIONS = "[+-/*]";
    Integer num1, num2;
    Character operation;

    public Calculate(Integer num1, Integer num2, Character operation) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculate calculate = (Calculate) o;
        return num1.equals(calculate.num1) &&
                num2.equals(calculate.num2) &&
                operation.equals(calculate.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num1, num2, operation);
    }

    @Override
    public String toString() {
        return num1.toString() + operation + num2.toString();
    }

    public int calc() {
        switch (operation) {
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return num1 + num2;
        }
    }

    public void checkNumbers() {
        checkNumber(num1);
        checkNumber(num2);
    }

    private void checkNumber(Integer number) {
        if ((number < 1) || (number > 10)) {
            throw new IllegalArgumentException(number + " не в диапазоне [1,10]");
        }
    }
}
