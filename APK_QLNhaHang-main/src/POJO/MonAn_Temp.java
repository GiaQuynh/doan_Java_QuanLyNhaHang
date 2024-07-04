/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class MonAn_Temp {

    public MonAn_Temp() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getGiamon() {
        return giamon;
    }

    public void setGiamon(String giamon) {
        this.giamon = giamon;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
    int id;

    public int getIddanhmuc() {
        return iddanhmuc;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setIddanhmuc(int iddanhmuc) {
        this.iddanhmuc = iddanhmuc;
    }
    int iddanhmuc;
    String tenmon;
    String giamon;
    String mota;
    String hinhanh;
    int soluong;

    public MonAn_Temp(int id, int iddanhmuc, String tenmon, String giamon, String mota, String hinhanh, int soluong) {
        this.id = id;
        this.iddanhmuc = iddanhmuc;
        this.tenmon = tenmon;
        this.giamon = giamon;
        this.mota = mota;
        this.hinhanh = hinhanh;
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "MonAn_Temp{"+ "id=" + id + ", tenmon='" + tenmon + '\''+ ", giamon='" + giamon + '\''+ ", soluong=" + soluong + '}';
    }

}
