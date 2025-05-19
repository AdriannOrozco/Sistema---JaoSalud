package Persistencia.Paciente;

import Model.Paciente;
import Persistencia.Database.ConexionBD;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class PacienteDAO {

    private static PacienteDAO instancia;

    public PacienteDAO() {
        // Constructor privado (patrón Singleton)
    }

    public static PacienteDAO getInstancia() {
        if (instancia == null) {
            instancia = new PacienteDAO();
        }
        return instancia;
    }

 public void create(Paciente paciente) throws Exception {
    String sqlUsuario = "INSERT INTO usuarios (usuario, contraseña, rol) VALUES (?, ?, ?)";
    String sqlPaciente = "INSERT INTO pacientes (primerNombre, segundoNombre, primerApellido, segundoApellido, tipoDocumento, numeroDocumento, telefono, direccionResidencia, estadoCivil, genero, email, EPS, tipoSangre, fechaNacimiento, fechaRegistro, edad, id_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    try (var con = ConexionBD.conectar()) {
        PreparedStatement pstmtUsuario = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmtUsuario.setString(1, paciente.getNumeroDocumento());
        pstmtUsuario.setString(2, paciente.getNumeroDocumento());
        pstmtUsuario.setString(3, "Paciente");
        pstmtUsuario.executeUpdate();
        
        int idUsuario = 0;
        ResultSet rs = pstmtUsuario.getGeneratedKeys();
        if (rs.next()) {
            idUsuario = rs.getInt(1);
        } else {
            throw new Exception("No se pudo obtener el ID del usuario");
        }
       
        PreparedStatement pstmtPaciente = con.prepareStatement(sqlPaciente);
        pstmtPaciente.setString(1, paciente.getPrimerNombre());
        pstmtPaciente.setString(2, paciente.getSegundoNombre());
        pstmtPaciente.setString(3, paciente.getPrimerApellido());
        pstmtPaciente.setString(4, paciente.getSegundoApellido());
        pstmtPaciente.setString(5, paciente.getTipoDocumento());
        pstmtPaciente.setString(6, paciente.getNumeroDocumento());
        pstmtPaciente.setString(7, paciente.getTelefono());
        pstmtPaciente.setString(8, paciente.getDireccionResidencia());
        pstmtPaciente.setString(9, paciente.getEstadoCivil());
        pstmtPaciente.setString(10, paciente.getGenero());
        pstmtPaciente.setString(11, paciente.getEmail());
        pstmtPaciente.setString(12, paciente.getEPS());
        pstmtPaciente.setString(13, paciente.getTipoSangre());
        pstmtPaciente.setDate(14, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
        pstmtPaciente.setDate(15, new java.sql.Date(paciente.getFechaRegistro().getTime()));
        pstmtPaciente.setString(16, paciente.getEdad());
        pstmtPaciente.setInt(17, idUsuario);  // Usar el ID generado del usuario
        
        int filasAfectadas = pstmtPaciente.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "El paciente se creó con éxito.",
                    "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el paciente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al crear paciente y usuario: " + e.getMessage());
    }
}

    public void update(Paciente paciente) throws Exception {
        String campo = null;
        String valor = null;

        if (paciente.getTipoDocumento() != null) {
            campo = "tipoDocumento";
            valor = paciente.getTipoDocumento();
            
        } else if (paciente.getTelefono() != null) {
            campo = "telefono";
            valor = paciente.getTelefono();
        } else if (paciente.getEmail() != null) {
            campo = "email";
            valor = paciente.getEmail();
        } else if (paciente.getDireccionResidencia() != null) {
            campo = "direccionResidencia";
            valor = paciente.getDireccionResidencia();
        } else if (paciente.getEstadoCivil() != null) {
            campo = "estadoCivil";
            valor = paciente.getEstadoCivil();
        } else if (paciente.getEPS() != null) {
            campo = "EPS";
            valor = paciente.getEPS();
        } else if (paciente.getEdad() != null) {
            campo = "edad";
            valor = paciente.getEdad();
        }

        if (campo == null || valor == null) {
            throw new IllegalArgumentException("No se encontró ningún campo válido para actualizar.");
        }

        String sql = "UPDATE pacientes SET " + campo + " = ? WHERE numeroDocumento = ?";

        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            pstmt.setString(2, paciente.getNumeroDocumento());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El campo '" + campo + "' se actualizó correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el paciente para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar paciente: " + e.getMessage());
        }
    }

    public boolean delete(String tipoDocumento, String numeroDocumento) throws Exception {
        String sql = "DELETE FROM pacientes WHERE tipoDocumento = ? AND numeroDocumento = ?";

        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, tipoDocumento);
            pstmt.setString(2, numeroDocumento);

            int filasAfectadas = pstmt.executeUpdate();

            return filasAfectadas > 0;
        }
    }


///
}
