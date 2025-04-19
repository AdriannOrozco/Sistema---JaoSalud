package Comandos;
import static Comandos.metodos.MetodosUtiles.*;
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ComandoCrearPaciente implements ICrearPaciente {

    @Override
    public void CrearPaciente(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
            String tipoDocumento, String numeroDocumento, String telefono, String direccionResidencia, String estadoCivil,
            String genero, String email, String EPS, String tipoSangre, Date fechaNacimiento, Date fechaRegistro, String edad) throws Exception {


if (primerNombre == null || primerNombre.trim().isEmpty() ||
    primerApellido == null || primerApellido.trim().isEmpty() || segundoApellido == null || segundoApellido.trim().isEmpty() ||
    tipoDocumento == null || tipoDocumento.trim().isEmpty() || numeroDocumento == null || numeroDocumento.trim().isEmpty() ||
    telefono == null || telefono.trim().isEmpty() || direccionResidencia == null || direccionResidencia.trim().isEmpty() ||
    estadoCivil == null || estadoCivil.trim().isEmpty() || genero == null || genero.trim().isEmpty() ||
    EPS == null || EPS.trim().isEmpty() ||
    tipoSangre == null || tipoSangre.trim().isEmpty() || edad.trim().isEmpty()) {
  
    throw new IllegalArgumentException("Campos obligatorios vacíos.");
}

if (primerNombre.length() < 3 || segundoNombre.length() < 3 || primerApellido.length() < 3 || segundoApellido.length() < 3) {
    throw new IllegalArgumentException("Nombres o apellidos demasiado cortos.");
}

if (!EsNombreValido(primerNombre) || !EsNombreValido(segundoNombre) || !EsNombreValido(primerApellido) || !EsNombreValido(segundoApellido)) {
    throw new IllegalArgumentException("Nombres inválidos.");
}

if (!ContieneSoloNumeros(numeroDocumento)) {
    throw new IllegalArgumentException("Documento inválido.");
}

if (numeroDocumento.length() < 7 || numeroDocumento.length() > 13) {
    throw new IllegalArgumentException("Longitud inválida del documento.");
}

if (!ContieneSoloNumeros(telefono) || telefono.length() < 10) {
    throw new IllegalArgumentException("Teléfono inválido.");
}

if (!EsNombreValido(genero)) {
    throw new IllegalArgumentException("Estado civil o género inválido.");
}

if (!EsEmailValido(email) || email.length() < 7) {
    throw new IllegalArgumentException("Correo inválido.");
}

if (direccionResidencia.length() < 7) {
    throw new IllegalArgumentException("Dirección inválida.");
}

if (EPS.length() < 6) {
    throw new IllegalArgumentException("EPS inválida.");
}

if (tipoSangre.length() > 5 || ContieneNumeros(tipoSangre)) {
    throw new IllegalArgumentException("Tipo de sangre inválido.");
}


if (!ContieneSoloNumeros(edad)) {
    throw new IllegalArgumentException("Edad inválida.");
}

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (verificarPacienteID(numeroDocumento)) {
            JOptionPane.showMessageDialog(null, "Ya existe un paciente con ese número de documento.", "Número de documento existente.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (VerificarPacienteEmail(email)) {
            JOptionPane.showMessageDialog(null, "Ya existe un paciente con ese email.", "Email existente.", JOptionPane.ERROR_MESSAGE);
            return;
        }

       String sql = "INSERT INTO pacientes (primerNombre, segundoNombre, primerApellido, segundoApellido, tipoDocumento, numeroDocumento, telefono, direccionResidencia, estadoCivil, genero, email, EPS, tipoSangre, fechaNacimiento, fechaRegistro, edad) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
       try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, primerNombre);
            pstmt.setString(2, segundoNombre);
            pstmt.setString(3, primerApellido);
            pstmt.setString(4, segundoApellido);
            pstmt.setString(5, tipoDocumento);
            pstmt.setString(6, numeroDocumento);
            pstmt.setString(7, telefono);
            pstmt.setString(8, direccionResidencia);
            pstmt.setString(9, estadoCivil);
            pstmt.setString(10, genero);
            pstmt.setString(11, email);
            pstmt.setString(12, EPS);
            pstmt.setString(13, tipoSangre);
            pstmt.setDate(14, new java.sql.Date(fechaNacimiento.getTime()));
            pstmt.setDate(15, new java.sql.Date(fechaRegistro.getTime()));
            pstmt.setString(16, edad);
            
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El paciente se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            throw new Exception("Error al agregar paciente: " + e.getMessage());
        }
    }
}
