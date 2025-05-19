package Persistencia.Doctor.Citas;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CargarDatosCitaDoctor {

    public void cargarDatosCitaParaDoctor(int idCita, JTextField nombrePaciente,
            JTextField docPaciente, JTextField fecha, JTextField nombreDoctor,
            JTextField consultorio) {

        ConexionBD con = new ConexionBD();
        String sql = "SELECT c.nombrePaciente, c.numeroDocumento, c.fechaCita, "
                + "CONCAT(m.primerNombre, ' ', m.primerApellido) as nombreMedico, "
                + "c.idConsultorio "
                + "FROM citas c "
                + "INNER JOIN medicos m ON m.identificacionDoctor = c.identificacionDoctor "
                + "WHERE c.idCita = ?";

        try (Connection conexion = con.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombrePaciente.setText(rs.getString("nombrePaciente"));
                docPaciente.setText(rs.getString("numeroDocumento"));
                fecha.setText(rs.getString("fechaCita"));
                nombreDoctor.setText(rs.getString("nombreMedico"));
                consultorio.setText(rs.getString("idConsultorio"));
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró la cita con el ID: " + idCita);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al obtener información de la cita: " + e.getMessage());
        }
    }
}
