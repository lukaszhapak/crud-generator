package sample.generator.entity;

import sample.file.FileOperationHelper;

public class EntityGenerator {

    private FileOperationHelper fileOperationHelper;

    public EntityGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(String entityName, String packageName) {
        entityName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1).toLowerCase();
        String fileName = "entity/" + entityName + ".java";

        String data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/entity/template.txt");

        data = data.replace("$packageName", packageName);
        data = data.replace("$entityName", entityName);

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
