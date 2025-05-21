
package Persistencia.Recepcionista;
import Persistencia.Database.ConexionBD;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Model.Recepcionista;
import java.sql.ResultSet;

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
    
    public void create(Recepcionista recepcionista) throws Exception {
    String sqlUsuario = "INSERT INTO usuarios (usuario, contraseña, rol) VALUES (?, ?, ?)";
    String sqlRecepcionista = "INSERT INTO recepcionistas (id, primerNombre, segundoNombre, primerApellido, segundoApellido, id_usuario) VALUES (?,?,?,?,?,?)";
    
    try (var con = ConexionBD.conectar()) {
        // Insertar en la tabla de usuarios
        PreparedStatement pstmtUsuario = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmtUsuario.setString(1, recepcionista.getId());
        pstmtUsuario.setString(2, recepcionista.getId());
        pstmtUsuario.setString(3, "Recepcionista");
        pstmtUsuario.executeUpdate();
        
        int idUsuario = 0;
        ResultSet rs = pstmtUsuario.getGeneratedKeys();
        if (rs.next()) {
            idUsuario = rs.getInt(1);
        } else {
            throw new Exception("No se pudo obtener el ID del usuario");
        }
        
        PreparedStatement pstmtRecepcionista = con.prepareStatement(sqlRecepcionista);
        pstmtRecepcionista.setString(1, recepcionista.getId());
        pstmtRecepcionista.setString(2, recepcionista.getPrimerNombre());
        pstmtRecepcionista.setString(3, recepcionista.getSegundoNombre());
        pstmtRecepcionista.setString(4, recepcionista.getPrimerApellido());
        pstmtRecepcionista.setString(5, recepcionista.getSegundoApellido());
        pstmtRecepcionista.setInt(6, idUsuario); 
        
        int filasAfectadas = pstmtRecepcionista.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("\"Recepcionista agregado con éxito.\"");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar al recepcionista", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al crear recepcionista y usuario: " + e.getMessage());
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
        
        String sql = "UPDATE recepcionistas SET " + campo + " = ? WHERE id = ?";
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
        String sql = "DELETE FROM recepcionistas WHERE id = ?";
        
          try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, recepcionista.getPrimerNombre());
        pstmt.setString(2,recepcionista.getSegundoNombre());
        pstmt.setString(3, recepcionista.getPrimerApellido());
        pstmt.setString(4, recepcionista.getSegundoApellido());
        pstmt.setString(5, recepcionista.getId());

        int filasAfectadas = pstmt.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("\"El recepcionista se eliminó correctamente.\"");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el recepcionista para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al eliminar recepcionista: " + e.getMessage());
    }
    }
}
