/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persona;

import javafx.beans.property.SimpleStringProperty;


public class Horario {
    public SimpleStringProperty materia=new SimpleStringProperty();
    public SimpleStringProperty dia=new SimpleStringProperty();
    public SimpleStringProperty salon=new SimpleStringProperty();
    public SimpleStringProperty hora1=new SimpleStringProperty();
    public SimpleStringProperty hora2=new SimpleStringProperty();

    public String getMateria() {
        return materia.get();
    }

    public String getDia() {
        return dia.get();
    }

    public String getSalon() {
        return salon.get();
    }

    public String getHora1() {
        return hora1.get();
    }

    public String getHora2() {
        return hora2.get();
    }
    
    
}
