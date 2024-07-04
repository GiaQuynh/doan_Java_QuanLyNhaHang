/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author ASUS
 */
public class ChitietPhieuNhapPOJO {

    public ChitietPhieuNhapPOJO(int idChitietPhieu, int idPhieuNhap, int idNguyenLieu, String tenNguyenLieu, float soLuongNhap, float donGia, float thanhTien) {
        this.idChitietPhieu = idChitietPhieu;
        this.idPhieuNhap = idPhieuNhap;
        this.idNguyenLieu = idNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
        this.soLuongNhap = soLuongNhap;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChitietPhieuNhapPOJO() {
    }

    public int getIdChitietPhieu() {
        return idChitietPhieu;
    }

    public void setIdChitietPhieu(int idChitietPhieu) {
        this.idChitietPhieu = idChitietPhieu;
    }

    public int getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(int idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
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

    private int idChitietPhieu;
    private int idPhieuNhap;
    private int idNguyenLieu;
    private String tenNguyenLieu;
    private float soLuongNhap;
    private float donGia;
    private float thanhTien;

}
