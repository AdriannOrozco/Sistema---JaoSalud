package View;

import UseCase.buscarUsuario;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoBlanco = new javax.swing.JPanel();
        lineaNegra = new javax.swing.JPanel();
        labelJAO = new javax.swing.JLabel();
        labelJAO1 = new javax.swing.JLabel();
        iconHospital = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelInicioSesión = new javax.swing.JLabel();
        cboRol = new javax.swing.JComboBox<>();
        labelSeleccione = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        labelUsuario1 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        buttonAtrás = new javax.swing.JButton();
        buttonEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondoBlanco.setBackground(new java.awt.Color(255, 255, 255));

        lineaNegra.setBackground(new java.awt.Color(0, 51, 102));

        labelJAO.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 60)); // NOI18N
        labelJAO.setForeground(new java.awt.Color(255, 255, 255));
        labelJAO.setText("JAO");

        labelJAO1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 60)); // NOI18N
        labelJAO1.setForeground(new java.awt.Color(255, 255, 255));
        labelJAO1.setText("SALUD");

        iconHospital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/LoginHospital.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("¡CUIDAMOS TU SALUD!");

        javax.swing.GroupLayout lineaNegraLayout = new javax.swing.GroupLayout(lineaNegra);
        lineaNegra.setLayout(lineaNegraLayout);
        lineaNegraLayout.setHorizontalGroup(
            lineaNegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineaNegraLayout.createSequentialGroup()
                .addGroup(lineaNegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconHospital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lineaNegraLayout.createSequentialGroup()
                        .addGroup(lineaNegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lineaNegraLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(labelJAO1))
                            .addGroup(lineaNegraLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel1))
                            .addGroup(lineaNegraLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(labelJAO)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        lineaNegraLayout.setVerticalGroup(
            lineaNegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineaNegraLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(labelJAO, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelJAO1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(iconHospital, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        labelInicioSesión.setBackground(new java.awt.Color(0, 0, 0));
        labelInicioSesión.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        labelInicioSesión.setText("INICIO DE SESIÓN");

        cboRol.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        cboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Administrador", "Recepcionista", "Médico", "Paciente" }));

        labelSeleccione.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelSeleccione.setText("1. SELECCIONE SU ROL:");

        labelUsuario.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelUsuario.setText("2. DIGITE SU USUARIO:");

        txtUsuario.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        labelUsuario1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        labelUsuario1.setText("3. DIGITE SU CONTRASEÑA:");

        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });

        buttonAtrás.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/atrás.png"))); // NOI18N
        buttonAtrás.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtrásActionPerformed(evt);
            }
        });

        buttonEntrar.setBackground(new java.awt.Color(0, 51, 102));
        buttonEntrar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        buttonEntrar.setForeground(new java.awt.Color(255, 255, 255));
        buttonEntrar.setText("¡ENTRAR!");
        buttonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoBlancoLayout = new javax.swing.GroupLayout(fondoBlanco);
        fondoBlanco.setLayout(fondoBlancoLayout);
        fondoBlancoLayout.setHorizontalGroup(
            fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoBlancoLayout.createSequentialGroup()
                .addGroup(fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoBlancoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonAtrás))
                    .addGroup(fondoBlancoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelSeleccione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsuario)
                            .addComponent(labelUsuario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtContraseña)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoBlancoLayout.createSequentialGroup()
                                .addComponent(buttonEntrar)
                                .addGap(81, 81, 81))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoBlancoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelInicioSesión)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(lineaNegra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fondoBlancoLayout.setVerticalGroup(
            fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineaNegra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fondoBlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAtrás)
                .addGap(14, 14, 14)
                .addComponent(labelInicioSesión)
                .addGap(18, 18, 18)
                .addComponent(labelSeleccione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelUsuario1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonEntrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoBlanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoBlanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void buttonAtrásActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtrásActionPerformed
        new Bienvenida().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonAtrásActionPerformed

    private void buttonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEntrarActionPerformed
        buscarUsuario buscar = new buscarUsuario();
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();
        String rol = cboRol.getSelectedItem().toString();
        String val1 = "Recepcionista";
        String val2 = "Paciente";
        String val3 = "Medico";
        String val4 = "Administrador";

        if (usuario == null || contraseña == null || rol.equalsIgnoreCase("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Hay datos sin completar.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        } else {

            if (buscar.accesoUsuario(usuario, contraseña, rol)) {

                if (rol.equals(val1)) {
                    new Recepcionista().setVisible(true);
                    this.dispose();
                }

                if (rol.equals(val2)) {
                    //Frame paciente
                    this.dispose();
                }

                if (rol.equals(val3)) {
                    //Frame médico
                    this.dispose();
                }

                if (rol.equals(val4)) {
                    //Frame adminstrador
                    this.dispose();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas.", "Error de login", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_buttonEntrarActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAtrás;
    private javax.swing.JButton buttonEntrar;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JPanel fondoBlanco;
    private javax.swing.JLabel iconHospital;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelInicioSesión;
    private javax.swing.JLabel labelJAO;
    private javax.swing.JLabel labelJAO1;
    private javax.swing.JLabel labelSeleccione;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelUsuario1;
    private javax.swing.JPanel lineaNegra;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
