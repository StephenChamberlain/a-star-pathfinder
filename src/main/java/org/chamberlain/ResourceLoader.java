package org.chamberlain;

import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;

public class ResourceLoader {

    public static ImageIcon createImageIcon(String path) {
        URL imgURL = ResourceLoader.class.getResource("resources/" + path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }

    public static InputStream getResourceAsStream(String resourcePath) {
        return ResourceLoader.class.getResourceAsStream("resources/xsd/GridFile.xsd");
    }
}
