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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author user
 */
public class FrameAbsensi extends javax.swing.JFrame {
    Connection connection;
    DefaultTableModel model;
    /**
     * Creates new form FrameAbsensi
     */
    public FrameAbsensi() {
        initComponents();
        connection = pinjam.koneksi.koneksi.getConnection();
        //panggil method
        ambilDataTabel();
        
        getCmbNama();
        
        tTgl.setText(new Date().toString());
        
        tAbsenID.requestFocus();
    }
    
    private void ambilDataTabel() {
        model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        try{
            Statement stat = connection.createStatement();
            String sql = "SELECT * FROM tblabsensi ";
            ResultSet res =stat.executeQuery(sql);
            while(res.next ()){
                Object[ ] obj = new Object[4];
                obj[0] = res.getString("Id_Absensi");
                obj[1] = res.getString("id_petugas");
                obj[2] = res.getString("tgl_absensi");
                obj[3] = res.getString("kehadiran");
                model.addRow(obj);
            }
        }catch(SQLException err) {
            err.printStackTrace();
        }
    }
    
    
    
    private void getCmbNama(){
        cmbNama.removeAllItems();
        try{
            Statement stat = connection.createStatement();
            String sql = "SELECT * FROM tblpetugas";
            ResultSet res = stat.executeQuery(sql);
            while(res.next ()){
                cmbNama.addItem(res.getString("nama_petugas"));
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
    
    //method reset isian data di textfield dan nonaktifkan
    //button simpan
    private void reset(){
        tAbsenID.setText("");
        tAbsenID.setEditable(true);
        bSimpan.setEnabled(true);
        
    }
    
    
    private void simpan() {
        PreparedStatement statement = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        String sql = "INSERT INTO tblabsensi "
                + "(id_absensi,id_petugas,tgl_absensi,kehadiran) "
                + "VALUE(?,?,?,?);";
                try {
                    statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, tAbsenID.getText());
                    statement.setString(2, cmbNama.getSelectedItem().toString());
                    statement.setString(3, formattedDate);
                    statement.setString(4, cmbKehadiran.getSelectedItem().toString());
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        String sql = "UPDATE tblabsensi SET "
                + "id_petugas=?,tgl_absensi=?,kehadiran=? "
                + "WHERE id_absensi=?";
                try {
                    statement = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, cmbNama.getSelectedItem().toString());
                    statement.setString(2, formattedDate);
                    statement.setString(3, cmbKehadiran.getSelectedItem().toString());
                    statement.setString(4, tAbsenID.getText());
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
    
    private void hapus() {
            PreparedStatement statement = null;
        String sql = "DELETE FROM tblabsensi WHERE id_absensi=?";
                try {
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, tAbsenID.getText());
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
    
    private void cari() {
    model = (DefaultTableModel) Tabel.getModel();
    PreparedStatement statement = null;
    String sql = "SELECT * FROM tblpetugas, tblabsensi WHERE "
            + "tblpetugas.id_petugas=tblabsensi.id_petugas AND "
            + "nama_petugas like ?";
    try {
        statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + tCari.getText() + "%");
        ResultSet res = statement.executeQuery();
            while(res.next()){
                Object[ ] obj = new Object[4];
                obj[0] = res.getString("id_absensi");
                obj[1] = res.getString("nama_petugas");
                obj[2] = res.getString("tgl_absensi");
                obj[3] = res.getString("kehadiran");
                model.addRow(obj);
            }
                } catch (SQLException err) {
                    err.printStackTrace();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tTgl = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbKehadiran = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        tAbsenID = new javax.swing.JTextField();
        bSimpan = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        cmbNama = new javax.swing.JComboBox();
        bRefresh = new javax.swing.JButton();
        bReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id_Absensi", "nama_petugas", "tgl_absensi", "kehadiran"
            }
        ));
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Absensi Petugas");

        jLabel2.setText("Tgl Absen :");

        jLabel3.setText("Nama Petugas :");

        tTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTglActionPerformed(evt);
            }
        });

        jLabel4.setText("Status Kehadiran :");

        cmbKehadiran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hadir", "Tidak Hadir", " " }));
        cmbKehadiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKehadiranActionPerformed(evt);
            }
        });

        jLabel5.setText("ID Absensi :");

        tAbsenID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tAbsenIDActionPerformed(evt);
            }
        });

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

        jLabel6.setText("Cari Nama Petugas :");

        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        cmbNama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bRefresh.setText("Refresh");
        bRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRefreshActionPerformed(evt);
            }
        });

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(351, 351, 351))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(tAbsenID, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(cmbNama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(130, 130, 130)
                                                .addComponent(jLabel4))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(cmbKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tCari)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(bCari, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bReset, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(bUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bHapus)
                        .addGap(10, 10, 10)
                        .addComponent(bSimpan)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tAbsenID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bSimpan)
                        .addComponent(bHapus)
                        .addComponent(bUbah)
                        .addComponent(bCari))
                    .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bRefresh)
                    .addComponent(bReset))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tAbsenIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tAbsenIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tAbsenIDActionPerformed

    private void tTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTglActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        if(!tAbsenID.getText().trim().isEmpty()) {
            if(!tAbsenID.getText().trim().isEmpty() &&
                    !tTgl.getText().trim().isEmpty()){
                //panggil metode ubah
                ubah();
                refresh();
                reset();
                JOptionPane.showMessageDialog(this,
                        "Absen berhasil diubah",
                        "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Lengkapi form terlebih dahulu!",
                        "Notifikasi", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void cmbKehadiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKehadiranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKehadiranActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if(!tAbsenID.getText().trim().isEmpty()
                && !tTgl.getText().trim().isEmpty()){
            simpan();
            refresh();
            reset();
            JOptionPane.showMessageDialog(this, "Anda Baerhasil absen!", 
                    "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Lengkapi form terlebih dahulu", 
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        tAbsenID.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 0).toString());
        cmbNama.setSelectedItem(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 1).toString());
        tTgl.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 2).toString());
        cmbKehadiran.setSelectedItem(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 3).toString());
        tAbsenID.setEditable(false);
        bSimpan.setEnabled(false);
    }//GEN-LAST:event_TabelMouseClicked

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        if(!tAbsenID.getText().trim().isEmpty()) {
            int alert = JOptionPane.showConfirmDialog(this, "Anda yakin ingin memghapus pesan ini?", 
                    "Notifikasi", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(alert == JOptionPane.YES_OPTION) {
                hapus();
                refresh();
                reset();
                JOptionPane.showMessageDialog(this, "Absen berhasil dihapus", 
                        "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                    "pilih data terlebih dahulu",
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bHapusActionPerformed

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
            java.util.logging.Logger.getLogger(FrameAbsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameAbsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameAbsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameAbsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameAbsensi().setVisible(true);
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
    private javax.swing.JComboBox cmbKehadiran;
    private javax.swing.JComboBox cmbNama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tAbsenID;
    private javax.swing.JTextField tCari;
    private javax.swing.JTextField tTgl;
    // End of variables declaration//GEN-END:variables
}
