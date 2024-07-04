/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Gia Bao
 */
public class TinhLuongNV
{ 
    private int MaLuong;
    private int staff_id;
    private int SoGioLam;
    private int LuongCoBan;
    private int TongLuong;

    public TinhLuongNV() {
    }

    public TinhLuongNV(int MaLuong, int staff_id, int SoGioLam, int LuongCoBan, int TongLuong) {
        this.MaLuong = MaLuong;
        this.staff_id = staff_id;
        this.SoGioLam = SoGioLam;
        this.LuongCoBan = LuongCoBan;
        this.TongLuong = TongLuong;
    }

    public int getMaLuong() {
        return MaLuong;
    }

    public void setMaLuong(int MaLuong) {
        this.MaLuong = MaLuong;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getSoGioLam() {
        return SoGioLam;
    }

    public void setSoGioLam(int SoGioLam) {
        this.SoGioLam = SoGioLam;
    }

    public int getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(int LuongCoBan) {
        this.LuongCoBan = LuongCoBan;
    }

    public int getTongLuong() {
        return TongLuong;
    }

    public void setTongLuong(int TongLuong) {
        this.TongLuong = TongLuong;
    }
    
    
   
}
