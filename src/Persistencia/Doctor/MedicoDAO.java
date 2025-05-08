package Persistencia.Doctor;

import Model.Medico;
import Persistencia.Database.ConexionBD;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class MedicoDAO {

    private static MedicoDAO instancia;

    public MedicoDAO() {

    }

    public static MedicoDAO getInstancia() {
        if (instancia == null) {
            instancia = new MedicoDAO();
        }
        return instancia;
    }

    public void create(Medico medico) throws Exception {
        String sql = "INSERT INTO medicos (primerNombre, segundoNombre,  primerApellido, String segundoApellido,identificacionDoctor,  especialidad,  añosExperiencia) VALUES (?,?,?,?,?,?,?)";
        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, medico.getPrimerNombre());
            pstmt.setString(2, medico.getSegundoNombre());
            pstmt.setString(3, medico.getPrimerApellido());
            pstmt.setString(4, medico.getSegundoApellido());
            pstmt.setString(5, medico.getIdentificacionDoctor());
            pstmt.setString(6, medico.getEspecialidad());
            pstmt.setString(7, medico.getAñosExperiencia());
            pstmt.executeUpdate();

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El medico se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el medico.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }

    public void update(Medico medico) throws Exception {
        String campo = null;
        String valor = null;
        if (medico.getPrimerNombre() != null) {
            campo = "PrimerNombre";
            valor = medico.getPrimerNombre();
        } else if (medico.getSegundoNombre() != null) {
            campo = "SegundoNombre";
            valor = medico.getSegundoNombre();
        } else if (medico.getPrimerApellido() != null) {
            campo = "PrimerApellido";
            valor = medico.getPrimerApellido();
        } else if (medico.getSegundoApellido() != null) {
            campo = "SegundoApellido";
            valor = medico.getSegundoApellido();
        } else if (medico.getIdentificacionDoctor() != null) {
            campo = "IdentificadorDoctor";
            valor = medico.getIdentificacionDoctor();
        } else if (medico.getEspecialidad() != null) {
            campo = "Especialidad";
            valor = medico.getEspecialidad();
        } else if (medico.getAñosExperiencia() != null) {
            campo = "AñosExperiencia";
            valor = medico.getAñosExperiencia();
        }

        if (campo == null || valor == null) {
            throw new IllegalArgumentException("No se encontró ningún campo válido para actualizar.");
        }

        String sql = "UPDATE medicos SET " + campo + " = ? WHERE IdentificacionDoctor = ?";

        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            pstmt.setString(2, medico.getIdentificacionDoctor());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El campo '" + campo + "' se actualizó correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el medico para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar medico: " + e.getMessage());
        }
    }

    public void delete(String identificacionDoctor) throws Exception {
        String sql = "DELETE FROM medicos WHERE IdentificacionDoctor = ?";

        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, identificacionDoctor);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El médico se eliminó correctamente.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el médico para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar médico: " + e.getMessage());
        }
    }
}
