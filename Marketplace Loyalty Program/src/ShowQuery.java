import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class ShowQuery {
    public static void showQueryUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. List all customers that are not part of Brand02’s program.");
            System.out.println("2. List customers that have joined a loyalty program but have not participated in any activity in that program.");
            System.out.println("3. List the rewards that are part of Brand01 loyalty program.");
            System.out.println("4. List all the loyalty programs that include “refer a friend” as an activity in at least one of their reward rules.");
            System.out.println("5. For Brand01, list for each activity type in their loyalty program, the number instances that have occurred.");
            System.out.println("6. List customers of Brand01 that have redeemed at least twice.");
            System.out.println("7. All brands where total number of points redeemed overall is less than 500 points");
            System.out.println("8. For Customer C0003, and Brand02, number of activities they have done in the period of 08/1/2021 and 9/30/2021.");
            System.out.println("9. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        query1();
                        break;
                    case 2:
                        query2();
                        break;
                    case 3:
                        query3();
                        break;
                    case 4:
                        query4();
                        break;
                    case 5:
                        query5();
                        break;
                    case 6:
                        query6();
                    case 7:
                        query7();
                        break;
                    case 8:
                        query8();
                        break;
                    case 9:
                        Home.showMenu();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }
            } catch (Exception e) {
                System.out.println("Please choose between 1 and 9. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    private static void query1() {
        int count = 0;
        String sqlCred = "SELECT * FROM TABLE(show_query_1)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Customer ID: " + rs.getString("CUSTID"));
                System.out.println("Customer First Name: " + rs.getString("CUSTFNAME"));
                System.out.println("Customer Last Name: " + rs.getString("CUSTLNAME"));
                System.out.println();
                count++;
            }

            if (count == 0) {
                System.out.println("No Customer Found.");
            }

            rs.close();
        } catch (
                SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        showQueryUI();
    }

    private static void query2() {
        String sqlCred = "SELECT * FROM TABLE(show_query_2)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                while (rs.next()) {
                    //TODO
                }
            } else {
                System.out.println("No Customer Found.");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    private static void query3() {
        String sqlCred = "SELECT * FROM TABLE(show_query_3)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                while (rs.next()) {
                    //TODO
                }
            } else {
                System.out.println("No Reward Found.");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    private static void query4() {
        String sqlCred = "SELECT * FROM TABLE(show_query_4)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                while (rs.next()) {
                    //TODO
                }
            } else {
                System.out.println("No Loyalty Program Found.");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    private static void query5() {
        String sqlCred = "SELECT * FROM TABLE(show_query_5)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                while (rs.next()) {
                    //TODO
                }
            } else {
                System.out.println("No Activity Type Found.");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    private static void query6() {
        String sqlCred = "SELECT * FROM TABLE(show_query_6)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                while (rs.next()) {
                    //TODO
                }
            } else {
                System.out.println("No Customer Found.");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    private static void query7() {
        String sqlCred = "SELECT * FROM TABLE(show_query_7)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                while (rs.next()) {
                    //TODO
                }
            } else {
                System.out.println("No Brand Found.");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }

    private static void query8() {
        String sqlCred = "SELECT * FROM TABLE(show_query_8)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            if (rs.next()) {
                while (rs.next()) {
                    //TODO
                }
            } else {
                System.out.println("No Activity Found.");
            }

            rs.close();
        } catch (SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }
    }
}
