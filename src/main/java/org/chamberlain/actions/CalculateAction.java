package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.ResourceLoader;
import org.chamberlain.model.AStarAlgorithm;
import org.chamberlain.model.GridModel;

public class CalculateAction extends AbstractAction {

    private static final String DESC = "Calculates the path between the start point and the end point";

    private GridModel model;

    private AStarAlgorithm calc;

    private boolean cutCorners = true;

    public CalculateAction(GridModel model) {
        super("Calculate path", ResourceLoader.createImageIcon("calculate.png"));
        setModel(model);
        putValue("ShortDescription", DESC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.calc = new AStarAlgorithm(getModel(), cutCorners());
        getCalc().calculate();
    }

    public AStarAlgorithm getCalc() {
        return this.calc;
    }

    public GridModel getModel() {
        return this.model;
    }

    public void setModel(GridModel model) {
        this.model = model;
    }

    public boolean cutCorners() {
        return this.cutCorners;
    }

    public void setCutCorners(boolean cutCorners) {
        this.cutCorners = cutCorners;
    }
}