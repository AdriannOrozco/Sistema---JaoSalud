/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Doctor.Consultorio;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConsultorioDAO {

    public String obtenerConsultorioPorEspecialidad(String especialidad) {
        // Cambiamos la consulta para usar el nombre correcto de la columna
        String sql = "SELECT idConsultorio FROM consultorios WHERE especialidad = ? LIMIT 1";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, especialidad);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("idConsultorio");  // Cambiado de numeroConsultorio a idConsultorio
            }
            return "No disponible";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public int obtenerContadorCitas(String idMedico) {
        LocalDate fechaHoy = LocalDate.now();
        // Modificamos la consulta para quitar la condición del estado
        String sql = "SELECT COUNT(*) as total FROM citas "
                + "WHERE identificacionDoctor = ? AND fechaCita = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ps.setString(2, fechaHoy.toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int obtenerNumeroConsultoriosDisponibles(String especialidad) {
        String sql = "SELECT COUNT(*) FROM consultorios WHERE especialidad = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, especialidad);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
