package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {
    
    private static Connection con = null;
    
    private static final String URL = "jdbc:mysql://localhost:3306/loginbarbershop?characterEncoding=utf8"; //Cambiar el nombre de la base de datos para que no genere error.
    private static final String USER = "root"; // Ajustar según el usuario de la base de datos.
    private static final String PASS = "";     // Ajusta según la contraseña de la base de datos.
    
      public Connection conectar() {
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            if (con != null) {
                System.out.println("Conexión establecida con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }
        return con;
    }
}
