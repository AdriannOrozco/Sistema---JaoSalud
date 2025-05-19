package Persistencia.Doctor.Medico;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDAO {

    public String obtenerEspecialidadMedico(String idMedico) {
        String sql = "SELECT especialidad FROM medicos WHERE identificacion = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("especialidad");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
