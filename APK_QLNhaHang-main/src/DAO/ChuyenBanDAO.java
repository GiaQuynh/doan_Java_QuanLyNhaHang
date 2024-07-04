/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Quin Quin
 */
public class ChuyenBanDAO {
     public static Boolean UpDateDonHang_Ban(int order_id, int idbanmoi) {
        try {
            String sql = "UPDATE DonHang SET [id_ban] = ? WHERE order_id = ?";
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idbanmoi);
            statement.setInt(2, order_id);

            int rowsAffected = statement.executeUpdate();

            provider.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean UpDateTrangThaiBanCu(int idbanCu) {
        try {
            String sql = "UPDATE Ban SET trang_thai = N'Trống' WHERE id_ban = ?";
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idbanCu);

            int rowsAffected = statement.executeUpdate();

            provider.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean UpDateTrangThaiBanNew(int idbanMoi) {
        try {
            String sql = "UPDATE Ban SET trang_thai = N'Có người' WHERE id_ban = ?";
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idbanMoi);

            int rowsAffected = statement.executeUpdate();

            provider.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
