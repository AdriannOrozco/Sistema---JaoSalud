package View;
public class Bienvenida extends javax.swing.JFrame {

    public Bienvenida() {
      initComponents();
      setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoPrincipal = new javax.swing.JPanel();
        iconoPrincipal = new javax.swing.JLabel();
        JLabelJao = new javax.swing.JLabel();
        JlabelSistema = new javax.swing.JLabel();
        botonEntrar = new javax.swing.JButton();
        lineaNegra = new javax.swing.JPanel();
        InformacionLabel = new javax.swing.JLabel();
        iconoColombia = new javax.swing.JLabel();
        JLabelCartagenaDeIndias = new javax.swing.JLabel();
        texto1 = new javax.swing.JLabel();
        texto2 = new javax.swing.JLabel();
        texto3 = new javax.swing.JLabel();
        texto4 = new javax.swing.JLabel();
        texto5 = new javax.swing.JLabel();
        texto6 = new javax.swing.JLabel();
        buttonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondoPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        iconoPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/LogoHospital.png"))); // NOI18N

        JLabelJao.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 48)); // NOI18N
        JLabelJao.setText("JAO SALUD");

        JlabelSistema.setFont(new java.awt.Font("JetBrains Mono", 0, 30)); // NOI18N
        JlabelSistema.setText("SISTEMA HOSPITALARIO");

        botonEntrar.setBackground(new java.awt.Color(255, 204, 0));
        botonEntrar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        botonEntrar.setText("ENTRAR");
        botonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEntrarActionPerformed(evt);
            }
        });

        lineaNegra.setBackground(new java.awt.Color(0, 0, 0));

        InformacionLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        InformacionLabel.setForeground(new java.awt.Color(255, 255, 255));
        InformacionLabel.setText("Contacto: JaoSalud@gmail.com | Teléfono: 0-675-786 |                                              Versión 7.7");

        javax.swing.GroupLayout lineaNegraLayout = new javax.swing.GroupLayout(lineaNegra);
        lineaNegra.setLayout(lineaNegraLayout);
        lineaNegraLayout.setHorizontalGroup(
            lineaNegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineaNegraLayout.createSequentialGroup()
                .addComponent(InformacionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        lineaNegraLayout.setVerticalGroup(
            lineaNegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineaNegraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InformacionLabel)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        iconoColombia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ColombiaInfo.png"))); // NOI18N

        JLabelCartagenaDeIndias.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        JLabelCartagenaDeIndias.setForeground(new java.awt.Color(0, 51, 102));
        JLabelCartagenaDeIndias.setText("COLOMBIA - CARTAGENA DE INDIAS");

        texto1.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        texto1.setText("Un sistema Hospitalario innovador que conecta pacientes,  ");

        texto2.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        texto2.setText("médicos y servicios de salud con: ");

        texto3.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        texto3.setText("1. Eficiencia.");

        texto4.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        texto4.setText("2. Seguridad.");

        texto5.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        texto5.setText("3. Calidad.");

        texto6.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        texto6.setText("Para brindarte la mejor atención en todo en momento.");

        buttonSalir.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalir.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        buttonSalir.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalir.setText("SALIR DEL SISTEMA");
        buttonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoPrincipalLayout = new javax.swing.GroupLayout(fondoPrincipal);
        fondoPrincipal.setLayout(fondoPrincipalLayout);
        fondoPrincipalLayout.setHorizontalGroup(
            fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineaNegra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fondoPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(texto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(fondoPrincipalLayout.createSequentialGroup()
                        .addGroup(fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLabelJao)
                            .addGroup(fondoPrincipalLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(texto3)
                                    .addComponent(texto4)
                                    .addComponent(texto5)))
                            .addComponent(JlabelSistema)
                            .addComponent(texto6)
                            .addGroup(fondoPrincipalLayout.createSequentialGroup()
                                .addComponent(iconoColombia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JLabelCartagenaDeIndias))
                            .addGroup(fondoPrincipalLayout.createSequentialGroup()
                                .addComponent(botonEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonSalir)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconoPrincipal))
        );
        fondoPrincipalLayout.setVerticalGroup(
            fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fondoPrincipalLayout.createSequentialGroup()
                        .addGroup(fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(iconoColombia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLabelCartagenaDeIndias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(81, 81, 81)
                        .addComponent(JLabelJao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JlabelSistema)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texto3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(texto4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(texto5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texto6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(fondoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lineaNegra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEntrarActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonEntrarActionPerformed

    private void buttonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirActionPerformed
    this.dispose();
    }//GEN-LAST:event_buttonSalirActionPerformed

    
    public static void main(String args[]) {    
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        new Bienvenida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InformacionLabel;
    private javax.swing.JLabel JLabelCartagenaDeIndias;
    private javax.swing.JLabel JLabelJao;
    private javax.swing.JLabel JlabelSistema;
    private javax.swing.JButton botonEntrar;
    private javax.swing.JButton buttonSalir;
    private javax.swing.JPanel fondoPrincipal;
    private javax.swing.JLabel iconoColombia;
    private javax.swing.JLabel iconoPrincipal;
    private javax.swing.JPanel lineaNegra;
    private javax.swing.JLabel texto1;
    private javax.swing.JLabel texto2;
    private javax.swing.JLabel texto3;
    private javax.swing.JLabel texto4;
    private javax.swing.JLabel texto5;
    private javax.swing.JLabel texto6;
    // End of variables declaration//GEN-END:variables
}
