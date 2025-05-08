package Comandos;

//Default imports
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoEliminarPaciente implements IEliminarPaciente {

    @Override
    public void EliminarPaciente(String tipoDocumento, String numeroDocumento) {

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al paciente?", "Seleccionar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso.", "Proceso cancelado", JOptionPane.OK_OPTION);
            return;
        }

        if (confirmacion == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM pacientes WHERE tipoDocumento = ? AND numeroDocumento = ?";
            try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1, tipoDocumento);
                pstmt.setString(2, numeroDocumento);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "El paciente se eliminó con éxito.", "Proceso completado", JOptionPane.OK_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay un paciente con cédula " + numeroDocumento, "No encontrado", JOptionPane.ERROR);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(), " error", JOptionPane.ERROR);
            }
        }
    }
}
