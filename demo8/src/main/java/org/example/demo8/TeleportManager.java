package org.example.demo8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TeleportManager Class
class TeleportManager {
    private Map<Cell, Cell> teleports = new HashMap<>();

    public void toggleTeleport(Cell cell) {
        if (cell.getType() == CellType.EMPTY) {
            cell.setType(CellType.TELEPORT);
            if (teleports.size() % 2 == 1) {
                Cell lastTeleport = new ArrayList<>(teleports.keySet()).get(teleports.size() - 1);
                teleports.put(lastTeleport, cell);
                teleports.put(cell, lastTeleport);
            } else {
                teleports.put(cell, null);
            }
        } else if (cell.getType() == CellType.TELEPORT) {
            cell.setType(CellType.EMPTY);
            teleports.remove(cell);
        }
    }

    public Map<Cell, Cell> getTeleports() {
        return teleports;
    }
}