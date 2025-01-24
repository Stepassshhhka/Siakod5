package org.example.demo8;

import java.util.*;

// Pathfinding Class
class Pathfinding {
    private final GridManager gridManager;
    private final TeleportManager teleportManager;

    public Pathfinding(GridManager gridManager, TeleportManager teleportManager) {
        this.gridManager = gridManager;
        this.teleportManager = teleportManager;
    }

    public int findPath(Cell startCell, Cell endCell) {
        if (startCell == null || endCell == null) {
            System.out.println("Установите пж начало и конец.");
            return -1;
        }

        PriorityQueue<PathNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Map<Cell, Integer> distances = new HashMap<>();
        Map<Cell, Cell> previous = new HashMap<>();

        for (Cell[] row : gridManager.grid) {
            for (Cell cell : row) {
                distances.put(cell, Integer.MAX_VALUE);
            }
        }

        distances.put(startCell, 0);
        queue.add(new PathNode(startCell, 0));

        while (!queue.isEmpty()) {
            PathNode current = queue.poll();
            Cell currentCell = current.cell;

            if (currentCell == endCell) {
                break;
            }

            for (Cell neighbor : getNeighbors(currentCell)) {
                if (neighbor.getType() == CellType.OBSTACLE) {
                    continue;
                }

                int newCost = distances.get(currentCell) + currentCell.getWeight();
                if (distances.get(neighbor) > newCost) {
                    distances.put(neighbor, newCost);
                    previous.put(neighbor, currentCell);
                    queue.add(new PathNode(neighbor, newCost));
                }
            }

            if (teleportManager.getTeleports().containsKey(currentCell)) {
                Cell teleportExit = teleportManager.getTeleports().get(currentCell);
                if (teleportExit != null && distances.get(teleportExit) > distances.get(currentCell)) {
                    distances.put(teleportExit, distances.get(currentCell));
                    previous.put(teleportExit, currentCell);
                    queue.add(new PathNode(teleportExit, distances.get(currentCell)));
                }
            }
        }

        if (distances.get(endCell) == Integer.MAX_VALUE) {
            return -1;
        }

        // Backtrack to reconstruct path
        Cell step = endCell;
        while (step != null) {
            step.setType(CellType.PATH);
            step = previous.get(step);
        }

        return distances.get(endCell);
    }

    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int row = cell.getRow();
        int col = cell.getCol();

        if (row > 0) neighbors.add(gridManager.grid[row - 1][col]);
        if (row < gridManager.grid.length - 1) neighbors.add(gridManager.grid[row + 1][col]);
        if (col > 0) neighbors.add(gridManager.grid[row][col - 1]);
        if (col < gridManager.grid[0].length - 1) neighbors.add(gridManager.grid[row][col + 1]);

        return neighbors;
    }
}
