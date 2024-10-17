package employeedatasystem;

import java.util.*;

/**
 *
 * @author Jafrul Hasan, ID: 12231045
 */
//Employee class for individual employee data
class Employee {

    String name;
    int skillLevel;
    int hours;
    double wages;

    public Employee(String name, int skillLevel, int hours, double wages) {
        this.name = name;
        this.skillLevel = skillLevel;
        this.hours = hours;
        this.wages = wages;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }
}

//EmployeeData class contains the methods of all features
public class EmployeeData {

    Scanner input = new Scanner(System.in);  // Create a Scanner object

    // Arrays to store employee names and worked hours
    ArrayList<String> employeeNames = new ArrayList<>();
    ArrayList<Integer> skillLevels = new ArrayList<>();
    ArrayList<Integer> workedHours = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();

    //inputData method used for taking input
    public void inputData(int maxDigit) {
        String empName = null, fullName = null;
        int skillLevel, hours;
        if (employeeNames.size() < maxDigit) {
            for (int i = 1; i <= maxDigit; i++) {
                //  Employee name is entered
                do {
                    System.out.println("Enter the name of the employee " + i + ": ");
                    empName = validateEmployeeName(input);

                } while (empName == null);

                //  Skill level is entered
                do {
                    System.out.print("Enter the skill level (1, 2, 3) of the employee, " + empName + ": ");
                    while (!input.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        System.out.print("Enter the skill level (1, 2, 3) of the employee, " + empName + ": ");
                        input.next();
                    }

                    skillLevel = input.nextInt();
                    input.nextLine();
                    if (skillLevel < 1 || skillLevel > 3) {
                        System.out.println("Error! Skill level should be 1, 2, or 3.");
                    }

                } while (skillLevel < 1 || skillLevel > 3);

                //  hours is entered
                do {
                    System.out.print("Enter the number of hours worked by " + empName + ": ");

                    while (!input.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer for hours.");
                        System.out.print("Enter the number of hours worked by " + empName + ": ");

                        input.next();
                    }

                    hours = input.nextInt();
                    input.nextLine();
                    if (hours < 0 || hours > 50) {
                        System.out.println("Error! worked Hours must be between 0 and 50.");
                    }

                } while (hours < 0 || hours > 50);

                // Add employee name and worked hours to arrays
                employeeNames.add(empName);
                skillLevels.add(skillLevel);
                workedHours.add(hours);
            }
            System.out.println(maxDigit + " Employees data have been entered and stored in array");
            // Populate the list with data from ArrayLists
            for (int i = 0; i < employeeNames.size(); i++) {
                employees.add(new Employee(employeeNames.get(i), skillLevels.get(i), workedHours.get(i), calculatePay(skillLevels.get(i), workedHours.get(i))));
            }
        } else {
            System.out.println("You have used full size of array");
        }

    }

    // Method to validate employee name format
    private static String validateEmployeeName(Scanner scanner) {
        String fullName = scanner.nextLine().trim();
        String empName = null;
        // Split the input into words
        String[] nameParts = fullName.split("\\s+");
        // Check the number of words entered
        if (nameParts.length == 2) {
            // Two words entered, consider them as first name and last name
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            String middleName = ""; // Middle name is empty in this case
            empName = firstName + " " + middleName + " " + lastName;
            if (empName.length() > 25) {
                System.out.println("Invalid input. Name must not exceed 25 digit.");
                empName = null;
            }
        } else if (nameParts.length == 3) {
            // Three words entered, consider them as first name, middle name, and last name
            String firstName = nameParts[0];
            String middleName = nameParts[1];
            String lastName = nameParts[2];
            empName = firstName + " " + middleName + " " + lastName;
            if (empName.length() > 25) {
                System.out.println("Invalid input. Name must not exceed 25 digit.");
                empName = null;
            }
        } else {
            // Invalid input
            System.out.println("Invalid input. Please enter a valid name format[First, Middle, Last].");
        }

        return empName;
    }

    //displayEmployeeDetails method used for displaying the data
    public void displayEmployeeDetails() {

        // Display the input data
        System.out.println("\nEmployee Worked hours and wages:");
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.println("\nEmployee        Worked hours             wages:");
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < employeeNames.size(); i++) {
            System.out.println(employeeNames.get(i) + "               " + workedHours.get(i) + "           $" + calculatePay(skillLevels.get(i), workedHours.get(i)));
        }
    }
    //calculatePay method used for calculating the wages

    public double calculatePay(int skillLevel, int hours) {
        // Define hourly rates for each skill level
        double hourlyRate;
        switch (skillLevel) {
            case 1:
                hourlyRate = 50.0;
                break;
            case 2:
                hourlyRate = 70.0;
                break;
            case 3:
                hourlyRate = 100.0;
                break;
            default:
                System.out.println("Invalid skill level. Assuming hourly rate of $50.");
                hourlyRate = 50.0;
        }

        // Calculate regular pay (up to 40 hours)
        double regularPay = Math.min(40, hours) * hourlyRate;

        // Calculate overtime pay (for hours over 40)
        double overtimePay = Math.max(0, hours - 40) * 1.5 * hourlyRate;

        // Total pay is the sum of regular pay and overtime pay
        return regularPay + overtimePay;
    }

    //sortByName method used for displaying the sort data by name
    void sortByName() {
        // Sort the list based on names using a custom comparator
        // Perform insertion sort based on names
        for (int i = 1; i < employees.size(); i++) {
            Employee key = employees.get(i);
            int j = i - 1;

            // Move elements of the sorted part of the list to make space for the key
            while (j >= 0 && employees.get(j).getName().compareTo(key.getName()) > 0) {
                employees.set(j + 1, employees.get(j));
                j--;
            }

            // Insert the key into the correct position
            employees.set(j + 1, key);
        }

        // Display the sorted data
        // Display the input data
        System.out.println("Sorted Employee Data by Name:");
        System.out.println("----------------------------------------------------");
        System.out.println("\nEmployee Worked hours and wages:");
        System.out.println("==================================================");
        System.out.println("\nEmployee        Worked hours             wages:");
        System.out.println("----------------------------------------------------");
        for (Employee employee : employees) {
            System.out.println(employee.name + "            " + employee.hours + "               $" + employee.wages);
        }

    }

    //sortByHours method used for displaying the sort data by hours
    void sortByHours() {
        // Perform insertion sort based on worked hours
        for (int i = 1; i < employees.size(); i++) {
            Employee key = employees.get(i);
            int j = i - 1;

            // Move elements of the sorted part of the list to make space for the key
            while (j >= 0 && employees.get(j).getHours() > key.getHours()) {
                employees.set(j + 1, employees.get(j));
                j--;
            }

            // Insert the key into the correct position
            employees.set(j + 1, key);
        }

        // Display the sorted data
        System.out.println("Sorted Employee Data by Hours:");
        System.out.println("----------------------------------------------------");
        System.out.println("\nEmployee Worked hours and wages:");
        System.out.println("==================================================");
        System.out.println("\nEmployee        Worked hours             wages:");
        System.out.println("----------------------------------------------------");
        for (Employee employee : employees) {
            System.out.println(employee.name + "            " + employee.hours + "               $" + employee.wages);
        }

    }
    //searchByName method used for search the details from the list

    void searchByName() {
        // Search for an employee by name
        System.out.print("Please enter emoployee name: ");
        String searchName = input.nextLine().trim().replaceAll("\\s+", " "); // Replace multiple spaces with a single space

        boolean found = false;
        for (Employee employee : employees) {
            String employeeName = employee.getName().trim().replaceAll("\\s+", " ");

            if (employeeName.equalsIgnoreCase(searchName)) {
                System.out.println("Name: " + employee.name + "- Hours: " + employee.hours + " - Wages: $" + employee.wages);
                found = true;
                break; // Stop searching once the employee is found
            }
        }

        if (!found) {
            System.out.println("Employee not found with name: " + searchName);
        }

    }
    //searchByHours method used for search the details from the list those are belew that hour

    void searchByHours() {
        // Search for an employee by name
        System.out.print("Please enter the specific number of worked hours: ");
        int searchHours = input.nextInt();

        for (Employee employee : employees) {
            int employeeHours = employee.getHours();

            if (employeeHours < searchHours) {
                System.out.println("Name: " + employee.name + "  - Hours: " + employee.hours + "  - Wages: $" + employee.wages);

            }
        }

    }
    //statistics method uses for displaying the statistics of the data
    void statistics() {

        // Perform insertion sort based on worked hours
        for (int i = 1; i < employees.size(); i++) {
            Employee key = employees.get(i);
            int j = i - 1;

            // Move elements of the sorted part of the list to make space for the key
            while (j >= 0 && employees.get(j).getHours() > key.getHours()) {
                employees.set(j + 1, employees.get(j));
                j--;
            }

            // Insert the key into the correct position
            employees.set(j + 1, key);
        }
        // Find details of the lowest and highest worked hours
        Employee lowestHoursEmployee = employees.get(0);
        Employee highestHoursEmployee = employees.get(employees.size() - 1);

        // Sort the hours ArrayList
        for (int i = 1; i < workedHours.size(); i++) {
            int key = workedHours.get(i);
            int j = i - 1;

            // Move elements of the sorted part of the list to make space for the key
            while (j >= 0 && workedHours.get(j) > key) {
                workedHours.set(j + 1, workedHours.get(j));
                j--;
            }

            // Insert the key into the correct position
            workedHours.set(j + 1, key);
        }
        // Find the median value
        double median;

        if (workedHours.size() % 2 == 0) {
            // If the size is even, average the middle two values
            int middle1 = workedHours.get(workedHours.size() / 2 - 1);
            int middle2 = workedHours.get(workedHours.size() / 2);
            median = (double) (middle1 + middle2) / 2;
        } else {
            // If the size is odd, the median is the middle value
            median = workedHours.get(workedHours.size() / 2);
        }

        System.out.println("The employee has lowest worked hours:" + lowestHoursEmployee.name + ", " + lowestHoursEmployee.hours + " hours");

        System.out.println("The employee has highest worked hours:" + highestHoursEmployee.name + ", " + highestHoursEmployee.hours + " hours");

        System.out.println("The median value of worked hours: " + median);

    }
}
