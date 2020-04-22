package sample.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperationHelper {

    public String getDataFromFile(String fileName) {
        StringBuilder result = new StringBuilder();

        try {
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNextLine()) {
                result.append(file.nextLine()).append("\n");
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

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
