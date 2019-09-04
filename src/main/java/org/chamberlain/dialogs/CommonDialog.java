package org.chamberlain.dialogs;

import com.jidesoft.dialog.BannerPanel;
import com.jidesoft.dialog.ButtonPanel;
import com.jidesoft.dialog.StandardDialog;
import com.jidesoft.plaf.UIDefaultsLookup;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.chamberlain.ResourceLoader;
import org.chamberlain.MainFrame;

public class CommonDialog extends StandardDialog {

    public static Color LIGHT_YELLOW = new Color(253, 255, 184);

    private String bannerTitle;

    private String bannerText;

    protected BannerPanel headerPanel;

    public CommonDialog(String title) {
        super(MainFrame.mainFrame, "");
        setBannerTitle(title);
    }

    @Override
    public JComponent createBannerPanel() {
        this.headerPanel = new BannerPanel(getBannerTitle(), getBannerText(), ResourceLoader.createImageIcon("warning.png"));
        this.headerPanel.setTitleIconLocation(7);
        this.headerPanel.setFont(new Font("Tahoma", 0, 11));
        this.headerPanel.setBackground(LIGHT_YELLOW);
        this.headerPanel.setBorder(BorderFactory.createBevelBorder(1));
        return this.headerPanel;
    }

    @Override
    public JComponent createContentPanel() {
        return new JPanel();
    }

    @Override
    public ButtonPanel createButtonPanel() {
        ButtonPanel buttonPanel = new ButtonPanel();
        JButton okButton = new JButton();
        JButton cancelButton = new JButton();
        buttonPanel.addButton(okButton);
        buttonPanel.addButton(cancelButton);
        okButton.setAction(new AbstractAction(UIDefaultsLookup.getString("OptionPane.okButtonText"),
                ResourceLoader.createImageIcon("ok.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommonDialog.this.setDialogResult(0);
                CommonDialog.this.setVisible(false);
                CommonDialog.this.dispose();
            }
        });
        cancelButton.setAction(new AbstractAction(UIDefaultsLookup.getString("OptionPane.cancelButtonText"), 
                ResourceLoader.createImageIcon("cancel.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommonDialog.this.setDialogResult(-1);
                CommonDialog.this.setVisible(false);
                CommonDialog.this.dispose();
            }
        });
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPanel;
    }

    public String getBannerTitle() {
        return this.bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
        setTitle(bannerTitle);
    }

    public String getBannerText() {
        return this.bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }
}
