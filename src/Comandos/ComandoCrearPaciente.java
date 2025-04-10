package Comandos;
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ComandoCrearPaciente implements ICrearPaciente {

    @Override
    public void CrearPaciente(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
            String tipoDocumento, String numeroDocumento, String telefono, String direccionResidencia, String estadoCivil,
            String genero, String email, String EPS, String tipoSangre, Date fechaNacimiento, Date fechaRegistro, int edad) throws Exception {

        if (verificarPaciente(numeroDocumento)) {
        JOptionPane.showInternalInputDialog(null, "Existe un paciente con ese número de documento.", "Proceso completado", JOptionPane.OK_OPTION);   
        return;
        }
          
             //Se crea la sentencia SQL
            String sql = "INSERT INTO nombre tabla (atributos) VALUES (?)";
        
            try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, primerNombre);
            pstmt.setString(2, segundoNombre);
            pstmt.setString(3, primerApellido);
            pstmt.setString(4, segundoApellido);
            pstmt.setString(5, tipoDocumento);
            pstmt.setString(6, numeroDocumento);
            pstmt.setString(7, telefono);
            pstmt.setString(8, direccionResidencia);
            pstmt.setString(9, estadoCivil);
            pstmt.setString(10, genero);
            pstmt.setString(11, email);
            pstmt.setString(12, EPS);
            pstmt.setString(13, tipoSangre);
            pstmt.setDate(14, new java.sql.Date(fechaNacimiento.getTime()));
            pstmt.setDate(15, new java.sql.Date(fechaRegistro.getTime()));
            pstmt.setInt(16, edad);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El paciente se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
             throw new Exception("Error al agregar paciente: " + e.getMessage());
        }              
    }

    //Métodos relacionados con este comando.
    public static boolean verificarPaciente(String numeroDocumento) {
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
}
