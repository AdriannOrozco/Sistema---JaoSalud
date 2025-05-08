/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Consultorio;
import Model.Medico;
import Persistencia.Database.ConexionBD;
import Persistencia.Doctor.MedicoDAO;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Paciente.PacienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class ComandoCrearMedico implements ICrearMedico {

    public void crearMedico(Medico medico) throws Exception {

        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();

        // Validación de campos obligatorios
        if (medico.getPrimerNombre() == null || medico.getPrimerNombre().trim().isEmpty()
                || medico.getPrimerApellido() == null || medico.getPrimerApellido().trim().isEmpty()
                || medico.getSegundoApellido() == null || medico.getSegundoApellido().trim().isEmpty()
                || medico.getIdentificacionDoctor() == null || medico.getIdentificacionDoctor().trim().isEmpty()
                || medico.getEspecialidad() == null || medico.getEspecialidad().trim().isEmpty()
                || medico.getAñosExperiencia() == null || medico.getAñosExperiencia().trim().isEmpty()) {

            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (medico.getPrimerNombre().length() < 3
                || medico.getPrimerApellido().length() < 3
                || medico.getSegundoApellido().length() < 3) {
            throw new IllegalArgumentException("Nombres o apellidos demasiado cortos.");
        }

        if (!metodo.EsNombreValido(medico.getPrimerNombre())
                || !metodo.EsNombreValido(medico.getSegundoNombre())
                || !metodo.EsNombreValido(medico.getPrimerApellido())
                || !metodo.EsNombreValido(medico.getSegundoApellido())) {
            throw new IllegalArgumentException("Nombres o apellidos inválidos.");
        }

        if (!metodo.ContieneSoloNumeros(medico.getIdentificacionDoctor())
                || medico.getIdentificacionDoctor().length() < 7
                || medico.getIdentificacionDoctor().length() > 13) {
            throw new IllegalArgumentException("Identificación del doctor inválida.");
        }

        if (!metodo.ContieneSoloNumeros(medico.getAñosExperiencia())) {
            throw new IllegalArgumentException("Años de experiencia inválidos.");
        }

        try {

            MedicoDAO create = MedicoDAO.getInstancia();
            create.create(medico);
            JOptionPane.showMessageDialog(null, "El Medico se agregó con éxito.",
                    "Proceso completado", JOptionPane.INFORMATION_MESSAGE);

            JOptionPane.showInternalMessageDialog(null, "El médico se agregó con éxito.", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Error al agregar médico: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void crearMedico(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String identificacionDoctor, String especialidad, String añosExperiencia, Consultorio consultorio) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
