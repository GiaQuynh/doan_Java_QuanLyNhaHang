/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.KetCaBaoCaoPOJO;
import POJO.MonAn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KetCaDoanhThuDAO {

    public static ArrayList<KetCaBaoCaoPOJO> DanhSach() {
        try {
            ArrayList<KetCaBaoCaoPOJO> list = new ArrayList<KetCaBaoCaoPOJO>();

            String sql = "select * from KetCaBaoCao";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                KetCaBaoCaoPOJO monan = new KetCaBaoCaoPOJO();
                monan.setIdbaocao(rs.getInt("idbaocao"));
                monan.setNhanvienketca(rs.getString("nhanvienketca"));
                monan.setGioVoCa(rs.getString("gioVoCa"));

                monan.setTienDauCa(rs.getString("TienDauCa"));
                monan.setTongSoHoaDon(rs.getInt("TongSoHoaDon"));
                monan.setTienHang(rs.getString("TienHang"));
                monan.setPhiDichVu(rs.getString("PhiDichVu"));
                monan.setTienGiamGia(rs.getString("TienGiamGia"));
                monan.setDoanhThu(rs.getString("DoanhThu"));
                monan.setTienCuoiCa(rs.getString("TienCuoiCa"));
                monan.setGioXuatKetCa(rs.getString("GioXuatKetCa"));

                list.add(monan);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static int TongSoDonHang() {
        try {
            String sql = "SELECT COUNT(*) AS total FROM DonHang WHERE trang_thai_thanhtoan = 'successful' AND DAY(ngay_dat) = DAY(GETDATE())";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            int kq = 0;
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                kq = rs.getInt("total");
            }
            provider.close();
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static boolean KiemTraKetCa() {
        try {
            String sql = "SELECT COUNT(*) AS total FROM KetCaBaoCao WHERE DAY(GioXuatKetCa) = DAY(GETDATE())";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            int kq = 0;
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                kq = rs.getInt("total");
            }
            provider.close();
            // Return true if there are records for the current day, false otherwise
            return kq > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Return false in case of exception
        }
    }

    public static double TienHangHomNay() {
        try {
            String sql = "select SUM(tongtiennhap) as TienHang from PhieuNhap where DAY(ngay_nhap_nguyenlieu) = DAY(GETDATE())";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            double kq = 0;
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                kq = rs.getDouble("TienHang");
            }
            provider.close();
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static double TongDoanhThuBanHang() {
        try {
            String sql = "select SUM(tongtien) as DoanhThuBan from DonHang where trang_thai_thanhtoan = 'successful' and DAY(ngay_dat) = DAY(GETDATE())";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            double kq = 0;
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                kq = rs.getDouble("DoanhThuBan");
            }
            provider.close();
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static boolean addRecord(String tennv, String giovoca, String tiendauca, int tonghoadon, String tienhang, String phidv, String tienGiam, String doanhthu, String Tiencuoca, String Gioketca) {
        try {
            Connect provider = new Connect();
            provider.ketNoiCSDL();

            PreparedStatement preparedStatement;
            String query = "INSERT INTO KetCaBaoCao (nhanvienketca, gioVoCa, TienDauCa, TongSoHoaDon, TienHang, PhiDichVu,TienGiamGia ,DoanhThu, TienCuoiCa, GioXuatKetCa) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = provider.ketNoiCSDL().prepareStatement(query);
            preparedStatement.setString(1, tennv);
            preparedStatement.setString(2, giovoca);
            preparedStatement.setString(3, tiendauca);
            preparedStatement.setInt(4, tonghoadon);
            preparedStatement.setString(5, tienhang);
            preparedStatement.setString(6, phidv);
            preparedStatement.setString(7, tienGiam);
            preparedStatement.setString(8, doanhthu);
            preparedStatement.setString(9, Tiencuoca);
            preparedStatement.setString(10, Gioketca);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
