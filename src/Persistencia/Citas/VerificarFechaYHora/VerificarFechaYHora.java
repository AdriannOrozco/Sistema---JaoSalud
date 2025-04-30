package Persistencia.Citas.VerificarFechaYHora;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class VerificarFechaYHora {

    public static boolean VerificarDisponibilidad(Date fechaCita, String hora) {
        // Validación defensiva para evitar errores por nulos
        if (fechaCita == null || hora == null || hora.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fecha u hora no válidas para verificar disponibilidad.");
            return false; // O puedes retornar true si quieres bloquear por seguridad
        }

        boolean ocupado = false;
        String sql = "SELECT COUNT(*) FROM citas WHERE fechaCita = ? AND hora = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(fechaCita.getTime()));
            ps.setString(2, hora);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                ocupado = count > 0;
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad del horario: " + e.getMessage());
        }

        return ocupado;
    }
}
