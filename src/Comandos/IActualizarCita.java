package Comandos;
import Model.Consultorio;
import Model.Medico;
import java.util.Date;

public interface IActualizarCita {
    
     void ActualizarCita(  
     Consultorio consultorio,
     Medico medicoAsignado,
     Date fechaCita,
     String hora,
     int idCita
    );
}
