import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Md Mirajul Islam (mislam22)
 */

public class Customer {
    public static HashMap<String, String> lpCodeNameMap = new HashMap<String, String>();
    public static ArrayList<String> allLPCodes = new ArrayList<String>();
    public static ArrayList<String> enrolledLPCodes = new ArrayList<String>();
    public static ArrayList<String> availableLPCodes = new ArrayList<String>();

    public static void customerUI() {
        initialize();
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Enroll in Loyalty Program");
            System.out.println("2. Reward Activities");
            System.out.println("3. View Wallet");
            System.out.println("4. Redeem Points");
            System.out.println("5. Log out");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        enrollLoyaltyProgram();
                        break;
                    case 2:
                        RewardActivity.rewardActivityUI();
                        break;
                    case 3:
                        viewWallet();
                        break;
                    case 4:
                        RedeemPoints.redeemPointsUI();
                        break;
                    case 5:
                        Home.showMenu();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 5. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    public static void enrollLoyaltyProgram() {
        if (availableLPCodes.size() == 0) {
            System.out.println("No Loyalty Program is available.");
            customerUI();
        } else {
            System.out.println("Choose any of the loyalty programs available for you");
            for (int i = 0; i < availableLPCodes.size(); i++) {
                System.out.println((i + 1) + ". " + lpCodeNameMap.get(availableLPCodes.get(i)));
            }

            boolean flag = false;
            Scanner sc = new Scanner(System.in);

            do {
                System.out.print("Enter your option:");
                int lpOption = sc.nextInt();

                if (lpOption >= 1 && lpOption <= availableLPCodes.size()) {
                    String lpCode = availableLPCodes.get(lpOption - 1);

                    int selection = Utility.chooseAddMenu(sc, "Enroll in the Loyalty Program");

                    if (selection != 2) {
                        saveLoyaltyProgram(lpCode);
                    }

                    customerUI();
                    flag = true;
                } else {
                    System.out.println("Please choose valid loyalty program option.");
                }
            } while (!flag);
        }
    }


    public static void viewWallet() {
        int selection;
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        System.out.println("Here is your wallet: ");
        String sql = "select CUSTOMERID, BID, ACTIVITYCODE, POINTSEARNED, REWARDCODE, POINTSREEDEMED from WALLETRE JOIN WALLETRR USING (CUSTOMERID) where CUSTOMERID='" + Login.loggedInUserId + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println("CustomerId: " + rs.getString("CUSTOMERID") +
                        "BrandId: " + rs.getString("BID") +
                        "ActivityCode: " + rs.getString("ACTIVITYCODE") +
                        "PointsEarned: " + rs.getString("POINTSEARNED") +
                        "RewardCode: " + rs.getString("REWARDCODE") +
                        "PointsRedeemed: " + rs.getString("POINTSREEDEMED"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        do {
            System.out.println("1. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                if (selection == 1) {
                    Customer.customerUI();
                } else {
                    System.out.println("You have entered a wrong option. Please choose again.");
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Please choose between 1. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    private static void initialize() {
        allLPCodes.clear();
        enrolledLPCodes.clear();
        availableLPCodes.clear();
        lpCodeNameMap.clear();

        //extracting all possible LPCodes from LP table
        String sql = "select LPCODE, LPNAME from LOYALTYPROGRAM where ISVALID = 1";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                allLPCodes.add(rs.getString("LPCODE"));
                lpCodeNameMap.put(rs.getString("LPCODE"), rs.getString("LPNAME"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        // extracting Customer already enrolled LPCodes
        sql = "select LPCODE from ENROLLP where CUSTOMERID =  '" + Login.loggedInUserId + "'";

        rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                enrolledLPCodes.add(rs.getString("LPCODE"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        // calculating available LPCodes
        allLPCodes.removeAll(enrolledLPCodes);
        availableLPCodes = allLPCodes;
    }

    private static void saveLoyaltyProgram(String lpCode) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call enroll_customer_loyalty_program(?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, lpCode);
            statement.registerOutParameter(3, Types.INTEGER);

            statement.execute();

            int ret = statement.getInt(3);

            if (ret == 0) {
                System.out.println("Loyalty Program is already enrolled. Please try again.");
            } else {
                System.out.println("Customer has been enrolled in the Loyalty Program successfully.");
            }

            statement.close();

        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Loyalty Program can not be enrolled. Please try again.");
            customerUI();
        }
    }
}
