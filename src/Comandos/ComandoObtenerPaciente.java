
package Comandos;
import java.util.List;
import Model.Paciente;
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.Date;

public class ComandoObtenerPaciente implements IObtenerPaciente {
    
    
    @Override
    public List<Paciente> getPaciente(){
        
        List<Paciente> listaPacientes = new ArrayList<>();
        String sql = "SELECT usuario, contraseña, primerNombre, segundoNombre, primerApellido, segundoApellido, tipoDocumento, numeroDocumento, "
                + "telefono, direccionResidencia,estadoCivil ,genero, email, EPS, tipoSangre, fechaNacimiento, fechaRegistro, edad FROM pacientes";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String contraseña = rs.getString("contraseña");
                
                String primerNombre = rs.getString("primerNombre");
                String segundoNombre = rs.getString("segundoNombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("SegundoApellido");
                
                String tipoDocumento = rs.getString("tipoDocumento");
                String numeroDocumento = rs.getString("numeroDocumento");
                String telefono = rs.getString("telefono");
                String direccionResidencia = rs.getString("direccionResidencia");
                String estadoCivil = rs.getString("estadoCivil");
                String genero = rs.getString("genero");
                String email= rs.getString("email");
                String EPS = rs.getString("EPS");
                String tipoSangre = rs.getString("tipoSangre");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                Date fechaRegistro = rs.getDate("fechaRegistro");
                int edad = rs.getInt("edad");

                //Llenar paramétros del constructor.
                Paciente paciente = new Paciente(usuario, contraseña, primerNombre, segundoNombre, primerApellido, segundoApellido,
                tipoDocumento, numeroDocumento, telefono, direccionResidencia,estadoCivil ,genero, email, EPS, tipoSangre, fechaNacimiento, fechaRegistro, edad);
                listaPacientes.add(paciente);
            }

        } catch (SQLException e) {
           JOptionPane.showConfirmDialog(null, "Error al obtener paciente " + e.getMessage());
        }

        return listaPacientes;
        
}
    
    
    
    
}
