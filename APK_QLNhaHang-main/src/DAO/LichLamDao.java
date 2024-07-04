/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import POJO.LichLamNV;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Gia Bao
 */
public class LichLamDao
{
    public static ArrayList<LichLamNV> HienThiDanhSachLichLam()
    {
        ArrayList<LichLamNV> dsLL = new ArrayList<LichLamNV>();
        try {
            String sql = "SELECT * FROM LichLamViec";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                LichLamNV ll = new LichLamNV();

                ll.setMaLichLam(rs.getInt("MaLichLam"));
                ll.setMaNV(rs.getInt("staff_id"));
                ll.setHoTen(rs.getString("ten"));

                Date ngayLam = rs.getDate("NgayLam");
                ll.setNgayLam(ngayLam); 

                // Chuyển đổi giá trị từ Time của SQL thành LocalTime
                Time gioBatDauSQL = rs.getTime("GioBatDau");
                Time gioKetThucSQL = rs.getTime("GioKetThuc");
                LocalTime gioBatDau = gioBatDauSQL.toLocalTime();
                LocalTime gioKetThuc = gioKetThucSQL.toLocalTime();

                // Tạo đối tượng Time mới từ LocalTime
                Time gioBatDauTime = Time.valueOf(gioBatDau);
                Time gioKetThucTime = Time.valueOf(gioKetThuc);

                // Đặt giá trị cho thuộc tính GioBatDau và GioKetThuc của đối tượng LichLamNV
                ll.setGioBatDau(gioBatDauTime);
                ll.setGioKetThuc(gioKetThucTime);

                dsLL.add(ll);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLL;
        }
    
    public static ArrayList<LichLamNV> TimKiemLichLam(Date KiemNgayLam)
    {
        ArrayList<LichLamNV> dsLL = new ArrayList<LichLamNV>();
    try {
        String sql = String.format("select * from LichLamViec where NgayLam='%tF'", KiemNgayLam);
        Connect provider = new Connect();
        provider.ketNoiCSDL();
        ResultSet rs = provider.executeQuery(sql);
        while (rs.next()) {
            LichLamNV ll = new LichLamNV();
            ll.setMaLichLam(rs.getInt("MaLichLam"));
            ll.setMaNV(rs.getInt("staff_id"));
            ll.setHoTen(rs.getString("ten"));
            
            Date ngayLam = rs.getDate("NgayLam");
            ll.setNgayLam(ngayLam); 

            // Chuyển đổi giá trị từ Time của SQL thành LocalTime
            Time gioBatDauSQL = rs.getTime("GioBatDau");
            Time gioKetThucSQL = rs.getTime("GioKetThuc");
            LocalTime gioBatDau = gioBatDauSQL.toLocalTime();
            LocalTime gioKetThuc = gioKetThucSQL.toLocalTime();

            // Tạo đối tượng Time mới từ LocalTime
            Time gioBatDauTime = Time.valueOf(gioBatDau);
            Time gioKetThucTime = Time.valueOf(gioKetThuc);

            // Đặt giá trị cho thuộc tính GioBatDau và GioKetThuc của đối tượng LichLamNV
            ll.setGioBatDau(gioBatDauTime);
            ll.setGioKetThuc(gioKetThucTime);
   
            dsLL.add(ll);
        }
        provider.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dsLL;
    }
    
    public static boolean ThemLichLam(LichLamNV ll) {
        boolean kq = false;

        Time gioBatDau = ll.getGioBatDau();
        String gioBatDauFormatted = gioBatDau.toString(); // Chuyển thời gian thành chuỗi

        Time gioKetThuc = ll.getGioKetThuc();
        String gioKetThucFormatted = gioKetThuc.toString(); // Chuyển thời gian thành chuỗi

        String sql = String.format("INSERT INTO LichLamViec(staff_id, ten, NgayLam, GioBatDau, GioKetThuc) VALUES (%d, '%s', '%tF', '%s', '%s')",
                ll.getMaNV(), ll.getHoTen(), ll.getNgayLam(), gioBatDauFormatted, gioKetThucFormatted);
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
    
    public static boolean XoaLichLam(int MaLich)
    {
        boolean kq = false;
        String sql = String.format("delete from LichLamViec where MaLichLam=%d", MaLich);
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
    
    public static boolean SuaLichLam(LichLamNV ll)
    {
       boolean kq = false;

    Time gioBatDau = ll.getGioBatDau();
    String gioBatDauFormatted = gioBatDau.toString(); // Chuyển thời gian thành chuỗi

    Time gioKetThuc = ll.getGioKetThuc();
    String gioKetThucFormatted = gioKetThuc.toString(); // Chuyển thời gian thành chuỗi
        String sql = String.format("UPDATE LichLamViec "
                                    + "SET staff_id = '%s', ten = '%s', NgayLam = '%tF', GioBatDau = '%s', GioKetThuc = '%s' "
                                    + "WHERE MaLichLam = '%d'", ll.getMaNV(), ll.getHoTen(), ll.getNgayLam(), gioBatDauFormatted, gioKetThucFormatted, ll.getMaLichLam());
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
