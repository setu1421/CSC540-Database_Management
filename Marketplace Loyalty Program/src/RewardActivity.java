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
 * @author Setu Kumar Basak (sbasak4)
 */

public class RewardActivity {

    public static HashMap<String, String> lpCodeNameMap = new HashMap<String, String>();
    public static HashMap<String, String> lpActivityCodeNameMap = new HashMap<String, String>();
    public static HashMap<String, String> lpBrandMap = new HashMap<String, String>();
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

        do {
            boolean innerFlag = false;
            for (int i = 0; i < joinedLPCodes.size(); i++) {
                System.out.println((i + 1) + ". " + lpCodeNameMap.get(joinedLPCodes.get(i)));
            }
            System.out.print("Choose your Loyalty Program option:");

            lpOption = sc.nextInt();

            if (lpOption >= 1 && lpOption <= joinedLPCodes.size()) {
                lpCode = joinedLPCodes.get(lpOption - 1);
                lpName = lpCodeNameMap.get(lpCode);

                getLPActivityTypes(lpCode);

                do {
                    System.out.println("Choose what operation you want to perform");
                    for (int i = 0; i < lpActivityTypes.size(); i++) {
                        System.out.println((i + 1) + ". " + lpActivityTypes.get(i));
                    }
                    System.out.println((lpActivityTypes.size() + 1) + ". Go Back");
                    System.out.print("Enter your option:");

                    selection = sc.nextInt();

                    innerFlag = true;
                    if (selection >= 1 && selection <= lpActivityTypes.size() + 1) {

                        if (selection == lpActivityTypes.size() + 1) {
                            Customer.customerUI();
                            return;
                        }

                        switch (lpActivityTypes.get(selection - 1)) {
                            case "Purchase":
                                Purchase.purchaseUI(lpBrandMap.get(lpCode), lpCode, lpActivityCodeNameMap.get("Purchase"));
                                break;
                            case "Leave a review":
                                LeaveReview.leaveReviewUI(lpBrandMap.get(lpCode), lpCode, lpActivityCodeNameMap.get("Write a review"));
                                break;
                            case "Refer a friend":
                                ReferFriend.referFriendUI(lpBrandMap.get(lpCode), lpCode, lpActivityCodeNameMap.get("Refer a friend"));
                                break;
                            default:
                                System.out.println("You have entered a wrong option. Please choose again.");
                                innerFlag = false;
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

    public static void getJoinedLoyaltyPrograms() {
        joinedLPCodes.clear();
        lpCodeNameMap.clear();

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
        lpActivityTypes.clear();
        lpActivityCodeNameMap.clear();
        lpBrandMap.clear();

        String sql = "select ACTIVITYCODE, ACTIVITYNAME, BRANDID from LOYALTYPROGRAM JOIN BRANDACTIVITYTYPE USING (BRANDID) JOIN ACTIVITYTYPE USING (ACTIVITYCODE)  where LPCODE =  '" + lpCode + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sql);
            while (rs.next()) {
                lpActivityTypes.add(rs.getString("ACTIVITYNAME"));
                lpActivityCodeNameMap.put(rs.getString("ACTIVITYNAME"), rs.getString("ACTIVITYCODE"));
                lpBrandMap.put(lpCode, rs.getString("BRANDID"));
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }
}
