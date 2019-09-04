package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.chamberlain.Controller;
import org.chamberlain.ResourceLoader;
import org.chamberlain.MainFrame;
import org.chamberlain.model.Square;
import org.chamberlain.utils.XMLFileFilter;
import org.chamberlain.utils.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SaveGridAction extends AbstractAction {

    private static final String DESC = "Saves the current grid";

    private Controller aStar;

    public SaveGridAction(Controller aStar) {
        super("Save", ResourceLoader.createImageIcon("save.png"));
        this.aStar = aStar;
        putValue("ShortDescription", DESC);
        putValue("AcceleratorKey", KeyStroke.getKeyStroke(83, 128));
        putValue("MnemonicKey", Integer.valueOf(83));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        XMLFileFilter filter = new XMLFileFilter();
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Grids"));
        chooser.setFileSelectionMode(0);
        int returnVal = chooser.showSaveDialog(MainFrame.mainFrame);
        if (returnVal == 0) {
            String filePath = chooser.getSelectedFile().getAbsolutePath();
            if (!filePath.endsWith(".xml")) {
                filePath = filePath + ".xml";
            }
            buildXML(filePath);
        }
    }

    private void buildXML(String filePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            Document document = factory.newDocumentBuilder().newDocument();
            Element modelRoot = document.createElement("Model");
            Element rows = document.createElement("Rows");
            rows.appendChild(document.createTextNode("" + this.aStar.getModel().getRows()));
            Element columns = document.createElement("Columns");
            columns.appendChild(document.createTextNode("" + this.aStar.getModel().getColumns()));
            Element data = document.createElement("GridData");
            this.aStar.getModel().getGrid().stream().map(square -> {
                Element squareNode = document.createElement("Square");
                Element obstacle = document.createElement("Obstacle");
                obstacle.appendChild(document.createTextNode("" + (square.isObstacle() ? "1" : "0")));
                squareNode.appendChild(obstacle);
                Element start = document.createElement("StartPoint");
                start.appendChild(document.createTextNode("" + (square.isStartPoint() ? "1" : "0")));
                squareNode.appendChild(start);
                Element finish = document.createElement("FinishPoint");
                finish.appendChild(document.createTextNode("" + (square.isFinishPoint() ? "1" : "0")));
                squareNode.appendChild(finish);
                return squareNode;
            }).forEachOrdered(squareNode -> {
                data.appendChild(squareNode);
            });
            modelRoot.appendChild(rows);
            modelRoot.appendChild(columns);
            modelRoot.appendChild(data);
            document.appendChild(modelRoot);
            XMLWriter.writeXmlFile(document, filePath);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }
}