/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Consultorio.BuscarPorNombre;

import Model.Consultorio;
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
public class BuscarConsultorioPorNombre {

    public String BuscarConsultorioNombre(String consultorio, JTextField txtEspecialidadConsultorio) throws Exception {
        if (consultorio.isEmpty()) {
            throw new IllegalArgumentException("El nombre del consultorio no puede estar vacío.");
        }

        String sql = "SELECT especialidad FROM consultorios " + " WHERE consultorio = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, consultorio);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtEspecialidadConsultorio.setText(rs.getString("especialidad"));
                ;

                return txtEspecialidadConsultorio + " ";
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar el consultorio: " + e.getMessage());
        }
    }

}
