/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.MoostrarTablasCita;

import Persistencia.Database.ConexionBD;
import com.sun.jdi.connect.spi.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Osvaldo
 */
public class FiltrarTablasCita {
    public void filtrarCitasPorFechaYDoctor(JTable tabla, String fecha, String idDoctor) {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("N° Cita");
    modelo.addColumn("Consultorio");
    modelo.addColumn("Doctor/a");
    modelo.addColumn("Fecha");
    modelo.addColumn("Hora");
    modelo.addColumn("Paciente");

    String sql = "SELECT * FROM citas WHERE fechaCita = ? AND identificacionDoctor = ? AND estado = true";

    try (Connection con = (Connection) ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, fecha);
        ps.setString(2, idDoctor);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = {
                rs.getInt("idCita"),
                rs.getInt("idConsultorio"),
                rs.getString("identificacionDoctor"),
                rs.getString("fechaCita"),
                rs.getString("hora"),
                rs.getString("nombrePaciente")
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al filtrar citas: " + e.getMessage());
    }
}

    private static class ResultSet {

        public ResultSet() {
        }

        private Object getInt(String idCita) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getString(String identificacionDoctor) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

}
