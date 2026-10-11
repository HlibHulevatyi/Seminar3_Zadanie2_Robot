package com.example.seminar3_zadanie2_robot;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane gamePane;

    @FXML
    public void initialize() {
        Robot robot = new Robot();
        robot.setLayoutX(100);
        robot.setLayoutY(100);
        gamePane.getChildren().add(robot);

    }
}
