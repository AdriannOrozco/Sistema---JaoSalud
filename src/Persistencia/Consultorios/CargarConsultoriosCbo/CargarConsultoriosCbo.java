
package Persistencia.Consultorios.CargarConsultoriosCbo;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
public class CargarConsultoriosCbo {
     public void CargarConsultorios(JComboBox<String> cboConsultorio, String value) {
        String sql = "SELECT * FROM consultorios";
        ConexionBD con = new ConexionBD();
        try (Connection connection = con.conectar(); PreparedStatement pst = connection.prepareStatement(sql)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    cboConsultorio.addItem(rs.getString(value));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
}
