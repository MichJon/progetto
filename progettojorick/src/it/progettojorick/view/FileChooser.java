package it.progettojorick.view;

import javax.swing.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileChooser extends JFrame {

private String url;

    public FileChooser(){

        super("file");

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(FileChooser.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            this.url = file.getPath();
            //System.out.println(url);

        }


    }

    public String getUrl() {
        return url;
    }
}
