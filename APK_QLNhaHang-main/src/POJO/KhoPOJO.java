/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author ASUS
 */
public class KhoPOJO {

    public KhoPOJO(int stock_id, String ten_nguyenlieu, float soluong, int idnguyenlieu, String donvitinh) {
        this.stock_id = stock_id;
        this.ten_nguyenlieu = ten_nguyenlieu;
        this.soluong = soluong;
        this.idnguyenlieu = idnguyenlieu;
        this.donvitinh = donvitinh;
    }

    public KhoPOJO() {
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public String getTen_nguyenlieu() {
        return ten_nguyenlieu;
    }

    public void setTen_nguyenlieu(String ten_nguyenlieu) {
        this.ten_nguyenlieu = ten_nguyenlieu;
    }

    public float getSoluong() {
        return soluong;
    }

    public void setSoluong(float soluong) {
        this.soluong = soluong;
    }

    public int getIdnguyenlieu() {
        return idnguyenlieu;
    }

    public void setIdnguyenlieu(int idnguyenlieu) {
        this.idnguyenlieu = idnguyenlieu;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

   

  
    private int stock_id;
    private String ten_nguyenlieu;
    private float soluong;
    private int idnguyenlieu;
    private String donvitinh;

  
}
