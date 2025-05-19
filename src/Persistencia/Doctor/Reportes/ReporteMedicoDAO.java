/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Doctor.Reportes;

import Comandos.GenerarReporteMedicoCommand;
import Comandos.PDFGenerator;
import Persistencia.Database.ConexionBD;
import java.sql.*;

/**
 *
 * @author Osvaldo
 */
public class ReporteMedicoDAO {
    private final Connection connection;
    private final PDFGenerator pdfGenerator;

    public ReporteMedicoDAO() throws SQLException {
        this.connection = ConexionBD.conectar();
        this.pdfGenerator = new PDFGenerator();
    }

    public void guardarReporte(GenerarReporteMedicoCommand comando) throws SQLException {
        // Crear tabla si no existe
        crearTablaReportesSiNoExiste();
        
        String sql = "INSERT INTO reportes_medicos (id_doctor, id_paciente, contenido, fecha_generacion) " +
                    "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, comando.getIdDoctor());
            ps.setString(2, comando.getIdPaciente());
            ps.setString(3, comando.getReporteTexto());
            ps.setTimestamp(4, Timestamp.valueOf(comando.getFechaGeneracion()));
            ps.executeUpdate();
            
            // Generar PDF
            pdfGenerator.generarReporteMedico(comando);
        } catch (Exception e) {
            throw new SQLException("Error al guardar el reporte: " + e.getMessage(), e);
        }
    }

    private void crearTablaReportesSiNoExiste() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS reportes_medicos (
                id INT AUTO_INCREMENT PRIMARY KEY,
                id_doctor VARCHAR(20),
                id_paciente VARCHAR(20),
                contenido TEXT,
                fecha_generacion DATETIME,
                FOREIGN KEY (id_doctor) REFERENCES medicos(identificacionDoctor),
                FOREIGN KEY (id_paciente) REFERENCES pacientes(numeroDocumento)
            )
        """;
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}
