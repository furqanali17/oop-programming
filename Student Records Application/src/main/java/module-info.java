module college.student_records {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires junit;
    requires java.sql;


    opens college.mtu_records to javafx.fxml;
    exports college.mtu_records;
    exports college.mtu_test;
    opens college.mtu_test to javafx.fxml;
    exports college.mtu_model;
    opens college.mtu_model to javafx.fxml;
    exports college.mtu_application;
    opens college.mtu_application to javafx.fxml;
}