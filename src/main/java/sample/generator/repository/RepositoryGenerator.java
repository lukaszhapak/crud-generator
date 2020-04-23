package sample.generator.repository;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;

public class RepositoryGenerator {

    private FileOperationHelper fileOperationHelper;

    public RepositoryGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        String entityName = entityData.getEntityName().substring(0, 1).toUpperCase() + entityData.getEntityName().substring(1).toLowerCase();
        String fileName = "repository/" + entityName + "Repository.java";

        String data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/repository/template.txt");

        data = data.replace("$packageName", entityData.getPackageName());
        data = data.replace("$Entity", entityName);

        data = data.replace("$idType", entityData.getId().getType());

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
