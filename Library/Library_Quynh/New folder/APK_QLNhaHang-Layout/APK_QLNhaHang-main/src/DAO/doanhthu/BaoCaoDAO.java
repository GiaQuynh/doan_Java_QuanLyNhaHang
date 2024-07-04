/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.doanhthu;

import DAO.Connect;
import static GUI.doanhthu.BaoCaoDoanhThu.connection;
import POJO.BaoCao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BaoCaoDAO {
    public static Connect connection;

    public static boolean themBaoCao(BaoCao pb) {
        boolean kq = false;
        String sqlInsert = "INSERT INTO BaoCaoThongKe (loai_bao_cao, thong_tin_chi_tiet, ngay_tao_bao_cao) VALUES (?, ?, ?)";
        connection = new Connect();
        try {
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setString(1, pb.getLoai_bao_cao());
            statement.setString(2, pb.getThong_tin_chi_tiet());
            statement.setDate(3, new java.sql.Date(pb.getNgay_tao_bao_cao().getTime()));
            int n = statement.executeUpdate();
            if (n == 1) {
                kq = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return kq;
    }
}
