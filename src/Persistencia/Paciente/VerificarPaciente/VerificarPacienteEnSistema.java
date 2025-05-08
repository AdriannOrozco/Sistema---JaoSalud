package Persistencia.Paciente.VerificarPaciente;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class VerificarPacienteEnSistema {

    public boolean verificarPaciente(String numeroDocumento, String tipoDocumento, String columnaSeleccionada1, String columnaSeleccionada2) throws Exception {

        String sql = "SELECT 1 FROM pacientes WHERE " + columnaSeleccionada1 + " = ? AND " + columnaSeleccionada2 + " = ?";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, numeroDocumento);
            ps.setString(2, tipoDocumento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    throw new IllegalArgumentException("El paciente buscado no está en el sistema.");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error para conectar" + e.getMessage());
        }
    }
}
