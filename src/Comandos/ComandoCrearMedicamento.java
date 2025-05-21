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
import Persistencia.Medicamento.VerificarPorNombre.VerificarMedicamentoPorNombre;
import javax.swing.JOptionPane;

/**
 *
 * @author JABER
 */
public class ComandoCrearMedicamento implements ICrearMedicamento {

    @Override
    public void CrearMedicamento(Medicamentos medicamento) throws Exception {
        MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();

        if (medicamento.getNombre() == null || medicamento.getNombre().trim().isEmpty()
                || medicamento.getPrecio() == null || medicamento.getPrecio() <= 0
                || medicamento.getPrecio().toString().trim().isEmpty() || medicamento.getDosisDiarias() == null
                || medicamento.getDosisDiarias() <= 0 || medicamento.getDosisDiarias().toString().trim().isEmpty()) {
            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }

        if (medicamento.getDosisDiarias() > 3) {
            throw new IllegalArgumentException("Ningun medicamento puede consumirse mas de 3 veces al dia");
        }
        
          if (VerificarMedicamentoPorNombre.VerificarMedicamentoNombre(medicamento.getNombre())) {
             throw new IllegalArgumentException( "Ya existe un medicamento con ese nombre.");
            
        }
        
        
        if (!metodo.ContieneSoloNumeros(medicamento.getPrecio().toString())
                || !metodo.ContieneSoloNumeros(medicamento.getDosisDiarias().toString())) {
            throw new IllegalArgumentException("Precio y Dosis Diarias deben ser solo números enteros positivos.");
        }

        try {
            MedicamentosDAO create = MedicamentosDAO.getInstancia();
            create.create(medicamento);
        } catch (SQLException e) {
            throw new Exception("Error al agregar consultorio: " + e.getMessage());
        }

    }
}
