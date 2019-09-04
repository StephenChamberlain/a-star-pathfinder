package org.chamberlain.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class XMLWriter {

    public static boolean writeXmlFile(Document doc, String filePath) {
        if (doc == null) {
            throw new IllegalArgumentException("DOM document is null, cannot write to XML!");
        }
        if (filePath == null) {
            throw new IllegalArgumentException("Filepath is null, cannot write to XML!");
        }
        writeXML(doc, filePath);
        if ((new File(filePath)).exists()) {
            return true;
        }
        return false;
    }

    private static void writeXML(Document doc, String filePath) {
        Writer writer = null;
        try {
            writer = new FileWriter(new File(filePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            Source source = new DOMSource(doc);
            Result result = new StreamResult(writer);
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.setOutputProperty("indent", "yes");
            xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            try {
                xformer.transform(source, result);
            } catch (TransformerException ex) {
                ex.printStackTrace();
            }
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerFactoryConfigurationError ex) {
            ex.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Document createDomDocument() {
        try {
            var builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.newDocument();
        } catch (ParserConfigurationException e) {
            return null;
        }
    }
}