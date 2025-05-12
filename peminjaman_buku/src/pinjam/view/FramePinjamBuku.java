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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author user
 */
public class FramePinjamBuku extends javax.swing.JFrame {
    Connection connection;
    DefaultTableModel model;
    /**
     * Creates new form FramePinjamBuku
     */
    public FramePinjamBuku() {
        initComponents();
        connection = pinjam.koneksi.koneksi.getConnection();
        //panggil method
        ambilTblBuku();
        ambilDataTabel();
        //autoFillUserData();
        tTgl.setText(new Date().toString());
        tCari.requestFocus();
    }
    private void ambilDataTabel() {
        model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        try{
            Statement stat = connection.createStatement();
            String sql = "SELECT * FROM tblpeminjam ";
            ResultSet res =stat.executeQuery(sql);
            while(res.next ()){
                Object[ ] obj = new Object[8];
                obj[0] = res.getString("id_peminjam");
                obj[1] = res.getString("id_user");
                obj[2] = res.getString("alamat");
                obj[3] = res.getString("no_telp");
                obj[4] = res.getString("email");
                obj[5] = res.getString("tgl_pinjam");
                obj[6] = res.getString("id_buku");
                obj[7] = res.getString("status_buku");
                model.addRow(obj);
            }
        }catch(SQLException err) {
            err.printStackTrace();
        }
    }
    
    private void ambilTblBuku() {
        model = (DefaultTableModel) TblBuku.getModel();
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
    
    
    
    
    
    private void refresh(){
            model = (DefaultTableModel) Tabel.getModel();
            model = (DefaultTableModel) TblBuku.getModel();
            model.setRowCount(0);
            ambilDataTabel();
            ambilTblBuku();
    }
    
    private void cari(){
        model = (DefaultTableModel) TblBuku.getModel();
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
        tAlamat.setText("");
        tTelp.setText("");
        tEmail.setText("");
        tIDPeminjam.setEditable(false);
        bSimpan.setEnabled(true);
    }
    
    private void simpan() {
        PreparedStatement statement = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        String sql = "INSERT INTO tblpeminjam (id_peminjam, id_user, alamat, no_telp, email, tgl_pinjam, id_buku, status_buku) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                try {
                    statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, tIDPeminjam.getText());
                    statement.setString(2, tUsername.getText());
                    statement.setString(3, tAlamat.getText());
                    statement.setString(4, tTelp.getText());
                    statement.setString(5, tEmail.getText());
                    statement.setString(6, formattedDate);
                    statement.setString(7, tBuku.getText());
                    statement.setString(8, cmbStatusBuku.getSelectedItem().toString());
                    statement.executeUpdate();
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idPeminjamBaru = generatedKeys.getInt(1); // Gunakan idPeminjamBaru jika diperlukan
                    }
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
        String sql = "UPDATE tblpeminjam SET "
                + "id_user=?,alamat=?,no_telp=?,email=?, tgl_pinjam=?, id_buku=?,status_buku=? "
                + "WHERE id_peminjam=?";
                try {
                    statement = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, tUsername.getText());
                    statement.setString(2, tAlamat.getText());
                    statement.setString(3, tTelp.getText());
                    statement.setString(4, tEmail.getText());
                    statement.setString(5, formattedDate);
                    statement.setString(6, tBuku.getText());
                    statement.setString(7, cmbStatusBuku.getSelectedItem().toString());
                    statement.setString(8, tIDPeminjam.getText());
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        tIDPeminjam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        tTelp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bSimpan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tTgl = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmbStatusBuku = new javax.swing.JComboBox();
        bReset = new java.awt.Button();
        bUbah = new javax.swing.JButton();
        tUsername = new javax.swing.JTextField();
        tBuku = new javax.swing.JTextField();
        tCari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bCari = new javax.swing.JButton();
        bRefresh = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id_peminjam", "username", "alamat", "no_telp", "email", "tgl_pinjam", "nama_buku", "status_buku"
            }
        ));
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel);

        jLabel3.setText("ID");

        jLabel4.setText("Username");

        jLabel5.setText("alamat");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane2.setViewportView(tAlamat);

        jLabel6.setText("no_telp");

        tTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTelpActionPerformed(evt);
            }
        });

        jLabel7.setText("email");

        jLabel8.setText("nama_buku");

        jLabel9.setText("Status Buku");

        bSimpan.setText("simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        jLabel10.setText("tgl_pinjam");

        jLabel11.setText("Nama Buku");

        jLabel12.setText("Status Buku");

        cmbStatusBuku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "tersedia", "dipinjam" }));

        bReset.setLabel("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        bUbah.setText("Update");
        bUbah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bUbahMouseClicked(evt);
            }
        });
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        tUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tUsernameActionPerformed(evt);
            }
        });

        tBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBukuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tIDPeminjam)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bSimpan)
                                .addGap(21, 21, 21)
                                .addComponent(bReset, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addComponent(tUsername)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addComponent(tTelp)
                                    .addComponent(jLabel10)
                                    .addComponent(tTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbStatusBuku, 0, 158, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(tBuku)))
                            .addComponent(bUbah))
                        .addContainerGap(135, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(642, 642, 642)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tIDPeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatusBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bUbah))
                            .addComponent(bReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(57, 57, 57)
                .addComponent(jLabel9)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(76, 76, 76))
        );

        jLabel2.setText("Cari :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Pilih Buku Yang Ingin di Pinjam");

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

        TblBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id_buku", "ISBN", "nama_buku", "pengarang", "penerbit", "thn_terbit"
            }
        ));
        TblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblBukuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TblBuku);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bCari, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bRefresh))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jLabel1)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 876, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCari)
                    .addComponent(bRefresh))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        if(!tCari.getText().trim().isEmpty()) {
            model = (DefaultTableModel) TblBuku.getModel();
            model.setRowCount(0);
            cari();
        } else {
            JOptionPane.showMessageDialog(this,
                "Masukkan nama buku yang ingin dicari!",
                "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bCariActionPerformed

    private void bRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRefreshActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_bRefreshActionPerformed

    private void tTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTelpActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if(!tAlamat.getText().trim().isEmpty()
                && !tTelp.getText().trim().isEmpty()
                && !tEmail.getText().trim().isEmpty()){
            simpan();
            refresh();
            reset();
            JOptionPane.showMessageDialog(this, "Record Peminjaman Baerhasil ditambahkan", 
                    "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Lengkapi form terlebih dahulu", 
                    "Notifikasi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void TblBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblBukuMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_TblBukuMouseClicked

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        tIDPeminjam.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 0).toString());
        tUsername.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 1).toString());
        tAlamat.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 2).toString());
        tTelp.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 3).toString());
        tEmail.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 4).toString());
        tTgl.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 5).toString());
        tBuku.setText(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 6).toString());
        cmbStatusBuku.setSelectedItem(Tabel.getModel().getValueAt
                (Tabel.getSelectedRow(), 4).toString());
        tIDPeminjam.setEditable(false);
        bSimpan.setEnabled(false);
    }//GEN-LAST:event_TabelMouseClicked

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bResetActionPerformed

    private void bUbahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUbahMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_bUbahMouseClicked

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        if(!tIDPeminjam.getText().trim().isEmpty()) {
            if(!tIDPeminjam.getText().trim().isEmpty() &&
                    !tTelp.getText().trim().isEmpty()
                    && !tAlamat.getText().trim().isEmpty() &&
                    !tEmail.getText().trim().isEmpty() &&
                    !tTgl.getText().trim().isEmpty()){
                //panggil metode ubah
                ubah();
                refresh();
                reset();
                JOptionPane.showMessageDialog(this,
                        "Data anda berhasil diubah",
                        "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Lengkapi form terlebih dahulu!",
                        "Notifikasi", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void tUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tUsernameActionPerformed

    private void tBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tBukuActionPerformed

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
            java.util.logging.Logger.getLogger(FramePinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePinjamBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JTable TblBuku;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bRefresh;
    private java.awt.Button bReset;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JComboBox cmbStatusBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JTextField tBuku;
    private javax.swing.JTextField tCari;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tIDPeminjam;
    private javax.swing.JTextField tTelp;
    private javax.swing.JTextField tTgl;
    private javax.swing.JTextField tUsername;
    // End of variables declaration//GEN-END:variables
}
