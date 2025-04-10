
package Comandos;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoCancelarCita implements ICancelarCita {
    
     @Override
    public void CancelarCita(int idCita){
        String sql = "DELETE FROM citas WHERE idCita";
        
      try (Connection con = ConexionBD.conectar();
         PreparedStatement pstmt = con.prepareStatement(sql)) {
       
            pstmt.setInt(1, idCita);
            

            int affectedRows = pstmt.executeUpdate();
            
            
            if (affectedRows > 0) {
           JOptionPane.showInternalInputDialog(null,"La cita se eliminó con éxito.", "Proceso completado", JOptionPane.OK_OPTION);
            } else {
           JOptionPane.showInternalInputDialog(null,"No hay una cita con cédula " + idCita , "No encontrado", JOptionPane.ERROR);
            }
        
    } catch (SQLException e) {
         JOptionPane.showInternalInputDialog(null,"Error al eliminar", "Error", JOptionPane.ERROR);
    }     
    }
}
