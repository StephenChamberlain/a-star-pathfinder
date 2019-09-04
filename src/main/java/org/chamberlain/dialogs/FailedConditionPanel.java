package org.chamberlain.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class FailedConditionPanel extends JPanel {

    private JScrollPane jScrollPane1;

    private JTextArea messageArea;

    public FailedConditionPanel(String message) {
        initComponents();
        try {
            this.messageArea.getDocument().insertString(0, message, null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.messageArea = new JTextArea();
        setLayout(new GridBagLayout());
        this.messageArea.setColumns(20);
        this.messageArea.setEditable(false);
        this.messageArea.setLineWrap(true);
        this.messageArea.setRows(5);
        this.messageArea.setWrapStyleWord(true);
        this.jScrollPane1.setViewportView(this.messageArea);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(this.jScrollPane1, gridBagConstraints);
    }
}