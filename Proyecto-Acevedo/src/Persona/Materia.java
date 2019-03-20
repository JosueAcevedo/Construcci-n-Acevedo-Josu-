/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persona;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Materia {
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty nrc = new SimpleStringProperty();
    public SimpleIntegerProperty horas1 = new SimpleIntegerProperty();
    public SimpleIntegerProperty horas2 = new SimpleIntegerProperty();
    public SimpleIntegerProperty creditos = new SimpleIntegerProperty();

    public String getNombre() {
        return nombre.get();
    }

    public String getNrc() {
        return nrc.get();
    }

    public int getHoras1() {
        return horas1.get();
    }


    public int getHoras2() {
        return horas2.get();
    }

    public int getCreditos() {
        return creditos.get();
    }

}
