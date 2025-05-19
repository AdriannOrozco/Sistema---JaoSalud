
package Persistencia.Paciente.PreguntarExisteDocumento;
import Persistencia.Database.ConexionBD;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PreguntarExisteDocumento {
 
    
    public static boolean ExisteNumeroDocumento(String numeroDocumento) throws Exception {
        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!MetodosCadenasDeTexto.ContieneSoloNumeros(numeroDocumento)) {
            throw new IllegalArgumentException("El número de documento solo debe contener números.");
        }

        String sql = "SELECT 1 FROM pacientes WHERE numeroDocumento = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new Exception("No hay un paciente con ese número documento. " + e.getMessage());
        }
    }
}
