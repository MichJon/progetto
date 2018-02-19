package it.progettojorick.view;

import javax.swing.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileChooser extends JFrame {

//private String url;
private String nomeFile;

    public FileChooser(){

        super("file");

        JFileChooser fc = new JFileChooser("./images");
        int returnVal = fc.showOpenDialog(FileChooser.this);
//        File dir=new File("./images");
//        fc.setCurrentDirectory(dir);


        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            //this.url = file.getPath();
            this.nomeFile=file.getName();
//            System.out.println(nomeFile);

        }


    }

//    public String getUrl() {
//        return url;
//    }

    public String getNomeFile() {
        return nomeFile;
    }
}
