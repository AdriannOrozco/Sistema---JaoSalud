package Comandos.metodos;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase representa métodos útiles generales-específicos.
 *
 * @author Adrian
 */
public class MetodosUtiles {

    //Métodos de uso universal.
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

    //Métodos de uso específico - CreatePatientCommand
    public static boolean verificarPacienteID(String numeroDocumento) {
        boolean existe = false;
        Connection con = ConexionBD.conectar();
        PreparedStatement ps;
        String sql = "SELECT COUNT(*) FROM pacientes WHERE numeroDocumento = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, numeroDocumento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                existe = count > 0;
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error para crear un paciente " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error of connection " + e.getMessage());
            }
        }
        return existe;
    }

    public static boolean VerificarPacienteEmail(String email) {
        boolean existe = false;
        Connection con = ConexionBD.conectar();
        PreparedStatement ps;
        String sql = "SELECT COUNT(*) FROM pacientes WHERE email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                existe = count > 0;
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error para crear un paciente " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error of connection " + e.getMessage());
            }
        }
        return existe;
    }

    public void cargarDatosPacientes(String numeroDocumento, String tipoDocumento, JTextField txtCargarEPS, JTextField txtCargarPrimerNombre, JTextField txtCargarPrimerApellido) throws Exception {
        ConexionBD con = new ConexionBD();
        String sql = "SELECT EPS, primerNombre, primerApellido FROM pacientes WHERE numeroDocumento = ? AND tipoDocumento = ?";

        try (Connection conexion = con.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            pstmt.setString(2, tipoDocumento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtCargarEPS.setText(rs.getString("EPS"));
                txtCargarPrimerNombre.setText(rs.getString("primerNombre"));
                txtCargarPrimerApellido.setText(rs.getString("primerApellido"));

            } else {
                JOptionPane.showMessageDialog(null, "El paciente con número de documento " + numeroDocumento + " no se encuentra en JaoSalud.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener información del paciente: " + e.getMessage());
        }
    }

    public boolean verificarPaciente(String numeroDocumento, String tipoDocumento, String columnaSeleccionada1, String columnaSeleccionada2) {
        String query = "SELECT 1 FROM pacientes WHERE " + columnaSeleccionada1 + " = ? AND " + columnaSeleccionada2 + " = ?";

        try (Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setString(1, numeroDocumento);
            ps.setString(2, tipoDocumento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    System.out.println(numeroDocumento + " no está registrado." + "Error de búsqueda" + JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            return false;
        }
    }

    public void CargarConsultorios(JComboBox<String> cboConsultorio, String value) {
        String sql = "SELECT * FROM consultorios";
        ConexionBD con = new ConexionBD();
        try (Connection connection = con.conectar(); PreparedStatement pst = connection.prepareStatement(sql)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    // Agregamos los datos al JComboBox
                    cboConsultorio.addItem(rs.getString(value));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    public void CargarDoctores(JComboBox<String> cboDoctor, String value, String value2) {
        String sql = "SELECT * FROM doctores";

        ConexionBD con = new ConexionBD();
        try (Connection connection = con.conectar(); PreparedStatement pst = connection.prepareStatement(sql)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    // Agregamos los datos al JComboBox
                    cboDoctor.addItem(rs.getString(value) + " " + rs.getString(value2));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    public void MostrarMedicos(JTable tablaDoctores) {

        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT * FROM medicos";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Identificacion");
            model.addColumn("Primer nombre");
            model.addColumn("Primer apellido");
            model.addColumn("Especialidad");
            model.addColumn("Años de experiencia");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("identificacion");
                row[1] = rs.getString("primerNombre");
                row[2] = rs.getString("primerApellido");
                row[3] = rs.getString("especialidad");
                row[4] = rs.getString("añosExperiencia");
                model.addRow(row);

            }
            tablaDoctores.setModel(model);
            tablaDoctores.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar doctores: " + e.getMessage());
        }

    }

    public static boolean ExisteNumeroDocumento(String numeroDocumento) throws Exception {
        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!ContieneSoloNumeros(numeroDocumento)) {
            throw new IllegalArgumentException("El número de documento solo debe contener números.");
        }

        String sql = "SELECT 1 FROM pacientes WHERE numero_documento = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new Exception("No hay un paciente con ese número documento. " + e.getMessage());
        }
    }

    public static String BuscarPacientePorDocumento(String numeroDocumento) throws Exception {

        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!ContieneSoloNumeros(numeroDocumento)) {
            throw new IllegalArgumentException("El número de documento sólo debe contener números.");
        }

        String sql = "SELECT nombre FROM pacientes WHERE numero_documento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nombre");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar paciente: " + e.getMessage());
        }
    }

    public static void CargarNombrePaciente(String numeroDocumento, JTextField campoNombre) {

        try {
            if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
                throw new IllegalArgumentException("El número de documento no puede estar vacío.");
            }

            if (!ContieneSoloNumeros(numeroDocumento)) {
                throw new IllegalArgumentException("El número de documento solo debe contener números.");
            }

            String nombre = BuscarPacientePorDocumento(numeroDocumento);

            if (nombre != null) {
                campoNombre.setText(nombre);
            } else {
                JOptionPane.showMessageDialog(null, "No existe un paciente con ese número de documento.");
                campoNombre.setText("");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            campoNombre.setText(""); // Limpiar si hay error
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el nombre del paciente: " + ex.getMessage());
            campoNombre.setText(""); // Limpiar si hay error
        }
    }


////////////////////////77
}
