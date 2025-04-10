package Model;

import java.util.Date;
import javax.swing.JOptionPane;

public class Paciente extends Usuarios {

    //Campo Nombres
    private String primerNombre; //Validado
    private String segundoNombre; //Validado
    private String primerApellido; //Validado
    private String segundoApellido; //Validado

    //Campo de documentos
    private String tipoDocumento; //(Modificable) //Validado
    private String numeroDocumento; //Validado

    //Campo información personal
    private String telefono; //(Modificable) Validado
    private String direccionResidencia; //(Modificable) 
    private String estadoCivil; //(Modificable) Validado
    private String genero; // Validado
    private String email; //(Modificable) //Validado

    private String EPS; //(Modificable) //Validado
    private String tipoSangre; //Validado
    private Date fechaNacimiento;
    private Date fechaRegistro;

    private int edad; //Solicitado (Modificable) //Validado

    public Paciente(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public Paciente(String usuario, String contraseña, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
            String tipoDocumento, String numeroDocumento, String telefono, String direccionResidencia, String estadoCivil,
            String genero, String email, String EPS, String tipoSangre, Date fechaNacimiento, Date fechaRegistro, int edad) throws Exception {

        super(usuario, contraseña);

        //Validar que campo nombres no este vacvío.
        if (primerNombre.trim().isEmpty() || primerApellido.trim().isEmpty() || segundoApellido.trim().isEmpty() || primerNombre == null || primerApellido == null || segundoApellido == null) {

            JOptionPane.showMessageDialog(null, "Hay campos vacíos.", "Campos de nombres.", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException("Validación de campos obligatorios");

        } else {
            //Validar que campo nombres no sea menor a 3 carácteres.

            if (primerNombre.length() < 3 || segundoNombre.length() < 3 || primerApellido.length() < 3 || primerApellido.length() < 3) {

                JOptionPane.showMessageDialog(null, "Hay datos demasiados cortos.", "Campos de nombres.", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException("Validación de campos obligatorios");

            } else {

                //Validar que campo nombres no contenga números ni caracteres especiales
                if (EsNombreValido(primerNombre) && EsNombreValido(segundoNombre) && EsNombreValido(primerApellido) && EsNombreValido(segundoApellido)) {

                    //Validamos campos de documentos no sea vacío
                    if (tipoDocumento.trim().isEmpty() || numeroDocumento.trim().isEmpty() || tipoDocumento == null || numeroDocumento == null) {

                        JOptionPane.showMessageDialog(null, "En el campo de identificación hay un dato restante.", "Campo de identificación.", JOptionPane.WARNING_MESSAGE);
                        throw new RuntimeException("Validación de campos obligatorios");
                    } else {

                        //Validar que campo número de documento contenga sólo números
                        if (ContieneSoloNumeros(numeroDocumento)) {

                            //Validar que campo número de documento no sea corto ni largo
                            if (numeroDocumento.length() < 7 || numeroDocumento.length() > 9) {

                                JOptionPane.showMessageDialog(null, "La longitud del número de documento es inválido.", "Campo de identificación.", JOptionPane.WARNING_MESSAGE);
                                throw new RuntimeException("Validación de campos obligatorios");

                            } else {

                                //Validamos campo información personal no esté vacía
                                if (telefono.trim().isEmpty() || direccionResidencia.trim().isEmpty() || estadoCivil.trim().isEmpty() || genero.trim().isEmpty() || email.trim().isEmpty()) {

                                    JOptionPane.showMessageDialog(null, "Hay campos vacíos.", "Campos de datos personales.", JOptionPane.WARNING_MESSAGE);
                                    throw new RuntimeException("Validación de campos obligatorios");

                                } else {

                                    //Validamos que solo el campo información personal sean cadena sin números
                                    if (EsNombreValido(estadoCivil) && EsNombreValido(genero)) {

                                        //Validamos el campo del teléfono
                                        if (!ContieneSoloNumeros(telefono) || telefono.length() < 10) {

                                            JOptionPane.showMessageDialog(null, "El teléfono es inválido, revisa de nuevo.", "Campo de información personal.", JOptionPane.WARNING_MESSAGE);
                                            throw new RuntimeException("Validación de campos obligatorios");

                                        } else {

                                            //Validamos el correo
                                            if (EsEmailValido(email)) {

                                                //Validamos la longitud del correo
                                                if (email.length() < 7) {
                                                    JOptionPane.showMessageDialog(null, "La dirección de correo electrónico es muy corta.", "Email inválido.", JOptionPane.WARNING_MESSAGE);
                                                    throw new RuntimeException("Validación de campos obligatorios");
                                                } else {
                                                    //Validamos la dirección de residencia
                                                    if (direccionResidencia.length() < 7) {
                                                        JOptionPane.showMessageDialog(null, "La dirección de residencia es muy corta.", "Dirección de residencia inválida.", JOptionPane.WARNING_MESSAGE);
                                                        throw new RuntimeException("Validación de campos obligatorios");
                                                    } else {

                                                        if (EPS.trim().isEmpty() || tipoSangre.trim().isEmpty() || edad == 0) {
                                                            JOptionPane.showMessageDialog(null, "Aún hay datos vacíos.", "Campo de EPS | Tipo de Sangre | Edad.", JOptionPane.WARNING_MESSAGE);
                                                            throw new RuntimeException("Validación de campos obligatorios");
                                                        } else {

                                                            if (edad <= 1 || edad > 120) {
                                                                JOptionPane.showMessageDialog(null, "La edad es inválida.", "Campo de EPS | Tipo de Sangre | Edad.", JOptionPane.WARNING_MESSAGE);
                                                                throw new RuntimeException("Validación de edad.");
                                                            } else {

                                                                //Validamos que la EPS no sea corto
                                                                if (EPS.length() < 6) {
                                                                    JOptionPane.showMessageDialog(null, "El nombre de la EPS es muy corto.", "Campo de EPS.", JOptionPane.WARNING_MESSAGE);
                                                                    throw new RuntimeException("Validación de EPS.");
                                                                } else {

                                                                    //Validamos que el tipo de sangre no sea inválido
                                                                    if (tipoSangre.length() > 5 || ContieneNumeros(tipoSangre)) {
                                                                        JOptionPane.showMessageDialog(null, "El tipo de sangre es inválido..", "Campo de EPS.", JOptionPane.WARNING_MESSAGE);
                                                                        throw new RuntimeException("Validación de tipo de sangre.");
                                                                    } else {
                                                                        
                                                                    }

                                                                }

                                                            }

                                                        }

                                                    }

                                                }

                                            } else {

                                                JOptionPane.showMessageDialog(null, "El correo ingresado es inválido.", "Campo de información personal.", JOptionPane.WARNING_MESSAGE);
                                                throw new RuntimeException("Validación de campos obligatorios");
                                            }

                                        }

                                    } else {

                                        JOptionPane.showMessageDialog(null, "El campo de información personal tiene números o caractéres especiales.", "Campo de información personal.", JOptionPane.WARNING_MESSAGE);
                                        throw new RuntimeException("Validación de campos obligatorios");
                                    }

                                }

                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "El número de documento sólo debe contener números.", "Campo de identificación.", JOptionPane.WARNING_MESSAGE);
                            throw new RuntimeException("Validación de campos obligatorios");
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El campo de nombres contiene números o caractáres especiales..", "Campos de nombres.", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Validación de campos obligatorios");
                }

            }

        }

        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.direccionResidencia = direccionResidencia;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.email = email;
        this.EPS = EPS;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.edad = edad;

    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEPS() {
        return EPS;
    }

    public void setEPS(String EPS) {
        this.EPS = EPS;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //Otros métodos relacionados.
    public static boolean ContieneNumeros(String str) {
        // Verifica que no haya dígitos en el string
        return str.matches(".*\\d.*");
    }

    public static boolean ContieneCaracteresPermitidos(String str) {
        // Permite letras (incluyendo acentos y ñ), espacios y apóstrofes comunes en nombres
        return str.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s']+$");
    }

    public static boolean EsNombreValido(String str) {
        return !ContieneNumeros(str) && ContieneCaracteresPermitidos(str);

    }

    public static boolean ContieneSoloNumeros(String texto) {
        return texto.matches("^[0-9]+$"); // Verifica que todos los caracteres sean dígitos
    }

    public static boolean EsEmailValido(String email) {
        return email.matches("^[^@]+@[^@]+\\.[^@]+$");
    }
}
