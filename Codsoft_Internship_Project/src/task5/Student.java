package task5;

public class Student {
    private int studentID; // Assuming you still use studentID
    private String studentName;
    private int rollNumber;
    private String grade;
    private String studentCity;

    public Student(int studentID, String studentName, int rollNumber, String grade, String studentCity) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.studentCity = studentCity;
    }

    public Student(String studentName, int rollNumber, String grade, String studentCity) {
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.studentCity = studentCity;
    }

    // Getters and setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

    @Override
    public String toString() {
        return "Student [studentID=" + studentID + ", studentName=" + studentName + ", rollNumber=" + rollNumber
                + ", grade=" + grade + ", studentCity=" + studentCity + "]";
    }
}