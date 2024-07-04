/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import POJO.TinhLuongNV;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Gia Bao
 */
public class TinhLuongDAO 
{
    public static ArrayList<TinhLuongNV> HienThiDanhSachLuong()
    {
        ArrayList<TinhLuongNV> dsTL = new ArrayList<TinhLuongNV>();
        try {
            String sql ="select * from TinhLuong";
            Connect provider = new Connect();
            provider.ketNoiCSDL();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next())
            {
                TinhLuongNV tl = new TinhLuongNV();
                
                tl.setMaLuong(rs.getInt("MaLuong"));
                tl.setStaff_id(rs.getInt("staff_id"));
                tl.setSoGioLam(rs.getInt("SoGioLam"));         
                tl.setLuongCoBan(rs.getInt("LuongCoBan"));
                tl.setTongLuong(rs.getInt("TongLuong"));

                dsTL.add(tl);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTL;
    }
    
    
    public static boolean ThemLuong(TinhLuongNV tl)
    {
        boolean kq = false;
        String sql = String.format("insert into TinhLuong(staff_id, SoGioLam, LuongCoBan, TongLuong) values (%d, %d, %d, %d)", tl.getStaff_id(),tl.getSoGioLam(), tl.getLuongCoBan(), tl.getTongLuong());
        Connect  provider = new Connect ();
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
    public static boolean XoaLuong(int MaLuong)
    {
        boolean kq = false;
        String sql = String.format("delete from TinhLuong where MaLuong=%d", MaLuong);
        Connect  provider = new Connect ();
        provider.ketNoiCSDL();
        int n = provider.executeUpdate(sql);
        if(n==1)
        {
            kq = true;
        }
        provider.close();
        return kq;
    }
    
    public static boolean SuaLuong(TinhLuongNV tl)
    {
        boolean kq = false;

    
        String sql = String.format("UPDATE TinhLuong "
                            + "SET staff_id = '%s', SoGioLam = %d, LuongCoBan = %d, TongLuong = %d "
                            + "WHERE MaLuong = %d", tl.getStaff_id(), tl.getSoGioLam(), tl.getLuongCoBan(), tl.getTongLuong(), tl.getMaLuong());

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
  
    
    public static boolean tinhLuong(int staff_id) {
        String sql = String.format("EXEC TinhLuongNV %d", staff_id);
        Connect  provider = new Connect();

        provider.ketNoiCSDL();
        int result = provider.executeUpdate(sql);
        provider.close();

        return result > 0;
    }
}
   
    

