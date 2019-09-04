package org.chamberlain.dialogs;

import com.jogamp.opengl.GLCapabilities;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTable;

public class OpenGLCapsPanel extends JPanel {

    private JScrollPane capsScrollPane;

    private JXTable capsTable;

    public OpenGLCapsPanel(GLCapabilities caps) {
        initComponents();
        fillTable(caps);
        this.capsTable.getTableHeader().setReorderingAllowed(false);
    }

    private void fillTable(GLCapabilities caps) {
        String capsString = caps.toString(); // TODO: capabilities changed a lot, refactor this
        capsString = capsString.replaceAll("GLCapabilities ", "");
        capsString = capsString.replaceAll("\\[", "");
        capsString = capsString.replaceAll("\\]", "");
        capsString = capsString.replaceAll(":", ",");
        String[] capabilities = capsString.split(",");
        DefaultTableModel model = (DefaultTableModel) this.capsTable.getModel();
        System.out.println("OpenGL Capabilities");
        for (String capability : capabilities) {
            model.addRow(new Object[]{capability.trim()});
            System.out.println(capability.trim());
        }
    }

    private void initComponents() {
        this.capsScrollPane = new JScrollPane();
        this.capsTable = new JXTable();
        setLayout(new GridBagLayout());
        this.capsTable.setModel(new DefaultTableModel(new Object[0][], new String[]{"Property", "Value"}));
        this.capsTable.setEditable(false);
        this.capsScrollPane.setViewportView(this.capsTable);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(this.capsScrollPane, gridBagConstraints);
    }
}