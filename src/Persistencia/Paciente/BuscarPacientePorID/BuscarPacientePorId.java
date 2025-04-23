package Persistencia.Paciente.BuscarPacientePorID;

import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adrian
 */
public class BuscarPacientePorId {

    public String BuscarPacientePorDocumento(String numeroDocumento) throws Exception {

        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!MetodosCadenasDeTexto.ContieneSoloNumeros(numeroDocumento)) {
            throw new IllegalArgumentException("El número de documento sólo debe contener números.");
        }

        String sql = "SELECT primerNombre, primerApellido, segundoApellido FROM pacientes WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String primerNombre = rs.getString("primerNombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("segundoApellido");
                return primerNombre + " " + primerApellido + " " + " " + segundoApellido;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar paciente: " + e.getMessage());
        }
    }
}
