package Persistencia.Consultorios.MostrarTablaConsultorios;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String sql = "SELECT idConsultorio, consultorio, especialidad FROM consultorios";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Consultorio ");
            model.addColumn("Consultorio");
            model.addColumn("Especialidad");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt("idConsultorio");
                row[1] = rs.getString("consultorio");
                row[2] = rs.getString("especialidad");
                model.addRow(row);

            }
            tablaConsultorios.setModel(model);
            tablaConsultorios.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar doctores: " + e.getMessage());
        }
    }

    public static void refrescarConsultorios(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("N° Consultorio");
        modelo.addColumn("Consultorio");
        modelo.addColumn("Especialidad");

        String sql = "SELECT idConsultorio, consultorio, especialidad FROM consultorios";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("idConsultorio"),
                    rs.getString("consultorio"),
                    rs.getString("especialidad")
                };
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al cargar consultorios: " + e.getMessage());
        }
    }

}
