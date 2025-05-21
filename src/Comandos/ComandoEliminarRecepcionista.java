/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Recepcionista;
import Persistencia.Database.ConexionBD;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Recepcionista.RecepcionistaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class ComandoEliminarRecepcionista implements IEliminarRecepcionista{

    @Override
    public void EliminarRecepcionista(String identificacionRecepcionista) throws Exception {
       
        if (identificacionRecepcionista == null || identificacionRecepcionista.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío");
        }
        
        if (!MetodosCadenasDeTexto.ContieneSoloNumeros(identificacionRecepcionista)) {
            throw new IllegalArgumentException("El número de documento sólo contiene números..");
        }
        
        
        int confirmacion = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro que desea despedir al recepcionista con documento " + identificacionRecepcionista + "?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, 
                    "Se ha cancelado el proceso.", 
                    "Proceso cancelado", 
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (confirmacion == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM recepcionistas WHERE id = ?";
            try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1,identificacionRecepcionista);
                

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "El recepcionista ha sido despedido", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay un recepcionista con cédula " + identificacionRecepcionista, "No encontrado", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(), " error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    }

    

