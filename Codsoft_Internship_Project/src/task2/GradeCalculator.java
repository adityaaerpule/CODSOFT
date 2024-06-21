package task2;

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int physics, chemistry, maths;

        // Input validation loop for Physics marks
        while (true) {
            System.out.print("Enter Physics marks (1-100): ");
            if (scanner.hasNextInt()) {
                physics = scanner.nextInt();
                if (physics >= 1 && physics <= 100) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Input validation loop for Chemistry marks
        while (true) {
            System.out.print("Enter Chemistry marks (1-100): ");
            if (scanner.hasNextInt()) {
                chemistry = scanner.nextInt();
                if (chemistry >= 1 && chemistry <= 100) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Input validation loop for Maths marks
        while (true) {
            System.out.print("Enter Maths marks (1-100): ");
            if (scanner.hasNextInt()) {
                maths = scanner.nextInt();
                if (maths >= 1 && maths <= 100) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Calculate total marks
        int totalMarks = physics + chemistry + maths;

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / 3.0;

        // Grade calculation based on average percentage and specified ranges
        char grade;
        if (averagePercentage >= 85) {
            grade = 'A';
        } else if (averagePercentage >= 70) {
            grade = 'B';
        } else if (averagePercentage >= 55) {
            grade = 'C';
        } else if (averagePercentage >= 40) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\nResults:");
        System.out.println("Total Marks: (" + totalMarks + "/300)");
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
