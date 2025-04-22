
package Persistencia.Consultorios.CargarConsultoriosPorEspecialidad;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class CargarConsultoriosPorEspecialidad {
    public void cargarConsultoriosPorEspecialidad(String especialidad, JComboBox cboConsultorio) {
        String sql = "SELECT consultorio FROM consultorios WHERE especialidad = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            cboConsultorio.removeAllItems();
            pstmt.setString(1, especialidad);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombreConsultorio = rs.getString("consultorio");
                cboConsultorio.addItem(nombreConsultorio);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar consultorios: " + e.getMessage());
        }
    }
}
