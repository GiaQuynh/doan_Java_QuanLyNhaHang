/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.DanhMucMonAn;
import POJO.MonAn;
import POJO.NguyenLieu;
import POJO.NguyenLieu_MonAn;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Types;

/**
 *
 * @author Admin
 */
public class ThucDonDao {

    public static ArrayList<MonAn> laydanhsachthucdon() {
        try {
            ArrayList<MonAn> list = new ArrayList<>();
            String sql = "select * from MonAn";
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
                list.add(monan);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<NguyenLieu> loadMenuNguyenLieu() {
        try {
            ArrayList<NguyenLieu> list = new ArrayList<>();
            String sql = "select * from NguyenLieu";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                NguyenLieu monan = new NguyenLieu();
                monan.setId(rs.getInt("idnguyenlieu"));
                monan.setTennguyenlieu(rs.getString("tennguyenlieu"));
                monan.setDonvi(rs.getString("donvitinh"));

                list.add(monan);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<DanhMucMonAn> loadMenuDanhmuc() {
        try {
            ArrayList<DanhMucMonAn> list = new ArrayList<>();
            String sql = "select * from DanhMucMonAn";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                DanhMucMonAn menu = new DanhMucMonAn();
                menu.setId(rs.getInt("danh_muc_id"));
                menu.setTendanhmuc(rs.getString("ten_danh_muc"));

                list.add(menu);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static int Insert(int iddanhmuc, String tenmon, String gia, String mota, String hinhanh) {
        try {
            String sqlInsert = "insert into MonAn (ten_mon,danh_muc_id,gia,mo_ta,hinh_anh) values(?,?,?,?,?)";

            String sqlSelectMaxId = "SELECT MAX(monan_id) AS max_id FROM MonAn";
            Connect provider = new Connect();
            Connection connection = provider.ketNoiCSDL();

            // Thực hiện câu lệnh INSERT
            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
            statementInsert.setString(1, tenmon);
            statementInsert.setInt(2, iddanhmuc);
            statementInsert.setString(3, gia);
            statementInsert.setString(4, mota);
            statementInsert.setString(5, hinhanh);
            int rowsAffected = statementInsert.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet resultSet = provider.executeQuery(sqlSelectMaxId);
                if (resultSet.next()) {
                    int maxId = resultSet.getInt("max_id");
                    return maxId;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static ArrayList<MonAn> getMonAn(int id) {
        try {
            ArrayList<MonAn> list = new ArrayList<>();
            String sql = "select * from MonAn where monan_id = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MonAn monan = new MonAn();
                monan.setId(rs.getInt("monan_id"));
                monan.setTenmon(rs.getString("ten_mon"));
                monan.setIddanhmuc(rs.getInt("danh_muc_id"));

                monan.setGiamon(rs.getString("gia"));
                monan.setMota(rs.getString("mo_ta"));
                monan.setHinhanh(rs.getString("hinh_anh"));
                //nhanVien.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(monan);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Boolean UpdateMonAn(int idmonan, String ten, int iddanhmuc, String gia, String mota, String hinhanh) {
        try {
//            String sql = "UPDATE MonAn \n"
//                    + "SET ten_mon = N'"+ten +"', danh_muc_id = "+iddanhmuc+", gia = '"+gia+"', mo_ta = N'"+mota+"', hinh_anh = '"+hinhanh+"' \n"
//                    + "WHERE monan_id = "+idmonan;

            String sql = "UPDATE MonAn SET ten_mon = ?, danh_muc_id = ?, gia = ?, mo_ta = ?, hinh_anh = ? WHERE monan_id = ?";
            System.out.println(sql);
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ten);
            statement.setInt(2, iddanhmuc);

            statement.setString(3, gia);
            statement.setString(4, mota);
            statement.setString(5, hinhanh);

            statement.setInt(6, idmonan);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean DeleteMonAn(int idmonan) {
        try {
            String sql = "delete MonAn where monan_id  = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idmonan);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static ArrayList<NguyenLieu_MonAn> getNguyenLieu(int idmonan) {
        try {
            ArrayList<NguyenLieu_MonAn> list = new ArrayList<>();
            String sql = "select * from MonAn_NguyenLieu_Default where monan_id = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idmonan);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NguyenLieu_MonAn monan = new NguyenLieu_MonAn();
                monan.setIdmon(rs.getInt("monan_id"));
                monan.setIdnguyenlieu(rs.getInt("nguyenlieu_id"));
                monan.setTennguyenlieu(rs.getString("tennguyenlieu"));

                monan.setDonvi(rs.getString("donvitinh"));
                monan.setSoluong(rs.getFloat("soluong"));

                //nhanVien.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(monan);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean InsertNguyenLieu_MonAn(NguyenLieu_MonAn nguyenlieu) {
        try {
            String sql = "INSERT INTO MonAn_NguyenLieu_Default (monan_id, nguyenlieu_id, tennguyenlieu, donvitinh, soluong) values(?,?,?,?,?)";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nguyenlieu.getIdmon());
            statement.setInt(2, nguyenlieu.getIdnguyenlieu());
            statement.setString(3, nguyenlieu.getTennguyenlieu());
            statement.setString(4, nguyenlieu.getDonvi());
            statement.setFloat(5, nguyenlieu.getSoluong());

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean KiemTraNguyenLieuTonTai(int idnguyenlieu, int idmonan) {
        try {
            String sql = "SELECT * FROM MonAn_NguyenLieu_Default WHERE nguyenlieu_id = ? and monan_id = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idnguyenlieu);
            statement.setInt(2, idmonan);

            ResultSet resultSet = statement.executeQuery();

            boolean exists = resultSet.next();

            provider.close();

            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean UpdateNguyenLieu(NguyenLieu_MonAn nguyenlieu) {
        try {
            String sql = "update MonAn_NguyenLieu_Default set soluong = ? where nguyenlieu_id = ? and monan_id = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, nguyenlieu.getSoluong());
            statement.setInt(2, nguyenlieu.getIdnguyenlieu());
            statement.setInt(3, nguyenlieu.getIdmon());

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean DeleteNguyenLieu(int idmonan, int idnguyenlieu) {
        try {
            String sql = "delete MonAn_NguyenLieu_Default where monan_id = ? and nguyenlieu_id  = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idmonan);
            statement.setInt(2, idnguyenlieu);

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static String getGiaTheoMon(int idmonan) {
        try {
            String giaca = "";
            String sql = "select gia from MonAn where monan_id = ?";
            Connect provider = new Connect();

            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idmonan);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) { // Di chuyển con trỏ ResultSet đến hàng đầu tiên và kiểm tra xem có dữ liệu không
                giaca = rs.getString("gia"); // Lấy giá trị từ cột "gia"
            }

            return giaca;
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Boolean UpdateGiaTheoMon(int idmonan, String giaca) {
        try {
            String sql = "update MonAn set gia  = ? where monan_id = ?";
            Connect provider = new Connect();
            System.out.println(sql);
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,giaca );
            statement.setFloat(2,idmonan );

            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
