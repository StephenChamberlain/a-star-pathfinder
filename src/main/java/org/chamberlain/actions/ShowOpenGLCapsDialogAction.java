package org.chamberlain.actions;

import com.jogamp.opengl.GLCapabilities;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.chamberlain.ResourceLoader;
import org.chamberlain.MainFrame;
import org.chamberlain.dialogs.OpenGLCapsDialog;

public class ShowOpenGLCapsDialogAction extends AbstractAction {

    private static final String DESC = "Displays the capabilities of OpenGL on this system";

    private final GLCapabilities caps;

    public ShowOpenGLCapsDialogAction(GLCapabilities caps) {
        super("OpenGL Capabilities", ResourceLoader.createImageIcon("gl_16x16.png"));
        this.caps = caps;
        putValue("ShortDescription", DESC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OpenGLCapsDialog dialog = new OpenGLCapsDialog(this.caps);
        dialog.pack();
        dialog.setSize(440, 400);
        dialog.setLocationRelativeTo(MainFrame.mainFrame);
        dialog.setVisible(true);
    }
}