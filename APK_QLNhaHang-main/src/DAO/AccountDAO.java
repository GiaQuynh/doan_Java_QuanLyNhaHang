/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    public static boolean signup(String user, String pass, String fullname) {
        try {
            String sql = "INSERT INTO Account (username, password_hash, role, FullName) VALUES (?, ?, 'staff', ?)";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql); // Tạo PreparedStatement từ kết nối
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.setString(3, fullname);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static boolean checklogin(String user, String pass) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM Account WHERE username = ? AND password_hash = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql); // Tạo PreparedStatement từ kết nối
            statement.setString(1, user);
            statement.setString(2, pass);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }

            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static ArrayList<String> getRole(String user, String pass) {
        ArrayList<String> names = new ArrayList<>();
        try {
            String sql = "SELECT role FROM Account WHERE username = ? AND password_hash = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql); // Tạo PreparedStatement từ kết nối
            statement.setString(1, user);
            statement.setString(2, pass);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("role");
                names.add(fullName);
            }

            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return names;
    }

    public static ArrayList<String> getName(String user, String pass) {
        ArrayList<String> names = new ArrayList<>();
        try {
            String sql = "SELECT FullName FROM Account WHERE username = ? AND password_hash = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql); // Tạo PreparedStatement từ kết nối
            statement.setString(1, user);
            statement.setString(2, pass);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("FullName");
                names.add(fullName);
            }

            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return names;
    }
}
