package Persistencia.Doctor.BuscarIdPorNombreDoctor;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class BuscarIdPorNombreDoctor {

    public static String BuscarIdentificacionPorNombreDoctor(String nombre) {

        String sql = "SELECT identificacionDoctor FROM medicos WHERE CONCAT(primerNombre, ' ', primerApellido) = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String identificacion = rs.getString("identificacionDoctor");
                return identificacion;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar médico: " + e.getMessage());
        }
        return null;
    }
}
