package Comandos;

import Model.Consultorio;
import Model.Medico;
import java.util.Date;

public interface IAgendarCita {
    
    public void AgendarCita(int idConsultorio, String identificacionDoctor, String motivo, Date fechaCita, String hora, int idCita, Date fechaRegistro, String nombrePaciente, String numeroDocumento, boolean estado) throws Exception;
}
