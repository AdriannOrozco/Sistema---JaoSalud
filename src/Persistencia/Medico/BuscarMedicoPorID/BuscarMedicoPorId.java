/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Medico.BuscarMedicoPorID;

import Persistencia.Database.ConexionBD;
import static Persistencia.MetodosUtiles.MetodosCadenasDeTexto.ContieneSoloNumeros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;

/**
 *
 * @author JABER
 */
public class BuscarMedicoPorId {

    public String BuscarMedicoPorDocumento(String identificacionDoctor ,JTextField jTextFieldPrimerNombre, JTextField jTextFieldSegundoNombre, JTextField jTextFieldPrimerApellido, JTextField jTextFieldSegundoApellido) throws Exception {

        if (identificacionDoctor == null || identificacionDoctor.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!ContieneSoloNumeros(identificacionDoctor)) {
            throw new IllegalArgumentException("El número de documento sólo debe contener números.");
        }
        
        String sql = "SELECT primerNombre, segundoNombre , primerApellido ,segundoApellido FROM medicos " + " WHERE identificacionDoctor = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, identificacionDoctor);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                jTextFieldPrimerNombre.setText(rs.getString("primerNombre"));
                jTextFieldSegundoNombre.setText(rs.getString("segundoNombre"));
                jTextFieldPrimerApellido.setText(rs.getString("primerApellido"));
                jTextFieldSegundoApellido.setText(rs.getString("segundoApellido"));
              return jTextFieldPrimerNombre + " " + jTextFieldSegundoNombre + " " + jTextFieldPrimerApellido + " " + jTextFieldSegundoApellido + " ";
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar paciente: " + e.getMessage());
        }
        

      
    }
    
        
    
}
