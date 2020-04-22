package sample.entity;

import lombok.Data;

import java.util.List;

@Data
public class EntityData {

    private String packageName;
    private String entityName;
    private Field id;
    private List<Field> fields;
}
