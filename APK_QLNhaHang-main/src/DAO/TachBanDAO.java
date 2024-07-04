/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.BanAn;
import POJO.MonAn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TachBanDAO {

    public static ArrayList<BanAn> laydanhsachban() {
        try {
            ArrayList<BanAn> list = new ArrayList<>();
            String sql = "select  * from Ban where  trang_thai = N'Trống'";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                BanAn monan = new BanAn();
                monan.setIdban(rs.getInt("id_ban"));
                monan.setTenban(rs.getString("ten_ban"));

                list.add(monan);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TachBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Boolean UpDateDonHang(int order_id, String tongbill, String tongtien) {
        try {
            String sql = "UPDATE DonHang SET TongBill = ?, tongtien = ? WHERE order_id = ?";
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, tongbill);
            statement.setString(2, tongtien);
            statement.setInt(3, order_id);

            int rowsAffected = statement.executeUpdate();

            provider.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean UpDateCTDH(int order_id, int id_monan, int so_luong, double thanhTien) {
        try {
            String sql = "UPDATE ChiTietDonHang SET so_luong = ?, Thanhtien = ? WHERE order_id = ? AND id_monan = ?";
            System.out.println(sql);
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, so_luong);
            statement.setDouble(2, thanhTien);
            statement.setInt(3, order_id);
            statement.setInt(4, id_monan);

            int rowsAffected = statement.executeUpdate();

            provider.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TachBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean deleteOrderDetail(int order_id, int id_monan, int so_luong, double thanhTien) {
        try {
            String sql = "UPDATE ChiTietDonHang SET so_luong = ?, Thanhtien = ? WHERE order_id = ? AND id_monan = ?";
            System.out.println(sql);
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, so_luong);
            statement.setDouble(2, thanhTien);
            statement.setInt(3, order_id);
            statement.setInt(4, id_monan);

            int rowsAffected = statement.executeUpdate();

            provider.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TachBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
