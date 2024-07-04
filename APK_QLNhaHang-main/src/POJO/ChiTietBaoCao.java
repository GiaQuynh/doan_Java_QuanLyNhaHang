/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author Quin Quin
 */
public class ChiTietBaoCao {
    int id_chitietbaocao;
    int id_baocao;
    int id_hoadon;
    float soTien;

    public ChiTietBaoCao() {
    }

    public ChiTietBaoCao(int id_chitietbaocao, int id_baocao, int id_hoadon, float soTien) {
        this.id_chitietbaocao = id_chitietbaocao;
        this.id_baocao = id_baocao;
        this.id_hoadon = id_hoadon;
        this.soTien = soTien;
    }

    public int getId_chitietbaocao() {
        return id_chitietbaocao;
    }

    public int getId_baocao() {
        return id_baocao;
    }

    public int getId_hoadon() {
        return id_hoadon;
    }

    public float getSoTien() {
        return soTien;
    }

    public void setId_chitietbaocao(int id_chitietbaocao) {
        this.id_chitietbaocao = id_chitietbaocao;
    }

    public void setId_baocao(int id_baocao) {
        this.id_baocao = id_baocao;
    }

    public void setId_hoadon(int id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public void setSoTien(float soTien) {
        this.soTien = soTien;
    }
    
    
}
