package sample;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.entity.EntityData;
import sample.entity.Field;
import sample.generator.controller.ControllerGenerator;
import sample.generator.entity.EntityGenerator;
import sample.generator.repository.RepositoryGenerator;
import sample.generator.service.ServiceGenerator;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Controller {

    public TextField entityName;
    public TextField packageName;
    public CheckBox idCheckBox;
    public ChoiceBox<String> fieldType1;
    public ChoiceBox<String> fieldType2;
    public ChoiceBox<String> fieldType3;
    public ChoiceBox<String> fieldType4;
    public ChoiceBox<String> fieldType5;
    public ChoiceBox<String> fieldType6;
    public ChoiceBox<String> fieldType7;
    public ChoiceBox<String> fieldType8;
    public ChoiceBox<String> fieldType9;
    public ChoiceBox<String> fieldType10;
    public TextField fieldName1;
    public TextField fieldName2;
    public TextField fieldName3;
    public TextField fieldName4;
    public TextField fieldName5;
    public TextField fieldName6;
    public TextField fieldName7;
    public TextField fieldName8;
    public TextField fieldName9;
    public TextField fieldName10;
    public CheckBox repositoryCheckBox;
    public CheckBox serviceCheckBox;
    public CheckBox controllerCheckBox;
    public ChoiceBox<String> controllerType;

    private EntityGenerator entityGenerator;
    private RepositoryGenerator repositoryGenerator;
    private ServiceGenerator serviceGenerator;
    private ControllerGenerator controllerGenerator;

    private HashMap<ChoiceBox<String>, TextField> fieldsInput;

    public void initialize() {
        entityGenerator = new EntityGenerator();
        repositoryGenerator = new RepositoryGenerator();
        serviceGenerator = new ServiceGenerator();
        controllerGenerator = new ControllerGenerator();

        fieldsInput = new LinkedHashMap<>();
        fieldsInput.put(fieldType1, fieldName1);
        fieldsInput.put(fieldType2, fieldName2);
        fieldsInput.put(fieldType3, fieldName3);
        fieldsInput.put(fieldType4, fieldName4);
        fieldsInput.put(fieldType5, fieldName5);
        fieldsInput.put(fieldType6, fieldName6);
        fieldsInput.put(fieldType7, fieldName7);
        fieldsInput.put(fieldType8, fieldName8);
        fieldsInput.put(fieldType9, fieldName9);
        fieldsInput.put(fieldType10, fieldName10);
    }

    public void generate() {
        EntityData entityData = new EntityData();
        entityData.setPackageName(packageName.getText());
        entityData.setEntityName(entityName.getText().substring(0, 1).toUpperCase() + entityName.getText().substring(1).toLowerCase());

        entityData.setId(new Field("Long", "id"));

        List<Field> fields = new ArrayList<>();
        fieldsInput.forEach((k, v) -> {
            if (k.getValue() != null) {
                fields.add(new Field(k.getValue(), v.getText()));
            }
        });
        entityData.setFields(fields);
        entityData.setControllerType(controllerType.getSelectionModel().getSelectedItem());

        entityGenerator.generate(entityData);
        repositoryGenerator.generate(entityData);
        serviceGenerator.generate(entityData);
        controllerGenerator.generate(entityData);
    }

    public void showGeneratedFiles() {
        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/generated"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
