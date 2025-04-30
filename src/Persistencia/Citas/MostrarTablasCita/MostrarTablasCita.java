package Persistencia.Citas.MostrarTablasCita;

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
public class MostrarTablasCita {
    
    public void MostrarCitas(JTable tablaCitas) {
        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT idCita, motivo ,idConsultorio, identificacionDoctor, fechaCita, hora, nombrePaciente, numeroDocumento FROM citas WHERE estado = true";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Cita");
            model.addColumn("Consultorio");
            model.addColumn("Doctor/a");
            model.addColumn("Fecha");
            model.addColumn("Hora");
            model.addColumn("Paciente");
   

            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("idCita");
                row[1] = rs.getString("idConsultorio");
                row[2] = rs.getString("identificacionDoctor");
                row[3] = rs.getString("fechaCita");
                row[4] = rs.getString("hora");
                row[5] = rs.getString("nombrePaciente");
                model.addRow(row);

            }
            tablaCitas.setModel(model);
            tablaCitas.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar doctores: " + e.getMessage());
        }
    }
    
    
     public static void refrescarCitas(JTable tabla) {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("N° Cita");
    modelo.addColumn("Consultorio");
    modelo.addColumn("Doctor/a");
    modelo.addColumn("Fecha");
    modelo.addColumn("Hora");
    modelo.addColumn("Paciente");

    String sql = "SELECT idCita, idConsultorio, identificacionDoctor, fechaCita, hora, nombrePaciente FROM citas WHERE estado = true";

    try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = {
                rs.getInt("idCita"),
                rs.getInt("idConsultorio"),
                rs.getString("identificacionDoctor"),
                rs.getDate("fechaCita"),   // Solo una vez
                rs.getString("hora"),
                rs.getString("nombrePaciente") // Cambiado de 'Paciente' a 'nombrePaciente'
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    } catch (SQLException e) {
        System.out.println("Error al cargar citas: " + e.getMessage());
    }
}
}
