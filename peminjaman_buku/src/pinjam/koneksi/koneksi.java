/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pinjam.koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author User
 */
public class koneksi {
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            String dbUrl = "jdbc:mysql://localhost:3306/peminjaman?user=root&password=";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(dbUrl);
        } catch (ClassNotFoundException | SQLException e) {
        }   
            return connection;
    }
}
}
        
