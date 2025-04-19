/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Comandos;

import Model.Consultorio;

/**
 *
 * @author JABER
 */
public interface ICrearMedico {
    void crearMedico(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
         String identificacionDoctor, String especialidad, double salario, int añosExperiencia, Consultorio consultorio) throws Exception;
}
