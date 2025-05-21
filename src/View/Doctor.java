/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Comandos.GenerarReporteMedicoCommand;
import Model.Paciente;
import Persistencia.Citas.CargarDatosCitaId.CargarDatosCita;
import Persistencia.Citas.VerificarCitaId.VerificarCitaId;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Persistencia.Doctor.Medico.MedicoDAO;
import Persistencia.Doctor.CargarNombreEspecialidad.CargarNombreEspecialidad;
import Persistencia.Doctor.Citas.CargarDatosCitaDoctor;
import Persistencia.Doctor.Citas.CitasContadorDAO;
import Persistencia.Doctor.Citas.CitasMedicoDAO;
import Persistencia.Doctor.Citas.EstadoCita;
import Persistencia.Doctor.Consultorio.ConsultorioDAO;
import Persistencia.Doctor.Medicamentos.MedicamentosDAO;
import Persistencia.Doctor.Pacientes.PacientePorDoctorDAO;
import Persistencia.Doctor.Reportes.CargarDatosPacienteReporte;
import Persistencia.Doctor.Reportes.ReporteMedicoDAO;
import Persistencia.Doctor.Resultados.ResultadoMedicoDAO;
import Persistencia.Doctor.Resultados.ResultadoMedicoPDFGenerator;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;
import infraestructura.sesion.SesionUsuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Osvaldo
 */
public class Doctor extends javax.swing.JFrame {

    private MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();
    private VerificarCitaId verificarCita = new VerificarCitaId();
    private PacientePorDoctorDAO pacientePorDoctorDAO;
    private MedicoDAO medicoDAO;
    private LocalDate fechaActual;
    private ConsultorioDAO consultorioDAO;
    private String idMedicoActual;
    private CitasContadorDAO citasContadorDAO;

    /**
     * Creates new form Doctor
     */
    public Doctor() {
        try {
            fechaActual = LocalDate.now();
            consultorioDAO = new ConsultorioDAO();
            citasContadorDAO = new CitasContadorDAO();
            pacientePorDoctorDAO = new PacientePorDoctorDAO();
            initComponents();
            MedicamentosDAO medicamentosDAO = new MedicamentosDAO();
            medicamentosDAO.cargarMedicamentosDisponibles(jcboxMedicamentosPreescrito);
            setExtendedState(MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            configurarTablaCitas(); // Agregar esta línea

            // Obtener y verificar el ID del médico
            idMedicoActual = SesionUsuario.getIdMedico();
            System.out.println("ID Médico cargado: " + idMedicoActual); // Para debug

            if (idMedicoActual == null || idMedicoActual.isEmpty()) {
                throw new IllegalStateException("No hay un médico con sesión iniciada");
            }

            // Cargar datos del médico
            cargarDatosMedico();
            actualizarTablaPacientes();

        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error detallado en consola
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            new Login().setVisible(true);
            return;
        }
    }

    private void resaltarBotonSeleccionado(JPanel botonSeleccionado) {
        // Color por defecto
        Color colorDefault = new Color(0, 51, 130);
        // Color seleccionado
        Color colorSeleccionado = new Color(0, 71, 150);

        // Resetear todos los botones
        panelBtnInicio.setBackground(colorDefault);
        panelBtnVerPaciente.setBackground(colorDefault);
        panelBtnCrearReporte.setBackground(colorDefault);
        panelBtnVerCita.setBackground(colorDefault);
        panelBtnAtenderCitas.setBackground(colorDefault);

        // Resaltar el botón seleccionado
        botonSeleccionado.setBackground(colorSeleccionado);
    }

    private void actualizarTablaPacientes() {
        try {
            if (idMedicoActual == null || idMedicoActual.isEmpty()) {
                System.out.println("ID Médico no válido");
                return;
            }
            System.out.println("Consultando pacientes para médico: " + idMedicoActual);
            List<Paciente> pacientes = pacientePorDoctorDAO.obtenerPacientesPorDoctor(idMedicoActual);
            System.out.println("Pacientes obtenidos: " + pacientes.size());

            // Limpiar tabla
            DefaultTableModel modelo = (DefaultTableModel) TablaVerPacientesBuscar.getModel();
            modelo.setRowCount(0);

            // Llenar tabla con datos
            for (Paciente paciente : pacientes) {
                modelo.addRow(new Object[]{
                    paciente.getPrimerNombre(),
                    paciente.getSegundoNombre(),
                    paciente.getPrimerApellido(),
                    paciente.getSegundoApellido(),
                    paciente.getTelefono()

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar pacientes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarTablaCitas() {
        String[] columnas = {
            "ID Cita",
            "Consultorio",
            "Hora",
            "Motivo",
            "Nombre Paciente",
            "Documento",
            "Estado"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaCitasDelDia.setModel(modelo);

        // Configurar el ancho de las columnas
        int[] anchos = {70, 100, 70, 200, 200, 100, 100};
        for (int i = 0; i < tablaCitasDelDia.getColumnCount(); i++) {
            tablaCitasDelDia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        // Configurar el renderizador para la columna de estado (columna 6)
        tablaCitasDelDia.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                if (value != null) {
                    String estado = value.toString();
                    switch (estado) {
                        case "Pendiente" ->
                            setForeground(new Color(0, 102, 204));  // Azul
                        case "Atendida" ->
                            setForeground(new Color(0, 153, 0));     // Verde
                        case "Expirada" ->
                            setForeground(new Color(204, 0, 0));     // Rojo
                        default ->
                            setForeground(table.getForeground());
                    }
                }

                setHorizontalAlignment(SwingConstants.CENTER);

                // Mantener el fondo blanco cuando está seleccionado
                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                } else {
                    setBackground(table.getBackground());
                }

                return this;
            }
        });

        // Configurar el renderer para centrar el texto en todas las columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tablaCitasDelDia.getColumnCount(); i++) {
            if (i != 6) { // Aplicar a todas las columnas excepto la de estado
                tablaCitasDelDia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        // Configurar la cabecera de la tabla
        JTableHeader header = tablaCitasDelDia.getTableHeader();
        header.setReorderingAllowed(false); // Evitar que se reordenen las columnas
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                setBackground(new Color(36, 47, 65));
                setForeground(Color.WHITE);
                setFont(getFont().deriveFont(Font.BOLD));
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(javax.swing.BorderFactory.createEtchedBorder());
                return c;
            }
        });

        // Configurar selección y altura de filas
        tablaCitasDelDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCitasDelDia.setRowHeight(25);
        tablaCitasDelDia.setShowGrid(true);
        tablaCitasDelDia.setGridColor(new Color(234, 234, 234));
        tablaCitasDelDia.setFocusable(false);
    }

    private void cargarDatosMedico() {
        try {
            CargarNombreEspecialidad cargarDatos = new CargarNombreEspecialidad();

            // Asegúrate que estos son los nombres exactos de tus JTextField en el panel de inicio
            cargarDatos.cargarNombreYEspecialidadDoctores(
                    idMedicoActual,
                    InombreDelDoctorCargado, // JTextField para el nombre
                    lEspecializacionDocotorCarga // JTextField para la especialidad
            );

            // Obtener consultorio y número de consultorios disponibles
            String consultorio = consultorioDAO.obtenerConsultorioPorEspecialidad(
                    lEspecializacionDocotorCarga.getText()
            );
            int consultoriosDisponibles = consultorioDAO.obtenerNumeroConsultoriosDisponibles(
                    lEspecializacionDocotorCarga.getText()
            );
            jLabel2.setText("°Numero De Consultorio: " + consultorio);
            InumeroDeConsultoriosDisp.setText("Consultorios disponibles: " + consultoriosDisponibles);

            // Actualizar contadores de citas
            actualizarContadorCitas();
        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error detallado en consola
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarContadorCitas() {
        try {
            // Obtener citas del día y atendidas
            int citasDelDia = citasContadorDAO.obtenerCitasDelDia(idMedicoActual, fechaActual);
            int citasAtendidas = citasContadorDAO.obtenerCitasAtendidas(idMedicoActual, fechaActual);

            // Actualizar los campos en la interfaz
            if (citasDelDia >= 0) {
                numeroDcitasEnElDiaDoctor.setText(String.valueOf(citasDelDia));
            } else {
                numeroDcitasEnElDiaDoctor.setText("Error");
            }

            if (citasAtendidas >= 0) {
                nroDCitasAtendidasDia.setText(String.valueOf(citasAtendidas));
            } else {
                nroDCitasAtendidasDia.setText("Error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            numeroDcitasEnElDiaDoctor.setText("Error");
            nroDCitasAtendidasDia.setText("Error");
        }
    }

    private void actualizarConsultoriosDisponibles() {
        try {
            String especialidadMedico = medicoDAO.obtenerEspecialidadMedico(idMedicoActual);

            if (especialidadMedico == null) {
                InumeroDeConsultoriosDisp.setText("Error");
                return;
            }

            int consultoriosDisp = consultorioDAO.obtenerNumeroConsultoriosDisponibles(especialidadMedico);
            InumeroDeConsultoriosDisp.setText(String.valueOf(consultoriosDisp));

        } catch (Exception e) {
            e.printStackTrace();
            InumeroDeConsultoriosDisp.setText("Error");
        }
    }

    private void configurarEventosMenu() {
        // Eventos click para navegación
        panelBtnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPaneDoctores.setSelectedIndex(0);
            }
        });

        panelBtnVerPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPaneDoctores.setSelectedIndex(1);
            }
        });

        // ... configurar demás botones del menú
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoBlancoPrincipal = new javax.swing.JPanel();
        panelAzul = new javax.swing.JPanel();
        labelApartadoDe = new javax.swing.JLabel();
        labelRecepcionisa = new javax.swing.JLabel();
        panelDecoración = new javax.swing.JPanel();
        panelBtnInicio = new javax.swing.JPanel();
        iconInicio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelBtnVerPaciente = new javax.swing.JPanel();
        iconAgregarPaciente = new javax.swing.JLabel();
        labelAgregarPaciente = new javax.swing.JLabel();
        labelMenúOpciones = new javax.swing.JLabel();
        panelBtnCrearReporte = new javax.swing.JPanel();
        iconEditarPaciente = new javax.swing.JLabel();
        labelEditarPaciente = new javax.swing.JLabel();
        panelBtnVerCita = new javax.swing.JPanel();
        iconAgendarCita = new javax.swing.JLabel();
        labelEditarPaciente1 = new javax.swing.JLabel();
        panelBtnAtenderCitas = new javax.swing.JPanel();
        iconHistorial = new javax.swing.JLabel();
        labelVerHistoriales = new javax.swing.JLabel();
        panelBtnCerrarSesion = new javax.swing.JPanel();
        iconCerrarSesion = new javax.swing.JLabel();
        labelCerrarSesion = new javax.swing.JLabel();
        iconMedico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPaneDoctores = new javax.swing.JTabbedPane();
        panelDeInicio = new javax.swing.JPanel();
        panelFondoBlancoInicio = new javax.swing.JPanel();
        labelTablaDeCita = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblRealodInicio = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelTablaDeCita1 = new javax.swing.JLabel();
        InombreDelDoctorCargado = new javax.swing.JTextField();
        lEspecializacionDocotorCarga = new javax.swing.JTextField();
        InumeroDeConsultoriosDisp = new javax.swing.JTextField();
        numeroDcitasEnElDiaDoctor = new javax.swing.JTextField();
        nroDCitasAtendidasDia = new javax.swing.JTextField();
        panelVerPaciente = new javax.swing.JPanel();
        labelPrimerNombre = new javax.swing.JPanel();
        labelTabbedAgregarPaciente = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        txtPrimerNombre = new javax.swing.JTextField();
        labelPrimerrNombre = new javax.swing.JLabel();
        txtSegundoNombre = new javax.swing.JTextField();
        labelSegundoNombre = new javax.swing.JLabel();
        txtPrimerApellido = new javax.swing.JTextField();
        txtSegundoApellido = new javax.swing.JTextField();
        labelPrimerApellido = new javax.swing.JLabel();
        labelSegundoApellido = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaVerPacientesBuscar = new javax.swing.JTable();
        buttonBuscarPacientes = new javax.swing.JButton();
        panelCrearReporte = new javax.swing.JPanel();
        panelFondoBlancoCrearReporte = new javax.swing.JPanel();
        panelTabbedCrearReporte = new javax.swing.JLabel();
        labelInformación = new javax.swing.JLabel();
        labelInformación1 = new javax.swing.JLabel();
        labelDigiteLaIdentificacion = new javax.swing.JLabel();
        txtBuscarNumeroIdentificacion = new javax.swing.JTextField();
        cboBuscarNumeroIdentificacion = new javax.swing.JComboBox<>();
        buttonBuscarPacienteReport = new javax.swing.JButton();
        buttonLimpiarbusq = new javax.swing.JButton();
        panelDivisionCrearReporte = new javax.swing.JPanel();
        labelEncontrado = new javax.swing.JLabel();
        labelDatoQueDesea = new javax.swing.JLabel();
        labelObligatorio = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtReporteAgregado = new javax.swing.JTextArea();
        txtCargarEPSReport = new javax.swing.JTextField();
        buttonGenerarReporte = new javax.swing.JButton();
        buttonCancelarReporte = new javax.swing.JButton();
        labelEncontrado1 = new javax.swing.JLabel();
        labelCargarEPS = new javax.swing.JLabel();
        labelCargarNombre = new javax.swing.JLabel();
        txtCargarPrimerNombreReport = new javax.swing.JTextField();
        txtCargarPrimerApellidoReport = new javax.swing.JTextField();
        panelVerCitas = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BtnBusquedaDeFechaSelect = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        FechaQSeSelecciono = new javax.swing.JTextField();
        CalendarioDelMedico = new com.toedter.calendar.JCalendar();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCitasDelDia = new javax.swing.JTable();
        panelAtenderCitaMedica = new javax.swing.JPanel();
        panelFondoBlancoAtenderCitaMedica = new javax.swing.JPanel();
        labelTabbedEditarCita = new javax.swing.JLabel();
        labelInformaciónCita = new javax.swing.JLabel();
        labelCitaIdentificacion = new javax.swing.JLabel();
        txtBuscarCita = new javax.swing.JTextField();
        labelObligatorioID = new javax.swing.JLabel();
        buttonBuscarCitaPorID = new javax.swing.JButton();
        panelDiv = new javax.swing.JPanel();
        lblDatosCitas = new javax.swing.JLabel();
        txtDocPaciente = new javax.swing.JTextField();
        txtCargarNPaciente = new javax.swing.JTextField();
        lblPaciente = new javax.swing.JLabel();
        lblDocPaciente = new javax.swing.JLabel();
        txtFechaCitaV = new javax.swing.JTextField();
        lblFechaaCita = new javax.swing.JLabel();
        lblNombreDoctor = new javax.swing.JLabel();
        txtNomDoctor = new javax.swing.JTextField();
        lblConsultorio = new javax.swing.JLabel();
        txtConsultorioAsignadoo = new javax.swing.JTextField();
        buttonTerminarCita = new javax.swing.JButton();
        jcboxTipoDeCita = new javax.swing.JComboBox<>();
        lblNombreDoctor1 = new javax.swing.JLabel();
        lblNombreDoctor2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtMDConsulta = new javax.swing.JTextArea();
        lblDatosCitas1 = new javax.swing.JLabel();
        lblNombreDoctor3 = new javax.swing.JLabel();
        lblNombreDoctor4 = new javax.swing.JLabel();
        lblNombreDoctor5 = new javax.swing.JLabel();
        lblNombreDoctor6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPArterial = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtFCardiaca = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtTemperatura = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtPYT = new javax.swing.JTextArea();
        lblNombreDoctor7 = new javax.swing.JLabel();
        lblNombreDoctor8 = new javax.swing.JLabel();
        lblNombreDoctor9 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtEstudSolicitados = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtAlergImporantes = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtEPrevias = new javax.swing.JTextArea();
        lblNombreDoctor10 = new javax.swing.JLabel();
        lblNombreDoctor11 = new javax.swing.JLabel();
        lblNombreDoctor12 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtMtosActuales = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtRecomendaciones = new javax.swing.JTextArea();
        LImpiarTexto = new javax.swing.JButton();
        jcboxMedicamentosPreescrito = new javax.swing.JComboBox<>();
        labelJaoSalud = new javax.swing.JLabel();
        labelCartagenaDeIndias = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondoBlancoPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        fondoBlancoPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAzul.setBackground(new java.awt.Color(0, 51, 102));

        labelApartadoDe.setBackground(new java.awt.Color(255, 255, 255));
        labelApartadoDe.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 30)); // NOI18N
        labelApartadoDe.setForeground(new java.awt.Color(255, 255, 255));
        labelApartadoDe.setText("APARTADO ");

        labelRecepcionisa.setBackground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 30)); // NOI18N
        labelRecepcionisa.setForeground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa.setText("MEDICO");

        panelDecoración.setBackground(new java.awt.Color(0, 51, 130));

        javax.swing.GroupLayout panelDecoraciónLayout = new javax.swing.GroupLayout(panelDecoración);
        panelDecoración.setLayout(panelDecoraciónLayout);
        panelDecoraciónLayout.setHorizontalGroup(
            panelDecoraciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoraciónLayout.setVerticalGroup(
            panelDecoraciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        panelBtnInicio.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnInicioMouseClicked(evt);
            }
        });

        iconInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/inicioRecepcionista.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("INICIO");

        javax.swing.GroupLayout panelBtnInicioLayout = new javax.swing.GroupLayout(panelBtnInicio);
        panelBtnInicio.setLayout(panelBtnInicioLayout);
        panelBtnInicioLayout.setHorizontalGroup(
            panelBtnInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnInicioLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(iconInicio)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnInicioLayout.setVerticalGroup(
            panelBtnInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnInicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBtnInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panelBtnVerPaciente.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnVerPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnVerPacienteMouseClicked(evt);
            }
        });

        iconAgregarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscarPaciente.png"))); // NOI18N

        labelAgregarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelAgregarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        labelAgregarPaciente.setText("VER PACIENTES");

        javax.swing.GroupLayout panelBtnVerPacienteLayout = new javax.swing.GroupLayout(panelBtnVerPaciente);
        panelBtnVerPaciente.setLayout(panelBtnVerPacienteLayout);
        panelBtnVerPacienteLayout.setHorizontalGroup(
            panelBtnVerPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnVerPacienteLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(iconAgregarPaciente)
                .addGap(18, 18, 18)
                .addComponent(labelAgregarPaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnVerPacienteLayout.setVerticalGroup(
            panelBtnVerPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnVerPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnVerPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelMenúOpciones.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        labelMenúOpciones.setForeground(new java.awt.Color(255, 255, 255));
        labelMenúOpciones.setText("MENÚ DE OPCIONES");

        panelBtnCrearReporte.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnCrearReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnCrearReporteMouseClicked(evt);
            }
        });

        iconEditarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/editarPaciente.png"))); // NOI18N

        labelEditarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelEditarPaciente.setText("CREAR REPORTE");

        javax.swing.GroupLayout panelBtnCrearReporteLayout = new javax.swing.GroupLayout(panelBtnCrearReporte);
        panelBtnCrearReporte.setLayout(panelBtnCrearReporteLayout);
        panelBtnCrearReporteLayout.setHorizontalGroup(
            panelBtnCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCrearReporteLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(iconEditarPaciente)
                .addGap(18, 18, 18)
                .addComponent(labelEditarPaciente)
                .addGap(39, 39, 39))
        );
        panelBtnCrearReporteLayout.setVerticalGroup(
            panelBtnCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCrearReporteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnVerCita.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnVerCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnVerCitaMouseClicked(evt);
            }
        });

        iconAgendarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/agendarCita.png"))); // NOI18N

        labelEditarPaciente1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente1.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente1.setText("VER CALENDARIO");

        javax.swing.GroupLayout panelBtnVerCitaLayout = new javax.swing.GroupLayout(panelBtnVerCita);
        panelBtnVerCita.setLayout(panelBtnVerCitaLayout);
        panelBtnVerCitaLayout.setHorizontalGroup(
            panelBtnVerCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnVerCitaLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(iconAgendarCita)
                .addGap(18, 18, 18)
                .addComponent(labelEditarPaciente1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnVerCitaLayout.setVerticalGroup(
            panelBtnVerCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnVerCitaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnVerCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEditarPaciente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnAtenderCitas.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnAtenderCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnAtenderCitasMouseClicked(evt);
            }
        });

        iconHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cita-medica.png"))); // NOI18N

        labelVerHistoriales.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelVerHistoriales.setForeground(new java.awt.Color(255, 255, 255));
        labelVerHistoriales.setText("Atender Cita");

        javax.swing.GroupLayout panelBtnAtenderCitasLayout = new javax.swing.GroupLayout(panelBtnAtenderCitas);
        panelBtnAtenderCitas.setLayout(panelBtnAtenderCitasLayout);
        panelBtnAtenderCitasLayout.setHorizontalGroup(
            panelBtnAtenderCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAtenderCitasLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(iconHistorial)
                .addGap(18, 18, 18)
                .addComponent(labelVerHistoriales)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnAtenderCitasLayout.setVerticalGroup(
            panelBtnAtenderCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAtenderCitasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnAtenderCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelVerHistoriales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnCerrarSesion.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnCerrarSesionMouseClicked(evt);
            }
        });

        iconCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cerrarSesión.png"))); // NOI18N

        labelCerrarSesion.setBackground(new java.awt.Color(204, 0, 0));
        labelCerrarSesion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelCerrarSesion.setForeground(new java.awt.Color(255, 51, 51));
        labelCerrarSesion.setText("CERRAR SESIÓN");

        javax.swing.GroupLayout panelBtnCerrarSesionLayout = new javax.swing.GroupLayout(panelBtnCerrarSesion);
        panelBtnCerrarSesion.setLayout(panelBtnCerrarSesionLayout);
        panelBtnCerrarSesionLayout.setHorizontalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(iconCerrarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnCerrarSesionLayout.setVerticalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnCerrarSesionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCerrarSesion)
                .addContainerGap())
        );

        iconMedico.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        iconMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/doctor.png"))); // NOI18N

        javax.swing.GroupLayout panelAzulLayout = new javax.swing.GroupLayout(panelAzul);
        panelAzul.setLayout(panelAzulLayout);
        panelAzulLayout.setHorizontalGroup(
            panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnCrearReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnVerCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnVerPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDecoración, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnAtenderCitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAzulLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenúOpciones)
                .addGap(52, 52, 52))
            .addGroup(panelAzulLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAzulLayout.createSequentialGroup()
                        .addComponent(labelRecepcionisa, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(iconMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelApartadoDe))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAzulLayout.setVerticalGroup(
            panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelApartadoDe, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRecepcionisa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(panelDecoración, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMenúOpciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnVerPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(panelBtnCrearReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnVerCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnAtenderCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        fondoBlancoPrincipal.add(panelAzul, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 300, 660));

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setForeground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        fondoBlancoPrincipal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 830, 40));

        jTabbedPaneDoctores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelFondoBlancoInicio.setBackground(new java.awt.Color(255, 255, 255));
        panelFondoBlancoInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        labelTablaDeCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTablaDeCita.setForeground(new java.awt.Color(0, 0, 102));
        labelTablaDeCita.setText("Especializacion:");

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/consultorioCita.png"))); // NOI18N
        jLabel2.setText("°Numero De Consultorio:");

        jLabel7.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));

        lblRealodInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/reiniciarInicio.png"))); // NOI18N
        lblRealodInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRealodInicioMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seleccionarDato.png"))); // NOI18N
        jLabel8.setText("°Numero De citas En el Dia:");

        jLabel9.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cheque.png"))); // NOI18N
        jLabel9.setText("°Numero De citas Atendidas:");

        labelTablaDeCita1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTablaDeCita1.setForeground(new java.awt.Color(0, 0, 102));
        labelTablaDeCita1.setText("Bienvenido Doctor/a:");

        InombreDelDoctorCargado.setEditable(false);
        InombreDelDoctorCargado.setBackground(new java.awt.Color(255, 255, 255));
        InombreDelDoctorCargado.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        InombreDelDoctorCargado.setForeground(new java.awt.Color(0, 0, 0));
        InombreDelDoctorCargado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        InombreDelDoctorCargado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lEspecializacionDocotorCarga.setEditable(false);
        lEspecializacionDocotorCarga.setBackground(new java.awt.Color(255, 255, 255));
        lEspecializacionDocotorCarga.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        lEspecializacionDocotorCarga.setForeground(new java.awt.Color(0, 0, 0));
        lEspecializacionDocotorCarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lEspecializacionDocotorCarga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        InumeroDeConsultoriosDisp.setEditable(false);
        InumeroDeConsultoriosDisp.setBackground(new java.awt.Color(255, 255, 255));
        InumeroDeConsultoriosDisp.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        InumeroDeConsultoriosDisp.setForeground(new java.awt.Color(0, 0, 0));
        InumeroDeConsultoriosDisp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        InumeroDeConsultoriosDisp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        numeroDcitasEnElDiaDoctor.setEditable(false);
        numeroDcitasEnElDiaDoctor.setBackground(new java.awt.Color(255, 255, 255));
        numeroDcitasEnElDiaDoctor.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        numeroDcitasEnElDiaDoctor.setForeground(new java.awt.Color(0, 0, 0));
        numeroDcitasEnElDiaDoctor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        numeroDcitasEnElDiaDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nroDCitasAtendidasDia.setEditable(false);
        nroDCitasAtendidasDia.setBackground(new java.awt.Color(255, 255, 255));
        nroDCitasAtendidasDia.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        nroDCitasAtendidasDia.setForeground(new java.awt.Color(0, 0, 0));
        nroDCitasAtendidasDia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        nroDCitasAtendidasDia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelFondoBlancoInicioLayout = new javax.swing.GroupLayout(panelFondoBlancoInicio);
        panelFondoBlancoInicio.setLayout(panelFondoBlancoInicioLayout);
        panelFondoBlancoInicioLayout.setHorizontalGroup(
            panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(21, 21, 21))
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addComponent(labelTablaDeCita, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRealodInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(nroDCitasAtendidasDia, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(21, 21, 21)
                                .addComponent(numeroDcitasEnElDiaDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addComponent(labelTablaDeCita1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lEspecializacionDocotorCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InombreDelDoctorCargado, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InumeroDeConsultoriosDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(349, 349, 349))
        );
        panelFondoBlancoInicioLayout.setVerticalGroup(
            panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addComponent(labelTablaDeCita1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(lblRealodInicio))
                            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTablaDeCita, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lEspecializacionDocotorCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(12, 12, 12))
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addComponent(InombreDelDoctorCargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InumeroDeConsultoriosDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(numeroDcitasEnElDiaDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70)
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(nroDCitasAtendidasDia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDeInicioLayout = new javax.swing.GroupLayout(panelDeInicio);
        panelDeInicio.setLayout(panelDeInicioLayout);
        panelDeInicioLayout.setHorizontalGroup(
            panelDeInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeInicioLayout.createSequentialGroup()
                .addComponent(panelFondoBlancoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDeInicioLayout.setVerticalGroup(
            panelDeInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneDoctores.addTab("Inicio", panelDeInicio);

        labelPrimerNombre.setBackground(new java.awt.Color(255, 255, 255));

        labelTabbedAgregarPaciente.setBackground(new java.awt.Color(0, 51, 102));
        labelTabbedAgregarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTabbedAgregarPaciente.setForeground(new java.awt.Color(0, 51, 102));
        labelTabbedAgregarPaciente.setText("Ver Pacientes");

        labelNombre.setFont(new java.awt.Font("JetBrains Mono", 3, 14)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(0, 0, 0));
        labelNombre.setText("1. INTRODUZCA SU NOMBRE:");

        txtPrimerNombre.setBackground(new java.awt.Color(225, 225, 225));
        txtPrimerNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtPrimerNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtPrimerNombre.setActionCommand("<Not Set>");
        txtPrimerNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPrimerNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimerNombreActionPerformed(evt);
            }
        });

        labelPrimerrNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        labelPrimerrNombre.setForeground(new java.awt.Color(0, 0, 0));
        labelPrimerrNombre.setText("Primer nombre");

        txtSegundoNombre.setEditable(false);
        txtSegundoNombre.setBackground(new java.awt.Color(225, 225, 225));
        txtSegundoNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtSegundoNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtSegundoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSegundoNombreActionPerformed(evt);
            }
        });

        labelSegundoNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        labelSegundoNombre.setForeground(new java.awt.Color(0, 0, 0));
        labelSegundoNombre.setText("Segundo nombre");

        txtPrimerApellido.setBackground(new java.awt.Color(225, 225, 225));
        txtPrimerApellido.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtPrimerApellido.setForeground(new java.awt.Color(0, 0, 0));
        txtPrimerApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimerApellidoActionPerformed(evt);
            }
        });

        txtSegundoApellido.setBackground(new java.awt.Color(225, 225, 225));
        txtSegundoApellido.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtSegundoApellido.setForeground(new java.awt.Color(0, 0, 0));
        txtSegundoApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSegundoApellidoActionPerformed(evt);
            }
        });

        labelPrimerApellido.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        labelPrimerApellido.setForeground(new java.awt.Color(0, 0, 0));
        labelPrimerApellido.setText("Primer apellido");

        labelSegundoApellido.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        labelSegundoApellido.setForeground(new java.awt.Color(0, 0, 0));
        labelSegundoApellido.setText("Segundo Apellido");

        TablaVerPacientesBuscar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Numero De telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaVerPacientesBuscar);

        buttonBuscarPacientes.setBackground(new java.awt.Color(0, 0, 204));
        buttonBuscarPacientes.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        buttonBuscarPacientes.setForeground(new java.awt.Color(255, 255, 255));
        buttonBuscarPacientes.setText("BUSCAR");
        buttonBuscarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarPacientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout labelPrimerNombreLayout = new javax.swing.GroupLayout(labelPrimerNombre);
        labelPrimerNombre.setLayout(labelPrimerNombreLayout);
        labelPrimerNombreLayout.setHorizontalGroup(
            labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombre)
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                        .addComponent(txtPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelSegundoNombre)))
                                    .addComponent(labelPrimerrNombre))
                                .addGap(26, 26, 26)
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPrimerApellido))
                                .addGap(18, 18, 18)
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSegundoApellido)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(labelTabbedAgregarPaciente)
                        .addGap(189, 189, 189)
                        .addComponent(buttonBuscarPacientes)))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        labelPrimerNombreLayout.setVerticalGroup(
            labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTabbedAgregarPaciente)
                    .addComponent(buttonBuscarPacientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPrimerrNombre)
                    .addComponent(labelSegundoNombre)
                    .addComponent(labelPrimerApellido)
                    .addComponent(labelSegundoApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelVerPacienteLayout = new javax.swing.GroupLayout(panelVerPaciente);
        panelVerPaciente.setLayout(panelVerPacienteLayout);
        panelVerPacienteLayout.setHorizontalGroup(
            panelVerPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelVerPacienteLayout.setVerticalGroup(
            panelVerPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneDoctores.addTab("Ver Pacientes", panelVerPaciente);

        panelFondoBlancoCrearReporte.setBackground(new java.awt.Color(255, 255, 255));

        panelTabbedCrearReporte.setBackground(new java.awt.Color(0, 51, 102));
        panelTabbedCrearReporte.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        panelTabbedCrearReporte.setForeground(new java.awt.Color(0, 51, 102));
        panelTabbedCrearReporte.setText("Crear Reporte");

        labelInformación.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelInformación.setForeground(new java.awt.Color(0, 0, 0));
        labelInformación.setText("Crear Tipo De reporte, Añadido, Historia Clinica relevante al Paciente, esto para Sumar");

        labelInformación1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelInformación1.setForeground(new java.awt.Color(0, 0, 0));
        labelInformación1.setText("informacion Correspondiente");

        labelDigiteLaIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelDigiteLaIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        labelDigiteLaIdentificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscarPaciente.png"))); // NOI18N
        labelDigiteLaIdentificacion.setText("DIGITE LA IDENTIFICACION DEL PACIENTE");

        txtBuscarNumeroIdentificacion.setBackground(new java.awt.Color(225, 225, 225));
        txtBuscarNumeroIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtBuscarNumeroIdentificacion.setForeground(new java.awt.Color(0, 0, 0));

        cboBuscarNumeroIdentificacion.setBackground(new java.awt.Color(225, 225, 225));
        cboBuscarNumeroIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboBuscarNumeroIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        cboBuscarNumeroIdentificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "C.C", "T.I", "R.C", "C.E" }));

        buttonBuscarPacienteReport.setBackground(new java.awt.Color(0, 0, 204));
        buttonBuscarPacienteReport.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        buttonBuscarPacienteReport.setForeground(new java.awt.Color(255, 255, 255));
        buttonBuscarPacienteReport.setText("BUSCAR");
        buttonBuscarPacienteReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarPacienteReportActionPerformed(evt);
            }
        });

        buttonLimpiarbusq.setBackground(new java.awt.Color(0, 0, 0));
        buttonLimpiarbusq.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        buttonLimpiarbusq.setForeground(new java.awt.Color(255, 255, 255));
        buttonLimpiarbusq.setText("LIMPIAR");
        buttonLimpiarbusq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarbusqActionPerformed(evt);
            }
        });

        panelDivisionCrearReporte.setBackground(new java.awt.Color(232, 232, 232));

        labelEncontrado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelEncontrado.setForeground(new java.awt.Color(0, 0, 0));
        labelEncontrado.setText("Reporte Importante Agregar:");

        javax.swing.GroupLayout panelDivisionCrearReporteLayout = new javax.swing.GroupLayout(panelDivisionCrearReporte);
        panelDivisionCrearReporte.setLayout(panelDivisionCrearReporteLayout);
        panelDivisionCrearReporteLayout.setHorizontalGroup(
            panelDivisionCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDivisionCrearReporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelEncontrado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDivisionCrearReporteLayout.setVerticalGroup(
            panelDivisionCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDivisionCrearReporteLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(labelEncontrado))
        );

        labelDatoQueDesea.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelDatoQueDesea.setForeground(new java.awt.Color(0, 0, 0));

        labelObligatorio.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelObligatorio.setForeground(new java.awt.Color(255, 0, 0));
        labelObligatorio.setText("*");

        txtReporteAgregado.setBackground(new java.awt.Color(225, 225, 225));
        txtReporteAgregado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtReporteAgregado.setForeground(new java.awt.Color(0, 0, 0));
        txtReporteAgregado.setRows(5);
        jScrollPane3.setViewportView(txtReporteAgregado);

        txtCargarEPSReport.setEditable(false);
        txtCargarEPSReport.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarEPSReport.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarEPSReport.setForeground(new java.awt.Color(0, 0, 0));
        txtCargarEPSReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargarEPSReportActionPerformed(evt);
            }
        });

        buttonGenerarReporte.setBackground(new java.awt.Color(0, 0, 204));
        buttonGenerarReporte.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonGenerarReporte.setForeground(new java.awt.Color(255, 255, 255));
        buttonGenerarReporte.setText("Agregar");
        buttonGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerarReporteActionPerformed(evt);
            }
        });

        buttonCancelarReporte.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelarReporte.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonCancelarReporte.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelarReporte.setText("CANCELAR");
        buttonCancelarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarReporteActionPerformed(evt);
            }
        });

        labelEncontrado1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelEncontrado1.setForeground(new java.awt.Color(0, 0, 0));
        labelEncontrado1.setText("DATOS DEL CLIENTE:");

        labelCargarEPS.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelCargarEPS.setForeground(new java.awt.Color(0, 0, 0));
        labelCargarEPS.setText("| EPS:");

        labelCargarNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelCargarNombre.setForeground(new java.awt.Color(0, 0, 0));
        labelCargarNombre.setText("| NOMBRE:");

        txtCargarPrimerNombreReport.setEditable(false);
        txtCargarPrimerNombreReport.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarPrimerNombreReport.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarPrimerNombreReport.setForeground(new java.awt.Color(0, 0, 0));

        txtCargarPrimerApellidoReport.setEditable(false);
        txtCargarPrimerApellidoReport.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarPrimerApellidoReport.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarPrimerApellidoReport.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelFondoBlancoCrearReporteLayout = new javax.swing.GroupLayout(panelFondoBlancoCrearReporte);
        panelFondoBlancoCrearReporte.setLayout(panelFondoBlancoCrearReporteLayout);
        panelFondoBlancoCrearReporteLayout.setHorizontalGroup(
            panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(panelTabbedCrearReporte))
                            .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelInformación)
                                    .addComponent(labelInformación1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                        .addGap(163, 163, 163)
                                        .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelDigiteLaIdentificacion)
                                            .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                                        .addComponent(buttonBuscarPacienteReport)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(buttonLimpiarbusq))
                                                    .addComponent(txtBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cboBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(labelObligatorio))))))
                            .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(labelEncontrado1)
                                .addGap(50, 50, 50)
                                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                        .addComponent(labelCargarEPS)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtCargarEPSReport, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                        .addComponent(labelCargarNombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCargarPrimerNombreReport, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCargarPrimerApellidoReport, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 167, Short.MAX_VALUE))
                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelDivisionCrearReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                        .addGap(0, 310, Short.MAX_VALUE)
                                        .addComponent(labelDatoQueDesea))
                                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                                        .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonGenerarReporte)
                                            .addComponent(buttonCancelarReporte))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        panelFondoBlancoCrearReporteLayout.setVerticalGroup(
            panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelTabbedCrearReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInformación)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInformación1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDigiteLaIdentificacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelObligatorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLimpiarbusq)
                    .addComponent(buttonBuscarPacienteReport))
                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCargarEPSReport, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEncontrado1)
                            .addComponent(labelCargarEPS)))
                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(labelDatoQueDesea)))
                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(labelCargarNombre)
                        .addGap(18, 18, 18))
                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCargarPrimerNombreReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCargarPrimerApellidoReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(panelDivisionCrearReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoCrearReporteLayout.createSequentialGroup()
                        .addComponent(buttonGenerarReporte)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancelarReporte))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout panelCrearReporteLayout = new javax.swing.GroupLayout(panelCrearReporte);
        panelCrearReporte.setLayout(panelCrearReporteLayout);
        panelCrearReporteLayout.setHorizontalGroup(
            panelCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoCrearReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelCrearReporteLayout.setVerticalGroup(
            panelCrearReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoCrearReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneDoctores.addTab("Crear Reporte", panelCrearReporte);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1130, 900));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("CALENDARIO DEL DOCTOR");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Search.png.png"))); // NOI18N

        BtnBusquedaDeFechaSelect.setBackground(new java.awt.Color(255, 255, 255));
        BtnBusquedaDeFechaSelect.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        BtnBusquedaDeFechaSelect.setForeground(new java.awt.Color(0, 0, 0));
        BtnBusquedaDeFechaSelect.setText("BUSCAR");
        BtnBusquedaDeFechaSelect.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        BtnBusquedaDeFechaSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusquedaDeFechaSelectActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fecha Seleccionada:");

        FechaQSeSelecciono.setEditable(false);
        FechaQSeSelecciono.setBackground(new java.awt.Color(225, 225, 225));
        FechaQSeSelecciono.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        FechaQSeSelecciono.setForeground(new java.awt.Color(0, 0, 0));
        FechaQSeSelecciono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FechaQSeSelecciono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaQSeSeleccionoActionPerformed(evt);
            }
        });

        CalendarioDelMedico.setBackground(new java.awt.Color(255, 255, 255));
        CalendarioDelMedico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CalendarioDelMedico.setForeground(new java.awt.Color(0, 0, 0));
        CalendarioDelMedico.setToolTipText("");
        CalendarioDelMedico.setDecorationBackgroundColor(new java.awt.Color(255, 255, 255));
        CalendarioDelMedico.setDecorationBackgroundVisible(false);
        CalendarioDelMedico.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        CalendarioDelMedico.setSundayForeground(new java.awt.Color(255, 51, 51));
        CalendarioDelMedico.setWeekdayForeground(new java.awt.Color(51, 255, 255));
        CalendarioDelMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalendarioDelMedicoMouseClicked(evt);
            }
        });
        CalendarioDelMedico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarioDelMedicoPropertyChange(evt);
            }
        });

        tablaCitasDelDia.setAutoCreateRowSorter(true);
        tablaCitasDelDia.setBackground(new java.awt.Color(255, 255, 255));
        tablaCitasDelDia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tablaCitasDelDia.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        tablaCitasDelDia.setForeground(new java.awt.Color(0, 0, 0));
        tablaCitasDelDia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id CIta", "Consultorio", "Hora", "Motivo", "Paciente", "Documento", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCitasDelDia.setToolTipText("");
        jScrollPane4.setViewportView(tablaCitasDelDia);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(FechaQSeSelecciono, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)
                                .addComponent(BtnBusquedaDeFechaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(CalendarioDelMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FechaQSeSelecciono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnBusquedaDeFechaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(41, 41, 41)
                        .addComponent(CalendarioDelMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 313, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelVerCitasLayout = new javax.swing.GroupLayout(panelVerCitas);
        panelVerCitas.setLayout(panelVerCitasLayout);
        panelVerCitasLayout.setHorizontalGroup(
            panelVerCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVerCitasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelVerCitasLayout.setVerticalGroup(
            panelVerCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVerCitasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneDoctores.addTab("Ver Calendario", panelVerCitas);

        panelFondoBlancoAtenderCitaMedica.setBackground(new java.awt.Color(255, 255, 255));

        labelTabbedEditarCita.setBackground(new java.awt.Color(0, 51, 102));
        labelTabbedEditarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTabbedEditarCita.setForeground(new java.awt.Color(0, 51, 102));
        labelTabbedEditarCita.setText("ATENDER CITA");

        labelInformaciónCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelInformaciónCita.setForeground(new java.awt.Color(0, 0, 0));
        labelInformaciónCita.setText("Ingresa El N° De la cita para Que aparezcan datos generales del paciente Y continuar ");

        labelCitaIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelCitaIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        labelCitaIdentificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/idCita.png"))); // NOI18N
        labelCitaIdentificacion.setText("N° DE LA CITA");

        txtBuscarCita.setBackground(new java.awt.Color(225, 225, 225));
        txtBuscarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        txtBuscarCita.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCitaActionPerformed(evt);
            }
        });

        labelObligatorioID.setBackground(new java.awt.Color(255, 0, 0));
        labelObligatorioID.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelObligatorioID.setForeground(new java.awt.Color(255, 0, 0));
        labelObligatorioID.setText("*");

        buttonBuscarCitaPorID.setBackground(new java.awt.Color(0, 0, 201));
        buttonBuscarCitaPorID.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        buttonBuscarCitaPorID.setForeground(new java.awt.Color(255, 255, 255));
        buttonBuscarCitaPorID.setText("CARGAR");
        buttonBuscarCitaPorID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarCitaPorIDActionPerformed(evt);
            }
        });

        panelDiv.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout panelDivLayout = new javax.swing.GroupLayout(panelDiv);
        panelDiv.setLayout(panelDivLayout);
        panelDivLayout.setHorizontalGroup(
            panelDivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDivLayout.setVerticalGroup(
            panelDivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        lblDatosCitas.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 15)); // NOI18N
        lblDatosCitas.setForeground(new java.awt.Color(0, 0, 0));
        lblDatosCitas.setText("DATOS DE LA CITA");

        txtDocPaciente.setEditable(false);
        txtDocPaciente.setBackground(new java.awt.Color(225, 225, 225));
        txtDocPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtDocPaciente.setForeground(new java.awt.Color(0, 0, 0));

        txtCargarNPaciente.setEditable(false);
        txtCargarNPaciente.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarNPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarNPaciente.setForeground(new java.awt.Color(0, 0, 0));

        lblPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblPaciente.setForeground(new java.awt.Color(0, 0, 0));
        lblPaciente.setText("NOMBRE PACIENTE:");

        lblDocPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblDocPaciente.setForeground(new java.awt.Color(0, 0, 0));
        lblDocPaciente.setText("DOCUMENTO PACIENTE:");

        txtFechaCitaV.setEditable(false);
        txtFechaCitaV.setBackground(new java.awt.Color(225, 225, 225));
        txtFechaCitaV.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtFechaCitaV.setForeground(new java.awt.Color(0, 0, 0));

        lblFechaaCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblFechaaCita.setForeground(new java.awt.Color(0, 0, 0));
        lblFechaaCita.setText("FECHA DE LA CITA:");

        lblNombreDoctor.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor.setText("NOMBRE DEL DOCTOR/A:");

        txtNomDoctor.setEditable(false);
        txtNomDoctor.setBackground(new java.awt.Color(225, 225, 225));
        txtNomDoctor.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtNomDoctor.setForeground(new java.awt.Color(0, 0, 0));

        lblConsultorio.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblConsultorio.setForeground(new java.awt.Color(0, 0, 0));
        lblConsultorio.setText("CONSULTORIO:");

        txtConsultorioAsignadoo.setEditable(false);
        txtConsultorioAsignadoo.setBackground(new java.awt.Color(225, 225, 225));
        txtConsultorioAsignadoo.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtConsultorioAsignadoo.setForeground(new java.awt.Color(0, 0, 0));

        buttonTerminarCita.setBackground(new java.awt.Color(0, 0, 0));
        buttonTerminarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonTerminarCita.setForeground(new java.awt.Color(255, 255, 255));
        buttonTerminarCita.setText("Terminar Cita");
        buttonTerminarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTerminarCitaActionPerformed(evt);
            }
        });

        jcboxTipoDeCita.setBackground(new java.awt.Color(225, 225, 225));
        jcboxTipoDeCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jcboxTipoDeCita.setForeground(java.awt.Color.black);
        jcboxTipoDeCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "Odontología", "Oftalmología", "Pediatría", "Cardiología", "Neurología", "Ginecología/Urología", "General" }));

        lblNombreDoctor1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor1.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor1.setText("TIPO DE CITA:");

        lblNombreDoctor2.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor2.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor2.setText("Motivo de la consulta:");

        txtMDConsulta.setBackground(new java.awt.Color(225, 225, 225));
        txtMDConsulta.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtMDConsulta.setForeground(new java.awt.Color(0, 0, 0));
        txtMDConsulta.setRows(5);
        jScrollPane5.setViewportView(txtMDConsulta);

        lblDatosCitas1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 15)); // NOI18N
        lblDatosCitas1.setForeground(new java.awt.Color(0, 0, 0));
        lblDatosCitas1.setText("Signos Vitales");

        lblNombreDoctor3.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor3.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor3.setText("Presión arterial:");

        lblNombreDoctor4.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor4.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor4.setText("Frecuencia cardíaca:");

        lblNombreDoctor5.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor5.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor5.setText("Temperatura:");

        lblNombreDoctor6.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor6.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor6.setText("Peso y talla:");

        txtPArterial.setBackground(new java.awt.Color(225, 225, 225));
        txtPArterial.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtPArterial.setForeground(new java.awt.Color(0, 0, 0));
        txtPArterial.setRows(5);
        jScrollPane6.setViewportView(txtPArterial);

        txtFCardiaca.setBackground(new java.awt.Color(225, 225, 225));
        txtFCardiaca.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtFCardiaca.setForeground(new java.awt.Color(0, 0, 0));
        txtFCardiaca.setRows(5);
        jScrollPane7.setViewportView(txtFCardiaca);

        txtTemperatura.setBackground(new java.awt.Color(225, 225, 225));
        txtTemperatura.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtTemperatura.setForeground(new java.awt.Color(0, 0, 0));
        txtTemperatura.setRows(5);
        jScrollPane8.setViewportView(txtTemperatura);

        txtPYT.setBackground(new java.awt.Color(225, 225, 225));
        txtPYT.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtPYT.setForeground(new java.awt.Color(0, 0, 0));
        txtPYT.setRows(5);
        jScrollPane9.setViewportView(txtPYT);

        lblNombreDoctor7.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor7.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor7.setText("Medicamentos actuales:");

        lblNombreDoctor8.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor8.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor8.setText("Alergias importantes:");

        lblNombreDoctor9.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor9.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor9.setText("Enfermedades previas:");

        txtEstudSolicitados.setBackground(new java.awt.Color(225, 225, 225));
        txtEstudSolicitados.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        txtEstudSolicitados.setForeground(new java.awt.Color(0, 0, 0));
        txtEstudSolicitados.setRows(5);
        jScrollPane10.setViewportView(txtEstudSolicitados);

        txtAlergImporantes.setBackground(new java.awt.Color(225, 225, 225));
        txtAlergImporantes.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtAlergImporantes.setForeground(new java.awt.Color(0, 0, 0));
        txtAlergImporantes.setRows(5);
        jScrollPane11.setViewportView(txtAlergImporantes);

        txtEPrevias.setBackground(new java.awt.Color(225, 225, 225));
        txtEPrevias.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtEPrevias.setForeground(new java.awt.Color(0, 0, 0));
        txtEPrevias.setRows(5);
        jScrollPane12.setViewportView(txtEPrevias);

        lblNombreDoctor10.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        lblNombreDoctor10.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor10.setText("Medicamentos prescritos:");

        lblNombreDoctor11.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        lblNombreDoctor11.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor11.setText("Estudios o exámenes solicitados:");

        lblNombreDoctor12.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        lblNombreDoctor12.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor12.setText("Recomendaciones médicas:");

        txtMtosActuales.setBackground(new java.awt.Color(225, 225, 225));
        txtMtosActuales.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtMtosActuales.setForeground(new java.awt.Color(0, 0, 0));
        txtMtosActuales.setRows(5);
        jScrollPane13.setViewportView(txtMtosActuales);

        txtRecomendaciones.setBackground(new java.awt.Color(225, 225, 225));
        txtRecomendaciones.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        txtRecomendaciones.setForeground(new java.awt.Color(0, 0, 0));
        txtRecomendaciones.setRows(5);
        jScrollPane15.setViewportView(txtRecomendaciones);

        LImpiarTexto.setBackground(new java.awt.Color(153, 153, 153));
        LImpiarTexto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        LImpiarTexto.setForeground(new java.awt.Color(0, 0, 0));
        LImpiarTexto.setText("Limpiar Text");
        LImpiarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LImpiarTextoActionPerformed(evt);
            }
        });

        jcboxMedicamentosPreescrito.setBackground(new java.awt.Color(225, 225, 225));
        jcboxMedicamentosPreescrito.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jcboxMedicamentosPreescrito.setForeground(java.awt.Color.black);
        jcboxMedicamentosPreescrito.setToolTipText("");

        javax.swing.GroupLayout panelFondoBlancoAtenderCitaMedicaLayout = new javax.swing.GroupLayout(panelFondoBlancoAtenderCitaMedica);
        panelFondoBlancoAtenderCitaMedica.setLayout(panelFondoBlancoAtenderCitaMedicaLayout);
        panelFondoBlancoAtenderCitaMedicaLayout.setHorizontalGroup(
            panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDiv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addComponent(lblNombreDoctor5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(LImpiarTexto))
                                .addGap(65, 65, 65)
                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addComponent(lblNombreDoctor12, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addComponent(lblNombreDoctor7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane13)
                                        .addGap(40, 40, 40))))
                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(lblNombreDoctor6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNombreDoctor10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcboxMedicamentosPreescrito, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                                .addComponent(lblPaciente)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCargarNPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblConsultorio)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtConsultorioAsignadoo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                                .addComponent(labelCitaIdentificacion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblDatosCitas)
                                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                                        .addComponent(labelObligatorioID, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(buttonBuscarCitaPorID)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblFechaaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(30, 30, 30)
                                                        .addComponent(txtFechaCitaV, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblNombreDoctor4)
                                        .addGap(13, 13, 13)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblNombreDoctor8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(labelInformaciónCita))
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                                .addComponent(buttonTerminarCita)
                                                .addGap(65, 65, 65)
                                                .addComponent(lblNombreDoctor11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                                        .addComponent(lblNombreDoctor1)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jcboxTipoDeCita, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                                        .addGap(8, 8, 8)
                                                        .addComponent(lblDatosCitas1)))
                                                .addGap(18, 18, 18)
                                                .addComponent(lblNombreDoctor2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblNombreDoctor3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblNombreDoctor9, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblDocPaciente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDocPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(lblNombreDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNomDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 58, Short.MAX_VALUE)))
                        .addGap(189, 356, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelTabbedEditarCita)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFondoBlancoAtenderCitaMedicaLayout.setVerticalGroup(
            panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTabbedEditarCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInformaciónCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDatosCitas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCitaIdentificacion)
                    .addComponent(labelObligatorioID)
                    .addComponent(buttonBuscarCitaPorID)
                    .addComponent(lblFechaaCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFechaCitaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCargarNPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblConsultorio)
                        .addComponent(txtConsultorioAsignadoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocPaciente)
                    .addComponent(txtDocPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreDoctor1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcboxTipoDeCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDatosCitas1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombreDoctor3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                .addComponent(lblNombreDoctor4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                        .addComponent(lblNombreDoctor5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreDoctor6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNombreDoctor10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcboxMedicamentosPreescrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)))
                        .addComponent(buttonTerminarCita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LImpiarTexto)
                        .addGap(63, 63, 63))
                    .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreDoctor2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreDoctor9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombreDoctor8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreDoctor7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombreDoctor11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreDoctor12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout panelAtenderCitaMedicaLayout = new javax.swing.GroupLayout(panelAtenderCitaMedica);
        panelAtenderCitaMedica.setLayout(panelAtenderCitaMedicaLayout);
        panelAtenderCitaMedicaLayout.setHorizontalGroup(
            panelAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtenderCitaMedicaLayout.createSequentialGroup()
                .addComponent(panelFondoBlancoAtenderCitaMedica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelAtenderCitaMedicaLayout.setVerticalGroup(
            panelAtenderCitaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoAtenderCitaMedica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneDoctores.addTab("Atender Cita Medica", panelAtenderCitaMedica);

        fondoBlancoPrincipal.add(jTabbedPaneDoctores, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 820, 530));

        labelJaoSalud.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        labelJaoSalud.setForeground(new java.awt.Color(0, 51, 102));
        labelJaoSalud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Salud.png"))); // NOI18N
        labelJaoSalud.setText("JAO SALUD");
        fondoBlancoPrincipal.add(labelJaoSalud, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, 60));

        labelCartagenaDeIndias.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelCartagenaDeIndias.setForeground(new java.awt.Color(0, 0, 51));
        labelCartagenaDeIndias.setText("CARTAGENA DE INDIAS - COLOMBIA");
        fondoBlancoPrincipal.add(labelCartagenaDeIndias, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondoBlancoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoBlancoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBtnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnInicioMouseClicked
        jTabbedPaneDoctores.setSelectedIndex(0); // Panel "Inicio"
        resaltarBotonSeleccionado(panelBtnInicio);
    }//GEN-LAST:event_panelBtnInicioMouseClicked

    private void panelBtnVerPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnVerPacienteMouseClicked
        jTabbedPaneDoctores.setSelectedIndex(1); // Panel "Ver Pacientes"
        resaltarBotonSeleccionado(panelBtnVerPaciente);
        actualizarTablaPacientes();
    }//GEN-LAST:event_panelBtnVerPacienteMouseClicked

    private void panelBtnCrearReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnCrearReporteMouseClicked
        jTabbedPaneDoctores.setSelectedIndex(2); // Panel "Crear Reporte"
        resaltarBotonSeleccionado(panelBtnCrearReporte);
    }//GEN-LAST:event_panelBtnCrearReporteMouseClicked

    private void panelBtnVerCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnVerCitaMouseClicked
        jTabbedPaneDoctores.setSelectedIndex(3); // Panel "Ver Citas"
        resaltarBotonSeleccionado(panelBtnVerCita);
    }//GEN-LAST:event_panelBtnVerCitaMouseClicked

    private void panelBtnAtenderCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAtenderCitasMouseClicked
        jTabbedPaneDoctores.setSelectedIndex(4); // Panel "Atender Cita"
        resaltarBotonSeleccionado(panelBtnAtenderCitas);

        MedicamentosDAO medicamentosDAO = new MedicamentosDAO();
        medicamentosDAO.cargarMedicamentosDisponibles(jcboxMedicamentosPreescrito);
    }//GEN-LAST:event_panelBtnAtenderCitasMouseClicked

    private void panelBtnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnCerrarSesionMouseClicked
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea cerrar sesión?",
                "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            SesionUsuario.cerrarSesion();
            new Login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_panelBtnCerrarSesionMouseClicked

    private void lblRealodInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRealodInicioMouseClicked
        cargarDatosMedico();
        JOptionPane.showMessageDialog(this,
                "Datos actualizados correctamente",
                "Actualización",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblRealodInicioMouseClicked

    private void mostrarPanelDatosCita() {
        panelDiv.setVisible(true);
        lblDatosCitas.setVisible(true);
        lblPaciente.setVisible(true);
        lblDocPaciente.setVisible(true);
        lblFechaaCita.setVisible(true);
        lblNombreDoctor.setVisible(true);
        lblConsultorio.setVisible(true);

        txtCargarNPaciente.setVisible(true);
        txtDocPaciente.setVisible(true);
        txtFechaCitaV.setVisible(true);
        txtNomDoctor.setVisible(true);
        txtConsultorioAsignadoo.setVisible(true);
    }
    private void buttonBuscarPacienteReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarPacienteReportActionPerformed
        try {
            String numeroDocumento = txtBuscarNumeroIdentificacion.getText();
            String tipoDocumento = cboBuscarNumeroIdentificacion.getSelectedItem().toString();

            if (numeroDocumento.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese un número de documento",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            CargarDatosPacienteReporte cargarDatos = new CargarDatosPacienteReporte();
            cargarDatos.cargarDatosPacienteReporte(
                    numeroDocumento,
                    tipoDocumento,
                    txtCargarEPSReport,
                    txtCargarPrimerNombreReport,
                    txtCargarPrimerApellidoReport
            );

            // Habilitar campos del reporte después de cargar los datos
            txtReporteAgregado.setEnabled(true);
            //buttonAgregarReporte.setEnabled(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar paciente: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonBuscarPacienteReportActionPerformed

    private void buttonLimpiarbusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarbusqActionPerformed
        // Limpiar campo de búsqueda
        txtBuscarNumeroIdentificacion.setText("");

        // Limpiar campos de información del paciente
        txtCargarEPSReport.setText("");
        txtCargarPrimerNombreReport.setText("");
        txtCargarPrimerApellidoReport.setText("");

        // Limpiar campo del reporte
        txtReporteAgregado.setText("");

        // Opcional: Deshabilitar campos que deberían estar deshabilitados inicialmente
        txtReporteAgregado.setEnabled(false);

        // Opcional: Restablecer el combo box de tipo de documento si existe
        if (cboBuscarNumeroIdentificacion.getItemCount() > 0) {
            cboBuscarNumeroIdentificacion.setSelectedIndex(0);
        }
    }//GEN-LAST:event_buttonLimpiarbusqActionPerformed

    private void txtCargarEPSReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargarEPSReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargarEPSReportActionPerformed

    private void buttonGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerarReporteActionPerformed
        try {
            // Crear el comando con los datos necesarios
            GenerarReporteMedicoCommand comando = new GenerarReporteMedicoCommand(
                    InombreDelDoctorCargado.getText(),
                    lEspecializacionDocotorCarga.getText(),
                    idMedicoActual,
                    txtCargarPrimerNombreReport.getText(),
                    txtBuscarNumeroIdentificacion.getText(), // ID del paciente
                    txtReporteAgregado.getText()
            );

            // Guardar y generar el reporte
            ReporteMedicoDAO reporteDAO = new ReporteMedicoDAO();
            reporteDAO.guardarReporte(comando);

            JOptionPane.showMessageDialog(this,
                    "Reporte generado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al generar el reporte: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_buttonGenerarReporteActionPerformed

    private void buttonCancelarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelarReporteActionPerformed

    private void CalendarioDelMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalendarioDelMedicoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CalendarioDelMedicoMouseClicked

    private void CalendarioDelMedicoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarioDelMedicoPropertyChange
        if ("calendar".equals(evt.getPropertyName())) {
            try {
                // Obtener la fecha seleccionada del calendario
                java.util.Calendar cal = CalendarioDelMedico.getCalendar();

                // Formatear la fecha al formato deseado (por ejemplo: yyyy-MM-dd)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = sdf.format(cal.getTime());

                // Mostrar la fecha en el TextField
                FechaQSeSelecciono.setText(sdf.format(cal.getTime()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error al procesar la fecha: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_CalendarioDelMedicoPropertyChange

    private void BtnBusquedaDeFechaSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusquedaDeFechaSelectActionPerformed
        try {
            if (FechaQSeSelecciono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor seleccione una fecha primero",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            CitasMedicoDAO citasDAO = new CitasMedicoDAO();
            DefaultTableModel modelo = (DefaultTableModel) tablaCitasDelDia.getModel();

            citasDAO.cargarCitasPorFecha(
                    idMedicoActual,
                    FechaQSeSelecciono.getText(),
                    modelo
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar citas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnBusquedaDeFechaSelectActionPerformed

    private void FechaQSeSeleccionoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaQSeSeleccionoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaQSeSeleccionoActionPerformed

    private void buttonBuscarCitaPorIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarCitaPorIDActionPerformed
        try {
            String idCitaStr = txtBuscarCita.getText().trim();

            if (idCitaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese el ID de la cita",
                        "Campo vacío",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!metodo.ContieneSoloNumeros(idCitaStr)) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese solo números",
                        "Formato inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idCita = Integer.parseInt(idCitaStr);
            CitasMedicoDAO citasDAO = new CitasMedicoDAO();
            EstadoCita estadoCita = citasDAO.obtenerEstadoDetallado(idCita);

            if (estadoCita == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró la cita especificada",
                        "Cita no encontrada",
                        JOptionPane.ERROR_MESSAGE);
                limpiarCamposResultado();
                return;
            }

            // Validar estado de la cita
            if (estadoCita.getFueAtendida()) {
                JOptionPane.showMessageDialog(this,
                        "Esta cita ya fue atendida el " + estadoCita.getFecha(),
                        "Cita ya atendida",
                        JOptionPane.WARNING_MESSAGE);
                limpiarCamposResultado();
                return;
            }

            if (estadoCita.getEstado() == CitasMedicoDAO.ESTADO_EXPIRADA) {
                JOptionPane.showMessageDialog(this,
                        "Esta cita expiró el " + estadoCita.getFecha() + " sin ser atendida",
                        "Cita expirada",
                        JOptionPane.WARNING_MESSAGE);
                limpiarCamposResultado();
                return;
            }

            // Si pasa todas las validaciones, mostrar el panel y cargar datos
            mostrarPanelDatosCita();

            CargarDatosCitaDoctor cargarDatos = new CargarDatosCitaDoctor();
            cargarDatos.cargarDatosCitaParaDoctor(
                    idCita,
                    txtCargarNPaciente,
                    txtDocPaciente,
                    txtFechaCitaV,
                    txtNomDoctor,
                    txtConsultorioAsignadoo
            );

            txtBuscarCita.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al buscar la cita: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            limpiarCamposResultado();
        }
    }//GEN-LAST:event_buttonBuscarCitaPorIDActionPerformed

    private void limpiarCamposResultado() {
        // Limpiar campos de datos de la cita
        txtBuscarCita.setText("");
        txtBuscarCita.setEnabled(true);
        txtCargarNPaciente.setText("");
        txtDocPaciente.setText("");
        txtFechaCitaV.setText("");
        txtNomDoctor.setText("");
        txtConsultorioAsignadoo.setText("");

        // Limpiar campos de tipo de cita y motivo
        jcboxTipoDeCita.setSelectedIndex(0);
        txtMDConsulta.setText("");

        // Limpiar signos vitales
        txtPArterial.setText("");
        txtFCardiaca.setText("");
        txtTemperatura.setText("");
        txtPYT.setText("");

        // Limpiar antecedentes y datos médicos
        txtAlergImporantes.setText("");
        txtEPrevias.setText("");
        txtMtosActuales.setText("");

        // Limpiar datos de la atención actual
        txtEstudSolicitados.setText("");
        txtRecomendaciones.setText("");
        jcboxMedicamentosPreescrito.setSelectedIndex(0);

    }
    private void buttonTerminarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTerminarCitaActionPerformed
        try {
            // Validar que haya un medicamento seleccionado
            if (jcboxMedicamentosPreescrito.getSelectedIndex() == 0
                    || jcboxMedicamentosPreescrito.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this,
                        "Debe seleccionar un medicamento",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCita = Integer.parseInt(txtBuscarCita.getText());

            // Guardar resultado médico
            ResultadoMedicoDAO resultadoDAO = new ResultadoMedicoDAO();
            boolean guardado = resultadoDAO.guardarResultadoMedico(
                    idCita,
                    txtCargarNPaciente.getText(),
                    txtDocPaciente.getText(),
                    txtConsultorioAsignadoo.getText(),
                    txtNomDoctor.getText(),
                    jcboxTipoDeCita.getSelectedItem().toString(),
                    txtMDConsulta.getText(),
                    txtPArterial.getText(),
                    txtFCardiaca.getText(),
                    txtTemperatura.getText(),
                    txtPYT.getText(),
                    txtEPrevias.getText(),
                    txtAlergImporantes.getText(),
                    txtMtosActuales.getText(),
                    jcboxMedicamentosPreescrito.getSelectedItem().toString(),
                    txtEstudSolicitados.getText(),
                    txtRecomendaciones.getText()
            );

            if (guardado) {
                // Actualizar estado de la cita
                CitasMedicoDAO citasDAO = new CitasMedicoDAO();
                boolean estadoActualizado = citasDAO.actualizarEstadoCita(idCita);

                if (!estadoActualizado) {
                    JOptionPane.showMessageDialog(this,
                            "Advertencia: No se pudo actualizar el estado de la cita",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }

                try {
                    // Generar PDF
                    ResultadoMedicoPDFGenerator pdfGenerator = new ResultadoMedicoPDFGenerator();
                    String rutaPDF = pdfGenerator.generarPDF(
                            txtCargarNPaciente.getText(),
                            txtDocPaciente.getText(),
                            txtNomDoctor.getText(),
                            jcboxTipoDeCita.getSelectedItem().toString(),
                            txtMDConsulta.getText(),
                            txtPArterial.getText(),
                            txtFCardiaca.getText(),
                            txtTemperatura.getText(),
                            txtPYT.getText(),
                            txtMDConsulta.getText(),
                            txtEstudSolicitados.getText(),
                            txtAlergImporantes.getText(),
                            txtEPrevias.getText(),
                            txtMtosActuales.getText(),
                            txtRecomendaciones.getText(),
                            jcboxMedicamentosPreescrito.getSelectedItem().toString()
                    );

                    JOptionPane.showMessageDialog(this,
                            "Cita finalizada exitosamente.\nPDF generado en: " + rutaPDF,
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Abrir el PDF generado
                    try {
                        Desktop.getDesktop().open(new File(rutaPDF));
                    } catch (Exception e) {
                        System.out.println("No se pudo abrir el PDF automáticamente: " + e.getMessage());
                    }

                    // Actualizar contadores si es necesario
                    actualizarContadorCitas();

                    // Limpiar campos
                    limpiarCamposResultado();

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "La cita se guardó pero hubo un error al generar el PDF: " + e.getMessage(),
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al guardar el resultado médico",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonTerminarCitaActionPerformed

    private void txtSegundoApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSegundoApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoApellidoActionPerformed

    private void txtPrimerApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimerApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimerApellidoActionPerformed

    private void txtSegundoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSegundoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoNombreActionPerformed

    private void txtPrimerNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimerNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimerNombreActionPerformed

    private void buttonBuscarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarPacientesActionPerformed
        actualizarTablaPacientes();
    }//GEN-LAST:event_buttonBuscarPacientesActionPerformed

    private void txtBuscarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCitaActionPerformed

    private void LImpiarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LImpiarTextoActionPerformed
        limpiarCamposResultado();
    }//GEN-LAST:event_LImpiarTextoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Doctor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBusquedaDeFechaSelect;
    private com.toedter.calendar.JCalendar CalendarioDelMedico;
    private javax.swing.JTextField FechaQSeSelecciono;
    private javax.swing.JTextField InombreDelDoctorCargado;
    private javax.swing.JTextField InumeroDeConsultoriosDisp;
    private javax.swing.JButton LImpiarTexto;
    private javax.swing.JTable TablaVerPacientesBuscar;
    private javax.swing.JButton buttonBuscarCitaPorID;
    private javax.swing.JButton buttonBuscarPacienteReport;
    private javax.swing.JButton buttonBuscarPacientes;
    private javax.swing.JButton buttonCancelarReporte;
    private javax.swing.JButton buttonGenerarReporte;
    private javax.swing.JButton buttonLimpiarbusq;
    private javax.swing.JButton buttonTerminarCita;
    private javax.swing.JComboBox<String> cboBuscarNumeroIdentificacion;
    private javax.swing.JPanel fondoBlancoPrincipal;
    private javax.swing.JLabel iconAgendarCita;
    private javax.swing.JLabel iconAgregarPaciente;
    private javax.swing.JLabel iconCerrarSesion;
    private javax.swing.JLabel iconEditarPaciente;
    private javax.swing.JLabel iconHistorial;
    private javax.swing.JLabel iconInicio;
    private javax.swing.JLabel iconMedico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPaneDoctores;
    private javax.swing.JComboBox<String> jcboxMedicamentosPreescrito;
    private javax.swing.JComboBox<String> jcboxTipoDeCita;
    private javax.swing.JTextField lEspecializacionDocotorCarga;
    private javax.swing.JLabel labelAgregarPaciente;
    private javax.swing.JLabel labelApartadoDe;
    private javax.swing.JLabel labelCargarEPS;
    private javax.swing.JLabel labelCargarNombre;
    private javax.swing.JLabel labelCartagenaDeIndias;
    private javax.swing.JLabel labelCerrarSesion;
    private javax.swing.JLabel labelCitaIdentificacion;
    private javax.swing.JLabel labelDatoQueDesea;
    private javax.swing.JLabel labelDigiteLaIdentificacion;
    private javax.swing.JLabel labelEditarPaciente;
    private javax.swing.JLabel labelEditarPaciente1;
    private javax.swing.JLabel labelEncontrado;
    private javax.swing.JLabel labelEncontrado1;
    private javax.swing.JLabel labelInformación;
    private javax.swing.JLabel labelInformación1;
    private javax.swing.JLabel labelInformaciónCita;
    private javax.swing.JLabel labelJaoSalud;
    private javax.swing.JLabel labelMenúOpciones;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelObligatorio;
    private javax.swing.JLabel labelObligatorioID;
    private javax.swing.JLabel labelPrimerApellido;
    private javax.swing.JPanel labelPrimerNombre;
    private javax.swing.JLabel labelPrimerrNombre;
    private javax.swing.JLabel labelRecepcionisa;
    private javax.swing.JLabel labelSegundoApellido;
    private javax.swing.JLabel labelSegundoNombre;
    private javax.swing.JLabel labelTabbedAgregarPaciente;
    private javax.swing.JLabel labelTabbedEditarCita;
    private javax.swing.JLabel labelTablaDeCita;
    private javax.swing.JLabel labelTablaDeCita1;
    private javax.swing.JLabel labelVerHistoriales;
    private javax.swing.JLabel lblConsultorio;
    private javax.swing.JLabel lblDatosCitas;
    private javax.swing.JLabel lblDatosCitas1;
    private javax.swing.JLabel lblDocPaciente;
    private javax.swing.JLabel lblFechaaCita;
    private javax.swing.JLabel lblNombreDoctor;
    private javax.swing.JLabel lblNombreDoctor1;
    private javax.swing.JLabel lblNombreDoctor10;
    private javax.swing.JLabel lblNombreDoctor11;
    private javax.swing.JLabel lblNombreDoctor12;
    private javax.swing.JLabel lblNombreDoctor2;
    private javax.swing.JLabel lblNombreDoctor3;
    private javax.swing.JLabel lblNombreDoctor4;
    private javax.swing.JLabel lblNombreDoctor5;
    private javax.swing.JLabel lblNombreDoctor6;
    private javax.swing.JLabel lblNombreDoctor7;
    private javax.swing.JLabel lblNombreDoctor8;
    private javax.swing.JLabel lblNombreDoctor9;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblRealodInicio;
    private javax.swing.JTextField nroDCitasAtendidasDia;
    private javax.swing.JTextField numeroDcitasEnElDiaDoctor;
    private javax.swing.JPanel panelAtenderCitaMedica;
    private javax.swing.JPanel panelAzul;
    private javax.swing.JPanel panelBtnAtenderCitas;
    private javax.swing.JPanel panelBtnCerrarSesion;
    private javax.swing.JPanel panelBtnCrearReporte;
    private javax.swing.JPanel panelBtnInicio;
    private javax.swing.JPanel panelBtnVerCita;
    private javax.swing.JPanel panelBtnVerPaciente;
    private javax.swing.JPanel panelCrearReporte;
    private javax.swing.JPanel panelDeInicio;
    private javax.swing.JPanel panelDecoración;
    private javax.swing.JPanel panelDiv;
    private javax.swing.JPanel panelDivisionCrearReporte;
    private javax.swing.JPanel panelFondoBlancoAtenderCitaMedica;
    private javax.swing.JPanel panelFondoBlancoCrearReporte;
    private javax.swing.JPanel panelFondoBlancoInicio;
    private javax.swing.JLabel panelTabbedCrearReporte;
    private javax.swing.JPanel panelVerCitas;
    private javax.swing.JPanel panelVerPaciente;
    private javax.swing.JTable tablaCitasDelDia;
    private javax.swing.JTextArea txtAlergImporantes;
    private javax.swing.JTextField txtBuscarCita;
    private javax.swing.JTextField txtBuscarNumeroIdentificacion;
    private javax.swing.JTextField txtCargarEPSReport;
    private javax.swing.JTextField txtCargarNPaciente;
    private javax.swing.JTextField txtCargarPrimerApellidoReport;
    private javax.swing.JTextField txtCargarPrimerNombreReport;
    private javax.swing.JTextField txtConsultorioAsignadoo;
    private javax.swing.JTextField txtDocPaciente;
    private javax.swing.JTextArea txtEPrevias;
    private javax.swing.JTextArea txtEstudSolicitados;
    private javax.swing.JTextArea txtFCardiaca;
    private javax.swing.JTextField txtFechaCitaV;
    private javax.swing.JTextArea txtMDConsulta;
    private javax.swing.JTextArea txtMtosActuales;
    private javax.swing.JTextField txtNomDoctor;
    private javax.swing.JTextArea txtPArterial;
    private javax.swing.JTextArea txtPYT;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextArea txtRecomendaciones;
    private javax.swing.JTextArea txtReporteAgregado;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    private javax.swing.JTextArea txtTemperatura;
    // End of variables declaration//GEN-END:variables
}
