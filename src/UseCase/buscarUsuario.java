package UseCase;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class buscarUsuario {
    
      public boolean accesoUsuario(String user, String pass, String rol) {
        ConexionBD db = new ConexionBD();
        boolean accesoCorrecto = false;

        try {
            Connection cn = db.conectar();
            
            //El encargado del backend debe encargarse que se verifique el atributo "Usuario" y "Contraseña" para el acceso.
            String sql = "SELECT usuario, contraseña, rol FROM usuarios WHERE usuario = ? AND contraseña = ? AND rol = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            pst.setString(3, rol);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                accesoCorrecto = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

        return accesoCorrecto;
    }
}
