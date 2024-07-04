package DAO;

import java.util.ArrayList;
import POJO.NguyenLieu;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class NguyenLieuDAO {

    public static ArrayList<NguyenLieu> getNguyenLieuData() {
        ArrayList<NguyenLieu> dataList = new ArrayList<>();
        String sql = "SELECT * FROM NguyenLieu";

        // Tạo một đối tượng của lớp Connect
        Connect ketNoiDAO = new Connect();

        // Mở kết nối đến cơ sở dữ liệu
        ketNoiDAO.ketNoiCSDL();

        // Gọi phương thức executeQuery từ đối tượng ketNoiDAO
        ResultSet rs = ketNoiDAO.executeQuery(sql);

        try {
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();
                nl.setId(rs.getInt("idnguyenlieu"));
                nl.setTennguyenlieu(rs.getString("tennguyenlieu"));
                nl.setDonvi(rs.getString("donvitinh"));
                dataList.add(nl);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối sau khi sử dụng
            ketNoiDAO.close();
        }
        return dataList;
    }

    public static ArrayList<NguyenLieu> timKiemNguyenLieu(String tenNL) {
        ArrayList<NguyenLieu> dsNL = new ArrayList<>();
        try {
            String sql = "SELECT * FROM NguyenLieu WHERE tennguyenlieu LIKE N'%" + tenNL + "%'";
            Connect kn = new Connect();
            kn.ketNoiCSDL();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();
                nl.setId(rs.getInt("idnguyenlieu"));
                nl.setTennguyenlieu(rs.getString("tennguyenlieu"));
                nl.setDonvi(rs.getString("donvitinh"));
                dsNL.add(nl);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNL;
    }

    public static ArrayList<NguyenLieu> timKiemNguyenLieu1(String keyword) {
        ArrayList<NguyenLieu> dsNL = new ArrayList<>();
        try {
            // Kiểm tra nếu keyword có thể được chuyển thành số (mã nguyên liệu là kiểu số)
            int maNguyenLieu = -1; // Giá trị mặc định cho mã nguyên liệu
            try {
                maNguyenLieu = Integer.parseInt(keyword);
            } catch (NumberFormatException ex) {
                // Nếu không thể chuyển đổi thành số, không làm gì cả
            }

            // Tạo câu truy vấn SQL
            String sql;
            if (maNguyenLieu != -1) {
                sql = "SELECT * FROM NguyenLieu WHERE idnguyenlieu = " + maNguyenLieu;
            } else {
                sql = "SELECT * FROM NguyenLieu WHERE tennguyenlieu LIKE N'%" + keyword + "%'";
            }

            Connect kn = new Connect();
            kn.ketNoiCSDL();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();
                nl.setId(rs.getInt("idnguyenlieu"));
                nl.setTennguyenlieu(rs.getString("tennguyenlieu"));
                nl.setDonvi(rs.getString("donvitinh"));
                dsNL.add(nl);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNL;
    }

    public static ArrayList<String> getAllUserNames() {
        ArrayList<String> userNames = new ArrayList<>();
        try {
            String sql = "SELECT staff_id FROM NhanVien";
            Connect kn = new Connect();
            kn.ketNoiCSDL();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                userNames.add(rs.getString("ten"));
            }
            kn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userNames;
    }
    public static boolean insertNL(NguyenLieu nl) {
        try {
            String sql = "insert NguyenLieu (tennguyenlieu,donvitinh) values (?,?)";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
         
            statement.setString(1, nl.getTennguyenlieu());
            statement.setString(2, nl.getDonvi());

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static boolean updateNL(NguyenLieu nl) {
        try {
            String sql = "update NguyenLieu set tennguyenlieu = ? , donvitinh = ? where idnguyenlieu = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nl.getTennguyenlieu());
            statement.setString(2, nl.getDonvi());
            statement.setInt(3, nl.getId());

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static boolean delete(int id) {
        try {
            String sql = "delete NguyenLieu where idnguyenlieu = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
