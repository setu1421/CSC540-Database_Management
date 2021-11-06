import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author Md Mirajul Islam (mislam22)
 */

public class ReferFriend {

    public static ArrayList<String> userFriendList = new ArrayList<>();

    public static void referFriendUI(String brandId, String lpCode, String activityCode) {
        getUserFriendList(lpCode);
        Scanner sc = new Scanner(System.in);
        int referOption, selection;
        boolean flag = false;

        System.out.println("Choose a friend to refer:");
        for (int i=0; i<userFriendList.size(); i++) {
            System.out.println((i+1) + ". " + userFriendList.get(i));
        }

        do {
            System.out.print("Enter your option:");
            referOption = sc.nextInt();
            userFriendList.remove(referOption-1); // can't refer the same person multiple times

            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Refer a friend");
            System.out.println("2. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        addBonusForReferer(activityCode, brandId);
                        referFriendUI(brandId, lpCode, activityCode);
                        break;
                    case 2:
                        Customer.customerUI();
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

    public static void getUserFriendList(String lpCode) {

        // all friends
        String sql = "select USERID from USERS where USERID <>  '" + Login.loggedInUserId + "' AND USERTYPE = 'C'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                userFriendList.add(rs.getString("USERID"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        // subtracting them who are already enrolled in the brand
        sql = "select CUSTOMERID from ENROLLP where LPCODE <>  '" + lpCode + "'";
        rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                userFriendList.remove(rs.getString("CUSTOMERID"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    public static void addBonusForReferer(String activityCode, String brandId) {

        int points = getPointsFromRERule(activityCode, brandId);

        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call update_customer_wallet_re(?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, brandId);
            statement.setString(3, activityCode);
            statement.setString(4, String.valueOf(points));

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Customer Wallet RE can not be updated. Please try again.");
            RewardActivity.rewardActivityUI();
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


}
