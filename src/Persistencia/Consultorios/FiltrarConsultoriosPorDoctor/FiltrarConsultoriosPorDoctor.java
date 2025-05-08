package Persistencia.Consultorios.FiltrarConsultoriosPorDoctor;

import Persistencia.Consultorios.CargarConsultoriosPorEspecialidad.CargarConsultoriosPorEspecialidad;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class FiltrarConsultoriosPorDoctor {

    CargarConsultoriosPorEspecialidad cargarConsultoriosPE = new CargarConsultoriosPorEspecialidad();

    public void filtrarConsultoriosPorDoctor(String nombreDoctor, String identificacionDoctor, JComboBox cboConsultorio) {
        String sql = "SELECT especialidad FROM medicos WHERE CONCAT(primerNombre, ' ', primerApellido) = ? AND identificacionDoctor = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreDoctor);
            pstmt.setString(2, identificacionDoctor);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String especialidad = rs.getString("especialidad");
                cargarConsultoriosPE.cargarConsultoriosPorEspecialidad(especialidad, cboConsultorio);
            } else {
                cboConsultorio.removeAllItems();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
