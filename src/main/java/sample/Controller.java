package sample;

import javafx.scene.control.TextField;
import sample.generator.entity.EntityGenerator;

public class Controller {

    public TextField entityName;

    private EntityGenerator entityGenerator;

    public void initialize() {
        entityGenerator = new EntityGenerator();
    }

    public void generate() {
        entityGenerator.generate(entityName.getText());
    }
}
