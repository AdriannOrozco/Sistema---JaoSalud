/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Medico;
import Persistencia.Doctor.MedicoDAO;

/**
 *
 * @author JABER
 */
public class ComandoActualizarMedico implements IActualizarMedico{

    @Override
    public void ActualizarMedico(Medico medico) throws Exception {
        
         if (medico.getIdentificacionDoctor() == null || medico.getIdentificacionDoctor().trim().isEmpty()) {
        throw new IllegalArgumentException("El número de cédula es obligatorio.");
    }

    if (medico.getPrimerNombre() == null || medico.getPrimerNombre().trim().length() < 3) {
        throw new IllegalArgumentException("El nombre debe tener mínimo 3 caracteres.");
    }

    if (medico.getSegundoNombre() == null || medico.getSegundoNombre().trim().length() < 3) {
        throw new IllegalArgumentException("El segundo nombre debe tener mínimo 3 caracteres.");
    }

    if (medico.getPrimerApellido() == null || medico.getPrimerApellido().trim().length() < 3) {
        throw new IllegalArgumentException("El primer apellido debe tener mínimo 3 caracteres.");
    }

    if (medico.getSegundoApellido() == null || medico.getSegundoApellido().trim().length() < 3) {
        throw new IllegalArgumentException("El segundo apellido debe tener mínimo 3 caracteres.");
    }

    if (medico.getEspecialidad() == null || medico.getEspecialidad().trim().isEmpty()) {
        throw new IllegalArgumentException("La especialidad del médico es obligatoria.");
    }
        
        MedicoDAO.getInstancia().update(medico);
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
