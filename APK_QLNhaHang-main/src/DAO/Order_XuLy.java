/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.BanAn;
import POJO.NguyenLieu_MonAn;
import POJO.Order;
import POJO.Order_Detail;
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
public class Order_XuLy {

    public static ArrayList<Order> getOrderByBan(int idban) {
        try {
            ArrayList<Order> listbanan = new ArrayList<>();
            String sql = "select * from DonHang where trang_thai_thanhtoan = 'unsuccessful' and id_ban= " + idban;
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                Order ban = new Order();

                ban.setOrder_id(rs.getInt("order_id"));
                ban.setId_ban(rs.getInt("id_ban"));
                ban.setGiamgia(rs.getFloat("gia_giam"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Timestamp ngayDatTimestamp = rs.getTimestamp("ngay_dat");
                ban.setNgayGioDat(ngayDatTimestamp);
                ban.setThanhtien(rs.getFloat("tongtien"));
                ban.setTrangthai(rs.getString("trang_thai_thanhtoan"));
                ban.setTongbill(rs.getFloat("TongBill"));

                listbanan.add(ban);
            }
            provider.close();
            return listbanan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean KiemTraBanTrong(int idban) {
        try {
            String sql = "select * from Ban where trang_thai = N'Có người' and id_ban = ?";
            Connect provider = new Connect();

            PreparedStatement statement = provider.ketNoiCSDL().prepareStatement(sql);
            statement.setInt(1, idban);
            ResultSet rs = statement.executeQuery();

            boolean coDonHang = rs.next(); // Kiểm tra xem ResultSet có dòng dữ liệu nào không

            provider.close();
            return coDonHang;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false trong trường hợp xảy ra lỗi
        }
    }

    public static boolean KiemTraBanTrongLoad(int idban) {
        try {
            String sql = "select * from DonHang where trang_thai_thanhtoan = N'successfull' and id_ban = ?";
            Connect provider = new Connect();

            PreparedStatement statement = provider.ketNoiCSDL().prepareStatement(sql);
            statement.setInt(1, idban);
            ResultSet rs = statement.executeQuery();

            boolean coDonHang = rs.next(); // Kiểm tra xem ResultSet có dòng dữ liệu nào không

            provider.close();
            return coDonHang;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false trong trường hợp xảy ra lỗi
        }
    }

    public static ArrayList<Order> KiemTraBan() {
        ArrayList<Order> danhSachBan = new ArrayList<>();
        try {
            String sql = "select * from DonHang";
            Connect provider = new Connect();

            PreparedStatement statement = provider.ketNoiCSDL().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order ban = new Order();
                ban.setOrder_id(rs.getInt("order_id"));
                ban.setId_ban(rs.getInt("id_ban"));
                ban.setGiamgia(rs.getFloat("gia_giam"));

                ban.setThanhtien(rs.getFloat("tongtien"));
                ban.setTrangthai(rs.getString("trang_thai_thanhtoan"));
                ban.setTongbill(rs.getFloat("TongBill"));
                danhSachBan.add(ban);
            }

            provider.close();
            return danhSachBan;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<Order_Detail> getOrderDetail(int idOrder) {
        try {
            ArrayList<Order_Detail> chitietorderlist = new ArrayList<>();
            String sql = "select * from ChiTietDonHang where order_id= " + idOrder;

            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                Order_Detail ban = new Order_Detail();

                ban.setDetailId(rs.getInt("detail_id"));
                ban.setOrderId(rs.getInt("order_id"));
                ban.setIdMonan(rs.getInt("id_monan"));
                ban.setTenMonan(rs.getString("tenmonan"));
                ban.setSoLuong(rs.getInt("so_luong"));
                ban.setThanhTien(rs.getFloat("Thanhtien"));
                ban.setDonGia(rs.getDouble("dongia"));

                chitietorderlist.add(ban);
            }
            provider.close();
            return chitietorderlist;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean InsertOrder(Order item, Date time) {
        try {
            String sql = "insert into DonHang(id_ban , gia_giam, tongtien ,trang_thai_thanhtoan,TongBill,ngay_dat)values(?,?,?,?,?,?)";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getId_ban());
            statement.setFloat(2, item.getGiamgia());
            statement.setFloat(3, item.getTongbill());
            statement.setString(4, item.getTrangthai());
            statement.setFloat(5, item.getThanhtien());

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(time);
            statement.setString(6, formattedDate);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static ArrayList<NguyenLieu_MonAn> getListNguyenLieu(int idMonAn) {
        try {
            ArrayList<NguyenLieu_MonAn> list = new ArrayList<>();
            String sql = "select * from MonAn_NguyenLieu_Default where monan_id = " + idMonAn;

            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                NguyenLieu_MonAn nguyenlieu = new NguyenLieu_MonAn();

                nguyenlieu.setIdmon(rs.getInt("monan_id"));
                nguyenlieu.setIdnguyenlieu(rs.getInt("nguyenlieu_id"));
                nguyenlieu.setTennguyenlieu(rs.getString("tennguyenlieu"));

                nguyenlieu.setDonvi(rs.getString("donvitinh"));
                nguyenlieu.setSoluong(rs.getFloat("soluong"));

                list.add(nguyenlieu);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static int GetIdOrder() {
        try {
            String query = "SELECT MAX(order_id) AS MaxID FROM DonHang;";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(query);
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt("MaxID");
            }
            provider.close();
            return orderId;
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

    public static float kiemTraSoLuongTrongKho(int idNguyenLieu) {
        String sql = "SELECT SUM(so_luong_ton) AS total_quantity FROM Kho WHERE idnguyenlieu = ?";
        try {
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idNguyenLieu);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                float totalQuantity = rs.getFloat("total_quantity");
                provider.close();
                return totalQuantity;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static float SoLuongNguyenLieuCanLam(int idNguyenLieu, int idMonan) {
        String sql = "SELECT soluong FROM MonAn_NguyenLieu_Default WHERE monan_id = ? AND nguyenlieu_id = ?";
        try {
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idMonan);
            preparedStatement.setInt(2, idNguyenLieu);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                float totalQuantity = rs.getFloat("soluong");
                provider.close();
                return totalQuantity;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static int DeleteBill(int idOrder) {
        String sql = "DELETE FROM DonHang WHERE order_id = ?";
        try {
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOrder);

            // Execute the delete operation
            int rowsAffected = preparedStatement.executeUpdate();

            provider.close();
            return rowsAffected; // Return the number of rows affected
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static boolean InsertOrderDetail(Order_Detail item) {
        try {
            String sql = "insert into ChiTietDonHang(order_id,id_monan,tenmonan,so_luong,dongia,Thanhtien) values(?,?,?,?,?,?)";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getOrderId());
            statement.setFloat(2, item.getIdMonan());
            statement.setString(3, item.getTenMonan());
            statement.setInt(4, item.getSoLuong());
            statement.setDouble(5, item.getDonGia());
            statement.setDouble(6, item.getThanhTien());
            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

//    public static int GetIDDetail() {
//        try {
//            String sql = "select MAX(detail_id) as MAXID from ChiTietDonHang";
//            Connect provider = new Connect();
//
//            Connection connection = provider.ketNoiCSDL();
//            provider.ketNoiCSDL();
//            ResultSet rs = provider.executeQuery(sql);
//            int orderDetailId = 0;
//            if (rs.next()) {
//                orderDetailId = rs.getInt("MAXID");
//            }
//            provider.close();
//            return orderDetailId;
//        } catch (SQLException ex) {
//            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return -1;
//
//    }
    public static Boolean UpDateTrangThaiBan(int idBan, Date time) {
        try {
            String sql = "update Ban set ngay_dat = ? , trang_thai = N'Có người' where id_ban = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(time);
            statement.setString(1, formattedDate);
            statement.setFloat(2, idBan);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean UpDateTrangThaiBanCancel(int idBan) {
        try {
            String sql = "update Ban set ngay_dat = NULL , trang_thai = N'Trống' where id_ban = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

//            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//            String formattedDate = dateFormat.format(time);
//            statement.setString(1, formattedDate);
            statement.setFloat(1, idBan);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean UpdateKho(int idnguyenlieu, float soluongUpdate) {
        try {
            String sql = "update Kho set so_luong_ton = ? where idnguyenlieu = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setFloat(1, soluongUpdate);
            statement.setInt(2, idnguyenlieu);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean UpdateDonHang(int orderId) {
        String sql = "UPDATE DonHang SET trang_thai_thanhtoan = 'successful' WHERE order_id = ?";
        try {
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);

            int rowsAffected = statement.executeUpdate(); // Execute the update statement

            provider.close();
            return rowsAffected > 0; // Return true if at least one row was affected
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Boolean UpdateBan(int idBan) {
        String sql = "update Ban set trang_thai = N'Trống' , ngay_dat = NULL where id_ban  = ?";
        try {
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idBan);

            int rowsAffected = statement.executeUpdate(); // Execute the update statement

            provider.close();
            return rowsAffected > 0; // Return true if at least one row was affected
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int GetOrderIDByIdban(int idBan) {
        String sql = "SELECT order_id FROM DonHang WHERE trang_thai_thanhtoan = 'unsuccessful' AND id_ban = ?";
        try {
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idBan);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                provider.close();
                return orderId;
            } else {
                provider.close();
                return 0; // No matching order found
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
