/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajarroba;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static agendajarroba.Lista.conn;

public class SqlHorario implements DaoHorario{

    @Override
    public List<String> getDatos2() throws SQLException{//(4) este metodo es el que devuelve la lista
        List<String> listaDatos=new ArrayList<>();
        Statement s;
        Connection conn=null;
        conn=new Conexion().getConnection();//(5) este metodo conecta a la bd
        ResultSet rs = null;
        String sQuery="select*from horariomateria;";
        try
        {
            s=conn.createStatement();
            //s1 = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=s.executeQuery (sQuery);
            System.out.println("Ejecutó la consulta");
            SqlMateria aux=new SqlMateria();
            
            //llenamos la lista
            while (rs!=null && rs.next()) {
                int idMateria=0;
                idMateria=(rs.getInt("idMateria"));
                String materia=aux.sacarNombre(idMateria);
                listaDatos.add(materia);
                listaDatos.add(rs.getString("salon"));
                int numDia=rs.getInt("dia");
                String diaS="indef";
                switch(numDia){
                    case 1:{
                        diaS="lunes";
                    }break;
                    case 2:{
                        diaS="martes";
                    }break;
                    case 3:{
                        diaS="miercoles";
                    }break;
                    case 4:{
                        diaS="jueves";
                    }break;
                    case 5:{
                        diaS="viernes";
                    }break;
                    case 6:{
                        diaS="sabado";
                    }break;
                    case 7:{
                        diaS="domingo";
                    }break;
                    default:{
                        diaS="indef";
                    }break;
                }
                listaDatos.add(diaS);
                listaDatos.add(rs.getString("horaInicio"));
                listaDatos.add(rs.getString("horaFin"));
            }
        }
        catch (SQLException e)
        {
            System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
        }
        finally{
            conn.close();
        }
        return listaDatos;
    }
      
    @Override
    public int borrarDatos2() throws SQLException{
       String sentencia;
       sentencia="delete from horariomateria;";     
       System.out.println(sentencia);
           try  {                 
               conn=new Conexion().getConnection();
               Statement s = conn.createStatement (); 	       
               //updtae para sentencias INSERT, UPDATE O DELETE, que no regresan un conjunto de resultados 	       
               return s.executeUpdate(sentencia); //regresa 0 o el número de filas afectadas  si todo ok
 	   }
 	   catch (SQLException e)
 	   {
 	       System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
 	   }
           finally{
              conn.close();
           }
           //si llegó a este punto quiere decir que lanzó una excepción
 	   return -1;  //regresar -1 si hay error
    }

    @Override
    public int agregarDatos2(int idMateria,String salon,int dia,String horaInicio,String horaFin) throws SQLException{
       String sentencia;
       //el id es autoincremental
       //Iterator<String> it = datos.iterator(); 
       //while(it.hasNext()){
           sentencia="insert into horariomateria (idMateria,salon,dia,horaInicio,horaFin) values ('"+idMateria+"','"+salon+"','"+dia+"','"+horaInicio+"','"+horaFin+"');";     
       System.out.println(sentencia);
           try  {                 
               conn=new Conexion().getConnection();
               Statement s = conn.createStatement (); 	       
               //updtae para sentencias INSERT, UPDATE O DELETE, que no regresan un conjunto de resultados 	       
               return s.executeUpdate(sentencia); //regresa 0 o el número de filas afectadas  si todo ok
 	   }
 	   catch (SQLException e)
 	   {
 	       System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
 	   }
           finally{
              conn.close();
           }
     //  }
       
           //si llegó a este punto quiere decir que lanzó una excepción
 	   return -1;  //regresar -1 si hay error
    }

    public List<String> getMateriasH(int x) throws SQLException {
        List<String> listaDatos=new ArrayList<>();
        Statement s;
        Connection conn=null;
        conn=new Conexion().getConnection();//(5) este metodo conecta a la bd
        ResultSet rs = null;
        String sQuery="select*from horariomateria where idMateria="+x+";";
        try
        {
            s=conn.createStatement();
            //s1 = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=s.executeQuery (sQuery);
            System.out.println("Ejecutó la consulta");
            SqlMateria aux=new SqlMateria();
            
            //llenamos la lista
            while (rs!=null && rs.next()) {
                int idMateria=0;
                idMateria=(rs.getInt("idMateria"));
                String materia=aux.sacarNombre(idMateria);
                listaDatos.add(materia);
                listaDatos.add(rs.getString("salon"));
                int numDia=rs.getInt("dia");
                String diaS="indef";
                switch(numDia){
                    case 1:{
                        diaS="lunes";
                    }break;
                    case 2:{
                        diaS="martes";
                    }break;
                    case 3:{
                        diaS="miercoles";
                    }break;
                    case 4:{
                        diaS="jueves";
                    }break;
                    case 5:{
                        diaS="viernes";
                    }break;
                    case 6:{
                        diaS="sabado";
                    }break;
                    case 7:{
                        diaS="domingo";
                    }break;
                    default:{
                        diaS="indef";
                    }break;
                }
                listaDatos.add(diaS);
                listaDatos.add(rs.getString("horaInicio"));
                listaDatos.add(rs.getString("horaFin"));
            }
        }
        catch (SQLException e)
        {
            System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
        }
        finally{
            conn.close();
        }
        return listaDatos;
    }

}