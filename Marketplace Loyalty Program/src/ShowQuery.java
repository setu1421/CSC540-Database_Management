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
        String sqlCred = "SELECT OC.CUSTOMERID CUSTID, OC.FNAME CUSTFNAME, OC.LNAME CUSTLNAME\n" +
                "FROM CUSTOMER OC\n" +
                "WHERE OC.CUSTOMERID NOT IN (\n" +
                "SELECT C.CUSTOMERID\n" +
                "FROM CUSTOMER C\n" +
                "INNER JOIN ENROLLP E\n" +
                "ON E.CUSTOMERID = C.CUSTOMERID\n" +
                "INNER JOIN LOYALTYPROGRAM L\n" +
                "ON L.LPCODE = E.LPCODE\n" +
                "WHERE L.BRANDID = 'Brand02')";

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
        int count = 0;
        String sqlCred = "SELECT E.CUSTOMERID CUSTID, E.LPCODE LCODE\n" +
                "FROM ENROLLP E\n" +
                "INNER JOIN LOYALTYPROGRAM L\n" +
                "ON E.LPCODE = L.LPCODE\n" +
                "WHERE NOT EXISTS (SELECT SER FROM WALLETRE WE WHERE WE.CUSTOMERID = E.CUSTOMERID AND WE.BID = L.BRANDID)\n" +
                "AND NOT EXISTS (SELECT SER FROM WALLETRR WR WHERE WR.CUSTOMERID = E.CUSTOMERID AND WR.BID = L.BRANDID)\n" +
                "ORDER BY E.CUSTOMERID";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Customer ID: " + rs.getString("CUSTID"));
                System.out.println("Loyalty Program ID: " + rs.getString("LCODE"));
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

    private static void query3() {
        int count = 0;
        String sqlCred = "SELECT R.REWARDCODE RCODE, R.REWARDNAME RNAME\n" +
                "FROM BRANDREWARDTYPE BR \n" +
                "INNER JOIN REWARDTYPE R\n" +
                "ON R.REWARDCODE = BR.REWARDCODE\n" +
                "WHERE BR.BRANDID = 'Brand01'";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Reward Code: " + rs.getString("RCODE"));
                System.out.println("Reward Name: " + rs.getString("RNAME"));
                System.out.println();
                count++;
            }

            if (count == 0) {
                System.out.println("No Reward Found.");
            }

            rs.close();
        } catch (
                SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        showQueryUI();
    }

    private static void query4() {
        int count = 0;
        String sqlCred = "SELECT L.LPCODE LCODE, L.LPNAME LNAME\n" +
                "FROM LOYALTYPROGRAM L\n" +
                "WHERE EXISTS (SELECT RE.BRANDID FROM RERULE RE WHERE RE.BRANDID = L.BRANDID AND RE.ACTIVITYCODE = 'A03')";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Loyalty Program Code: " + rs.getString("LCODE"));
                System.out.println("Loyalty Program Name: " + rs.getString("LNAME"));
                System.out.println();
                count++;
            }

            if (count == 0) {
                System.out.println("No Loyalty Program Found.");
            }

            rs.close();
        } catch (
                SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        showQueryUI();
    }

    private static void query5() {
        int count = 0;
        String sqlCred = "SELECT BA.ACTIVITYCODE ACODE, A.ACTIVITYNAME ANAME, COUNT(SER) CNT \n" +
                "FROM BRANDACTIVITYTYPE BA\n" +
                "INNER JOIN ACTIVITYTYPE A\n" +
                "ON A.ACTIVITYCODE = BA.ACTIVITYCODE\n" +
                "INNER JOIN WALLETRE WE\n" +
                "ON WE.BID = BA.BRANDID AND BA.ACTIVITYCODE = WE.ACTIVITYCODE\n" +
                "WHERE BA.BRANDID = 'Brand01'\n" +
                "GROUP BY BA.ACTIVITYCODE, A.ACTIVITYNAME";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Activity Code: " + rs.getString("ACODE"));
                System.out.println("Activity Name: " + rs.getString("ANAME"));
                System.out.println("No. of Instances: " + rs.getString("CNT"));
                System.out.println();
                count++;
            }

            if (count == 0) {
                System.out.println("No Activity Code Found.");
            }

            rs.close();
        } catch (
                SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        showQueryUI();
    }

    private static void query6() {
        int count = 0;
        String sqlCred = "SELECT E.CUSTOMERID AS CUSID, C.FNAME FNAME, C.LNAME LNAME\n" +
                "FROM ENROLLP E\n" +
                "INNER JOIN LOYALTYPROGRAM L\n" +
                "ON E.LPCODE = L.LPCODE\n" +
                "INNER JOIN CUSTOMER C\n" +
                "ON C.CUSTOMERID = E.CUSTOMERID\n" +
                "WHERE L.BRANDID = 'Brand01' \n" +
                "AND 2 <= (SELECT COUNT(WR.SER) FROM WALLETRR WR WHERE WR.BID = 'Brand01' AND WR.CUSTOMERID = E.CUSTOMERID)";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Customer ID: " + rs.getString("CUSID"));
                System.out.println("Customer First Name: " + rs.getString("FNAME"));
                System.out.println("Customer Last Name: " + rs.getString("LNAME"));
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

    private static void query7() {
        int count = 0;
        String sqlCred = "SELECT WR.BID BRANDID, B.BNAME BRANDNAME, SUM(POINTSREEDEMED) TOTPOINTS\n" +
                "FROM WALLETRR WR\n" +
                "INNER JOIN BRAND B\n" +
                "ON B.BID = WR.BID\n" +
                "GROUP BY WR.BID, B.BNAME\n" +
                "HAVING SUM(POINTSREEDEMED) < 500";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Brand ID: " + rs.getString("BRANDID"));
                System.out.println("Brand Name: " + rs.getString("BRANDNAME"));
                System.out.println("Points Redeemed: " + rs.getString("TOTPOINTS"));
                System.out.println();
                count++;
            }

            if (count == 0) {
                System.out.println("No Brand Found.");
            }

            rs.close();
        } catch (
                SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        showQueryUI();
    }

    private static void query8() {
        int count = 0;
        String sqlCred = "SELECT COUNT(SER) AS CNT\n" +
                "FROM\n" +
                "(\n" +
                "SELECT SER\n" +
                "FROM WALLETRE\n" +
                "WHERE CUSTOMERID = 'C0003' AND BID = 'Brand02' \n" +
                "AND DATEOFACTIVITY BETWEEN TO_DATE('01/08/2021', 'DD/MM/YYYY') AND TO_DATE('30/09/2021', 'DD/MM/YYYY')\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT SER \n" +
                "FROM WALLETRR\n" +
                "WHERE CUSTOMERID = 'C0003' AND BID = 'Brand02' \n" +
                "AND DATEOFACTIVITY BETWEEN TO_DATE('01/08/2021', 'DD/MM/YYYY') AND TO_DATE('30/09/2021', 'DD/MM/YYYY')\n" +
                ")";

        ResultSet rs = null;
        try {
            rs = Home.statement.executeQuery(sqlCred);
            while (rs.next()) {
                System.out.println("Total Activities: " + rs.getInt("CNT"));
                System.out.println();
                count++;
            }

            if (count == 0) {
                System.out.println("No Activities Found.");
            }

            rs.close();
        } catch (
                SQLException e) {
            Utility.close(rs);
            e.printStackTrace();
        }

        showQueryUI();
    }
}
