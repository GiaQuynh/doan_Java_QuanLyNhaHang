/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.KhoPOJO;
import POJO.PhieuNhapPOJO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 *
 * @author ASUS
 */
public class KhoDAO {

    //Thử
    public static ArrayList<KhoPOJO> getKhoData() {
        ArrayList<KhoPOJO> dataList = new ArrayList<>();
        String sql = "SELECT * FROM Kho";

        Connect ketNoiDAO = new Connect();
        ketNoiDAO.ketNoiCSDL();
        ResultSet rs = ketNoiDAO.executeQuery(sql);

        try {
            while (rs.next()) {
                KhoPOJO kho = new KhoPOJO();
                kho.setIdnguyenlieu(rs.getInt("stock_id"));
                kho.setTen_nguyenlieu(rs.getString("ten_nguyenlieu"));
                kho.setSoluong(rs.getInt("so_luong_ton"));
                dataList.add(kho);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ketNoiDAO.close();
        }
        return dataList;
    }

    //Get Kho sẽ Update và Insert SoLuongton 
    public static ArrayList<KhoPOJO> GetDanhSachKho() {
        ArrayList<KhoPOJO> dataList = new ArrayList<>();

        String sql = "select * from Kho";
        Connect ketNoiDAO = new Connect();
        ketNoiDAO.ketNoiCSDL();

        // Gọi phương thức executeQuery từ đối tượng ketNoiDAO
        ResultSet rs = ketNoiDAO.executeQuery(sql);

        try {
            while (rs.next()) {
                KhoPOJO pn = new KhoPOJO();
                pn.setStock_id(rs.getInt("stock_id"));
                pn.setIdnguyenlieu(rs.getInt("idnguyenlieu"));
                pn.setTen_nguyenlieu(rs.getString("ten_nguyenlieu"));
                pn.setSoluong(rs.getInt("so_luong_ton"));
                pn.setDonvitinh(rs.getString("donvitinh"));
                dataList.add(pn);
            }
            ketNoiDAO.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataList;

    }

    public static ArrayList<KhoPOJO> timKiemKho(String keyword) {
        ArrayList<KhoPOJO> dsKho = new ArrayList<>();
        try {
            // Kiểm tra nếu keyword có thể được chuyển thành số (mã kho là kiểu số)
            int maKho = -1; // Giá trị mặc định cho mã kho
            try {
                maKho = Integer.parseInt(keyword);
            } catch (NumberFormatException ex) {
                // Nếu không thể chuyển đổi thành số, không làm gì cả
            }

            // Tạo câu truy vấn SQL
            String sql;
            if (maKho != -1) {
                sql = "SELECT * FROM Kho WHERE idnguyenlieu = " + maKho;
            } else {
                sql = "SELECT * FROM Kho WHERE ten_nguyenlieu LIKE N'%" + keyword + "%'";
            }
            System.out.println(sql);
            Connect kn = new Connect();
            kn.ketNoiCSDL();
            ResultSet rs = kn.executeQuery(sql);
            while (rs.next()) {
                KhoPOJO kho = new KhoPOJO();
                kho.setStock_id(rs.getInt("stock_id"));
                kho.setIdnguyenlieu(rs.getInt("idnguyenlieu"));
                kho.setTen_nguyenlieu(rs.getString("ten_nguyenlieu"));
                kho.setSoluong(rs.getInt("so_luong_ton"));
                kho.setDonvitinh(rs.getString("donvitinh"));

                dsKho.add(kho);
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKho;
    }

    public static boolean insertKho(KhoPOJO item) {
        try {
            String sql = "INSERT INTO Kho (idnguyenlieu, ten_nguyenlieu, so_luong_ton, donvitinh) VALUES  (?, ?, ?, ?)";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getIdnguyenlieu());
            statement.setString(2, item.getTen_nguyenlieu());
            statement.setFloat(3, item.getSoluong());
            statement.setString(4, item.getDonvitinh());

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(KhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static boolean updateKho(float soluong, int idnguyenlieu) {
        try {
            String sql = "update Kho set so_luong_ton = ? where idnguyenlieu = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, soluong);
            statement.setInt(2, idnguyenlieu);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(KhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static boolean KiemTraIdNguyenLieu(int idnguyenlieu) {
        try {
            String sql = "select * from Kho where idnguyenlieu = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idnguyenlieu);

            ResultSet resultSet = statement.executeQuery(); // Thực thi câu lệnh SELECT

            boolean exists = resultSet.next(); // Kiểm tra xem có kết quả trả về hay không

            provider.close();

            return exists; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(KhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static float GetSoLuongByIDNguyenLieu(int idnguyenlieu) {
        try {
            String sql = "SELECT so_luong_ton FROM Kho WHERE idnguyenlieu = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idnguyenlieu);

            ResultSet resultSet = statement.executeQuery(); // Thực thi câu lệnh SELECT

            int soLuong = 0;
            if (resultSet.next()) {
                soLuong = resultSet.getInt("so_luong_ton"); // Lấy giá trị so_luong_ton từ ResultSet
            }

            provider.close();

            return soLuong;
        } catch (SQLException ex) {
            Logger.getLogger(KhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

}
