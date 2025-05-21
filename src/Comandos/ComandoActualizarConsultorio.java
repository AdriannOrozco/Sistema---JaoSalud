/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Consultorio;
import Persistencia.Consultorios.ConsultorioDAO;

/**
 *
 * @author JABER
 */
public class ComandoActualizarConsultorio implements IActualizarConsultorio {
   

    @Override
    public void ActualizarConsultorio(Consultorio consultorio) throws Exception {

        // Validaciones usando getters
        if (consultorio.getConsultorio() == null || consultorio.getConsultorio().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del consultorio es obligatorio.");
        }

        if (consultorio.getEspecialidad() == null || consultorio.getEspecialidad().trim().length() < 3) {
            throw new IllegalArgumentException("La especialidad del consultorio debe tener al menos 3 caracteres.");
        }

        // Llamada al DAO
        ConsultorioDAO.getInstancia().update(consultorio);
    }
}
