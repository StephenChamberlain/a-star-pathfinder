package org.chamberlain.dialogs;

import javax.swing.JComponent;
import org.chamberlain.IconLoader;

public class NewGridDialog extends CommonDialog {

    private NewGridPanel panel;

    private int currentColumns;

    private int currentRows;

    public NewGridDialog(int currentColumns, int currentRows) {
        super("New grid");
        this.currentColumns = currentColumns;
        this.currentRows = currentRows;
        setBannerText("Please specify the size of the new grid in columns (width) and rows (height)");
    }

    public JComponent createBannerPanel() {
        super.createBannerPanel();
        this.headerPanel.setTitleIcon(IconLoader.createImageIcon("new32.png", ""));
        return this.headerPanel;
    }

    public JComponent createContentPanel() {
        this.panel = new NewGridPanel(this.currentColumns, this.currentRows);
        return this.panel;
    }

    public int getRows() {
        return this.panel.getRows();
    }

    public int getColumns() {
        return this.panel.getColumns();
    }
}