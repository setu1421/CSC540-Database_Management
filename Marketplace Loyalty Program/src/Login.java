import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Login {
    public static String loggedInUserId, loggedInUserType;

    public static void loginUI() {
        Scanner sc = new Scanner(System.in);
        boolean loginSuccessful = false;

        do {
            System.out.print("Enter your userid:");
            String userid = sc.nextLine();
            System.out.print("Enter your password:");
            String password = sc.nextLine();

            int selection = Utility.chooseAddMenu(sc, "Sign in");

            if (selection == 1 && validateLogin(userid, password)) {
                loginSuccessful = checkUserIdAndPassword(userid, password);
            } else {
                Home.showMenu();
            }

            sc.nextLine();
        } while (!loginSuccessful);

        if (loggedInUserType.equalsIgnoreCase("A")) {
            Admin.adminUI();
        } else if (loggedInUserType.equalsIgnoreCase("B")) {
            Brand.brandUI();
        } else {
            Customer.customerUI();
        }
    }

    private static boolean validateLogin(String userid, String password) {
        boolean isValid = true;

        if (userid.trim().isEmpty()) {
            System.out.println("UserId can not be empty.");
            isValid = false;
        }

        if (password.trim().isEmpty()) {
            System.out.println("Password can not be empty.");
            isValid = false;
        }

        return isValid;
    }

    private static boolean checkUserIdAndPassword(String userid, String password) {
        boolean loginSuccessful = false;

        String sqlCred = "select usertype from users where userid =  '" + userid
                + "' and password='" + password + "'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                loggedInUserType = rs.getString("USERTYPE");
                loggedInUserId = userid;
                loginSuccessful = true;
            } else {
                System.out.println("Login credentials are incorrect. Please try again.");
            }
            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        return loginSuccessful;
    }

}
