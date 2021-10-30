import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Utility {
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Throwable tw) {
                System.out.println(tw.getMessage());
            }
        }
    }

    public static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Throwable tw) {
                System.out.println(tw.getMessage());
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable tw) {
                System.out.println(tw.getMessage());
            }
        }
    }

    public static int chooseAddMenu(Scanner sc, String menuText) {
        boolean flag = false;
        int selection = 2;

        do{
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. " + menuText);
            System.out.println("2. Go Back");
            System.out.print("Enter your options:");

            try {
                selection = sc.nextInt();
                if (selection != 1 && selection != 2) {
                    System.out.println("You have entered a wrong option. Please choose again.");
                } else
                {
                    flag = true;
                }
            } catch (Exception e) {
                System.out.println("Please choose between 1 and 2. Please choose again.");
                sc.next();
            }
        } while(!flag);

        return selection;
    }
}
