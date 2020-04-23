package sample.generator.entity;

import sample.entity.EntityData;
import sample.entity.Field;
import sample.file.FileOperationHelper;

public class EntityGenerator {

    private FileOperationHelper fileOperationHelper;

    public EntityGenerator() {
        fileOperationHelper = new FileOperationHelper();
    }

    public void generate(EntityData entityData) {
        String fileName = "entity/" + entityData.getEntityName() + ".java";

        String data = fileOperationHelper.getDataFromFile("./src/main/java/sample/generator/entity/template.txt");

        data = data.replace("$packageName", entityData.getPackageName() + ".entity");
        data = data.replace("$Entity", entityData.getEntityName());

        String id = "    @Id\n";
        id += "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n";
        id += "    private " + entityData.getId().getType() + " " + entityData.getId().getName() + ";";
        data = data.replace("$id", id);

        StringBuilder fields = new StringBuilder();

        for (Field field : entityData.getFields()) {
            fields.append("    private ").append(field.getType()).append(" ").append(field.getName().toLowerCase()).append(";\n");
        }

        data = data.replace("$fields", fields);

        fileOperationHelper.saveDataInFile(fileName, data);
    }
}
