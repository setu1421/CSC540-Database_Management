import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author Md Mirajul Islam (mislam22)
 */

public class Purchase {

    public static ArrayList<String> giftCards = new ArrayList<>();
    public static int purchaseCount = 0;

    public static void purchaseUI(String brandId, String lpCode, String lpName, String activityCode, String activityName) {
        Scanner sc = new Scanner(System.in);
        int giftCardOption, selection, gcUsed=0;
        String usedGiftCard;
        boolean flag = false;

        do {
            if (getGiftCards() != 0) {
                System.out.println("Select the gift card to use for purchase");
                for(int i=0; i<giftCards.size(); i++) {
                    System.out.println((i+1) + ". " + giftCards.get(i));
                }
                System.out.print("Enter your option:");
                giftCardOption = sc.nextInt();
                usedGiftCard = giftCards.get(giftCardOption-1);
                gcUsed = 1;
            }

            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Purchase");
            System.out.println("2. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;
                int purchasePoints=0;

                switch (selection) {
                    case 1:
                        savePurchaseInformation(Login.loggedInUserId, brandId, gcUsed);
                        purchasePoints = getPointsFromRERule(activityCode,brandId);
                        updateCustomerWalletRE(Login.loggedInUserId, brandId, activityCode, purchasePoints);
                        RewardActivity.rewardActivityUI();
                        break;
                    case 2:
                        Customer.customerUI();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }
            } catch (Exception e) {
                System.out.println("Please choose between 1 and 2. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }


    public static int getGiftCards() {
        return 0;
    }

    public static void savePurchaseInformation(String customerId, String brandId, int gcUsed) {

        try {
            PreparedStatement ps = Home.connection.prepareStatement("insert into PURCHASE(PURCHASEID, CUSTOMERID, BID, GCUSED) values (?,?,?,?)");
            String purchaseId = "PCHS" + (++purchaseCount);
            ps.setString(1, purchaseId);
            ps.setString(2, customerId);
            ps.setString(3, brandId);
            ps.setString(4, String.valueOf(gcUsed));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Purchase has been added successfully.");
            } else {
                System.out.println("Purchase information can not be added. Please try again.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Purchase information already present. Please try again.");
        } catch(SQLException e) {
            System.out.println("Purchase information can not be added. Please try again.");
        }
    }

    public static int getPointsFromRERule(String activityCode, String brandId) {
        String sql = "select POINTS from RERULE where ACTIVITYCODE = '" + activityCode + "' AND BRANDID = '" + brandId + "' ORDER BY VERSIONNO DESC";

        ResultSet rs = null;
        int points = 0;

        try {
            rs = Home.statement.executeQuery(sql);
            if (rs.next()) {
                points = rs.getInt("POINTS");
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
        return points;
    }

    public static void updateCustomerWalletRE(String customerId, String brandId, String activityCode, int purchasePoints) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call update_customer_wallet_re(?, ?, ?, ?)}");
            statement.setString(1, customerId);
            statement.setString(2, brandId);
            statement.setString(3, activityCode);
            statement.setString(4, String.valueOf(purchasePoints));

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Customer Wallet RE can not be updated. Please try again.");
            RewardActivity.rewardActivityUI();
        }
    }

}
