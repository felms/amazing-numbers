import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.");

        long request = -1;
        do {
            System.out.println("\n\nEnter a request:");
            request = scanner.nextLong();

            if (!isNatural(request)) {
                System.out.println("The first parameter should be a natural number or zero.");

            } else if (request > 0) {
                checkProperties(request);
            }

        } while (request != 0);

        scanner.close();
    }

    public static boolean isNatural(long number) {

        return number >= 0;
    }

    public static void checkProperties(long number) {
        boolean even = number % 2 == 0;

        System.out.printf("Properties of %d\n" +
                        "\teven: %s\n" +
                        "\todd: %s\n" +
                        "\tbuzz: %s\n" +
                        "\tduck: %s\n" +
                        "\tpalindromic: %s\n",
                        number, even, !even, isBuzzNumber(number), isDuckNumber(number), isPalindrome(number));
    }

    public static boolean isBuzzNumber(long number) {

        return (number % 10) == 7 || (number % 7) == 0;

    }

    public static boolean isDuckNumber(long number) {

        while (number > 0) {
            if(number % 10 == 0) {
                return true;
            }
            number /= 10;
        }

        return false;
    }

    public static boolean isPalindrome(long number) {
        long n = number;
        long reverse = 0;

        while (n > 0) {
            long lastDigit = n % 10;
            reverse = reverse * 10 + lastDigit;
            n /= 10;
        }

        return number == reverse;
    }
}
