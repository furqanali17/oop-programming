package college.mtu_records;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;


/**
 * The View class represents the graphical user interface for the student record system.
 * <p>
 * It contains buttons, text fields, and combo boxes for adding, removing, listing, searching,
 * and sorting records. It also holds the scene object for displaying the interface.
 * <p>
 * The class has a reference to the controller object and stores the selected student and module,
 * as well as their corresponding states.
 */
public class View {

    //initialize the scene
    private Scene scene;

    //method to getScene used in application
    public Scene getScene() {
        return scene;
    }

    //initialize controller variable
    protected Controller controller;

    //creates comboBoxes
    protected ComboBox<String> comboBox = new ComboBox<>();
    protected ComboBox<String> comboBox_2 = new ComboBox<>();

    //initialize variables used in comboBox
    protected boolean state;
    protected boolean statePassed;
    protected String selectedStudent;
    protected String selectedModule;


    //initializing the buttons
    //tab 1 buttons
    protected Button add, remove, list, exitTab1;
    //tab 2 buttons
    protected Button addModule, removeModule, listModule, exitTab2;
    //tab 3 buttons
    protected Button submit, delete, exitTab3;
    //tab 4 buttons
    protected Button search, alphabetical_sort, numerical_sort, memoryLeak, exitTab4;

    //initializing all text fields
    //tab 1 text fields
    protected TextField studentNameInput, studentIdInput, studentDobInput, studentSemesterInput;
    //tab 2 text fields
    protected TextField moduleNameInput, moduleCodeInput, moduleSemesterInput;
    //tab 3 text fields
    protected TextField gradeInput;
    //tab 4 text fields
    protected TextField searchInput;

    /**
     * Constructor that sets the controller.
     */
    public View() {
        controller = new Controller(this);
    }

    /**
     * Method that makes everything in the view.
     */
    public void startUp() {

        //naming all the buttons
        add = new Button("Add");
        addModule = new Button("Add");
        remove = new Button("Remove");
        removeModule = new Button("Remove");
        list = new Button("List");
        listModule = new Button("List");
        exitTab1 = new Button("Exit");
        exitTab2 = new Button("Exit");
        exitTab3 = new Button("Exit");
        exitTab4 = new Button("Exit");
        submit = new Button("Submit");
        delete = new Button("Delete Grade");
        alphabetical_sort = new Button("Alphabetical");
        numerical_sort = new Button("Numerical");
        search = new Button("Search");
        memoryLeak = new Button("Memory Leak");


        //setting the list to the textarea where the arraylist is displayed
        controller.list1 = new TextArea();
        controller.list2 = new TextArea();
        controller.list3 = new TextArea();
        controller.list4 = new TextArea();


        //setting the textarea to not be editable
        controller.list1.setEditable(false);
        controller.list2.setEditable(false);
        controller.list3.setEditable(false);
        controller.list4.setEditable(false);


        //splitting application into tabs
        TabPane tabPane = new TabPane();


        //creating the layout for tab 1
        GridPane layout_1 = new GridPane();

        //making the first tab
        Tab tab1 = new Tab("Tab 1", layout_1);
        //setting the tab to not be able to be closed
        tab1.setClosable(false);

        //naming text fields for tab 1
        studentNameInput = new TextField();
        studentNameInput.setPromptText("Enter your name");
        studentIdInput = new TextField();
        studentIdInput.setPromptText("R00XXXXXX");
        studentDobInput = new TextField();
        studentDobInput.setPromptText("Enter your date of birth");
        studentSemesterInput = new TextField();
        studentSemesterInput.setPromptText("Enter your current semester");

        //naming labels for tab 1
        Label lblName = new Label("Name");
        Label lblStdId = new Label("Student ID");
        Label lblDob = new Label("Date of Birth");
        Label lblSem = new Label("Current Semester");

        //creating button bars to hold all the buttons
        ButtonBar buttonBarTab1Row1 = new ButtonBar();
        ButtonBar buttonBarTab1Row2 = new ButtonBar();

        //adding the buttons to the button bars
        buttonBarTab1Row1.getButtons().addAll(add, remove, list);
        buttonBarTab1Row2.getButtons().addAll(exitTab1);

        //adding everything to the layout of tab 1
        layout_1.add(lblName, 0, 0, 1, 1);
        layout_1.add(studentNameInput, 1, 0, 1, 1);
        layout_1.add(lblStdId, 0, 1, 1, 1);
        layout_1.add(studentIdInput, 1, 1, 1, 1);
        layout_1.add(lblDob, 0, 2, 1, 1);
        layout_1.add(studentDobInput, 1, 2, 1, 1);
        layout_1.add(lblSem, 0, 3, 1, 1);
        layout_1.add(studentSemesterInput, 1, 3, 1, 1);
        layout_1.add(buttonBarTab1Row1, 0, 4, 2, 1);
        layout_1.add(controller.list1, 0, 5, 2, 1);

        //setting preferred width and height fot the text box
        controller.list1.setPrefWidth(100);
        controller.list1.setPrefHeight(200);
        layout_1.add(buttonBarTab1Row2, 0, 6, 2, 1);

        //setting horizontal and vertical gaps for the layout
        layout_1.setHgap(10);
        layout_1.setVgap(5);

        //setting padding for the layout
        layout_1.setPadding(new Insets(10, 10, 10, 10));

        //setting the constraints for the column
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        layout_1.getColumnConstraints().addAll(column1, column2);
        column2.setPrefWidth(200);

        //horizontal alignment of the layout
        GridPane.setHalignment(lblName, HPos.RIGHT);
        GridPane.setHalignment(lblStdId, HPos.RIGHT);
        GridPane.setHalignment(lblDob, HPos.RIGHT);

        //horizontal growth priority of the layout
        GridPane.setHgrow(studentNameInput, Priority.ALWAYS);
        GridPane.setHgrow(studentIdInput, Priority.ALWAYS);
        GridPane.setHgrow(studentDobInput, Priority.ALWAYS);
        GridPane.setHgrow(controller.list1, Priority.ALWAYS);
        GridPane.setVgrow(controller.list1, Priority.ALWAYS);


        //creating the layout for tab 2
        GridPane layout_2 = new GridPane();

        //making the second tab
        Tab tab2 = new Tab("Tab 2", layout_2);
        //setting the tab to not be able to be closed
        tab2.setClosable(false);

        //naming text fields for tab 2
        moduleNameInput = new TextField();
        moduleNameInput.setPromptText("Enter module");
        moduleCodeInput = new TextField();
        moduleCodeInput.setPromptText("MTXXX");
        moduleSemesterInput = new TextField();
        moduleSemesterInput.setPromptText("Enter module semester");

        //naming labels for tab 2
        Label lblModuleName = new Label("Module Name");
        Label lblCode = new Label("Module Code");
        Label lblModSem = new Label("Semester");

        //setting horizontal and vertical gaps for the layout
        layout_2.setHgap(10);
        layout_2.setVgap(5);

        //setting padding for the layout
        layout_2.setPadding(new Insets(10, 10, 10, 10));

        //creating button bars to hold all the buttons
        ButtonBar buttonBarTab2Row1 = new ButtonBar();
        ButtonBar buttonBarTab2Row2 = new ButtonBar();

        //adding the buttons to the button bars
        buttonBarTab2Row1.getButtons().addAll(addModule, removeModule, listModule);
        buttonBarTab2Row2.getButtons().addAll(exitTab2);

        //adding everything to the layout of tab 2
        layout_2.add(lblModuleName, 0, 1);
        layout_2.add(moduleNameInput, 1, 1);
        layout_2.add(lblCode, 0, 2);
        layout_2.add(moduleCodeInput, 1, 2);
        layout_2.add(lblModSem, 0, 3);
        layout_2.add(moduleSemesterInput, 1, 3);
        layout_2.add(buttonBarTab2Row1, 0, 4, 2, 1);
        layout_2.add(controller.list2, 0, 5, 2, 1);

        //setting preferred width and height fot the text box
        controller.list2.setPrefWidth(100);
        controller.list2.setPrefHeight(200);
        layout_2.add(buttonBarTab2Row2, 0, 6, 2, 1);

        //setting the constraints for the column
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHalignment(HPos.RIGHT);
        column3.setHgrow(Priority.NEVER);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setHgrow(Priority.ALWAYS);
        layout_2.getColumnConstraints().addAll(column3, column4);

        //horizontal alignment of the layout
        GridPane.setHalignment(lblModuleName, HPos.RIGHT);

        //horizontal growth priority of the layout
        GridPane.setHgrow(moduleNameInput, Priority.ALWAYS);
        GridPane.setHgrow(controller.list2, Priority.ALWAYS);
        GridPane.setVgrow(controller.list2, Priority.ALWAYS);


        //creating the layout for tab 3
        GridPane layout_3 = new GridPane();

        //making the third tab
        Tab tab3 = new Tab("Tab 3", layout_3);
        //setting the tab to not be able to be closed
        tab3.setClosable(false);

        //naming text fields for tab 3
        gradeInput = new TextField();
        gradeInput.setPromptText("Enter grade");

        //naming labels for tab 3
        Label lblGrade = new Label("Grade");
        Label modifyGrade = new Label("Input new grade to modify");

        //radio button to show all modules
        RadioButton tickBoxShowAllModules = new RadioButton("Show all modules");

        //calls combo box
        controller.comboBox();
        comboBox.setPromptText("Select a student");
        comboBox.getItems().addAll(controller.studentListCombo);
        comboBox.setItems(controller.observableList);
        //add listener to combo box
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //temp to split selected student into array of strings which are separated by a space
            String[] temp = newValue.split(" ");
            //assigns the selected student to the first string
            selectedStudent = temp[0];
            //calls the second combo box with state
            //this is done so that the second combo box populates bases on this combo box
            controller.comboBox_2(selectedStudent, state);
            //calls method to check if the selected student already has a grade
            controller.modifyCheck(selectedStudent, selectedModule);
            controller.list3.setText("Student selected with Id " + selectedStudent);
        });


        //start of second combo box
        comboBox_2.setPromptText("Select a module");
        comboBox_2.getItems().addAll(controller.moduleListCombo);
        comboBox_2.setItems(controller.observableListModule);
        //add listener to second combo box
        comboBox_2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //temp to split selected module into array of strings which are separated by a space
            String[] temp = newValue.split(" ");
            //assigns the selected module to the first string
            selectedModule = temp[0];
            //calls method to check if the selected student already has a grade
            controller.modifyCheck(selectedStudent, selectedModule);
            controller.list3.setText("Module selected with Id " + selectedModule);
        });

        //setting vertical gap for the layout
        layout_3.setVgap(5);

        //setting padding for the layout
        layout_3.setPadding(new Insets(10, 10, 10, 10));

        //creating button bars to hold all the buttons
        ButtonBar buttonBarTab3Row1 = new ButtonBar();
        ButtonBar buttonBarTab3Row2 = new ButtonBar();

        //adding the buttons to the button bars
        buttonBarTab3Row1.getButtons().addAll(modifyGrade, submit, delete);
        buttonBarTab3Row2.getButtons().addAll(exitTab3);

        //adding everything to the layout of tab 3
        layout_3.add(comboBox, 0, 0);
        layout_3.add(comboBox_2, 0, 1);
        layout_3.add(lblGrade, 0, 2);
        layout_3.add(gradeInput, 0, 3);
        layout_3.add(tickBoxShowAllModules, 0, 4);
        layout_3.add(buttonBarTab3Row1, 0, 5);
        layout_3.add(controller.list3, 0, 6);
        layout_3.add(buttonBarTab3Row2, 0, 7);

        //setting the constraints for the column
        ColumnConstraints column5 = new ColumnConstraints();
        ColumnConstraints column6 = new ColumnConstraints();
        layout_3.getColumnConstraints().add(column5);
        layout_3.getColumnConstraints().add(column6);
        layout_3.getColumnConstraints().addAll(column5, column6);

        //horizontal alignment of the layout
        GridPane.setHalignment(comboBox, HPos.LEFT);
        GridPane.setHalignment(comboBox_2, HPos.LEFT);

        //horizontal and vertical growth priority of the layout
        GridPane.setHgrow(controller.list3, Priority.ALWAYS);
        GridPane.setVgrow(controller.list3, Priority.ALWAYS);


        //creating the layout for tab 4
        GridPane layout_4 = new GridPane();

        //making the fourth tab
        Tab tab4 = new Tab("Tab 4", layout_4);
        //setting the tab to not be able to be closed
        tab4.setClosable(false);

        //naming text fields for tab 4
        searchInput = new TextField();
        searchInput.setPromptText("R00XXXXXX");

        //naming labels for tab 4
        Label lblSearch = new Label("Search for Student");
        Label lblSort = new Label("Sort:");

        //radio button to show all passed modules
        RadioButton tickBoxPassedModules = new RadioButton("Show only passed modules");

        //setting vertical gap for the layout
        layout_4.setVgap(5);

        //setting padding for the layout
        layout_4.setPadding(new Insets(10, 10, 10, 10));

        //creating button bars to hold all the buttons
        ButtonBar buttonBarTab4Row1 = new ButtonBar();

        //adding the buttons to the button bars
        buttonBarTab4Row1.getButtons().addAll(lblSort, alphabetical_sort, numerical_sort, memoryLeak, exitTab4);

        //adding everything to the layout of tab 4
        layout_4.add(lblSearch, 0, 0);
        layout_4.add(searchInput, 0, 1);
        layout_4.add(search, 0, 2);
        layout_4.add(tickBoxPassedModules, 0, 3);
        layout_4.add(controller.list4, 0, 4);
        controller.list4.setPrefWidth(100);
        controller.list4.setPrefHeight(200);
        layout_4.add(buttonBarTab4Row1, 0, 5);

        //setting the constraints for the column
        ColumnConstraints column7 = new ColumnConstraints();
        ColumnConstraints column8 = new ColumnConstraints();
        layout_4.getColumnConstraints().add(column7);
        layout_4.getColumnConstraints().add(column8);
        layout_4.getColumnConstraints().addAll(column7, column8);

        //horizontal alignment of the layout
        GridPane.setHalignment(lblSearch, HPos.LEFT);

        //horizontal and vertical growth priority of the layout
        GridPane.setHgrow(searchInput, Priority.ALWAYS);
        GridPane.setHgrow(controller.list4, Priority.ALWAYS);
        GridPane.setVgrow(controller.list4, Priority.ALWAYS);


        //on action events for when the respective buttons are pressed
        add.setOnAction(actionEvent -> controller.addStudent(studentNameInput, studentIdInput, studentDobInput, studentSemesterInput));
        addModule.setOnAction(actionEvent -> controller.addModule(moduleNameInput, moduleCodeInput, moduleSemesterInput));
        remove.setOnAction(actionEvent -> controller.removeStudent(studentIdInput));
        removeModule.setOnAction(actionEvent -> controller.removeModule(moduleCodeInput));
        list.setOnAction(actionEvent -> controller.listStudent());
        listModule.setOnAction(actionEvent -> controller.listModule());
        exitTab1.setOnAction(actionEvent -> controller.exit());
        exitTab2.setOnAction(actionEvent -> controller.exit());
        exitTab3.setOnAction(actionEvent -> controller.exit());
        exitTab4.setOnAction(actionEvent -> controller.exit());
        submit.setOnAction(actionEvent -> controller.submit(selectedStudent, selectedModule, gradeInput));
        delete.setOnAction(actionEvent -> controller.delete(selectedStudent, selectedModule));
        search.setOnAction(actionEvent -> controller.search(searchInput, statePassed));
        tickBoxPassedModules.setOnAction(actionEvent ->
                tickBoxPassedModules.selectedProperty().addListener((o, oldValue, newValue) -> {
                    statePassed = newValue;
                    controller.search(searchInput, statePassed);
                }));
        tickBoxShowAllModules.setOnAction(actionEvent ->
                tickBoxShowAllModules.selectedProperty().addListener((o, oldValue, newValue) -> {
                    state = newValue;
                    controller.comboBox_2(selectedStudent, state);
                }));

        memoryLeak.setOnAction(event -> controller.memoryLeak());
        alphabetical_sort.setOnAction(actionEvent -> {
            String input = "moduleName";
            String sort = "asc";
            controller.sort(searchInput, input, sort);
        });
        numerical_sort.setOnAction(actionEvent -> {
            String input = "grade";
            String sort = "desc";
            controller.sort(searchInput, input, sort);
        });

        //add all tabs to tab pane
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
        scene = new Scene(tabPane, 500, 350);

    }
}