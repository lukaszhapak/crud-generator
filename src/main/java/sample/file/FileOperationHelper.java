package sample.file;

import java.io.*;

public class FileOperationHelper {

    public String getDataFromFile(String fileName) {
        StringBuilder result = new StringBuilder();
        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
