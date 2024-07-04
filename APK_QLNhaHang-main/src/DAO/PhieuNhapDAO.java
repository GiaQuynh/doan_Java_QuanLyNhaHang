/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.BanAn;
import POJO.NhaCungCapNguyenLieu;
import POJO.PhieuNhapPOJO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class PhieuNhapDAO {
    
    public static int GetIdPhieu() {
        try {
            String sql = "select MAX(id_phieu_nhap) as MAX from PhieuNhap";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            
            ResultSet rs = provider.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("MAX");
                
            }
            
            provider.close();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public static ArrayList<NhaCungCapNguyenLieu> GetDanhSach() {
        ArrayList<NhaCungCapNguyenLieu> dataList = new ArrayList<>();
        String sql = "SELECT * FROM NhaCungCapNguyenLieu";
        Connect ketNoiDAO = new Connect();
        ketNoiDAO.ketNoiCSDL();
        ResultSet rs = ketNoiDAO.executeQuery(sql);
        
        try {
            while (rs.next()) {
                NhaCungCapNguyenLieu pn = new NhaCungCapNguyenLieu();
                pn.setIdNhaCungCap(rs.getInt("idNhaCungCap"));
                pn.setTenNCC(rs.getString("tenNCC"));
                pn.setSoDienThoai(rs.getString("soDienThoai"));
                pn.setEmail(rs.getString("email"));
                pn.setDiaChi(rs.getString("diaChi"));
                pn.setChuthichnhacungcap(rs.getString("chuthichnhacungcap"));
                
                dataList.add(pn);
            }
            ketNoiDAO.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataList;
    }
    
    public static ArrayList<PhieuNhapPOJO> getPhieuNhap() {
        ArrayList<PhieuNhapPOJO> dataList = new ArrayList<>();
        String sql = "select id_phieu_nhap,ten_phieu,TenNhaCungCap , tongtiennhap, ngay_nhap_nguyenlieu from PhieuNhap";
        Connect ketNoiDAO = new Connect();
        ketNoiDAO.ketNoiCSDL();

        // Gọi phương thức executeQuery từ đối tượng ketNoiDAO
        ResultSet rs = ketNoiDAO.executeQuery(sql);
        
        try {
            while (rs.next()) {
                PhieuNhapPOJO pn = new PhieuNhapPOJO();
                pn.setIdphieunhap(rs.getInt("id_phieu_nhap"));
                pn.setTenPhieu(rs.getString("ten_phieu"));
                pn.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                pn.setTongTienNhap(rs.getFloat("tongtiennhap"));
                pn.setNgayNhapNguyenLieu(rs.getDate("ngay_nhap_nguyenlieu"));
                dataList.add(pn);
            }
            ketNoiDAO.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataList;
    }

//    public static float layTongTien(int maPhieuNhap) {
//        float tongTien = 0.0f;
//        Connect kn = new Connect();
//        kn.ketNoiCSDL();
//        try {
//            String sql = "SELECT tongtiennhap FROM PhieuNhap WHERE id_phieu_nhap = ?";
//            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
//            statement.setInt(1, maPhieuNhap);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                tongTien = rs.getFloat("tongtiennhap");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            kn.close();
//        }
//        return tongTien;
//    }
    public static boolean insertPhieuNhap(String tenphieunhap, String nguoithuchien, Date selectedDate, String tongtien, int idNhaCungCap, String tenNhaCungCap) {
        
        try {
            String sql = "INSERT INTO PhieuNhap (idNhaCungCap, TenNhaCungCap, nguoiThucHien, ten_phieu, tongtiennhap, ngay_nhap_nguyenlieu) VALUES (?, ?, ?, ?, ?, ?)";
            Connect provider = new Connect();
            
            Connection connection = provider.ketNoiCSDL();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idNhaCungCap);
            statement.setString(2, tenNhaCungCap);
            statement.setString(3, nguoithuchien);
            statement.setString(4, tenphieunhap);
            statement.setFloat(5, Float.parseFloat(tongtien));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(selectedDate);
            statement.setString(6, formattedDate);
            
            int rowsAffected = statement.executeUpdate(); // Thực thi câu lệnh INSERT

            provider.close();
            
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(Order_XuLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }

//    public static boolean capNhatPhieuNhap1(PhieuNhapPOJO pn) {
//        boolean kq = false;
//        Connect kn = new Connect();
//        kn.ketNoiCSDL();
//        try {
//            String sql = "UPDATE PhieuNhap SET idNhanVien= ? ,ten_phieu = ?, ngay_nhap_nguyenlieu = ?,tongtiennhap=? WHERE id_phieu_nhap = ?";
//            System.out.println(sql);
//
//            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
//            statement.setInt(1, pn.getStaffId());
//            statement.setString(2, pn.getTenPhieu());
//            statement.setDate(3, new java.sql.Date(pn.getNgayNhapNguyenLieu().getTime()));
//            statement.setFloat(4, pn.getTongTienNhap());
//            statement.setInt(5, pn.getId());
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
//   public static boolean xoaPhieuNhap(int idPhieuNhap) {
//    boolean ketQua = false;
//    String sql = String.format("DELETE FROM PhieuNhap WHERE id_phieu_nhap = %d", idPhieuNhap);
//
//    Connect ketNoiDAO = new Connect();
//    ketNoiDAO.open();
//    int n = ketNoiDAO.executeUpdate(sql);
//    if (n == 1) {
//        ketQua = true;
//    }
//    ketNoiDAO.close();
//    return ketQua;
//}
    public static boolean xoaPhieuNhap1(int idPhieuNhap) {
        boolean kq = false;
        String sql = "DELETE FROM PhieuNhap WHERE id_phieu_nhap = ?";
        
        Connect kn = new Connect();
        kn.ketNoiCSDL();
        
        try {
            
            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
            statement.setInt(1, idPhieuNhap);
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

//    public static ArrayList<PhieuNhapPOJO> timKiemPhieuNhap(String keyword) {
//        ArrayList<PhieuNhapPOJO> dsPhieuNhap = new ArrayList<>();
//        try {
//            // Kiểm tra nếu keyword có thể được chuyển thành số (mã phiếu là kiểu số)
//            int maPhieu = -1; // Giá trị mặc định cho mã phiếu
//            try {
//                maPhieu = Integer.parseInt(keyword);
//            } catch (NumberFormatException ex) {
//                // Nếu không thể chuyển đổi thành số, không làm gì cả
//            }
//
//            // Tạo câu truy vấn SQL
//            String sql;
//            if (maPhieu != -1) {
//                sql = "SELECT * FROM PhieuNhap WHERE id_phieu_nhap = " + maPhieu;
//            } else {
//                sql = "SELECT * FROM PhieuNhap WHERE ten_phieu LIKE N'%" + keyword + "%'";
//            }
//
//            Connect kn = new Connect();
//            kn.ketNoiCSDL();
//            ResultSet rs = kn.executeQuery(sql);
//            while (rs.next()) {
//                PhieuNhapPOJO phieuNhap = new PhieuNhapPOJO();
//
//                phieuNhap.setId(rs.getInt("id_phieu_nhap"));
//                phieuNhap.setStaffId(rs.getInt("idNhanVien"));
//                phieuNhap.setTenPhieu(rs.getString("ten_phieu"));
//                phieuNhap.setTongTienNhap(rs.getFloat("tongtiennhap"));
//                phieuNhap.setNgayNhapNguyenLieu(rs.getDate("ngay_nhap_nguyenlieu"));
//                dsPhieuNhap.add(phieuNhap);
//            }
//            kn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsPhieuNhap;
//    }
    public static ArrayList<PhieuNhapPOJO> timKiemPhieuNhapTheoThoiGian(String ngaytimkiem) {
        ArrayList<PhieuNhapPOJO> dsPhieuNhap = new ArrayList<>();
        try {
            String sql = "select * from PhieuNhap where ngay_nhap_nguyenlieu = ?";
            
            Connect kn = new Connect();
            kn.ketNoiCSDL();
            
            PreparedStatement statement = kn.ketNoiCSDL().prepareStatement(sql);
            statement.setString(1, ngaytimkiem);
            
            ResultSet rs = statement.executeQuery();

            // Duyệt qua các kết quả và thêm vào danh sách Phiếu Nhập
            while (rs.next()) {
                
                int id = rs.getInt("id_phieu_nhap");
                int idNCC = rs.getInt("idNhaCungCap");
                String tenPhieu = rs.getString("ten_phieu");
                String nguoiThucHien = rs.getString("nguoiThucHien");
                String TenNhaCungCap = rs.getString("TenNhaCungCap");
                float tongTienNhap = rs.getFloat("tongtiennhap");
                Date ngayNhapNguyenLieu = rs.getDate("ngay_nhap_nguyenlieu");
                
                PhieuNhapPOJO phieuNhap = new PhieuNhapPOJO(id, idNCC, tenPhieu, nguoiThucHien, TenNhaCungCap, tongTienNhap, ngayNhapNguyenLieu);
                dsPhieuNhap.add(phieuNhap);
            }

            // Đóng kết nối
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhieuNhap;
    }
}
