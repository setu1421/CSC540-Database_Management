import java.sql.*;
import java.util.Scanner;

/**
 * Defines the functionality of Reward Earning rules
 *
 * @author Setu Kumar Basak (sbasak4)
 */

public class RERULES {
    /**
     * Shows the reward earning rules menu
     *
     * @param add Defines whether to update or add rule
     */
    public static void reRulesUI(boolean add) {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        do {
            System.out.print("Enter Activity Category:");
            String acCategory = sc.nextLine();
            System.out.print("Enter no. of Points:");
            int points = sc.nextInt();

            int selection;
            if (add) {
                selection = Utility.chooseAddMenu(sc, "addRERule");
            } else {
                selection = Utility.chooseAddMenu(sc, "updateRERule");
            }

            if (selection == 1) {
                if (add) {
                    saveRERULE(acCategory, points);
                } else {
                    updateRERULE(acCategory, points);
                }
            } else {
                flag = true;
                Brand.brandUI();
            }

            sc.nextLine();
        } while (!flag);
    }

    /**
     * Adds the reward earning rule
     *
     * @param acCategory The activity category code
     * @param points     The no. of points for the rule
     */
    private static void saveRERULE(String acCategory, int points) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call add_re_rule(?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, acCategory);
            statement.setInt(3, points);
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int ret = statement.getInt(4);

            statement.close();

            if (ret == 0) {
                System.out.println("Reward Earning Rule with this activity code already present. Please try again.");
            } else if (ret == 1) {
                System.out.println("Reward Earning Rule has been added successfully.");
            } else {
                System.out.println("The Activity code is not valid. Please try again.");
            }

        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Reward Earning Rule can not be added. Please try again.");
        }
    }

    /**
     * Adds the reward earning rule
     *
     * @param acCategory The activity category code of the rule
     * @param points     The no of points of the rule
     */
    private static void updateRERULE(String acCategory, int points) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call update_re_rule(?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, acCategory);
            statement.setInt(3, points);
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int ret = statement.getInt(4);

            statement.close();

            if (ret == 0) {
                System.out.println("Reward Earning Rule with this activity code is not present. Please try again.");
            } else {
                System.out.println("Reward Earning Rule has been updated successfully.");
            }

        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Reward Earning Rule can not be updated. Please try again.");
        }
    }
}
