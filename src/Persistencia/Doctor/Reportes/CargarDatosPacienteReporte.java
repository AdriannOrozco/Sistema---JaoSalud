package Persistencia.Doctor.Reportes;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextField;

public class CargarDatosPacienteReporte {

    public void cargarDatosPacienteReporte(String numeroDocumento, String tipoDocumento,
            JTextField txtCargarEPSReport,
            JTextField txtCargarPrimerNombreReport,
            JTextField txtCargarPrimerApellidoReport) throws Exception {

        String sql = "SELECT EPS, primerNombre, primerApellido FROM pacientes "
                + "WHERE numeroDocumento = ? AND tipoDocumento = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, numeroDocumento);
            ps.setString(2, tipoDocumento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtCargarEPSReport.setText(rs.getString("EPS"));
                txtCargarPrimerNombreReport.setText(rs.getString("primerNombre"));
                txtCargarPrimerApellidoReport.setText(rs.getString("primerApellido"));
            } else {
                throw new Exception("No se encontró el paciente");
            }
        }
    }
}
