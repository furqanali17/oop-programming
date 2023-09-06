package college.mtu_application;

import college.mtu_records.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**

 This is the main class for the student application.
 It extends the JavaFX Application class and sets up the user interface for the application.
 */
public class MyApplication extends Application {

    /**
     * The main method of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets up the user interface for the application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {

        View view = new View();
        view.startUp();

        //setting the title of the application
        stage.setTitle("Student Record");

        //set up the primary stage and show the view
        stage.setScene(view.getScene());
        stage.show();
    }
}
