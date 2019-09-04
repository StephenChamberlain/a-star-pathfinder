package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.chamberlain.AStarPathFinding;
import org.chamberlain.IconLoader;
import org.chamberlain.MainFrame;
import org.chamberlain.dialogs.NewGridDialog;
import org.chamberlain.model.GridModel;

public class NewGridAction extends AbstractAction {

    private static String desc = "Creates a new grid";

    private AStarPathFinding aStarPathFinding;

    private NewGridDialog dialog;

    public NewGridAction(AStarPathFinding aStarPathFinding) {
        super("New", IconLoader.createImageIcon("new.png", ""));
        this.aStarPathFinding = aStarPathFinding;
        putValue("ShortDescription", desc);
        putValue("AcceleratorKey", KeyStroke.getKeyStroke(78, 128));
        putValue("MnemonicKey", Integer.valueOf(78));
    }

    public void actionPerformed(ActionEvent e) {
        showNewGridDialog();
        if (this.dialog.getDialogResult() == 0) {
            this.aStarPathFinding.setModel(new GridModel(this.dialog.getRows(), this.dialog.getColumns()));
        }
    }

    private void showNewGridDialog() {
        this.dialog = new NewGridDialog(this.aStarPathFinding.getModel().getColumns(), this.aStarPathFinding.getModel().getRows());
        this.dialog.pack();
        this.dialog.setSize(310, 190);
        this.dialog.setLocationRelativeTo(MainFrame.mainFrame);
        this.dialog.setVisible(true);
    }
}