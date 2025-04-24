package Persistencia.Consultorios.BuscarIdConsultorioPorConsultorio;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class BuscarIdConsultorioPorConsultorio {

    public static int BuscarIdConsultorio(String consultorio) {

        String sql = "SELECT idConsultorio FROM consultorios WHERE consultorio = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, consultorio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idConsultorio = rs.getInt("idConsultorio");
                return idConsultorio;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar consultorio: " + e.getMessage());
        }
        return 0;
    }
}
