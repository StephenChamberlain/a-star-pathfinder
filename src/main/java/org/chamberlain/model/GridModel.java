package org.chamberlain.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GridModel {

    private List<Square> grid;

    private int rows;

    private int columns;

    private String gridName;

    public GridModel(int rows, int columns) {
        this.grid = new ArrayList();
        initModel(rows, columns);
    }

    public void initModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        int size = 50;
        int index = 0;
        rows *= size;
        columns *= size;
        int row;
        for (row = 0; row < rows; row += size) {
            int column;
            for (column = 0; column < columns; column += size) {
                Square square = new Square(new Point(column, row + size), new Point(column + size, row + size), new Point(column, row), new Point(column + size, row), index++);
                if (row == 0) {
                    square.setSide(GridSide.BOTTOM);
                } else if (row == rows - 1) {
                    square.setSide(GridSide.TOP);
                } else if (column == 0) {
                    square.setSide(GridSide.LEFT);
                } else if (column == columns - 1) {
                    square.setSide(GridSide.RIGHT);
                }
                getGrid().add(square);
            }
        }
    }

    public void initTestModel(int size) {
        getGrid().add(new Square(new Point(0, size), new Point(size, size), new Point(0, 0), new Point(size, 0), 0));
        getGrid().add(new Square(new Point(-size, 0), new Point(0, 0), new Point(-size, -size), new Point(0, -size), 1));
        getGrid().add(new Square(new Point(0, 0), new Point(size, 0), new Point(0, -size), new Point(size, -size), 2));
        getGrid().add(new Square(new Point(-size, size), new Point(0, size), new Point(-size, 0), new Point(0, 0), 3));
    }

    public List<Square> getGrid() {
        return this.grid;
    }

    public void setGrid(List<Square> grid) {
        this.grid = grid;
    }

    public void clear() {
        for (Square square : this.grid) {
            square.setObstacle(false);
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rows: " + this.rows + "\n");
        builder.append("Columns: " + this.columns + "\n");
        return builder.toString();
    }

    public String getGridName() {
        if (this.gridName == null) {
            return "New grid";
        }
        return this.gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName.replaceAll(".xml", "");
    }
}