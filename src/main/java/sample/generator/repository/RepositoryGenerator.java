package sample.generator.repository;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;

public class RepositoryGenerator {

    private FileOperationHelper fileOperationHelper;

    public RepositoryGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        String fileName = "repository/" + entityData.getEntityName() + "Repository.java";

        String data = fileOperationHelper.getDataFromFile("/repository/template.txt");

        data = data.replace("$packageName", entityData.getPackageName());
        data = data.replace("$Entity", entityData.getEntityName());

        data = data.replace("$idType", entityData.getId().getType());

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
