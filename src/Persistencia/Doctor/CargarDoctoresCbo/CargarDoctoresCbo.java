package Persistencia.Doctor.CargarDoctoresCbo;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CargarDoctoresCbo {
    public void CargarDoctores(JComboBox<String> cboDoctor, String value, String value2) {
        String sql = "SELECT * FROM medicos";

        ConexionBD con = new ConexionBD();
        try (Connection connection = con.conectar(); PreparedStatement pst = connection.prepareStatement(sql)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    cboDoctor.addItem(rs.getString(value) + " " + rs.getString(value2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
}
