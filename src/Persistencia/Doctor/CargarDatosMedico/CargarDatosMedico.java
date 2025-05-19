package Persistencia.Doctor.CargarDatosMedico;

import Persistencia.Database.ConexionBD;
import javax.swing.JLabel;
import java.sql.*;

public class CargarDatosMedico {

    public void cargarInformacionMedico(String idMedico, JLabel nombreLabel,
            JLabel especializacionLabel, JLabel consultorioLabel) {
        String sql = "SELECT CONCAT(primerNombre, ' ', primerApellido) as nombre, "
                + "especialidad, idConsultorio "
                + "FROM medicos WHERE identificacionDoctor = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombreLabel.setText(rs.getString("nombre"));
                especializacionLabel.setText(rs.getString("especialidad"));
                consultorioLabel.setText(rs.getString("idConsultorio"));
            } else {
                throw new SQLException("No se encontró información del médico");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar datos del médico: " + e.getMessage());
        }
    }
}
