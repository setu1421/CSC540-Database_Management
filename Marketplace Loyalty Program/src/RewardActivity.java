import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author Md Mirajul Islam (mislam22)
 */

public class RewardActivity {

    public static HashMap<String, String> lpCodeNameMap = new HashMap<String, String>();
    public static ArrayList<String> joinedLPCodes = new ArrayList<String>();
    public static ArrayList<String> lpActivityTypes = new ArrayList<>();

    public static void rewardActivityUI() {
        getJoinedLoyaltyPrograms();

        Scanner sc = new Scanner(System.in);
        int lpOption, selection;
        String lpCode, lpName;
        boolean flag = false;

        if (joinedLPCodes.size() == 0) {
            System.out.println("Sorry, customer hasn't enrolled in any Loyalty Program");
            Customer.customerUI();
            return;
        }

        for(int i=0; i<joinedLPCodes.size(); i++) {
            System.out.println((i+1) + ". " + lpCodeNameMap.get(joinedLPCodes.get(i)));
        }

        do {
            System.out.print("Choose your Loyalty Program option:");

            lpOption = sc.nextInt();
            lpCode = joinedLPCodes.get(lpOption-1);
            lpName = lpCodeNameMap.get(lpCode);

            getLPActivityTypes(lpCode);

            System.out.println("Choose what operation you want to perform");
            for (int i=0; i<lpActivityTypes.size(); i++) {
                System.out.println((i+1) + ". " + lpActivityTypes.get(i));
            }
            System.out.println((lpActivityTypes.size() + 1) +". Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (lpActivityTypes.get(selection-1)) {
                    case "Purchase":
                        Purchase.purchaseUI();
                        break;
                    case "Leave a review":
                        LeaveReview.leaveReviewUI();
                        break;
                    case "Refer a friend":
                        ReferFriend.referFriendUI();
                        break;
                    case "Go back":
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

    public static void getJoinedLoyaltyPrograms() {
        String sql = "select LPCODE from ENROLLP where CUSTOMERID =  '" + Login.loggedInUserId + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                joinedLPCodes.add(rs.getString("LPCODE"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        //for lpcode - lpname map
        sql = "select LPCODE, LPNAME from LOYALTYPROGRAM where ISVALID = 1";
        rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                lpCodeNameMap.put(rs.getString("LPCODE"), rs.getString("LPNAME"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    public static void getLPActivityTypes(String lpCode) {
        String sql = "select ACTIVITYNAME from LOYALTYPROGRAM JOIN BRANDACTIVITYTYPE USING (BRANDID) JOIN ACTIVITYTYPE USING (ACTIVITYCODE)  where LPCODE =  '" + lpCode + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                lpActivityTypes.add(rs.getString("ACTIVITYNAME"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }
}
