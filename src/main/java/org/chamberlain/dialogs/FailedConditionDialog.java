package org.chamberlain.dialogs;

import javax.swing.JComponent;
import org.chamberlain.IconLoader;

public class FailedConditionDialog extends CommonDialog {

    private String message;

    public FailedConditionDialog(String message) {
        super("Failed calculation condition!");
        this.message = message;
    }

    public JComponent createBannerPanel() {
        super.createBannerPanel();
        this.headerPanel.setTitleIcon(IconLoader.createImageIcon("error.png", ""));
        return this.headerPanel;
    }

    public JComponent createContentPanel() {
        return new FailedConditionPanel(this.message);
    }
}