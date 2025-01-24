package org.example.demo8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.*;

// GridManager Class
// Main Application Class
public class DijkstraWithTeleport extends Application {

    private GridManager gridManager;
    private TeleportManager teleportManager;
    private Pathfinding pathfinding;
    private Label pathLengthLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pathfinding with Teleports");

        gridManager = new GridManager(10);
        teleportManager = new TeleportManager();
        pathfinding = new Pathfinding(gridManager, teleportManager);

        GridPane gridPane = gridManager.createGridPane(cell -> handleCellClick(cell));

        VBox controls = new VBox(10);
        controls.setPadding(new Insets(10));

        pathLengthLabel = new Label("Path length: ");

        Button findPathButton = new Button("Find Path");
        findPathButton.setOnAction(e -> {
            int pathLength = pathfinding.findPath(gridManager.getStartCell(), gridManager.getEndCell());
            if (pathLength >= 0) {
                pathLengthLabel.setText("Path length: " + pathLength);
            } else {
                pathLengthLabel.setText("No path found");
            }
        });

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
            gridManager.resetGrid(teleportManager);
            pathLengthLabel.setText("Path length: ");
        });

        controls.getChildren().addAll(findPathButton, resetButton, pathLengthLabel);

        HBox root = new HBox(10, gridPane, controls);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleCellClick(Cell cell) {
        gridManager.handleCellClick(cell, teleportManager);
    }

    public static void main(String[] args) {
        launch(args);
    }
}



