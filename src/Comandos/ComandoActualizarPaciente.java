package Comandos;

import Model.Paciente;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Paciente.PacienteDAO;

public class ComandoActualizarPaciente implements IActualizarPaciente {

    @Override
    public void actualizarPaciente(Paciente paciente) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();

        if (paciente.getNumeroDocumento() == null || paciente.getNumeroDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento es obligatorio.");
        }

        if (paciente.getTipoDocumento() != null && paciente.getTipoDocumento().equals("Seleccionar")) {
            throw new IllegalArgumentException("Seleccione un tipo de documento válido.");
        }

        if (paciente.getEstadoCivil() != null && paciente.getEstadoCivil().equals("Seleccionar")) {
            throw new IllegalArgumentException("Seleccione un estado civil válido.");
        }

        if (paciente.getEPS() != null && paciente.getEPS().equals("Seleccionar")) {
            throw new IllegalArgumentException("Seleccione una EPS válida.");
        }

        if (paciente.getEmail() != null && (!metodo.EsEmailValido(paciente.getEmail()) || paciente.getEmail().length() < 7)) {
            throw new IllegalArgumentException("Correo inválido.");
        }

        if (paciente.getTelefono() != null && (!metodo.ContieneSoloNumeros(paciente.getTelefono()) || paciente.getTelefono().length() != 10)) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }

        if (paciente.getEdad() != null && !metodo.ContieneSoloNumeros(paciente.getEdad())) {
            throw new IllegalArgumentException("Edad inválida.");
        }

        if (paciente.getDireccionResidencia() != null && paciente.getDireccionResidencia().length() < 7) {
            throw new IllegalArgumentException("Dirección inválida.");
        }

        PacienteDAO.getInstancia().update(paciente);
       // PacienteDAO crearPaciente = new PacienteDAO();
        //crearPaciente.update(paciente);
    }
}