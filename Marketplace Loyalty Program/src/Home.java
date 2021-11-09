import java.sql.*;
import java.util.*;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Home {

    public static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    //public static final String user = "sbasak4";
    //public static final String passwd = "abcd1234";

    //public static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    //public static final String user = "c##scott";
    //public static final String passwd = "tiger";

    public static Connection connection = null;
    public static Statement statement = null;

    public static void main(String[] args) {
        try {

            // Load the driver. This creates an instance of the driver
            // and calls the registerDriver method to make Oracle Thin
            // driver available to clients.

            Class.forName("oracle.jdbc.OracleDriver");
            Scanner sc = new Scanner(System.in);

            try {

                // Take the input of database credentials from user
                System.out.println("Enter database credentials:");
                System.out.print("Enter username:");
                String user = sc.nextLine();
                System.out.print("Enter password:");
                String passwd = sc.nextLine();

                // Get a connection from the first driver in the
                // DriverManager list that recognizes the URL jdbcURL
                connection = DriverManager.getConnection(jdbcURL, user, passwd);
                statement = connection.createStatement();

                System.out.println("\t\tWelcome to MarketPlace Loyalty Program.\n\n");
                showMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                exitProgram();
            }
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Shows the menu for home dashboard
     */

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);

        int selection = 1;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Show Queries");
            System.out.println("4. Exit");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();

                switch (selection) {
                    case 1:
                        Login.loginUI();
                        break;
                    case 2:
                        Signup.signUpUI();
                        break;
                    case 3:
                        ShowQuery.showQueryUI();
                        break;
                    case 4:
                        exitProgram();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                }
            } catch (Exception e) {
                System.out.println("You have entered a wrong option. Please choose again.");
                showMenu();
            }
        } while (selection != 4);
    }

    /**
     * Closes the open connections and exit the program
     */
    public static void exitProgram() {
        Utility.close(connection);
        Utility.close(statement);
        System.exit((0));
    }
}



