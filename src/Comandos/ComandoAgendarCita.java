package Comandos;

import static Comandos.metodos.MetodosUtiles.*;
import Model.ConexionBD;
import Model.Consultorio;
import Model.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ComandoAgendarCita implements IAgendarCita {

    @Override
    public void AgendarCita(Consultorio consultorio, Medico medicoAsignado, String motivo, Date fechaCita, String hora, int idCita, Date fechaRegistro, String nombrePaciente, String numeroDocumento, boolean estado) throws Exception {

        if (consultorio == null || medicoAsignado == null || motivo == null || motivo.trim().isEmpty()
                || fechaCita == null || idCita == 0 || fechaRegistro == null || nombrePaciente == null || numeroDocumento == null) {
            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (!ContieneCaracteresPermitidos(motivo) || motivo.length() < 10) {
            throw new IllegalArgumentException("El motivo digitado es inválido.");
        }

        if (!ContieneSoloNumeros(numeroDocumento)) {
            throw new IllegalArgumentException("El número de documento sólo debe cargar números.");
        }

        if (!ExisteNumeroDocumento(numeroDocumento)) {
            JOptionPane.showMessageDialog(null, "No existe un número de documento registrado.");
            return;
        }

        if (BuscarPacientePorDocumento(numeroDocumento) == null) {
            JOptionPane.showMessageDialog(null, "No existe un paciente con ese número de documento.");
            return;
        }   
        nombrePaciente = BuscarPacientePorDocumento(numeroDocumento);       
        String sql = "INSERT INTO citas (consultorio, medicoAsignado, motivo, fechaCita, hora, fechaRegistro, idCita, nombrePaciente, numeroDocumento, estado) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setObject(1, consultorio);
            pstmt.setObject(2, medicoAsignado);
            pstmt.setString(3, motivo);
            pstmt.setDate(4, new java.sql.Date(fechaCita.getTime()));
            pstmt.setString(5, hora);
            pstmt.setDate(6, new java.sql.Date(fechaRegistro.getTime()));
            pstmt.setInt(7, idCita);
            pstmt.setString(8, nombrePaciente);
            pstmt.setString(9, numeroDocumento);
            pstmt.setBoolean(10, estado);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La cita se agendó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agendar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            throw new Exception("Error al agendar cita: " + e.getMessage());
        }

    }
}
