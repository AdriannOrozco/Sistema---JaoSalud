package Comandos;
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ComandoCrearPaciente implements ICrearPaciente {
    
   @Override
   public void CrearPaciente(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido
           ,String tipoDocumento, String numeroDocumento,String telefono,  String direccionResidencia, String estadoCivil
           ,String genero, String email, String EPS, String tipoSangre, Date fechaNacimiento, Date fechaRegistro, int edad){
   
    //Se crea la sentencia SQL
    String sql = "INSERT INTO nombre tabla (atributos) VALUES (?)";
    try (Connection con = ConexionBD.conectar();
         PreparedStatement pstmt = con.prepareStatement(sql)) {
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
        
        pstmt.executeUpdate();
        JOptionPane.showInternalInputDialog(null,"El paciente se agregó con éxito.", "Proceso completado", JOptionPane.OK_OPTION);
        
    } catch (SQLException e) {
         JOptionPane.showInternalInputDialog(null,"Error al agregar paciente.", "Error", JOptionPane.ERROR);
    }
    
   }
}
