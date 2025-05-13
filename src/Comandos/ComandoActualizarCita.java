package Comandos;

import Model.Cita;
import Persistencia.Citas.CitaDAO;
import Persistencia.Citas.VerificarFechaYHora.VerificarFechaYHora;

public class ComandoActualizarCita implements IActualizarCita {

    @Override
    public void actualizarCita(Cita cita) throws Exception {
        VerificarFechaYHora verificar = new VerificarFechaYHora();

        if (cita.getMotivo() != null) {
            if (cita.getMotivo().isEmpty()) {
                throw new IllegalArgumentException("El motivo no puede estar vacío.");
            }
            if (cita.getMotivo().length() < 15) {
                throw new IllegalArgumentException("El motivo está demasiado corto.");
            }
        }

        if (cita.getHora() != null) {
            if (verificar.VerificarDisponibilidad(cita.getFechaCita(), cita.getHora())) {
                throw new IllegalArgumentException("No se puede duplicar la cita.");
            }
        }

        CitaDAO.getInstancia().update(cita);
    }
}
