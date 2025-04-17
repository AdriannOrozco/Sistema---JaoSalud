package Comandos;

public interface IActualizarPaciente {

    void actualizarTipoDocumento(String numeroDocumento, String nuevoTipoIdentificacion) throws Exception;
    
    void actualizarTelefono(String numeroDocumento, String nuevoTelefono) throws Exception;

    void actualizarEmail(String numeroDocumento, String nuevoEmail) throws Exception;

    void actualizarDireccion(String numeroDocumento, String nuevaDireccion) throws Exception;

    void actualizarEstadoCivil(String numeroDocumento, String nuevoEstadoCivil) throws Exception;

    void actualizarEdad(String numeroDocumento, String nuevaEdad) throws Exception;
    
    void actualizarEPS(String numeroDocumento, String nuevaEPS) throws Exception;
    
}
