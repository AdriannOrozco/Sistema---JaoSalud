/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Email.ConfiguracionEmail;

/**
 *
 * @author Osvaldo
 */
public class EmailConfig {

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;
    private static final String USERNAME = "correojaosalud@gmail.com";
    private static final String PASSWORD = "JaoSaludColombo25"; 

    public static String getHost() {
        return HOST;
    }

    public static int getPort() {
        return PORT;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
