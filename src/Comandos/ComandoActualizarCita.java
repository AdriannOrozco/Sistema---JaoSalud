package Comandos;

import Model.Cita;
import Persistencia.Citas.CitaDAO;
import Persistencia.Citas.VerificarFechaYHora.VerificarFechaYHora;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;

public class ComandoActualizarCita implements IActualizarCita {

    @Override
    public void actualizarCita(Cita cita) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();
        VerificarFechaYHora verificar = new VerificarFechaYHora();
        CitaDAO.getInstancia().update(cita);
    }
}
