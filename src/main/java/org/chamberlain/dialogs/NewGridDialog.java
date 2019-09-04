package org.chamberlain.dialogs;

import javax.swing.JComponent;
import org.chamberlain.ResourceLoader;

public class NewGridDialog extends CommonDialog {

    private final int currentColumns;
    private final int currentRows;
    
    private NewGridPanel panel;

    public NewGridDialog(int currentColumns, int currentRows) {
        super("New grid");
        this.currentColumns = currentColumns;
        this.currentRows = currentRows;
        setBannerText("Please specify the size of the new grid in columns (width) and rows (height)");
    }

    @Override
    public JComponent createBannerPanel() {
        super.createBannerPanel();
        this.headerPanel.setTitleIcon(ResourceLoader.createImageIcon("new32.png"));
        return this.headerPanel;
    }

    @Override
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