package Persona;

import javafx.beans.property.SimpleStringProperty;


public class Alumno {
    
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellidoP = new SimpleStringProperty();
    public SimpleStringProperty apellidoM = new SimpleStringProperty();
    public SimpleStringProperty matricula = new SimpleStringProperty();
    
    
    public String getNombre(){
        return nombre.get();
    }
    
    public String getApellidoP(){
        return apellidoP.get();
    }
    
    public String getApellidoM (){
        return apellidoM.get();
    }
    
    public String getMatricula(){
        return matricula.get();
    }
    
}