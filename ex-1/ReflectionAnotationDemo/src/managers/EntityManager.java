package managers;

import anotations.FmiEntity;
import anotations.FmiEntityField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class EntityManager {

    public static void reflect() {

    }

    public static void listAllMethods(Class classReference) {

        Method[] fieldCollection = classReference.getMethods();
        for(int i = 0; i < fieldCollection.length; i++) {
            System.out.println(fieldCollection[i].getName());
        }
    }

    public static void listAllFields(Class classReference) {

        Field[] fieldCollection = classReference.getDeclaredFields();
        for(int i = 0; i < fieldCollection.length; i++) {
            System.out.println(fieldCollection[i].getName());
        }
    }

    public static void listAllPublicFields(Class classReference) {

        Field[] fieldCollection = classReference.getFields();
        for(int i = 0; i < fieldCollection.length; i++) {
            System.out.println(fieldCollection[i].getName());
        }
    }

    public static void createEntity(Class classReference) {

        String query = "CREATE TABLE IF NOT EXISTS ";

        // CREATE TABLE IF NOT EXISTS {TABLE_NAME} (
        //  f1 VARCHAR,
        //  f2 INT
        // );

        // * Convention over Configuration
        // * Configuration over Convention

        // 1. Get class name as table
        try {
            String tableName = getEntityTableName(classReference);
            query += " " + tableName + " (";
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 2. Get annotated fields - data types
        Field[] publicFiledColumnCollection = classReference.getFields();
        for(int i = 0; i < publicFiledColumnCollection.length; i++) {

            Field activeField = publicFiledColumnCollection[i];
            FmiEntityField activeFieldAnnotation = activeField.getAnnotation(FmiEntityField.class);
            if(activeFieldAnnotation != null) {
                query += activeField.getName() + " " + activeFieldAnnotation.dataType() + "(" + activeFieldAnnotation.dataLength() + "),";
            }
        }

        query += ");";
        System.out.println(query);
    }

    private static String getEntityTableName(Class classReference) throws NoSuchMethodException {

        FmiEntity annotationReference = (FmiEntity) classReference.getAnnotation(FmiEntity.class);

        Method methodReference = annotationReference.annotationType().getMethod("table");
        String defaultAnnotationValue = (String) methodReference.getDefaultValue();

        if(annotationReference.table().length() == 0) {
            return classReference.getSimpleName().toLowerCase();
        }

        return annotationReference.table();
    }

    public static void insert(Class classReference) {
        // INSERT INTO {TABLE_NAME}(f1, f2, f3) VALUES(v1, v2, v3);
    }

    public static void delete(Class classReference) {

        // f1 -> primary field
        // DELETE FROM {TABLE_NAME} WHERE f1 = v1
    }

    public static void update(Class classReference) {
        // UPDATE {TABLE_NAME} SET f1 = v1
    }

    public static void select(Class classReference) {
        // SELECT * FROM {TABLE_NAME}
    }
}
