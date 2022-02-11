public class ANumber {

    private long number;
    private boolean buzz;
    private boolean duck;
    private boolean palindromic;
    private boolean gapful;
    private boolean even;

    public ANumber(long number) {
        this.number = number;
        this.buzz = ANumber.isBuzzNumber(this.number);
        this.duck = ANumber.isDuckNumber(this.number);
        this.palindromic = ANumber.isPalindrome(this.number);
        this.gapful = ANumber.isGapful(this.number);
        this.even = this.number % 2 == 0;
    }
    public static boolean isBuzzNumber(long number) {

        // Buzz numbers are numbers that are either
        // divisible by 7 or end with 7.

        return (number % 10) == 7 || (number % 7) == 0;

    }

    public static boolean isDuckNumber(long number) {

        // A Duck number is a positive number that contains zeroes.
        // For example, 3210, 8050896, 70709 are Duck numbers.
        // Note that a number with a leading 0 is not a Duck number.

        while (number > 0) {
            if(number % 10 == 0) {
                return true;
            }
            number /= 10;
        }

        return false;
    }

    public static boolean isPalindrome(long number) {

        // A Palindromic number is symmetrical;
        // in other words, it stays the same regardless
        // of whether we read it from left or right.

        long n = number;
        long reverse = 0;

        while (n > 0) {
            long lastDigit = n % 10;
            reverse = reverse * 10 + lastDigit;
            n /= 10;
        }

        return number == reverse;
    }

    public static boolean isGapful(long number) {

        // Gapful number is a number that contains at least 3 digits
        // and is divisible by the concatenation of its first and last digit
        // without a remainder.

        // Checks if it has at least 3 digits.
        if (number < 100) {
            return false;
        }

        long firstDigit = 0;
        long lastDigit = number % 10;
        long aux = number;
        while(aux > 0) {
            firstDigit = aux % 10;
            aux /= 10;
        }

        long div = firstDigit * 10 + lastDigit;

        return number % div == 0;
    }

    public String properties() {
        return String.format("\nProperties of %d\n" +
                        "\tbuzz: %s\n" +
                        "\tduck: %s\n" +
                        "\tpalindromic: %s\n" +
                        "\tgapful: %s\n" +
                        "\teven: %s\n" +
                        "\todd: %s\n",
                number,
                ANumber.isBuzzNumber(number),
                ANumber.isDuckNumber(number),
                ANumber.isPalindrome(number),
                ANumber.isGapful(number),
                even, !even);
    }

    public String toString() {
        String s = this.number + " is ";
        boolean firstProperty = true;

        if (buzz) {
            s += "buzz";
            firstProperty = false;
        }

        if (duck) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "duck";
            firstProperty = false;
        }

        if (palindromic) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "palindromic";
            firstProperty = false;
        }

        if (gapful) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "gapful";
            firstProperty = false;
        }

        if (even) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "even";
            firstProperty = false;
        } else {
            if (!firstProperty) {
                s += ", ";
            }
            s += "odd";
            firstProperty = false;
        }

        return s;
    }
}
