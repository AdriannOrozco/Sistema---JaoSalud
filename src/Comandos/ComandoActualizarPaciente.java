package Comandos;

import static Comandos.metodos.MetodosUtiles.*;
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoActualizarPaciente implements IActualizarPaciente {

    @Override
    public void actualizarTelefono(String numeroDocumento, String nuevoTelefono) throws Exception {

        if (nuevoTelefono == null || nuevoTelefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        if (!ContieneSoloNumeros(nuevoTelefono) || nuevoTelefono.length() != 10) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }

        String sql = "UPDATE pacientes SET telefono = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevoTelefono);
            pstmt.setString(2, numeroDocumento);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Teléfono actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar teléfono: " + e.getMessage());
        }

    }

    @Override
    public void actualizarEmail(String numeroDocumento, String nuevoEmail) throws Exception {

        if (nuevoEmail == null || nuevoEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        if (!EsEmailValido(nuevoEmail)) {
            throw new IllegalArgumentException("Email inválido.");
        }

        String sql = "UPDATE pacientes SET email = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevoEmail);
            pstmt.setString(2, numeroDocumento);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Email actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar email: " + e.getMessage());
        }

    }

    @Override
    public void actualizarDireccion(String numeroDocumento, String nuevaDireccion) throws Exception {

        if (nuevaDireccion == null || nuevaDireccion.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        if (!ContieneSoloNumeros(nuevaDireccion) || nuevaDireccion.length() != 10) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }

        String sql = "UPDATE pacientes SET direccionResidencia = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevaDireccion);
            pstmt.setString(2, numeroDocumento);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Dirección de residencia actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar dirección de residencia: " + e.getMessage());
        }

    }

    @Override
    public void actualizarEstadoCivil(String numeroDocumento, String nuevoEstadoCivil) throws Exception {

        if (nuevoEstadoCivil == null || nuevoEstadoCivil.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        

        String sql = "UPDATE pacientes SET estadoCivil = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevoEstadoCivil);
            pstmt.setString(2, numeroDocumento);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Estado civil actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado civil:  " + e.getMessage());
        }

    }

    @Override
    public void actualizarEdad(String numeroDocumento, String nuevaEdad) throws Exception {

        if (nuevaEdad == null || nuevaEdad.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }

        String sql = "UPDATE pacientes SET edad = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevaEdad);
            pstmt.setString(2, numeroDocumento);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Edad actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la edad:  " + e.getMessage());
        }

    }

    public void actualizarEPS(String numeroDocumento, String nuevaEPS) throws Exception {

        if (nuevaEPS == null || nuevaEPS.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }

        String sql = "UPDATE pacientes SET EPS = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevaEPS);
            pstmt.setString(2, numeroDocumento);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "EPS actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la EPS:  " + e.getMessage());
        }

    }
    
    @Override
    public void actualizarTipoDocumento(String numeroDocumento, String nuevoTipoIdentificacion) throws Exception{
       
        if (nuevoTipoIdentificacion == null || nuevoTipoIdentificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }

        String sql = "UPDATE pacientes SET tipoDocumento = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevoTipoIdentificacion);
            pstmt.setString(2, numeroDocumento);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Tipo de documento actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar tipo de documento:  " + e.getMessage());
        }
        
    }
}
