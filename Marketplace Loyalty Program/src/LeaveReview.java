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

public class LeaveReview {

    public static int reviewCount = 0;

    public static void leaveReviewUI(String brandId) {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;
        String review;

        do {
            System.out.println("Give your review:");
            review = sc.nextLine();

            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Leave a review");
            System.out.println("2. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        saveReview(review, brandId);
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


    public static void saveReview(String review, String brandId) {
        try {
            PreparedStatement ps = Home.connection.prepareStatement("insert into REVIEW(REVIEWID, REVIEWTEXT, CUSTOMERID, BID) values (?,?,?,?)");
            String reviewId = "RVW" + (++reviewCount);
            ps.setString(1, reviewId);
            ps.setString(2, review);
            ps.setString(3, Login.loggedInUserId);
            ps.setString(4, brandId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Review has been added successfully.");
            } else {
                System.out.println("Review information can not be added. Please try again.");
            }
        } catch(SQLException e) {
            System.out.println("Review information can not be added. Please try again.");
        }
    }
}
