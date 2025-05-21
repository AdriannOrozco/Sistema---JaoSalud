/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Medicamentos.BuscarMedicamentoPorID;

import Persistencia.Database.ConexionBD;
import static Persistencia.MetodosUtiles.MetodosCadenasDeTexto.ContieneSoloNumeros;
import static Persistencia.MetodosUtiles.MetodosCadenasDeTexto.EsNombreValido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;

/**
 *
 * @author JABER
 */
public class BuscarMedicamentoPorId {
    public String BuscarMedicamenoPorNombre(String Medicamento, JTextField txtPrecioMedicamento1, JTextField txtDosisMedicamento1) throws Exception{
        
         if (Medicamento == null || Medicamento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!EsNombreValido(Medicamento)) {
            throw new IllegalArgumentException("El número de documento sólo debe contener números.");
        }
       
        String sql = "SELECT precio, dosisDiarias  FROM medicamentos " + " WHERE nombre = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, Medicamento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtPrecioMedicamento1.setText(rs.getString("precio"));
                txtDosisMedicamento1.setText(rs.getString("dosisDiarias"));
                
              return txtPrecioMedicamento1 + " " + txtDosisMedicamento1 + " ";
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar recepcionista: " + e.getMessage());
        }
    }
}
