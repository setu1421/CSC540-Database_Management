import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public static String userType = "";

    public static void loginUI() {
        Scanner sc = new Scanner(System.in);
        boolean loginSuccessful = false;

        do {
            System.out.print("Enter your userid:");
            String userid = sc.next();
            System.out.print("Enter your password:");
            String password = sc.next();

            int selection = chooseSignInMenu(sc);

            if (selection == 1) {
                loginSuccessful = checkUserIdAndPassword(userid, password);
            } else {
                Home.showMenu();
            }

        } while (!loginSuccessful);

        if (userType.equals("A")) {
            System.out.println("Your are a admin.");
        }

    }

    private static int chooseSignInMenu(Scanner sc) {
        System.out.println("Choose what operation you want to perform");
        System.out.println("1. Sign in");
        System.out.println("2. Go Back");
        System.out.print("Enter your options:");

        int selection = sc.nextInt();

        if (selection != 1 && selection != 2) {
            System.out.println("You have entered a wrong option. Please choose again.");
            chooseSignInMenu(sc);
        }

        return selection;
    }

    private static boolean checkUserIdAndPassword(String userid, String password) {
        boolean loginSuccessful = false;

        String sqlCred = "select usertype from users where userid=  '" + userid
                + "' and password='" + password + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                userType = rs.getString("USERTYPE");
                loginSuccessful = true;
            } else {
                System.out.println("Wrong userid and password. Please try again.");
            }
            rs.close();
        } catch (SQLException e) {
            Home.close(rs);
            e.printStackTrace();
        }

        return loginSuccessful;
    }

}
