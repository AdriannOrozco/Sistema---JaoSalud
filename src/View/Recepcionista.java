package View;

import Model.Paciente;
import Comandos.ComandoCancelarCita;
import Comandos.ComandoActualizarPaciente;
import Comandos.ComandoAgendarCita;
import Comandos.ComandoCrearPaciente;
import Comandos.ComandoEliminarPaciente;
import Comandos.IActualizarPaciente;
import Comandos.IAgendarCita;
import Comandos.ICancelarCita;
import Comandos.ICrearPaciente;
import Comandos.IEliminarPaciente;
import Model.Cita;
import Persistencia.Citas.CargarDatosCitaId.CargarDatosCita;
import Persistencia.Citas.CargarMotivo.CargarMotivo;
import Persistencia.Citas.MostrarTablasCita.MostrarTablasCita;
import Persistencia.Citas.VerificarCitaId.VerificarCitaId;
import Persistencia.Consultorios.BuscarIdConsultorioPorConsultorio.BuscarIdConsultorioPorConsultorio;
import Persistencia.Consultorios.CargarConsultoriosCbo.CargarConsultoriosCbo;
import Persistencia.Consultorios.FiltrarConsultoriosPorDoctor.FiltrarConsultoriosPorDoctor;
import Persistencia.Doctor.BuscarIdPorNombreDoctor.BuscarIdPorNombreDoctor;
import Persistencia.Doctor.CargarDoctoresCbo.CargarDoctoresCbo;
import Persistencia.Doctor.CargarNombreEspecialidad.CargarNombreEspecialidad;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Date;
import java.util.Random;
import Persistencia.Paciente.BuscarPacientePorID.BuscarPacientePorId;
import Persistencia.Paciente.CargarDatosPaciente.CargarDatosPacientes;
import Persistencia.Paciente.CargarNombreDelPaciente.CargarNombreDelPaciente;
import Persistencia.Paciente.VerificarPaciente.VerificarPacienteEnSistema;
import Persistencia.MetodosUtiles.MetodosCadenasDeTexto;

import Persistencia.Paciente.PreguntarExisteDocumento.PreguntarExisteDocumento;

public class Recepcionista extends javax.swing.JFrame {

    //Persistencias
    BuscarPacientePorId buscarID = new BuscarPacientePorId();
    CargarNombreDelPaciente cargar2 = new CargarNombreDelPaciente();
    VerificarPacienteEnSistema verificar = new VerificarPacienteEnSistema();
    MetodosCadenasDeTexto metodo = new MetodosCadenasDeTexto();
    CargarDoctoresCbo cargarCboDoctores = new CargarDoctoresCbo();
    CargarConsultoriosCbo cargarCboConsulotios = new CargarConsultoriosCbo();
    MostrarTablasCita mostrarCitasEnTabla = new MostrarTablasCita();
    CargarDatosPacientes cargarDatosPacientes = new CargarDatosPacientes();
    PreguntarExisteDocumento existe = new PreguntarExisteDocumento();
    BuscarIdPorNombreDoctor buscarDoctorId = new BuscarIdPorNombreDoctor();
    CargarNombreEspecialidad cargarEspecialidadNombreDoc = new CargarNombreEspecialidad();
    VerificarCitaId verificarCita = new VerificarCitaId();
    CargarDatosCita cargarADatosCita = new CargarDatosCita();
    Paciente paciente = new Paciente();

    DefaultTableModel modeloC = new DefaultTableModel();

    public Recepcionista() {
        initComponents();
        setLocationRelativeTo(null);
        SeccionEditarDatoPaciente();
        ValidarCalendarioFechaNacimiento();
        validarCalendarioCitas();
        ocultarSeccionEditarCita();
        cargarCboConsulotios.CargarConsultorios(cboConsultorio, "consultorio");
        cargarCboDoctores.CargarDoctores(cboDoctor, "primerNombre", "primerApellido");
        mostrarCitasEnTabla.MostrarCitas(tablaCitas);
    }

    public void validarCalendarioCitas() {
        Calendar minDate = Calendar.getInstance();
        minDate.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        dateChooserFechaCita.setMinSelectableDate(minDate.getTime());

        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2025, Calendar.DECEMBER, 31, 23, 59, 59);
        dateChooserFechaCita.setMaxSelectableDate(maxDate.getTime());

        Calendar defaultDate = Calendar.getInstance();
        defaultDate.set(2025, Calendar.JANUARY, 1);
        dateChooserFechaCita.setDate(defaultDate.getTime());
    }

    public void ValidarCalendarioFechaNacimiento() {
        Calendar maxDate = Calendar.getInstance();
        maxDate.set(2025, Calendar.DECEMBER, 31, 23, 59, 59);
        dateChooserFechaNacimiento.setMaxSelectableDate(maxDate.getTime());
    }

    public void SeccionEditarDatoPaciente() {
        //Apartado de citas
        panelDivisionEditarPaciente.setVisible(false);
        labelEncontrado.setVisible(false);
        labelDatoQueDesea.setVisible(false);
        labelModificarDato.setVisible(false);
        cboSeleccionarDato.setVisible(false);
        labelDigiteActualizacion.setVisible(false);
        labelCargarNombre.setVisible(false);
        txtActualizarDato.setVisible(false);
        buttonActualizar.setVisible(false);
        buttonCancelarModificacion.setVisible(false);
        labelCargarEPS.setVisible(false);
        txtCargarEPS.setVisible(false);
        labelCargarNombre.setVisible(false);
        txtCargarPrimerNombre.setVisible(false);
        txtCargarPrimerApellido.setVisible(false);
        buttonCancelarModificacion.setVisible(false);
        buttonEliminarPaciente.setVisible(false);
        labelEstadoCivil1.setVisible(false);
        cboEstadoCivil1.setVisible(false);
        cboTipoIdentificacion1.setVisible(false);
        labelTipoIdentificación1.setVisible(false);
        cboEPS1.setVisible(false);
        labelEpsPaciente1.setVisible(false);
    }

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
        panelBtnAgregarPaciente = new javax.swing.JPanel();
        iconAgregarPaciente = new javax.swing.JLabel();
        labelAgregarPaciente = new javax.swing.JLabel();
        labelMenúOpciones = new javax.swing.JLabel();
        panelBtnEditarPaciente = new javax.swing.JPanel();
        iconEditarPaciente = new javax.swing.JLabel();
        labelEditarPaciente = new javax.swing.JLabel();
        panelBtnAgendarCita = new javax.swing.JPanel();
        iconAgendarCita = new javax.swing.JLabel();
        labelEditarPaciente1 = new javax.swing.JLabel();
        panelBtnEditarCita = new javax.swing.JPanel();
        iconEditarCita = new javax.swing.JLabel();
        labelEditarCita = new javax.swing.JLabel();
        panelBtnHistoriales = new javax.swing.JPanel();
        iconHistorial = new javax.swing.JLabel();
        labelVerHistoriales = new javax.swing.JLabel();
        panelBtnCerrarSesion = new javax.swing.JPanel();
        iconCerrarSesion = new javax.swing.JLabel();
        labelCerrarSesion = new javax.swing.JLabel();
        iconRecepcinista = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelDeInicio = new javax.swing.JPanel();
        panelFondoBlancoInicio = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCitas = new javax.swing.JTable();
        labelTablaDeCita = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarMedicoId = new javax.swing.JTextField();
        buttonOkMedicos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCargarNombreMedico = new javax.swing.JTextField();
        txtCargarEspecialidad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscarMotivoID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCargarMotivo = new javax.swing.JTextArea();
        buttonOkCargarMotivos = new javax.swing.JButton();
        lblRealodInicio = new javax.swing.JLabel();
        panelAgregarPaciente = new javax.swing.JPanel();
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
        labelIdentificacion = new javax.swing.JLabel();
        labelSegundoApellido = new javax.swing.JLabel();
        labelTipoIdentificación = new javax.swing.JLabel();
        cboTipoIdentificacion = new javax.swing.JComboBox<>();
        labelNumeroIdentificacion = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();
        labelContacto = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        panelSeparaciónPaciente2 = new javax.swing.JPanel();
        labelDatosDelPaciente = new javax.swing.JLabel();
        labelDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        labelEstadoCivil = new javax.swing.JLabel();
        cboEstadoCivil = new javax.swing.JComboBox<>();
        labelGeneroPaciente = new javax.swing.JLabel();
        cboGenero = new javax.swing.JComboBox<>();
        labelTipoSangre = new javax.swing.JLabel();
        cboTipoSangre = new javax.swing.JComboBox<>();
        panelSeparaciónPaciente = new javax.swing.JPanel();
        labelRestoDeInfo = new javax.swing.JLabel();
        labelFechaNacimiento = new javax.swing.JLabel();
        labelEpsPaciente = new javax.swing.JLabel();
        cboEPS = new javax.swing.JComboBox<>();
        labelEdad = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        buttonAgregar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        dateChooserFechaNacimiento = new com.toedter.calendar.JDateChooser();
        panelEditarPaciente = new javax.swing.JPanel();
        panelFondoBlancoEditarPaciente = new javax.swing.JPanel();
        panelTabbedEditarPaciente = new javax.swing.JLabel();
        labelInformación = new javax.swing.JLabel();
        labelInformación1 = new javax.swing.JLabel();
        labelDigiteLaIdentificacion = new javax.swing.JLabel();
        txtBuscarNumeroIdentificacion = new javax.swing.JTextField();
        cboBuscarNumeroIdentificacion = new javax.swing.JComboBox<>();
        buttonBuscar = new javax.swing.JButton();
        buttonCancelarBusq = new javax.swing.JButton();
        panelDivisionEditarPaciente = new javax.swing.JPanel();
        labelEncontrado = new javax.swing.JLabel();
        labelDatoQueDesea = new javax.swing.JLabel();
        cboSeleccionarDato = new javax.swing.JComboBox<>();
        labelModificarDato = new javax.swing.JLabel();
        labelDigiteActualizacion = new javax.swing.JLabel();
        txtActualizarDato = new javax.swing.JTextField();
        buttonActualizar = new javax.swing.JButton();
        buttonCancelarModificacion = new javax.swing.JButton();
        labelObligatorio = new javax.swing.JLabel();
        txtCargarEPS = new javax.swing.JTextField();
        labelCargarEPS = new javax.swing.JLabel();
        labelCargarNombre = new javax.swing.JLabel();
        txtCargarPrimerNombre = new javax.swing.JTextField();
        txtCargarPrimerApellido = new javax.swing.JTextField();
        buttonEliminarPaciente = new javax.swing.JButton();
        labelEstadoCivil1 = new javax.swing.JLabel();
        cboEstadoCivil1 = new javax.swing.JComboBox<>();
        cboTipoIdentificacion1 = new javax.swing.JComboBox<>();
        labelTipoIdentificación1 = new javax.swing.JLabel();
        labelEpsPaciente1 = new javax.swing.JLabel();
        cboEPS1 = new javax.swing.JComboBox<>();
        panelAgendarCita = new javax.swing.JPanel();
        panelFondoBlancoAgendarCita = new javax.swing.JPanel();
        labelTabbedAgendarCita = new javax.swing.JLabel();
        labelConsultorio = new javax.swing.JLabel();
        cboConsultorio = new javax.swing.JComboBox<>();
        labelSeleccioneDoctor = new javax.swing.JLabel();
        cboDoctor = new javax.swing.JComboBox<>();
        labelMotivoDeLaCita = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMotivo = new javax.swing.JTextArea();
        labelHoraCita = new javax.swing.JLabel();
        cboHoraCita = new javax.swing.JComboBox<>();
        buttonAgendar = new javax.swing.JButton();
        buttonReiniciar = new javax.swing.JButton();
        labelNumeroAgenda = new javax.swing.JLabel();
        txtIdentificacionCita = new javax.swing.JTextField();
        labelEsAutomatico = new javax.swing.JLabel();
        buttonVerMedicos = new javax.swing.JButton();
        labelIdentificacionDelPaciente = new javax.swing.JLabel();
        txtIdentificacionPaciente = new javax.swing.JTextField();
        labelNombreDelPaciente = new javax.swing.JLabel();
        txtCampoNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dateChooserFechaCita = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        buttonCargarPaciente = new javax.swing.JButton();
        panelEditar = new javax.swing.JPanel();
        panelFondoBlancoEditarCita = new javax.swing.JPanel();
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
        txtHoraaCita = new javax.swing.JTextField();
        txtFechaCitaV = new javax.swing.JTextField();
        lblHoraCita = new javax.swing.JLabel();
        lblFechaaCita = new javax.swing.JLabel();
        lblNombreDoctor = new javax.swing.JLabel();
        txtNomDoctor = new javax.swing.JTextField();
        lblConsultorio = new javax.swing.JLabel();
        txtConsultorioAsignadoo = new javax.swing.JTextField();
        buttonCancelarCita = new javax.swing.JButton();
        buttonConsultorios = new javax.swing.JButton();
        buttonMedicos = new javax.swing.JButton();
        panelHistorialMédico = new javax.swing.JPanel();
        panelFondoBlancoHistorialMedico = new javax.swing.JPanel();
        labelTabbedHistorialMédico = new javax.swing.JLabel();
        labelJaoSalud = new javax.swing.JLabel();
        labelCartagenaDeIndias = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondoBlancoPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        fondoBlancoPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAzul.setBackground(new java.awt.Color(0, 51, 102));

        labelApartadoDe.setBackground(new java.awt.Color(255, 255, 255));
        labelApartadoDe.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 30)); // NOI18N
        labelApartadoDe.setForeground(new java.awt.Color(255, 255, 255));
        labelApartadoDe.setText("APARTADO DE ");

        labelRecepcionisa.setBackground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 30)); // NOI18N
        labelRecepcionisa.setForeground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa.setText("RECEPCIONISTA");

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
                .addContainerGap()
                .addComponent(iconInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        panelBtnAgregarPaciente.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnAgregarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnAgregarPacienteMouseClicked(evt);
            }
        });

        iconAgregarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/agregarPaciente.png"))); // NOI18N

        labelAgregarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelAgregarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        labelAgregarPaciente.setText("AGREGAR PACIENTE");

        javax.swing.GroupLayout panelBtnAgregarPacienteLayout = new javax.swing.GroupLayout(panelBtnAgregarPaciente);
        panelBtnAgregarPaciente.setLayout(panelBtnAgregarPacienteLayout);
        panelBtnAgregarPacienteLayout.setHorizontalGroup(
            panelBtnAgregarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgregarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelAgregarPaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnAgregarPacienteLayout.setVerticalGroup(
            panelBtnAgregarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnAgregarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelMenúOpciones.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        labelMenúOpciones.setForeground(new java.awt.Color(255, 255, 255));
        labelMenúOpciones.setText("MENÚ DE OPCIONES");

        panelBtnEditarPaciente.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnEditarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnEditarPacienteMouseClicked(evt);
            }
        });

        iconEditarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/editarPaciente.png"))); // NOI18N

        labelEditarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente.setText("EDITAR PACIENTE");

        javax.swing.GroupLayout panelBtnEditarPacienteLayout = new javax.swing.GroupLayout(panelBtnEditarPaciente);
        panelBtnEditarPaciente.setLayout(panelBtnEditarPacienteLayout);
        panelBtnEditarPacienteLayout.setHorizontalGroup(
            panelBtnEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEditarPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconEditarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(labelEditarPaciente)
                .addGap(31, 31, 31))
        );
        panelBtnEditarPacienteLayout.setVerticalGroup(
            panelBtnEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEditarPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnAgendarCita.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnAgendarCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnAgendarCitaMouseClicked(evt);
            }
        });

        iconAgendarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/agendarCita.png"))); // NOI18N

        labelEditarPaciente1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente1.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente1.setText("AGENDAR CITA");

        javax.swing.GroupLayout panelBtnAgendarCitaLayout = new javax.swing.GroupLayout(panelBtnAgendarCita);
        panelBtnAgendarCita.setLayout(panelBtnAgendarCitaLayout);
        panelBtnAgendarCitaLayout.setHorizontalGroup(
            panelBtnAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgendarCitaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgendarCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEditarPaciente1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnAgendarCitaLayout.setVerticalGroup(
            panelBtnAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgendarCitaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEditarPaciente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnEditarCita.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnEditarCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnEditarCitaMouseClicked(evt);
            }
        });

        iconEditarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/editarCita.png"))); // NOI18N

        labelEditarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarCita.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarCita.setText("EDITAR CITA");

        javax.swing.GroupLayout panelBtnEditarCitaLayout = new javax.swing.GroupLayout(panelBtnEditarCita);
        panelBtnEditarCita.setLayout(panelBtnEditarCitaLayout);
        panelBtnEditarCitaLayout.setHorizontalGroup(
            panelBtnEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEditarCitaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconEditarCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEditarCita)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnEditarCitaLayout.setVerticalGroup(
            panelBtnEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEditarCitaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconEditarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEditarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnHistoriales.setBackground(new java.awt.Color(0, 51, 130));
        panelBtnHistoriales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnHistorialesMouseClicked(evt);
            }
        });

        iconHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/verInformes.png"))); // NOI18N

        labelVerHistoriales.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelVerHistoriales.setForeground(new java.awt.Color(255, 255, 255));
        labelVerHistoriales.setText("VER HISTORIALES");

        javax.swing.GroupLayout panelBtnHistorialesLayout = new javax.swing.GroupLayout(panelBtnHistoriales);
        panelBtnHistoriales.setLayout(panelBtnHistorialesLayout);
        panelBtnHistorialesLayout.setHorizontalGroup(
            panelBtnHistorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnHistorialesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconHistorial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelVerHistoriales)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnHistorialesLayout.setVerticalGroup(
            panelBtnHistorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnHistorialesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnHistorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                .addContainerGap()
                .addComponent(iconCerrarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnCerrarSesionLayout.setVerticalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        iconRecepcinista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fotoRecepcionista.png"))); // NOI18N

        javax.swing.GroupLayout panelAzulLayout = new javax.swing.GroupLayout(panelAzul);
        panelAzul.setLayout(panelAzulLayout);
        panelAzulLayout.setHorizontalGroup(
            panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnEditarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnHistoriales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAzulLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenúOpciones)
                .addGap(49, 49, 49))
            .addComponent(panelDecoración, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelAzulLayout.createSequentialGroup()
                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAzulLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelApartadoDe)
                            .addComponent(labelRecepcionisa)))
                    .addGroup(panelAzulLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(iconRecepcinista)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAzulLayout.setVerticalGroup(
            panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelApartadoDe, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelRecepcionisa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconRecepcinista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelDecoración, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMenúOpciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnAgregarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(panelBtnEditarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnEditarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnHistoriales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        fondoBlancoPrincipal.add(panelAzul, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 660));

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setForeground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        fondoBlancoPrincipal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 690, 40));

        panelFondoBlancoInicio.setBackground(new java.awt.Color(255, 255, 255));

        tablaCitas.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tablaCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        tablaCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCitasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCitas);

        labelTablaDeCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTablaDeCita.setForeground(new java.awt.Color(0, 0, 102));
        labelTablaDeCita.setText("CITAS EN EL SISTEMA");

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/consultorioCita.png"))); // NOI18N
        jLabel2.setText("CONSULTAR MÉDICO CON SU ID:");

        txtBuscarMedicoId.setBackground(new java.awt.Color(225, 225, 225));
        txtBuscarMedicoId.setForeground(new java.awt.Color(0, 0, 0));

        buttonOkMedicos.setBackground(new java.awt.Color(0, 0, 153));
        buttonOkMedicos.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        buttonOkMedicos.setForeground(new java.awt.Color(255, 255, 255));
        buttonOkMedicos.setText("OK");
        buttonOkMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkMedicosActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("NOMBRE:");

        jLabel5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ESPECIALIDAD:");

        txtCargarNombreMedico.setEditable(false);
        txtCargarNombreMedico.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarNombreMedico.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarNombreMedico.setForeground(new java.awt.Color(0, 0, 0));

        txtCargarEspecialidad.setEditable(false);
        txtCargarEspecialidad.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarEspecialidad.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarEspecialidad.setForeground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/motivoCita.png"))); // NOI18N
        jLabel6.setText("CONSULTAR MOTIVO DE LA CITA:");

        txtBuscarMotivoID.setBackground(new java.awt.Color(225, 225, 225));
        txtBuscarMotivoID.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtBuscarMotivoID.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));

        txtCargarMotivo.setEditable(false);
        txtCargarMotivo.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarMotivo.setColumns(20);
        txtCargarMotivo.setRows(5);
        jScrollPane3.setViewportView(txtCargarMotivo);

        buttonOkCargarMotivos.setBackground(new java.awt.Color(0, 0, 153));
        buttonOkCargarMotivos.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        buttonOkCargarMotivos.setForeground(new java.awt.Color(255, 255, 255));
        buttonOkCargarMotivos.setText("OK");
        buttonOkCargarMotivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkCargarMotivosActionPerformed(evt);
            }
        });

        lblRealodInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/reiniciarInicio.png"))); // NOI18N
        lblRealodInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRealodInicioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelFondoBlancoInicioLayout = new javax.swing.GroupLayout(panelFondoBlancoInicio);
        panelFondoBlancoInicio.setLayout(panelFondoBlancoInicioLayout);
        panelFondoBlancoInicioLayout.setHorizontalGroup(
            panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCargarNombreMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCargarEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18))
                            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoBlancoInicioLayout.createSequentialGroup()
                                        .addComponent(txtBuscarMedicoId, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonOkMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoBlancoInicioLayout.createSequentialGroup()
                                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                                        .addComponent(txtBuscarMotivoID, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonOkCargarMotivos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(59, 59, 59)
                                .addComponent(jLabel7))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addComponent(lblRealodInicio)
                        .addGap(136, 136, 136)
                        .addComponent(labelTablaDeCita)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoInicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelFondoBlancoInicioLayout.setVerticalGroup(
            panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRealodInicio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTablaDeCita, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonOkMedicos)
                            .addComponent(txtBuscarMedicoId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCargarNombreMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCargarEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBuscarMotivoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonOkCargarMotivos)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout panelDeInicioLayout = new javax.swing.GroupLayout(panelDeInicio);
        panelDeInicio.setLayout(panelDeInicioLayout);
        panelDeInicioLayout.setHorizontalGroup(
            panelDeInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeInicioLayout.createSequentialGroup()
                .addComponent(panelFondoBlancoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDeInicioLayout.setVerticalGroup(
            panelDeInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inicio", panelDeInicio);

        labelPrimerNombre.setBackground(new java.awt.Color(255, 255, 255));

        labelTabbedAgregarPaciente.setBackground(new java.awt.Color(0, 51, 102));
        labelTabbedAgregarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTabbedAgregarPaciente.setForeground(new java.awt.Color(0, 51, 102));
        labelTabbedAgregarPaciente.setText("AGREGAR PACIENTE ");

        labelNombre.setFont(new java.awt.Font("JetBrains Mono", 3, 14)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(0, 0, 0));
        labelNombre.setText("1. INTRODUZCA SU NOMBRE:");

        txtPrimerNombre.setBackground(new java.awt.Color(225, 225, 225));
        txtPrimerNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtPrimerNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtPrimerNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimerNombreActionPerformed(evt);
            }
        });

        labelPrimerrNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        labelPrimerrNombre.setForeground(new java.awt.Color(0, 0, 0));
        labelPrimerrNombre.setText("Primer nombre");

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

        labelIdentificacion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 3, 14)); // NOI18N
        labelIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        labelIdentificacion.setText("2. IDENTIFICACIÓN:");

        labelSegundoApellido.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        labelSegundoApellido.setForeground(new java.awt.Color(0, 0, 0));
        labelSegundoApellido.setText("Segundo Apellido");

        labelTipoIdentificación.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelTipoIdentificación.setForeground(new java.awt.Color(0, 0, 0));
        labelTipoIdentificación.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/tipoIdentificacionPaciente.png"))); // NOI18N
        labelTipoIdentificación.setText("Especifique el tipo:");

        cboTipoIdentificacion.setBackground(new java.awt.Color(225, 225, 225));
        cboTipoIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboTipoIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        cboTipoIdentificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "C.C", "T.I", "R.C", "C.E" }));

        labelNumeroIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelNumeroIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        labelNumeroIdentificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/numeroIdentificacionPaciente.png"))); // NOI18N
        labelNumeroIdentificacion.setText("Número de identificación");

        txtNumeroDocumento.setBackground(new java.awt.Color(225, 225, 225));
        txtNumeroDocumento.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtNumeroDocumento.setForeground(new java.awt.Color(0, 0, 0));

        labelContacto.setFont(new java.awt.Font("JetBrains Mono", 3, 14)); // NOI18N
        labelContacto.setForeground(new java.awt.Color(0, 0, 0));
        labelContacto.setText("3. CONTACTO DEL PACIENTE");

        txtEmail.setBackground(new java.awt.Color(225, 225, 225));
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        labelEmail.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(0, 0, 0));
        labelEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/emailPaciente.png"))); // NOI18N
        labelEmail.setText("CORREO ELECTRÓNICO:");

        labelTelefono.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelTelefono.setForeground(new java.awt.Color(0, 0, 0));
        labelTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/telefonoPaciente.png"))); // NOI18N
        labelTelefono.setText("TELÉFONO DE LA PERSONA:");

        txtTelefono.setBackground(new java.awt.Color(225, 225, 225));
        txtTelefono.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));

        panelSeparaciónPaciente2.setBackground(new java.awt.Color(232, 232, 232));

        javax.swing.GroupLayout panelSeparaciónPaciente2Layout = new javax.swing.GroupLayout(panelSeparaciónPaciente2);
        panelSeparaciónPaciente2.setLayout(panelSeparaciónPaciente2Layout);
        panelSeparaciónPaciente2Layout.setHorizontalGroup(
            panelSeparaciónPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        panelSeparaciónPaciente2Layout.setVerticalGroup(
            panelSeparaciónPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        labelDatosDelPaciente.setBackground(new java.awt.Color(0, 0, 0));
        labelDatosDelPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 3, 14)); // NOI18N
        labelDatosDelPaciente.setForeground(new java.awt.Color(0, 0, 0));
        labelDatosDelPaciente.setText("4. DATOS DEL PACIENTE:");

        labelDireccion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelDireccion.setForeground(new java.awt.Color(0, 0, 0));
        labelDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/direccionPaciente.png"))); // NOI18N
        labelDireccion.setText("Dirección del paciente:");

        txtDireccion.setBackground(new java.awt.Color(225, 225, 225));
        txtDireccion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(0, 0, 0));

        labelEstadoCivil.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelEstadoCivil.setForeground(new java.awt.Color(0, 0, 0));
        labelEstadoCivil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/estadoCivilPaciente.png"))); // NOI18N
        labelEstadoCivil.setText("Estado civil:");

        cboEstadoCivil.setBackground(new java.awt.Color(225, 225, 225));
        cboEstadoCivil.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboEstadoCivil.setForeground(new java.awt.Color(0, 0, 0));
        cboEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Casado/a", "Soltero/a" }));

        labelGeneroPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelGeneroPaciente.setForeground(new java.awt.Color(0, 0, 0));
        labelGeneroPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/generoPaciente.png"))); // NOI18N
        labelGeneroPaciente.setText("Especifique el genero:");

        cboGenero.setBackground(new java.awt.Color(225, 225, 225));
        cboGenero.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboGenero.setForeground(new java.awt.Color(0, 0, 0));
        cboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino" }));

        labelTipoSangre.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelTipoSangre.setForeground(new java.awt.Color(0, 0, 0));
        labelTipoSangre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/tipoSangrePaciente.png"))); // NOI18N
        labelTipoSangre.setText("Tipo de sangre:");

        cboTipoSangre.setBackground(new java.awt.Color(225, 225, 225));
        cboTipoSangre.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboTipoSangre.setForeground(new java.awt.Color(0, 0, 0));
        cboTipoSangre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));

        panelSeparaciónPaciente.setBackground(new java.awt.Color(232, 232, 232));

        javax.swing.GroupLayout panelSeparaciónPacienteLayout = new javax.swing.GroupLayout(panelSeparaciónPaciente);
        panelSeparaciónPaciente.setLayout(panelSeparaciónPacienteLayout);
        panelSeparaciónPacienteLayout.setHorizontalGroup(
            panelSeparaciónPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        panelSeparaciónPacienteLayout.setVerticalGroup(
            panelSeparaciónPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        labelRestoDeInfo.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 3, 14)); // NOI18N
        labelRestoDeInfo.setForeground(new java.awt.Color(0, 0, 0));
        labelRestoDeInfo.setText("5. RESTO DE INFORMACIÓN");

        labelFechaNacimiento.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelFechaNacimiento.setForeground(new java.awt.Color(0, 0, 0));
        labelFechaNacimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cumpleanosPaciente.png"))); // NOI18N
        labelFechaNacimiento.setText("Fecha de nacimiento:");

        labelEpsPaciente.setBackground(new java.awt.Color(0, 0, 0));
        labelEpsPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelEpsPaciente.setForeground(new java.awt.Color(0, 0, 0));
        labelEpsPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/EpsPaciente.png"))); // NOI18N
        labelEpsPaciente.setText("EPS de afiliación:");

        cboEPS.setBackground(new java.awt.Color(225, 225, 225));
        cboEPS.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboEPS.setForeground(new java.awt.Color(0, 0, 0));
        cboEPS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "SaludTotal", "CooSalud", "Mutual Ser" }));

        labelEdad.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelEdad.setForeground(new java.awt.Color(0, 0, 0));
        labelEdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/edadesPacientes.png"))); // NOI18N
        labelEdad.setText("Edad del paciente:");

        txtEdad.setBackground(new java.awt.Color(225, 225, 225));
        txtEdad.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtEdad.setForeground(new java.awt.Color(0, 0, 0));

        buttonAgregar.setBackground(new java.awt.Color(0, 0, 204));
        buttonAgregar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregar.setText("AGREGAR");
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarActionPerformed(evt);
            }
        });

        buttonCancelar.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonCancelar.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelar.setText("CANCELAR");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout labelPrimerNombreLayout = new javax.swing.GroupLayout(labelPrimerNombre);
        labelPrimerNombre.setLayout(labelPrimerNombreLayout);
        labelPrimerNombreLayout.setHorizontalGroup(
            labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombre)
                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                        .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(labelTipoIdentificación, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNumeroIdentificacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelIdentificacion, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelContacto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(cboTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelSeparaciónPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelDatosDelPaciente)
                                    .addComponent(txtDireccion)
                                    .addComponent(labelDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelEstadoCivil)
                                    .addComponent(cboEstadoCivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelGeneroPaciente)
                                    .addComponent(cboGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTipoSangre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelTipoSangre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelSeparaciónPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
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
                                    .addComponent(labelPrimerApellido))))
                        .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSegundoApellido)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(labelRestoDeInfo))
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelEpsPaciente)
                                    .addComponent(cboEPS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtEdad)))
                                    .addComponent(dateChooserFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonCancelar)
                                    .addComponent(buttonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelPrimerNombreLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTabbedAgregarPaciente)
                .addGap(227, 227, 227))
        );
        labelPrimerNombreLayout.setVerticalGroup(
            labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelTabbedAgregarPaciente)
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
                .addGap(18, 18, 18)
                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSeparaciónPaciente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSeparaciónPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                        .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addComponent(labelIdentificacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelTipoIdentificación)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelNumeroIdentificacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelContacto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTelefono)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addComponent(labelDatosDelPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelEstadoCivil)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelGeneroPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelTipoSangre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addComponent(labelRestoDeInfo)
                                .addGap(14, 14, 14)
                                .addComponent(labelFechaNacimiento)
                                .addGap(8, 8, 8)
                                .addComponent(dateChooserFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelEpsPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboEPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelEdad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonCancelar)))
                        .addGap(0, 59, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelAgregarPacienteLayout = new javax.swing.GroupLayout(panelAgregarPaciente);
        panelAgregarPaciente.setLayout(panelAgregarPacienteLayout);
        panelAgregarPacienteLayout.setHorizontalGroup(
            panelAgregarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelAgregarPacienteLayout.setVerticalGroup(
            panelAgregarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Agregar Paciente", panelAgregarPaciente);

        panelFondoBlancoEditarPaciente.setBackground(new java.awt.Color(255, 255, 255));

        panelTabbedEditarPaciente.setBackground(new java.awt.Color(0, 51, 102));
        panelTabbedEditarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        panelTabbedEditarPaciente.setForeground(new java.awt.Color(0, 51, 102));
        panelTabbedEditarPaciente.setText("EDITAR PACIENTES");

        labelInformación.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelInformación.setForeground(new java.awt.Color(0, 0, 0));
        labelInformación.setText("Actualización de datos si algún paciente realiza una modificación de sus datos u ocurre de");

        labelInformación1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelInformación1.setForeground(new java.awt.Color(0, 0, 0));
        labelInformación1.setText("de manera natural.");

        labelDigiteLaIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelDigiteLaIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        labelDigiteLaIdentificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscarPaciente.png"))); // NOI18N
        labelDigiteLaIdentificacion.setText("DIGITE LA IDENTIFICACIÓN PARA BUSCAR:");

        txtBuscarNumeroIdentificacion.setBackground(new java.awt.Color(225, 225, 225));
        txtBuscarNumeroIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtBuscarNumeroIdentificacion.setForeground(new java.awt.Color(0, 0, 0));

        cboBuscarNumeroIdentificacion.setBackground(new java.awt.Color(225, 225, 225));
        cboBuscarNumeroIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboBuscarNumeroIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        cboBuscarNumeroIdentificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "C.C", "T.I", "R.C", "C.E" }));

        buttonBuscar.setBackground(new java.awt.Color(0, 0, 204));
        buttonBuscar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        buttonBuscar.setForeground(new java.awt.Color(255, 255, 255));
        buttonBuscar.setText("BUSCAR");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        buttonCancelarBusq.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelarBusq.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        buttonCancelarBusq.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelarBusq.setText("CANCELAR");
        buttonCancelarBusq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarBusqActionPerformed(evt);
            }
        });

        panelDivisionEditarPaciente.setBackground(new java.awt.Color(232, 232, 232));

        javax.swing.GroupLayout panelDivisionEditarPacienteLayout = new javax.swing.GroupLayout(panelDivisionEditarPaciente);
        panelDivisionEditarPaciente.setLayout(panelDivisionEditarPacienteLayout);
        panelDivisionEditarPacienteLayout.setHorizontalGroup(
            panelDivisionEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDivisionEditarPacienteLayout.setVerticalGroup(
            panelDivisionEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        labelEncontrado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelEncontrado.setForeground(new java.awt.Color(0, 0, 0));
        labelEncontrado.setText("DATOS DEL CLIENTE:");

        labelDatoQueDesea.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelDatoQueDesea.setForeground(new java.awt.Color(0, 0, 0));

        cboSeleccionarDato.setBackground(new java.awt.Color(225, 225, 225));
        cboSeleccionarDato.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboSeleccionarDato.setForeground(new java.awt.Color(0, 0, 0));
        cboSeleccionarDato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Tipo de documento", "Teléfono", "Dirección de residencia", "Estado civil", "Email", "EPS", "Edad" }));
        cboSeleccionarDato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeleccionarDatoActionPerformed(evt);
            }
        });

        labelModificarDato.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelModificarDato.setForeground(new java.awt.Color(0, 0, 0));
        labelModificarDato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seleccionarDato.png"))); // NOI18N
        labelModificarDato.setText("SELECCIONE EL DATO PARA MODIFICAR:");

        labelDigiteActualizacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelDigiteActualizacion.setForeground(new java.awt.Color(0, 0, 0));
        labelDigiteActualizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/actualizarDato.png"))); // NOI18N
        labelDigiteActualizacion.setText("ACTUALICE EL DATO SELECCIONADO:");

        txtActualizarDato.setBackground(new java.awt.Color(225, 225, 225));
        txtActualizarDato.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtActualizarDato.setForeground(new java.awt.Color(0, 0, 0));

        buttonActualizar.setBackground(new java.awt.Color(0, 0, 204));
        buttonActualizar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        buttonActualizar.setText("ACTUALIZAR");
        buttonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActualizarActionPerformed(evt);
            }
        });

        buttonCancelarModificacion.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelarModificacion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonCancelarModificacion.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelarModificacion.setText("CANCELAR");
        buttonCancelarModificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarModificacionActionPerformed(evt);
            }
        });

        labelObligatorio.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelObligatorio.setForeground(new java.awt.Color(255, 0, 0));
        labelObligatorio.setText("*");

        txtCargarEPS.setEditable(false);
        txtCargarEPS.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarEPS.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarEPS.setForeground(new java.awt.Color(0, 0, 0));
        txtCargarEPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargarEPSActionPerformed(evt);
            }
        });

        labelCargarEPS.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelCargarEPS.setForeground(new java.awt.Color(0, 0, 0));
        labelCargarEPS.setText("| EPS:");

        labelCargarNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        labelCargarNombre.setForeground(new java.awt.Color(0, 0, 0));
        labelCargarNombre.setText("| NOMBRE:");

        txtCargarPrimerNombre.setEditable(false);
        txtCargarPrimerNombre.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarPrimerNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarPrimerNombre.setForeground(new java.awt.Color(0, 0, 0));

        txtCargarPrimerApellido.setEditable(false);
        txtCargarPrimerApellido.setBackground(new java.awt.Color(225, 225, 225));
        txtCargarPrimerApellido.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCargarPrimerApellido.setForeground(new java.awt.Color(0, 0, 0));

        buttonEliminarPaciente.setBackground(new java.awt.Color(0, 0, 0));
        buttonEliminarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonEliminarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        buttonEliminarPaciente.setText("ELIMINAR PACIENTE");
        buttonEliminarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarPacienteActionPerformed(evt);
            }
        });

        labelEstadoCivil1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelEstadoCivil1.setForeground(new java.awt.Color(0, 0, 0));
        labelEstadoCivil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/estadoCivilPaciente.png"))); // NOI18N
        labelEstadoCivil1.setText("Estado civil:");

        cboEstadoCivil1.setBackground(new java.awt.Color(225, 225, 225));
        cboEstadoCivil1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboEstadoCivil1.setForeground(new java.awt.Color(0, 0, 0));
        cboEstadoCivil1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Casado/a", "Soltero/a" }));

        cboTipoIdentificacion1.setBackground(new java.awt.Color(225, 225, 225));
        cboTipoIdentificacion1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboTipoIdentificacion1.setForeground(new java.awt.Color(0, 0, 0));
        cboTipoIdentificacion1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "C.C", "T.I", "R.C", "C.E" }));

        labelTipoIdentificación1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelTipoIdentificación1.setForeground(new java.awt.Color(0, 0, 0));
        labelTipoIdentificación1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/tipoIdentificacionPaciente.png"))); // NOI18N
        labelTipoIdentificación1.setText("Especifique el tipo:");

        labelEpsPaciente1.setBackground(new java.awt.Color(0, 0, 0));
        labelEpsPaciente1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelEpsPaciente1.setForeground(new java.awt.Color(0, 0, 0));
        labelEpsPaciente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/EpsPaciente.png"))); // NOI18N
        labelEpsPaciente1.setText("EPS de afiliación:");

        cboEPS1.setBackground(new java.awt.Color(225, 225, 225));
        cboEPS1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboEPS1.setForeground(new java.awt.Color(0, 0, 0));
        cboEPS1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "SaludTotal", "CooSalud", "Mutual Ser" }));

        javax.swing.GroupLayout panelFondoBlancoEditarPacienteLayout = new javax.swing.GroupLayout(panelFondoBlancoEditarPaciente);
        panelFondoBlancoEditarPaciente.setLayout(panelFondoBlancoEditarPacienteLayout);
        panelFondoBlancoEditarPacienteLayout.setHorizontalGroup(
            panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDivisionEditarPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEncontrado)
                                    .addComponent(labelDatoQueDesea))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                        .addComponent(labelCargarEPS)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCargarEPS))
                                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                        .addComponent(labelCargarNombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCargarPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCargarPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(panelTabbedEditarPaciente))
                            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelInformación)
                                    .addComponent(labelInformación1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                        .addGap(163, 163, 163)
                                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelDigiteLaIdentificacion)
                                            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                                        .addComponent(buttonBuscar)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(buttonCancelarBusq))
                                                    .addComponent(txtBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cboBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(labelObligatorio))))))
                            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSeleccionarDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelModificarDato)
                                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEstadoCivil1)
                                            .addComponent(cboEstadoCivil1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelEpsPaciente1)
                                            .addComponent(cboEPS1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(labelTipoIdentificación1)
                                    .addComponent(cboTipoIdentificacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDigiteActualizacion)
                                    .addComponent(txtActualizarDato, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                                        .addComponent(buttonActualizar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonCancelarModificacion))
                                    .addComponent(buttonEliminarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelFondoBlancoEditarPacienteLayout.setVerticalGroup(
            panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelTabbedEditarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInformación)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInformación1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDigiteLaIdentificacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBuscarNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelObligatorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelarBusq)
                    .addComponent(buttonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDivisionEditarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCargarEPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelEncontrado)
                        .addComponent(labelCargarEPS)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDatoQueDesea)
                    .addComponent(labelCargarNombre)
                    .addComponent(txtCargarPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCargarPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(labelDigiteActualizacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtActualizarDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelModificarDato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSeleccionarDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEstadoCivil1)
                            .addComponent(labelEpsPaciente1))))
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonCancelarModificacion)
                            .addComponent(buttonActualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminarPaciente))
                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboEstadoCivil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboEPS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTipoIdentificación1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoIdentificacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelEditarPacienteLayout = new javax.swing.GroupLayout(panelEditarPaciente);
        panelEditarPaciente.setLayout(panelEditarPacienteLayout);
        panelEditarPacienteLayout.setHorizontalGroup(
            panelEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelEditarPacienteLayout.setVerticalGroup(
            panelEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Editar Paciente", panelEditarPaciente);

        panelFondoBlancoAgendarCita.setBackground(new java.awt.Color(255, 255, 255));

        labelTabbedAgendarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTabbedAgendarCita.setForeground(new java.awt.Color(0, 51, 102));
        labelTabbedAgendarCita.setText("AGENDAR CITA");

        labelConsultorio.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelConsultorio.setForeground(new java.awt.Color(0, 0, 0));
        labelConsultorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/consultorioCita.png"))); // NOI18N
        labelConsultorio.setText("SELECCIONE EL CONSULTORIO:");

        cboConsultorio.setBackground(new java.awt.Color(225, 225, 225));
        cboConsultorio.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboConsultorio.setForeground(new java.awt.Color(0, 0, 0));
        cboConsultorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));

        labelSeleccioneDoctor.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelSeleccioneDoctor.setForeground(new java.awt.Color(0, 0, 0));
        labelSeleccioneDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/doctoresCita.png"))); // NOI18N
        labelSeleccioneDoctor.setText("SELECCIONE EL DOCTOR:");

        cboDoctor.setBackground(new java.awt.Color(225, 225, 225));
        cboDoctor.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboDoctor.setForeground(new java.awt.Color(0, 0, 0));
        cboDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        cboDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDoctorActionPerformed(evt);
            }
        });

        labelMotivoDeLaCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelMotivoDeLaCita.setForeground(new java.awt.Color(0, 0, 0));
        labelMotivoDeLaCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/motivoCita.png"))); // NOI18N
        labelMotivoDeLaCita.setText("¿CUÁL ES EL MOTIVO DE LA CITA?");

        txtAreaMotivo.setBackground(new java.awt.Color(225, 225, 225));
        txtAreaMotivo.setColumns(20);
        txtAreaMotivo.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtAreaMotivo.setRows(5);
        jScrollPane1.setViewportView(txtAreaMotivo);

        labelHoraCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelHoraCita.setForeground(new java.awt.Color(0, 0, 0));
        labelHoraCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/horaCita.png"))); // NOI18N
        labelHoraCita.setText("SELECCIONE LA HORA:");

        cboHoraCita.setBackground(new java.awt.Color(225, 225, 225));
        cboHoraCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboHoraCita.setForeground(new java.awt.Color(0, 0, 0));
        cboHoraCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "7:00", "8:00 ", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" }));

        buttonAgendar.setBackground(new java.awt.Color(0, 0, 201));
        buttonAgendar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonAgendar.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgendar.setText("AGENDAR CITA");
        buttonAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgendarActionPerformed(evt);
            }
        });

        buttonReiniciar.setBackground(new java.awt.Color(0, 0, 0));
        buttonReiniciar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonReiniciar.setForeground(new java.awt.Color(255, 255, 255));
        buttonReiniciar.setText("REINICIAR");
        buttonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReiniciarActionPerformed(evt);
            }
        });

        labelNumeroAgenda.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelNumeroAgenda.setForeground(new java.awt.Color(0, 0, 0));
        labelNumeroAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/idCita.png"))); // NOI18N
        labelNumeroAgenda.setText("IDENTIFICACIÓN DE LA CITA:");

        txtIdentificacionCita.setEditable(false);
        txtIdentificacionCita.setBackground(new java.awt.Color(225, 225, 225));
        txtIdentificacionCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtIdentificacionCita.setForeground(new java.awt.Color(0, 0, 0));

        labelEsAutomatico.setFont(new java.awt.Font("JetBrains Mono", 0, 8)); // NOI18N
        labelEsAutomatico.setForeground(new java.awt.Color(204, 0, 0));
        labelEsAutomatico.setText("* AUTOMÁTICO");

        buttonVerMedicos.setBackground(new java.awt.Color(255, 255, 255));
        buttonVerMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/verMedicos.png"))); // NOI18N
        buttonVerMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerMedicosActionPerformed(evt);
            }
        });

        labelIdentificacionDelPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelIdentificacionDelPaciente.setForeground(new java.awt.Color(0, 0, 0));
        labelIdentificacionDelPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/numeroIdentificacionPaciente.png"))); // NOI18N
        labelIdentificacionDelPaciente.setText("IDENTIFICACIÓN DEL PACIENTE:");

        txtIdentificacionPaciente.setBackground(new java.awt.Color(225, 225, 225));
        txtIdentificacionPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtIdentificacionPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtIdentificacionPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionPacienteActionPerformed(evt);
            }
        });

        labelNombreDelPaciente.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelNombreDelPaciente.setForeground(new java.awt.Color(0, 0, 0));
        labelNombreDelPaciente.setText("NOMBRE DEL PACIENTE:");

        txtCampoNombre.setEditable(false);
        txtCampoNombre.setBackground(new java.awt.Color(225, 225, 225));
        txtCampoNombre.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtCampoNombre.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fechaCitaAgenda.png"))); // NOI18N
        jLabel1.setText("FECHA DE AGENDA DE LA CITA:");

        dateChooserFechaCita.setBackground(new java.awt.Color(225, 225, 225));
        dateChooserFechaCita.setForeground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buttonCargarPaciente.setBackground(new java.awt.Color(0, 0, 153));
        buttonCargarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonCargarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        buttonCargarPaciente.setText("CARGAR");
        buttonCargarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFondoBlancoAgendarCitaLayout = new javax.swing.GroupLayout(panelFondoBlancoAgendarCita);
        panelFondoBlancoAgendarCita.setLayout(panelFondoBlancoAgendarCitaLayout);
        panelFondoBlancoAgendarCitaLayout.setHorizontalGroup(
            panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelHoraCita)
                            .addComponent(dateChooserFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(buttonCargarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelConsultorio)
                                            .addComponent(labelNumeroAgenda)
                                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                                .addComponent(txtIdentificacionCita, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelEsAutomatico))
                                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(buttonAgendar)
                                                .addGap(18, 18, 18)
                                                .addComponent(buttonReiniciar))))
                                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cboConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(labelTabbedAgendarCita))
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelIdentificacionDelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdentificacionPaciente))
                            .addComponent(labelNombreDelPaciente)
                            .addComponent(txtCampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMotivoDeLaCita)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelSeleccioneDoctor))
                                .addGap(22, 22, 22))
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addComponent(cboDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonVerMedicos)
                                .addContainerGap())))))
        );
        panelFondoBlancoAgendarCitaLayout.setVerticalGroup(
            panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelTabbedAgendarCita)
                .addGap(18, 18, 18)
                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMotivoDeLaCita)
                            .addComponent(labelIdentificacionDelPaciente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSeleccioneDoctor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonVerMedicos)
                                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(cboDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(labelConsultorio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addComponent(txtIdentificacionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelNombreDelPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCargarPaciente)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateChooserFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(labelNumeroAgenda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEsAutomatico)
                                    .addComponent(txtIdentificacionCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonReiniciar)
                                    .addComponent(buttonAgendar))
                                .addGap(27, 27, 27))
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                                .addComponent(labelHoraCita)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout panelAgendarCitaLayout = new javax.swing.GroupLayout(panelAgendarCita);
        panelAgendarCita.setLayout(panelAgendarCitaLayout);
        panelAgendarCitaLayout.setHorizontalGroup(
            panelAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelAgendarCitaLayout.setVerticalGroup(
            panelAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Agendar Cita", panelAgendarCita);

        panelFondoBlancoEditarCita.setBackground(new java.awt.Color(255, 255, 255));

        labelTabbedEditarCita.setBackground(new java.awt.Color(0, 51, 102));
        labelTabbedEditarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTabbedEditarCita.setForeground(new java.awt.Color(0, 51, 102));
        labelTabbedEditarCita.setText("EDITAR CITA");

        labelInformaciónCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        labelInformaciónCita.setForeground(new java.awt.Color(0, 0, 0));
        labelInformaciónCita.setText("Actualización de datos de una cita si es necesario por un imprevisto o petición del paciente");

        labelCitaIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelCitaIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        labelCitaIdentificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/idCita.png"))); // NOI18N
        labelCitaIdentificacion.setText("N° DE LA CITA");

        txtBuscarCita.setBackground(new java.awt.Color(225, 225, 225));
        txtBuscarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        txtBuscarCita.setForeground(new java.awt.Color(0, 0, 0));

        labelObligatorioID.setBackground(new java.awt.Color(255, 0, 0));
        labelObligatorioID.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelObligatorioID.setForeground(new java.awt.Color(255, 0, 0));
        labelObligatorioID.setText("*");

        buttonBuscarCitaPorID.setBackground(new java.awt.Color(0, 0, 201));
        buttonBuscarCitaPorID.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        buttonBuscarCitaPorID.setForeground(new java.awt.Color(255, 255, 255));
        buttonBuscarCitaPorID.setText("BUSCAR");
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
            .addGap(0, 23, Short.MAX_VALUE)
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

        txtHoraaCita.setEditable(false);
        txtHoraaCita.setBackground(new java.awt.Color(225, 225, 225));
        txtHoraaCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtHoraaCita.setForeground(new java.awt.Color(0, 0, 0));

        txtFechaCitaV.setEditable(false);
        txtFechaCitaV.setBackground(new java.awt.Color(225, 225, 225));
        txtFechaCitaV.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtFechaCitaV.setForeground(new java.awt.Color(0, 0, 0));

        lblHoraCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblHoraCita.setForeground(new java.awt.Color(0, 0, 0));
        lblHoraCita.setText("HORA DE LA CITA:");

        lblFechaaCita.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblFechaaCita.setForeground(new java.awt.Color(0, 0, 0));
        lblFechaaCita.setText("FECHA DE LA CITA:");

        lblNombreDoctor.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        lblNombreDoctor.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreDoctor.setText("DOCUMENTO DOCTOR:");

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

        buttonCancelarCita.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelarCita.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonCancelarCita.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelarCita.setText("CANCELAR CITA");
        buttonCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarCitaActionPerformed(evt);
            }
        });

        buttonConsultorios.setBackground(new java.awt.Color(255, 255, 255));
        buttonConsultorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/verMedicos.png"))); // NOI18N
        buttonConsultorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConsultoriosActionPerformed(evt);
            }
        });

        buttonMedicos.setBackground(new java.awt.Color(255, 255, 255));
        buttonMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/verMedicos.png"))); // NOI18N
        buttonMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMedicosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFondoBlancoEditarCitaLayout = new javax.swing.GroupLayout(panelFondoBlancoEditarCita);
        panelFondoBlancoEditarCita.setLayout(panelFondoBlancoEditarCitaLayout);
        panelFondoBlancoEditarCitaLayout.setHorizontalGroup(
            panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDiv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                .addGap(254, 254, 254)
                                .addComponent(labelTabbedEditarCita))
                            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelInformaciónCita))
                            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                .addGap(245, 245, 245)
                                .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelCitaIdentificacion)
                                    .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                        .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(buttonBuscarCitaPorID)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelObligatorioID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblDatosCitas))
                            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                        .addComponent(lblNombreDoctor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNomDoctor))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                        .addComponent(lblDocPaciente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDocPaciente))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                        .addComponent(lblPaciente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCargarNPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonMedicos)
                                .addGap(19, 19, 19)
                                .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                        .addComponent(lblFechaaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFechaCitaV, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                            .addComponent(lblConsultorio)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtConsultorioAsignadoo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(buttonConsultorios))
                                        .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                            .addComponent(lblHoraCita)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtHoraaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                                .addGap(213, 213, 213)
                                .addComponent(buttonCancelarCita)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelFondoBlancoEditarCitaLayout.setVerticalGroup(
            panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelTabbedEditarCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInformaciónCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCitaIdentificacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelObligatorioID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonBuscarCitaPorID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDatosCitas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonMedicos)
                        .addGap(105, 105, 105)
                        .addComponent(buttonCancelarCita)
                        .addGap(65, 65, 65))
                    .addGroup(panelFondoBlancoEditarCitaLayout.createSequentialGroup()
                        .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaaCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCargarNPaciente)
                                .addComponent(txtFechaCitaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDocPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDocPaciente)
                            .addComponent(lblHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraaCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonConsultorios, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNombreDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblConsultorio)
                                .addComponent(txtConsultorioAsignadoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(194, 194, 194))))
        );

        javax.swing.GroupLayout panelEditarLayout = new javax.swing.GroupLayout(panelEditar);
        panelEditar.setLayout(panelEditarLayout);
        panelEditarLayout.setHorizontalGroup(
            panelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarLayout.createSequentialGroup()
                .addComponent(panelFondoBlancoEditarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelEditarLayout.setVerticalGroup(
            panelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoEditarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Editar Cita", panelEditar);

        panelFondoBlancoHistorialMedico.setBackground(new java.awt.Color(255, 255, 255));

        labelTabbedHistorialMédico.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        labelTabbedHistorialMédico.setForeground(new java.awt.Color(0, 0, 0));
        labelTabbedHistorialMédico.setText("HISTORIAL MÉDICO");

        javax.swing.GroupLayout panelFondoBlancoHistorialMedicoLayout = new javax.swing.GroupLayout(panelFondoBlancoHistorialMedico);
        panelFondoBlancoHistorialMedico.setLayout(panelFondoBlancoHistorialMedicoLayout);
        panelFondoBlancoHistorialMedicoLayout.setHorizontalGroup(
            panelFondoBlancoHistorialMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoHistorialMedicoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelTabbedHistorialMédico)
                .addContainerGap(514, Short.MAX_VALUE))
        );
        panelFondoBlancoHistorialMedicoLayout.setVerticalGroup(
            panelFondoBlancoHistorialMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoHistorialMedicoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelTabbedHistorialMédico)
                .addContainerGap(453, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelHistorialMédicoLayout = new javax.swing.GroupLayout(panelHistorialMédico);
        panelHistorialMédico.setLayout(panelHistorialMédicoLayout);
        panelHistorialMédicoLayout.setHorizontalGroup(
            panelHistorialMédicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoHistorialMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelHistorialMédicoLayout.setVerticalGroup(
            panelHistorialMédicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoHistorialMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Historial Médico", panelHistorialMédico);

        fondoBlancoPrincipal.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 660, 530));

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
            .addComponent(fondoBlancoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoBlancoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBtnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnInicioMouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_panelBtnInicioMouseClicked

    private void panelBtnAgregarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAgregarPacienteMouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_panelBtnAgregarPacienteMouseClicked

    private void panelBtnEditarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEditarPacienteMouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_panelBtnEditarPacienteMouseClicked

    private void panelBtnAgendarCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAgendarCitaMouseClicked
        jTabbedPane1.setSelectedIndex(3);
        setEnabledFalse();
    }//GEN-LAST:event_panelBtnAgendarCitaMouseClicked

    private void panelBtnEditarCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEditarCitaMouseClicked
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_panelBtnEditarCitaMouseClicked

    private void panelBtnHistorialesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnHistorialesMouseClicked
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_panelBtnHistorialesMouseClicked

    private void txtPrimerNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimerNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimerNombreActionPerformed

    private void txtSegundoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSegundoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoNombreActionPerformed

    private void txtPrimerApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimerApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimerApellidoActionPerformed

    private void txtSegundoApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSegundoApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoApellidoActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void panelBtnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnCerrarSesionMouseClicked
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelBtnCerrarSesionMouseClicked

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed

        //Limpiar campo de nombre
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");

        //Limpiar campo de identificación
        cboTipoIdentificacion.setSelectedIndex(0);
        txtNumeroDocumento.setText("");

        //Limpiar campo de contacto del paciente
        txtEmail.setText("");
        txtTelefono.setText("");

        //Limpiar campo de datos del paciente
        txtDireccion.setText("");
        cboEstadoCivil.setSelectedIndex(0);
        cboGenero.setSelectedIndex(0);
        cboTipoSangre.setSelectedIndex(0);

        //Limpiar campo resto de información
        //dateChooserFechaNacimiento.setDate(null);
        cboEPS.setSelectedIndex(0);
        txtEdad.setText("");
        JOptionPane.showMessageDialog(null, "Proceso de agendar paciente cancelado", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonCancelarBusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarBusqActionPerformed
        txtBuscarNumeroIdentificacion.setText("");
        cboBuscarNumeroIdentificacion.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, "Proceso de buscar el paciente cancelado", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonCancelarBusqActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed

        try {
            String numeroDocumento = txtBuscarNumeroIdentificacion.getText();
            String tipoDocumento = cboBuscarNumeroIdentificacion.getSelectedItem().toString();

            if (numeroDocumento.trim().isEmpty() || tipoDocumento.trim().equalsIgnoreCase("--")) {
                JOptionPane.showMessageDialog(null, "Hay campos sin específicar", "Datos sin marcas", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!metodo.ContieneSoloNumeros(numeroDocumento) || numeroDocumento.length() < 7 || numeroDocumento.length() > 12) {
                JOptionPane.showMessageDialog(null, "Número de documento inválido", "Número de documento inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (verificar.verificarPaciente(numeroDocumento, tipoDocumento, "numeroDocumento", "tipoDocumento")) {

                //Cargar datos en labels
                cargarDatosPacientes.cargarDatosPacientes(numeroDocumento, tipoDocumento, txtCargarEPS, txtCargarPrimerNombre, txtCargarPrimerApellido);

                //Cuando se encuentre el cliente, aparece la sección actualizar dato.
                panelDivisionEditarPaciente.setVisible(true);
                labelEncontrado.setVisible(true);
                labelDatoQueDesea.setVisible(true);
                labelModificarDato.setVisible(true);
                cboSeleccionarDato.setVisible(true);
                labelDigiteActualizacion.setVisible(true);
                txtActualizarDato.setVisible(true);
                labelCargarEPS.setVisible(true);
                txtCargarEPS.setVisible(true);
                labelCargarNombre.setVisible(true);
                txtCargarPrimerNombre.setVisible(true);
                txtCargarPrimerApellido.setVisible(true);

                txtActualizarDato.setEnabled(false);
                buttonActualizar.setVisible(true);
                buttonCancelarModificacion.setVisible(true);
                buttonEliminarPaciente.setVisible(true);

                txtBuscarNumeroIdentificacion.setEnabled(false);
                buttonBuscar.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "Ese usuario no está registrado en el sistema. ", "No encontrado", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void cboSeleccionarDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeleccionarDatoActionPerformed
        String opcionSeleccionada = (String) cboSeleccionarDato.getSelectedItem();
        if ("Seleccionar".equals(opcionSeleccionada)) {
            txtActualizarDato.setEnabled(false);
            labelEstadoCivil1.setVisible(false);
            cboEstadoCivil1.setVisible(false);
            cboTipoIdentificacion1.setVisible(false);
            labelTipoIdentificación1.setVisible(false);
        }

        if ("Email".equals(opcionSeleccionada) || "Dirección de residencia".equals(opcionSeleccionada) || "Teléfono".equals(opcionSeleccionada) || "Edad".equals(opcionSeleccionada)) {
            txtActualizarDato.setEnabled(true);
            labelEstadoCivil1.setVisible(false);
            cboEstadoCivil1.setVisible(false);
            cboTipoIdentificacion1.setVisible(false);
            labelTipoIdentificación1.setVisible(false);
            cboEPS1.setVisible(false);
            labelEpsPaciente1.setVisible(false);
        }

        if ("Tipo de documento".equals(opcionSeleccionada) || "Estado civil".equals(opcionSeleccionada) || "EPS".equals(opcionSeleccionada)) {
            txtActualizarDato.setEnabled(false);

        }

        if ("Estado civil".equals(opcionSeleccionada)) {
            labelEstadoCivil1.setVisible(true);
            cboEstadoCivil1.setVisible(true);

            cboEPS1.setVisible(false);
            labelEpsPaciente1.setVisible(false);

            cboTipoIdentificacion1.setVisible(false);
            labelTipoIdentificación1.setVisible(false);
        }

        if ("Tipo de documento".equals(opcionSeleccionada)) {
            labelEstadoCivil1.setVisible(false);
            cboEstadoCivil1.setVisible(false);

            cboTipoIdentificacion1.setVisible(true);
            labelTipoIdentificación1.setVisible(true);

            cboEPS1.setVisible(false);
            labelEpsPaciente1.setVisible(false);

        }

        if ("EPS".equals(opcionSeleccionada)) {
            labelEstadoCivil1.setVisible(false);
            cboEstadoCivil1.setVisible(false);

            cboTipoIdentificacion1.setVisible(false);
            labelTipoIdentificación1.setVisible(false);

            cboEPS1.setVisible(true);
            labelEpsPaciente1.setVisible(true);
        }
    }//GEN-LAST:event_cboSeleccionarDatoActionPerformed

    private void buttonCancelarModificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarModificacionActionPerformed

        //Limpiamos los datos
        cboSeleccionarDato.setSelectedIndex(0);
        txtActualizarDato.setText("");

        //Ocultamos la sección
        panelDivisionEditarPaciente.setVisible(false);
        labelEncontrado.setVisible(false);
        labelDatoQueDesea.setVisible(false);
        labelModificarDato.setVisible(false);
        cboSeleccionarDato.setVisible(false);
        labelDigiteActualizacion.setVisible(false);
        txtActualizarDato.setVisible(false);
        buttonActualizar.setVisible(false);
        buttonCancelarModificacion.setVisible(false);
        labelCargarEPS.setVisible(false);
        txtCargarEPS.setVisible(false);
        labelCargarNombre.setVisible(false);
        txtCargarPrimerNombre.setVisible(false);
        txtCargarPrimerApellido.setVisible(false);
        labelModificarDato.setVisible(false);
        cboSeleccionarDato.setVisible(false);
        labelDigiteActualizacion.setVisible(false);
        txtActualizarDato.setVisible(false);
        buttonEliminarPaciente.setVisible(false);
        cboEPS1.setVisible(false);
        labelEpsPaciente1.setVisible(false);

        txtBuscarNumeroIdentificacion.setEnabled(true);
        buttonBuscar.setEnabled(true);

        txtBuscarNumeroIdentificacion.setText("");
        cboBuscarNumeroIdentificacion.setSelectedIndex(0);

        //Mensaje para el usuario
        JOptionPane.showMessageDialog(null, "Proceso de modificar un dato cancelado", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonCancelarModificacionActionPerformed

    private void buttonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReiniciarActionPerformed
        txtIdentificacionCita.setText("");
        txtIdentificacionPaciente.setEnabled(true);
        buttonCargarPaciente.setEnabled(true);

    }//GEN-LAST:event_buttonReiniciarActionPerformed

    private void txtCargarEPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargarEPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargarEPSActionPerformed

    private void buttonEliminarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarPacienteActionPerformed
        try {
            String numeroDocumento = txtBuscarNumeroIdentificacion.getText();
            String tipoDocumento = cboBuscarNumeroIdentificacion.getSelectedItem().toString();
            
            IEliminarPaciente eliminar = new ComandoEliminarPaciente();
            eliminar.EliminarPaciente(tipoDocumento, numeroDocumento);
            limpiarDespuesDeActualizar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
        }

    }//GEN-LAST:event_buttonEliminarPacienteActionPerformed

    private void buttonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarActionPerformed

        try {
            String primerNombre = txtPrimerNombre.getText();
            String segundoNombre = txtSegundoNombre.getText();
            String primerApellido = txtPrimerApellido.getText();
            String segundoApellido = txtSegundoApellido.getText();
            String tipoIdentificacion = cboTipoIdentificacion.getSelectedItem().toString();
            String numeroDocumento = txtNumeroDocumento.getText();
            String telefono = txtTelefono.getText();
            String direccionResidencia = txtDireccion.getText();
            String estadoCivil = cboEstadoCivil.getSelectedItem().toString();
            String genero = cboGenero.getSelectedItem().toString();
            String email = txtEmail.getText();
            String EPS = cboEPS.getSelectedItem().toString();
            String tipoSangre = cboTipoSangre.getSelectedItem().toString();
            Date fechaNacimiento = dateChooserFechaNacimiento.getDate();
            Date fechaRegistro = new Date();
            String edad = txtEdad.getText();

            Paciente nuevoPaciente = new Paciente(
                    primerNombre, segundoNombre, primerApellido, segundoApellido,
                    tipoIdentificacion, numeroDocumento, telefono, direccionResidencia,
                    estadoCivil, genero, email, EPS, tipoSangre,
                    fechaNacimiento, fechaRegistro, edad
            );

            ICrearPaciente comando = new ComandoCrearPaciente();
            comando.CrearPaciente(nuevoPaciente);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_buttonAgregarActionPerformed

    private void buttonVerMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerMedicosActionPerformed
        new infoMedicos().setVisible(true);
    }//GEN-LAST:event_buttonVerMedicosActionPerformed

    private void txtIdentificacionPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionPacienteActionPerformed

    private void limpiarDespuesDeActualizar() {
        txtBuscarNumeroIdentificacion.setEnabled(true);
        buttonBuscar.setEnabled(true);

        panelDivisionEditarPaciente.setVisible(false);
        labelEncontrado.setVisible(false);
        labelDatoQueDesea.setVisible(false);
        labelModificarDato.setVisible(false);
        cboSeleccionarDato.setVisible(false);
        labelDigiteActualizacion.setVisible(false);
        txtActualizarDato.setVisible(false);
        buttonActualizar.setVisible(true);
        buttonCancelarModificacion.setVisible(false);
        labelCargarEPS.setVisible(false);
        txtCargarEPS.setVisible(false);
        labelCargarNombre.setVisible(false);
        txtCargarPrimerNombre.setVisible(false);
        txtCargarPrimerApellido.setVisible(false);
        labelModificarDato.setVisible(false);
        cboSeleccionarDato.setVisible(false);
        labelDigiteActualizacion.setVisible(false);
        txtActualizarDato.setVisible(false);
        cboEPS1.setVisible(false);
        labelEpsPaciente1.setVisible(false);
        labelEstadoCivil1.setVisible(false);
        cboEstadoCivil1.setVisible(false);

        cboTipoIdentificacion1.setVisible(false);
        labelTipoIdentificación1.setVisible(false);

        cboEPS1.setVisible(false);
        labelEpsPaciente1.setVisible(false);
        buttonActualizar.setVisible(false);
        buttonCancelarModificacion.setVisible(false);
        buttonEliminarPaciente.setVisible(false);
    }


    private void buttonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActualizarActionPerformed
        IActualizarPaciente actualizacion = new ComandoActualizarPaciente();
        String opcionSeleccionada = (String) cboSeleccionarDato.getSelectedItem();
        String numeroDocumento = txtBuscarNumeroIdentificacion.getText();

        try {
            // Validación básica
            if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar el número de documento para actualizar.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            paciente.setNumeroDocumento(numeroDocumento);

            switch (opcionSeleccionada) {
                case "Tipo de documento":
                    String nuevoTipoID = cboTipoIdentificacion1.getSelectedItem().toString();
                    if (nuevoTipoID.equals("Seleccionar")) {
                        JOptionPane.showMessageDialog(null, "Escoja una opción válida", "Campo vacío", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    paciente.setTipoDocumento(nuevoTipoID);
                    cboTipoIdentificacion1.setSelectedIndex(0);
                    break;

                case "Teléfono":
                    paciente.setTelefono(txtActualizarDato.getText());
                    break;

                case "Dirección de residencia":
                    paciente.setDireccionResidencia(txtActualizarDato.getText());
                    txtActualizarDato.setText("");
                    break;

                case "Estado civil":
                    String nuevoEstado = cboEstadoCivil1.getSelectedItem().toString();
                    if (nuevoEstado.equals("Seleccionar")) {
                        JOptionPane.showMessageDialog(null, "Escoja una opción válida", "Campo vacío", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    paciente.setEstadoCivil(nuevoEstado);
                    cboEstadoCivil1.setSelectedIndex(0);
                    break;

                case "Email":
                    paciente.setEmail(txtActualizarDato.getText());
                    txtActualizarDato.setText("");
                    break;

                case "EPS":
                    String nuevaEPS = cboEPS1.getSelectedItem().toString();
                    if (nuevaEPS.equals("Seleccionar")) {
                        JOptionPane.showMessageDialog(null, "Escoja una opción válida", "Campo vacío", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    paciente.setEPS(nuevaEPS);
                    break;

                case "Edad":
                    String nuevaEdad = txtActualizarDato.getText();
                    try {
                        int edad = Integer.parseInt(nuevaEdad);
                        if (edad > 0 && edad < 100) {
                            paciente.setEdad(String.valueOf(edad));
                        } else {
                            JOptionPane.showMessageDialog(null, "Edad no válida", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Edad inválida", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Seleccione un campo válido a actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
            }

            // Ejecutar actualización
            actualizacion.actualizarPaciente(paciente);
            limpiarDespuesDeActualizar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_buttonActualizarActionPerformed

    private void buttonCargarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCargarPacienteActionPerformed
        String numeroDocumento = txtIdentificacionPaciente.getText();

        try {

            if (existe.ExisteNumeroDocumento(numeroDocumento)) {

                if (buscarID.BuscarPacientePorDocumento(numeroDocumento) == null) {
                    JOptionPane.showMessageDialog(null, "El número de identificación no concide con el nombre", "Error autentitación", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    cargar2.CargarNombrePaciente(numeroDocumento, txtCampoNombre);
                    setEnabledTrue();
                    JOptionPane.showMessageDialog(null, "Paciente cargado con éxito.", "Consulta exitosa", JOptionPane.DEFAULT_OPTION);
                }

            } else {
                JOptionPane.showMessageDialog(null, "El número de identificación no existe", "Error autentitación", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }


    }//GEN-LAST:event_buttonCargarPacienteActionPerformed

    private void cboDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDoctorActionPerformed
        FiltrarConsultoriosPorDoctor filtrarConsultoriosPorDoctor = new FiltrarConsultoriosPorDoctor();
        if (cboDoctor.getSelectedItem() != null) {
            String doctorSeleccionado = cboDoctor.getSelectedItem().toString();
            String identificacionDoctor = buscarDoctorId.BuscarIdentificacionPorNombreDoctor(doctorSeleccionado);

            filtrarConsultoriosPorDoctor.filtrarConsultoriosPorDoctor(doctorSeleccionado, identificacionDoctor, cboConsultorio);
        }
    }//GEN-LAST:event_cboDoctorActionPerformed

    private void buttonAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgendarActionPerformed
        BuscarIdConsultorioPorConsultorio buscarIdDelConsultorio = new BuscarIdConsultorioPorConsultorio();
        try {

            IAgendarCita agendar = new ComandoAgendarCita();
            String nombre = cboDoctor.getSelectedItem().toString();
            String consultorio = cboConsultorio.getSelectedItem().toString();

            int idConsultorio = buscarIdDelConsultorio.BuscarIdConsultorio(consultorio);

            String identificacion = buscarDoctorId.BuscarIdentificacionPorNombreDoctor(nombre);

            String motivo = txtAreaMotivo.getText();

            Date fechaCita = dateChooserFechaCita.getDate();

            String hora = cboHoraCita.getSelectedItem().toString();

            int idCita = new Random().nextInt(1000);

            Date fechaRegistro = new Date();

            String nombrePaciente = txtCampoNombre.getText();
            String numeroDocumento = txtIdentificacionPaciente.getText();
            boolean estado = true;
            
            Cita nuevaCita = new Cita(idConsultorio, identificacion,motivo,fechaCita, hora, idCita,
            fechaRegistro, nombrePaciente, numeroDocumento,estado);
            
            IAgendarCita comando = new ComandoAgendarCita();
            comando.AgendarCita(nuevaCita);
            txtIdentificacionCita.setText("" + idCita);
            limpiarCampos();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_buttonAgendarActionPerformed

    private void buttonOkMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkMedicosActionPerformed

        try {
            JTableHeader header = tablaCitas.getTableHeader();
            header.setDefaultRenderer(headerRenderer);
            String id = txtBuscarMedicoId.getText();
            cargarEspecialidadNombreDoc.cargarNombreYEspecialidadDoctores(id, txtCargarNombreMedico, txtCargarEspecialidad);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_buttonOkMedicosActionPerformed

    private void tablaCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCitasMouseClicked
        int fila = tablaCitas.getSelectedRow();

        if (fila != -1) {
            String idDoctor = tablaCitas.getValueAt(fila, 2).toString(); // columna 2: identificacionDoctor
            txtBuscarMedicoId.setText(idDoctor);
        }

    }//GEN-LAST:event_tablaCitasMouseClicked

    private void buttonOkCargarMotivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkCargarMotivosActionPerformed
        CargarMotivo motivoCitaId = new CargarMotivo();
        try {
            JTableHeader header = tablaCitas.getTableHeader();
            header.setDefaultRenderer(headerRenderer);
            String idCita = txtBuscarMotivoID.getText();
            motivoCitaId.cargarMotivoPorID(idCita, txtCargarMotivo);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_buttonOkCargarMotivosActionPerformed

    private void lblRealodInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRealodInicioMouseClicked
        txtBuscarMotivoID.setText("");
        txtCargarMotivo.setText("");
        txtBuscarMedicoId.setText("");
        txtCargarNombreMedico.setText("");
        txtCargarEspecialidad.setText("");
        mostrarCitasEnTabla.refrescarCitas(tablaCitas);
        JOptionPane.showMessageDialog(null, "Campos recargados", "Limpieza", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblRealodInicioMouseClicked

    private void buttonBuscarCitaPorIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarCitaPorIDActionPerformed
        try {

            String idCita = txtBuscarCita.getText();

            if (idCita == null) {
                JOptionPane.showMessageDialog(null, "Hay campos sin específicar", "Datos sin marcas", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!metodo.ContieneSoloNumeros(idCita)) {
                JOptionPane.showMessageDialog(null, "El número de la cita sólo puede tener números.", "Datos incorrectos.", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCitaP = Integer.parseInt(idCita);

            if (verificarCita.verificarCita(idCitaP)) {
                mostrarSeccionEditarCita();

                //String consultorio
                //Cargamos
                cargarADatosCita.cargarDatosCita(idCitaP, txtCargarNPaciente, txtDocPaciente, txtHoraaCita, txtFechaCitaV, txtNomDoctor, txtConsultorioAsignadoo);
            } else {
                JOptionPane.showMessageDialog(null, "Ese número de cita no está registrado en el sistema. ", "No encontrado", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_buttonBuscarCitaPorIDActionPerformed

    private void buttonCancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarCitaActionPerformed
        try {
            String idCitaStr = txtBuscarCita.getText();
            int idCita = Integer.parseInt(idCitaStr);
            ICancelarCita eliminar = new ComandoCancelarCita();
            eliminar.CancelarCita(idCita);
            ocultarSeccionEditarCita();
            txtBuscarCita.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
        }

    }//GEN-LAST:event_buttonCancelarCitaActionPerformed

    private void buttonConsultoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConsultoriosActionPerformed
        new infoConsultorios().setVisible(true);

    }//GEN-LAST:event_buttonConsultoriosActionPerformed

    private void buttonMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMedicosActionPerformed
        new infoMedicos().setVisible(true);
    }//GEN-LAST:event_buttonMedicosActionPerformed

    //Cambiar aparienia de la tabla
    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Establecer el color de fondo y el color de texto
            setBackground(new Color(232, 230, 229));
            setForeground(Color.BLACK);

            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    };

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recepcionista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonActualizar;
    private javax.swing.JButton buttonAgendar;
    private javax.swing.JButton buttonAgregar;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonBuscarCitaPorID;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonCancelarBusq;
    private javax.swing.JButton buttonCancelarCita;
    private javax.swing.JButton buttonCancelarModificacion;
    private javax.swing.JButton buttonCargarPaciente;
    private javax.swing.JButton buttonConsultorios;
    private javax.swing.JButton buttonEliminarPaciente;
    private javax.swing.JButton buttonMedicos;
    private javax.swing.JButton buttonOkCargarMotivos;
    private javax.swing.JButton buttonOkMedicos;
    private javax.swing.JButton buttonReiniciar;
    private javax.swing.JButton buttonVerMedicos;
    private javax.swing.JComboBox<String> cboBuscarNumeroIdentificacion;
    private javax.swing.JComboBox<String> cboConsultorio;
    private javax.swing.JComboBox<String> cboDoctor;
    private javax.swing.JComboBox<String> cboEPS;
    private javax.swing.JComboBox<String> cboEPS1;
    private javax.swing.JComboBox<String> cboEstadoCivil;
    private javax.swing.JComboBox<String> cboEstadoCivil1;
    private javax.swing.JComboBox<String> cboGenero;
    private javax.swing.JComboBox<String> cboHoraCita;
    private javax.swing.JComboBox<String> cboSeleccionarDato;
    private javax.swing.JComboBox<String> cboTipoIdentificacion;
    private javax.swing.JComboBox<String> cboTipoIdentificacion1;
    private javax.swing.JComboBox<String> cboTipoSangre;
    private com.toedter.calendar.JDateChooser dateChooserFechaCita;
    private com.toedter.calendar.JDateChooser dateChooserFechaNacimiento;
    private javax.swing.JPanel fondoBlancoPrincipal;
    private javax.swing.JLabel iconAgendarCita;
    private javax.swing.JLabel iconAgregarPaciente;
    private javax.swing.JLabel iconCerrarSesion;
    private javax.swing.JLabel iconEditarCita;
    private javax.swing.JLabel iconEditarPaciente;
    private javax.swing.JLabel iconHistorial;
    private javax.swing.JLabel iconInicio;
    private javax.swing.JLabel iconRecepcinista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAgregarPaciente;
    private javax.swing.JLabel labelApartadoDe;
    private javax.swing.JLabel labelCargarEPS;
    private javax.swing.JLabel labelCargarNombre;
    private javax.swing.JLabel labelCartagenaDeIndias;
    private javax.swing.JLabel labelCerrarSesion;
    private javax.swing.JLabel labelCitaIdentificacion;
    private javax.swing.JLabel labelConsultorio;
    private javax.swing.JLabel labelContacto;
    private javax.swing.JLabel labelDatoQueDesea;
    private javax.swing.JLabel labelDatosDelPaciente;
    private javax.swing.JLabel labelDigiteActualizacion;
    private javax.swing.JLabel labelDigiteLaIdentificacion;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelEdad;
    private javax.swing.JLabel labelEditarCita;
    private javax.swing.JLabel labelEditarPaciente;
    private javax.swing.JLabel labelEditarPaciente1;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEncontrado;
    private javax.swing.JLabel labelEpsPaciente;
    private javax.swing.JLabel labelEpsPaciente1;
    private javax.swing.JLabel labelEsAutomatico;
    private javax.swing.JLabel labelEstadoCivil;
    private javax.swing.JLabel labelEstadoCivil1;
    private javax.swing.JLabel labelFechaNacimiento;
    private javax.swing.JLabel labelGeneroPaciente;
    private javax.swing.JLabel labelHoraCita;
    private javax.swing.JLabel labelIdentificacion;
    private javax.swing.JLabel labelIdentificacionDelPaciente;
    private javax.swing.JLabel labelInformación;
    private javax.swing.JLabel labelInformación1;
    private javax.swing.JLabel labelInformaciónCita;
    private javax.swing.JLabel labelJaoSalud;
    private javax.swing.JLabel labelMenúOpciones;
    private javax.swing.JLabel labelModificarDato;
    private javax.swing.JLabel labelMotivoDeLaCita;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelNombreDelPaciente;
    private javax.swing.JLabel labelNumeroAgenda;
    private javax.swing.JLabel labelNumeroIdentificacion;
    private javax.swing.JLabel labelObligatorio;
    private javax.swing.JLabel labelObligatorioID;
    private javax.swing.JLabel labelPrimerApellido;
    private javax.swing.JPanel labelPrimerNombre;
    private javax.swing.JLabel labelPrimerrNombre;
    private javax.swing.JLabel labelRecepcionisa;
    private javax.swing.JLabel labelRestoDeInfo;
    private javax.swing.JLabel labelSegundoApellido;
    private javax.swing.JLabel labelSegundoNombre;
    private javax.swing.JLabel labelSeleccioneDoctor;
    private javax.swing.JLabel labelTabbedAgendarCita;
    private javax.swing.JLabel labelTabbedAgregarPaciente;
    private javax.swing.JLabel labelTabbedEditarCita;
    private javax.swing.JLabel labelTabbedHistorialMédico;
    private javax.swing.JLabel labelTablaDeCita;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelTipoIdentificación;
    private javax.swing.JLabel labelTipoIdentificación1;
    private javax.swing.JLabel labelTipoSangre;
    private javax.swing.JLabel labelVerHistoriales;
    private javax.swing.JLabel lblConsultorio;
    private javax.swing.JLabel lblDatosCitas;
    private javax.swing.JLabel lblDocPaciente;
    private javax.swing.JLabel lblFechaaCita;
    private javax.swing.JLabel lblHoraCita;
    private javax.swing.JLabel lblNombreDoctor;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblRealodInicio;
    private javax.swing.JPanel panelAgendarCita;
    private javax.swing.JPanel panelAgregarPaciente;
    private javax.swing.JPanel panelAzul;
    private javax.swing.JPanel panelBtnAgendarCita;
    private javax.swing.JPanel panelBtnAgregarPaciente;
    private javax.swing.JPanel panelBtnCerrarSesion;
    private javax.swing.JPanel panelBtnEditarCita;
    private javax.swing.JPanel panelBtnEditarPaciente;
    private javax.swing.JPanel panelBtnHistoriales;
    private javax.swing.JPanel panelBtnInicio;
    private javax.swing.JPanel panelDeInicio;
    private javax.swing.JPanel panelDecoración;
    private javax.swing.JPanel panelDiv;
    private javax.swing.JPanel panelDivisionEditarPaciente;
    private javax.swing.JPanel panelEditar;
    private javax.swing.JPanel panelEditarPaciente;
    private javax.swing.JPanel panelFondoBlancoAgendarCita;
    private javax.swing.JPanel panelFondoBlancoEditarCita;
    private javax.swing.JPanel panelFondoBlancoEditarPaciente;
    private javax.swing.JPanel panelFondoBlancoHistorialMedico;
    private javax.swing.JPanel panelFondoBlancoInicio;
    private javax.swing.JPanel panelHistorialMédico;
    private javax.swing.JPanel panelSeparaciónPaciente;
    private javax.swing.JPanel panelSeparaciónPaciente2;
    private javax.swing.JLabel panelTabbedEditarPaciente;
    private javax.swing.JTable tablaCitas;
    private javax.swing.JTextField txtActualizarDato;
    private javax.swing.JTextArea txtAreaMotivo;
    private javax.swing.JTextField txtBuscarCita;
    private javax.swing.JTextField txtBuscarMedicoId;
    private javax.swing.JTextField txtBuscarMotivoID;
    private javax.swing.JTextField txtBuscarNumeroIdentificacion;
    private javax.swing.JTextField txtCampoNombre;
    private javax.swing.JTextField txtCargarEPS;
    private javax.swing.JTextField txtCargarEspecialidad;
    private javax.swing.JTextArea txtCargarMotivo;
    private javax.swing.JTextField txtCargarNPaciente;
    private javax.swing.JTextField txtCargarNombreMedico;
    private javax.swing.JTextField txtCargarPrimerApellido;
    private javax.swing.JTextField txtCargarPrimerNombre;
    private javax.swing.JTextField txtConsultorioAsignadoo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDocPaciente;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFechaCitaV;
    private javax.swing.JTextField txtHoraaCita;
    private javax.swing.JTextField txtIdentificacionCita;
    private javax.swing.JTextField txtIdentificacionPaciente;
    private javax.swing.JTextField txtNomDoctor;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public void setEnabledFalse() {
        dateChooserFechaCita.setEnabled(false);
        cboHoraCita.setEnabled(false);
        txtAreaMotivo.setEnabled(false);
        cboDoctor.setEnabled(false);
        cboConsultorio.setEnabled(false);
        buttonAgendar.setEnabled(false);
        buttonReiniciar.setEnabled(false);
        buttonVerMedicos.setEnabled(false);
    }

    public void setEnabledTrue() {
        dateChooserFechaCita.setEnabled(true);
        cboHoraCita.setEnabled(true);
        txtAreaMotivo.setEnabled(true);
        cboDoctor.setEnabled(true);
        cboConsultorio.setEnabled(true);
        buttonAgendar.setEnabled(true);
        buttonReiniciar.setEnabled(true);
        buttonCargarPaciente.setEnabled(false);
        buttonVerMedicos.setEnabled(true);
    }

    public void limpiarCampos() {
        cboDoctor.setSelectedIndex(-1); // Deselecciona todo
        cboConsultorio.setSelectedIndex(-1);
        cboHoraCita.setSelectedIndex(-1);

        txtAreaMotivo.setText("");
        txtCampoNombre.setText("");
        setEnabledFalse(); // Si aquí no se sobreescribe lo limpiado
        buttonCargarPaciente.setEnabled(false);
        txtCampoNombre.setEnabled(false);
        txtIdentificacionPaciente.setEnabled(false);
        buttonReiniciar.setEnabled(true);
    }

    public void ocultarSeccionEditarCita() {
        panelDiv.setVisible(false);

        lblDatosCitas.setVisible(false);
        lblPaciente.setVisible(false);
        lblDocPaciente.setVisible(false);
        lblHoraCita.setVisible(false);
        lblNombreDoctor.setVisible(false);
        lblConsultorio.setVisible(false);
        lblFechaaCita.setVisible(false);

        txtCargarNPaciente.setVisible(false);
        txtDocPaciente.setVisible(false);
        txtNomDoctor.setVisible(false);
        txtFechaCitaV.setVisible(false);
        txtHoraaCita.setVisible(false);
        txtConsultorioAsignadoo.setVisible(false);
        buttonCancelarCita.setVisible(false);
        buttonConsultorios.setVisible(false);
        buttonMedicos.setVisible(false);
    }

    public void mostrarSeccionEditarCita() {
        panelDiv.setVisible(true);

        lblDatosCitas.setVisible(true);
        lblPaciente.setVisible(true);
        lblDocPaciente.setVisible(true);
        lblHoraCita.setVisible(true);
        lblNombreDoctor.setVisible(true);
        lblConsultorio.setVisible(true);
        lblFechaaCita.setVisible(true);

        txtCargarNPaciente.setVisible(true);
        txtDocPaciente.setVisible(true);
        txtNomDoctor.setVisible(true);
        txtFechaCitaV.setVisible(true);
        txtHoraaCita.setVisible(true);
        txtConsultorioAsignadoo.setVisible(true);
        buttonCancelarCita.setVisible(true);
        buttonConsultorios.setVisible(true);
        buttonMedicos.setVisible(true);
        txtBuscarCita.setEnabled(false);
    }

}
