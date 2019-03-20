package agendajarroba;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface Dao {
    public List<String> getDatos() throws SQLException;
    public int borrarDatos() throws SQLException;
    public int agregarDatos(String matricula,String nombre,String apellido1,String apellido2) throws SQLException;
    
}
