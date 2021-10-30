import java.sql.*;
import java.util.*;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Admin {
    public static void adminUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Add brand");
            System.out.println("2. Add customer");
            System.out.println("3. Show brand's info");
            System.out.println("4. Show customer's info");
            System.out.println("5. Add activity type");
            System.out.println("6. Add reward type");
            System.out.println("7. Logout");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        addBrand();
                        break;
                    case 2:
                        addCustomer();
                        break;
                    case 3:
                        showBrandInfo();
                        break;
                    case 4:
                        showCustomerInfo();
                        break;
                    case 5:
                        addActivityType();
                        break;
                    case 6:
                        addRewardType();
                        break;
                    case 7:
                        adminLogout();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 7. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    public static void showCustomerInfo() {
        String customerUserId;
        int selection;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter customer userid:");
            customerUserId = sc.nextLine();

            selection = Utility.chooseAddMenu(sc, "showCustomerInfo");

            if (selection == 2) {
                adminUI();
            } else {
                try {
                    String sqlBrandSelect = "select * from customer where customerid =  " + customerUserId;

                    ResultSet rs = Home.statement.executeQuery(sqlBrandSelect);

                    if (rs.next()) {
                        String customerName = rs.getString("name");
                        String customerAddress = rs.getString("address");
                        String customerPhone = rs.getString("phone");

                        System.out.println("Customer UserId: " + customerUserId);
                        System.out.println("Customer Name: " + customerName);
                        System.out.println("Customer Address: " + customerAddress);
                        System.out.println("Customer Phone: " + customerPhone);
                    } else {
                        System.out.println("Customer can not be found. Please try again.");
                    }
                } catch (SQLException e) {
                    System.out.println("Customer can not be found. Please try again.");
                }
            }
            // Extra nextLine to consume the empty line.
            sc.nextLine();
        } while (selection != 2);
    }

    public static void showBrandInfo() {
        String brandUserId;
        int selection;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter brand userid:");
            brandUserId = sc.nextLine();

            selection = Utility.chooseAddMenu(sc, "showBrandInfo");

            if (selection == 2) {
                adminUI();
            } else {
                try {
                    String sqlBrandSelect = "select * from brand where brandid =  " + brandUserId;

                    ResultSet rs = Home.statement.executeQuery(sqlBrandSelect);

                    if (rs.next()) {
                        String brandName = rs.getString("name");
                        String brandAddress = rs.getString("address");

                        System.out.println("Brand UserId: " + brandUserId);
                        System.out.println("Brand Name: " + brandName);
                        System.out.println("Brand Address: " + brandAddress);
                    } else {
                        System.out.println("Brand can not be found. Please try again.");
                    }
                } catch (SQLException e) {
                    System.out.println("Brand can not be found. Please try again.");
                }
                // Extra nextLine to consume the empty line.
                sc.nextLine();
            }
        } while (selection != 2);
    }

    public static void addBrand() {
        String brandUserId, brandName, brandAddress;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter brand userid:");
        brandUserId = sc.nextLine();
        System.out.print("Enter brand name:");
        brandName = sc.nextLine();
        System.out.print("Enter brand address:");
        brandAddress = sc.nextLine();

        int selection = Utility.chooseAddMenu(sc, "addBrand");
        CallableStatement statement = null;

        if (selection == 2) {
            adminUI();
        } else {
            try {
                statement = Home.connection.prepareCall("{call admin_add_brand(?, ?, ?, ?)}");
                statement.setString(1, brandUserId);
                statement.setString(2, brandName);
                statement.setString(3, brandAddress);
                statement.registerOutParameter(4, Types.INTEGER);

                statement.execute();
                int ret = statement.getInt(4);

                if (ret == 0) {
                    System.out.println("Brand can not be added. Please try again.");
                } else {
                    System.out.println("Brand has been added successfully.");
                }

                statement.close();

                adminUI();
            } catch (SQLException e) {
                Utility.close(statement);
                System.out.println("Brand can not be added. Please try again.");
                adminUI();
            }
        }
    }

    public static void addCustomer() {
        String customerUserId, customerFName, customerLName, customerAddress, customerPhone;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer userid:");
        customerUserId = sc.nextLine();
        System.out.print("Enter customer first name:");
        customerFName = sc.nextLine();
        System.out.print("Enter customer last name:");
        customerLName = sc.nextLine();
        System.out.print("Enter customer address:");
        customerAddress = sc.nextLine();
        System.out.print("Enter customer phone:");
        customerPhone = sc.nextLine();

        int selection = Utility.chooseAddMenu(sc, "addCustomer");
        CallableStatement statement = null;

        if (selection == 2) {
            adminUI();
        } else {
            try {
                statement = Home.connection.prepareCall("{call admin_add_customer(?, ?, ?, ?, ?, ?)}");
                statement.setString(1, customerUserId);
                statement.setString(2, customerFName);
                statement.setString(3, customerLName);
                statement.setString(4, customerAddress);
                statement.setString(5, customerPhone);
                statement.registerOutParameter(6, Types.INTEGER);

                statement.execute();

                int ret = statement.getInt(6);
                if (ret == 0) {
                    System.out.println("Customer can not be added. Please try again.");
                } else {
                    System.out.println("Customer has been added successfully.");
                }

                statement.close();

                adminUI();
            } catch (SQLException e) {
                Utility.close(statement);
                System.out.println("Customer can not be added. Please try again.");
                adminUI();
            }
        }
    }

    public static void addActivityType() {
        String activityName, activityCode;
        int selection;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter activity code:");
            activityCode = sc.nextLine();
            System.out.print("Enter activity name:");
            activityName = sc.nextLine();

            selection = Utility.chooseAddMenu(sc, "addActivityType");

            if (selection == 2) {
                adminUI();
            } else {
                try {
                    PreparedStatement ps = Home.connection.prepareStatement("Insert into ActivityType (ACTIVITYCODE, ACTIVITYNAME) values (?,?)");
                    ps.setString(1, activityCode);
                    ps.setString(2, activityName);

                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Activity Type has been added successfully.");
                    } else {
                        System.out.println("Activity Type can not be added. Please try again.");
                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println("Activity Type already present. Please try again.");
                } catch(SQLException e)
                {
                    System.out.println("Activity Type can not be added. Please try again.");
                }
            }

            sc.nextLine();
        } while (selection != 2);
    }

    public static void addRewardType() {
        String rewardName, rewardCode;
        int selection;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter reward code:");
            rewardCode = sc.nextLine();
            System.out.print("Enter reward name:");
            rewardName = sc.nextLine();

            selection = Utility.chooseAddMenu(sc, "addRewardType");

            if (selection == 2) {
                adminUI();
            } else {
                try {
                    PreparedStatement ps = Home.connection.prepareStatement("Insert into RewardType (REWARDCODE, REWARDNAME) values (?,?)");
                    ps.setString(1, rewardCode);
                    ps.setString(2, rewardName);

                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Reward Type has been added successfully.");
                    } else {
                        System.out.println("Reward Type can not be added. Please try again.");
                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println("Reward Type already present. Please try again.");
                } catch(SQLException e)
                {
                    System.out.println("Reward Type can not be added. Please try again.");
                }
            }

            sc.nextLine();
        } while (selection != 2);
    }

    public static void adminLogout() {
        Home.showMenu();
    }
}
