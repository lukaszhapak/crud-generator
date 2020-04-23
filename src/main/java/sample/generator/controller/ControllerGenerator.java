package sample.generator.controller;

import sample.entity.EntityData;
import sample.file.FileOperationHelper;
import sample.generator.template.TemplateGenerator;

public class ControllerGenerator {


    private FileOperationHelper fileOperationHelper;
    private TemplateGenerator templateGenerator;

    public ControllerGenerator() {
        fileOperationHelper = new FileOperationHelper();
        templateGenerator = new TemplateGenerator();
    }

    public void generate(EntityData entityData) {
        String fileName = "controller/" + entityData.getEntityName() + "Controller.java";
        String data = "";

        if (entityData.getControllerType().equals("Rest")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/controller/rest-template.txt");
        } else if (entityData.getControllerType().equals("Thymeleaf")) {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/controller/thymeleaf-template.txt");
            templateGenerator.generate(entityData);
        } else {
            data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/controller/jsp-template.txt");
            templateGenerator.generate(entityData);
        }

        data = data.replace("$package", entityData.getPackageName());
        data = data.replace("$Entity", entityData.getEntityName());
        data = data.replace("$entity", entityData.getEntityName().toLowerCase());

        fileOperationHelper.saveDataInFile(fileName, data);

    }
}
