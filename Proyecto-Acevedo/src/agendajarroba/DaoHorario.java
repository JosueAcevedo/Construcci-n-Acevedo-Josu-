
package agendajarroba;

import java.sql.SQLException;
import java.util.List;


public interface DaoHorario {
    public List<String> getDatos2() throws SQLException;
    public int borrarDatos2() throws SQLException;
    public int agregarDatos2(int idMateria,String salon,int dia,String horaInicio,String horaFin) throws SQLException;
}
