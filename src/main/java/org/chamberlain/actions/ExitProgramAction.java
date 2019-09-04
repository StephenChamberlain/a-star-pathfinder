package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.IconLoader;

public class ExitProgramAction extends AbstractAction {

    private static String desc = "Exits the program";

    public ExitProgramAction() {
        super("Exit", IconLoader.createImageIcon("exit.png", ""));
        putValue("ShortDescription", desc);
    }

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}