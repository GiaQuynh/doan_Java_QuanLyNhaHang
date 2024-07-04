/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.math.BigDecimal;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class PhieuNhapPOJO {

    public PhieuNhapPOJO() {
    }

    public PhieuNhapPOJO(int idphieunhap, int idNhaCungCap, String tenPhieu, String tenNguoiThucHien, String TenNhaCungCap, Float tongTienNhap, Date ngayNhapNguyenLieu) {
        this.idphieunhap = idphieunhap;
        this.idNhaCungCap = idNhaCungCap;
        this.tenPhieu = tenPhieu;
        this.tenNguoiThucHien = tenNguoiThucHien;
        this.TenNhaCungCap = TenNhaCungCap;
        this.tongTienNhap = tongTienNhap;
        this.ngayNhapNguyenLieu = ngayNhapNguyenLieu;
    }

    public int getIdphieunhap() {
        return idphieunhap;
    }

    public void setIdphieunhap(int idphieunhap) {
        this.idphieunhap = idphieunhap;
    }

    public int getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(int idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public String getTenPhieu() {
        return tenPhieu;
    }

    public void setTenPhieu(String tenPhieu) {
        this.tenPhieu = tenPhieu;
    }

    public String getTenNguoiThucHien() {
        return tenNguoiThucHien;
    }

    public void setTenNguoiThucHien(String tenNguoiThucHien) {
        this.tenNguoiThucHien = tenNguoiThucHien;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public Float getTongTienNhap() {
        return tongTienNhap;
    }

    public void setTongTienNhap(Float tongTienNhap) {
        this.tongTienNhap = tongTienNhap;
    }

    public Date getNgayNhapNguyenLieu() {
        return ngayNhapNguyenLieu;
    }

    public void setNgayNhapNguyenLieu(Date ngayNhapNguyenLieu) {
        this.ngayNhapNguyenLieu = ngayNhapNguyenLieu;
    }

   

    private int idphieunhap;
    private int idNhaCungCap;
    private String tenPhieu;
    private String tenNguoiThucHien;
    private String TenNhaCungCap;

    private Float tongTienNhap;
    private Date ngayNhapNguyenLieu;
}
