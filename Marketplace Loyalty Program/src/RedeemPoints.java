import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author Md Mirajul Islam (mislam22)
 */

public class RedeemPoints {

    public static ArrayList<String> availableBrandIds = new ArrayList<>();
    public static ArrayList<String> availableRewardIds = new ArrayList<>();
    public static int rewardPoints=0;

    public static void redeemPointsUI() {
        getAvailableBrands();
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        System.out.println("Choose the brand to Redeem Points");
        for (int i=0; i<availableBrandIds.size(); i++) {
            System.out.println((i+1) + ". " + availableBrandIds.get(i));
        }
        System.out.print("Enter your option:");
        int brandOption = sc.nextInt();
        String brandId = availableBrandIds.get(brandOption-1);

        getAvailableRewards(brandId);

        System.out.println("Choose the Reward to redeem");
        for (int i=0; i<availableRewardIds.size(); i++) {
            System.out.println((i+1) + ". " + availableRewardIds.get(i));
        }
        System.out.print("Enter your option:");
        int rewardOption = sc.nextInt();
        String rewardId = availableRewardIds.get(rewardOption-1);

        System.out.print("Enter Quantity:");
        int rewardQty = sc.nextInt();
        int redeemedPoints = rewardQty * rewardPoints;

        int selection = Utility.chooseAddMenu(sc, "Rewards Selection");
        if (selection == 2) {
            Customer.customerUI();
        } else {
            updateRedeemAndCustomerWalletRR(rewardId, redeemedPoints, brandId, rewardQty);
            redeemPointsUI();
        }
    }

    public static void getAvailableBrands() {
        String customerId = Login.loggedInUserId;
        String sql = "select BRANDID from ENROLLP JOIN LOYALTYPROGRAM USING (LPCODE) where CUSTOMERID =  '" + customerId + "' AND ISVALID = 1";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                availableBrandIds.add(rs.getString("BRANDID"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    public static void getAvailableRewards(String brandId) {
        String sql = "select SUM(POINTSEARNED) from WALLETRE where CUSTOMERID =  '" + Login.loggedInUserId + "' AND BID = '" + brandId + "'";
        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            if (rs.next()) {
                rewardPoints = rs.getInt("SUM(POINTSEARNED)");
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        sql = "select REWARDCODE from RRRULE ORDER BY VERSIONNO DESC where BRANDID = '" + brandId + "' AND POINTS <= '" + rewardPoints + "'";
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                availableRewardIds.add(rs.getString("REWARDCODE"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    public static void updateRedeemAndCustomerWalletRR(String rewardId, int redeemedPoints, String brandId, int rewardQty) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call update_redeem_and_customer_wallet_rr(?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, brandId);
            statement.setString(3, rewardId);
            statement.setString(4, String.valueOf(redeemedPoints));
            statement.setString(5, String.valueOf(rewardQty));

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Customer Wallet RR can not be updated. Please try again.");
            RewardActivity.rewardActivityUI();
        }
    }
}
