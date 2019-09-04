package org.chamberlain;

import javax.swing.JPopupMenu;
import org.chamberlain.actions.SetFinishPointAction;
import org.chamberlain.actions.SetStartPointAction;
import org.chamberlain.model.Square;

public class GridPopupMenu extends JPopupMenu {

    private Square currentSquare;

    private AStarPathFinding aStar;

    private SetStartPointAction setStartPointAction;

    private SetFinishPointAction setFinishPointAction;

    public GridPopupMenu(AStarPathFinding aStar) {
        this.aStar = aStar;
        this.setStartPointAction = new SetStartPointAction(aStar);
        this.setFinishPointAction = new SetFinishPointAction(aStar);
        add(this.setStartPointAction);
        add(this.setFinishPointAction);
    }

    public Square getCurrentSquare() {
        return this.currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
        this.setStartPointAction.setCurrentSquare(currentSquare);
        this.setFinishPointAction.setCurrentSquare(currentSquare);
    }
}