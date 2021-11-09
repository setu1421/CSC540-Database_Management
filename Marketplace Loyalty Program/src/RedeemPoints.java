import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Defines the functionality of redeeming points
 *
 * @author Md Mirajul Islam (mislam22)
 * @author Setu Kumar Basak (sbasak4)
 */

public class RedeemPoints {
    public static ArrayList<String> availableBrandIds = new ArrayList<>();
    public static ArrayList<String> availableLP = new ArrayList<>();
    public static ArrayList<String> availableRewardIds = new ArrayList<>();
    public static ArrayList<String> availableRewardNames = new ArrayList<>();
    public static int rewardPoints = 0;

    /**
     * Shows redeem points menus
     */
    public static void redeemPointsUI() {
        getAvailableBrands();
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        if (availableLP.size() == 0) {
            System.out.println("Sorry, customer hasn't enrolled in any Loyalty Program");
            Customer.customerUI();
            return;
        }

        do {
            boolean innerFlag = false;
            System.out.println("Choose the loyalty program to Redeem Points");
            for (int i = 0; i < availableLP.size(); i++) {
                System.out.println((i + 1) + ". " + availableLP.get(i));
            }

            System.out.print("Enter your option:");
            int lpOption = sc.nextInt();

            if (lpOption >= 1 && lpOption <= availableLP.size()) {
                String lpId = availableLP.get(lpOption - 1);
                String brandId = availableBrandIds.get(lpOption - 1);

                getAvailableRewards(brandId);

                do {
                    System.out.println("Choose the Reward to redeem.");
                    for (int i = 0; i < availableRewardIds.size(); i++) {
                        System.out.println((i + 1) + ". " + availableRewardIds.get(i) + ":" + availableRewardNames.get(i));
                    }
                    System.out.println((availableRewardIds.size() + 1) + ". Go Back");
                    System.out.print("Enter your option:");

                    int rewardOption = sc.nextInt();

                    innerFlag = true;
                    if (rewardOption >= 1 && rewardOption <= availableRewardIds.size() + 1) {

                        if (rewardOption == availableRewardIds.size() + 1) {
                            Customer.customerUI();
                            return;
                        }

                        String rewardCode = availableRewardIds.get(rewardOption - 1);
                        String rewardName = availableRewardNames.get(rewardOption - 1);
                        System.out.print("Enter Quantity:");
                        int rewardQty = sc.nextInt();

                        if (rewardQty <= 0) {
                            innerFlag = false;
                            System.out.println("Please enter non negative value.");
                            continue;
                        }

                        int selection = Utility.chooseAddMenu(sc, "Rewards Selection");

                        if (selection == 2) {
                            Customer.customerUI();
                        } else {
                            doRedeemActivity(brandId, lpId, rewardCode, rewardQty);
                        }
                    } else {
                        System.out.println("You have entered a wrong option. Please choose again.");
                        innerFlag = false;
                    }
                } while (!innerFlag);

                flag = true;
            } else {
                System.out.println("Please choose valid loyalty program option.");
            }
        } while (!flag);
    }

    /**
     * Defines the functionality of redeem activity
     *
     * @param brandId    The brand id of redeem activity
     * @param lpCode     The loyalty program code of redeem activity
     * @param rewardCode The reward code of redeem activity
     * @param rewardQty  The reward quantity of the reward activity
     */
    private static void doRedeemActivity(String brandId, String lpCode, String rewardCode, int rewardQty) {
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call customer_redeem(?, ?, ?, ?, ?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, brandId);
            statement.setString(3, lpCode);
            statement.setString(4, rewardCode);
            statement.setInt(5, rewardQty);
            statement.setInt(6, rewardPoints);
            statement.registerOutParameter(7, Types.INTEGER);

            statement.execute();
            int ret = statement.getInt(7);

            if (ret == 1) {
                System.out.println("Customer has redeemed successfully.");
            } else {
                System.out.println("Customer could not redeem successfully.");
            }
            statement.close();
            RewardActivity.rewardActivityUI();
        } catch (SQLException e) {
            Utility.close(statement);
            System.out.println("Customer could not redeem successfully.");
            RewardActivity.rewardActivityUI();
        }
    }

    /**
     * Defines the functionality to retrieve available brands
     */
    public static void getAvailableBrands() {
        availableBrandIds.clear();

        String customerId = Login.loggedInUserId;
        String sql = "select BRANDID, LPNAME from ENROLLP JOIN LOYALTYPROGRAM USING (LPCODE) where CUSTOMERID =  '" + customerId + "' AND ISVALID = 1 " +
                "AND BRANDID IN (SELECT BRANDID FROM BRANDREWARDTYPE WHERE CURQUANTITY > 0)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                availableBrandIds.add(rs.getString("BRANDID"));
                availableLP.add(rs.getString("LPNAME"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    public static void getAvailableRewards(String brandId) {
        availableRewardIds.clear();
        availableRewardNames.clear();
        availableLP.clear();

        rewardPoints = getRewardPoints(brandId);
        System.out.println("You have " + rewardPoints + " reward points.");

        ResultSet rs = null;

        String sql = "SELECT R1.REWARDCODE RCODE, RE.REWARDNAME RNAME from RRRULE R1 \n" +
                "INNER JOIN REWARDTYPE RE\n" +
                "ON R1.REWARDCODE = RE.REWARDCODE\n" +
                "where R1.BRANDID = '" + brandId + "' AND R1.POINTS <= " + rewardPoints + "\n" +
                "AND R1.VERSIONNO = (SELECT MAX(R2.VERSIONNO) FROM RRRULE R2 WHERE  R1.BRANDID =  R2.BRANDID AND R1.REWARDCODE = R2.REWARDCODE)";
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                availableRewardIds.add(rs.getString("RCODE"));
                availableRewardNames.add(rs.getString("RNAME"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    /**
     * Defines the functionality of retrieving reward points
     *
     * @param brandId The brand Id
     * @return Returns the customer reward points of the specified brand
     */
    private static int getRewardPoints(String brandId) {
        int rewardPoints = 0;
        CallableStatement statement = null;
        try {
            statement = Home.connection.prepareCall("{call customer_reward_points(?, ?, ?)}");
            statement.setString(1, Login.loggedInUserId);
            statement.setString(2, brandId);
            statement.registerOutParameter(3, Types.INTEGER);

            statement.execute();
            rewardPoints = statement.getInt(3);
            statement.close();
        } catch (SQLException e) {
            Utility.close(statement);
        }

        return rewardPoints;
    }
}
