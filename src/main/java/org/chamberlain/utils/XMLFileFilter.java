package org.chamberlain.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class XMLFileFilter extends FileFilter {

    private ExtensionUtils utils = new ExtensionUtils();

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return false;
        }
        String extension = this.utils.getExtension(f);
        if (extension != null && extension.equals("xml")) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return "XML Grid Files";
    }

    class ExtensionUtils {

        public String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');
            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
    }
}