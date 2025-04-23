package Comandos;
import Model.Cita;
import Persistencia.Citas.CitaDAO;
import Persistencia.Consultorios.VerificarDisponibilidad.VerificarDisponibilidad;
import Persistencia.Doctor.VerificarDisponibilidad.VerificarDisponibilidadDoc;
import Persistencia.Paciente.BuscarPacientePorID.BuscarPacientePorId;
import Persistencia.Paciente.PreguntarExisteDocumento.PreguntarExisteDocumento;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComandoAgendarCita implements IAgendarCita {

    @Override
    public void AgendarCita(Cita cita) throws Exception {

        BuscarPacientePorId buscar = new BuscarPacientePorId();
        PreguntarExisteDocumento existeDocumento = new PreguntarExisteDocumento();
        VerificarDisponibilidadDoc vdDoctor = new VerificarDisponibilidadDoc();
        VerificarDisponibilidad vdConsultorio = new VerificarDisponibilidad();

        if (cita.getIdentificacion() == null
                || cita.getMotivo() == null
                || cita.getMotivo().trim().isEmpty()
                || cita.getFechaCita() == null
                || cita.getIdCita() == 0
                || cita.getFechaRegistro() == null
                || cita.getNombrePaciente() == null
                || cita.getNumeroDocumento() == null) {

            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (cita.getMotivo().length() < 10) {
            throw new IllegalArgumentException("El motivo digitado es inválido.");
        }

        if (cita.getHora().equals("Seleccionar")) {
            throw new IllegalArgumentException("Seleccione una hora.");
        }

        if (!existeDocumento.ExisteNumeroDocumento(cita.getNumeroDocumento())) {
            throw new IllegalArgumentException("No existe un número de documento registrado.");
        }

        if (buscar.BuscarPacientePorDocumento(cita.getNumeroDocumento()) == null) {
            throw new IllegalArgumentException("No existe un paciente con ese número de documento.");
        }

        if (vdDoctor.VerificarDisponibilidadDoctor(cita.getIdentificacion(), cita.getFechaCita(), cita.getHora())) {
            throw new IllegalArgumentException("El doctor ya está reservado.");
        }

        if (vdConsultorio.VerificarDisponibilidadConsultorio(cita.getIdConsultorio(), cita.getFechaCita(), cita.getHora())) {
            throw new IllegalArgumentException("Consultorio ocupado.");
        }

        String nombrePaciente = buscar.BuscarPacientePorDocumento(cita.getNumeroDocumento());
        cita.setNombrePaciente(nombrePaciente);

        try {
            CitaDAO create = CitaDAO.getInstancia();
            create.create(cita);
            JOptionPane.showMessageDialog(null, "La cita se agendo con éxito.",
                    "Proceso completado", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            throw new Exception("Error al agendar cita paciente: " + e.getMessage());
        }

    }
}
