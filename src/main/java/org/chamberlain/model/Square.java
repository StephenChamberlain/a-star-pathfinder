package org.chamberlain.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Square {

    private int index;

    private Square parent;

    private List<Square> children;

    private Point topLeft;

    private Point topRight;

    private Point bottomLeft;

    private Point bottomRight;

    private Point centre;

    private boolean obstacle;

    private boolean startPoint;

    private boolean finishPoint;

    private GridSide side;

    private double F;

    private int G;

    private double H;

    public Square(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight, int index) {
        this.index = -1;
        this.children = new ArrayList();
        this.F = -1.0D;
        this.G = -1;
        this.H = -1.0D;
        setTopLeft(topLeft);
        setTopRight(topRight);
        setBottomLeft(bottomLeft);
        setBottomRight(bottomRight);
        setIndex(index);
        setSide(GridSide.NONE);
        this.centre = getCentralPoint();
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public Point getTopRight() {
        return this.topRight;
    }

    public void setTopRight(Point topRight) {
        this.topRight = topRight;
    }

    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Point getBottomRight() {
        return this.bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Point getCentralPoint() {
        Point centre = new Point();
        centre.x = ((getTopRight()).x - (getTopLeft()).x) / 2;
        centre.x += (getTopLeft()).x;
        centre.y = ((getTopRight()).y - (getBottomRight()).y) / 2;
        centre.y += (getBottomRight()).y;
        return centre;
    }

    public boolean isObstacle() {
        return this.obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
        if (obstacle) {
            System.out.println("Set square " + getIndex() + " as an obstacle");
        }
    }

    public boolean isStartPoint() {
        return this.startPoint;
    }

    public void setStartPoint(boolean startPoint) {
        this.startPoint = startPoint;
    }

    public boolean isFinishPoint() {
        return this.finishPoint;
    }

    public void setFinishPoint(boolean finishPoint) {
        this.finishPoint = finishPoint;
    }

    public void addChild(Square square) {
        this.children.add(square);
    }

    public List getChildren() {
        return this.children;
    }

    public Square getParent() {
        return this.parent;
    }

    public void setParent(Square parent) {
        this.parent = parent;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getF() {
        return this.F;
    }

    public void setF(double F) {
        this.F = F;
    }

    public int getG() {
        return this.G;
    }

    public void setG(int G) {
        this.G = G;
    }

    public double getH() {
        return this.H;
    }

    public void setH(double H) {
        this.H = H;
    }

    public GridSide getSide() {
        return this.side;
    }

    public void setSide(GridSide side) {
        this.side = side;
    }
}