/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import POJO.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Gia Bao
 */
public class NhanVienDao 
{
    public static ArrayList<NhanVien> HienThiDanhSachNhanVien()
    {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        try {
            String sql ="select * from nhanvien";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next())
            {
                NhanVien nv = new NhanVien();
                
                nv.setMaNV(rs.getInt("staff_id"));
                nv.setHoTen(rs.getString("ten"));
                // Lấy ngày sinh từ ResultSet và chuyển đổi thành kiểu Date
                Date ngaySinh = rs.getDate("NgaySinh");
                nv.setNgaySinh(ngaySinh); // Gán ngày sinh cho đối tượng NhanVien
                nv.setEmail(rs.getString("email"));
                nv.setSoCanCuoc(rs.getString("socancuoccd"));
                nv.setSDT(rs.getString("so_dien_thoai"));

                dsNV.add(nv);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNV;
    }
    
    public static ArrayList<NhanVien> TimNhanVien(int MaNV)
    {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        try {
            String sql = String.format("select * from NhanVien where staff_id=%d", MaNV);
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next())
            {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt("staff_id"));
                nv.setHoTen(rs.getString("ten"));
                // Lấy ngày sinh từ ResultSet và chuyển đổi thành kiểu Date
                Date ngaySinh = rs.getDate("NgaySinh");
                nv.setNgaySinh(ngaySinh); // Gán ngày sinh cho đối tượng NhanVien
                nv.setEmail(rs.getString("email"));
                nv.setSoCanCuoc(rs.getString("socancuoccd"));
                nv.setSDT(rs.getString("so_dien_thoai"));
                       
                dsNV.add(nv);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNV;
    }
   
    public static boolean ThemNhanVien(NhanVien nv) {
    boolean kq = false;
    String sql = String.format("INSERT INTO NhanVien(ten, NgaySinh, email, socancuoccd, so_dien_thoai) VALUES ('%s', '%tF', '%s', '%s', '%s')", nv.getHoTen(), nv.getNgaySinh(), nv.getEmail(), nv.getSoCanCuoc(), nv.getSDT());

    Connect provider = new Connect();
    
    try {
        provider.ketNoiCSDL();
        
        int n = provider.executeUpdate(sql);
        
        if (n == 1) {
            kq = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    
    return kq;
}
 
    public static boolean XoaNhanVien(int MaNV)
    {
        boolean kq = false;
        String sql = String.format("delete from NhanVien where staff_id=%d", MaNV);
        Connect provider = new Connect();
        provider.ketNoiCSDL();
        int n = provider.executeUpdate(sql);
        if(n==1)
        {
            kq = true;
        }
        provider.close();
        return kq;
    }
    
    public static boolean SuaNhanVien(NhanVien nv)
    {
        boolean kq = false;
        
        String sql = String.format("UPDATE NhanVien "
                                    + "SET ten = '%s', NgaySinh = '%tF', email = '%s', socancuoccd = '%s', so_dien_thoai = '%s' "
                                    + "WHERE staff_id = '%d'", nv.getHoTen(), nv.getNgaySinh(), nv.getEmail(), nv.getSoCanCuoc(), nv.getSDT(), nv.getMaNV());
       Connect provider = new Connect();
        try {
        provider.ketNoiCSDL();
        
        int n = provider.executeUpdate(sql);
        
        if (n == 1) {
            kq = true;
        }
        } catch (Exception e) {
        e.printStackTrace();
        } finally {
        provider.close();
        }
        
         return kq;
    }
    
   
}
