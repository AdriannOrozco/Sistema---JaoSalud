package Persistencia.Doctor;

import Model.Medico;
import Persistencia.Database.ConexionBD;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

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
    String sqlUsuario = "INSERT INTO usuarios (usuario, contraseña, rol) VALUES (?, ?, ?)";
    String sqlMedico = "INSERT INTO medicos (primerNombre, segundoNombre, primerApellido, segundoApellido, identificacionDoctor, especialidad, añosExperiencia, id_usuario) VALUES (?,?,?,?,?,?,?,?)";
    
    try (var con = ConexionBD.conectar()) {
   
        PreparedStatement pstmtUsuario = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmtUsuario.setString(1, medico.getIdentificacionDoctor());
        pstmtUsuario.setString(2, medico.getIdentificacionDoctor());
        pstmtUsuario.setString(3, "Medico");
        pstmtUsuario.executeUpdate();
       
        int idUsuario = 0;
        ResultSet rs = pstmtUsuario.getGeneratedKeys();
        if (rs.next()) {
            idUsuario = rs.getInt(1);
        } else {
            throw new Exception("No se pudo obtener el ID del usuario");
        }
  
        PreparedStatement pstmtMedico = con.prepareStatement(sqlMedico);
        pstmtMedico.setString(1, medico.getPrimerNombre());
        pstmtMedico.setString(2, medico.getSegundoNombre());
        pstmtMedico.setString(3, medico.getPrimerApellido());
        pstmtMedico.setString(4, medico.getSegundoApellido());
        pstmtMedico.setString(5, medico.getIdentificacionDoctor());
        pstmtMedico.setString(6, medico.getEspecialidad());
        pstmtMedico.setString(7, medico.getAñosExperiencia());
        pstmtMedico.setInt(8, idUsuario);  
        
        int filasAfectadas = pstmtMedico.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("El médico se creó con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el médico.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        throw new Exception("Error al crear médico y usuario: " + e.getMessage());
    }
}

    public void update(Medico medico) throws Exception {
        String campo = null;
        String valor = null;
        if (medico.getPrimerNombre() != null) {
            campo = "primerNombre";
            valor = medico.getPrimerNombre();
        } else if (medico.getSegundoNombre() != null) {
            campo = "segundoNombre";
            valor = medico.getSegundoNombre();
        } else if (medico.getPrimerApellido() != null) {
            campo = "primerApellido";
            valor = medico.getPrimerApellido();
        } else if (medico.getSegundoApellido() != null) {
            campo = "segundoApellido";
            valor = medico.getSegundoApellido();
        } else if (medico.getIdentificacionDoctor() != null) {
            campo = "identificadorDoctor";
            valor = medico.getIdentificacionDoctor();
        } else if (medico.getEspecialidad() != null) {
            campo = "especialidad";
            valor = medico.getEspecialidad();
        } else if (medico.getAñosExperiencia() != null) {
            campo = "añosExperiencia";
            valor = medico.getAñosExperiencia();
        }

        if (campo == null || valor == null) {
            throw new IllegalArgumentException("No se encontró ningún campo válido para actualizar.");
        }

        String sql = "UPDATE medicos SET " + campo + " = ? WHERE identificacionDoctor = ?";

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
        String sql = "DELETE FROM medicos WHERE identificacionDoctor = ?";

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
