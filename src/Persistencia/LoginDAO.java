package Persistencia;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public boolean validarCredenciales(String usuario, String contraseña, String rol) {
        if (usuario == null || usuario.trim().isEmpty()
                || contraseña == null || contraseña.trim().isEmpty()) {
            return false;
        }

        String sql = "SELECT COUNT(*) FROM usuarios WHERE username = ? AND password = ? AND rol = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ps.setString(3, rol);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String obtenerIdentificacion(String usuario, String rol) {
        String sql = "SELECT identificacion FROM usuarios WHERE username = ? AND rol = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, rol);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("identificacion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String obtenerNombreCompleto(String identificacion, String rol) {
        String tableName;
        switch (rol.toLowerCase()) {
            case "doctor" ->
                tableName = "doctores";
            case "recepcionista" ->
                tableName = "recepcionistas";
            case "administrador" ->
                tableName = "administradores";
            case "paciente" ->
                tableName = "pacientes";
            default -> {
                return null;
            }
        }

        String sql = "SELECT primerNombre, segundoNombre, primerApellido, segundoApellido "
                + "FROM " + tableName + " WHERE identificacion = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, identificacion);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String primerNombre = rs.getString("primerNombre");
                    String segundoNombre = rs.getString("segundoNombre");
                    String primerApellido = rs.getString("primerApellido");
                    String segundoApellido = rs.getString("segundoApellido");

                    return String.format("%s %s %s %s",
                            primerNombre,
                            segundoNombre != null ? segundoNombre : "",
                            primerApellido,
                            segundoApellido != null ? segundoApellido : "").trim();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
