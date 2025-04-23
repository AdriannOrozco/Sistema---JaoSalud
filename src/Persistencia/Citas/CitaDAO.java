package Persistencia.Citas;
import Model.Cita;
import Persistencia.Database.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CitaDAO {

    private static CitaDAO instancia;

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

}
