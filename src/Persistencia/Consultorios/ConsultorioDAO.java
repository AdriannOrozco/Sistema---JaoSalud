
package Persistencia.Consultorios;

import Model.Consultorio;
import Persistencia.Database.ConexionBD;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class ConsultorioDAO {

    private static ConsultorioDAO instancia;

    
    public ConsultorioDAO() {

    }

    public static ConsultorioDAO getInstancia() {
        if (instancia == null) {
            instancia = new ConsultorioDAO();
        }
        return instancia;
    }

    public void create(Consultorio consultorio) throws Exception {
    String sql = "INSERT INTO consultorios (consultorio, especialidad) VALUES  (?, ?)";
    try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
       
        pstmt.setString(1, consultorio.getConsultorio());  
        pstmt.setString(2,consultorio.getEspecialidad());

        int filasAfectadas = pstmt.executeUpdate(); 

        if (filasAfectadas > 0) {
            System.out.println("Se agregó correctamente el consultorio " + consultorio.getConsultorio());
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el consultorio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al crear consultorio: " + e.getMessage());
    }
}


    public void update(Consultorio consultorio) throws Exception {
        String campo = null;
        String valor = null;

        if (consultorio.getConsultorio() != null) {
            campo = "consultorio";
            valor = consultorio.getConsultorio();
        } else if (consultorio.getEspecialidad() != null) {
            campo = "especialidad";
            valor = consultorio.getEspecialidad();
        }

        if (campo == null || valor == null) {
            throw new IllegalArgumentException("No se encontró ningún campo válido para actualizar.");
        }

        String sql = "UPDATE consultorio SET " + campo + " = ? WHERE idConsultorio = ?";
        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            pstmt.setString(2, consultorio.getEspecialidad());
           

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La especialidad fue actualizada correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el consultorio para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar el consultorio: " + e.getMessage());
        }

    }
    
    public void delete(Consultorio consultorio) throws Exception {
    String sql = "DELETE FROM consultorio WHERE idConsultorio = ?";

    try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, consultorio.getConsultorio());
        pstmt.setString(2,consultorio.getEspecialidad());

        int filasAfectadas = pstmt.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "El consultorio se eliminó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el consultorio para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al eliminar consultorio: " + e.getMessage());
    }
}

}
