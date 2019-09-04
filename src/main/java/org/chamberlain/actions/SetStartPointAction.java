package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.Controller;
import org.chamberlain.ResourceLoader;
import org.chamberlain.model.Square;

public class SetStartPointAction extends AbstractAction {

    private static final String DESC = "Sets the starting point";

    private Square currentSquare;

    private final Controller aStar;

    public SetStartPointAction(Controller aStar) {
        super("Set start point", ResourceLoader.createImageIcon("start.png"));
        this.aStar = aStar;
        putValue("ShortDescription", DESC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.aStar.getModel().getGrid().forEach(square -> square.setStartPoint(false));
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
