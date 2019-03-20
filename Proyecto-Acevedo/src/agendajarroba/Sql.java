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


public class Sql implements Dao{

    @Override
    public List<String> getDatos() throws SQLException{//(4) este metodo es el que devuelve la lista
        List<String> listaDatos=new ArrayList<>();
        Statement s;
        Connection conn=null;
        conn=new Conexion().getConnection();//(5) este metodo conecta a la bd
        ResultSet rs = null;
        String sQuery="select*from alumnos;";
        try
        {
            s=conn.createStatement();
            //s1 = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=s.executeQuery (sQuery);
            System.out.println("Ejecutó la consulta");
            //llenamos la lista
            while (rs!=null && rs.next()) {                
                listaDatos.add(rs.getString("matricula"));
                listaDatos.add(rs.getString("nombres"));
                listaDatos.add(rs.getString("apellido1"));
                listaDatos.add(rs.getString("apellido2"));
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
    public int borrarDatos() throws SQLException{
       String sentencia;
       sentencia="delete from alumnos;";     
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
    public int agregarDatos(String matricula,String nombre,String apellido1,String apellido2) throws SQLException{
       String sentencia;
       //el id es autoincremental
       //Iterator<String> it = datos.iterator(); 
       //while(it.hasNext()){
           sentencia="insert into alumnos values ('"+matricula+"','"+nombre+"','"+apellido1+"','"+apellido2+"');";     
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
    
      
}
