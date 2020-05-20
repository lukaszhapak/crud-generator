package sample.generator.view;

import sample.entity.EntityData;
import sample.entity.Field;
import sample.file.FileOperationHelper;

public class ViewGenerator {

    private FileOperationHelper fileOperationHelper;

    public ViewGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        generateList(entityData);
        generateFragments(entityData);
        generateAdd(entityData);
        generateDetails(entityData);
        generateUpdate(entityData);
    }

    private void generateFragments(EntityData entityData) {
        String fileName = "templates/fragments/" + entityData.getEntityName().toLowerCase() + ".html";
        String data = "";

        if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/thymeleaf-fragments.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/jsp-list.txt");
        }

        data = data.replace("$Entity", entityData.getEntityName());
        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        fileOperationHelper.saveDataInFile(fileName, data);
    }

    private void generateList(EntityData entityData) {
        String fileName = "templates/" + entityData.getEntityName().toLowerCase() + "/list.html";
        String data = "";

        if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/thymeleaf-list.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/jsp-list.txt");
        }

        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        StringBuilder tableHeaders = new StringBuilder();
        StringBuilder tableData = new StringBuilder();

        for (Field field : entityData.getFields()) {
            tableHeaders.append("            <th>").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1).toLowerCase()).append("</th>\n");
            tableData.append("            <td th:text=\"${").append(entityData.getEntityName().toLowerCase()).append(".").append(field.getName()).append("}\"></td>\n");
        }

        data = data.replace("$tableHeaders", tableHeaders.substring(0, tableHeaders.length() - 1));
        data = data.replace("$tableData", tableData.substring(0, tableData.length() - 1));

        fileOperationHelper.saveDataInFile(fileName, data);
    }

    private void generateDetails(EntityData entityData) {
        String fileName = "templates/" + entityData.getEntityName().toLowerCase() + "/" + entityData.getEntityName().toLowerCase() + ".html";
        String data = "";

        if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/thymeleaf-details.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/jsp-details.txt");
        }

        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        StringBuilder tableRows = new StringBuilder();

        for (Field field : entityData.getFields()) {
            tableRows.append("        <tr>\n" + "            <td>").append(field.getName()).append(":</td>\n")
                    .append("            <td th:text=\"${").append(entityData.getEntityName().toLowerCase()).append(".")
                    .append(field.getName()).append("}\"></td>\n").append("        </tr>\n");
        }

        data = data.replace("$tableRows", tableRows.substring(0, tableRows.length() - 1));

        fileOperationHelper.saveDataInFile(fileName, data);
    }

    private void generateAdd(EntityData entityData) {
        String fileName = "templates/" + entityData.getEntityName().toLowerCase() + "/add.html";
        String data = "";

        if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/thymeleaf-add.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/jsp-add.txt");
        }

        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        StringBuilder fields = new StringBuilder();

        for (Field field : entityData.getFields()) {
            fields.append("        <label for=").append(field.getName()).append(">").append(field.getName()).append(":</label>\n");
            fields.append("        <input type=\"text\" class=\"form-control\" id=").append(field.getName()).append("  th:field=\"*{").append(field.getName()).append("}\">\n");
        }

        data = data.replace("$fields", fields.substring(0, fields.length() - 1));

        fileOperationHelper.saveDataInFile(fileName, data);
    }

    private void generateUpdate(EntityData entityData) {
        String fileName = "templates/" + entityData.getEntityName().toLowerCase() + "/update.html";
        String data = "";

        if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/thymeleaf-update.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/view/jsp-update.txt");
        }

        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        StringBuilder fields = new StringBuilder();

        for (Field field : entityData.getFields()) {
            fields.append("    <tr>\n");
            fields.append("        <td><label for=\"").append(field.getName()).append("\">").append(field.getName()).append(":</label></td>\n");
            fields.append("        <td><input type=\"text\" class=\"form-control\" id=\"").append(field.getName()).append("\"  th:field=\"*{").append(field.getName()).append("}\"></td>\n");
            fields.append("    </tr>\n");
        }

        data = data.replace("$fields", fields.substring(0, fields.length() - 1));

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
