package infraestructura.sesion;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para mantener información del usuario logueado en la aplicación
 *
 * @author Osvaldo
 */
public class SesionUsuario {

    private static String idRecepcionistaLogeado;
    private static String idPacienteLogeado;
    private static String idMedicoLogeado;
    private static String idAdministradorLogeado;

    /**
     * Establece el ID del recepcionista logueado
     *
     * @param id ID del recepcionista
     */
    public static void setIdRecepcionista(String id) {
        idRecepcionistaLogeado = id;
    }

    /**
     * Establece el ID del paciente logueado
     *
     * @param id ID del paciente
     */
    public static void setIdPaciente(String id) {
        idPacienteLogeado = id;
    }

    /**
     * Establece el ID del médico logueado
     *
     * @param id ID del médico
     */
    public static void setIdMedico(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del médico no puede estar vacío");
        }
        idMedicoLogeado = id.trim();
    }

    /**
     * Establece el ID del administrador logueado
     *
     * @param id ID del administrador
     */
    public static void setIdAdministrador(String id) {
        idAdministradorLogeado = id;
    }

    /**
     * Obtiene el ID del recepcionista logueado
     *
     * @return ID del recepcionista
     */
    public static String getIdRecepcionista() {
        return idRecepcionistaLogeado;
    }

    /**
     * Obtiene el ID del paciente logueado
     *
     * @return ID del paciente
     */
    public static String getIdPaciente() {
        return idPacienteLogeado;
    }

    /**
     * Obtiene el ID del médico logueado
     *
     * @return ID del médico
     */
    public static String getIdMedico() {
        return idMedicoLogeado;
    }

    /**
     * Obtiene el ID del administrador logueado
     *
     * @return ID del administrador
     */
    public static String getIdAdministrador() {
        return idAdministradorLogeado;
    }

    /**
     * Obtiene el ID del usuario logueado independientemente de su rol
     *
     * @return ID del usuario logueado o null si no hay ningún usuario logueado
     */
    public static String getIdUsuarioLogueado() {
        // Devolver el primer ID no nulo que encontremos
        if (idMedicoLogeado != null && !idMedicoLogeado.isEmpty()) {
            return idMedicoLogeado;
        } else if (idPacienteLogeado != null && !idPacienteLogeado.isEmpty()) {
            return idPacienteLogeado;
        } else if (idRecepcionistaLogeado != null && !idRecepcionistaLogeado.isEmpty()) {
            return idRecepcionistaLogeado;
        } else if (idAdministradorLogeado != null && !idAdministradorLogeado.isEmpty()) {
            return idAdministradorLogeado;
        }
        // Si no hay usuario logueado, devolver null
        return null;
    }

    /**
     * Determina el tipo de usuario actualmente logueado
     *
     * @return String con el tipo de usuario: "medico", "paciente",
     * "recepcionista", "administrador" o "ninguno"
     */
    public static String getTipoUsuarioLogueado() {
        if (idMedicoLogeado != null && !idMedicoLogeado.isEmpty()) {
            return "medico";
        } else if (idPacienteLogeado != null && !idPacienteLogeado.isEmpty()) {
            return "paciente";
        } else if (idRecepcionistaLogeado != null && !idRecepcionistaLogeado.isEmpty()) {
            return "recepcionista";
        } else if (idAdministradorLogeado != null && !idAdministradorLogeado.isEmpty()) {
            return "administrador";
        }
        return "ninguno";
    }

    /**
     * Cierra la sesión de todos los usuarios
     */
    public static void cerrarSesion() {
        idMedicoLogeado = null;
        idRecepcionistaLogeado = null;
        idPacienteLogeado = null;
        idAdministradorLogeado = null;
    }

    /**
     * Verifica si un médico existe en la base de datos
     *
     * @param idMedico ID del médico a verificar
     * @return true si el médico existe, false en caso contrario
     */
    public static boolean verificarMedicoExiste(String idMedico) {
        String sql = "SELECT COUNT(*) FROM medicos WHERE identificacionDoctor = ?";
        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idMedico);
            ResultSet rs = ps.executeQuery();

            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error verificando médico: " + e.getMessage());
        }
    }

    public static boolean verificarRecepcionistaExiste(String usuario) {
        String sql = "SELECT COUNT(*) FROM recepcionistas WHERE id = ?";
        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verificarPacienteExiste(String usuario) {
        String sql = "SELECT COUNT(*) FROM pacientes WHERE id = ?";
        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verificarAdministradorExiste(String usuario) {
        String sql = "SELECT COUNT(*) FROM administrador WHERE id = ?";
        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
