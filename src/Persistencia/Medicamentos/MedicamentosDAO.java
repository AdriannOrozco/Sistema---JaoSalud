/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Medicamentos;

import Model.Medicamentos;
import Persistencia.Database.ConexionBD;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class MedicamentosDAO {

    private static MedicamentosDAO instancia;

    public MedicamentosDAO() {

    }

    public static MedicamentosDAO getInstancia() {
        if (instancia == null) {
            instancia = new MedicamentosDAO();
        }
        return instancia;
    }

    public void create(Medicamentos medicamento) throws Exception {

        String sql = "INSERT INTO medicamentos (nombre , precio , dosisDiarias , disponible) VALUES (?,?,?,?)";

        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, medicamento.getNombre());
            pstmt.setDouble(2, medicamento.getPrecio());
            pstmt.setInt(3, medicamento.getDosisDiarias());
            pstmt.setBoolean(4, medicamento.getDisponible());

            pstmt.executeUpdate();

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El Medicamento se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el medicamento.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Medicamentos medicamento) throws Exception {

        String campo = null;
        String valor = null;

        if (medicamento.getPrecio() != null){
            campo = "precio";
            valor = medicamento.getPrecio().toString();
        }else if(medicamento.getDosisDiarias() != null){
            campo = "dosisDiaria";
            valor = medicamento.getDosisDiarias().toString();
        }
        
         if (campo == null || valor == null) {
            throw new IllegalArgumentException("No se encontró ningún campo válido para actualizar.");
        }
         
        String sql = "UPDATE medicamentos SET " + campo + " = ? WHERE idMedicamento = ?";
         try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            pstmt.setString(2, medicamento.getNombre());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El campo '" + campo + "' se actualizó correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el medicamento para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar medicamento: " + e.getMessage());
        }
    }
    
    public void delete(Medicamentos medicamento) throws Exception{
        String sql = "DELETE FROM medicamentos WHERE idMedicamento = ?";
        
        
    try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, medicamento.getNombre());
        pstmt.setDouble(2,medicamento.getPrecio());
        pstmt.setInt(3, medicamento.getDosisDiarias());
        pstmt.setBoolean(4, medicamento.getDisponible());

        int filasAfectadas = pstmt.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "El medicamento se eliminó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el medicamento para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al eliminar medicamento: " + e.getMessage());
    }
    }

}
