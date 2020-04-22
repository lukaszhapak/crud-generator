package sample;

import javafx.scene.control.TextField;
import sample.entity.EntityData;
import sample.generator.entity.EntityGenerator;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Controller {

    public TextField entityName;
    public TextField packageName;

    private EntityGenerator entityGenerator;

    public void initialize() {
        entityGenerator = new EntityGenerator();
    }

    public void generate() {
        EntityData entityData = new EntityData();
        entityData.setPackageName(packageName.getText());
        entityData.setEntityName(entityName.getText());
        entityGenerator.generate(entityData);
    }

    public void showGeneratedFiles() {
        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/generated"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
