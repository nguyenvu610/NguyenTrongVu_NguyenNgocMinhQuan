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
public class DonGiat {

    private int maDon;
    private int maKhachHang;
    private Date ngayNhan;
    private Date ngayTra;
    private String trangThai;
    private boolean daThanhToan;
    private double tongTien;

    // Constructor đầy đủ
    public DonGiat(int maDon, int maKhachHang, Date ngayNhan, Date ngayTra,
            String trangThai, boolean daThanhToan, double tongTien) {
        this.maDon = maDon;
        this.maKhachHang = maKhachHang;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
        this.daThanhToan = daThanhToan;
        this.tongTien = tongTien;
    }

    // Constructor khi chưa có mã đơn
    public DonGiat(int maKhachHang, Date ngayNhan, Date ngayTra,
            String trangThai, boolean daThanhToan, double tongTien) {
        this.maKhachHang = maKhachHang;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
        this.daThanhToan = daThanhToan;
        this.tongTien = tongTien;
    }

    // Getter/Setter
    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(boolean daThanhToan) {
        this.daThanhToan = daThanhToan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "DonGiat{"
                + "maDon=" + maDon
                + ", maKhachHang=" + maKhachHang
                + ", ngayNhan=" + ngayNhan
                + ", ngayTra=" + ngayTra
                + ", trangThai='" + trangThai + '\''
                + ", daThanhToan=" + daThanhToan
                + ", tongTien=" + tongTien
                + '}';
    }
}
