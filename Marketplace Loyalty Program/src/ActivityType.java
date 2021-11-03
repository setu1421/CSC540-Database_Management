import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class ActivityType {
    public static void activityTypeUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Purchase");
            System.out.println("2. Leave a review");
            System.out.println("3. Refer a friend");
            System.out.println("4. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();

                switch (selection) {
                    case 1:
                        addActivityType("A01");
                        break;
                    case 2:
                        addActivityType("A02");
                        break;
                    case 3:
                        addActivityType("A03");
                        break;
                    case 4:
                        flag = true;
                        activityGoBack();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 4. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    private static void addActivityType(String activityCode) {
        String sql = "Insert into BRANDACTIVITYTYPE(BRANDID, ACTIVITYCODE) values (?,?)";
        try {
            PreparedStatement ps = Home.connection.prepareStatement(sql);
            ps.setString(1, Login.loggedInUserId);
            ps.setString(2, activityCode);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Activity Type has been added successfully.");
            } else {
                System.out.println("Activity Type can not be added. Please try again.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Activity Type already present. Please try again.");
        } catch (SQLException e) {
            System.out.println("Activity Type can not be added. Please try again.");
        }
    }

    private static void activityGoBack() {
        if (LoyaltyProgram.lpType.equalsIgnoreCase("R")) {
            Regular.regularUI();
        } else {
            Tier.tierUI();
        }
    }
}
