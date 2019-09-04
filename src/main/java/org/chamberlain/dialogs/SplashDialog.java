package org.chamberlain.dialogs;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashDialog extends JDialog {

    private JLabel icon;

    private JLabel label;

    private JPanel panel;

    public SplashDialog(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        this.panel = new JPanel();
        this.label = new JLabel();
        this.icon = new JLabel();
        getContentPane().setLayout(new GridBagLayout());
        setDefaultCloseOperation(2);
        setBackground(Color.white);
        setUndecorated(true);
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(new Color(255, 255, 255));
        this.panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.label.setBackground(new Color(255, 255, 255));
        this.label.setText("Loading program...");
        this.label.setOpaque(true);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        this.panel.add(this.label, gridBagConstraints);
        this.icon.setBackground(new Color(255, 255, 255));
        this.icon.setIcon(new ImageIcon(getClass().getResource("/org/chamberlain/resources/splash.gif")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.panel.add(this.icon, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        getContentPane().add(this.panel, gridBagConstraints);
        pack();
    }
}