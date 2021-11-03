import java.sql.*;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class RRRules {
    public static void rrRulesUI(boolean add) {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        do {
            System.out.print("Enter Reward Category:");
            String reCategory = sc.nextLine();
            System.out.print("Enter no. of Points:");
            int points = sc.nextInt();

            int selection;
            if (add) {
                selection = Utility.chooseAddMenu(sc, "addRRRule");
            } else {
                selection = Utility.chooseAddMenu(sc, "updateRRRule");
            }

            if (selection == 1) {
                if (add) {
                    saveRERULE(reCategory, points);
                } else {
                    updateRERULE(reCategory, points);
                }
            } else {
                flag = true;
                Brand.brandUI();
            }

            sc.nextLine();
        } while (!flag);
    }

    private static void saveRERULE(String reCategory, int points) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call add_rr_rule(?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, reCategory);
            statement.setInt(3, points);
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int ret = statement.getInt(4);

            statement.close();

            if (ret == 0) {
                System.out.println("Reward Redeeming Rule with this reward code already present. Please try again.");
            } else if (ret == 1) {
                System.out.println("Reward Redeeming Rule has been added successfully.");
            } else {
                System.out.println("The Reward code is not valid. Please try again.");
            }

        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Reward Redeeming Rule can not be added. Please try again.");
        }
    }

    private static void updateRERULE(String reCategory, int points) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call update_re_rule(?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, reCategory);
            statement.setInt(3, points);
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int ret = statement.getInt(4);

            statement.close();

            if (ret == 0) {
                System.out.println("Reward Redeeming Rule with this reward code is not present. Please try again.");
            } else {
                System.out.println("Reward Redeeming Rule has been updated successfully.");
            }

        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Reward Redeeming Rule can not be updated. Please try again.");
        }
    }
}
