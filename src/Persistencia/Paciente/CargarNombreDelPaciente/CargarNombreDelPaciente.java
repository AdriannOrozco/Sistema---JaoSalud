package Persistencia.Paciente.CargarNombreDelPaciente;

import static Comandos.metodos.MetodosUtiles.ContieneSoloNumeros;
import Persistencia.Paciente.BuscarPacientePorID.BuscarPacientePorId;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CargarNombreDelPaciente {

    public void CargarNombrePaciente(String numeroDocumento, JTextField campoNombre) {

        try {
            if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
                throw new IllegalArgumentException("El número de documento no puede estar vacío.");
            }

            if (!ContieneSoloNumeros(numeroDocumento)) {
                throw new IllegalArgumentException("El número de documento solo debe contener números.");
            }
            
            BuscarPacientePorId buscador = new BuscarPacientePorId();
            String nombre = buscador.BuscarPacientePorDocumento(numeroDocumento);

            if (nombre != null) {
                campoNombre.setText(nombre);
            } else {
                campoNombre.setText("");
                throw new IllegalArgumentException("No existe un paciente con ese documento.");

            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            campoNombre.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el nombre del paciente: " + ex.getMessage());
            campoNombre.setText("");
        }
    }
}
