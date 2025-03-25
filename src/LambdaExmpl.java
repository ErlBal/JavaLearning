import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

interface MathOperation {
    int operate(int a, int b);
}

public class LambdaExmpl {
    public static void main(String[] args) {
        // Exmpl 1
        System.out.println("Exmpl 1:");
        MathOperation Addition = (a, b) -> a + b;
        MathOperation Subtraction = (a, b) -> a - b;
        MathOperation Multiplication = (a, b) -> a * b;
        MathOperation Division = (a, b) -> a / b;
        System.out.println("Addition: " + Addition.operate(2, 3) + "\nSubtraction: " +
                Subtraction.operate(2, 3) + "\nMultiplication: " + Multiplication.operate(2, 3) +
                "\nDivision: " + Division.operate(2, 3));

        // Exmpl 2
        System.out.println("\nExmpl 2:");
        Predicate<Integer> odd = num -> num % 2 != 0;
        List<Integer> numbers = Arrays.asList(10, 15, 22, 33, 40, 55);
        List<Integer> oddNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (odd.test(num)) {
                oddNumbers.add(num);
            }
        }
        System.out.println("Odd Numbers: " + oddNumbers);

        // Exmpl 3
        System.out.println("\nExmpl 3:");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.sort((a, b) -> b.compareTo(a));
        System.out.println("Sorted Names: " + names);

        // Exmpl 4
        System.out.println("\nExmpl 4:");
        List<String> strings = Arrays.asList("hello", "java", "lambda");

        Function<String, String> transform = s -> {
            char[] chars = s.toUpperCase().toCharArray();
            int left = 0, right = chars.length - 1;
            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
            return new String(chars);
        };
        List<String> transStrings = new ArrayList<>();
        for (String string : strings) {
            String transString = transform.apply(string);
            transStrings.add(transString);
        }
        System.out.println("Transformed Strings: " + transStrings);

        // Exmpl 5
        System.out.println("\nExmpl 5:");
        List<String> cities = Arrays.asList("New York", "London", "Tokyo", "Berlin");
        Consumer<String> printCity = city -> System.out.println(city);
        System.out.println("ета версия обычная: ");
        cities.forEach(printCity);

        // Exmpl 6
        System.out.println("\nExmpl 6:");
        Consumer<String> printCityRef = System.out::println;
        System.out.println("ето версия метод референс: ");
        cities.forEach(printCityRef);

        // Exmpl 7
        System.out.println("\nExmpl 7:");
        BiFunction<Integer, Integer, Integer> maxFunc = Integer::max;
        BiFunction<Integer, Integer, Integer> minFunc = Integer::min;
        System.out.println("Max: " + maxFunc.apply(25, 40));
        System.out.println("Min: " + minFunc.apply(25, 40));
    }
}

