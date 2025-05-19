package Persistencia.Doctor.VerificarDisponibilidad;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

public class VerificarDisponibilidadDoc {

    public boolean VerificarDisponibilidadDoctor(String identificacion, Date fechaCita, String hora) {
        boolean ocupado = false;

        String sql = "SELECT COUNT(*) FROM citas WHERE identificacionDoctor = ? AND fechaCita = ? AND hora = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificacion);
            ps.setDate(2, new java.sql.Date(fechaCita.getTime()));
            ps.setString(3, hora);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                ocupado = count > 0;
            }
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad del doctor: " + e.getMessage());
        }

        return ocupado;
    }
}
