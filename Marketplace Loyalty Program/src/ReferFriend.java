import java.sql.*;
import java.util.Scanner;

/**
 * Defines the functionality of referring a friend
 *
 * @author Md Mirajul Islam (mislam22)
 * @author Setu Kumar Basak (sbasak4)
 */

public class ReferFriend {
    /**
     * Shows refer a friend menus
     *
     * @param brandId      The brand id of the referral activity
     * @param lpCode       The loyalty program of the referral activity
     * @param activityCode The activity code of the referral activity
     */
    public static void referFriendUI(String brandId, String lpCode, String activityCode) {
        Scanner sc = new Scanner(System.in);

        int selection = Utility.chooseAddMenu(sc, "Refer");

        if (selection == 2) {
            Customer.customerUI();
        } else {
            CallableStatement statement = null;
            try {
                statement = Home.connection.prepareCall("{call customer_refer_friend(?, ?, ?, ?)}");
                statement.setString(1, Login.loggedInUserId);
                statement.setString(2, brandId);
                statement.setString(3, lpCode);
                statement.setString(4, activityCode);
                ;

                statement.execute();
                System.out.println("Customer has been referred successfully.");
                statement.close();
                RewardActivity.rewardActivityUI();
            } catch (SQLException e) {
                Utility.close(statement);
                System.out.println("Customer could not be referred successfully. Please try again.");
                RewardActivity.rewardActivityUI();
            }
        }
    }
}
