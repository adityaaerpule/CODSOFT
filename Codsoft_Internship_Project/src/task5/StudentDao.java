package task5;

import java.sql.*;

public class StudentDao {

    // Method to insert a student into the database
    public static boolean insertStudentToDb(Student st) {
        boolean isSuccess = false;
        try {
            // Establish connection
            Connection con = Connection_provider.create_connection();

            // SQL query for inserting student details
            String query = "INSERT INTO students (sname, sroll, sgrade, scity) VALUES (?, ?, ?, ?)";

            // Prepare statement with the query
            PreparedStatement pstmt = con.prepareStatement(query);

            // Set the values for the prepared statement
            pstmt.setString(1, st.getStudentName());
            pstmt.setString(2, String.valueOf(st.getRollNumber()));
            pstmt.setString(3, st.getGrade());
            pstmt.setString(4, st.getStudentCity());

            // Execute the query
            pstmt.executeUpdate();

            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }


    // Method to delete a student from the database using student ID
    public static boolean deleteStudent(int userId) {
        boolean isDeleted = false;
        try {
            // Establish connection
            Connection con = Connection_provider.create_connection();

            // SQL query to delete student by ID
            String query = "DELETE FROM students WHERE id = ?";

            // Prepare statement with the query
            PreparedStatement pstmt = (PreparedStatement) con.createStatement(); // Use prepareStatement for better security

            // Set the ID value for the prepared statement
            pstmt.setInt(1, userId);

            // Execute the query
            pstmt.executeUpdate();

            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    // Method to display all students from the database
    public static void displayStudents() {
        try {
            // Establish connection
            Connection con = Connection_provider.create_connection();

            // SQL query to select all students
            String query = "SELECT * FROM students";

            // Create statement to execute the query
            Statement stmt = con.createStatement();

            // Execute the query and get the result set
            ResultSet resultSet = stmt.executeQuery(query);

            // Loop through the result set and display each student's details
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("sname");
                String rollNumber = resultSet.getString("sroll");  // Use correct column name
                String grade = resultSet.getString("sgrade");
                String city = resultSet.getString("scity");

                // Print the student details
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Roll Number: " + rollNumber);
                System.out.println("Grade: " + grade);
                System.out.println("City: " + city);
                System.out.println("------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
