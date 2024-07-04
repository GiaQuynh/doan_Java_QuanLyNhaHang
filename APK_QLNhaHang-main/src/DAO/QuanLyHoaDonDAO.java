/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Order;
import POJO.Order_Detail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class QuanLyHoaDonDAO {

    public static ArrayList<Order> GetHoaDonThanhCong() {
        try {
            ArrayList<Order> listDonhang = new ArrayList<>();
            String sql = "select * from DonHang where trang_thai_thanhtoan = 'successful'";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                Order bill = new Order();
                bill.setOrder_id(rs.getInt("order_id"));
                bill.setId_ban(rs.getInt("id_ban"));
                bill.setGiamgia(rs.getFloat("gia_giam"));

                bill.setNgayGioDat(rs.getTimestamp("ngay_dat"));
                bill.setTongbill(rs.getFloat("TongBill"));
                bill.setThanhtien(rs.getFloat("tongtien"));
                bill.setTrangthai(rs.getString("trang_thai_thanhtoan"));

                listDonhang.add(bill);
            }
            provider.close();
            return listDonhang;
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<Order_Detail> GetDetail(int idorder) {
        try {
            ArrayList<Order_Detail> listDonhang = new ArrayList<>();
            String sql = "select * from ChiTietDonHang where order_id = ?";
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idorder);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order_Detail bill = new Order_Detail();
                bill.setDetailId(rs.getInt("detail_id"));
                bill.setOrderId(rs.getInt("order_id"));
                bill.setIdMonan(rs.getInt("id_monan"));

                bill.setTenMonan(rs.getString("tenmonan"));
                bill.setSoLuong(rs.getInt("so_luong"));
                bill.setDonGia(rs.getFloat("dongia"));
                bill.setThanhTien(rs.getFloat("Thanhtien"));

                listDonhang.add(bill);
            }
            provider.close();
            return listDonhang;
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean delete(int id) {
        try {
            String sql = "delete DonHang where order_id =?";
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
