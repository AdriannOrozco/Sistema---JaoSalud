package Comandos;

public interface IActualizarPaciente {
    
   void ActualizarPaciente(  
     String tipoDocumento,
     String telefono,
     String direccionResidencia,
     String estadoCivil,
     String email,
     String EPS,
     String edad,
      String numeroDocumento
    ) throws Exception;
}
