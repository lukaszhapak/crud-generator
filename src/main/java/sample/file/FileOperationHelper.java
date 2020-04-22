package sample.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileOperationHelper {

    public void saveDataInFile(String fileName, String data) {
        try {
            File file = new File(System.getProperty("user.dir") + "/generated/" + fileName);
            file.getParentFile().mkdirs();
            PrintWriter out = new PrintWriter(file);
            out.println(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
