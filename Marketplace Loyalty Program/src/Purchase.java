import java.sql.*;
import java.util.Scanner;

/**
 * @author Md Mirajul Islam (mislam22)
 * @author Setu Kumar Basak (sbasak4)
 */

public class Purchase {

    public static void purchaseUI(String brandId, String lpCode, String activityCode) {
        int giftCardCount = getGiftCardCount(brandId);
        int isGCUsed = 0;

        Scanner sc = new Scanner(System.in);

        if (giftCardCount != 0) {
            boolean flag = false;

            do {
                flag = true;
                System.out.println("You have " + giftCardCount + " gift card. Do you want to use for purchase?");
                System.out.println("1: Yes");
                System.out.println("2: No");
                System.out.print("Enter your option:");

                int gcSelection = sc.nextInt();

                if (gcSelection == 1) {
                    isGCUsed = 1;
                } else if (gcSelection == 2) {
                    isGCUsed = 0;
                } else {
                    System.out.println("Please choose option between 1 and 2.");
                    flag = false;
                }
            } while (!flag);
        } else {
            System.out.println("You have no gift card available. Please purchase without gift card.");
        }

        int selection = Utility.chooseAddMenu(sc, "Purchase");

        if (selection == 2) {
            Customer.customerUI();
        } else {
            CallableStatement statement = null;
            try {
                statement = Home.connection.prepareCall("{call customer_add_purchase(?, ?, ?, ?, ?)}");
                statement.setString(1, Login.loggedInUserId);
                statement.setString(2, brandId);
                statement.setString(3, lpCode);
                statement.setString(4, activityCode);
                statement.setInt(5, isGCUsed);

                statement.execute();
                System.out.println("Purchase has been successful.");
                statement.close();
                RewardActivity.rewardActivityUI();
            } catch (SQLException e) {
                Utility.close(statement);
                System.out.println("Purchase can not be done. Please try again.");
                RewardActivity.rewardActivityUI();
            }
        }
    }

    private static int getGiftCardCount(String brandId) {
        int giftCardCount = 0;

        String sql = "select COUNT(*) GIFTCOUNT FROM WALLETRR where CUSTOMERID =  '" + Login.loggedInUserId + "' AND BID = '" + brandId + "' AND USED = 0";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            if (rs.next()) {
                giftCardCount = rs.getInt("GIFTCOUNT");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        return giftCardCount;
    }
}


