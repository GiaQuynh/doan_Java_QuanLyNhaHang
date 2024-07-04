/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class BanAn {

    int idban;
    String tenban;
    Timestamp  ngayGioDat;
    String trangthai;
    String ghichu;

    public int getIdban() {
        return idban;
    }

    public void setIdban(int idban) {
        this.idban = idban;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public Timestamp getNgayGioDat() {
        return ngayGioDat;
    }

    public void setNgayGioDat(Timestamp ngayGioDat) {
        this.ngayGioDat = ngayGioDat;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public BanAn(int idban, String tenban, Timestamp ngayGioDat, String trangthai, String ghichu) {
        this.idban = idban;
        this.tenban = tenban;
        this.ngayGioDat = ngayGioDat;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
    }

    public BanAn() {
    }

}
