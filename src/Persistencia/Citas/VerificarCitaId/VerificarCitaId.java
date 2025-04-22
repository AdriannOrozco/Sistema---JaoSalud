package Persistencia.Citas.VerificarCitaId;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class VerificarCitaId {

    public boolean verificarCita(int idCita) {
        String sql = "SELECT 1 FROM citas WHERE idCita = ?";
        try (Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                System.out.println(idCita + " no está registrada." + "Error de búsqueda" + JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            return false;
        }
    }
}
