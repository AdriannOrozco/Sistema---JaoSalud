package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/agendasync?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASS = "";     
    public static Connection conectar() {
        Connection con = null; 
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}
