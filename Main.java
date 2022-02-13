import java.util.ArrayList;
import java.util.List;
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

                if (!inputOk(req)) {
                    continue;
                }

                request = Long.parseLong(req[0]);

                if (request > 0) {

                    if (req.length == 4) {
                        listLength = Integer.parseInt(req[1]);
                        searchProperties(request, listLength, req[2], req[3]);
                    } else if (req.length == 3) {
                        listLength = Integer.parseInt(req[1]);
                        searchProperties(request, listLength, req[2]);
                    } else if (req.length == 2) {
                        listLength = Integer.parseInt(req[1]);
                        checkProperties(request, listLength);
                    } else {
                        checkProperties(request, 0);
                    }
                }
            }

        } while (request != 0);

        System.out.println("\nGoodbye!");

        scanner.close();
    }

    public static boolean inputOk(String[] request) {

        if (!isNatural(request[0])) {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }

        if (request.length > 1 && !isNatural(request[1])) {
            System.out.println("The second parameter should be a natural number.");
            return false;
        }

        if (request.length == 3 && !ANumber.isAvailableProperty(request[2])) {
            String s = String.format("\n\nThe property [%s] is wrong.", request[2].toUpperCase());
            s += String.format("\nAvailable properties: %s",
                    ANumber.listAvailableProperties());
            System.out.println(s);
            return false;
        }

        if (request.length == 4) {
            if (!ANumber.isAvailableProperty(request[2]) && !ANumber.isAvailableProperty(request[3])) {
                String s = String.format("\n\nThe properties [%s, %s] are wrong.", request[2].toUpperCase(), request[3].toUpperCase());
                s += String.format("\nAvailable properties: %s",
                        ANumber.listAvailableProperties());
                System.out.println(s);
                return false;
            }

            if (!ANumber.isAvailableProperty(request[2])) {
                String s = String.format("\n\nThe property [%s] is wrong.", request[2].toUpperCase());
                s += String.format("\nAvailable properties: %s",
                        ANumber.listAvailableProperties());
                System.out.println(s);
                return false;
            }

            if (!ANumber.isAvailableProperty(request[3])) {
                String s = String.format("\n\nThe property [%s] is wrong.", request[3].toUpperCase());
                s += String.format("\nAvailable properties: %s",
                        ANumber.listAvailableProperties());
                System.out.println(s);
                return false;
            }

            if (ANumber.mutuallyExclusiveProperties(request[2], request[3])) {
                String s = String.format("\n\nThe request contains mutually exclusive properties: [%s, %s]\n" +
                        "There are no numbers with these properties.", request[2].toUpperCase(), request[3].toUpperCase());
                System.out.println(s);
                return false;
            }
        }

        return true;
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

    public static void searchProperties(long number, int listLength, String... properties) {
        List<ANumber> found = new ArrayList<>();

        int count = 0;
        ANumber aNumber;
        while (count < listLength) {
            aNumber = new ANumber(number);

            if (properties.length == 2) {
                if (aNumber.hasProperty(properties[0]) && aNumber.hasProperty(properties[1])) {
                    found.add(aNumber);
                    count++;
                }
            } else if (properties.length == 1) {
                if (aNumber.hasProperty(properties[0])) {
                    found.add(aNumber);
                    count++;
                }
            }

            number++;
        }

        System.out.println("\n");
        for (ANumber a : found) {
            System.out.printf("\t%s\n", a);
        }
    }

    public static void printInstructions() {
        System.out.println("\n\nSupported requests:\n" +
                "- enter a natural number to know its properties; \n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

    }
}
