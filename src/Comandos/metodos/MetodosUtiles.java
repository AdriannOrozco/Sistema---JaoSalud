package Comandos.metodos;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase representa métodos útiles generales-específicos.
 *
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

    public static String buscarNombreConsultorioPorId(String idConsultorio) {
        String sql = "SELECT consultorio FROM consultorios WHERE idConsultorio = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idConsultorio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String consultorio = rs.getString("consultorio");
                return consultorio;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar consultorio: " + e.getMessage());
        }
        return null;
    }


    public static boolean VerificarDisponibilidadDoctor(String identificacion, Date fechaCita, String hora) {
        boolean ocupado = false;

        String sql = "SELECT COUNT(*) FROM citas WHERE identificacionDoctor = ? AND fechaCita = ? AND hora = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificacion);
            ps.setDate(2, new java.sql.Date(fechaCita.getTime()));
            ps.setString(3, hora);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                ocupado = count > 0;
            }
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad del doctor: " + e.getMessage());
        }

        return ocupado;
    }

    public static boolean VerificarDisponibilidadConsultorio(int idConsultorio, Date fechaCita, String hora) {
        boolean ocupado = false;

        String sql = "SELECT COUNT(*) FROM citas WHERE idConsultorio = ? AND fechaCita = ? AND hora = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idConsultorio);
            ps.setDate(2, new java.sql.Date(fechaCita.getTime()));
            ps.setString(3, hora);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                ocupado = count > 0;
            }

            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad del consultorio: " + e.getMessage());
        }

        return ocupado;
    }

   


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
