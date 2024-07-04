/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class KhoNguyenLieu {

    int id_kho_nguyenlieu;
    int id_nguyenlieu;
    String tennguyenlieu;
    float soluong;

    public int getId_nguyenlieu() {
        return id_nguyenlieu;
    }

    public void setId_nguyenlieu(int id_nguyenlieu) {
        this.id_nguyenlieu = id_nguyenlieu;
    }

    public int getId_kho_nguyenlieu() {
        return id_kho_nguyenlieu;
    }

    public void setId_kho_nguyenlieu(int id_kho_nguyenlieu) {
        this.id_kho_nguyenlieu = id_kho_nguyenlieu;
    }

    public String getTennguyenlieu() {
        return tennguyenlieu;
    }

    public void setTennguyenlieu(String tennguyenlieu) {
        this.tennguyenlieu = tennguyenlieu;
    }

    public float getSoluong() {
        return soluong;
    }

    public void setSoluong(float soluong) {
        this.soluong = soluong;
    }

    public KhoNguyenLieu() {
    }
}
