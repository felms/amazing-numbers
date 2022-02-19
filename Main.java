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

                    if (req.length > 3) {
                        String[] properties = new String[req.length - 2];
                        for (int i = 0; i < properties.length; i++) {
                            properties[i] = req[i + 2];
                        }
                        listLength = Integer.parseInt(req[1]);
                        searchProperties(request, listLength, properties);
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

        if (request.length > 3) {

            int wrongProperties = 0;
            List<String> wrongP = new ArrayList<>();
            for (int i = 2; i < request.length; i++) {
                if (!ANumber.isAvailableProperty(request[i])) {
                    wrongProperties++;
                    wrongP.add(request[i]);
                }
            }

            if (wrongProperties == 1) {
                String s = String.format("\n\nThe property [%s] is wrong.", wrongP.get(0).toUpperCase());
                s += String.format("\nAvailable properties: %s",
                        ANumber.listAvailableProperties());
                System.out.println(s);
                return false;
            }

            if (wrongProperties > 1) {
                String r = wrongP.get(0).toUpperCase();
                for (int i = 1; i < wrongProperties; i++) {
                    r += ", " + wrongP.get(i).toUpperCase();
                }

                String s = String.format("\n\nThe properties [%s] are wrong.", r);
                s += String.format("\nAvailable properties: %s",
                        ANumber.listAvailableProperties());
                System.out.println(s);
                return false;

            }

            boolean mExProperties = false;
            List<String> mxP = new ArrayList<>();
            for (int i = 2; i < request.length; i++) {
                String p0 = request[i];
                for (int j = i + 1; j < request.length; j++) {
                    String p1 = request[j];
                    if (ANumber.mutuallyExclusiveProperties(p0, p1)) {
                        mExProperties = true;
                        mxP.add(p0);
                        mxP.add(p1);
                    }
                }
            }

            if (mExProperties) {
                String r = "[" + mxP.get(0).toUpperCase() + ", " + mxP.get(1).toUpperCase() + "]";
                String s = String.format("\n\nThe request contains mutually exclusive properties: %s\n" +
                        "There are no numbers with these properties.", r);
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

            if (properties.length > 1) {
                boolean foundNumber = true;
                for (int i = 0; i < properties.length; i++) {
                    
                    if ((properties[i].startsWith("-") && !aNumber.hasProperty(properties[i]))
                        || (!properties[i].startsWith("-") && aNumber.hasProperty(properties[i]))) {
                        foundNumber = foundNumber && true;
                    } else {
                        foundNumber = foundNumber && false;
                    }
                }
                if (foundNumber) {
                    found.add(aNumber);
                    count++;
                }
            } else if (properties.length == 1) {
                if (properties[0].startsWith("-") && !aNumber.hasProperty(properties[0])) {
                    found.add(aNumber);
                    count++;
                } else if (!properties[0].startsWith("-") && aNumber.hasProperty(properties[0])) {
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
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

    }
}
