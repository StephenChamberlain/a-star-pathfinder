package org.chamberlain.utils;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.chamberlain.MainFrame;
import org.chamberlain.ResourceLoader;
import org.chamberlain.dialogs.ErrorDialog;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLReader implements ErrorHandler {

    public Document parseXmlFile(String filePath) {
        try {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(new File(filePath));
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(ResourceLoader.getResourceAsStream("resources/xsd/GridFile.xsd"));
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            return document;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            errorDialog(e);
        }
        return null;

    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        errorDialog(exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        errorDialog(exception);
    }

    @Override
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
