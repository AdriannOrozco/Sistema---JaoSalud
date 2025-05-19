
package Persistencia.Doctor.MostrarTablaDoctores;

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
public class MostrarTablaDoctores {
     public void MostrarMedicos(JTable tablaDoctores) {

        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT * FROM medicos";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Identificación");
            model.addColumn("Primer nombre");
            model.addColumn("Primer apellido");
            model.addColumn("Especialidad");
            model.addColumn("Años de experiencia");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("identificacionDoctor");
                row[1] = rs.getString("primerNombre");
                row[2] = rs.getString("primerApellido");
                row[3] = rs.getString("especialidad");
                row[4] = rs.getString("añosExperiencia");
                model.addRow(row);

            }
            tablaDoctores.setModel(model);
            tablaDoctores.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar doctores: " + e.getMessage());
        }
    }
     
     public static void refrescarDoctores(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Identificación");
        modelo.addColumn("Primer nombre");
        modelo.addColumn("Primer apellido");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Años de experiencia");

        String sql = "SELECT identificacionDoctor, primerNombre, primerApellido, especialidad, añosExperiencia FROM medicos";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("identificacionDoctor"),
                    rs.getString("primerNombre"),
                    rs.getString("primerApellido"),
                    rs.getString("especialidad"),
                    rs.getString("añosExperiencia")
                };
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al cargar consultorios: " + e.getMessage());
        }
    }
     
     
     
     
     
     
     
     
}
