package Comandos;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoCancelarCita implements ICancelarCita {

    @Override
    public void CancelarCita(int idCita) {

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la cita? Esta quedará eliminada del sistema.", "Seleccionar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso.", "Proceso cancelado", JOptionPane.OK_OPTION);
            return;
        }

        if (confirmacion == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM citas WHERE idCita = ?";

            try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setInt(1, idCita);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "La cita se eliminó con éxito.", "Proceso completado", JOptionPane.OK_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay una cita con cédula " + idCita, "No encontrado", JOptionPane.ERROR);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar", "Error", JOptionPane.ERROR);
            }
        }

    }
}
