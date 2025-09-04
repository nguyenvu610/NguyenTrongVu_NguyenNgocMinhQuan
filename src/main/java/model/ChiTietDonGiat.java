/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tai Smart PC
 */
public class ChiTietDonGiat {

    private int maChiTiet;
    private int maDon;
    private int maDichVu;
    private int soLuong;
    private String moTa;

    public ChiTietDonGiat(int maChiTiet, int maDon, int maDichVu, int soLuong, String moTa) {
        this.maChiTiet = maChiTiet;
        this.maDon = maDon;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public ChiTietDonGiat(int maDon, int maDichVu, int soLuong, String moTa) {
        this.maDon = maDon;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public int getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "ChiTietDonGiat{"
                + "maChiTiet=" + maChiTiet
                + ", maDon=" + maDon
                + ", maDichVu=" + maDichVu
                + ", soLuong=" + soLuong
                + ", moTa='" + moTa + '\''
                + '}';
    }
}
