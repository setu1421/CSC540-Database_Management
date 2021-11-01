import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Tier {
    public static void tierUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Tiers Set up");
            System.out.println("2. Activity Types");
            System.out.println("3. Reward Types");
            System.out.println("4. Go Back");
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
                        LoyaltyProgram.loyaltyProgramUI();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 4. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }
}