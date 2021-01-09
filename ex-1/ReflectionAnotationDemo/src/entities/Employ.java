package entities;
import Interfaces.ApplicationEntity;
import anotations.*;

@FmiEntity(table = "tb_employs") // Table name
public class Employ implements ApplicationEntity {

    @FmiEntityField( dataType = "VARCHAR", dataLength = "100")
    public String fname;

    @FmiEntityField( dataType = "VARCHAR", dataLength = "150")
    public String lname;

    @FmiEntityField( dataType = "INT")
    public int age;

    public String address;
    public String phone;
}