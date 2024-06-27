package task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Student_Management {
    public static void main(String[] args) throws IOException {
        System.out.println("Hi, Welcome to the DASHBOARD.");
        System.out.println("");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Press 1 to ADD student.");
            System.out.println("Press 2 to DELETE student.");
            System.out.println("Press 3 to DISPLAY student.");
            System.out.println("Press 4 to EXIT app.");

            int choice = Integer.parseInt(br.readLine());

            if (choice == 1) {
                // add student
                System.out.println("Enter Student Name : ");
                String name = br.readLine();

                System.out.println("Enter Student Roll Number : ");
                int rollNumber = Integer.parseInt(br.readLine());

                System.out.println("Enter Student Grade : ");
                String grade = br.readLine();

                System.out.println("Enter Student City : ");
                String city = br.readLine();

                // create student object to store student
                Student st = new Student(name, rollNumber, grade, city);
                boolean answer = StudentDao.insertStudentToDb(st);
                if (answer) {
                    System.out.println("Student is added successfully !");
                } else {
                    System.out.println("Something went wrong. Try again");
                }
                System.out.println(st);

            } else if (choice == 2) {
                // delete student
                System.out.print("Enter Student ID to delete : ");
                int userId = Integer.parseInt(br.readLine());
                boolean answer = StudentDao.deleteStudent(userId);

                if (answer) {
                    System.out.println("Deleted....");
                } else {
                    System.out.println("Something went wrong.....");
                }

            } else if (choice == 3) {
                // display student
                StudentDao.displayStudents();

            } else if (choice == 4) {
                // exit app
                break;
            } else {
                System.out.println("Invalid option, please select a valid option.");
            }
        }
        System.out.println("Thank you for using my application.\nSee you soon.\n");
    }
}