package org.chamberlain.dialogs;

import com.jogamp.opengl.GLCapabilities;
import javax.swing.JComponent;
import org.chamberlain.ResourceLoader;

public class OpenGLCapsDialog extends CommonDialog {

    private final GLCapabilities caps;

    public OpenGLCapsDialog(GLCapabilities caps) {
        super("OpenGL Capabilities");
        this.caps = caps;
        setBannerText("Displays OpenGL capabilities as returned by the "
                + caps.getClass().toString().replaceAll("class ", "") + " class");
    }

    @Override
    public JComponent createBannerPanel() {
        super.createBannerPanel();
        this.headerPanel.setTitleIcon(ResourceLoader.createImageIcon("gl.png"));
        return this.headerPanel;
    }

    @Override
    public JComponent createContentPanel() {
        return new OpenGLCapsPanel(this.caps);
    }
}
