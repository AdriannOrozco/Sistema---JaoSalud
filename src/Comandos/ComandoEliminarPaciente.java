package Comandos;
import Persistencia.Paciente.PacienteDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;

public class ComandoEliminarPaciente implements IEliminarPaciente {

    @Override
    public void EliminarPaciente(String tipoDocumento, String numeroDocumento) throws Exception {
        
        if (tipoDocumento == "--" || tipoDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacío");
        }
        
        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío");
        }
        
         if (!MetodosCadenasDeTexto.ContieneSoloNumeros(numeroDocumento)) {
            throw new IllegalArgumentException("El número de documento sólo contiene números..");
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro que desea eliminar al paciente con documento " + numeroDocumento + "?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, 
                    "Se ha cancelado el proceso.", 
                    "Proceso cancelado", 
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
               
                PacienteDAO eliminar = PacienteDAO.getInstancia();
                boolean eliminado = eliminar.delete(tipoDocumento, numeroDocumento);

                if (eliminado) {
                    JOptionPane.showMessageDialog(null, 
                            "El paciente se eliminó con éxito.", 
                            "Proceso completado", 
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "No se encontró un paciente con documento " + numeroDocumento, 
                            "No encontrado", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                throw new Exception("Error al eliminar el paciente: " + e.getMessage());
            }
        }
    }
}