package Comandos;

import Model.Medico;
import Persistencia.Doctor.MedicoDAO;
import Persistencia.Doctor.ValidarCedulaDoctor.VerificarDoctorId;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoCrearMedico implements ICrearMedico {

    @Override
    public void crearMedico(Medico medico) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();

        // Validación de campos obligatorios
        if (medico.getPrimerNombre() == null || medico.getPrimerNombre().trim().isEmpty()
                || medico.getSegundoNombre() == null || medico.getSegundoNombre().isEmpty()
                || medico.getPrimerApellido() == null || medico.getPrimerApellido().trim().isEmpty()
                || medico.getSegundoApellido() == null || medico.getSegundoApellido().trim().isEmpty()
                || medico.getIdentificacionDoctor() == null || medico.getIdentificacionDoctor().trim().isEmpty()
                || medico.getEspecialidad() == null || medico.getEspecialidad().trim().isEmpty()
                || medico.getAñosExperiencia() == null || medico.getAñosExperiencia().trim().isEmpty()) {

            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (medico.getPrimerNombre().length() < 3
                || medico.getSegundoNombre().length() < 3
                || medico.getPrimerApellido().length() < 3
                || medico.getSegundoApellido().length() < 3) {
            throw new IllegalArgumentException("Nombres o apellidos demasiado cortos.");
        }

        if (!metodo.EsNombreValido(medico.getPrimerNombre())
                || (medico.getSegundoNombre() != null && !medico.getSegundoNombre().isEmpty() && !metodo.EsNombreValido(medico.getSegundoNombre()))
                || !metodo.EsNombreValido(medico.getPrimerApellido())
                || !metodo.EsNombreValido(medico.getSegundoApellido())) {
            throw new IllegalArgumentException("Nombres o apellidos inválidos.");
        }

        if (!metodo.ContieneSoloNumeros(medico.getIdentificacionDoctor())
                || medico.getIdentificacionDoctor().length() < 7
                || medico.getIdentificacionDoctor().length() > 10) {
            throw new IllegalArgumentException("Identificación del doctor inválida.");
        }

        if (VerificarDoctorId.verificarDoctorPorId(medico.getIdentificacionDoctor())) {
            JOptionPane.showMessageDialog(null, "Ya existe un doctor con ese número de documento.", "Número de documento existente.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!metodo.ContieneSoloNumeros(medico.getAñosExperiencia())) {
            throw new IllegalArgumentException("Años de experiencia inválidos.");
        }

        try {
            MedicoDAO create = MedicoDAO.getInstancia();
            create.create(medico);
            JOptionPane.showMessageDialog(null, "El médico se agregó con éxito.",
                    "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new Exception("Error al agregar medico: " + e.getMessage());
        }
    }

}
