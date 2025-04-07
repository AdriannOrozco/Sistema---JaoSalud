package Comandos;

//Default imports
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoEliminarPaciente implements IEliminarPaciente{
    
    @Override
    public void EliminarPaciente(String tipoDocumento, String numeroDocumento){
        String sql = "DELETE FROM pacientes WHERE tipoDocumento = ? AND numeroDocumento = ?";
        
      try (Connection con = ConexionBD.conectar();
         PreparedStatement pstmt = con.prepareStatement(sql)) {
       
            pstmt.setString(1, tipoDocumento);
            pstmt.setString(2, numeroDocumento);

            int affectedRows = pstmt.executeUpdate();
            
            
            if (affectedRows > 0) {
           JOptionPane.showInternalInputDialog(null,"El paciente se eliminó con éxito.", "Proceso completado", JOptionPane.OK_OPTION);
            } else {
           JOptionPane.showInternalInputDialog(null,"No hay un paciente con cédula " + numeroDocumento, "No encontrado", JOptionPane.ERROR);
            }
        
    } catch (SQLException e) {
         JOptionPane.showInternalInputDialog(null,"Error al eliminar", "Error", JOptionPane.ERROR);
    }     
    }
}
