package agendajarroba;

import java.sql.*;

public class Conexion2
{
    private Connection conn;
    private String host;
    private String db;
    private String username;
    private String password;
    
    private static Conexion2 conexion2;
   
    public Conexion2()
    {   
        host = "localhost";
    	db = "materias";
    	username = "root"; //LO MEJOR ES INCLUIR OTRO USUARIO
    	password = "rockmanchester24";

        try 
        {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		//Intentamos conectarnos a la base de Datos 
		System.out.println("Conectando a la base...");
		String url ="jdbc:mysql://"+host+"/"+db+"?autoReconnect=true&useSSL=false";
                //jdbc:mysql://localhost/tintoreria                
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Conexion a BD establecida");		
	} catch(SQLException ex) {
		System.out.println("Error de mysql");
	} catch (ClassNotFoundException e) {
                e.printStackTrace();		
	} catch(Exception e) {
		System.out.println("Se produjo un error inesperado: "+e.getMessage());

	}	
	conexion2 = this;
    }
     
    public Conexion2(String host, String db, String username, String password) throws ClassNotFoundException, SQLException
    {
            Class.forName("com.mysql.jdbc.Driver");
            this.host = host;
            this.db = db;
            this.username = username;
            this.password = password;
            conn = DriverManager.getConnection ("jdbc:mysql://" + host +"/"+db,username,password);
            conexion2 = this;
    }
    
    public String getHost() {
 	   return host;
    }
    
    public void setHost(String host) {
 	   this.host = host;
    }
	   
    public String getDb() {
 	   return db;
    }
    
    public void setDb(String db) {
 	   this.db = db;
    }	   
    
    public String getUsername() {
 	   return username;
    }
    
    public void setUsername(String username) {
 	   this.username = username;
    }

    public String getPassword() {
 	   return password;
    }
    
    public void setPassword(String password) {
 	   this.password = password;
    }
    
    public Connection getConnection() { 	       	       
 	       return conn; 	   	   
    }
    
    public void close() {
 	   try
 	   {
 		   conn.close();
 	   }
 	   catch (SQLException e)
 	   {
 	       System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
 	   }    	   
    }

	public static Conexion2 getConexion() {
		return conexion2;
	}

	public static void setConexion(Conexion2 conexion) {
		Conexion2.conexion2 = conexion;
	}
}