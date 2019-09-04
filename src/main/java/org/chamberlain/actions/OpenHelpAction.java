package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.chamberlain.IconLoader;

public class OpenHelpAction extends AbstractAction {

    private static String desc = "Opens an XML grid file";

    public OpenHelpAction() {
        super("Help", IconLoader.createImageIcon("help.png", ""));
        putValue("ShortDescription", desc);
        putValue("AcceleratorKey", KeyStroke.getKeyStroke("F1"));
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Process proc = Runtime.getRuntime().exec("cmd /c Application\\Help\\help.html");
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null);
            int exitVal = proc.waitFor();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}