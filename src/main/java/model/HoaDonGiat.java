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
    private String tenKhachHang;
    private String trangThai;
    private double tongTien;

    // Constructor mặc định
    public HoaDonGiat() {
    }

    // Constructor đầy đủ với ID (cho việc load từ database)
    public HoaDonGiat(int maHoaDon, String tenKhachHang, String trangThai, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    // Constructor không có ID (cho việc thêm mới)
    public HoaDonGiat(String tenKhachHang, String trangThai, double tongTien) {
        this.tenKhachHang = tenKhachHang;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    // Getter & Setter
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
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

    @Override
    public String toString() {
        return "HoaDonGiat{"
                + "maHoaDon=" + maHoaDon
                + ", tenKhachHang='" + tenKhachHang + '\''
                + ", trangThai='" + trangThai + '\''
                + ", tongTien=" + tongTien
                + '}';
    }
}
