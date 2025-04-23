package Comandos.metodos;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MetodosUtiles {


    public static String buscarNombreConsultorioPorId(String idConsultorio) {
        String sql = "SELECT consultorio FROM consultorios WHERE idConsultorio = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idConsultorio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String consultorio = rs.getString("consultorio");
                return consultorio;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar consultorio: " + e.getMessage());
        }
        return null;
    }
}
