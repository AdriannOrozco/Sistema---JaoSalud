/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Medicamento.VerificarPorNombre;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JABER
 */
public class VerificarMedicamentoPorNombre {
    public static boolean VerificarMedicamentoNombre(String nombre) throws Exception {
        boolean existe = false;
        Connection con = ConexionBD.conectar();
        PreparedStatement ps;
        String sql = "SELECT COUNT(*) FROM medicamentos WHERE nombre = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                existe = count > 0;
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Error para verifcar el medicamento " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            throw new IllegalArgumentException("Error de conexión " + e.getMessage());    
            }
        }
        return existe;
    }
    
}
