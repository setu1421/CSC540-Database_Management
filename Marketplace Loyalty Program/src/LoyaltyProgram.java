import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class LoyaltyProgram {
    public static void loyaltyProgramUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Regular");
            System.out.println("2. Tier");
            System.out.println("3. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        Regular.regularUI();
                        break;
                    case 2:
                        Tier.tierUI();
                        break;
                    case 3:
                        Brand.brandUI();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 3. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }
}
