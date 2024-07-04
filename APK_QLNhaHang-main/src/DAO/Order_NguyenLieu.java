/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.KhoNguyenLieu;
import POJO.NguyenLieu_MonAn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Order_NguyenLieu {

    public static ArrayList<NguyenLieu_MonAn> getNguyenLieuByIdMon(int idmon) {
        try {
            ArrayList<NguyenLieu_MonAn> listmonan = new ArrayList<>();
            String sql = "select * from MonAn_NguyenLieu_Default where monan_id = " + idmon;
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                NguyenLieu_MonAn monan = new NguyenLieu_MonAn();
                monan.setIdmon(rs.getInt("monan_id"));
                monan.setIdnguyenlieu(rs.getInt("nguyenlieu_id"));
                monan.setTennguyenlieu(rs.getString("tennguyenlieu"));

                monan.setDonvi(rs.getString("donvitinh"));
                monan.setSoluong(rs.getFloat("soluong"));

                listmonan.add(monan);
            }
            provider.close();
            return listmonan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_NguyenLieu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<KhoNguyenLieu> getNguyenLieuKho() {
        try {
            ArrayList<KhoNguyenLieu> listnguyenlieu = new ArrayList<>();
            String sql = "select * from Kho";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                KhoNguyenLieu nguyenlieu = new KhoNguyenLieu();
                nguyenlieu.setId_kho_nguyenlieu(rs.getInt("stock_id"));
                nguyenlieu.setTennguyenlieu(rs.getString("ten_nguyenlieu"));
                nguyenlieu.setSoluong(rs.getFloat("so_luong_ton"));

                listnguyenlieu.add(nguyenlieu);
            }
            provider.close();
            return listnguyenlieu;
        } catch (SQLException ex) {
            Logger.getLogger(Order_NguyenLieu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<KhoNguyenLieu> updateKho() {
        try {
            ArrayList<KhoNguyenLieu> listnguyenlieu = new ArrayList<>();
            String sql = "select * from Kho";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                KhoNguyenLieu nguyenlieu = new KhoNguyenLieu();
                nguyenlieu.setId_kho_nguyenlieu(rs.getInt("stock_id"));
                nguyenlieu.setTennguyenlieu(rs.getString("ten_nguyenlieu"));
                nguyenlieu.setSoluong(rs.getFloat("so_luong_ton"));

                listnguyenlieu.add(nguyenlieu);
            }
            provider.close();
            return listnguyenlieu;
        } catch (SQLException ex) {
            Logger.getLogger(Order_NguyenLieu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
