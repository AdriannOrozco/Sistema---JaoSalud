package Comandos.metodos;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

    public void cargarDatosPacientes(String numeroDocumento, String tipoDocumento, JTextField txtCargarEPS, JTextField txtCargarPrimerNombre, JTextField txtCargarPrimerApellido) throws Exception {
        ConexionBD con = new ConexionBD();
        String sql = "SELECT EPS, primerNombre, segundoNombre FROM pacientes WHERE numeroDocumento = ? AND tipoDocumento = ?";

        try (Connection conexion = con.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            pstmt.setString(2, tipoDocumento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtCargarEPS.setText(rs.getString("EPS"));
                txtCargarPrimerNombre.setText(rs.getString("primerNombre"));
                txtCargarPrimerApellido.setText(rs.getString("primerApellido"));

            } else {
                JOptionPane.showMessageDialog(null, "El paciente con número de documento " + numeroDocumento + " no se encuentra en JaoSalud.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener información del paciente: " + e.getMessage());
        }
    }

    public boolean verificarPaciente(String numeroDocumento, String tipoDocumento, String columnaSeleccionada1, String columnaSeleccionada2) {
        String query = "SELECT 1 FROM barbers WHERE " + columnaSeleccionada1 + " = ? AND " + columnaSeleccionada2 + " = ?";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setString(1, numeroDocumento);
            ps.setString(2, tipoDocumento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, numeroDocumento + " no está registrado." + "Error de búsqueda" + JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
            return false;
        }
    }

}
