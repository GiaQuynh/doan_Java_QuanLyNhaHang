/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.BanAn;
import POJO.DanhMucMonAn;
import POJO.MonAn;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Order_Load {

    public static ArrayList<MonAn> getThucDon() {
        try {
            ArrayList<MonAn> listmonan = new ArrayList<>();
            String sql = "select * from MonAn where danh_muc_id  <> (SELECT danh_muc_id FROM DanhMucMonAn WHERE ten_danh_muc = N'Đồ uống'); ";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                MonAn monan = new MonAn();
                monan.setId(rs.getInt("monan_id"));
                monan.setTenmon(rs.getString("ten_mon"));
                monan.setIddanhmuc(rs.getInt("danh_muc_id"));

                monan.setGiamon(rs.getString("gia"));
                monan.setMota(rs.getString("mo_ta"));
                monan.setHinhanh(rs.getString("hinh_anh"));
                //nhanVien.setTrangThai(rs.getBoolean("TrangThai"));
                listmonan.add(monan);
            }
            provider.close();
            return listmonan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static ArrayList<MonAn> getThucUong() {
        try {
            ArrayList<MonAn> listmonan = new ArrayList<>();
            String sql = "select * from MonAn where danh_muc_id  = (SELECT danh_muc_id FROM DanhMucMonAn WHERE ten_danh_muc = N'Đồ uống'); ";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                MonAn monan = new MonAn();
                monan.setId(rs.getInt("monan_id"));
                monan.setTenmon(rs.getString("ten_mon"));
                monan.setIddanhmuc(rs.getInt("danh_muc_id"));

                monan.setGiamon(rs.getString("gia"));
                monan.setMota(rs.getString("mo_ta"));
                monan.setHinhanh(rs.getString("hinh_anh"));
                //nhanVien.setTrangThai(rs.getBoolean("TrangThai"));
                listmonan.add(monan);
            }
            provider.close();
            return listmonan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static ArrayList<BanAn> getBanAn() {
        try {
            ArrayList<BanAn> listbanan = new ArrayList<>();
            String sql = "select * from Ban";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            while (rs.next()) {
                BanAn ban = new BanAn();
                ban.setIdban(rs.getInt("id_ban"));
                ban.setTenban(rs.getString("ten_ban"));
                Timestamp ngayDatTimestamp = rs.getTimestamp("ngay_dat");
                ban.setNgayGioDat(ngayDatTimestamp);

                ban.setTrangthai(rs.getString("trang_thai"));
                ban.setGhichu(rs.getString("ghi_chu"));
                listbanan.add(ban);
            }
            provider.close();
            return listbanan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static BanAn getBanAnByID(int idBan) {
        try {
            BanAn ban = null; // Declare ban here to return it later
            String sql = "SELECT * FROM Ban WHERE id_ban = "+idBan;
            Connect provider = new Connect();
            provider.ketNoiCSDL();

            ResultSet rs = provider.executeQuery(sql);

            if (rs.next()) { // Changed while to if since we expect a single record
                ban = new BanAn();
                ban.setIdban(rs.getInt("id_ban"));
                ban.setTenban(rs.getString("ten_ban"));
                Timestamp ngayDatTimestamp = rs.getTimestamp("ngay_dat");
                ban.setNgayGioDat(ngayDatTimestamp);
                ban.setTrangthai(rs.getString("trang_thai"));
                ban.setGhichu(rs.getString("ghi_chu"));
            }

            provider.close();
            return ban;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<DanhMucMonAn> getMenu() {
        try {
            ArrayList<DanhMucMonAn> listmonan = new ArrayList<>();
            String sql = "SELECT * FROM DanhMucMonAn WHERE danh_muc_id <> (SELECT danh_muc_id FROM DanhMucMonAn WHERE ten_danh_muc = N'Đồ uống');";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                DanhMucMonAn monan = new DanhMucMonAn();
                monan.setId(rs.getInt("danh_muc_id"));
                monan.setTendanhmuc(rs.getString("ten_danh_muc"));

                listmonan.add(monan);
            }
            provider.close();
            return listmonan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static ArrayList<MonAn> TimKiemMonAn(String tenmon) {
        try {
            ArrayList<MonAn> listmonan = new ArrayList<>();
            String sql = "select * from MonAn where ten_mon like N'%" + tenmon + "%'";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                MonAn monan = new MonAn();
                monan.setId(rs.getInt("monan_id"));
                monan.setTenmon(rs.getString("ten_mon"));
                monan.setIddanhmuc(rs.getInt("danh_muc_id"));

                monan.setGiamon(rs.getString("gia"));
                monan.setMota(rs.getString("mo_ta"));
                monan.setHinhanh(rs.getString("hinh_anh"));

                listmonan.add(monan);
            }
            provider.close();
            return listmonan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<MonAn> TimKiemThucUong(String tenmon) {
        try {
            ArrayList<MonAn> listmonan = new ArrayList<>();
            String sql = "select * from MonAn where danh_muc_id  = (SELECT danh_muc_id FROM DanhMucMonAn WHERE ten_danh_muc = N'Đồ uống') and ten_mon like N'%" + tenmon + "%'";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                MonAn monan = new MonAn();
                monan.setId(rs.getInt("monan_id"));
                monan.setTenmon(rs.getString("ten_mon"));
                monan.setIddanhmuc(rs.getInt("danh_muc_id"));

                monan.setGiamon(rs.getString("gia"));
                monan.setMota(rs.getString("mo_ta"));
                monan.setHinhanh(rs.getString("hinh_anh"));

                listmonan.add(monan);
            }
            provider.close();
            return listmonan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<MonAn> GetMonAnByidDanhMuc(int iddanhmuc) {
        try {
            ArrayList<MonAn> listmonan = new ArrayList<>();
            String sql = "select * from MonAn where danh_muc_id = " + iddanhmuc;

            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                MonAn monan = new MonAn();
                monan.setId(rs.getInt("monan_id"));
                monan.setTenmon(rs.getString("ten_mon"));
                monan.setIddanhmuc(rs.getInt("danh_muc_id"));

                monan.setGiamon(rs.getString("gia"));
                monan.setMota(rs.getString("mo_ta"));
                monan.setHinhanh(rs.getString("hinh_anh"));

                listmonan.add(monan);
            }
            provider.close();
            return listmonan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
