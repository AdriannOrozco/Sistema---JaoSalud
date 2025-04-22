package Persistencia.Citas.CargarDatosCitaId;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CargarDatosCita {

    public void cargarDatosCita(int idCita, JTextField txtNombrePacienteCita, JTextField txtNumeroDocumentoPacienteCita,
            JTextField txtHoraCita, JTextField txtFechaCita, JTextField txtIdentificacionDoctor, JTextField txtIdConsultorio) {

        ConexionBD con = new ConexionBD();
        String sql = "SELECT nombrePaciente, numeroDocumento, hora, fechaCita, identificacionDoctor, idConsultorio FROM citas WHERE idCita = ?";

        try (Connection conexion = con.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCita);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtNombrePacienteCita.setText(rs.getString("nombrePaciente"));
                txtNumeroDocumentoPacienteCita.setText(rs.getString("numeroDocumento"));
                txtHoraCita.setText(rs.getString("hora"));
                txtFechaCita.setText(rs.getString("fechaCita"));
                txtIdentificacionDoctor.setText(rs.getString("identificacionDoctor"));
                txtIdConsultorio.setText(rs.getString("idConsultorio"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener información de la cita: " + e.getMessage());
        }
    }
}
