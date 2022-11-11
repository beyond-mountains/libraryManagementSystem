package Utils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public void readFileByLine(JTextArea textArea, File file) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader br = new BufferedReader(fileReader)
        ) {
            textArea.setText("");
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line+"\n");
                textArea.paintImmediately(textArea.getBounds());
            }
        } catch (IOException e) {
            e.printStackTrace();
            textArea.setText("文件读取失败...");
        }
    }
}
