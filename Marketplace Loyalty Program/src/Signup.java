import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * Defines the functionality of signup
 *
 * @author Setu Kumar Basak (sbasak4)
 */

public class Signup {
    /**
     * Shows the signup menus
     */
    public static void signUpUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Brand Sign-Up");
            System.out.println("2. Customer Sign-Up");
            System.out.println("3. Go Back");
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
                        Home.showMenu();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 3. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    /**
     * Defines the functionality of signing up brand
     */
    public static void addBrand() {
        String brandUserId, brandPassword, brandName, brandAddress;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter brand userid:");
        brandUserId = sc.nextLine();
        System.out.print("Enter brand password:");
        brandPassword = sc.nextLine();
        System.out.print("Enter brand name:");
        brandName = sc.nextLine();
        System.out.print("Enter brand address:");
        brandAddress = sc.nextLine();

        int selection = Utility.chooseAddMenu(sc, "Sign-up");
        CallableStatement statement = null;

        if (selection == 2 || (selection == 1 && !validateBrand(brandUserId, brandPassword, brandName, brandAddress))) {
            signUpUI();
        } else {
            try {
                statement = Home.connection.prepareCall("{call add_brand(?, ?, ?, ?, ?)}");
                statement.setString(1, brandUserId);
                statement.setString(2, brandPassword);
                statement.setString(3, brandName);
                statement.setString(4, brandAddress);
                statement.registerOutParameter(5, Types.INTEGER);

                statement.execute();
                int ret = statement.getInt(5);
                statement.close();

                if (ret == 0) {
                    System.out.println("Brand user id is already taken. Please try again.");
                    addBrand();
                } else {
                    System.out.println("Brand has been added successfully.");
                    Login.loginUI();
                }
            } catch (SQLException e) {
                Utility.close(statement);
                System.out.println("Brand can not be added. Please try again.");
                addBrand();
            }
        }
    }

    /**
     * Validates the brand information
     *
     * @param brandUserId   Brand user id
     * @param brandPassword Brand password
     * @param brandName     Brand name
     * @param brandAddress  Brand address
     * @return Returns true if the brand information is valid otherwise false
     */
    private static boolean validateBrand(String brandUserId, String brandPassword, String brandName, String brandAddress) {
        boolean isValid = true;

        if (brandUserId.trim().isEmpty()) {
            System.out.println("Brand UserId can not be empty.");
            isValid = false;
        }

        if (brandPassword.trim().isEmpty()) {
            System.out.println("Brand password can not be empty.");
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
     * Defines the functionality of signing up customer
     */
    public static void addCustomer() {
        String customerUserId, customerPassword, customerFName, customerLName, customerAddress, customerPhone;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer userid:");
        customerUserId = sc.nextLine();
        System.out.print("Enter customer password:");
        customerPassword = sc.nextLine();
        System.out.print("Enter customer first name:");
        customerFName = sc.nextLine();
        System.out.print("Enter customer last name:");
        customerLName = sc.nextLine();
        System.out.print("Enter customer address:");
        customerAddress = sc.nextLine();
        System.out.print("Enter customer phone:");
        customerPhone = sc.nextLine();

        int selection = Utility.chooseAddMenu(sc, "Sign-up");
        CallableStatement statement = null;

        if (selection == 2 || (selection == 1 && !validateCustomer(customerUserId, customerPassword, customerFName, customerLName, customerAddress, customerPhone))) {
            signUpUI();
        } else {
            try {
                statement = Home.connection.prepareCall("{call add_customer(?, ?, ?, ?, ?, ?, ?)}");
                statement.setString(1, customerUserId);
                statement.setString(2, customerPassword);
                statement.setString(3, customerFName);
                statement.setString(4, customerLName);
                statement.setString(5, customerAddress);
                statement.setString(6, customerPhone);
                statement.registerOutParameter(7, Types.INTEGER);

                statement.execute();
                int ret = statement.getInt(7);
                statement.close();

                if (ret == 0) {
                    System.out.println("Customer user id is already taken. Please try again.");
                    addCustomer();
                } else {
                    System.out.println("Customer has been added successfully.");
                    Login.loginUI();
                }
            } catch (SQLException e) {
                Utility.close(statement);
                System.out.println("Customer can not be added. Please try again.");
                addCustomer();
            }
        }
    }

    /**
     * Validates the customer information
     *
     * @param customerUserId   Customer user id
     * @param customerPassword Customer password
     * @param customerFName    Customer first name
     * @param customerLName    Customer last name
     * @param customerAddress  Customer address
     * @param customerPhone    Customer phone
     * @return Returns true if customer information is valid otherwise false
     */
    private static boolean validateCustomer(String customerUserId, String customerPassword, String customerFName, String customerLName, String customerAddress, String customerPhone) {
        boolean isValid = true;

        if (customerUserId.trim().isEmpty()) {
            System.out.println("Customer UserId can not be empty.");
            isValid = false;
        }

        if (customerPassword.trim().isEmpty()) {
            System.out.println("Customer password can not be empty.");
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
}
