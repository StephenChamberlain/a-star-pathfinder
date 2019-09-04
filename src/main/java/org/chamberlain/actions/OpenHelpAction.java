package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.chamberlain.ResourceLoader;

public class OpenHelpAction extends AbstractAction {

    private static final String DESC = "Opens an XML grid file";

    public OpenHelpAction() {
        super("Help", ResourceLoader.createImageIcon("help.png"));
        putValue("ShortDescription", DESC);
        putValue("AcceleratorKey", KeyStroke.getKeyStroke("F1"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Runtime.getRuntime().exec("cmd /c Application\\Help\\help.html");
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
