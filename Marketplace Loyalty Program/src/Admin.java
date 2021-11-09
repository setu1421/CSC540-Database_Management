import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Shows the admin dashboard menus and related functionalities
 *
 * @author Setu Kumar Basak (sbasak4)
 */

public class Admin {
    /**
     * Shows the admin menus
     */
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

    /**
     * Take customer user id as input and shows the customer info
     */
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
                    String sqlBrandSelect = "select * from customer where customerid = '" + customerUserId + "'";

                    ResultSet rs = Home.statement.executeQuery(sqlBrandSelect);

                    if (rs.next()) {
                        String customerFName = rs.getString("FNAME");
                        String customerLName = rs.getString("LNAME");
                        String customerAddress = rs.getString("ADDRESS");
                        String customerPhone = rs.getString("PHONENUMBER");

                        System.out.println("Customer UserId: " + customerUserId);
                        System.out.println("Customer First Name: " + customerFName);
                        System.out.println("Customer Last Name: " + customerLName);
                        System.out.println("Customer Phone: " + customerPhone);
                        System.out.println("Customer Address: " + customerAddress);
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

    /**
     * Take brand user id as input and shows the brand info
     */
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
                    String sqlBrandSelect = "select * from brand where bid = '" + brandUserId + "'";

                    ResultSet rs = Home.statement.executeQuery(sqlBrandSelect);

                    if (rs.next()) {
                        String brandName = rs.getString("BNAME");
                        String brandAddress = rs.getString("ADDRESS");
                        Date brandJoinDate = rs.getDate("JOINDATE");

                        System.out.println("Brand UserId: " + brandUserId);
                        System.out.println("Brand Name: " + brandName);
                        System.out.println("Brand Address: " + brandAddress);
                        System.out.println("Brand Join Date: " + brandJoinDate);
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

    /**
     * Take brand information and adds the brand in the marketplace
     */
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

        if (selection == 2 || (selection == 1 && !validateBrand(brandUserId, brandName, brandAddress))) {
            adminUI();
        } else {
            CallableStatement statement = null;
            try {
                statement = Home.connection.prepareCall("{call admin_add_brand(?, ?, ?, ?)}");
                statement.setString(1, brandUserId);
                statement.setString(2, brandName);
                statement.setString(3, brandAddress);
                statement.registerOutParameter(4, Types.INTEGER);

                statement.execute();
                int ret = statement.getInt(4);

                if (ret == 0) {
                    System.out.println("Brand user id is already taken. Please try again.");
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

    /**
     * Validates the brand information
     */
    private static boolean validateBrand(String brandUserId, String brandName, String brandAddress) {
        boolean isValid = true;

        if (brandUserId.trim().isEmpty()) {
            System.out.println("Brand UserId can not be empty.");
            isValid = false;
        }

        if (brandName.trim().isEmpty()) {
            System.out.println("Brand name can not be empty.");
            isValid = false;
        }

        if (brandAddress.trim().isEmpty()) {
            System.out.println("Brand address can not be empty.");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Take customer information and adds the customer in the marketplace
     */
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

        if (selection == 2 || (selection == 1 && !validateCustomer(customerUserId, customerFName, customerLName, customerAddress, customerPhone))) {
            adminUI();
        } else {
            CallableStatement statement = null;
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
                    System.out.println("Customer user id is already taken. Please try again.");
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

    /**
     * Validates the customer information
     */
    private static boolean validateCustomer(String customerUserId, String customerFName, String customerLName, String customerAddress, String customerPhone) {
        boolean isValid = true;

        if (customerUserId.trim().isEmpty()) {
            System.out.println("Customer UserId can not be empty.");
            isValid = false;
        }

        if (customerFName.trim().isEmpty()) {
            System.out.println("Customer first name can not be empty.");
            isValid = false;
        }

        if (customerLName.trim().isEmpty()) {
            System.out.println("Customer last name can not be empty.");
            isValid = false;
        }

        if (customerAddress.trim().isEmpty()) {
            System.out.println("Customer address can not be empty.");
            isValid = false;
        }

        if (customerPhone.trim().isEmpty()) {
            System.out.println("Customer phone can not be empty.");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Take activity type information and adds the activity type in the marketplace
     */
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

            if (selection == 2 || (selection == 1 && !validateActivityType(activityName, activityCode))) {
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
                } catch (SQLException e) {
                    System.out.println("Activity Type can not be added. Please try again.");
                }
            }

            sc.nextLine();
        } while (selection != 2);
    }

    /**
     * Validates the activity type information
     */
    private static boolean validateActivityType(String activityName, String activityCode) {
        boolean isValid = true;

        if (activityName.trim().isEmpty()) {
            System.out.println("Activity name can not be empty.");
            isValid = false;
        }

        if (activityCode.trim().isEmpty()) {
            System.out.println("Activity code can not be empty.");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Take reward type information and adds the reward type in the marketplace
     */
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

            if (selection == 2 || (selection == 1 && !validateRewardType(rewardName, rewardCode))) {
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
                } catch (SQLException e) {
                    System.out.println("Reward Type can not be added. Please try again.");
                }
            }

            sc.nextLine();
        } while (selection != 2);
    }

    /**
     * Validates the reward type information
     */
    private static boolean validateRewardType(String rewardName, String rewardCode) {
        boolean isValid = true;

        if (rewardName.trim().isEmpty()) {
            System.out.println("Reward name can not be empty.");
            isValid = false;
        }

        if (rewardCode.trim().isEmpty()) {
            System.out.println("Reward code can not be empty.");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Logs out admin and shows the home dashboard
     */
    public static void adminLogout() {
        Home.showMenu();
    }
}
