import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ANumber {

    private long number;
    private boolean buzz;
    private boolean duck;
    private boolean palindromic;
    private boolean gapful;
    private boolean spy;
    private boolean square;
    private boolean sunny;
    private boolean jumping;
    private boolean happy;
    private boolean sad;
    private boolean even;

    public enum AvailableProperties {
        EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD
    }

    public ANumber(long number) {
        this.number = number;
        this.buzz = ANumber.isBuzzNumber(this.number);
        this.duck = ANumber.isDuckNumber(this.number);
        this.palindromic = ANumber.isPalindrome(this.number);
        this.gapful = ANumber.isGapful(this.number);
        this.spy = ANumber.isSpy(this.number);
        this.square = ANumber.isSquare(this.number);
        this.sunny = ANumber.isSunny(this.number);
        this.jumping = ANumber.isJumping(this.number);
        this.happy = ANumber.isHappy(this.number);
        this.sad = !this.happy;
        this.even = this.number % 2 == 0;
    }

    private static boolean isBuzzNumber(long number) {

        // Buzz numbers are numbers that are either
        // divisible by 7 or end with 7.

        return (number % 10) == 7 || (number % 7) == 0;

    }

    private static boolean isDuckNumber(long number) {

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

    private static boolean isPalindrome(long number) {

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

    private static boolean isGapful(long number) {

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

    private static boolean isSpy(long number) {

        // A number is said to be Spy if the sum of all digits
        // is equal to the product of all digits.

        long sum = 0;
        long product = 1;
        long n = number;
        List<Integer> digits = new ArrayList<>();

        while (n > 0) {
            digits.add((int)n % 10);
            n /= 10;
        }

        for (int d : digits) {
            sum += d;
            product *= d;
        }

        return sum == product;
    }

    private static boolean isSquare(long number) {

        // A square number or a perfect square is an integer
        // that is the square of an integer;
        // in other words, it is the product of an integer with itself.
        // For example, 9 is a square number,
        // since it equals 32 and can be written as 3×3.

        long x = (long) Math.sqrt(number);

        return Math.pow(x, 2) == number;
    }

    private static boolean isSunny(long number) {

        // N is a sunny number if N+1 is a perfect square number.
        return isSquare(number + 1);
    }

    private static boolean isJumping(long number) {

        // A number is a Jumping number if the adjacent digits inside the number differ by 1.
        // The difference between 9 and 0 is not considered as 1. Single-digit numbers are considered Jumping numbers.

        List<Long> digits = new ArrayList<>();

        // Gets digits
        long n = number;
        while (n > 0) {
            Long d = n % 10;
            digits.add(d);
            n /= 10;
        }

        // Single-digit numbers are considered Jumping numbers.
        if (digits.size() == 0) {
            return true;
        }


        long n0 = digits.get(0);
        for (int i = 1; i < digits.size(); i++) {
            long diff = Math.abs(digits.get(i) - n0);

            if(diff != 1L) {
                return false;
            }
            n0 = digits.get(i);
        }

        return true;
    }

    private static boolean isHappy(long number) {
        
        // a happy number is a number that reaches 1 after a sequence during which the number is replaced by the sum of each digit squares. 
        // For example, 13 is a happy number, as 1^2 + 3^2 = 10 which leads to 1^2 + 0^2 = 1. 
        // On the other hand, 4 is not a happy number because the sequence starts with 4^2 = 16, 
        // 1^2 + 6^2 = 37, and finally reaches 2^2 + 0^2 = 4. 
        // A number that is not happy is called Sad (or Unhappy).

        List<Long> seenNumbers = new ArrayList<>();

        while (number > 1 && !seenNumbers.contains(number)) {
            seenNumbers.add(number);
            number = sumOfDigitSquares(number);
        }        
        
        return number == 1;
        
    }

    private static long sumOfDigitSquares(long number) {
        
        long sum = 0;
        
        while (number > 0) {
            long l = number % 10;
            sum += l * l;
            number /= 10;
        }      

        return sum;
    }

    public static boolean mutuallyExclusiveProperties(String property1, String property2){

        if (property1.equalsIgnoreCase("-" + property2)
                || property2.equalsIgnoreCase("-" + property1)) {
            return true;
        }

        if ((property1.equalsIgnoreCase("even")
                && property2.equalsIgnoreCase("odd")) ||
                (property1.equalsIgnoreCase("odd")
                        && property2.equalsIgnoreCase("even"))) {
            return true;
        }

        if ((property1.equalsIgnoreCase("-even")
                && property2.equalsIgnoreCase("-odd")) ||
                (property1.equalsIgnoreCase("-odd")
                        && property2.equalsIgnoreCase("-even"))) {
            return true;
        }

        if ((property1.equalsIgnoreCase("happy")
                && property2.equalsIgnoreCase("sad")) ||
                (property1.equalsIgnoreCase("sad")
                        && property2.equalsIgnoreCase("happy"))) {
            return true;
        }

        if ((property1.equalsIgnoreCase("-happy")
                && property2.equalsIgnoreCase("-sad")) ||
                (property1.equalsIgnoreCase("-sad")
                        && property2.equalsIgnoreCase("-happy"))) {
            return true;
        }

        if ((property1.equalsIgnoreCase("duck")
                && property2.equalsIgnoreCase("spy")) ||
                (property1.equalsIgnoreCase("spy")
                        && property2.equalsIgnoreCase("duck"))) {
            return true;
        }

        if ((property1.equalsIgnoreCase("sunny")
                && property2.equalsIgnoreCase("square")) ||
                (property1.equalsIgnoreCase("square")
                        && property2.equalsIgnoreCase("sunny"))) {
            return true;
        }

        if ((property1.equalsIgnoreCase("sad")
                && property2.equalsIgnoreCase("happy")) ||
                (property1.equalsIgnoreCase("happy")
                        && property2.equalsIgnoreCase("sad"))) {
            return true;
        }

        return false;
    }

    public static boolean isAvailableProperty(String propertyName) {

        propertyName = propertyName.toUpperCase();
        propertyName = propertyName.startsWith("-") ? propertyName.replaceFirst("-", "") : propertyName;
        for (AvailableProperties p : AvailableProperties.values()) {
            if (p.name().equals(propertyName)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasProperty(String property) {

        property = property.startsWith("-") ? property.replaceFirst("-", "") : property;
        AvailableProperties aProperty = AvailableProperties.valueOf(property.toUpperCase());

        switch(aProperty) {
            case BUZZ:
                return this.buzz;
            case DUCK:
                return this.duck;
            case PALINDROMIC:
                return this.palindromic;
            case GAPFUL:
                return this.gapful;
            case SPY:
                return this.spy;
            case SQUARE:
                return this.square;
            case SUNNY:
                return this.sunny;
            case JUMPING:
                return this.jumping;
            case HAPPY:
                return this.happy;
            case SAD:
                return this.sad;
            case EVEN:
                return number % 2 == 0;
            case ODD:
                return number % 2 != 0;
        }

        return false;
    }

    public static String listAvailableProperties() {
        return Arrays.asList(AvailableProperties.values()).toString();
    }

    public String listProperties() {
        return String.format("\nProperties of %d\n" +
                        "\tbuzz: %s\n" +
                        "\tduck: %s\n" +
                        "\tpalindromic: %s\n" +
                        "\tgapful: %s\n" +
                        "\tspy: %s\n" +
                        "\tsquare: %s\n" +
                        "\tsunny: %s\n" +
                        "\tjumping: %s\n" +
                        "\thappy: %s\n" +
                        "\tsad: %s\n" +
                        "\teven: %s\n" +
                        "\todd: %s\n",
                number,
                this.buzz,
                this.duck,
                this.palindromic,
                this.gapful,
                this.spy,
                this.square,
                this.sunny,
                this.jumping,
                this.happy,
                this.sad,
                even, !even);
    }

    public String toString() {
        String s = this.number + " is ";
        boolean firstProperty = true;

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
        
        if (buzz) {
            if (!firstProperty) {
                s += ", ";
            }
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

        if (spy) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "spy";
            firstProperty = false;
        }

        if (square) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "square";
            firstProperty = false;
        }

        if (sunny) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "sunny";
            firstProperty = false;
        }

        if (jumping) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "jumping";
            firstProperty = false;
        }
        
        if (happy) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "happy";
            firstProperty = false;
        }

        if (sad) {
            if (!firstProperty) {
                s += ", ";
            }
            s += "sad";
            firstProperty = false;
        }

        return s;
    }
}
