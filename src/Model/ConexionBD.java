package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {
    
    //Recordar cambiar "barbershop" por el futuro nombre de la base de datos.
    private static final String URL = "jdbc:mysql://localhost:3306/barbershop?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASS = "";     
    
    public static Connection conectar() {
        Connection con = null; 
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion establecida con exito.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}
