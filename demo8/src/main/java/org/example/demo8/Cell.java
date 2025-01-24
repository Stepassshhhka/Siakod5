package org.example.demo8;

import javafx.scene.control.Button;

import java.util.Random;

class Cell {
    private final int row, col;
    private Button button;
    private CellType type;
    Random random = new Random();
    private int weight = 1;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.type = CellType.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
        updateButtonStyle();
    }

    private void updateButtonStyle() {
        switch (type) {
            case EMPTY -> button.setStyle("-fx-background-color: white;");
            case OBSTACLE -> button.setStyle("-fx-background-color: black;");
            case START -> button.setStyle("-fx-background-color: green;");
            case END -> button.setStyle("-fx-background-color: red;");
            case TELEPORT -> button.setStyle("-fx-background-color: blue;");
            case PATH -> button.setStyle("-fx-background-color: yellow;");
        }
    }
}

enum CellType {
    EMPTY, OBSTACLE, START, END, TELEPORT, PATH
}
