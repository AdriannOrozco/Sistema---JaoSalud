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
import java.time.LocalDate;

/**
 *
 * @author Osvaldo
 */
public class CitasContadorDAO {

    public int obtenerCitasDelDia(String idMedico, LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM citas WHERE identificacionDoctor = ? AND fechaCita = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
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

    public int obtenerCitasAtendidas(String idMedico, LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM citas WHERE identificacionDoctor = ? AND fechaCita = ? AND estado = 1";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
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
