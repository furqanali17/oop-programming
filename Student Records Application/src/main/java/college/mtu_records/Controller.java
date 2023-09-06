package college.mtu_records;

import college.mtu_database.Database;
import college.mtu_model.Grade;
import college.mtu_model.Module;
import college.mtu_model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * The Controller class manages the logic for the student record system.
 * <p>
 * It holds references to the view, student, module, grade and connection objects.
 * <p>
 * It also maintains lists for students, modules, grades, and list box components.
 */
public class Controller {

    //instance variables
    private View view;
    private Student student;
    private Module module;
    private Grade grade;
    private Statement statement;
    private Connection connection;


    //list box components for displaying records
    protected TextArea list1, list2, list3, list4;

    //lists for managing records
    protected ArrayList<Student> studentsList = new ArrayList<>();
    protected ArrayList<Module> modulesList = new ArrayList<>();
    protected ArrayList<String> gradesList = new ArrayList<>();
    protected ArrayList<String> studentListCombo = new ArrayList<>();
    protected ArrayList<String> moduleListCombo = new ArrayList<>();
    protected ObservableList<String> observableList = FXCollections.observableArrayList(studentListCombo);
    protected ObservableList<String> observableListModule = FXCollections.observableArrayList(moduleListCombo);

    /**
     * Constructor that sets the view and establishes a database connection.
     *
     * @param v The view to be associated with the controller.
     */
    public Controller(View v) {
        this.view = v;
        Database database = new Database();
        this.connection = database.getConnection();
        try {
            if (connection != null) {
                statement = connection.createStatement();
            } else {
                System.err.println("Error: Connection is null. Cannot create statement.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Default constructor for the Controller class.
     * Made to use in tests.
     */
    public Controller() {}

    /**
     * Validates if the given student name is not empty.
     *
     * @param name The student name to validate.
     * @return A validation error message if the name is empty, otherwise null.
     */
    public String validateStudentName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Student name cannot be empty.";
        }
        //null indicates student name is valid
        return null;
    }

    /**
     * Validates if the given module name is not empty.
     *
     * @param name The module name to validate.
     * @return A validation error message if the name is empty, otherwise null.
     */
    public String validateModuleName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Module name cannot be empty.";
        }
        //null indicates module name is valid
        return null;
    }

    /**
     * Validates if the given student ID is not empty and follows the required format.
     *
     * @param id The student ID to validate.
     * @return A validation error message if the ID is empty or does not follow the required format, otherwise null.
     */
    public String validateStudentId(String id) {
        if (id == null || id.isEmpty()) {
            return "Student ID cannot be empty.";
        }
        if (!id.contains("R00") || id.length() != 9) {
            return "Invalid student ID. It must start with 'R00' and be 9 characters long.";
        }
        //null indicates student ID is valid
        return null;
    }

    /**
     * Validates if the given module code is not empty and follows the required format.
     *
     * @param code The module code to validate.
     * @return A validation error message if the code is empty or does not follow the required format, otherwise null.
     */
    public String validateModuleCode(String code) {
        if (code == null || code.isEmpty()) {
            return "Module code cannot be empty.";
        }
        if (!code.contains("MT") || code.length() != 5) {
            return "Invalid module code. It must start with 'MT' and be 5 characters long.";
        }
        //null indicates module code is valid
        return null;
    }

    /**
     * Validates if the given grade is within the acceptable range (0-100).
     *
     * @param grade The grade to validate.
     * @return true if the grade is valid, otherwise false.
     */
    public boolean validateGrade(double grade) {
        return grade >= 0 && grade <= 100;
    }

    /**
     * Validates if the given date of birth is in the correct format and follows the age requirements.
     *
     * @param dob The date of birth to validate.
     * @return A validation error message if the date format is incorrect or the age is not within the required range, otherwise null.
     */
    public String validateDob(String dob) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(dob, dateFormatter);

            //check if the date is not after the current local date
            if (parsedDate.isAfter(LocalDate.now())) {
                return "Date of birth cannot be greater than today's date.";
            }

            //calculate the age based on the date of birth
            int age = Period.between(parsedDate, LocalDate.now()).getYears();

            //ensure the minimum age is 16 years
            if (age < 16) {
                return "Student must be at least 16 years old.";
            }
            //null indicates date of birth is valid
            return null;
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use the format DD/MM/YYYY.";
        }
    }

    /**
     * Validates if the given semester value is greater than 10.
     *
     * @param semester The semester to validate.
     * @return true if the semester is valid, otherwise false.
     */
    public boolean validateSemester(int semester) {
        return (semester > 10);
    }

    /**
     * Adds a new student to the ArrayList and the database if the provided information is valid.
     * It validates the input data, displays a warning message if there are any errors, and adds
     * the student to the database if all input data is valid.
     *
     * @param name       The TextField containing the student's name.
     * @param id         The TextField containing the student's ID.
     * @param dob        The TextField containing the student's date of birth.
     * @param currentSem The TextField containing the student's current semester.
     */
    public void addStudent(TextField name, TextField id, TextField dob, TextField currentSem) {
        String nameError = validateStudentName(name.getText());
        String dobError = validateDob(dob.getText());

        boolean semesterError;
        try {
            semesterError = validateSemester(Integer.parseInt(currentSem.getText()));
        } catch (NumberFormatException e) {
            semesterError = true;
        }

        String studentIdError = validateStudentId(id.getText());

        if (nameError != null || dobError != null || studentIdError != null || semesterError) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete Information");

            //create all error messages
            StringBuilder errorMessage = new StringBuilder();
            if (nameError != null) {
                errorMessage.append(nameError).append("\n");
            }
            if (dobError != null) {
                errorMessage.append(dobError).append("\n");
            }
            if (studentIdError != null) {
                errorMessage.append("Invalid student ID. It must start with 'R00' and be 9 characters long.").append("\n");
            }
            if (semesterError) {
                errorMessage.append("Invalid semester. It must be between 1 and 10.").append("\n");
            }

            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        } else {
            student = new Student(name.getText(), id.getText(), dob.getText(), Integer.parseInt(currentSem.getText()));
            String sqlAdd = "insert into student " + "values(?,?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, student.getStudentName());
                preparedStatement.setString(2, student.getStudentId());
                preparedStatement.setString(3, student.getDateOfBirth());
                preparedStatement.setInt(4, student.getCurrentSemester());

                preparedStatement.executeUpdate();
                comboBox();
                list1.setText("New student added");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes a student from the database if the provided student ID is valid.
     * It validates the input student ID, displays a warning message if there are any errors,
     * and removes the student from the database if the input student ID is valid.
     *
     * @param id The TextField containing the student's ID to be removed.
     */
    public void removeStudent(TextField id) {
        String studentIdError = validateStudentId(id.getText());

        if (studentIdError != null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText(studentIdError);
            alert.showAndWait();
        } else {
            try {
                String sqlRemoveGrade = "DELETE FROM grade WHERE studentId = ?";
                String sqlRemoveStudent = "DELETE FROM student WHERE studentId = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveGrade, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, id.getText());

                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(sqlRemoveStudent, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, id.getText());

                preparedStatement.executeUpdate();
                list1.setText("Existing student removed");
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error removing student");
                alert.setContentText("An error occurred while trying to remove the student: " + e.getMessage());
                alert.showAndWait();
            }
            comboBox();
        }
    }

    /**
     * Lists all students from the database in the list box.
     * Fetches all student records from the database, adds them to the studentsList ArrayList,
     * and displays them in the list box (list1).
     */
    public void listStudent() {
        studentsList.clear();

        String sqlSelectStudents = "select * from student";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectStudents);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder studentListBuilder = new StringBuilder();
            while (resultSet.next()) {
                student = new Student(resultSet.getString("name"), resultSet.getString("studentId"),
                        resultSet.getString("dateOfBirth"), resultSet.getInt("currentSemester"));
                studentsList.add(student);

                //append the student to the list without adding a newline character
                studentListBuilder.append(student);
            }

            list1.setText(studentListBuilder.toString());
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
    }

    /**
     * Exits the program after prompting the user to save their changes.
     * Displays a confirmation alert with custom "Yes" and "No" buttons.
     * If the user selects "Yes", the program will exit without saving.
     */
    public void exit() {
        //create custom buttons for "yes" or "no"
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        //alert that pops up when exiting
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit!");
        alert.setContentText("Are you sure you want to exit?");
        alert.getButtonTypes().setAll(yesButton, noButton); // Set the custom buttons for the alert

        Optional<ButtonType> option = alert.showAndWait();

        //if condition for when "no" is pressed which closes the program
        if (option.isPresent() && option.get() == yesButton) {
            System.exit(0);
        }
    }

    /**
     * Submits a grade for a selected student and module.
     * Validates that both a student and module have been selected and a grade has been entered.
     * If any input is missing or invalid, a warning alert is displayed.
     * If the grade for the selected student and module already exists, the grade is updated;
     * otherwise, a new grade entry is created.
     *
     * @param selectedStudent The student ID of the selected student.
     * @param selectedModule  The module code of the selected module.
     * @param gradeInput      The TextField containing the input grade.
     */
    public void submit(String selectedStudent, String selectedModule, TextField gradeInput) {
        if (selectedStudent == null || selectedModule == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Selection Missing");
            alert.setContentText("Please select both a student and a module before submitting.");
            alert.showAndWait();
        } else if (gradeInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Grade");
            alert.setContentText("Please enter a grade before submitting.");
            alert.showAndWait();
        } else {
            try {
                double grade = Double.parseDouble(gradeInput.getText());
                if (!validateGrade(grade)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Invalid Grade");
                    alert.setContentText("Please enter a valid grade between 0 and 100.");
                    alert.showAndWait();
                } else {
                    try {
                        String sqlCheckDuplicate = "select count(*) from grade where studentId=? and moduleCode=?";
                        PreparedStatement checkDuplicateStatement = connection.prepareStatement(sqlCheckDuplicate);
                        checkDuplicateStatement.setString(1, selectedStudent);
                        checkDuplicateStatement.setString(2, selectedModule);
                        ResultSet resultSet = checkDuplicateStatement.executeQuery();
                        resultSet.next();
                        int count = resultSet.getInt(1);

                        if (count > 0) {
                            String sqlUpdateGrade = "update grade set grade=? where studentId=? and moduleCode=?";
                            PreparedStatement updateStatement = connection.prepareStatement(sqlUpdateGrade);
                            updateStatement.setDouble(1, grade);
                            updateStatement.setString(2, selectedStudent);
                            updateStatement.setString(3, selectedModule);
                            updateStatement.executeUpdate();
                            list3.setText("Grade for Module with module code " + selectedModule + " changed to " + grade);
                        } else {
                            String sqlAddGrade = "insert into grade values(?,?,?)";
                            PreparedStatement addStatement = connection.prepareStatement(sqlAddGrade);
                            addStatement.setString(1, selectedStudent);
                            addStatement.setString(2, selectedModule);
                            addStatement.setDouble(3, grade);
                            addStatement.executeUpdate();
                            list3.setText("Grade for Module with module code " + selectedModule + " added as " + grade);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid Grade");
                alert.setContentText("Please enter a valid grade between 0 and 100.");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes a grade entry for the specified student ID and module code.
     * If the student ID or module code is not provided, a warning alert is displayed.
     * If the specified grade does not exist, a warning alert is displayed.
     *
     * @param id     The student ID.
     * @param module The module code.
     */
    public void delete(String id, String module) {
        if (id == null || id.trim().isEmpty() || module == null || module.trim().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Please provide both the student ID and module code before deleting a grade.");
            alert.showAndWait();
        } else {
            String checkGradeExists = "select * from grade where studentId = ? and moduleCode = ?";
            String deleteGrade = "delete from grade where studentId = ? and moduleCode = ?";
            try {
                PreparedStatement checkStatement = connection.prepareStatement(checkGradeExists, Statement.RETURN_GENERATED_KEYS);
                checkStatement.setString(1, id);
                checkStatement.setString(2, module);
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next()) {
                    PreparedStatement deleteStatement = connection.prepareStatement(deleteGrade, Statement.RETURN_GENERATED_KEYS);
                    deleteStatement.setString(1, id);
                    deleteStatement.setString(2, module);
                    deleteStatement.executeUpdate();
                    list3.setText("Grade deleted for selected module");
                    view.gradeInput.clear();
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Grade not found");
                    alert.setContentText("There is no grade for the specified student ID and module. Please add a grade before deleting.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Populates the student ComboBox with student IDs and names.
     * Fetches the students from the database and updates the observable list
     * with the student ID and name in a formatted string.
     */
    public void comboBox() {
        studentListCombo.clear();

        String sqlStudent = "select studentId, name from student";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStudent);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                studentListCombo.add(resultSet.getString("studentId") + " | " + resultSet.getString("name"));
            }

            observableList.setAll(studentListCombo);
        } catch (SQLException e) {
            System.err.println("Error fetching students for the combo box: " + e.getMessage());
        }
    }

    /**
     * Populates the module ComboBox with module codes and names based on the state.
     * If the state is true, all modules are fetched from the database.
     * If the state is false, only modules that are available for the specified student ID are fetched.
     *
     * @param id    The student ID.
     * @param state The state indicating whether to fetch all modules or only available modules for the student.
     */
    public void comboBox_2(String id, boolean state) {
        moduleListCombo.clear();
        String sqlModule;

        if (state) {
            sqlModule = "select moduleCode, moduleName from module";
        } else {
            sqlModule = "select name, currentSemester, module.semester, module.moduleCode, module.moduleName from student join module where student.currentSemester >= module.semester and student.studentId = ?";
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlModule);

            if (!state) {
                preparedStatement.setString(1, id);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                moduleListCombo.add(resultSet.getString("moduleCode") + " | " + resultSet.getString("moduleName"));
            }

            observableListModule.setAll(moduleListCombo);
            System.out.println(moduleListCombo);
        } catch (SQLException e) {
            System.err.println("Error fetching modules for the combo box: " + e.getMessage());
        }
    }

    /**
     * Checks if a grade exists for the specified student and module.
     * If the grade exists, it sets the grade input field with the fetched grade.
     * If the grade does not exist, it clears the grade input field.
     *
     * @param selectedStudent The selected student ID.
     * @param selectedModule  The selected module code.
     */
    public void modifyCheck(String selectedStudent, String selectedModule) {
        String sqlModifyCheck = "select grade from grade where studentId = ? and moduleCode = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlModifyCheck);
            preparedStatement.setString(1, selectedStudent);
            preparedStatement.setString(2, selectedModule);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                view.gradeInput.setText(resultSet.getString("grade"));
            } else {
                view.gradeInput.setText("");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching grade: " + e.getMessage());
        }
    }

    /**
     * Adds a module to the database with the provided name, code, and current semester.
     * Validates the module name, code, and semester before adding.
     * If there are any validation errors, a warning alert is displayed.
     *
     * @param name       The module name TextField.
     * @param code       The module code TextField.
     * @param currentSem The current semester TextField.
     */
    public void addModule(TextField name, TextField code, TextField currentSem) {
        String nameError = validateModuleName(name.getText());
        String codeError = validateModuleCode(code.getText());

        boolean semesterError;
        try {
            semesterError = validateSemester(Integer.parseInt(currentSem.getText()));
        } catch (NumberFormatException e) {
            semesterError = true;
        }

        if (nameError != null || codeError != null || semesterError) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete Information");

            //create all error messages
            StringBuilder errorMessage = new StringBuilder();
            if (nameError != null) {
                errorMessage.append(nameError).append("\n");
            }
            if (codeError != null) {
                errorMessage.append(codeError).append("\n");
            }
            if (semesterError) {
                errorMessage.append("Invalid semester. It must be between 1 and 10.").append("\n");
            }

            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        } else {
            module = new Module(name.getText(), code.getText(), Integer.parseInt(currentSem.getText()));
            String sqlAdd = "insert into module " + "values(?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, module.getModuleName());
                preparedStatement.setString(2, module.getModuleCode());
                preparedStatement.setInt(3, module.getSemester());

                preparedStatement.executeUpdate();
                list2.setText("New module added");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes a module from the database with the provided module code.
     * Validates the module code before removing the module.
     * If there are any validation errors, a warning alert is displayed.
     *
     * @param code The module code TextField.
     */
    public void removeModule(TextField code) {
        String moduleCodeError = validateModuleCode(code.getText());

        if (moduleCodeError != null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText(moduleCodeError);
            alert.showAndWait();
        } else {
            try {
                String sqlRemoveGrade = "DELETE FROM grade WHERE moduleCode = ?";
                String sqlRemoveModule = "DELETE FROM module WHERE moduleCode = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveGrade, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, code.getText());

                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(sqlRemoveModule, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, code.getText());

                preparedStatement.executeUpdate();
                list2.setText("Existing module removed");
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error removing module");
                alert.setContentText("An error occurred while trying to remove the module: " + e.getMessage());
                alert.showAndWait();
            }
            comboBox();
        }
    }

    /**
     * Retrieves the list of modules from the database and displays them in the 'list2' TextArea.
     * The list of modules is cleared before fetching the updated list from the database.
     */
    public void listModule() {
        modulesList.clear();

        String sqlSelectModules = "SELECT * FROM module";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectModules);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder moduleListBuilder = new StringBuilder();
            while (resultSet.next()) {
                module = new Module(resultSet.getString("moduleName"), resultSet.getString("moduleCode"),
                        resultSet.getInt("semester"));
                modulesList.add(module);

                //append the module to the list without adding a newline character
                moduleListBuilder.append(module);
            }

            list2.setText(moduleListBuilder.toString());
        } catch (SQLException e) {
            System.err.println("Error fetching modules: " + e.getMessage());
        }
    }

    /**
     * Searches for a student with the provided student ID and displays their grades.
     * If the 'statePassed' parameter is true, only modules with passing grades will be displayed.
     * If the student ID is invalid, a warning alert is displayed.
     *
     * @param id          The student ID TextField.
     * @param statePassed A boolean value to determine if only passing grades should be displayed.
     */
    public void search(TextField id, boolean statePassed) {
        String studentId = id.getText();

        String validationMessage = validateStudentId(studentId);
        if (validationMessage != null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Student ID");
            alert.setContentText(validationMessage);
            alert.showAndWait();
            return;
        }

        String sqlSearchStudent = "SELECT * FROM student WHERE studentId = ?";
        String sqlSearchGrade = statePassed ?
                "SELECT module.moduleName, grade.grade " +
                        "FROM module JOIN grade ON module.moduleCode = grade.moduleCode " +
                        "WHERE grade.studentId = ? AND grade >= 40" :
                "SELECT module.moduleName, grade.grade " +
                        "FROM module JOIN grade ON module.moduleCode = grade.moduleCode " +
                        "WHERE grade.studentId = ?";

        gradesList.clear();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSearchStudent)) {
            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                student = new Student(resultSet.getString("name"), resultSet.getString("studentId"),
                        resultSet.getString("dateOfBirth"), resultSet.getInt("currentSemester"));
                gradesList.add(student.toString());

                try (PreparedStatement preparedStatementGrade = connection.prepareStatement(sqlSearchGrade)) {
                    preparedStatementGrade.setString(1, studentId);
                    ResultSet resultSetGrade = preparedStatementGrade.executeQuery();

                    while (resultSetGrade.next()) {
                        grade = new Grade(resultSetGrade.getFloat("grade"));
                        module = new Module(resultSetGrade.getString("moduleName"));
                        gradesList.add(module.toString2());
                        gradesList.add(grade.toString2());
                    }
                }

                list4.setText(gradesList.toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", ""));
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error finding student");
                alert.setContentText("Searched student not found in database");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts the displayed grades based on the provided input and sort order.
     * The sorted list is then displayed in the 'list4' TextArea.
     *
     * @param id    The student ID TextField.
     * @param input The column to be sorted in the SQL query.
     * @param sort  The sort order (ASC or DESC) for the SQL query.
     */
    public void sort(TextField id, String input, String sort) {
        String studentDetails = gradesList.get(0);
        String sqlSearchGrade = "select module.moduleName," +
                "grade.grade from module join grade on module.moduleCode = grade.moduleCode where grade.studentId ='" + id.getText() + "'" + " order by " + input + " " + sort;
        gradesList.clear();
        try {
            gradesList.add(studentDetails);
            ResultSet resultSet = statement.executeQuery(sqlSearchGrade);
            while (resultSet.next()) {
                grade = new Grade(resultSet.getFloat("grade"));
                module = new Module(resultSet.getString("moduleName"));
                gradesList.add(module.toString2());
                gradesList.add(grade.toString2());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        list4.setText(gradesList.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    /**
     * Creates an infinite loop that will cause an OutOfMemoryError.
     */
    public void memoryLeak() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Potential Crash");
        alert.setContentText("Running this method may crash the program. Are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        long startTime;
        long endTime;
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ArrayList<Student> studentList = new ArrayList<>();
            startTime = System.currentTimeMillis();
            try {
                while (true) {
                    studentList.add(new Student("Furqan Ali", "R00163178", "06/10/1999", 3));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.err.println("Out of memory error: " + e.getMessage());
                endTime = System.currentTimeMillis();
            }
            //this shows the time taken to crash in seconds
            long timeElapsed = (endTime - startTime) / 1000;
            System.err.println("The time it took to crash: " + timeElapsed + " seconds.");
        }
    }
}