package Persistencia.Citas;

import Model.Cita;
import Persistencia.Database.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class CitaDAO {

    private static CitaDAO instancia;

    public CitaDAO() {

    }

    public static CitaDAO getInstancia() {
        if (instancia == null) {
            instancia = new CitaDAO();
        }
        return instancia;
    }

    public void create(Cita cita) throws Exception {
        String sql = "INSERT INTO citas (idConsultorio, identificacionDoctor, motivo, fechaCita, hora, fechaRegistro, idCita, nombrePaciente, numeroDocumento, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, cita.getIdConsultorio());
            pstmt.setString(2, cita.getIdentificacion());
            pstmt.setString(3, cita.getMotivo());
            pstmt.setDate(4, new java.sql.Date(cita.getFechaCita().getTime()));
            pstmt.setString(5, cita.getHora());
            pstmt.setDate(6, new java.sql.Date(cita.getFechaRegistro().getTime()));
            pstmt.setInt(7, cita.getIdCita());
            pstmt.setString(8, cita.getNombrePaciente());
            pstmt.setString(9, cita.getNumeroDocumento());
            pstmt.setBoolean(10, cita.isEstado());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                throw new SQLException("La cita se agendó con éxito.");
            } else {
                throw new SQLException("No se pudo agendar la cita.");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Error al agendar cita: " + e.getMessage());
        }
    }

    public boolean delete(int idCita) throws Exception {

        String sql = "DELETE FROM citas WHERE idCita = ?";
        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idCita);
            int filasAfectadas = pstmt.executeUpdate();

            return filasAfectadas > 0;
        }
    }

    public void update(Cita cita) throws Exception {
        String campo = null;
        String valor = null;

        if (cita.getMotivo() != null) {
            campo = "motivo";
            valor = cita.getMotivo();
            
        } else if (cita.getHora() != null) {
            campo = "hora";
            valor = cita.getHora();
        } else if (cita.getFechaCita() != null) {
            campo = "fechaCita";
            SimpleDateFormat fechaCita = new SimpleDateFormat("yyyy-MM-dd");
            valor = fechaCita.format(cita.getFechaCita());
        }

        if (campo == null || valor == null) {
            throw new IllegalArgumentException("No se encontró ningún campo válido para actualizar.");
        }
        
        String sql = "UPDATE citas SET " + campo + " = ? WHERE idCita = ?";
        
        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            pstmt.setInt(2, cita.getIdCita());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El campo '" + campo + "' se actualizó correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la cita para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar cita: " + e.getMessage());
        }

    }

}
