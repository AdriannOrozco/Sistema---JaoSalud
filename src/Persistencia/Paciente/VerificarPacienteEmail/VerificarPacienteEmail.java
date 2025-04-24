package Persistencia.Paciente.VerificarPacienteEmail;

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
public class VerificarPacienteEmail {

    public static boolean VerificarPacientePorEmail(String email) throws Exception {
        boolean existe = false;
        Connection con = ConexionBD.conectar();
        PreparedStatement ps;
        String sql = "SELECT COUNT(*) FROM pacientes WHERE email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
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
                throw new IllegalArgumentException("Error para conectar " + e.getMessage());
            }
        }
        return existe;
    }
}
