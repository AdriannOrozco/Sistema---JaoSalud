package Comandos;

import Persistencia.Citas.CitaDAO;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import javax.swing.JOptionPane;

public class ComandoCancelarCita implements ICancelarCita {

    @Override
    public void CancelarCita(String idCita) throws Exception {
         MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();
        // Validaciones
        if (idCita == null) {
            JOptionPane.showMessageDialog(null, "El ID de la cita no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!metodo.ContieneSoloNumeros(idCita)) {
            throw new IllegalArgumentException("Número de cita inválido.");
        }
        
        int idCitaInt;
        idCitaInt = Integer.parseInt(idCita.trim());
        
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la cita? Esta quedará eliminada del sistema.", "Seleccionar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso.", "Proceso cancelado", JOptionPane.OK_OPTION);
            return;
        }

        if (confirmacion == JOptionPane.YES_OPTION) {

            try {
                CitaDAO eliminar = new CitaDAO();

                boolean cancelado = eliminar.delete(idCitaInt);

                if (cancelado) {
                    JOptionPane.showMessageDialog(null,
                            "La cita se canceló con éxito.",
                            "Proceso completado",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se encontró una cita con número " + idCita,
                            "No encontrado",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                throw new Exception("Error al eliminar la cita: " + e.getMessage());
            }

        }

    }
}
