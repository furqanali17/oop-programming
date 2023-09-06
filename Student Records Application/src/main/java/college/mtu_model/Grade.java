package college.mtu_model;

/**
 * This class represents the grade of a module of a student in a college.
 * It contains information about the studentID, moduleCode, and grade.
 */
public class Grade {

    private Student studentId;
    private Module moduleCode;
    private float grade;

    /**
     * Constructs a new Grade object with the given student, module, and grade.
     *
     * @param studentId  The student who received this grade.
     * @param moduleCode The module for which this grade was received.
     * @param grade      The grade received by the student.
     */

    public Grade(Student studentId, Module moduleCode, float grade) {
        this.studentId = studentId;
        this.moduleCode = moduleCode;
        this.grade = grade;
    }

    public Grade(float grade) {
        this.grade = grade;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Module getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(Module moduleCode) {
        this.moduleCode = moduleCode;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {

        return "Student ID: " + studentId +
                " | " +
                "Module Code: " + moduleCode +
                " | " +
                "Grade: " + grade +
                "\n";
    }

    public String toString2() {

        return "Grade: " + grade +
                "\n";
    }
}
