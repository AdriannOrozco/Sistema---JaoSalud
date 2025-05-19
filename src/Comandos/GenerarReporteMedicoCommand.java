/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import java.time.LocalDateTime;

/**
 *
 * @author Osvaldo
 */
public class GenerarReporteMedicoCommand {
    private final String nombreDoctor;
    private final String especialidadDoctor;
    private final String idDoctor;
    private final String nombrePaciente;
    private final String idPaciente;  // Asegúrate de que este campo exista
    private final String reporteTexto;
    private final LocalDateTime fechaGeneracion;

    public GenerarReporteMedicoCommand(
            String nombreDoctor, 
            String especialidadDoctor,
            String idDoctor,
            String nombrePaciente, 
            String idPaciente,  // Agregar este parámetro
            String reporteTexto) {
        this.nombreDoctor = nombreDoctor;
        this.especialidadDoctor = especialidadDoctor;
        this.idDoctor = idDoctor;
        this.nombrePaciente = nombrePaciente;
        this.idPaciente = idPaciente;
        this.reporteTexto = reporteTexto;
        this.fechaGeneracion = LocalDateTime.now();
    }

    // Getters
    public String getNombreDoctor() { return nombreDoctor; }
    public String getEspecialidadDoctor() { return especialidadDoctor; }
    public String getIdDoctor() { return idDoctor; }
    public String getNombrePaciente() { return nombrePaciente; }
    public String getIdPaciente() { return idPaciente; }  // Agregar este getter
    public String getReporteTexto() { return reporteTexto; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
}
