package Persistencia.Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/jaosalud?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "2407";

    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion establecida con exito.");
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error de conexion: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }
    }
}
