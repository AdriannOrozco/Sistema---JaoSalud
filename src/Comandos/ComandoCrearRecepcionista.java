/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Recepcionista;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Recepcionista.RecepcionistaDAO;
import java.sql.SQLException;

/**
 *
 * @author JABER
 */
public class ComandoCrearRecepcionista implements ICrearRecepcionista {

    @Override
    public void crearRecepcionista(Recepcionista recepcionista) throws Exception {
         MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();
        
          if (recepcionista.getPrimerNombre() == null || recepcionista.getPrimerNombre().trim().isEmpty()
                || recepcionista.getSegundoNombre() == null || recepcionista.getSegundoNombre().trim().isEmpty()
                || recepcionista.getPrimerApellido() == null || recepcionista.getPrimerApellido().trim().isEmpty()
                || recepcionista.getSegundoApellido() == null || recepcionista.getSegundoApellido().trim().isEmpty()
                || recepcionista.getId() == null || recepcionista.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Campos obligatorios vacíos.");
        }
        try {
            RecepcionistaDAO create = RecepcionistaDAO.getInstancia();
            create.create(recepcionista);
        } catch (SQLException e) {
            throw new Exception("Error al agregar consultorio: " + e.getMessage());
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
