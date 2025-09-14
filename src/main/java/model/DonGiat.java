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
    private String tenKhachHang;
    private Date ngayNhan;
    private Date ngayTra;
    private String trangThai;

    // Constructor mặc định
    public DonGiat() {
    }

    // Constructor với java.sql.Date
    public DonGiat(int maDon, String tenKhachHang, Date ngayNhan, Date ngayTra, String trangThai) {
        this.maDon = maDon;
        this.tenKhachHang = tenKhachHang;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    // Constructor với java.util.Date - ĐÃ SỬA LỖI
    public DonGiat(int maDon, String tenKhachHang, java.util.Date ngayNhan, java.util.Date ngayTra, String trangThai) {
        this.maDon = maDon;
        this.tenKhachHang = tenKhachHang;
        // Chuyển đổi từ java.util.Date sang java.sql.Date
        this.ngayNhan = ngayNhan != null ? new java.sql.Date(ngayNhan.getTime()) : null;
        this.ngayTra = ngayTra != null ? new java.sql.Date(ngayTra.getTime()) : null;
        this.trangThai = trangThai;
    }

    // Getter & Setter
    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
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

    @Override
    public String toString() {
        return "DonGiat{"
                + "maDon=" + maDon
                + ", tenKhachHang='" + tenKhachHang + '\''
                + ", ngayNhan=" + ngayNhan
                + ", ngayTra=" + ngayTra
                + ", trangThai='" + trangThai + '\''
                + '}';
    }
}
