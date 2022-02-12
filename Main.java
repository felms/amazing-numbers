import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        printInstructions();

        long request = -1;
        int listLength = 0;
        String[] req;
        do {
            System.out.print("\n\nEnter a request: ");
            req = scanner.nextLine().split(" ");
            if ( req.length == 0 || req[0].equals("")) {
                printInstructions();
            } else {

                if (!isNatural(req[0])) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }

                if (req.length > 1 && !isNatural(req[1])) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }

                if (req.length > 2 && !ANumber.isAvailableProperty(req[2])) {
                    String s = String.format("\n\nThe property [%s] is wrong.", req[2].toUpperCase());
                    s += String.format("\nAvailable properties: %s",
                            ANumber.listAvailableProperties());
                    System.out.println(s);
                    continue;
                }

                request = Long.parseLong(req[0]);
                if (request > 0) {
                    listLength = req.length > 1 ? Integer.parseInt(req[1]) : listLength;
                    checkProperties(request, listLength);
                }
            }

        } while (request != 0);

        scanner.close();
    }

    public static boolean isNatural(String number) {

        boolean natural = true;
        try {
            long l = Long.parseLong(number);
            natural = l >= 0;
        } catch (NumberFormatException efe) {
            natural = false;
        }

        return natural;
    }

    public static void checkProperties(long number, int listLength) {

        if(listLength == 0) {
            System.out.printf(new ANumber(number).listProperties());

        } else {
            System.out.println("\n");
            for (long i = number; i < number + listLength; i++) {
                ANumber a = new ANumber(i);
                System.out.printf("\t%s\n", a);
            }
        }
    }

    public static void printInstructions() {
        System.out.println("\n\nSupported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

    }
}
