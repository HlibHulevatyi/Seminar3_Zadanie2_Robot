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
                double maxX = gamePane.getWidth();
                double maxY = gamePane.getHeight();

                if (robot.isAutoMode()) {
                    robot.moveAuto(maxX, maxY);
                } else {
                    if (wPressed) robot.moveManual(Robot.Direction.UP, maxX, maxY);
                    else if (sPressed) robot.moveManual(Robot.Direction.DOWN, maxX, maxY);
                    else if (aPressed) robot.moveManual(Robot.Direction.LEFT, maxX, maxY);
                    else if (dPressed) robot.moveManual(Robot.Direction.RIGHT, maxX, maxY);
                }
            }
        };
        timer.start();
    }
}