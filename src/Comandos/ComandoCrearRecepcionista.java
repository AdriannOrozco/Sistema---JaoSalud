/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Model.Recepcionista;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import Persistencia.Recepcionista.RecepcionistaDAO;
import Persistencia.Recepcionista.VerificarPorID.VerificarRecepcionistaID;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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

        if (recepcionista.getPrimerNombre().trim().length() < 3
                || recepcionista.getSegundoNombre().trim().length() < 3
                || recepcionista.getPrimerApellido().trim().length() < 3
                || recepcionista.getSegundoApellido().trim().length() < 3) {
            throw new IllegalArgumentException("Nombres o apellidos demasiado cortos.");
        }

        if (!metodo.EsNombreValido(recepcionista.getPrimerNombre())
                || !metodo.EsNombreValido(recepcionista.getSegundoNombre())
                || !metodo.EsNombreValido(recepcionista.getPrimerApellido())
                || !metodo.EsNombreValido(recepcionista.getSegundoApellido())) {

            throw new IllegalArgumentException("Nombres inválidos.");
        }

        if (!metodo.ContieneSoloNumeros(recepcionista.getId())) {
            throw new IllegalArgumentException("Documento inválido.");
        }

        if (recepcionista.getId().length() < 10 || recepcionista.getId().length() > 10) {
            throw new IllegalArgumentException("Longitud inválida del documento.");
        }

        if (VerificarRecepcionistaID.VerificarRecepcionistaPorId(recepcionista.getId())) {
            throw new IllegalArgumentException("Ya existe un recepcionista con ese número de documento.");

        }

        try {
            RecepcionistaDAO create = RecepcionistaDAO.getInstancia();
            create.create(recepcionista);
        } catch (SQLException e) {
            throw new Exception("Error al agregar consultorio: " + e.getMessage());
        }

    }

}
