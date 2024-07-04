/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;
import java.sql.Time;

import java.util.Date;

/**
 *
 * @author Gia Bao
 */
public class LichLamNV
{
    private int MaLichLam;
    private int MaNV;
    private String HoTen;
    private Date NgayLam;
    private Time GioBatDau;
    private Time GioKetThuc;

    public LichLamNV() {
    }

    public LichLamNV(int MaLichLam, int MaNV, String HoTen, Date NgayLam, Time GioBatDau, Time GioKetThuc) {
        this.MaLichLam = MaLichLam;
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.NgayLam = NgayLam;
        this.GioBatDau = GioBatDau;
        this.GioKetThuc = GioKetThuc;
    }

    public int getMaLichLam() {
        return MaLichLam;
    }

    public void setMaLichLam(int MaLichLam) {
        this.MaLichLam = MaLichLam;
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

    public Date getNgayLam() {
        return NgayLam;
    }

    public void setNgayLam(Date NgayLam) {
        this.NgayLam = NgayLam;
    }

    public Time getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(Time GioBatDau) {
        this.GioBatDau = GioBatDau;
    }

    public Time getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(Time GioKetThuc) {
        this.GioKetThuc = GioKetThuc;
    }

    
    
    
}
