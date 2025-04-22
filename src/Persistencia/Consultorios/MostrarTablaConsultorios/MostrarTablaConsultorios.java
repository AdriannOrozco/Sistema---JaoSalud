package Persistencia.Consultorios.MostrarTablaConsultorios;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adrian
 */
public class MostrarTablaConsultorios {

    public void MostrarConsultorios(JTable tablaConsultorios) {
        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT * FROM consultorios";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Consultorio ");
            model.addColumn("Consultorio");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("idConsultorio");
                row[1] = rs.getString("consultorio");
                model.addRow(row);

            }
            tablaConsultorios.setModel(model);
            tablaConsultorios.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar doctores: " + e.getMessage());
        }
    }
}
