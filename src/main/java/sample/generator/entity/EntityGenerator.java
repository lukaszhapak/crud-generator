package sample.generator.entity;

import sample.file.FileOperationHelper;

public class EntityGenerator {

    private FileOperationHelper fileOperationHelper;

    public EntityGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(String entityName) {
        entityName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1).toLowerCase();
        String fileName = "entity/" + entityName + ".java";

        fileOperationHelper.saveDataInFile(fileName, entityName);
    }
}
