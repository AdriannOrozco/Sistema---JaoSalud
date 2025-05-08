/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;
import Model.Consultorio;
import Persistencia.Consultorios.ConsultorioDAO;
import Persistencia.Database.ConexionBD;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Paciente.PacienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class ComandoCrearConsultorio implements ICrearConsultorio {
   public void crearConsultorio(Consultorio consultorio) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();

        // Validaciones usando getters
        if (consultorio.getIdConsultorio() == null || consultorio.getIdConsultorio().trim().isEmpty()
                || consultorio.getEspecialidad() == null || consultorio.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (!metodo.ContieneSoloNumeros(consultorio.getIdConsultorio())) {
            throw new IllegalArgumentException("El ID del consultorio debe contener solo números.");
        }

        if (consultorio.getIdConsultorio().length() < 3 || consultorio.getIdConsultorio().length() > 10) {
            throw new IllegalArgumentException("Longitud inválida del ID del consultorio.");
        }

        if (!metodo.EsNombreValido(consultorio.getEspecialidad()) || consultorio.getEspecialidad().length() < 3) {
            throw new IllegalArgumentException("Especialidad inválida.");
        }

       try {
            ConsultorioDAO create = ConsultorioDAO.getInstancia();
            create.create(consultorio);
            JOptionPane.showMessageDialog(null, "El paciente se agregó con éxito.",
                    "Proceso completado", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            throw new Exception("Error al agregar paciente: " + e.getMessage());
        }
    }

    @Override
    public void CrearConsultorio(String idConsultorio, String especialidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
