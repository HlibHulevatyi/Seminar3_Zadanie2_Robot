module com.example.seminar3_zadanie2_robot {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.seminar3_zadanie2_robot to javafx.fxml;
    exports com.example.seminar3_zadanie2_robot;
}