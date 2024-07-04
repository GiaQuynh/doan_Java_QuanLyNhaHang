/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Connect {

    public static Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    String strServer = "DESKTOP-MEUMH4V";
    String strDatabase = "QL_NhaHang";
    String strUser = "S1";
    String strPassword = "123";

    public Connect() {
        ketNoiCSDL();
    }

    public void ketNoiCSDL() {
        try {
            try {
                String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Thêm cài đặt SSL vào URL kết nối
            String connectURL = "jdbc:sqlserver://" + strServer
                    + ":1433;databaseName=" + strDatabase
                    + ";user=" + strUser + ";password=" + strPassword
                    + ";encrypt=true;trustServerCertificate=true;";
            connection = DriverManager.getConnection(connectURL);
            if (connection != null) {
                System.out.println("Kết nối thành công");
            } else {
                System.out.println("Kết nối thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet executeQuery(String strSQL) {
        try {
            ResultSet rs;
            Statement sm = connection.createStatement();
            rs = sm.executeQuery(strSQL);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int executeUpdate(String strSQL) {
        try {
            int result;
            Statement sm = connection.createStatement();
            result = sm.executeUpdate(strSQL);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static void main(String[] args) {
        Connect db = new Connect();

    }

    public CallableStatement prepareCall(String storedProcedure) throws SQLException {
        return connection.prepareCall(storedProcedure);
    }

    public PreparedStatement prepareStatement(String sqlInsert) throws SQLException {
        return connection.prepareStatement(sqlInsert);
    }
}
