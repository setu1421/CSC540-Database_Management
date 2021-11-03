import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

/**
 * @author Setu Kumar Basak (sbasak4)
 */

public class Tier {
    public static void tierUI() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean flag = false;

        do {
            System.out.println("Choose what operation you want to perform");
            System.out.println("1. Tiers Set up");
            System.out.println("2. Activity Types");
            System.out.println("3. Reward Types");
            System.out.println("4. Go Back");
            System.out.print("Enter your option:");

            try {
                selection = sc.nextInt();
                flag = true;

                switch (selection) {
                    case 1:
                        tierSetup();
                        break;
                    case 2:
                        ActivityType.activityTypeUI();
                        break;
                    case 3:
                        RewardType.rewardTypeUI();
                        break;
                    case 4:
                        LoyaltyProgram.loyaltyProgramUI();
                        break;
                    default:
                        System.out.println("You have entered a wrong option. Please choose again.");
                        flag = false;
                }

            } catch (Exception e) {
                System.out.println("Please choose between 1 and 4. Please choose again.");
                sc.next();
            }
        } while (!flag);
    }

    private static void tierSetup() {
        boolean tierCountOk = false;
        Scanner sc = new Scanner(System.in);
        int tiersCount = 0;

        do {
            System.out.print("Enter no. of tiers(minimum 1 and maximum 3): ");
            tiersCount = sc.nextInt();

            if (tiersCount > 0 && tiersCount < 4) {
                tierCountOk = true;
            } else {
                System.out.println("Please enter at least 1 tier and at most 3 tiers.");
            }
        } while (!tierCountOk);

        boolean tierFlag = false;

        String[] tierNames = new String[3];
        int[] tierPoints = new int[3], tierMultipliers = new int[3];


        do {
            String tierName = "";
            int tierPoint = 0, tierMultiplier = 1;

            for (int i = 0; i < tiersCount; i++) {
                sc.nextLine(); // Clear the scanner buffer

                System.out.print("Enter Tier" + (i + 1) + " Name:");
                tierName = sc.nextLine();
                if (i != 0) {
                    System.out.print("Enter Tier" + (i + 1) + " Points Required:");
                    tierPoint = sc.nextInt();
                    System.out.print("Enter Tier" + (i + 1) + " Points Multiplier:");
                    tierMultiplier = sc.nextInt();
                } else {
                    System.out.println("Points required will be 0 for base tier.");
                    System.out.println("Points multiplier will be 1 for base tier.");
                }

                tierNames[i] = tierName;
                tierPoints[i] = tierPoint;
                tierMultipliers[i] = tierMultiplier;
            }

            if (validateTierInput(tierNames, tierPoints, tierMultipliers, tiersCount)) {
                tierFlag = true;
            }
        } while (!tierFlag);

        int selection = Utility.chooseAddMenu(sc, "Set up");

        if (selection == 2) {
            tierUI();
        } else {
            saveTiers(tierNames, tierPoints, tierMultipliers, tiersCount);
            tierUI();
        }
    }

    private static void saveTiers(String[] tierNames, int[] tierPoints, int[] tierMultipliers, int tiersCount) {
        try {
            for (int i = 0; i < tiersCount; i++) {

                String sql = "Insert into TIER (TIERNAME, POINTS, MULTIPLIER, LPCODE) values (?,?,?,?)";
                PreparedStatement ps = Home.connection.prepareStatement(sql);
                ps.setString(1, tierNames[i]);
                ps.setInt(2, tierPoints[i]);
                ps.setInt(3, tierMultipliers[i]);
                ps.setString(4, LoyaltyProgram.lpCode);

                ps.executeUpdate();
            }

            System.out.println("Tiers information has been added successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Tiers name can not be same. Please try again.");
        } catch (SQLException e) {
            System.out.println("Tiers can not be added successfully. Please try again.");
        }
    }

    private static boolean validateTierInput(String[] tierNames, int[] tierPoints, int[] tierMultipliers, int tiersCount) {
        boolean isValid = true;
        for (int i = 0; i < tiersCount; i++) {
            if (tierNames[i].equals("")) {
                System.out.println("Tier" + (i + 1) + " name can not be empty.");
                isValid = false;
            }

            if (i != 0 && tierPoints[i] == 0) {
                System.out.println("Tier" + (i + 1) + " points can not be 0 except base tier.");
                isValid = false;
            }

            if (tierMultipliers[i] <= 0) {
                System.out.println("Tier" + (i + 1) + " multipliers can not be less than equal to 0.");
                isValid = false;
            }
        }

        return isValid;
    }
}