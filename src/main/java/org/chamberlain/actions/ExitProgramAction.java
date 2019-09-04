package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.ResourceLoader;

public class ExitProgramAction extends AbstractAction {

    private static final String DESC = "Exits the program";

    public ExitProgramAction() {
        super("Exit", ResourceLoader.createImageIcon("exit.png"));
        putValue("ShortDescription", DESC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
