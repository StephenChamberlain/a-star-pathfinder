package org.chamberlain.dialogs;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorDialog extends CommonDialog {

    private String text;

    public ErrorDialog() {
        super("Error!");
    }

    @Override
    public JComponent createContentPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel(this.text));
        return panel;
    }

    public void setText(String text) {
        this.text = text;
    }
}