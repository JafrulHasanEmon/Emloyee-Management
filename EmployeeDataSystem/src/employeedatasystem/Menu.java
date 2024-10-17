package employeedatasystem;

import java.util.*;

/**
 *
 * @author MD Jafrul Hasan ID: 12231045
 */
//Menu class contains methods for the menu 
public class Menu {

    Scanner input = new Scanner(System.in);  // Create a Scanner object

    EmployeeData employeeData = new EmployeeData();
//displayMenu method displayed the list of features

    public void displayMenu(int maxDigit) {
        int switchNumber = 0;
        do {
            // Prompt the user to enter a number
            System.out.println("1. Input & validate data");
            System.out.println("2. Display");
            System.out.println("3. Sort by name");
            System.out.println("4. Sort by hours ");
            System.out.println("5. Search by name");
            System.out.println("6. Search by hours");
            System.out.println("7. Statistics");
            System.out.println("8. Exit");

            System.out.print("Enter An option from (1-8): ");
            switchNumber = input.nextInt();
            // Use a switch statement to determine the select a process

            // Check for the exit condition
            if (switchNumber == 8) {
                System.out.println("Exiting the system. Goodbye!");
                break; // Exit the loop
            }
            // Validate that dayNumber is not 0
            if (switchNumber <= 0 || switchNumber > 8) {
                System.out.println("Options 1-8 must be entered");
            } else {
                String dayOfWeek = null;
                switch (switchNumber) {
                    case 1:
                        employeeData.inputData(maxDigit);
                        break;
                    case 2:
                        employeeData.displayEmployeeDetails();
                        break;
                    case 3:
                        employeeData.sortByName();
                        break;
                    case 4:
                        employeeData.sortByHours();
                        break;
                    case 5:
                        employeeData.searchByName();
                        break;
                    case 6:
                        employeeData.searchByHours();
                        break;
                    case 7:
                        employeeData.statistics();
                        break;
                }

            }
        } while (switchNumber != 8);
    }

}
