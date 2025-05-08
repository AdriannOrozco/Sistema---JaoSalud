
package Persistencia.MetodosUtiles;
public class MetodosCadenasDeTexto {
    
      public  boolean ContieneNumeros(String str) {
        return str.matches(".*\\d.*");
    }

    public  boolean ContieneCaracteresPermitidos(String str) {
        return str.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s']+$");
    }

    public  boolean EsNombreValido(String str) {
        return !ContieneNumeros(str) && ContieneCaracteresPermitidos(str);

    }

    public  boolean ContieneSoloNumeros(String texto) {
        return texto.matches("^[0-9]+$");
    }

    public boolean EsEmailValido(String email) {
        return email.matches("^[^@]+@[^@]+\\.[^@]+$");
    }
}
