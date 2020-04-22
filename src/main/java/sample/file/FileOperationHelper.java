package sample.file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileOperationHelper {

    public void saveDataInFile(String fileName, String data) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.println(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
