package org.chamberlain.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import org.chamberlain.Controller;
import org.chamberlain.ResourceLoader;
import org.chamberlain.MainFrame;
import org.chamberlain.model.GridModel;
import org.chamberlain.model.Square;
import org.chamberlain.utils.XMLFileFilter;
import org.chamberlain.utils.XMLReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class OpenGridAction extends AbstractAction {

    private static final String DESC = "Opens an XML grid file";

    private Controller aStar;

    private XMLReader reader;

    private String filePath;

    public OpenGridAction(Controller aStar) {
        super("Open", ResourceLoader.createImageIcon("open.png"));
        this.aStar = aStar;
        putValue("ShortDescription", DESC);
        putValue("AcceleratorKey", KeyStroke.getKeyStroke(79, 128));
        putValue("MnemonicKey", Integer.valueOf(79));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (showOpenDialog()) {
            this.reader = new XMLReader();
            System.out.println("Opening: " + this.filePath);
            Document doc = this.reader.parseXmlFile(this.filePath);
            if (doc != null) {
                Element modelRoot = doc.getDocumentElement();
                NodeList row = modelRoot.getElementsByTagName("Rows");
                int rows = Integer.parseInt(row.item(0).getFirstChild().getNodeValue());
                NodeList column = modelRoot.getElementsByTagName("Columns");
                int cols = Integer.parseInt(column.item(0).getFirstChild().getNodeValue());
                GridModel model = new GridModel(rows, cols);
                NodeList gridData = modelRoot.getElementsByTagName("GridData");
                NodeList squares = ((Element) gridData.item(0)).getElementsByTagName("Square");
                for (int i = 0; i < squares.getLength(); i++) {
                    Element square = (Element) squares.item(i);
                    NodeList obstacle = square.getElementsByTagName("Obstacle");
                    ((Square) model.getGrid().get(i)).setObstacle(obstacle.item(0).getFirstChild().getNodeValue().equals("1"));
                    NodeList startPoint = square.getElementsByTagName("StartPoint");
                    ((Square) model.getGrid().get(i)).setStartPoint(startPoint.item(0).getFirstChild().getNodeValue().equals("1"));
                    NodeList finishPoint = square.getElementsByTagName("FinishPoint");
                    ((Square) model.getGrid().get(i)).setFinishPoint(finishPoint.item(0).getFirstChild().getNodeValue().equals("1"));
                }
                model.setGridName(this.filePath.substring(this.filePath.lastIndexOf("\\"), this.filePath.length()));
                this.aStar.setModel(model);
            }
        }
    }

    private boolean showOpenDialog() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new XMLFileFilter());
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Application\\Grids"));
        chooser.setFileSelectionMode(0);
        int returnVal = chooser.showOpenDialog(MainFrame.mainFrame);
        if (returnVal == 0) {
            this.filePath = chooser.getSelectedFile().getPath();
            return true;
        }
        this.filePath = null;
        return false;
    }
}