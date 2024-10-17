package employeedatasystem;

import java.util.*;

/**
 *
 * @author MD Jafrul Hasan ID: 12231045
 */
//EmployeeDataSystem is the driver class of this project 
public class EmployeeDataSystem {

    //This method will return the largest digit of a student id
    public int getMaxDigit(int studentID) {
        int largest = 0;

        while (studentID != 0) {
            int r = studentID % 10;

            // Find the largest digit
            largest = Math.max(r, largest);

            studentID = studentID / 10;
        }
        return largest;
    }
    //Main method of the program where the program starts

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        EmployeeDataSystem empDataSys = new EmployeeDataSystem();
        Menu menu = new Menu();
        int studentID = 0, maxDigit = 0;
        System.out.print("Enter your ID and the system will find out the largest digit in your ID>");
        studentID = input.nextInt();
        maxDigit = empDataSys.getMaxDigit(studentID);
        System.out.println("Largest digit in your ID: " + maxDigit);
        System.out.println();
        System.out.println("         Employee Data Management System     ");
        System.out.println("------------------------------------------------------");
        menu.displayMenu(maxDigit);

    }

}
