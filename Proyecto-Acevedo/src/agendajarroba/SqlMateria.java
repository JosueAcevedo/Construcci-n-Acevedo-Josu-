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
import java.util.Iterator;


public class SqlMateria implements DaoMaterias{

    @Override
    public List<String> getDatos2() throws SQLException{//(4) este metodo es el que devuelve la lista
        List<String> listaDatos=new ArrayList<>();
        Statement s;
        Connection conn=null;
        conn=new Conexion().getConnection();//(5) este metodo conecta a la bd
        ResultSet rs = null;
        String sQuery="select*from materias;";
        try
        {
            s=conn.createStatement();
            //s1 = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=s.executeQuery (sQuery);
            System.out.println("Ejecutó la consulta");
            //llenamos la lista
            while (rs!=null && rs.next()) {
                listaDatos.add(rs.getString("nombre"));
                listaDatos.add(rs.getString("nrc"));
                listaDatos.add(String.valueOf(rs.getInt("horas1")));
                listaDatos.add(String.valueOf(rs.getInt("horas2")));
                listaDatos.add(String.valueOf(rs.getInt("creditos")));
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
       sentencia="delete from materias;";     
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
    public int agregarDatos2(String nombre,String nrc,int horas1,int horas2,int creditos) throws SQLException{
       String sentencia;
       //el id es autoincremental
       //Iterator<String> it = datos.iterator(); 
       //while(it.hasNext()){
           sentencia="insert into materias (nombre,nrc,horas1,horas2,creditos) values ('"+nombre+"','"+nrc+"','"+horas1+"','"+horas2+"','"+creditos+"');";     
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
    @Override
    public String sacarNombre(int idMateria) throws SQLException{
        String nombre="indef";
        List<String> listaDatos=new ArrayList<>();
        Statement s;
        Connection conn=null;
        conn=new Conexion().getConnection();//(5) este metodo conecta a la bd
        ResultSet rs = null;
        String sQuery="select * from materias;";
        try
        {
            s=conn.createStatement();
            //s1 = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=s.executeQuery (sQuery);
            System.out.println("Ejecutó la consulta");
            //llenamos la lista
            while (rs!=null && rs.next()) {
                listaDatos.add(String.valueOf(rs.getInt("id")));
                listaDatos.add(rs.getString("nombre"));
            }
        }
        catch (SQLException e)
        {
            System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
        }
        finally{
            conn.close();
        }
        Iterator<String> it = listaDatos.iterator();
        while(it.hasNext()){
            int n=Integer.parseInt(it.next());
            String x=it.next();
            if(n==idMateria){
                nombre=x;
            }
        }
        return nombre;
    }
    public int getID(String materia) throws SQLException{
        int x=-1;
        List<String> listaDatos=new ArrayList<>();
        Statement s;
        Connection conn=null;
        conn=new Conexion().getConnection();//(5) este metodo conecta a la bd
        ResultSet rs = null;
        String sQuery="select * from materias;";
        try
        {
            s=conn.createStatement();
            //s1 = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=s.executeQuery (sQuery);
            System.out.println("Ejecutó la consulta");
            //llenamos la lista
            while (rs!=null && rs.next()) {
                listaDatos.add(rs.getString("nombre"));
                listaDatos.add(String.valueOf(rs.getInt("id")));
            }
        }
        catch (SQLException e)
        {
            System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
        }
        finally{
            conn.close();
        }
        Iterator<String> it = listaDatos.iterator();
        while(it.hasNext()){
            String temp=it.next();
            int temp2=Integer.parseInt(it.next());
            //System.out.println("SqlMateria Linea 173 el nombre de la meteria que se busca es: "+temp+"  y el numero de id para regresar es:"+temp2);
            if(temp == null ? materia == null : temp.equals(materia)){
                x=temp2;
            }
        }
        return x;
    }

    public List<String> getMateriasH(int x) throws SQLException {
        List<String> listaDatos=new ArrayList<>();
        Statement s;
        Connection conn=null;
        conn=new Conexion().getConnection();//(5) este metodo conecta a la bd
        ResultSet rs = null;
        String sQuery="select*from horarios where idMateria=x;";
        try
        {
            s=conn.createStatement();
            //s1 = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=s.executeQuery (sQuery);
            System.out.println("Ejecutó la consulta");
            //llenamos la lista
            while (rs!=null && rs.next()) {
                listaDatos.add(rs.getString("nombre"));
                listaDatos.add(rs.getString("nrc"));
                listaDatos.add(String.valueOf(rs.getInt("horas1")));
                listaDatos.add(String.valueOf(rs.getInt("horas2")));
                listaDatos.add(String.valueOf(rs.getInt("creditos")));
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
