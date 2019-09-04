package org.chamberlain.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.chamberlain.MainFrame;
import org.chamberlain.dialogs.ErrorDialog;
import org.chamberlain.dialogs.FailedConditionDialog;

public final class AStarAlgorithm {

    public static final int MOVE_COST = 10;
    public static final int MOVE_COST_DIAGONAL = 14;

    private final GridModel model;
    private final List<Square> open;
    private final List<Square> closed;
    private final List<Square> unwalkable;

    private Square start;
    private Square finish;

    private boolean pathFound;
    private boolean cutCorners;

    public AStarAlgorithm(GridModel model, boolean cutCorners) {
        this.start = null;
        this.finish = null;
        this.open = new ArrayList();
        this.closed = new ArrayList();
        this.unwalkable = new ArrayList();
        this.pathFound = false;
        this.cutCorners = true;
        this.model = model;
        setCutCorners(cutCorners);
        System.out.println("GridModel: " + model.toString());
    }

    public void calculate() {
        if (checkStartConditions()) {
            getOpen().add(getStart());
            Square leadSquare = getStart();
            while (!this.pathFound) {
                Square[] currentNodes = getSurroundingNodes(leadSquare);
                if (noCandidates(currentNodes)) {
                    this.unwalkable.add(leadSquare);
                    if (this.closed.isEmpty()) {
                        ErrorDialog dialog = new ErrorDialog();
                        dialog.setText("No path possible!");
                        dialog.pack();
                        dialog.setLocationRelativeTo(MainFrame.mainFrame);
                        dialog.setVisible(true);
                        return;
                    }
                    leadSquare = (Square) this.closed.remove(this.closed.size() - 1);
                    continue;
                }
                Square newLeadSquare = null;
                for (Square candidate : currentNodes) {
                    if (candidate != null) {
                        if (!this.closed.contains(candidate)) {
                            candidate.setParent(leadSquare);
                            candidate.setF(calculateF(candidate.getG(), calculateH(candidate.getCentralPoint(), getFinish().getCentralPoint())));
                            if (newLeadSquare == null) {
                                newLeadSquare = candidate;
                            } else if (candidate.getF() < newLeadSquare.getF()) {
                                newLeadSquare = candidate;
                            }
                            System.out.println("F=" + candidate.getF());
                            getOpen().add(candidate);
                        }
                    }
                }
                this.closed.add(leadSquare);
                this.open.remove(leadSquare);
                leadSquare = newLeadSquare;
                if (leadSquare == null) {
                    continue;
                }
                System.out.println("F=" + leadSquare.getF());
                getOpen().add(leadSquare);
                if (newLeadSquare.getCentralPoint().equals(getFinish().getCentralPoint())) {
                    this.pathFound = true;
                }
            }
        }
    }

    private boolean noCandidates(Square[] currentNodes) {
        for (Square square : currentNodes) {
            if (square != null) {
                return false;
            }
        }
        return true;
    }

    private Square[] getSurroundingNodes(Square leadSquare) {
        Square[] currentNodes = new Square[8];
        int TOP_LEFT = 0;
        int TOP = 1;
        int TOP_RIGHT = 2;
        int RIGHT = 3;
        int BOTTOM_LEFT = 4;
        int BOTTOM = 5;
        int BOTTOM_RIGHT = 6;
        int LEFT = 7;
        int index = leadSquare.getIndex() + this.model.getColumns() - 1;
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[TOP_LEFT] = (Square) this.model.getGrid().get(index);
            currentNodes[TOP_LEFT].setG(14);
        } else {
            currentNodes[TOP_LEFT] = null;
        }
        index = leadSquare.getIndex() + this.model.getColumns();
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[TOP] = (Square) this.model.getGrid().get(index);
            currentNodes[TOP].setG(10);
        } else {
            currentNodes[TOP] = null;
        }
        index = leadSquare.getIndex() + this.model.getColumns() + 1;
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[TOP_RIGHT] = (Square) this.model.getGrid().get(index);
            currentNodes[TOP_RIGHT].setG(14);
        } else {
            currentNodes[TOP_RIGHT] = null;
        }
        index = leadSquare.getIndex() + 1;
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[RIGHT] = (Square) this.model.getGrid().get(index);
            currentNodes[RIGHT].setG(10);
        } else {
            currentNodes[RIGHT] = null;
        }
        index = leadSquare.getIndex() - this.model.getColumns() + 1;
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[BOTTOM_LEFT] = (Square) this.model.getGrid().get(index);
            currentNodes[BOTTOM_LEFT].setG(14);
        } else {
            currentNodes[BOTTOM_LEFT] = null;
        }
        index = leadSquare.getIndex() - this.model.getColumns();
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[BOTTOM] = (Square) this.model.getGrid().get(index);
            currentNodes[BOTTOM].setG(10);
        } else {
            currentNodes[BOTTOM] = null;
        }
        index = leadSquare.getIndex() - this.model.getColumns() - 1;
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[BOTTOM_RIGHT] = (Square) this.model.getGrid().get(index);
            currentNodes[BOTTOM_RIGHT].setG(14);
        } else {
            currentNodes[BOTTOM_RIGHT] = null;
        }
        index = leadSquare.getIndex() - 1;
        if (index < this.model.getGrid().size() && index > 0 && !this.unwalkable.contains(this.model.getGrid().get(index)) && !this.closed.contains(this.model.getGrid().get(index))) {
            currentNodes[LEFT] = (Square) this.model.getGrid().get(index);
            currentNodes[LEFT].setG(10);
        } else {
            currentNodes[LEFT] = null;
        }
        if (!cutCorners()) {
            System.out.println("Not cutting corners...");
            if ((currentNodes[LEFT] != null && currentNodes[LEFT].isObstacle()) || (currentNodes[TOP] != null && currentNodes[TOP].isObstacle())) {
                System.out.println("TOP_LEFT blocked");
                currentNodes[TOP_LEFT] = null;
            }
            if ((currentNodes[TOP] != null && currentNodes[TOP].isObstacle()) || (currentNodes[RIGHT] != null && currentNodes[RIGHT].isObstacle())) {
                System.out.println("TOP_RIGHT blocked");
                currentNodes[TOP_RIGHT] = null;
            }
            if ((currentNodes[RIGHT] != null && currentNodes[RIGHT].isObstacle()) || (currentNodes[BOTTOM] != null && currentNodes[BOTTOM].isObstacle())) {
                System.out.println("BOTTOM_RIGHT blocked");
                currentNodes[BOTTOM_RIGHT] = null;
            }
            if ((currentNodes[BOTTOM] != null && currentNodes[BOTTOM].isObstacle()) || (currentNodes[LEFT] != null && currentNodes[LEFT].isObstacle())) {
                System.out.println("BOTTOM_LEFT blocked");
                currentNodes[BOTTOM_LEFT] = null;
            }
        }
        for (int i = 0; i < currentNodes.length; i++) {
            if (currentNodes[i] != null && currentNodes[i].isObstacle()) {
                currentNodes[i] = null;
            }
        }
        if (null != leadSquare.getSide()) switch (leadSquare.getSide()) {
            case LEFT:
                currentNodes[TOP_LEFT] = null;
                currentNodes[LEFT] = null;
                currentNodes[BOTTOM_LEFT] = null;
                break;
            case RIGHT:
                currentNodes[TOP_RIGHT] = null;
                currentNodes[RIGHT] = null;
                currentNodes[BOTTOM_RIGHT] = null;
                break;
            case TOP:
                currentNodes[TOP_LEFT] = null;
                currentNodes[TOP] = null;
                currentNodes[TOP_RIGHT] = null;
                break;
            case BOTTOM:
                currentNodes[BOTTOM_LEFT] = null;
                currentNodes[BOTTOM] = null;
                currentNodes[BOTTOM_RIGHT] = null;
                break;
            default:
                break;
        }
        return currentNodes;
    }

    private double calculateF(int G, double H) {
        return G + H;
    }

    private double calculateH(Point a, Point b) {
        return a.distance(b);
    }

    private boolean checkStartConditions() {
        boolean startPoint = false;
        boolean finishPoint = false;
        for (Square square : this.model.getGrid()) {
            if (square.isStartPoint()) {
                startPoint = true;
                setStart(square);
                continue;
            }
            if (square.isFinishPoint()) {
                finishPoint = true;
                setFinish(square);
                continue;
            }
            if (square.isObstacle()) {
                getUnwalkable().add(square);
            }
        }
        if (!startPoint) {
            FailedConditionDialog dialog = new FailedConditionDialog("You must define a starting point in the grid");
            dialog.pack();
            dialog.setSize(440, 400);
            dialog.setLocationRelativeTo(MainFrame.mainFrame);
            dialog.setVisible(true);
            return false;
        }
        if (!finishPoint) {
            FailedConditionDialog dialog = new FailedConditionDialog("You must define a finishing point in the grid");
            dialog.pack();
            dialog.setSize(440, 400);
            dialog.setLocationRelativeTo(MainFrame.mainFrame);
            dialog.setVisible(true);
            return false;
        }
        return true;
    }

    public List<Square> getOpen() {
        return this.open;
    }

    public List<Square> getClosed() {
        return this.closed;
    }

    public List<Square> getUnwalkable() {
        return this.unwalkable;
    }

    public void clearOpenList() {
        this.open.clear();
    }

    public void clearClosedList() {
        this.closed.clear();
    }

    public void clearUnwalkableList() {
        this.unwalkable.clear();
    }

    public Square getStart() {
        return this.start;
    }

    public void setStart(Square start) {
        this.start = start;
    }

    public Square getFinish() {
        return this.finish;
    }

    public void setFinish(Square finish) {
        this.finish = finish;
    }

    public boolean cutCorners() {
        return this.cutCorners;
    }

    public void setCutCorners(boolean cutCorners) {
        this.cutCorners = cutCorners;
    }
}
