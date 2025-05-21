package View;
import Persistencia.Consultorios.MostrarTablaConsultorios.MostrarTablaConsultorios;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class infoConsultorios extends javax.swing.JFrame {

    MostrarTablaConsultorios tabla = new MostrarTablaConsultorios();
    
    public infoConsultorios() {
        initComponents();
        ubicarFrame();
        tabla.MostrarConsultorios(tablaConsultorios);
        
    }
    
    public void ubicarFrame(){
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int x = screenWidth - this.getWidth();
        int y = 0;
        this.setLocation(x,y);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoBlanco = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaConsultorios = new javax.swing.JTable();
        iconJaoSalud = new javax.swing.JLabel();
        labelJaoSalud = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRealodInicio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondoBlanco.setBackground(new java.awt.Color(255, 255, 255));

        tablaConsultorios.setBackground(new java.awt.Color(225, 225, 225));
        tablaConsultorios.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tablaConsultorios.setForeground(new java.awt.Color(0, 0, 0));
        tablaConsultorios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaConsultorios);

        iconJaoSalud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Salud.png"))); // NOI18N

        labelJaoSalud.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 30)); // NOI18N
        labelJaoSalud.setForeground(new java.awt.Color(0, 0, 102));
        labelJaoSalud.setText("CONSULTORIOS - JAO SALUD");

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cerrarSesión.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("CERRAR");

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        lblRealodInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/reiniciarInicio.png"))); // NOI18N
        lblRealodInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRealodInicioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBlancoLayout = new javax.swing.GroupLayout(fondoBlanco);
        fondoBlanco.setLayout(fondoBlancoLayout);
        fondoBlancoLayout.setHorizontalGroup(
            fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fondoBlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconJaoSalud)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelJaoSalud)
                .addGap(59, 59, 59)
                .addGroup(fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fondoBlancoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRealodInicio)
                .addGap(18, 18, 18))
        );
        fondoBlancoLayout.setVerticalGroup(
            fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoBlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(iconJaoSalud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelJaoSalud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(fondoBlancoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fondoBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRealodInicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
    this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void lblRealodInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRealodInicioMouseClicked

        
        tabla.refrescarConsultorios(tablaConsultorios);
        JOptionPane.showMessageDialog(null, "Campos actualizados", "Limpieza", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblRealodInicioMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new infoConsultorios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fondoBlanco;
    private javax.swing.JLabel iconJaoSalud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelJaoSalud;
    private javax.swing.JLabel lblRealodInicio;
    private javax.swing.JTable tablaConsultorios;
    // End of variables declaration//GEN-END:variables
}
