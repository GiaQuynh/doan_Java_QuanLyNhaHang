/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.thanhtoan.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Parameter_ThanhToan {

    public Parameter_ThanhToan(String idhoadon, String tenNhanVien, String tenBan, String ngaythanhtoan, ArrayList<ItemMonAn_report> listThanhToan,String total) {
        this.idhoadon = idhoadon;
        this.tenNhanVien = tenNhanVien;
        this.tenBan = tenBan;
        this.ngaythanhtoan = ngaythanhtoan;
        this.field = listThanhToan;
        this.total = total;
    }

    public Parameter_ThanhToan() {
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(String ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public ArrayList<ItemMonAn_report> getListThanhToan() {
        return field;
    }

    public void setListThanhToan(ArrayList<ItemMonAn_report> listThanhToan) {
        this.field = listThanhToan;
    }

    public String getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(String idhoadon) {
        this.idhoadon = idhoadon;
    }
    String idhoadon;
    String total;
    String tenNhanVien;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<ItemMonAn_report> getField() {
        return field;
    }

    public void setField(ArrayList<ItemMonAn_report> field) {
        this.field = field;
    }
    String tenBan;
    String ngaythanhtoan;
    ArrayList<ItemMonAn_report> field;

}
