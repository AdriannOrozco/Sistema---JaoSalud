package Comandos;

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
            String numeroDocumento){
    
    String sql = "UPDATE pacientes SET tipoDocumento = ?, telefono = ?, direccionResidencia = ?, email = ?, "
                   + "EPS = ?, edad = ? WHERE numeroDocumento = ?";
    
try (Connection con = ConexionBD.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Establecer los valores en la consulta
            
            pstmt.setString(1, tipoDocumento);
            pstmt.setString(2, telefono);
            pstmt.setString(3, direccionResidencia);
            pstmt.setString(4, estadoCivil);
            pstmt.setString(5, email);
            pstmt.setString(6, EPS);
            pstmt.setString(7, edad);
            pstmt.setString(8, numeroDocumento); //Where de coincidencia 
           
            // Ejecutar la consulta
            int affectedRows= pstmt.executeUpdate();

            if (affectedRows> 0) {
            JOptionPane.showMessageDialog(null, "Actualización completada");
            } else {
            JOptionPane.showMessageDialog(null, "No se encuentra un paciente con ese número de identificación");
            }

        } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage());
        }
    }
    }
    
    

