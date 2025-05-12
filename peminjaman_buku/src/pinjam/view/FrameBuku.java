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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author user
 */
public class FrameBuku extends javax.swing.JFrame {
    Connection connection;
    DefaultTableModel model;
    /**
     * Creates new form FrameBuku
     */
    public FrameBuku() {
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
            String sql = "SELECT * FROM tblbuku ";
            ResultSet res =stat.executeQuery(sql);
            while(res.next ()){
                Object[ ] obj = new Object[6];
                obj[0] = res.getString("id_buku");
                obj[1] = res.getString("ISBN");
                obj[2] = res.getString("nama_buku");
                obj[3] = res.getString("pengarang");
                obj[4] = res.getString("penerbit");
                obj[5] = res.getString("thn_terbit");
                
                model.addRow(obj);
            }
        }catch(SQLException err) {
            err.printStackTrace();
        }
    }
    
    private int getIDStatus(String status_buku){
        int id = 0;
        try {
            PreparedStatement st = connection.prepareStatement
                    ("SELECT id_peminjam FROM tbluser WHERE status_nuku=?",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, status_buku);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id_peminjaman");
            }
            return id;
        } catch (SQLException ex) {
                Logger.getLogger(FrameBuku.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private void refresh(){
            model = (DefaultTableModel) Tabel.getModel();
            model.setRowCount(0);
            ambilDataTabel();
    }
    
    private void cari(){
        model = (DefaultTableModel) Tabel.getModel();
    PreparedStatement statement = null;
    String sql = "SELECT * FROM tblbuku WHERE nama_buku like ?";
    try {
        statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + tCari.getText() + "%");
        ResultSet res = statement.executeQuery();
            while(res.next()){
                Object[ ] obj = new Object[7];
                obj[0] = res.getString("id_buku");
                obj[1] = res.getString("ISBN");
                obj[2] = res.getString("nama_buku");
                obj[3] = res.getString("pengarang");
                obj[4] = res.getString("penerbit");
                obj[5] = res.getString("thn_terbit");
                obj[6] = res.getString("status_buku");
                model.addRow(obj);
            }
                } catch (SQLException err) {
                    err.printStackTrace();
                }
    }
    
    private void reset(){
        tIDBuku.setText("");
        tISBN.setText("");
        tNama.setText("");
        tPengarang.setText("");
        tPenerbit.setText("");
        tTahun.setText("");
        tIDBuku.setEditable(true);
        bSimpan.setEnabled(true);
    }
    
    private void simpan() {
        PreparedStatement statement = null;
        String sql = "INSERT INTO tblbuku (id_buku,ISBN,nama_buku,pengarang,penerbit,thn_terbit)" 
                + "VALUES(?,?,?,?,?,?);";
                try {
                    statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, tIDBuku.getText());
                    statement.setString(2, tISBN.getText());
                    statement.setString(3, tNama.getText());
                    statement.setString(4, tPengarang.getText());
                    statement.setString(5, tPenerbit.getText());
                    statement.setString(6, tTahun.getText());
                    //statement.setInt(7, getIDStatus(cmbStatus.getSelectedItem().toString()));
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
        String sql = "DELETE FROM tblbuku WHERE id_buku=?";
                try {
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, tIDBuku.getText());
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
        String sql = "UPDATE tblbuku SET "
                + "isbn=?,nama_buku=?,pengarang=?,penerbit=?,thn_terbit=? "
                + "WHERE id_buku=?";
                try {
                    statement = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, tISBN.getText());
                    statement.setString(2, tNama.getText());
                    statement.setString(3, tPengarang.getText());
                    statement.setString(4, tPenerbit.getText());
                    statement.setString(5, tTahun.getText());
                    //statement.setString(6, cmbStatus.getSelectedItem().toString());
                    statement.setString(6, tIDBuku.getText());
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
        tCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        bRefresh = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tIDBuku = new javax.swing.JTextField();
        tISBN = new javax.swing.JTextField();
        tNama = new javax.swing.JTextField();
        tPengarang = new javax.swing.JTextField();
        tPenerbit = new javax.swing.JTextField();
        tTahun = new javax.swing.JTextField();
        bSimpan = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Daftar Buku");

        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id_buku", "ISBN", "nama_buku", "Pengarang", "Penerbit", "thn_terbit"
            }
        ));
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel);

        jLabel2.setText("Cari Buku :");

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

        jLabel3.setText("Input Buku :");

        jLabel4.setText("ID Buku :");

        jLabel5.setText("Tahun Terbit :");

        jLabel7.setText("Penerbit :");

        jLabel8.setText("ISBN :");

        jLabel9.setText("Pengarang :");

        jLabel10.setText("Nama Buku :");

        bSimpan.setText("tambah");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bUbah.setText("ubah");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        bHapus.setText("hapus");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        bReset.setText("reset");
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
                .addGap(373, 373, 373)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCari, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel7))
                                        .addGap(87, 87, 87)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(tISBN)
                                            .addComponent(tTahun, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                                    .addComponent(tIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(bRefresh)
                            .addComponent(tPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bReset)
                                    .addComponent(bUbah)))))
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCari)
                    .addComponent(bRefresh))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(bSimpan)
                    .addComponent(bUbah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bHapus)
                    .addComponent(bReset))
                .addGap(53, 53, 53))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCariActionPerformed

    private void bRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRefreshActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_bRefreshActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        if(!tCari.getText().trim().isEmpty()) {
            model = (DefaultTableModel) Tabel.getModel();
            model.setRowCount(0);
            cari();
        } else {
                JOptionPane.showMessageDialog(this, 
                        "Masukkan nama buku yang ingin dicari!",
                        "Notifikasi", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_bCariActionPerformed

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        tIDBuku.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 0).toString());
        tISBN.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 1).toString());
        tNama.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 2).toString());
        tPengarang.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 3).toString());
        tPenerbit.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 4).toString());
        tTahun.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 5).toString());
        //cmbStatus.setSelectedItem(Tabel.getModel().getValueAt
                //(Tabel.getSelectedRow(), 6).toString());
        tIDBuku.setEditable(false);
        bSimpan.setEnabled(false);
    }//GEN-LAST:event_TabelMouseClicked

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if(!tIDBuku.getText().trim().isEmpty()
                && !tISBN.getText().trim().isEmpty()
                && !tNama.getText().trim().isEmpty()
                && !tPengarang.getText().trim().isEmpty()
                && !tPenerbit.getText().trim().isEmpty()
                && !tTahun.getText().trim().isEmpty()){
            simpan();
            refresh();
            reset();
            JOptionPane.showMessageDialog(this, "Buku Berhasil ditambahkan", 
                    "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Lengkapi form terlebih dahulu", 
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        if(!tIDBuku.getText().trim().isEmpty()) {
        if(!tIDBuku.getText().trim().isEmpty()
                && !tISBN.getText().trim().isEmpty()
                && !tNama.getText().trim().isEmpty()
                && !tPengarang.getText().trim().isEmpty()
                && !tPenerbit.getText().trim().isEmpty()
                && !tTahun.getText().trim().isEmpty()){
            ubah();
            refresh();
            reset();
            JOptionPane.showMessageDialog(this, "Buku Baerhasil diubah", 
                    "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Lengkapi form terlebih dahulu", 
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
      }
    }//GEN-LAST:event_bUbahActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        if(!tIDBuku.getText().trim().isEmpty()) {
            int alert = JOptionPane.showConfirmDialog(this, "Anda yakin ingin memghapus pesan ini?", 
                    "Notifikasi", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(alert == JOptionPane.YES_OPTION) {
                hapus();
                refresh();
                reset();
                JOptionPane.showMessageDialog(this, "buku berhasil dihapus", 
                        "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                    "pilih data terlebih dahulu",
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bHapusActionPerformed

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
            java.util.logging.Logger.getLogger(FrameBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameBuku().setVisible(true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tCari;
    private javax.swing.JTextField tIDBuku;
    private javax.swing.JTextField tISBN;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tPenerbit;
    private javax.swing.JTextField tPengarang;
    private javax.swing.JTextField tTahun;
    // End of variables declaration//GEN-END:variables
}
