/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Doctor.Resultados;

import Persistencia.Database.ConexionBD;
import java.sql.*;

/**
 *
 * @author Osvaldo
 */
public class ResultadoMedicoDAO {

    public boolean guardarResultadoMedico(int idCita, String nombrePaciente,
            String documentoPaciente, String consultorio, String documentoDoctor,
            String tipoCita, String motivoConsulta, String presionArterial,
            String frecuenciaCardiaca, String temperatura, String pesoTalla,
            String enfermedadesPrevias, String alergiasImportantes,
            String medicamentosActuales, String medicamentosPrescritos,
            String estudiosExamenes, String recomendacionesMedicas) {

        String sql = "INSERT INTO resultados_medicos (idCita, nombrePaciente, documentoPaciente, "
                + "consultorio, documentoDoctor, tipoCita, motivoConsulta, presionArterial, "
                + "frecuenciaCardiaca, temperatura, pesoTalla, enfermedadesPrevias, "
                + "alergiasImportantes, medicamentosActuales, medicamentosPrescritos, "
                + "estudiosExamenes, recomendacionesMedicas) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = new ConexionBD().conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            ps.setString(2, nombrePaciente);
            ps.setString(3, documentoPaciente);
            ps.setString(4, consultorio);
            ps.setString(5, documentoDoctor);
            ps.setString(6, tipoCita);
            ps.setString(7, motivoConsulta);
            ps.setString(8, presionArterial);
            ps.setString(9, frecuenciaCardiaca);
            ps.setString(10, temperatura);
            ps.setString(11, pesoTalla);
            ps.setString(12, enfermedadesPrevias);
            ps.setString(13, alergiasImportantes);
            ps.setString(14, medicamentosActuales);
            ps.setString(15, medicamentosPrescritos);
            ps.setString(16, estudiosExamenes);
            ps.setString(17, recomendacionesMedicas);

            int result = ps.executeUpdate();
            if (result > 0) {
                actualizarEstadoCita(idCita);
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void actualizarEstadoCita(int idCita) {
        String sql = "UPDATE citas SET estado = 0 WHERE idCita = ?";

        try (Connection conn = new ConexionBD().conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
