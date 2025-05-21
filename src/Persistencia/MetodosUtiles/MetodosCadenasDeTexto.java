
package Persistencia.MetodosUtiles;
public class MetodosCadenasDeTexto {
    
      public static boolean ContieneNumeros(String str) {
        return str.matches(".*\\d.*");
    }

    public static boolean ContieneCaracteresPermitidos(String str) {
        return str.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s']+$");
    }

    public static boolean EsNombreValido(String str) {
        return !ContieneNumeros(str) && ContieneCaracteresPermitidos(str);

    }

    public static boolean ContieneSoloNumeros(String texto) {
        return texto.matches("^[0-9]+$");
    }

    public static boolean EsEmailValido(String email) {
        return email.matches("^[^@]+@[^@]+\\.[^@]+$");
    }
}
