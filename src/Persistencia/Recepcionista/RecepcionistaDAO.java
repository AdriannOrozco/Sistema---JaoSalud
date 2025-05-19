/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Recepcionista;
import Persistencia.Database.ConexionBD;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Model.Recepcionista;

/**
 *
 * @author JABER
 */
public class RecepcionistaDAO {
    private static RecepcionistaDAO instancia;

    public RecepcionistaDAO() {

    }

    public static RecepcionistaDAO getInstancia() {
        if (instancia == null) {
            instancia = new RecepcionistaDAO();
        }
        return instancia;
    }
    
    public void create(Recepcionista recepcionista) throws Exception{
        
        String sql = "INSERT INTO recepcionistas (primerNombre, segundoNombre, primerApellido, segundoApellido, identificacionRecepcionista) VALUES (?,?,?,?,?)";
        
        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, recepcionista.getPrimerNombre());
            pstmt.setString(2,recepcionista.getSegundoNombre());
            pstmt.setString(3, recepcionista.getPrimerApellido());
            pstmt.setString(4, recepcionista.getSegundoApellido());
            pstmt.setString(5, recepcionista.getId());

            pstmt.executeUpdate();

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Recepcionista agregado con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar al recepcionista", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }
    
    public void uptdate (Recepcionista recepcionista) throws Exception{
        String campo = null;
        String valor = null;
        
        if(recepcionista.getPrimerNombre() == null){
            campo = "primerNombre";
            valor = recepcionista.getPrimerNombre().toString();
        }else if(recepcionista.getSegundoApellido() == null){
            campo = "segundoNombre";
            valor = recepcionista.getSegundoNombre().toString();
        }else if (recepcionista.getPrimerApellido() == null ){
            campo = "primerApellido";
            valor = recepcionista.getPrimerApellido().toString();
        }else if (recepcionista.getSegundoApellido() == null){
            campo = "segundoApellido";
            valor = recepcionista.getSegundoApellido().toString();
        }else if (recepcionista.getId() == null){
            campo = "identificacionRecepcionista";
            valor = recepcionista.getId().toString();
        }
        
        String sql = "UPDATE recepcionistas SET " + campo + " = ? WHERE identificacionRecepcionista = ?";
         try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            pstmt.setString(2, recepcionista.getId());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El campo " + campo + "' se actualizó correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el recepcionista para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar recepcionista: " + e.getMessage());
        }
             
    }
    
    public void delete(Recepcionista recepcionista) throws Exception{
        String sql = "DELETE FROM recepcionistas WHERE identificacionRecepcionista = ?";
        
          try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, recepcionista.getPrimerNombre());
        pstmt.setString(2,recepcionista.getSegundoNombre());
        pstmt.setString(3, recepcionista.getPrimerApellido());
        pstmt.setString(4, recepcionista.getSegundoApellido());
        pstmt.setString(5, recepcionista.getId());

        int filasAfectadas = pstmt.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "El recepcionista se eliminó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el recepcionista para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al eliminar recepcionista: " + e.getMessage());
    }
    }
}
