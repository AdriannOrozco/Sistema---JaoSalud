package Comandos;

import static Comandos.metodos.MetodosUtiles.*;
import Persistencia.Database.ConexionBD;
import Persistencia.Paciente.BuscarPacientePorID.BuscarPacientePorId;
import Persistencia.Paciente.PreguntarExisteDocumento.PreguntarExisteDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ComandoAgendarCita implements IAgendarCita {

    @Override
    public void AgendarCita(int idConsultorio, String identificacionDoctor, String motivo, Date fechaCita, String hora,
            int idCita, Date fechaRegistro, String nombrePaciente, String numeroDocumento, boolean estado) throws Exception {

        BuscarPacientePorId buscar = new BuscarPacientePorId();
        PreguntarExisteDocumento existeDocumento = new PreguntarExisteDocumento();
        
        // Validaciones iniciales
        if (identificacionDoctor == null || motivo == null || motivo.trim().isEmpty()
                || fechaCita == null || idCita == 0 || fechaRegistro == null || nombrePaciente == null || numeroDocumento == null) {
            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (motivo.length() < 10) {
            throw new IllegalArgumentException("El motivo digitado es inválido.");
        }

        if (hora.equals("Seleccionar")) {
            throw new IllegalArgumentException("Seleccione una hora.");
        }

        if (!existeDocumento.ExisteNumeroDocumento(numeroDocumento)) {
            throw new IllegalArgumentException("No existe un número de documento registrado.");
        }

        if (buscar.BuscarPacientePorDocumento(numeroDocumento) == null) {
            throw new IllegalArgumentException("No existe un paciente con ese npumero de documento.");
        }

        if (VerificarDisponibilidadDoctor(identificacionDoctor, fechaCita, hora)) {
            throw new IllegalArgumentException("El doctor ya está reservado.");
        }

        if (VerificarDisponibilidadConsultorio(idConsultorio, fechaCita, hora)) {
            throw new IllegalArgumentException("Consultorio ocupado.");
        }

        nombrePaciente = buscar.BuscarPacientePorDocumento(numeroDocumento);

        String sql = "INSERT INTO citas (idConsultorio, identificacionDoctor, motivo, fechaCita, hora, fechaRegistro, idCita, nombrePaciente, numeroDocumento, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idConsultorio);
            pstmt.setString(2, identificacionDoctor);
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
