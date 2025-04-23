package Persistencia.Citas.CargarMotivo;

import Persistencia.Database.ConexionBD;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CargarMotivo {
    public void cargarMotivoPorID(String idCitaStr, JTextArea area_motivo) {
        String sql = "SELECT motivo FROM citas WHERE idCita = ?";

        if (idCitaStr == null) {
            throw new IllegalArgumentException("El número de la cita no puede estar vacío");
        }
        if (!MetodosCadenasDeTexto.ContieneSoloNumeros(idCitaStr)) {
            throw new IllegalArgumentException("El número de la cita sólo contiene números.");
        }

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idCita;
            idCita = Integer.parseInt(idCitaStr);
            pstmt.setInt(1, idCita);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String motivo = rs.getString("motivo");
                area_motivo.setText(motivo);
            } else {
                throw new IllegalArgumentException("No existe una cita con esa identificación..");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
