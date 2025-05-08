/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Consultorios;

import Model.Consultorio;
import Persistencia.Database.ConexionBD;
import Persistencia.Doctor.MedicoDAO;
import com.sun.jdi.connect.spi.Connection;
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
        String sql = "INSERT INTO consultorios ( idConsultorio, especialidad) VALUES (?,?)";
        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, consultorio.getIdConsultorio());
            pstmt.setString(2, consultorio.getEspecialidad());
            pstmt.executeUpdate();

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El consultorio se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el consultorio.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            throw new Exception("Error" + e.getMessage());
        }
    }

    public void update(Consultorio consultorio) throws Exception {
        String campo = null;
        String valor = null;

        if (consultorio.getIdConsultorio() != null) {
            campo = "idConsultorio";
            valor = consultorio.getIdConsultorio();
        } else if (consultorio.getEspecialidad() != null) {
            campo = "especialidad";
            valor = consultorio.getEspecialidad();
        }

        if (campo == null || valor == null) {
            throw new IllegalArgumentException("No se encontró ningún campo válido para actualizar.");
        }

        String sql = "UPDATE consultorio SET " + campo + " = ? WHERE idConsultorio = ?";
        try (var con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, consultorio.getEspecialidad());
            pstmt.setString(2, consultorio.getIdConsultorio());

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
        pstmt.setString(1, consultorio.getIdConsultorio());

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
