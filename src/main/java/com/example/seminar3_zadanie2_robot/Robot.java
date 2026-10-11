package com.example.seminar3_zadanie2_robot;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Robot extends Pane {
    private final Rectangle body;

    public Robot() {
        body = new Rectangle(50, 50, Color.RED);
        getChildren().add(body);

    }

}
