/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.ConexionBD;
import Model.Consultorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class ComandoCrearMedico implements ICrearMedico {

    @Override
    public void crearMedico(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
            String identificacionDoctor, String especialidad, double salario, int añosExperiencia, Consultorio consultorio) {
        

        //Se crea la sentencia SQL
        String sql = "INSERT INTO medicos (primerNombre, segundoNombre, primerApellido, segundoApellido, "
                + "identificacionDoctor, especialidad, salario, añosExperiencia) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, primerNombre);
            pstmt.setString(2, segundoNombre);
            pstmt.setString(3, primerApellido);
            pstmt.setString(4, segundoApellido);
            pstmt.setString(5, identificacionDoctor);
            pstmt.setString(6, especialidad);
            pstmt.setDouble(7, salario);
            pstmt.setInt(8, añosExperiencia);

            pstmt.executeUpdate();
            JOptionPane.showInternalMessageDialog(null, "El médico se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Error al agregar médico: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
