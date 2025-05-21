/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Recepcionista.BuscarPorID;
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
public class BuscarRecepcionistaPorId {
    
    public String BuscarRecepcionistaPorDocumento(String identificacionRecepcionista, JTextField txtPrimerNombreRecepcionista, JTextField txtSegundoNombreRecepcionista,JTextField txtPrimerApellidoRecepcionista, JTextField txtSegundoApellidoRecepcionista) throws Exception{
        
         if (identificacionRecepcionista == null || identificacionRecepcionista.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!ContieneSoloNumeros(identificacionRecepcionista)) {
            throw new IllegalArgumentException("El número de documento sólo debe contener números.");
        }
       
        String sql = "SELECT primerNombre, segundoNombre , primerApellido , segundoApellido FROM recepcionistas " + " WHERE identificacionRecepcionista = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, identificacionRecepcionista);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtPrimerNombreRecepcionista.setText(rs.getString("primerNombre"));
                txtSegundoNombreRecepcionista.setText(rs.getString("segundoNombre"));
                txtPrimerApellidoRecepcionista.setText(rs.getString("primerApellido"));
                txtSegundoApellidoRecepcionista.setText(rs.getString("segundoApellido"));
              return txtPrimerNombreRecepcionista + " " + txtSegundoNombreRecepcionista + " " + txtPrimerApellidoRecepcionista + " " + txtSegundoApellidoRecepcionista + " ";
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar recepcionista: " + e.getMessage());
        }
    }
}
