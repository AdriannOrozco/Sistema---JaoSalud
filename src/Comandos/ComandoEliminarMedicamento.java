/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Medicamentos;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class ComandoEliminarMedicamento implements IEliminarMedicamento{

    @Override
    public void EliminarMedicamento(String nombre) throws Exception {
   
        
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el medicamento?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso.", "Proceso cancelado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (confirmacion == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM medicamentos WHERE nombre = ?";

            try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1, nombre);

                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null, "El medicamento se eliminó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún medicamento con nombre: " + nombre, "No encontrado", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar medicamento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
}
