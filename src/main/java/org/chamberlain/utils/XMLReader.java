package org.chamberlain.utils;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.chamberlain.MainFrame;
import org.chamberlain.dialogs.ErrorDialog;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLReader implements ErrorHandler {

    public Document parseXmlFile(String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            documentBuilder.setEntityResolver(new DTDEntityResolver());
            documentBuilder.setErrorHandler(this);
            File file = new File(filename);
            return documentBuilder.parse(file);
        } catch (SAXException e) {
            errorDialog(e);
        } catch (ParserConfigurationException e) {
            errorDialog(e);
        } catch (IOException e) {
            errorDialog(e);
        }
        return null;
    }

    public void warning(SAXParseException exception) throws SAXException {
        errorDialog(exception);
    }

    public void error(SAXParseException exception) throws SAXException {
        errorDialog(exception);
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        errorDialog(exception);
    }

    private void errorDialog(Exception exception) {
        ErrorDialog error = new ErrorDialog();
        error.setText("Could not open grid file! " + exception.getMessage());
        error.pack();
        error.setLocationRelativeTo(MainFrame.mainFrame);
        error.setVisible(true);
        exception.printStackTrace();
    }
}