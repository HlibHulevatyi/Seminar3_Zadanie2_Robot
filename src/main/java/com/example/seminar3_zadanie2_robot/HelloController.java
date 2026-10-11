package com.example.seminar3_zadanie2_robot;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;

public class HelloController {
    @FXML
    private Pane gamePane;
    private boolean wPressed, aPressed, sPressed, dPressed;
    private Robot robot;

    @FXML
    public void initialize() {
        robot = new Robot();
        robot.setLayoutX(100);
        robot.setLayoutY(100);
        gamePane.getChildren().add(robot);

        Platform.runLater(() -> {
            Scene scene = gamePane.getScene();
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case W -> wPressed = true;
                    case S -> sPressed = true;
                    case A -> aPressed = true;
                    case D -> dPressed = true;
                }
            });
            scene.setOnKeyReleased(e -> {

                switch (e.getCode()) {
                    case W -> wPressed = false;
                    case S -> sPressed = false;
                    case A -> aPressed = false;
                    case D -> dPressed = false;
                }
            });
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (wPressed) robot.moveManual(Robot.Direction.UP);
                if (sPressed) robot.moveManual(Robot.Direction.DOWN);
                if (aPressed) robot.moveManual(Robot.Direction.LEFT);
                if (dPressed) robot.moveManual(Robot.Direction.RIGHT);
            }
        };
        timer.start();
    }
}