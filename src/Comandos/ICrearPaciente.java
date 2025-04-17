package Comandos;
import java.util.Date;
public interface ICrearPaciente {
    
public void crearPaciente(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido
, String tipoDocumento, String numeroDocumento, String telefono ,String direccionResidencia, String estadoCivil
, String genero, String email,  String EPS, String tipoSangre, Date fechaNacimiento, Date fechaRegistro, int edad) throws Exception;
}
