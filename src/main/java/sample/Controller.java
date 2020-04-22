package sample;

import javafx.scene.control.TextField;
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
        entityGenerator.generate(entityName.getText(), packageName.getText());
    }

    public void showGeneratedFiles() {
        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/generated"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
