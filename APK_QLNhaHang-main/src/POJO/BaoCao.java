/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.Date;

/**
 *
 * @author Quin Quin
 */
public class BaoCao {
    int id_baocao;
    String loai_bao_cao;
    float TongTien;
    String thong_tin_chi_tiet;
    Date ngay_tao_bao_cao;

    public BaoCao() {
    }

    public BaoCao(int id_baocao, String loai_bao_cao, float TongTien, String thong_tin_chi_tiet, Date ngay_tao_bao_cao) {
        this.id_baocao = id_baocao;
        this.loai_bao_cao = loai_bao_cao;
        this.TongTien = TongTien;
        this.thong_tin_chi_tiet = thong_tin_chi_tiet;
        this.ngay_tao_bao_cao = ngay_tao_bao_cao;
    }

    public int getId_baocao() {
        return id_baocao;
    }

    public String getLoai_bao_cao() {
        return loai_bao_cao;
    }

    public float getTongTien() {
        return TongTien;
    }

    public String getThong_tin_chi_tiet() {
        return thong_tin_chi_tiet;
    }

    public Date getNgay_tao_bao_cao() {
        return ngay_tao_bao_cao;
    }

    public void setId_baocao(int id_baocao) {
        this.id_baocao = id_baocao;
    }

    public void setLoai_bao_cao(String loai_bao_cao) {
        this.loai_bao_cao = loai_bao_cao;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public void setThong_tin_chi_tiet(String thong_tin_chi_tiet) {
        this.thong_tin_chi_tiet = thong_tin_chi_tiet;
    }

    public void setNgay_tao_bao_cao(Date ngay_tao_bao_cao) {
        this.ngay_tao_bao_cao = ngay_tao_bao_cao;
    }
    
    
}
