package Persistencia.Citas.MostrarTablasCita;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel; // Asumiendo esta clase para la conexión a BD

/**
 * Clase para mostrar tablas de citas
 *
 * @author Osvaldo
 */
public class MostrarTablasCita {

    private Connection conexion;

    public MostrarTablasCita() {
        // Inicializar la conexión (asumiendo que tienes una clase ConexionBD)
        try {
            conexion = ConexionBD.conectar();
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void MostrarCitas(JTable tablaCitas) {
        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT idCita, motivo ,idConsultorio,fechaCita, hora, nombrePaciente, numeroDocumento FROM citas WHERE estado = true";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Cita");
            model.addColumn("Consultorio");
            model.addColumn("Fecha");
            model.addColumn("Hora");
            model.addColumn("Paciente");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("idCita");
                row[1] = rs.getString("idConsultorio");
                row[2] = rs.getString("fechaCita");
                row[3] = rs.getString("hora");
                row[4] = rs.getString("nombrePaciente");
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
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Paciente");

        String sql = "SELECT idCita, idConsultorio,fechaCita, hora, nombrePaciente FROM citas WHERE estado = true";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("idCita"),
                    rs.getInt("idConsultorio"),
                    rs.getDate("fechaCita"),
                    rs.getString("hora"),
                    rs.getString("nombrePaciente")
                };
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al cargar citas: " + e.getMessage());
        }
    }

    /**
     * Muestra las citas por fecha y médico específico
     *
     * @param tabla La tabla donde se mostrarán los resultados
     * @param fecha La fecha en formato yyyy-MM-dd
     * @param idMedico El ID del médico logueado
     * @return DefaultTableModel con los datos de las citas
     */
    public DefaultTableModel MostrarCitaPorFechaYMedico(JTable tabla, String fecha, String idMedico) {
        DefaultTableModel modelo;

        // Los nombres de columnas deben coincidir con tu diseño original
        String[] columnas = {"NÂ° Cita", "Motivo", "Consultorio", "Doctor/a", "Hora", "Paciente", "Documento"};
        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT c.idCita, c.motivo, co.consultorio, "
                + "CONCAT(m.primerNombre, ' ', m.primerApellido) AS doctor, "
                + "c.hora, c.nombrePaciente, c.numeroDocumento "
                + "FROM citas c "
                + "JOIN medicos m ON c.identificacionDoctor = m.identificacionDoctor "
                + "JOIN consultorios co ON c.idConsultorio = co.idConsultorio "
                + "WHERE DATE(c.fechaCita) = ? AND c.identificacionDoctor = ? "
                + "ORDER BY c.hora ASC";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, fecha);
            ps.setString(2, idMedico);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("id_cita");
                registros[1] = rs.getString("motivo");
                registros[2] = rs.getString("nombre_consultorio");
                registros[3] = rs.getString("doctor");
                registros[4] = rs.getString("hora_cita");
                registros[5] = rs.getString("paciente");
                registros[6] = rs.getString("num_documento");

                modelo.addRow(registros);
            }

            return modelo;

        } catch (SQLException e) {
            System.out.println("Error al mostrar citas: " + e.getMessage());
            return null;
        }
    }

    // Otros métodos que puedas necesitar para tu gestión de citas
}
