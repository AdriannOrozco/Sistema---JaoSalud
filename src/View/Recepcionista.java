package View;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Recepcionista extends javax.swing.JFrame {

    DefaultTableModel modeloC = new DefaultTableModel();

    public Recepcionista() {
        initComponents();
        setLocationRelativeTo(null);
        tablaDeCitas();
        seccionEditarDatoPaciente();

    }

    public void seccionEditarDatoPaciente() {
        //Apartado de citas
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
    }

    private void tablaDeCitas() {
        modeloC.addColumn("Motivo");
        modeloC.addColumn("Hora");
        modeloC.addColumn("Consultorio");
        modeloC.addColumn("Médico");
        modeloC.addColumn("Paciente");
        tablaDeCitas.setModel(modeloC);
        tablaDeCitas.getColumnModel().getColumn(0).setWidth(120);
        tablaDeCitas.getColumnModel().getColumn(0).setMinWidth(120);
        tablaDeCitas.getColumnModel().getColumn(0).setMaxWidth(120);
        tablaDeCitas.getColumnModel().getColumn(1).setWidth(120);
        tablaDeCitas.getColumnModel().getColumn(1).setMinWidth(120);
        tablaDeCitas.getColumnModel().getColumn(1).setMaxWidth(120);

        //Prieba
        JTableHeader header = tablaDeCitas.getTableHeader();
        header.setDefaultRenderer(headerRenderer);
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
        labelTabbedInicio = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDeCitas = new javax.swing.JTable();
        labelTablaDeCita = new javax.swing.JLabel();
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
        txtNumeroIdentificacion = new javax.swing.JTextField();
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
        dateChooserFechaNacimiento = new com.toedter.calendar.JDateChooser();
        labelFechaNacimiento = new javax.swing.JLabel();
        labelEpsPaciente = new javax.swing.JLabel();
        cboEPS = new javax.swing.JComboBox<>();
        labelEdad = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        buttonAgregar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
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
        buttonCancelarAgendar = new javax.swing.JButton();
        labelNumeroAgenda = new javax.swing.JLabel();
        txtIdentificacionCita = new javax.swing.JTextField();
        labelEsAutomatico = new javax.swing.JLabel();
        panelEditar = new javax.swing.JPanel();
        panelFondoBlancoEditarCita = new javax.swing.JPanel();
        labelTabbedEditarCita = new javax.swing.JLabel();
        labelInformaciónCita = new javax.swing.JLabel();
        labelCitaIdentificacion = new javax.swing.JLabel();
        txtBuscarCita = new javax.swing.JTextField();
        labelObligatorioID = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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

        fondoBlancoPrincipal.add(panelAzul, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 630));

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setForeground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        fondoBlancoPrincipal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 660, 40));

        panelFondoBlancoInicio.setBackground(new java.awt.Color(255, 255, 255));

        labelTabbedInicio.setBackground(new java.awt.Color(0, 51, 102));
        labelTabbedInicio.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelTabbedInicio.setForeground(new java.awt.Color(0, 51, 102));
        labelTabbedInicio.setText("INICIO");

        tablaDeCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaDeCitas);

        labelTablaDeCita.setFont(new java.awt.Font("JetBrains Mono Medium", 0, 18)); // NOI18N
        labelTablaDeCita.setForeground(new java.awt.Color(0, 0, 0));
        labelTablaDeCita.setText("ESTÁS SON LAS CITAS PENDIENTES EN EL SISTEMA:");

        javax.swing.GroupLayout panelFondoBlancoInicioLayout = new javax.swing.GroupLayout(panelFondoBlancoInicio);
        panelFondoBlancoInicio.setLayout(panelFondoBlancoInicioLayout);
        panelFondoBlancoInicioLayout.setHorizontalGroup(
            panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addGroup(panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(labelTabbedInicio)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(labelTablaDeCita)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        panelFondoBlancoInicioLayout.setVerticalGroup(
            panelFondoBlancoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoInicioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTabbedInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(labelTablaDeCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelDeInicioLayout = new javax.swing.GroupLayout(panelDeInicio);
        panelDeInicio.setLayout(panelDeInicioLayout);
        panelDeInicioLayout.setHorizontalGroup(
            panelDeInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        txtNumeroIdentificacion.setBackground(new java.awt.Color(225, 225, 225));
        txtNumeroIdentificacion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtNumeroIdentificacion.setForeground(new java.awt.Color(0, 0, 0));

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

        dateChooserFechaNacimiento.setBackground(new java.awt.Color(225, 225, 225));
        dateChooserFechaNacimiento.setForeground(new java.awt.Color(225, 225, 225));

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
                                        .addComponent(txtNumeroIdentificacion, javax.swing.GroupLayout.Alignment.LEADING))
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
                                .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(dateChooserFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelEpsPaciente)
                                        .addComponent(cboEPS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(labelPrimerNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtEdad)))))
                            .addGroup(labelPrimerNombreLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
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
                    .addGroup(labelPrimerNombreLayout.createSequentialGroup()
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
                                        .addComponent(txtNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(labelRestoDeInfo)
                                        .addGap(14, 14, 14)
                                        .addComponent(labelFechaNacimiento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateChooserFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelEpsPaciente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboEPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelEdad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(buttonAgregar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonCancelar)))
                                .addGap(0, 27, Short.MAX_VALUE)))
                        .addContainerGap())
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelGeneroPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTipoSangre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
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
        labelDatoQueDesea.setText("DATO QUE DESEA:");

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

        javax.swing.GroupLayout panelFondoBlancoEditarPacienteLayout = new javax.swing.GroupLayout(panelFondoBlancoEditarPaciente);
        panelFondoBlancoEditarPaciente.setLayout(panelFondoBlancoEditarPacienteLayout);
        panelFondoBlancoEditarPacienteLayout.setHorizontalGroup(
            panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDivisionEditarPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboSeleccionarDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelModificarDato))
                        .addGap(84, 84, 84)
                        .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDigiteActualizacion)
                            .addComponent(txtActualizarDato, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                                .addComponent(labelObligatorio)))))))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelFondoBlancoEditarPacienteLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(buttonActualizar)
                .addGap(18, 18, 18)
                .addComponent(buttonCancelarModificacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(27, 27, 27)
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelModificarDato)
                    .addComponent(labelDigiteActualizacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtActualizarDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSeleccionarDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFondoBlancoEditarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonActualizar)
                    .addComponent(buttonCancelarModificacion))
                .addContainerGap(69, Short.MAX_VALUE))
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

        buttonCancelarAgendar.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelarAgendar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonCancelarAgendar.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelarAgendar.setText("CANCELAR LA CITA");
        buttonCancelarAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarAgendarActionPerformed(evt);
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

        javax.swing.GroupLayout panelFondoBlancoAgendarCitaLayout = new javax.swing.GroupLayout(panelFondoBlancoAgendarCita);
        panelFondoBlancoAgendarCita.setLayout(panelFondoBlancoAgendarCitaLayout);
        panelFondoBlancoAgendarCitaLayout.setHorizontalGroup(
            panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(labelTabbedAgendarCita)
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(labelConsultorio))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEsAutomatico)
                            .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelSeleccioneDoctor)
                                .addComponent(cboConsultorio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelHoraCita)
                                .addComponent(cboHoraCita, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelNumeroAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdentificacionCita)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addComponent(buttonAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancelarAgendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelMotivoDeLaCita)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        panelFondoBlancoAgendarCitaLayout.setVerticalGroup(
            panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelTabbedAgendarCita)
                .addGap(18, 18, 18)
                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelConsultorio)
                    .addComponent(labelMotivoDeLaCita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createSequentialGroup()
                        .addComponent(cboConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSeleccioneDoctor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAgendar)
                    .addGroup(panelFondoBlancoAgendarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelHoraCita)
                        .addComponent(buttonCancelarAgendar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelNumeroAgenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdentificacionCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEsAutomatico, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
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

        jButton1.setBackground(new java.awt.Color(0, 0, 201));
        jButton1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BUSCAR");

        javax.swing.GroupLayout panelFondoBlancoEditarCitaLayout = new javax.swing.GroupLayout(panelFondoBlancoEditarCita);
        panelFondoBlancoEditarCita.setLayout(panelFondoBlancoEditarCitaLayout);
        panelFondoBlancoEditarCitaLayout.setHorizontalGroup(
            panelFondoBlancoEditarCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addComponent(jButton1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelObligatorioID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(10, Short.MAX_VALUE))
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
                .addComponent(jButton1)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelEditarLayout = new javax.swing.GroupLayout(panelEditar);
        panelEditar.setLayout(panelEditarLayout);
        panelEditarLayout.setHorizontalGroup(
            panelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondoBlancoEditarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(423, Short.MAX_VALUE))
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

        fondoBlancoPrincipal.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 660, 500));

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
            .addComponent(fondoBlancoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        txtNumeroIdentificacion.setText("");

        //Limpiar campo de contacto del paciente
        txtEmail.setText("");
        txtTelefono.setText("");

        //Limpiar campo de datos del paciente
        txtDireccion.setText("");
        cboEstadoCivil.setSelectedIndex(0);
        cboGenero.setSelectedIndex(0);
        cboTipoSangre.setSelectedIndex(0);

        //Limpiar campo resto de información
        dateChooserFechaNacimiento.setDate(null);
        cboEPS.setSelectedIndex(0);
        txtEdad.setText("");
        JOptionPane.showMessageDialog(null, "Proceso de agendar paciente cancelado", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonCancelarBusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarBusqActionPerformed
        txtBuscarNumeroIdentificacion.setText("");
        cboBuscarNumeroIdentificacion.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, "Proceso de buscar el paciente candelado", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonCancelarBusqActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed

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

        //Desactivar el TextField al inicializar la sección
        txtActualizarDato.setEnabled(false);
        buttonActualizar.setVisible(true);
        buttonCancelarModificacion.setVisible(true);
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void cboSeleccionarDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeleccionarDatoActionPerformed
        String opcionSeleccionada = (String) cboSeleccionarDato.getSelectedItem();
        if ("Seleccionar".equals(opcionSeleccionada)) {
            txtActualizarDato.setEnabled(false);
        } else {
            txtActualizarDato.setEnabled(true);
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

        //Mensaje para el usuario
        JOptionPane.showMessageDialog(null, "Proceso de modificar un dato cancelado", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonCancelarModificacionActionPerformed

    private void buttonCancelarAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarAgendarActionPerformed
        cboConsultorio.setSelectedIndex(0);
        cboDoctor.setSelectedIndex(0);
        cboHoraCita.setSelectedIndex(0);
        txtAreaMotivo.setText("");
        txtIdentificacionCita.setText("");

    }//GEN-LAST:event_buttonCancelarAgendarActionPerformed

    private void txtCargarEPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargarEPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargarEPSActionPerformed

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
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonCancelarAgendar;
    private javax.swing.JButton buttonCancelarBusq;
    private javax.swing.JButton buttonCancelarModificacion;
    private javax.swing.JComboBox<String> cboBuscarNumeroIdentificacion;
    private javax.swing.JComboBox<String> cboConsultorio;
    private javax.swing.JComboBox<String> cboDoctor;
    private javax.swing.JComboBox<String> cboEPS;
    private javax.swing.JComboBox<String> cboEstadoCivil;
    private javax.swing.JComboBox<String> cboGenero;
    private javax.swing.JComboBox<String> cboHoraCita;
    private javax.swing.JComboBox<String> cboSeleccionarDato;
    private javax.swing.JComboBox<String> cboTipoIdentificacion;
    private javax.swing.JComboBox<String> cboTipoSangre;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JLabel labelEsAutomatico;
    private javax.swing.JLabel labelEstadoCivil;
    private javax.swing.JLabel labelFechaNacimiento;
    private javax.swing.JLabel labelGeneroPaciente;
    private javax.swing.JLabel labelHoraCita;
    private javax.swing.JLabel labelIdentificacion;
    private javax.swing.JLabel labelInformación;
    private javax.swing.JLabel labelInformación1;
    private javax.swing.JLabel labelInformaciónCita;
    private javax.swing.JLabel labelJaoSalud;
    private javax.swing.JLabel labelMenúOpciones;
    private javax.swing.JLabel labelModificarDato;
    private javax.swing.JLabel labelMotivoDeLaCita;
    private javax.swing.JLabel labelNombre;
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
    private javax.swing.JLabel labelTabbedInicio;
    private javax.swing.JLabel labelTablaDeCita;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelTipoIdentificación;
    private javax.swing.JLabel labelTipoSangre;
    private javax.swing.JLabel labelVerHistoriales;
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
    private javax.swing.JTable tablaDeCitas;
    private javax.swing.JTextField txtActualizarDato;
    private javax.swing.JTextArea txtAreaMotivo;
    private javax.swing.JTextField txtBuscarCita;
    private javax.swing.JTextField txtBuscarNumeroIdentificacion;
    private javax.swing.JTextField txtCargarEPS;
    private javax.swing.JTextField txtCargarPrimerApellido;
    private javax.swing.JTextField txtCargarPrimerNombre;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdentificacionCita;
    private javax.swing.JTextField txtNumeroIdentificacion;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
