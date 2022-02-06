import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        String n = scanner.nextLine();

        if (!isNatural(n)) {
            System.out.println("This number is not natural!");

        } else {
            int number = Integer.parseInt(n);
            checkProperties(number);
        }

        scanner.close();
    }

    public static boolean isNatural(String number) {
        
        if (number == null) {
            return false;
        }

        try {
            int d = Integer.parseInt(number);
            
            return d > 0;

        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void checkProperties(int number) {
        boolean even = number % 2 == 0;

        System.out.printf("Properties of %d\n" +
                            "\teven: %s\n" +
                            "\todd: %s\n" +
                            "\tbuzz: %s\n" +
                            "\tduck: %s\n",
                            number, even, !even, isBuzzNumber(number), isDuckNumber(number));
    }

    public static boolean isBuzzNumber(int number) {
        
        return (number % 10) == 7 || (number % 7) == 0;

    }
    
    public static boolean isDuckNumber(int number) {
        
        while (number > 0) {
            if(number % 10 == 0) {
                return true;
            }
            number /= 10;
        }

        return false;
    }
}
