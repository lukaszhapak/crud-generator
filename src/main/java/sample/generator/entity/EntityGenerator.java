package sample.generator.entity;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;

public class EntityGenerator {

    private FileOperationHelper fileOperationHelper;

    public EntityGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        String entityName = entityData.getEntityName().substring(0, 1).toUpperCase() + entityData.getEntityName().substring(1).toLowerCase();
        String fileName = "entity/" + entityName + ".java";

        String data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/entity/template.txt");

        data = data.replace("$packageName", entityData.getPackageName());
        data = data.replace("$entityName", entityName);

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
