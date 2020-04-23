package sample.generator.template;

import sample.entity.EntityData;
import sample.entity.Field;
import sample.file.FileOperationHelper;

public class TemplateGenerator {

    private FileOperationHelper fileOperationHelper;

    public TemplateGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        generateList(entityData);
        generateFragments(entityData);
    }

    private void generateFragments(EntityData entityData) {
        String fileName = "templates/fragments/" + entityData.getEntityName().toLowerCase() + ".html";
        String data = "";

        if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/template/thymeleaf-fragments.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/template/jsp-list.txt");

        }

        data = data.replace("$Entity", entityData.getEntityName());
        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        fileOperationHelper.saveDataInFile(fileName, data);
    }

    private void generateList(EntityData entityData) {
        String fileName = "templates/" + entityData.getEntityName().toLowerCase() + "/list.html";
        String data = "";

        if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/template/thymeleaf-list.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/template/jsp-list.txt");
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
}
