import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author Md Mirajul Islam (mislam22)
 * @author Setu Kumar Basak (sbasak4)
 */

public class LeaveReview {

    public static void leaveReviewUI(String brandId, String lpCode, String activityCode) {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        String review;

        do {
            flag = true;
            System.out.println("Enter your review:");
            review = sc.nextLine();

            if (review.trim().isEmpty()) {
                System.out.println("Review can not be empty.");
                flag = false;
                continue;
            }

            int selection = Utility.chooseAddMenu(sc, "Leave a review");

            if (selection == 2) {
                Customer.customerUI();
            } else {
                CallableStatement statement = null;
                try {
                    statement = Home.connection.prepareCall("{call customer_add_review(?, ?, ?, ?, ?)}");
                    statement.setString(1, Login.loggedInUserId);
                    statement.setString(2, brandId);
                    statement.setString(3, lpCode);
                    statement.setString(4, activityCode);
                    statement.setString(5, activityCode);

                    statement.execute();
                    System.out.println("Review has been added successfully.");
                    statement.close();
                    RewardActivity.rewardActivityUI();
                } catch (SQLException e) {
                    Utility.close(statement);
                    System.out.println("Review can not be added. Please try again.");
                    RewardActivity.rewardActivityUI();
                }
            }
        } while (!flag);
    }
}
