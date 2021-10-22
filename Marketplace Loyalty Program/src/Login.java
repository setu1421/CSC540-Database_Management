import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Login {
    public static String userType = "";

    public static void loginUI() {
        Scanner sc = new Scanner(System.in);
        boolean loginSuccessful = false;

        do {
            System.out.print("Enter your userid:");
            String userid = sc.nextLine();
            System.out.print("Enter your password:");
            String password = sc.nextLine();

            int selection = Utility.chooseAddMenu(sc, "Sign in");

            if (selection == 1) {
                loginSuccessful = checkUserIdAndPassword(userid, password);
            } else {
                Home.showMenu();
            }

            sc.nextLine();
        } while (!loginSuccessful);

        if (userType.equals("A")) {
            Admin.adminUI();
        } else if(userType.equals("B"))
        {
            //TODO: go to Brand dashboard
        } else
        {
            //TODO: go to Customer dashboard
        }

    }

    private static boolean checkUserIdAndPassword(String userid, String password) {
        boolean loginSuccessful = false;

        String sqlCred = "select usertype from users where userid =  '" + userid
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
            Utility.close(rs);
            e.printStackTrace();
        }

        return loginSuccessful;
    }

}
