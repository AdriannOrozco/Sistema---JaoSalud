/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Doctor.Citas;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Osvaldo
 */
public class CitasMedicoDAO {

    // Constantes para estados
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_ATENDIDA = 1;
    public static final int ESTADO_EXPIRADA = 2;

    public void actualizarCitasExpiradas() {
        String sql = "UPDATE citas SET estado = ? "
                + "WHERE fechaCita < CURRENT_DATE "
                + "AND fueAtendida = false "
                + "AND estado = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ESTADO_EXPIRADA);
            ps.setInt(2, ESTADO_PENDIENTE);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar citas expiradas: " + e.getMessage());
        }
    }

    // Método existente modificado para mostrar estado como texto
    public void cargarCitasPorFecha(String idMedico, String fecha, DefaultTableModel modelo) {
        // Primero actualizar las citas expiradas
        actualizarCitasExpiradas();

        String sql = "SELECT c.idCita, c.idConsultorio, c.hora, c.motivo, "
                + "c.nombrePaciente, c.numeroDocumento, c.estado, c.fueAtendida "
                + "FROM citas c "
                + "WHERE c.identificacionDoctor = ? AND c.fechaCita = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ps.setString(2, fecha);
            modelo.setRowCount(0);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String estadoTexto;
                    if (rs.getBoolean("fueAtendida")) {
                        estadoTexto = "Atendida";
                    } else if (rs.getInt("estado") == ESTADO_EXPIRADA) {
                        estadoTexto = "Expirada";
                    } else {
                        estadoTexto = "Pendiente";
                    }

                    Object[] fila = {
                        rs.getString("idCita"),
                        rs.getString("idConsultorio"),
                        rs.getString("hora"),
                        rs.getString("motivo"),
                        rs.getString("nombrePaciente"),
                        rs.getString("numeroDocumento"),
                        estadoTexto
                    };
                    modelo.addRow(fila);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar citas: " + e.getMessage(), e);
        }
    }

    // Nuevo método para actualizar el estado de la cita
    public boolean actualizarEstadoCita(int idCita) {
        // Primero verificar si la cita no está expirada
        EstadoCita estadoCita = obtenerEstadoDetallado(idCita);
        if (estadoCita != null && estadoCita.getEstado() == ESTADO_EXPIRADA) {
            return false;
        }

        String sql = "UPDATE citas SET estado = ?, fueAtendida = true WHERE idCita = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ESTADO_ATENDIDA);
            ps.setInt(2, idCita);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar estado de cita: " + e.getMessage());
        }
    }

    public boolean verificarEstadoCita(int idCita) {
        actualizarCitasExpiradas(); // Actualizar estados antes de verificar

        String sql = "SELECT estado, fueAtendida FROM citas WHERE idCita = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                boolean fueAtendida = rs.getBoolean("fueAtendida");
                int estado = rs.getInt("estado");

                // La cita está disponible si no fue atendida y no está expirada
                return !fueAtendida && estado != ESTADO_EXPIRADA;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public EstadoCita obtenerEstadoDetallado(int idCita) {
        actualizarCitasExpiradas();

        String sql = "SELECT estado, fueAtendida, fechaCita FROM citas WHERE idCita = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                boolean fueAtendida = rs.getBoolean("fueAtendida");
                int estado = rs.getInt("estado");
                String fecha = rs.getString("fechaCita");

                return new EstadoCita(estado, fueAtendida, fecha);
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Clase auxiliar para manejar el estado

