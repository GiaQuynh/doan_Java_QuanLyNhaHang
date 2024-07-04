/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;

import POJO.ChitietPhieuNhapPOJO;
import POJO.PhieuNhapPOJO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ChitietPhieuNhapDAO {

    public static ArrayList<ChitietPhieuNhapPOJO> getCTPhieuNhap(int id) {
        ArrayList<ChitietPhieuNhapPOJO> dataList = new ArrayList<>();
        String sql = "select * from ChitietPhieuNhap where id_phieu_nhap = "+id;
        Connect ketNoiDAO = new Connect();
        ketNoiDAO.ketNoiCSDL();

        ResultSet rs = ketNoiDAO.executeQuery(sql);

        try {
            while (rs.next()) {
                ChitietPhieuNhapPOJO pn = new ChitietPhieuNhapPOJO();
                pn.setIdChitietPhieu(rs.getInt("id_chitietphieu"));
                pn.setIdPhieuNhap(rs.getInt("id_phieu_nhap"));
                pn.setIdNguyenLieu(rs.getInt("idnguyenlieu"));
                pn.setTenNguyenLieu(rs.getString("tennguyenlieu"));
                pn.setSoLuongNhap(rs.getInt("soluongnhap"));
                pn.setDonGia(rs.getFloat("dongia"));
                pn.setThanhTien(rs.getFloat("thanhtien"));
                dataList.add(pn);
            }
            ketNoiDAO.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataList;
    }

    public static boolean insertChiTietPhieuNhap(ChitietPhieuNhapPOJO ph) {

        try {
            String sql = "INSERT INTO ChitietPhieuNhap (id_phieu_nhap, idnguyenlieu, tennguyenlieu, soluongnhap, dongia, thanhtien) VALUES (?, ?, ?, ?, ?, ?)";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ph.getIdPhieuNhap());
            statement.setInt(2, ph.getIdNguyenLieu());
            statement.setString(3, ph.getTenNguyenLieu());
            statement.setFloat(4, ph.getSoLuongNhap());
            statement.setFloat(5, ph.getDonGia());
            statement.setFloat(6, ph.getThanhTien());

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(ChitietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

//    public static boolean capNhatPhieuNhap_TongTien(PhieuNhapPOJO pn) {
//        boolean kq = false;
//        Connect kn = new Connect();
//        kn.ketNoiCSDL();
//        try {
//            String sql = "UPDATE PhieuNhap SET tongtiennhap=? WHERE id_phieu_nhap = ?";
//            System.out.println(sql);
//
//            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
//            statement.setFloat(1, pn.getTongTienNhap());
//            statement.setInt(2, pn.getId());
//            int n = statement.executeUpdate();
//            if (n == 1) {
//                kq = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            kn.close();
//        }
//        return kq;
//    }
//    public static boolean capNhatCTPhieuNhap_ThanhTien_Dongia_Soluong(ChitietPhieuNhapPOJO pn) {
//        boolean kq = false;
//        Connect kn = new Connect();
//        kn.ketNoiCSDL();
//        try {
//            String sql = "UPDATE ChitietPhieuNhap SET dongia=?,soluongnhap=?,thanhtien=? WHERE id_phieu_nhap = ? AND idnguyenlieu=?";
//            System.out.println(sql);
//
//            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
//
//            statement.setFloat(1, pn.getDonGia());
//            statement.setInt(2, pn.getSoLuongNhap());
//            statement.setFloat(3, pn.getThanhTien());
//            statement.setInt(4, pn.getId());
//            statement.setInt(5, pn.getIdNguyenLieu());
//            int n = statement.executeUpdate();
//            if (n == 1) {
//                kq = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            kn.close();
//        }
//        return kq;
//    }
//
//    public static boolean capNhatCTPhieuNhap(ChitietPhieuNhapPOJO pn) {
//        boolean kq = false;
//        Connect kn = new Connect();
//        kn.ketNoiCSDL();
//        try {
//            String sql = "UPDATE ChitietPhieuNhap SET  WHERE id_phieu_nhap = ? AND idnguyenlieu= ?";
//            System.out.println(sql);
//            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
//            statement.setInt(1, pn.getId());
//            statement.setInt(2, pn.getIdNguyenLieu());
//
//            int n = statement.executeUpdate();
//            if (n == 1) {
//                kq = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            kn.close();
//        }
//        return kq;
//    }
//
//    public static ArrayList<ChitietPhieuNhapPOJO> getMaCTPhieuNhap(int maPhieuNhap) {
//        ArrayList<ChitietPhieuNhapPOJO> dataList = new ArrayList<>();
//        Connect kn = new Connect();
//        kn.ketNoiCSDL();
//        String sql = "SELECT * FROM ChitietPhieuNhap WHERE id_phieu_nhap = ?";
//
//        try {
//            // Tạo câu lệnh PreparedStatement
//            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
//
//            // Thiết lập tham số cho câu lệnh PreparedStatement
//            statement.setInt(1, maPhieuNhap);
//
//            // Thực thi truy vấn
//            ResultSet rs = statement.executeQuery();
//
//            // Duyệt qua các kết quả và tạo các đối tượng ChitietPhieuNhapPOJO
//            while (rs.next()) {
//                ChitietPhieuNhapPOJO pn = new ChitietPhieuNhapPOJO();
//                pn.setId(rs.getInt("id_phieu_nhap"));
//                pn.setIdNguyenLieu(rs.getInt("idnguyenlieu"));
//                pn.setSoLuongNhap(rs.getInt("soluongnhap"));
//                pn.setDonGia(rs.getFloat("dongia"));
//                pn.setThanhTien(rs.getFloat("thanhtien"));
//                dataList.add(pn);
//            }
//
//            // Đóng kết nối và các tài nguyên
//            rs.close();
//            statement.close();
//            kn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return dataList;
//    }
//
//    public static ArrayList<ChitietPhieuNhapPOJO> getChiTietPhieuNhapByID1(int maPhieuNhap) {
//        ArrayList<ChitietPhieuNhapPOJO> dataList = new ArrayList<>();
//        Connect kn = new Connect();
//        kn.ketNoiCSDL();
//
//        // Câu lệnh SQL để truy vấn chi tiết phiếu nhập và tên nguyên liệu
//        String sql = "SELECT ctpn.id_phieu_nhap, ctpn.idnguyenlieu, nl.tennguyenlieu, ctpn.soluongnhap, ctpn.dongia, ctpn.thanhtien "
//                + "FROM ChitietPhieuNhap ctpn "
//                + "INNER JOIN NguyenLieu nl ON ctpn.idnguyenlieu = nl.idnguyenlieu "
//                + "WHERE ctpn.id_phieu_nhap = ?";
//
//        try {
//            // Tạo câu lệnh PreparedStatement
//            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
//
//            // Thiết lập tham số cho câu lệnh PreparedStatement
//            statement.setInt(1, maPhieuNhap);
//
//            // Thực thi truy vấn
//            ResultSet rs = statement.executeQuery();
//
//            // Duyệt qua các kết quả và tạo các đối tượng ChitietPhieuNhapPOJO
//            while (rs.next()) {
//                ChitietPhieuNhapPOJO pn = new ChitietPhieuNhapPOJO();
//                pn.setId(rs.getInt("id_phieu_nhap"));
//                pn.setIdNguyenLieu(rs.getInt("idnguyenlieu"));
//                pn.setTenNguyenLieu(rs.getString("tennguyenlieu"));
//                pn.setSoLuongNhap(rs.getInt("soluongnhap"));
//                pn.setDonGia(rs.getFloat("dongia"));
//                pn.setThanhTien(rs.getFloat("thanhtien"));
//                dataList.add(pn);
//            }
//
//            // Đóng kết nối và các tài nguyên
//            rs.close();
//            statement.close();
//            kn.close();
//            System.out.println("Number of details: " + dataList.size()); // Kiểm tra số lượng chi tiết phiếu nhập
//        } catch (SQLException ex) {
//            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return dataList;
//    }
    public static boolean xoaCTPhieuNhap(int idPhieuNhap, int idNguyenLieu) {
        boolean kq = false;
        String sql = "DELETE FROM ChitietPhieuNhap WHERE id_phieu_nhap = ? AND idnguyenlieu=?";

        Connect kn = new Connect();
        kn.ketNoiCSDL();

        try {

            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
            statement.setInt(1, idPhieuNhap);
            statement.setInt(2, idNguyenLieu);
            int n = statement.executeUpdate();
            if (n == 1) {
                kq = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kn.close();
        }
        return kq;
    }
}
