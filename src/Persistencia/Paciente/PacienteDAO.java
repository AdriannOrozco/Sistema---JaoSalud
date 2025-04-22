package Persistencia.Paciente;

import Model.Paciente;
import Persistencia.Database.ConexionBD;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

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

        String sql = "INSERT INTO pacientes (primerNombre, segundoNombre, primerApellido, segundoApellido, tipoDocumento, numeroDocumento, telefono, direccionResidencia, estadoCivil, genero, email, EPS, tipoSangre, fechaNacimiento, fechaRegistro, edad) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, paciente.getPrimerNombre());
            pstmt.setString(2, paciente.getSegundoNombre());
            pstmt.setString(3, paciente.getPrimerApellido());
            pstmt.setString(4, paciente.getSegundoApellido());
            pstmt.setString(5, paciente.getTipoDocumento());
            pstmt.setString(6, paciente.getNumeroDocumento());
            pstmt.setString(7, paciente.getTelefono());
            pstmt.setString(8, paciente.getDireccionResidencia());
            pstmt.setString(9, paciente.getEstadoCivil());
            pstmt.setString(10, paciente.getGenero());
            pstmt.setString(11, paciente.getEmail());
            pstmt.setString(12, paciente.getEPS());
            pstmt.setString(13, paciente.getTipoSangre());
            pstmt.setDate(14, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            pstmt.setDate(15, new java.sql.Date(paciente.getFechaRegistro().getTime()));
            pstmt.setString(16, paciente.getEdad());

            pstmt.executeUpdate();

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El paciente se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
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


///
}
