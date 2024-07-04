/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.doanhthu;

import DAO.Connect;
import POJO.DonHang;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DSDonHang {

    public static ArrayList<DonHang> hienthiDSDonHang(int idban) {
        try {
            ArrayList<DonHang> listbanan = new ArrayList<>();
            String sql = "select * from DonHang, Ban";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                DonHang ban = new DonHang();

                ban.setOrder_id(rs.getInt("order_id"));
                ban.setThanhtien(rs.getFloat("tongtien"));
                listbanan.add(ban);
            }
            provider.close();
            return listbanan;
        } catch (SQLException ex) {
            Logger.getLogger(DSDonHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
