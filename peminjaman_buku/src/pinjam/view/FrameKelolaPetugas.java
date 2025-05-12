/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pinjam.view;
import pinjam.koneksi.koneksi;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FrameKelolaPetugas extends javax.swing.JFrame {
    Connection connection;
    DefaultTableModel model;

    /**
     * Creates new form FrameKelolaPetugas
     */
    public FrameKelolaPetugas() {
        initComponents();
        connection = pinjam.koneksi.koneksi.getConnection();
        //panggil method
        ambilDataTabel();
        //kursor aktif di tIDProdi
        tCari.requestFocus();
    }
    
    private void ambilDataTabel() {
        model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        try{
            Statement stat = connection.createStatement();
            String sql = "SELECT * FROM tblpetugas";
            ResultSet res =stat.executeQuery(sql);
            while(res.next ()){
                Object[ ] obj = new Object[6];
                obj[0] = res.getString("id_petugas");
                obj[1] = res.getString("nama_petugas");
                obj[2] = res.getString("password");
                obj[3] = res.getString("alamat");
                obj[4] = res.getString("no_telp");
                obj[5] = res.getString("email");
                model.addRow(obj);
            }
        }catch(SQLException err) {
            err.printStackTrace();
        }
    }
    
    private void refresh(){
            model = (DefaultTableModel) Tabel.getModel();
            model.setRowCount(0);
            ambilDataTabel();
    }
    
    private void cari(){
        model = (DefaultTableModel) Tabel.getModel();
    PreparedStatement statement = null;
    String sql = "SELECT * FROM tblpetugas WHERE nama_petugas like ?";
    try {
        statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + tCari.getText() + "%");
        ResultSet res = statement.executeQuery();
            while(res.next()){
                Object[ ] obj = new Object[6];
                obj[0] = res.getString("id_petugas");
                obj[1] = res.getString("nama_petugas");
                obj[2] = res.getString("password");
                obj[3] = res.getString("alamat");
                obj[4] = res.getString("no_telp");
                obj[5] = res.getString("email");
                model.addRow(obj);
            }
                } catch (SQLException err) {
                    err.printStackTrace();
                }
    }
    
    private void reset(){
        tIDPetugas.setText("");
        tNama.setText("");
        tPass.setText("");
        tAlamat.setText("");
        tTelp.setText("");
        tEmail.setText("");
        tIDPetugas.setEditable(true);
        bSimpan.setEnabled(true);
    }
    
    private void simpan() {
        PreparedStatement statement = null;
        String sql = "INSERT INTO tblpetugas (id_petugas,nama_petugas,password,alamat,no_telp,email) VALUES(?,?,?,?,?,?);";
                try {
                    statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, tIDPetugas.getText());
                    statement.setString(2, tNama.getText());
                    statement.setString(3, tPass.getText());
                    statement.setString(4, tAlamat.getText());
                    statement.setString(5, tTelp.getText());
                    statement.setString(6, tEmail.getText());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
    }
    
    private void hapus(){
        PreparedStatement statement = null;
        String sql = "DELETE FROM tblpetugas WHERE id_petugas=?";
                try {
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, tIDPetugas.getText());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
        }
    }
    
    private void ubah() {
            PreparedStatement statement = null;
        String sql = "UPDATE tblpetugas SET "
                + "nama_petugas=?,password=?,alamat=?,no_telp=?,email=? "
                + "WHERE id_petugas=?";
                try {
                    statement = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, tNama.getText());
                    statement.setString(2, tPass.getText());
                    statement.setString(3, tAlamat.getText());
                    statement.setString(4, tTelp.getText());
                    statement.setString(5, tEmail.getText());
                    statement.setString(6, tIDPetugas.getText());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                     }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tIDPetugas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tPass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        tTelp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tEmail = new javax.swing.JTextField();
        bSimpan = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        bRefresh = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Kelola Petugas");

        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id Petugas", "Nama Petugas", "Password", "Alamat", "No Telp", "Email"
            }
        ));
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel);

        jLabel2.setText("ID Petugas");

        jLabel3.setText("Nama Petugas");

        jLabel4.setText("Password");

        jLabel5.setText("Alamat");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane2.setViewportView(tAlamat);

        jLabel6.setText("No Telp");

        tTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTelpActionPerformed(evt);
            }
        });

        jLabel7.setText("Email");

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bHapus.setText("Hapus");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        bUbah.setText("Ubah");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        jLabel8.setText("Cari Nama Petugas :");

        tCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCariActionPerformed(evt);
            }
        });

        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        bRefresh.setText("Refresh");
        bRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRefreshActionPerformed(evt);
            }
        });

        jLabel9.setText("Register Petugas :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(tIDPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(bSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bReset, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bCari, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bRefresh))
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel4)
                                            .addComponent(tPass, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(309, 309, 309))))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCari)
                    .addComponent(bRefresh))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tIDPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSimpan)
                            .addComponent(bHapus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bUbah)
                            .addComponent(bReset)))
                    .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTelpActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        if(!tIDPetugas.getText().trim().isEmpty()) {
            int alert = JOptionPane.showConfirmDialog(this, "Anda yakin ingin memghapus pesan ini?", 
                    "Notifikasi", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(alert == JOptionPane.YES_OPTION) {
                hapus();
                refresh();
                reset();
                JOptionPane.showMessageDialog(this, "Petugas berhasil dihapus", 
                        "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                    "pilih data terlebih dahulu",
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        if(!tIDPetugas.getText().trim().isEmpty()) {
            if(!tIDPetugas.getText().trim().isEmpty() &&
                    !tNama.getText().trim().isEmpty() &&
                    !tPass.getText().trim().isEmpty() &&
                    !tAlamat.getText().trim().isEmpty() &&
                    !tTelp.getText().trim().isEmpty() &&
                    !tEmail.getText().trim().isEmpty()){
                //panggil metode ubah
                ubah();
                refresh();
                reset();
                JOptionPane.showMessageDialog(this,
                        "Petugas berhasil diubah",
                        "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Lengkapi form terlebih dahulu!",
                        "Notifikasi", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void tCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCariActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        if(!tCari.getText().trim().isEmpty()) {
            model = (DefaultTableModel) Tabel.getModel();
            model.setRowCount(0);
            cari();
        } else {
                JOptionPane.showMessageDialog(this, 
                        "Masukkan nama petugas yang ingin dicari!",
                        "Notifikasi", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_bCariActionPerformed

    private void bRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRefreshActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_bRefreshActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if(!tIDPetugas.getText().trim().isEmpty()
                && !tNama.getText().trim().isEmpty()
                && !tPass.getText().trim().isEmpty()
                && !tAlamat.getText().trim().isEmpty()
                && !tTelp.getText().trim().isEmpty()
                && !tEmail.getText().trim().isEmpty()){
            simpan();
            refresh();
            reset();
            JOptionPane.showMessageDialog(this, "Petugas Baerhasil ditambahkan", 
                    "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Lengkapi form terlebih dahulu", 
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        tIDPetugas.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 0).toString());
        tNama.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 1).toString());
        tPass.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 2).toString());
        tAlamat.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 3).toString());
        tTelp.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 4).toString());
        tEmail.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 5).toString());
        tIDPetugas.setEditable(false);
        bSimpan.setEnabled(false);
    }//GEN-LAST:event_TabelMouseClicked

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bResetActionPerformed

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
            java.util.logging.Logger.getLogger(FrameKelolaPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameKelolaPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameKelolaPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameKelolaPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameKelolaPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bRefresh;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JTextField tCari;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tIDPetugas;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tPass;
    private javax.swing.JTextField tTelp;
    // End of variables declaration//GEN-END:variables
}
