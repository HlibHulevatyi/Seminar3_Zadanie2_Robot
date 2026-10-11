package com.example.seminar3_zadanie2_robot;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Robot extends Pane {
    private final Rectangle body;
    private final Rectangle visor;
    private Direction currentDirection = Direction.RIGHT;
    private boolean autoMode = false;
    private double speed = 2.0;

    public enum Direction { UP, DOWN, LEFT, RIGHT }

    public Robot() {
        body = new Rectangle(50, 50, Color.RED);
        visor = new Rectangle(10, 10, Color.YELLOW);
        getChildren().addAll(body, visor);
        updateVisorPosition();

        this.setOnMouseClicked(event -> {
            autoMode = !autoMode;
            event.consume();
        });
    }

    public boolean isAutoMode() { return autoMode; }

    public void moveManual(Direction dir, double maxX, double maxY) {
        if (autoMode) return;
        currentDirection = dir;
        updateVisorPosition();
        step(dir, maxX, maxY);
    }

    public void moveAuto(double maxX, double maxY) {
        if (!autoMode) return;
        step(currentDirection, maxX, maxY);
    }

    private void step(Direction dir, double maxX, double maxY) {
        double newX = getLayoutX();
        double newY = getLayoutY();

        switch (dir) {
            case RIGHT -> newX += speed;
            case LEFT -> newX -= speed;
            case DOWN -> newY += speed;
            case UP -> newY -= speed;
        }

        boolean hitWall = false;
        if (newX < 0) { newX = 0; hitWall = true; }
        else if (newX > maxX - body.getWidth()) { newX = maxX - body.getWidth(); hitWall = true; }

        if (newY < 0) { newY = 0; hitWall = true; }
        else if (newY > maxY - body.getHeight()) { newY = maxY - body.getHeight(); hitWall = true; }

        setLayoutX(newX);
        setLayoutY(newY);

        if (hitWall && autoMode) {
            switch (currentDirection) {
                case UP -> currentDirection = Direction.DOWN;
                case DOWN -> currentDirection = Direction.UP;
                case LEFT -> currentDirection = Direction.RIGHT;
                case RIGHT -> currentDirection = Direction.LEFT;
            }
            updateVisorPosition();
        }
    }

    private void updateVisorPosition() {
        switch (currentDirection) {
            case RIGHT -> { visor.setX(40); visor.setY(20); }
            case LEFT -> { visor.setX(0); visor.setY(20); }
            case UP -> { visor.setX(20); visor.setY(0); }
            case DOWN -> { visor.setX(20); visor.setY(40); }
        }
    }
}