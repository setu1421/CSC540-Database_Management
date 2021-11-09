import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

/**
 * Defines the functionality of reward type
 *
 * @author Setu Kumar Basak (sbasak4)
 */

public class RewardType {
    /**
     * Shows the reward type menus
     */
    public static void rewardTypeUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.print("Enter Quantity:");
            int quantity = sc.nextInt();

            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Gift Card");
            System.out.println("2. Free Product");
            System.out.println("3. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();

                switch (selection) {
                    case 1:
                        addRewardType("R01", quantity);
                        break;
                    case 2:
                        addRewardType("R02", quantity);
                        break;
                    case 3:
                        flag = true;
                        rewardGoBack();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 3. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    /**
     * Adds the reward type
     *
     * @param rewardCode The reward code
     * @param quantity   The reward quantity
     */
    private static void addRewardType(String rewardCode, int quantity) {
        String sql = "Insert into BRANDREWARDTYPE(BRANDID, REWARDCODE, TOTQUANTITY, CURQUANTITY) values (?,?,?,?)";
        try {
            PreparedStatement ps = Home.connection.prepareStatement(sql);
            ps.setString(1, Login.loggedInUserId);
            ps.setString(2, rewardCode);
            ps.setInt(3, quantity);
            ps.setInt(4, quantity);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Reward Type has been added successfully.");
            } else {
                System.out.println("Reward Type can not be added. Please try again.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Reward Type already present. Please try again.");
        } catch (SQLException e) {
            System.out.println("Reward Type can not be added. Please try again.");
        }
    }

    /**
     * Defines the functionality of reward go back
     */
    private static void rewardGoBack() {
        if (LoyaltyProgram.lpType.equalsIgnoreCase("R")) {
            Regular.regularUI();
        } else {
            Tier.tierUI();
        }
    }
}
