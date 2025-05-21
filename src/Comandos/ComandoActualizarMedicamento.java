/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Medicamentos;
import Persistencia.Medicamentos.MedicamentosDAO;

/**
 *
 * @author JABER
 */
public class ComandoActualizarMedicamento implements IActualizarMedicamento{

    @Override
    public void ActualizarMedicamento(Medicamentos medicamento) throws Exception {
     
        if(medicamento.getNombre() == null || medicamento.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del consultorio es obligatorio.");
        }
        
        if(medicamento.getNombre().trim().length() < 3){
            throw new IllegalArgumentException("El nombre del consultorio debe tener minimo 3 letras");
        }
        
        if(medicamento.getPrecio() == null || medicamento.getPrecio() < 0){
              throw new IllegalArgumentException("El precio del producto debe ser mayor a 0");
        }
          MedicamentosDAO.getInstancia().update(medicamento);
          
    }
    
}
