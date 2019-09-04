package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.IconLoader;
import org.chamberlain.model.Calculation;
import org.chamberlain.model.GridModel;

public class CalculateAction extends AbstractAction {

    private static String desc = "Calculates the path between the start point and the end point";

    private GridModel model;

    private Calculation calc;

    private boolean cutCorners = true;

    public CalculateAction(GridModel model) {
        super("Calculate path", IconLoader.createImageIcon("calculate.png", ""));
        setModel(model);
        putValue("ShortDescription", desc);
    }

    public void actionPerformed(ActionEvent e) {
        this.calc = new Calculation(getModel(), cutCorners());
        getCalc().calculate();
    }

    public Calculation getCalc() {
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