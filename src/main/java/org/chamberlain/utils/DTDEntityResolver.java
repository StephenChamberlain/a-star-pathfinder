package org.chamberlain.utils;

import java.io.IOException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DTDEntityResolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (systemId.endsWith(".dtd")) {
            return new InputSource(System.getProperty("user.dir") + "\\Application\\GridFile.dtd");
        }
        return null;
    }
}