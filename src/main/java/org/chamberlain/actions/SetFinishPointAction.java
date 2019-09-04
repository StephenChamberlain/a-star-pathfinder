package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.AStarPathFinding;
import org.chamberlain.IconLoader;
import org.chamberlain.model.Square;

public class SetFinishPointAction extends AbstractAction {

    private static String desc = "Sets the finish point";

    private Square currentSquare;

    private AStarPathFinding aStar;

    public SetFinishPointAction(AStarPathFinding aStar) {
        super("Set finish point", IconLoader.createImageIcon("finish.png", ""));
        this.aStar = aStar;
        putValue("ShortDescription", desc);
    }

    public void actionPerformed(ActionEvent e) {
        for (Square square : this.aStar.getModel().getGrid()) {
            square.setFinishPoint(false);
        }
        this.currentSquare.setFinishPoint(!this.currentSquare.isFinishPoint());
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