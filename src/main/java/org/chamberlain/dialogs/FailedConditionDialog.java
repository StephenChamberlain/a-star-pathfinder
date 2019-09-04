package org.chamberlain.dialogs;

import javax.swing.JComponent;
import org.chamberlain.ResourceLoader;

public class FailedConditionDialog extends CommonDialog {

    private final String message;

    public FailedConditionDialog(String message) {
        super("Failed calculation condition!");
        this.message = message;
    }

    @Override
    public JComponent createBannerPanel() {
        super.createBannerPanel();
        this.headerPanel.setTitleIcon(ResourceLoader.createImageIcon("error.png"));
        return this.headerPanel;
    }

    @Override
    public JComponent createContentPanel() {
        return new FailedConditionPanel(this.message);
    }
}