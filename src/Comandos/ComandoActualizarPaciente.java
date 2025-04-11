package Comandos;
import static Comandos.metodos.MetodosUtiles.*;
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoActualizarPaciente implements IActualizarPaciente {

    @Override
    public void ActualizarPaciente(
            String tipoDocumento,
            String telefono,
            String direccionResidencia,
            String estadoCivil,
            String email,
            String EPS,
            String edad,
            String numeroDocumento) throws Exception {
        
        if (tipoDocumento.trim().isEmpty() || tipoDocumento == null || telefono.trim().isEmpty() || telefono == null
                || direccionResidencia.trim().isEmpty() || direccionResidencia == null || estadoCivil.trim().isEmpty()
                || estadoCivil == null || email.trim().isEmpty() | email == null || EPS.trim().isEmpty() || EPS == null
                || edad.trim().isEmpty() || edad == null) {
            throw new IllegalArgumentException("Hay campos vacíos.");       
        }
        
        if (!ContieneSoloNumeros(telefono) || !ContieneSoloNumeros(edad)) {
            throw new IllegalArgumentException("Hay datos que sólo pueden contener números (Teléfono-edad.)");
        }
        
        if (telefono.length() < 10 || telefono.length() > 10) {
            throw new IllegalArgumentException("El número de teléfono tiene una ongitud inválida.");
        }
        
        if(!EsEmailValido(email) || email.length() < 7){
            throw new IllegalArgumentException("El email es inválido.");
        }
        
        if (!EsNombreValido(estadoCivil)) {
            throw new IllegalArgumentException("El estado civil no puede tener números.");
        }
        
        if (!ContieneCaracteresPermitidos(direccionResidencia)) {
            throw new IllegalArgumentException("La dirección de residencia no puede tener carácteres especiales.");
        }
        
        if (VerificarPacienteEmail(email)) {
        JOptionPane.showMessageDialog(null, "Ya existe un paciente con ese email.", "Email existente.", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        String sql = "UPDATE pacientes SET tipoDocumento = ?, telefono = ?, direccionResidencia = ?, email = ?, EPS = ?, edad = ? WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, tipoDocumento);
            pstmt.setString(2, telefono);
            pstmt.setString(3, direccionResidencia);
            pstmt.setString(4, estadoCivil);
            pstmt.setString(5, email);
            pstmt.setString(6, EPS);
            pstmt.setString(7, edad);
            pstmt.setString(8, numeroDocumento);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Actualización completada");
            } else {
                JOptionPane.showMessageDialog(null, "No se encuentra un paciente con ese número de identificación");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage());
        }
    }
}