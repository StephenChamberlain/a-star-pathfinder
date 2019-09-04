package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.AStarPathFinding;
import org.chamberlain.IconLoader;
import org.chamberlain.model.Square;

public class SetStartPointAction extends AbstractAction {

    private static String desc = "Sets the starting point";

    private Square currentSquare;

    private AStarPathFinding aStar;

    public SetStartPointAction(AStarPathFinding aStar) {
        super("Set start point", IconLoader.createImageIcon("start.png", ""));
        this.aStar = aStar;
        putValue("ShortDescription", desc);
    }

    public void actionPerformed(ActionEvent e) {
        for (Square square : this.aStar.getModel().getGrid()) {
            square.setStartPoint(false);
        }
        this.currentSquare.setStartPoint(!this.currentSquare.isStartPoint());
    }

    public Square getCurrentSquare() {
        return this.currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
        if (currentSquare != null && currentSquare.isObstacle()) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }
    }
}