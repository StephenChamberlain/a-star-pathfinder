package org.chamberlain.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class NewGridPanel extends JPanel {

    private JSpinner columns;

    private JLabel columnsLabel;

    private JSpinner rows;

    private JLabel rowsLabel;

    public NewGridPanel(int currentColumns, int currentRows) {
        initComponents();
        this.columns.setValue(Integer.valueOf(currentColumns));
        this.rows.setValue(Integer.valueOf(currentRows));
    }

    public int getRows() {
        return ((Integer) this.rows.getValue()).intValue();
    }

    public int getColumns() {
        return ((Integer) this.columns.getValue()).intValue();
    }

    private void initComponents() {
        this.columnsLabel = new JLabel();
        this.columns = new JSpinner();
        this.rowsLabel = new JLabel();
        this.rows = new JSpinner();
        setLayout(new GridBagLayout());
        this.columnsLabel.setText("Columns");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.5D;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(this.columnsLabel, gridBagConstraints);
        this.columns.setModel(new SpinnerNumberModel(4, 4, 150, 1));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5D;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        add(this.columns, gridBagConstraints);
        this.rowsLabel.setText("Rows");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.5D;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        add(this.rowsLabel, gridBagConstraints);
        this.rows.setModel(new SpinnerNumberModel(4, 4, 150, 1));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5D;
        gridBagConstraints.insets = new Insets(0, 0, 5, 5);
        add(this.rows, gridBagConstraints);
    }
}