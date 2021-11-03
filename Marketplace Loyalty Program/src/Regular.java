import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Regular {
    public static void regularUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Activity Types");
            System.out.println("2. Reward Types");
            System.out.println("3. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        ActivityType.activityTypeUI();
                        break;
                    case 2:
                        RewardType.rewardTypeUI();
                        break;
                    case 3:
                        LoyaltyProgram.loyaltyProgramUI();
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
