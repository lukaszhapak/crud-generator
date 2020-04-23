package sample.generator.service;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;

public class ServiceGenerator {


    private FileOperationHelper fileOperationHelper;

    public ServiceGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        String entityName = entityData.getEntityName().substring(0, 1).toUpperCase() + entityData.getEntityName().substring(1).toLowerCase();
        String fileName = "service/" + entityName + "Service.java";

        String data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/service/template.txt");

        data = data.replace("$packageName", entityData.getPackageName());
        data = data.replace("$Entity", entityName);

        data = data.replace("$entity", entityName.toLowerCase());

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}