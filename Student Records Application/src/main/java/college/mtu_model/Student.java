package college.mtu_model;

/**
 * This class represents a student in a college.
 * It contains information about the student's name, ID, date of birth, and current semester.
 */
public class Student {

    private String studentName;
    private String studentId;
    private String dateOfBirth;
    private int currentSemester;

    /**
     * Constructs a new Student object with the given name, ID, date of birth, and current semester.
     *
     * @param studentName     The name of the student.
     * @param studentId       The ID of the student.
     * @param dateOfBirth     The date of birth of the student.
     * @param currentSemester The current semester of the student.
     */
    public Student(String studentName, String studentId, String dateOfBirth, int currentSemester) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.dateOfBirth = dateOfBirth;
        this.currentSemester = currentSemester;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    @Override
    public String toString() {

        return "Student Name: " + studentName +
                " | " +
                "Student ID: " + studentId +
                " | " +
                "DOB: " + dateOfBirth +
                " | " +
                "Current Semester: " + currentSemester +
                "\n";
    }
}
