package org.chamberlain;

import java.net.URL;
import javax.swing.ImageIcon;

public class IconLoader {

    public static ImageIcon createImageIcon(String path, String description) {
        URL imgURL = IconLoader.class.getResource("resources/" + path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}