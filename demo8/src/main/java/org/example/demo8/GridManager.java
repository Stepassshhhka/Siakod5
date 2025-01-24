package org.example.demo8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class GridManager {
    private final int gridSize;
    public Cell[][] grid;
    private Cell startCell;
    private Cell endCell;

    public GridManager(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new Cell[gridSize][gridSize];
    }

    public GridPane createGridPane(CellClickHandler clickHandler) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Cell cell = new Cell(row, col);
                grid[row][col] = cell;

                Button button = new Button();
                button.setPrefSize(40, 40);
                cell.setButton(button);

                button.setOnAction(e -> clickHandler.onClick(cell));
                gridPane.add(button, col, row);
            }
        }

        return gridPane;
    }

    public void resetGrid(TeleportManager teleportManager) {
        startCell = null;
        endCell = null;
        teleportManager.getTeleports().clear();


        for (Cell[] row : grid) {
            for (Cell cell : row) {
                cell.setType(CellType.EMPTY);
            }
        }
    }

    public void handleCellClick(Cell cell, TeleportManager teleportManager) {
        if (startCell == null) {
            startCell = cell;
            cell.setType(CellType.START);
        } else if (endCell == null) {
            endCell = cell;
            cell.setType(CellType.END);
        } else {
            teleportManager.toggleTeleport(cell);
        }
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }
}