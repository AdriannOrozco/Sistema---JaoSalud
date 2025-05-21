/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Consultorio;
import Persistencia.Consultorios.ConsultorioDAO;
import Persistencia.Consultorios.ValidarConsultorio.ValidarConsultorio;
import Persistencia.Database.ConexionBD;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class ComandoCrearConsultorio implements ICrearConsultorio {

    @Override
    public void CrearConsultorio(Consultorio consultorio) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();

        // Validaciones usando getters
        if (consultorio.getConsultorio() == null || consultorio.getConsultorio().trim().isEmpty()
                || consultorio.getEspecialidad() == null || consultorio.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (ValidarConsultorio.VerificarPoConsultorio(consultorio.getConsultorio())) {
            JOptionPane.showMessageDialog(null, "Ya existe un consultorio con ese nombre", "Error de registro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            ConsultorioDAO create = ConsultorioDAO.getInstancia();
            create.create(consultorio);
            JOptionPane.showMessageDialog(null, "El consultorio se ha registrado exitosamente", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new Exception("Error al agregar consultorio: " + e.getMessage());
        }
    }

}
