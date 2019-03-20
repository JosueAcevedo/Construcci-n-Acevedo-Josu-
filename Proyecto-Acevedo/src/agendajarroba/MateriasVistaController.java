/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajarroba;

import Persona.Horario;
import Persona.Materia;
import Persona.Alumno;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author rockm
 */
public class MateriasVistaController implements Initializable {
    @FXML private Button aniadirBT;
    @FXML private Button modificarBT;
    @FXML private Button eliminarBT;
    @FXML private Button nuevoBT;
    @FXML private Button guardarBT;
    @FXML private Button cargarBT;
    
    // Declaramos los textfileds
    @FXML private TextField nombreTF;
    @FXML private TextField nrcTF;
    @FXML private TextField horasTeoricasTF;
    @FXML private TextField horasPracticasTF;
    @FXML private TextField creditosTF;
    
    // Declaramos la tabla y las columnas
    @FXML private TableView<Materia> materiasTV;
    @FXML private TableColumn nombreTC;
    @FXML private TableColumn nrcTC;
    @FXML private TableColumn horasTeoricasTC;
    @FXML private TableColumn horasPracticasTC;
    @FXML private TableColumn creditosTC;
    ObservableList<Materia> materias;
    
    private int posicionMateriaEnTabla;
    @FXML private Font x1;
    @FXML private Font x2;
    @FXML private MenuBar menuMB;
    @FXML private Menu listasM;
    @FXML private MenuItem alumnosMI;
    
    
    //declaracion de horarios
    
    //declaramos los botones
    @FXML private Button aniadirBT2;
    @FXML private Button modificarBT2;
    @FXML private Button eliminarBT2;
    @FXML private Button nuevoBT2;
    //declaramos los text fields
    @FXML private TextField salonTF;
    @FXML private TextField diaTF;
    @FXML private TextField hora1TF;
    @FXML private TextField hora2TF;
    //declaramos la tabla y columnas
    @FXML private TableView<Horario> horariosTV;
    @FXML private TableColumn salonTC;
    @FXML private TableColumn diaTC;
    @FXML private TableColumn hora1TC;
    @FXML private TableColumn hora2TC;
    //declaramos extras
    ObservableList<Horario> horarios;
    
    private int posicionHorarioEnTabla;
    @FXML
    private TableColumn materiaTC;
    @FXML
    private Button mostrarBT;

    
    
    
    /**
     * metodo que realiza las acciones para agregar materia
     * @param event 
     */
    @FXML private void nuevo(ActionEvent event) {
        nombreTF.setText("");
        nrcTF.setText("");
        horasTeoricasTF.setText("");
        horasPracticasTF.setText("");
        creditosTF.setText("");
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);
        aniadirBT.setDisable(false);
    }
    
    /**
     * metodo que añade una nueva materia
     * @param event 
     */
    @FXML private void aniadir(ActionEvent event) {
        Materia materia = new Materia();
        materia.nombre.set(nombreTF.getText());
        materia.nrc.set(nrcTF.getText());
        materia.horas1.set(Integer.parseInt(horasTeoricasTF.getText()));
        materia.horas2.set(Integer.parseInt(horasPracticasTF.getText()));
        materia.creditos.set(Integer.parseInt(creditosTF.getText()));
        materias.add(materia);
    }
    
    /**
     * metodo que modifica el valor de un elemento
     * @param event 
     */
    @FXML private void modificar(ActionEvent event) {
        Materia materia = new Materia();
        materia.nombre.set(nombreTF.getText());
        materia.nrc.set(nrcTF.getText());
        materia.horas1.set(Integer.parseInt(horasTeoricasTF.getText()));
        materia.horas2.set(Integer.parseInt(horasPracticasTF.getText()));
        materia.creditos.set(Integer.parseInt(creditosTF.getText()));
        materias.set(posicionMateriaEnTabla, materia);
    }
    
    /**
     * metodo que elimina un elemento de la tabla
     * @param event 
     */
    @FXML
    private void eliminar(ActionEvent event) {
        materias.remove(posicionMateriaEnTabla);
    }
    
    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<Materia> selectorTablaMaterias =
            new ListChangeListener<Materia>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Materia> c) {
                    try {
                        ponerMateriaSeleccionada();
                    } catch (SQLException ex) {
                        Logger.getLogger(MateriasVistaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
    
    
     /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    
    public Materia getTablaMateriaSeleccionada() {
        if (materiasTV != null) {
            List<Materia> tabla = materiasTV.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Materia competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void ponerMateriaSeleccionada() throws SQLException {
        final Materia materia = getTablaMateriaSeleccionada();
        posicionMateriaEnTabla = materias.indexOf(materia);

        if (materia != null) {

            // Pongo los textFields con los datos correspondientes
            nombreTF.setText(materia.getNombre());
            nrcTF.setText(materia.getNrc());
            horasTeoricasTF.setText(String.valueOf(materia.getHoras1()));
            horasPracticasTF.setText(String.valueOf(materia.getHoras2()));
            creditosTF.setText(String.valueOf(materia.getCreditos()));

            // Pongo los botones en su estado correspondiente
            modificarBT.setDisable(false);
            eliminarBT.setDisable(false);
            aniadirBT.setDisable(true);

        }
        this.cargarMaterias(materias.get(posicionMateriaEnTabla).getNombre());
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<Horario> selectorTablaHorarios =
            new ListChangeListener<Horario>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Horario> c) {
                    ponerHorarioSeleccionado();
                }
            };
    
    
     /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    
    public Horario getTablaHorarioSeleccionado() {
        if (materiasTV != null) {
            List<Horario> tabla = horariosTV.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Horario competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void ponerHorarioSeleccionado() {
        final Horario horario = getTablaHorarioSeleccionado();
        posicionHorarioEnTabla = horarios.indexOf(horario);

        if (horario != null) {

            // Pongo los textFields con los datos correspondientes
            salonTF.setText(horario.getSalon());
            diaTF.setText(horario.getDia());
            hora1TF.setText(String.valueOf(horario.getHora1()));
            hora2TF.setText(String.valueOf(horario.getHora2()));

            // Pongo los botones en su estado correspondiente
            modificarBT2.setDisable(false);
            eliminarBT2.setDisable(false);
            aniadirBT2.setDisable(true);

        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * metodo para inicializar la tabla de horarios
     */
    private void inicializarTablaHorarios(){
        salonTC.setCellValueFactory(new PropertyValueFactory<Horario, String>("salon"));
        diaTC.setCellValueFactory(new PropertyValueFactory<Horario, String>("dia"));
        hora1TC.setCellValueFactory(new PropertyValueFactory<Horario, String>("hora1"));
        hora2TC.setCellValueFactory(new PropertyValueFactory<Horario, String>("hora2"));
        materiaTC.setCellValueFactory(new PropertyValueFactory<Horario, String>("materia"));
        

        horarios = FXCollections.observableArrayList();
        horariosTV.setItems(horarios);
    }
        /**
     * Método para inicializar la tabla
     */
    private void inicializarTablaMaterias() {
        nombreTC.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
        nrcTC.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nrc"));
        horasTeoricasTC.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("horas1"));
        horasPracticasTC.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("horas2"));
        creditosTC.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("creditos"));
        

        materias = FXCollections.observableArrayList();
        materiasTV.setItems(materias);
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializamos la tabla
        this.inicializarTablaMaterias();

        // Ponemos estos dos botones para que no se puedan seleccionar
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);

        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<Materia> tablaPersonaSel = materiasTV.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaMaterias);
        
        // Inicializamos la tabla de horarios
        this.inicializarTablaHorarios();

        // Ponemos estos dos botones para que no se puedan seleccionar
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);

        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<Horario> tablaHorariosSel = horariosTV.getSelectionModel().getSelectedItems();
        tablaHorariosSel.addListener(selectorTablaHorarios);

    }    

    @FXML
    private void cargarA(MouseEvent event) {
        SqlMateria sql=new SqlMateria();
        materias.clear();
        List<String> listaDatos=null;
        try {
            listaDatos = sql.getDatos2();//(3) metodo que manda a traer una lista de la consulta de las rentas de noviembre
        } catch (SQLException ex) {
            Logger.getLogger(VistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final ObservableList<Materia> tablaPersonaSel = materiasTV.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaMaterias);
        Iterator<String> it = listaDatos.iterator();
        while(it.hasNext()){
            Materia p1 = new Materia();
            p1.nombre.set(it.next());
            p1.nrc.set(it.next());
            p1.horas1.set(Integer.parseInt(it.next()));
            p1.horas2.set(Integer.parseInt(it.next()));
            p1.creditos.set(Integer.parseInt(it.next()));
            materias.add(p1);
        }
    }

    @FXML
    private void guardarA(MouseEvent event) throws SQLException {
        this.mos1();
        SqlMateria sql=new SqlMateria();
        SqlHorario sql2=new SqlHorario();
        sql2.borrarDatos2();
        sql.borrarDatos2();
        
        ///
        final ObservableList<Materia> tablaPersonaSel = materiasTV.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaMaterias);
        List<String> datos=null;
        
        for (int i = 0; i < materiasTV.getItems().size(); i++){
            String nombre=(String) materiasTV.getItems().get(i).getNombre();
            String nrc=(String) materiasTV.getItems().get(i).getNrc();
            int horas1= materiasTV.getItems().get(i).getHoras1();
            int horas2= materiasTV.getItems().get(i).getHoras2();
            int creditos= materiasTV.getItems().get(i).getCreditos();
            int q=sql.agregarDatos2(nombre,nrc,horas1,horas2,creditos);
        }
        /////
        final ObservableList<Horario> tablaHorarioSel = horariosTV.getSelectionModel().getSelectedItems();
        tablaHorarioSel.addListener(selectorTablaHorarios);
        for (int i = 0; i < horariosTV.getItems().size(); i++){
            String materia=horarios.get(i).getMateria();
            //System.out.println("linea 352 el nombre de la materia que se busca es: "+materia);
            int materia1=sql.getID(materia);
            //System.out.println("linea 354 el id que se usa para la metria es :"+materia1);
            String salon=(String) horariosTV.getItems().get(i).getSalon();
           
            String dia= horariosTV.getItems().get(i).getDia(); 
            System.out.println("linea 358 materiascontroller el dia que se saca es: "+dia);
            int dia1=-1;
            switch (dia){
                case "lunes":{
                    dia1=1;
                }break;
                case "martes":{
                    dia1=2;
                }break;
                case "miercoles":{
                    dia1=3;
                }break;
                case "jueves":{
                    dia1=4;
                }break;
                case "viernes":{
                    dia1=5;
                }break;
                case "sabado":{
                    dia1=6;
                }break;
                case "domingo":{
                    dia1=7;
                }break;
                case "default":{
                    dia1=-1;
                }break;
            }
            System.out.println("linea 386 materiacontroller el dia que se mete es: "+dia1);
            String hora1= horariosTV.getItems().get(i).getHora1();
            String hora2= horariosTV.getItems().get(i).getHora2();
            int q=sql2.agregarDatos2(materia1,salon,dia1,hora1,hora2);
        }
        ////////////////////////////////////////////////////////////
        
        
        
        
        
    }







    @FXML
    private void listadoMaterias(ActionEvent event) {
        //
    }

    @FXML
    private void aniadir2(ActionEvent event) throws SQLException {
        SqlHorario sql=new SqlHorario();
        SqlMateria sql2=new SqlMateria();
        Horario horario = new Horario();
        horario.salon.set(salonTF.getText());
        horario.dia.set(diaTF.getText());
        horario.hora1.set(hora1TF.getText());
        horario.hora2.set(hora2TF.getText());
        horario.materia.set(materias.get(posicionMateriaEnTabla).getNombre());
        System.out.println(materias.get(posicionMateriaEnTabla).getNombre());
        horarios.add(horario);
        int x=sql2.getID(horario.getMateria());
        sql.agregarDatos2(x,horario.getSalon(),this.getNumeroDia(horario.getDia()),horario.getHora1(),horario.getHora2());
    }
    private int getNumeroDia(String dia){
        int dia1=-1;
        switch (dia){
            case "lunes":{
                dia1=1;
            }break;
            case "martes":{
                dia1=2;
            }break;
            case "miercoles":{
                dia1=3;
            }break;
            case "jueves":{
                dia1=4;
            }break;
            case "viernes":{
                dia1=5;
            }break;
            case "sabado":{
                dia1=6;
            }break;
            case "domingo":{
                dia1=7;
            }break;
            case "default":{
                dia1=-1;
            }break;
        }
        return dia1;
    }

    @FXML
    private void modificar2(ActionEvent event) {
        Horario horario = new Horario();
        horario.salon.set(salonTF.getText());
        horario.dia.set(diaTF.getText());
        horario.hora1.set(hora1TF.getText());
        horario.hora2.set(hora2TF.getText());
        horarios.set(posicionHorarioEnTabla, horario);
    }

    @FXML
    private void eliminar2(ActionEvent event) {
        horarios.remove(posicionHorarioEnTabla);
    }

    @FXML
    private void nuevo2(ActionEvent event) {
        salonTF.setText("");
        diaTF.setText("");
        hora1TF.setText("");
        hora2TF.setText("");
        modificarBT2.setDisable(true);
        eliminarBT2.setDisable(true);
        aniadirBT2.setDisable(false);
    }
    private void cargarMaterias (String materia) throws SQLException{
        SqlHorario sql2=new SqlHorario();
        SqlMateria sql=new SqlMateria();
        int x=sql.getID(materia);
        horarios.clear();
        List<String> listaDatos=sql2.getMateriasH(x);
        final ObservableList<Horario> tablaHorarioSel = horariosTV.getSelectionModel().getSelectedItems();
        tablaHorarioSel.addListener(selectorTablaHorarios);
        Iterator<String> it = listaDatos.iterator();
        while(it.hasNext()){
            Horario p1 = new Horario();
            p1.materia.set(it.next());
            p1.salon.set(it.next());
            p1.dia.set(it.next());
            p1.hora1.set(it.next());
            p1.hora2.set(it.next());
            horarios.add(p1);
        }
        
    }

    @FXML
    private void mos(ActionEvent event) {
        SqlHorario sql = new SqlHorario();
        horarios.clear();
        List<String> listaDatos=null;
        try {
            listaDatos = sql.getDatos2();//(3) metodo que manda a traer una lista de la consulta de las rentas de noviembre
        } catch (SQLException ex) {
            Logger.getLogger(VistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final ObservableList<Horario> tablaHorarioSel = horariosTV.getSelectionModel().getSelectedItems();
        tablaHorarioSel.addListener(selectorTablaHorarios);
        Iterator<String> it = listaDatos.iterator();
        while(it.hasNext()){
            Horario p1 = new Horario();
            p1.materia.set(it.next());
            p1.salon.set(it.next());
            p1.dia.set(it.next());
            p1.hora1.set(it.next());
            p1.hora2.set(it.next());
            horarios.add(p1);
        }
        
    }
    private void mos1() {
        SqlHorario sql = new SqlHorario();
        horarios.clear();
        List<String> listaDatos=null;
        try {
            listaDatos = sql.getDatos2();//(3) metodo que manda a traer una lista de la consulta de las rentas de noviembre
        } catch (SQLException ex) {
            Logger.getLogger(VistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final ObservableList<Horario> tablaHorarioSel = horariosTV.getSelectionModel().getSelectedItems();
        tablaHorarioSel.addListener(selectorTablaHorarios);
        Iterator<String> it = listaDatos.iterator();
        while(it.hasNext()){
            Horario p1 = new Horario();
            p1.materia.set(it.next());
            p1.salon.set(it.next());
            p1.dia.set(it.next());
            p1.hora1.set(it.next());
            p1.hora2.set(it.next());
            horarios.add(p1);
        }
        
    }
    
}
