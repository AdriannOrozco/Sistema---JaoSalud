/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Medicamentos;
import Persistencia.Medicamentos.MedicamentosDAO;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Persistencia.Database.ConexionBD;

/**
 *
 * @author JABER
 */
public class ComandoCrearMedicamento implements ICrearMedicamento {

    public void CrearMedicamento(Medicamentos medicamento) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();

        if (medicamento.getNombre() == null || medicamento.getNombre().trim().isEmpty()
                || medicamento.getPrecio() == null || medicamento.getPrecio() <= 0
                || medicamento.getPrecio().toString().trim().isEmpty() || medicamento.getDosisDiarias() == null
                || medicamento.getDosisDiarias() <= 0 || medicamento.getDosisDiarias().toString().trim().isEmpty()
                ) {
            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }
        
        
        try {
            MedicamentosDAO create = MedicamentosDAO.getInstancia();
            create.create(medicamento);
        } catch (SQLException e) {
            throw new Exception("Error al agregar consultorio: " + e.getMessage());
        }
        
    }
}
