package Persistencia.Consultorios.VerificarDisponibilidad;
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
public class VerificarDisponibilidad {
    public static boolean VerificarDisponibilidadConsultorio(int idConsultorio, Date fechaCita, String hora) {
        boolean ocupado = false;

        String sql = "SELECT COUNT(*) FROM citas WHERE idConsultorio = ? AND fechaCita = ? AND hora = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idConsultorio);
            ps.setDate(2, new java.sql.Date(fechaCita.getTime()));
            ps.setString(3, hora);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                ocupado = count > 0;
            }

            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad del consultorio: " + e.getMessage());
        }

        return ocupado;
    }
}
