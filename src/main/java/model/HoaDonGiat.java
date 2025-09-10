/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
/**
 *
 * @author Tai Smart PC
 */
public class HoaDonGiat {

    private int maHoaDon;
    private int maKhachHang;
    private String trangThai;
    private double tongTien;

    public HoaDonGiat(int maKH, String trangThai, double tongTien) {
        this.maKhachHang = maKH;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    public HoaDonGiat(int maHD, int maKH, String trangThai, double tongTien) {
        this(maKH, trangThai, tongTien);
        this.maHoaDon = maHD;
    }

    // Getter & Setter
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
