import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Brand {
    public static void brandUI() {
        initialize();

        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. addLoyaltyProgram");
            System.out.println("2. addRERules");
            System.out.println("3. updateRERules");
            System.out.println("4. addRRRules");
            System.out.println("5. updateRRRules");
            System.out.println("6. validateLoyaltyProgram");
            System.out.println("7. Log out");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        //TODO
                        break;
                    case 2:
                        //TODO
                        break;
                    case 3:
                        //TODO
                        break;
                    case 4:
                        //TODO
                        break;
                    case 5:
                        //TODO
                        break;
                    case 6:
                        //TODO
                        break;
                    case 7:
                        Home.showMenu();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 7. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    // Check if the brand is already enrolled in a loyalty program or not.
    public static void initialize()
    {
        //TODO: Get the LPCode which Brand is enrolled in if present.
    }
}
