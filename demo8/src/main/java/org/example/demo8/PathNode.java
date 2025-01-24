package org.example.demo8;

class PathNode {
    final Cell cell;
    final int cost;

    public PathNode(Cell cell, int cost) {
        this.cell = cell;
        this.cost = cost;
    }
}