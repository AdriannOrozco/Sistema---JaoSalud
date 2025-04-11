package Comandos.metodos;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Esta clase representa métodos útiles generales-específicos.
 * @author Adrian
 */

public class MetodosUtiles {

    //Métodos de uso universal.
    public static boolean ContieneNumeros(String str) {
        return str.matches(".*\\d.*");
    }

    public static boolean ContieneCaracteresPermitidos(String str) {
        return str.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s']+$");
    }

    public static boolean EsNombreValido(String str) {
        return !ContieneNumeros(str) && ContieneCaracteresPermitidos(str);

    }

    public static boolean ContieneSoloNumeros(String texto) {
        return texto.matches("^[0-9]+$");
    }

    public static boolean EsEmailValido(String email) {
        return email.matches("^[^@]+@[^@]+\\.[^@]+$");
    }
    
    //Métodos de uso específico - CreatePatientCommand
    public static boolean verificarPacienteID(String numeroDocumento) {
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error para crear un paciente " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error of connection " + e.getMessage());
            }
        }
        return existe;
    }

    public static boolean VerificarPacienteEmail(String email) {
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error para crear un paciente " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error of connection " + e.getMessage());
            }
        }
        return existe;
    }
    
}
