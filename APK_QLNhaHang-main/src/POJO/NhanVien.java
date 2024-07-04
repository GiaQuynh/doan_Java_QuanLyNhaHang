/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author Gia Bao
 */
public class NhanVien {
    
    private int MaNV;
    private String HoTen;
    private Date NgaySinh;
    private String Email;
    private String SoCanCuoc;
    private String SDT;

    public NhanVien() {
    }

    public NhanVien(int MaNV, String HoTen, Date NgaySinh, String Email, String SoCanCuoc, String SDT) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.Email = Email;
        this.SoCanCuoc = SoCanCuoc;
        this.SDT = SDT;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSoCanCuoc() {
        return SoCanCuoc;
    }

    public void setSoCanCuoc(String SoCanCuoc) {
        this.SoCanCuoc = SoCanCuoc;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    
    
    
    

}


