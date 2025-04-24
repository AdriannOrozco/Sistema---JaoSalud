package Persistencia.Paciente.VerificarPacienteID;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class VerificarPacienteID {
    
    public static boolean VerificarPacientePorId(String numeroDocumento) throws Exception {
        boolean existe = false;
        Connection con = ConexionBD.conectar();
        PreparedStatement ps;
        String sql = "SELECT COUNT(*) FROM pacientes WHERE numeroDocumento = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, numeroDocumento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                existe = count > 0;
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Error para verifcar el paciente " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            throw new IllegalArgumentException("Error de conexión " + e.getMessage());    
            }
        }
        return existe;
    }
}
