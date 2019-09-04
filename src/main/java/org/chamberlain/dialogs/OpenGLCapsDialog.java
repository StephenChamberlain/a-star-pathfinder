package org.chamberlain.dialogs;

import com.jogamp.opengl.GLCapabilities;
import javax.swing.JComponent;
import org.chamberlain.IconLoader;

public class OpenGLCapsDialog extends CommonDialog {

    private GLCapabilities caps;

    public OpenGLCapsDialog(GLCapabilities caps) {
        super("OpenGL Capabilities");
        this.caps = caps;
        setBannerText("Displays OpenGL capabilities as returned by the " + caps.getClass().toString().replaceAll("class ", "") + " class");
    }

    public JComponent createBannerPanel() {
        super.createBannerPanel();
        this.headerPanel.setTitleIcon(IconLoader.createImageIcon("gl.png", ""));
        return this.headerPanel;
    }

    public JComponent createContentPanel() {
        return new OpenGLCapsPanel(this.caps);
    }
}