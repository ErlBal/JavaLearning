import java.util.Random;

public class Main {

    public static void processArray(int[] array) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int secondMin = Integer.MAX_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : array) {
            sum += num;
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        for (int num : array) {
            if (num > min && num < secondMin) {
                secondMin = num;
            }
            if (num < max && num > secondMax) {
                secondMax = num;
            }
        }

        double average = (double) sum / array.length;

        System.out.println("Array: " + java.util.Arrays.toString(array));
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Second Min: " + (secondMin == Integer.MAX_VALUE ? -1 : secondMin));
        System.out.println("Second Max: " + (secondMax == Integer.MIN_VALUE ? -1 : secondMax));
    }


    public static boolean isSentencePalindrome(String sentence) {
        String cleaned = sentence.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindrome(cleaned);
    }

    public static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }
        processArray(array);


        boolean result = isSentencePalindrome("1h.2e,ll ,o lle2 h1");
        System.out.println(result);
    }
}
