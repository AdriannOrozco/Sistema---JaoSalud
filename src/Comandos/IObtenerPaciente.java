package Comandos;
import Model.Paciente;
import java.util.List;

public interface IObtenerPaciente {
    List<Paciente> getPaciente() throws Exception;
}
