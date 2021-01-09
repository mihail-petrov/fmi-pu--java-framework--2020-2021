import Interfaces.ApplicationEntity;
import entities.Employ;
import managers.EntityManager;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;

public class Application {

    public static void main(String[] args) {

        // * all the fields
        // ApplicationEntity newEmploy = new Employ();

        // Class classBlueprint = newEmploy.getClass();
        // Class classBlueprint = Employ.class;

        //System.out.println(classBlueprint.getName());
        // System.out.println(classBlueprint.getInterfaces());

//        System.out.println("$$Interface collection : ");
//        Class[] interfaceCollection = classBlueprint.getInterfaces();
//        for(int i = 0; i < interfaceCollection.length; i++) {
//            System.out.println(interfaceCollection[i].getName());
//        }
//
//        System.out.println("$$Field collection : ") ;
//        Field[] fieldCollection = classBlueprint.getDeclaredFields();
//        for(int i = 0; i < fieldCollection.length; i++) {
//            System.out.println(fieldCollection[i].getName());
//        }

        // EntityManager.listAllPublicFields(Employ.class);

//          ORM Example
//        Employ employ = new Employ();
//        // INSERT
//
//        employ.setName("Mihail");
//        // UPDATE
//
//        employ.getUsername("Mihail");
//        // SELECT

        EntityManager.createEntity(Employ.class);
    }
}
