package Comandos;
import Model.Paciente;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Paciente.PacienteDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Persistencia.Paciente.VerificarPacienteEmail.VerificarPacienteEmail;
import Persistencia.Paciente.VerificarPacienteID.VerificarPacienteID;

public class ComandoCrearPaciente implements ICrearPaciente {

    @Override
    public void CrearPaciente(Paciente paciente) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();
        

        if (paciente.getPrimerNombre() == null || paciente.getPrimerNombre().trim().isEmpty()
                || paciente.getPrimerApellido() == null || paciente.getPrimerApellido().trim().isEmpty()
                || paciente.getSegundoApellido() == null || paciente.getSegundoApellido().trim().isEmpty()
                || paciente.getTipoDocumento() == null || paciente.getTipoDocumento().trim().isEmpty()
                || paciente.getNumeroDocumento() == null || paciente.getNumeroDocumento().trim().isEmpty()
                || paciente.getTelefono() == null || paciente.getTelefono().trim().isEmpty()
                || paciente.getDireccionResidencia() == null || paciente.getDireccionResidencia().trim().isEmpty()
                || paciente.getEstadoCivil() == null || paciente.getEstadoCivil().trim().isEmpty()
                || paciente.getGenero() == null || paciente.getGenero().trim().isEmpty()
                || paciente.getEPS() == null || paciente.getEPS().trim().isEmpty()
                || paciente.getTipoSangre() == null || paciente.getTipoSangre().trim().isEmpty()
                || paciente.getEdad() == null || paciente.getEdad().trim().isEmpty()) {

            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (paciente.getPrimerNombre().length() < 3
                || paciente.getPrimerApellido().length() < 3
                || paciente.getSegundoApellido().length() < 3) {

            throw new IllegalArgumentException("Nombres o apellidos demasiado cortos.");
        }

        if (!metodo.EsNombreValido(paciente.getPrimerNombre())
                || !metodo.EsNombreValido(paciente.getSegundoNombre())
                || !metodo.EsNombreValido(paciente.getPrimerApellido())
                || !metodo.EsNombreValido(paciente.getSegundoApellido())) {

            throw new IllegalArgumentException("Nombres inválidos.");
        }

        if (!metodo.ContieneSoloNumeros(paciente.getNumeroDocumento())) {
            throw new IllegalArgumentException("Documento inválido.");
        }

        if (paciente.getNumeroDocumento().length() < 7 || paciente.getNumeroDocumento().length() > 13) {
            throw new IllegalArgumentException("Longitud inválida del documento.");
        }
        
       
        if (!metodo.ContieneSoloNumeros(paciente.getTelefono()) || paciente.getTelefono().length() < 10) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }

        if (!metodo.EsNombreValido(paciente.getGenero())) {
            throw new IllegalArgumentException("Estado civil o género inválido.");
        }

        if (!metodo.EsEmailValido(paciente.getEmail()) || paciente.getEmail().length() < 7) {
            throw new IllegalArgumentException("Correo inválido.");
        }

        if (paciente.getDireccionResidencia().length() < 7) {
            throw new IllegalArgumentException("Dirección inválida.");
        }

        if (paciente.getEPS().length() < 6) {
            throw new IllegalArgumentException("EPS inválida.");
        }

        if (paciente.getTipoSangre().length() > 5 || metodo.ContieneNumeros(paciente.getTipoSangre())) {
            throw new IllegalArgumentException("Tipo de sangre inválido.");
        }

        if (!metodo.ContieneSoloNumeros(paciente.getEdad())) {
            throw new IllegalArgumentException("Edad inválida.");
        }

        if (VerificarPacienteID.VerificarPacientePorId(paciente.getNumeroDocumento())) {
            JOptionPane.showMessageDialog(null, "Ya existe un paciente con ese número de documento.", "Número de documento existente.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (VerificarPacienteEmail.VerificarPacientePorEmail(paciente.getEmail())) {
            JOptionPane.showMessageDialog(null, "Ya existe un paciente con ese email.", "Email existente.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            PacienteDAO create = PacienteDAO.getInstancia();
            create.create(paciente);
            JOptionPane.showMessageDialog(null, "El paciente se agregó con éxito.",
                    "Proceso completado", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            throw new Exception("Error al agregar paciente: " + e.getMessage());
        }
    }
}