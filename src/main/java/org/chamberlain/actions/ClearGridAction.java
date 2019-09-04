package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.chamberlain.AStarPathFinding;
import org.chamberlain.IconLoader;
import org.chamberlain.MainFrame;
import org.chamberlain.dialogs.CommonDialog;

public class ClearGridAction extends AbstractAction {

    private static String desc = "Clears the current grid of obstacles, start, finish and lists";

    private AStarPathFinding aStar;

    public ClearGridAction(AStarPathFinding aStar) {
        super("Reset", IconLoader.createImageIcon("clear.png", ""));
        this.aStar = aStar;
        putValue("ShortDescription", desc);
        putValue("AcceleratorKey", KeyStroke.getKeyStroke(82, 128));
        putValue("MnemonicKey", Integer.valueOf(82));
    }

    public void actionPerformed(ActionEvent e) {
        if (confirm()) {
            this.aStar.getModel().clear();
            CalculateAction calc = (CalculateAction) MainFrame.mainFrame.getCalculateButtonAction();
            calc.getCalc().clearOpenList();
            calc.getCalc().clearClosedList();
            calc.getCalc().clearUnwalkableList();
            calc.getCalc().getStart().setStartPoint(false);
            calc.getCalc().getFinish().setFinishPoint(false);
        }
    }

    private boolean confirm() {
        CommonDialog dialog = new CommonDialog("Clear all obstacles");
        dialog.setBannerText("Are you sure you want to erase all of the obstacles in the grid?");
        dialog.pack();
        dialog.setSize(440, 120);
        dialog.setLocationRelativeTo(MainFrame.mainFrame);
        dialog.setVisible(true);
        if (dialog.getDialogResult() == 0) {
            return true;
        }
        return false;
    }
}