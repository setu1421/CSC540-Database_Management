import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class LoyaltyProgram {
    public static String lpName = "", lpCode = "", lpType = "";

    public static void loyaltyProgramUI() {
        Scanner sc = new Scanner(System.in);

        if (checkIfBrandAlreadyEnrolled()) {
            System.out.println("Brand is already enrolled in Loyalty Program " + lpName);

            int selection = Utility.chooseAddMenu(sc, "Add Activity/Reward Types");
            if (selection == 1) {
                if (lpType.equalsIgnoreCase("R")) {
                    Regular.regularUI();
                } else {
                    Tier.tierUI();
                }
            } else {
                Brand.brandUI();
            }
        }

        int selection;
        boolean flag = false;

        do {
            System.out.print("Enter loyalty program name:");
            lpName = sc.nextLine();

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
                        saveLoyaltyProgram(lpName, "R");
                        Regular.regularUI();
                        break;
                    case 2:
                        saveLoyaltyProgram(lpName, "T");
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

    private static boolean checkIfBrandAlreadyEnrolled() {
        boolean alreadyEnrolled = false;

        String sql = "select lpcode, lpname, lptype from loyaltyprogram where brandid =  '" + Login.loggedInUserId + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            if (rs.next()) {
                lpCode = rs.getString("LPCODE");
                lpName = rs.getString("LPNAME");
                lpType = rs.getString("LPTYPE");
                alreadyEnrolled = true;
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        return alreadyEnrolled;
    }

    private static void saveLoyaltyProgram(String lpName, String lpType) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call enroll_brand_loyalty_program(?, ?, ?, ?)}");
            statement.setString(1, lpName);
            statement.setString(2, lpType);
            statement.setString(3, Login.loggedInUserId);
            statement.registerOutParameter(4, Types.VARCHAR);

            statement.execute();

            lpCode = statement.getString(4);
            LoyaltyProgram.lpType = lpType;

            statement.close();

            System.out.println("Loyalty Program has been added. Please add activity types, " +
                    "reward types, rules etc to make the program active.");

        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Loyalty Program can not be added. Please try again.");
            loyaltyProgramUI();
        }
    }
}
