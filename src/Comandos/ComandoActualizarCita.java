package Comandos;
import Persistencia.Database.ConexionBD;
import Model.Consultorio;
import Model.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ComandoActualizarCita implements IActualizarCita {

    @Override
    public void ActualizarCita(
            Consultorio consultorio,
            Medico medicoAsignado,
            Date fechaCita,
            String hora,
            int idCita) {

        String sql = "UPDATE citas SET consultorio = ?, medicoAsignado = ?, fechaCita = ?, hora = ?, WHERE idCita = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Establecer los valores en la consulta
            pstmt.setObject(1, consultorio);
            pstmt.setObject(2, medicoAsignado);
            pstmt.setDate(3, new java.sql.Date(fechaCita.getTime()));
            pstmt.setString(4,hora);
            pstmt.setInt(5, idCita); //Where de coincidencia 

            // Ejecutar la consulta
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Actualización completada");
            } else {
                JOptionPane.showMessageDialog(null, "No se encuentra una cita con ese número de identificación");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage());
        }
    }
}
