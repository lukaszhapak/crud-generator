package sample.generator.service;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;

public class ServiceGenerator {


    private FileOperationHelper fileOperationHelper;

    public ServiceGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        String fileName = "service/" + entityData.getEntityName() + "Service.java";

        String data = fileOperationHelper.getDataFromFile("/service/template.txt");

        data = data.replace("$packageName", entityData.getPackageName());
        data = data.replace("$Entity", entityData.getEntityName());

        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
