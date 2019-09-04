package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.Controller;
import org.chamberlain.ResourceLoader;
import org.chamberlain.model.Square;

public class SetFinishPointAction extends AbstractAction {

    private static final String DESC = "Sets the finish point";

    private Square currentSquare;

    private final Controller aStar;

    public SetFinishPointAction(Controller aStar) {
        super("Set finish point", ResourceLoader.createImageIcon("finish.png"));
        this.aStar = aStar;
        putValue("ShortDescription", DESC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.aStar.getModel().getGrid().forEach(square -> square.setFinishPoint(false));
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
