package POJO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhap_item {

    public ChiTietPhieuNhap_item() {
    }

    public ChiTietPhieuNhap_item(int idNguyenLieu, String tenNguyenLieu, float soLuongNhap, float donGia,String donvitinh, float thanhTien) {
        this.idNguyenLieu = idNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
        this.soLuongNhap = soLuongNhap;
        this.donGia = donGia;
        this.donvitinh = donvitinh;
        this.thanhTien = thanhTien;
    }
    
    public int getIdNguyenLieu() {
        return idNguyenLieu;
    }

    public void setIdNguyenLieu(int idNguyenLieu) {
        this.idNguyenLieu = idNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public float getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(float soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    private int idNguyenLieu;
    private String tenNguyenLieu;
    private float soLuongNhap;
    private float donGia;
    private float thanhTien;
    private String donvitinh;
}
