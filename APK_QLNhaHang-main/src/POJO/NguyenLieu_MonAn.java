/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class NguyenLieu_MonAn {
    int idmon;
    int idnguyenlieu;
    String tennguyenlieu;
    String donvi;
    float soluong;

    public NguyenLieu_MonAn() {
    }

    public NguyenLieu_MonAn(int idmon, int idnguyenlieu, String tennguyenlieu, String donvi, float soluong) {
        this.idmon = idmon;
        this.idnguyenlieu = idnguyenlieu;
        this.tennguyenlieu = tennguyenlieu;
        this.donvi = donvi;
        this.soluong = soluong;
    }

    public int getIdmon() {
        return idmon;
    }

    public void setIdmon(int idmon) {
        this.idmon = idmon;
    }

    public int getIdnguyenlieu() {
        return idnguyenlieu;
    }

    public void setIdnguyenlieu(int idnguyenlieu) {
        this.idnguyenlieu = idnguyenlieu;
    }

    public String getTennguyenlieu() {
        return tennguyenlieu;
    }

    public void setTennguyenlieu(String tennguyenlieu) {
        this.tennguyenlieu = tennguyenlieu;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public float getSoluong() {
        return soluong;
    }

    public void setSoluong(float soluong) {
        this.soluong = soluong;
    }
    
}
