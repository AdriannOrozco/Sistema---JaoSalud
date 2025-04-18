package Comandos.metodos;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
        String sql = "SELECT * FROM medicos";

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
                row[0] = rs.getString("identificacionDoctor");
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

        String sql = "SELECT 1 FROM pacientes WHERE numeroDocumento = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new Exception("No hay un paciente con ese número documento. " + e.getMessage());
        }
    }

    public static String BuscarIdentificacionPorNombreDoctor(String nombre) {

        String sql = "SELECT identificacionDoctor FROM medicos WHERE CONCAT(primerNombre, ' ', primerApellido) = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String identificacion = rs.getString("identificacionDoctor");
                return identificacion;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar médico: " + e.getMessage());
        }
        return null;
    }

    public static int BuscarIdConsultorioPorConsultorio(String consultorio) {

        String sql = "SELECT idConsultorio FROM consultorios WHERE consultorio = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, consultorio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idConsultorio = rs.getInt("idConsultorio");
                return idConsultorio;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar consultorio: " + e.getMessage());
        }
        return 0;
    }

    public static String BuscarPacientePorDocumento(String numeroDocumento) throws Exception {

        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!ContieneSoloNumeros(numeroDocumento)) {
            throw new IllegalArgumentException("El número de documento sólo debe contener números.");
        }

        String sql = "SELECT primerNombre, primerApellido, segundoApellido FROM pacientes WHERE numeroDocumento = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, numeroDocumento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String primerNombre = rs.getString("primerNombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("segundoApellido");
                return primerNombre + " " + primerApellido + " " + " " + segundoApellido;
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

    public void MostrarConsultorios(JTable tablaConsultorios) {
        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT * FROM consultorios";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Consultorio ");
            model.addColumn("Consultorio");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("idConsultorio");
                row[1] = rs.getString("consultorio");
                model.addRow(row);

            }
            tablaConsultorios.setModel(model);
            tablaConsultorios.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar doctores: " + e.getMessage());
        }
    }

    public void MostrarCitas(JTable tablaCitas) {
        try {
            Connection con = ConexionBD.conectar();
            String sql = "SELECT idCita, motivo ,idConsultorio, identificacionDoctor, fechaCita, hora, nombrePaciente, numeroDocumento FROM citas WHERE estado = true";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Cita");
            model.addColumn("Consultorio");
            model.addColumn("Doctor/a");
            model.addColumn("Fecha");
            model.addColumn("Hora");
            model.addColumn("Paciente");
            //model.addColumn("DNI paciente");

            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("idCita");
                row[1] = rs.getString("idConsultorio");
                row[2] = rs.getString("identificacionDoctor");
                row[3] = rs.getString("fechaCita");
                row[4] = rs.getString("hora");
                row[5] = rs.getString("nombrePaciente");
                //row [6] = rs.getString("numeroDocumento");
                model.addRow(row);

            }
            tablaCitas.setModel(model);
            tablaCitas.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar doctores: " + e.getMessage());
        }
    }

    public void filtrarConsultoriosPorDoctor(String nombreDoctor, String identificacionDoctor, JComboBox cboConsultorio) {
        String sql = "SELECT especialidad FROM medicos WHERE CONCAT(primerNombre, ' ', primerApellido) = ? AND identificacionDoctor = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreDoctor);
            pstmt.setString(2, identificacionDoctor);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String especialidad = rs.getString("especialidad");
                cargarConsultoriosPorEspecialidad(especialidad, cboConsultorio);
            } else {
                cboConsultorio.removeAllItems();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void cargarNombreYEspecialidadDoctores(String identificacionDoctor, JTextField txtNombre, JTextField txtEspecialidad) {
        String sql = "SELECT CONCAT(primerNombre, ' ', primerApellido) AS nombre, especialidad FROM medicos WHERE identificacionDoctor = ?";

        if (identificacionDoctor == null) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }
        if (!ContieneSoloNumeros(identificacionDoctor)) {
            throw new IllegalArgumentException("El número de documento sólo contiene números..");
        }

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, identificacionDoctor);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String especialidad = rs.getString("especialidad");

                txtNombre.setText(nombre);
                txtEspecialidad.setText(especialidad);
            } else {
                throw new IllegalArgumentException("No existe un doctor con esa identificación..");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void cargarMotivoPorID(String idCitaStr, JTextArea area_motivo) {
        String sql = "SELECT motivo FROM citas WHERE idCita = ?";

        if (idCitaStr == null) {
        throw new IllegalArgumentException("El número de la cita no puede estar vacío");
        }
        if (!ContieneSoloNumeros(idCitaStr)) {
            throw new IllegalArgumentException("El número de la cita sólo contiene números.");
        }

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idCita;
            idCita = Integer.parseInt(idCitaStr);
            pstmt.setInt(1, idCita);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String motivo = rs.getString("motivo");
                area_motivo.setText(motivo);
            } else {
                throw new IllegalArgumentException("No existe una cita con esa identificación..");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void cargarConsultoriosPorEspecialidad(String especialidad, JComboBox cboConsultorio) {
        String sql = "SELECT consultorio FROM consultorios WHERE especialidad = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            cboConsultorio.removeAllItems();
            pstmt.setString(1, especialidad);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombreConsultorio = rs.getString("consultorio");
                cboConsultorio.addItem(nombreConsultorio);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar consultorios: " + e.getMessage());
        }
    }

    public static boolean VerificarDisponibilidadDoctor(String identificacion, Date fechaCita, String hora) {
        boolean ocupado = false;

        String sql = "SELECT COUNT(*) FROM citas WHERE identificacionDoctor = ? AND fechaCita = ? AND hora = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificacion);
            ps.setDate(2, new java.sql.Date(fechaCita.getTime()));
            ps.setString(3, hora);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                ocupado = count > 0;
            }
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad del doctor: " + e.getMessage());
        }

        return ocupado;
    }

    public static boolean VerificarDisponibilidadConsultorio(int idConsultorio, Date fechaCita, String hora) {
        boolean ocupado = false;

        String sql = "SELECT COUNT(*) FROM citas WHERE idConsultorio = ? AND fechaCita = ? AND hora = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idConsultorio);
            ps.setDate(2, new java.sql.Date(fechaCita.getTime()));
            ps.setString(3, hora);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                ocupado = count > 0;
            }

            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad del consultorio: " + e.getMessage());
        }

        return ocupado;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
