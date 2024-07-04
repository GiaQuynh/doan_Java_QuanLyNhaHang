/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class Order_Detail {

    private int detailId;
    private int orderId;
    private int idMonan;
    private String tenMonan;

    private int soLuong;
    private double donGia;
    private double thanhTien;

    public Order_Detail() {
    }

    public Order_Detail(int detailId, int orderId, int idMonan, String tenMonan, int soLuong, double donGia, double thanhTien) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.idMonan = idMonan;
        this.tenMonan = tenMonan;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getIdMonan() {
        return idMonan;
    }

    public void setIdMonan(int idMonan) {
        this.idMonan = idMonan;
    }

    public String getTenMonan() {
        return tenMonan;
    }

    public void setTenMonan(String tenMonan) {
        this.tenMonan = tenMonan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

}
