import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Brand {
    public static boolean isAlreadyEnrolled = false, isActive = false;
    public static String lpType = "R", lpCode = "";

    public static void brandUI() {
        intialize();

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

                if (!isAlreadyEnrolled) {
                    if (selection >= 2 && selection <= 5) {
                        System.out.println("Brand has not enrolled in a program yet. Please enroll first.");
                        flag = false;
                        continue;
                    }
                } else {
                    if (selection == 6 && isActive) {
                        System.out.println("Loyalty program has been validated before and is in active state.");
                        flag = false;
                        continue;
                    }
                }

                switch (selection) {
                    case 1:
                        LoyaltyProgram.loyaltyProgramUI();
                        break;
                    case 2:
                        RERULES.reRulesUI(true);
                        break;
                    case 3:
                        RERULES.reRulesUI(false);
                        break;
                    case 4:
                        RRRules.rrRulesUI(true);
                        break;
                    case 5:
                        RRRules.rrRulesUI(false);
                        break;
                    case 6:
                        validateLoyaltyProgram();
                        flag = false;
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

    private static void validateLoyaltyProgram() {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call validate_loyalty_program(?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, lpCode);
            statement.setString(3, lpType);
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int ret = statement.getInt(4);

            if (ret == 0) {
                System.out.println("Tiers are not defined. Please define the Tiers.");
            } else if (ret == 1) {
                System.out.println("One Reward Earning rule must be defined.");
            } else if (ret == 2) {
                System.out.println("One Reward Redeeming rule must be defined.");
            } else {
                System.out.println("Loyalty Program has been validated and set to active status.");
                isActive = true;
            }

            statement.close();

        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Loyalty Program can not be validated. Please try again.");
        }
    }

    private static void intialize() {
        String sql = "select LPCODE, LPTYPE, ISVALID from LOYALTYPROGRAM where BRANDID =  '" + Login.loggedInUserId + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            if (rs.next()) {
                isAlreadyEnrolled = true;
                lpCode = rs.getString("LPCODE");
                isActive = rs.getBoolean("ISVALID");
                lpType = rs.getString("LPTYPE");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }
}
