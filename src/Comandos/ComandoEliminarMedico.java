/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

//Default imports
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author JABER
 */
public class ComandoEliminarMedico implements IEliminarMedico {
    public void eliminarMedico(String identificacionDoctor){
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea despedir al medico?", "Seleccionar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso.", "Proceso cancelado", JOptionPane.OK_OPTION);
            return;
        }

        if (confirmacion == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM medicos WHERE identificacionDoctor = ?";
            try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1,identificacionDoctor);
                

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "El medico ha sido despedido", "Proceso completado", JOptionPane.OK_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay un medico con cédula " + identificacionDoctor, "No encontrado", JOptionPane.ERROR);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(), " error", JOptionPane.ERROR);
            }
        }
    }

    @Override
    public void EliminarMedico(String identificacionDoctor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
