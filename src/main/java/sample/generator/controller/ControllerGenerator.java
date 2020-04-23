package sample.generator.controller;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;

public class ControllerGenerator {


    private FileOperationHelper fileOperationHelper;

    public ControllerGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        String entityName = entityData.getEntityName().substring(0, 1).toUpperCase() + entityData.getEntityName().substring(1).toLowerCase();
        String fileName = "controller/" + entityName + "Controller.java";
        String data = "";

        if (entityData.getControllerType().equals("Rest")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/controller/rest-template.txt");
        } else if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/controller/thymeleaf-template.txt");
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/controller/jsp-template.txt");
        }

        data = data.replace("$package", entityData.getPackageName());
        data = data.replace("$Entity", entityName);
        data = data.replace("$entity", entityName.toLowerCase());

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
