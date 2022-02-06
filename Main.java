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

            boolean isEven = number % 2 == 0;
            System.out.printf("This number is %s.\n", isEven ? "Even" : "Odd");

            isBuzzNumber(number);
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

    public static void isBuzzNumber(int number) {
        
        int lastDigit = number % 10;
        
        boolean divisibleBy7 = number % 7 == 0;

        if(lastDigit == 7 || divisibleBy7) {
            System.out.println("It is a Buzz number.\nExplanation:");
            
            if(lastDigit == 7 && divisibleBy7) {
                System.out.printf("%d is divisible by 7 and ends with 7.\n", number);
            } else if(divisibleBy7) {
                System.out.printf("%d is divisible by 7.\n", number);
            } else {
                System.out.printf("%d ends with 7.\n", number);
            }

        } else {
            System.out.printf("It is not Buzz number.\n" + 
                                "Explanation:\n" + 
                                "%d is neither divisible by 7 nor does it end with 7.\n", 
                                number);
        }
    }
}