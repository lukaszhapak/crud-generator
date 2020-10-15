package sample.generator.controller;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;
import sample.generator.view.ViewGenerator;

public class ControllerGenerator {


    private FileOperationHelper fileOperationHelper;
    private ViewGenerator viewGenerator;

    public ControllerGenerator() {
        fileOperationHelper = new FileOperationHelper();
        viewGenerator = new ViewGenerator();
    }

    public void generate(EntityData entityData) {
        String fileName = "controller/" + entityData.getEntityName() + "Controller.java";
        String data = "";

        if (entityData.getControllerType().equals("Rest")) {
            data = fileOperationHelper.getDataFromFile("/controller/rest-template.txt");
        } else if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("/controller/thymeleaf-template.txt");
            viewGenerator.generate(entityData);
        }

        data = data.replace("$package", entityData.getPackageName());
        data = data.replace("$Entity", entityData.getEntityName());
        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        fileOperationHelper.saveDataInFile(fileName, data);

    }
}
