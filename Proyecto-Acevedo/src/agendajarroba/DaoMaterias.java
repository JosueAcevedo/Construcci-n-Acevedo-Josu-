
package agendajarroba;

import java.sql.SQLException;
import java.util.List;


public interface DaoMaterias {
    public List<String> getDatos2() throws SQLException;
    public int borrarDatos2() throws SQLException;
    public int agregarDatos2(String nombre,String nrc,int horas1,int horas2,int creditos) throws SQLException;
    public String sacarNombre(int idMateria) throws SQLException;
}
