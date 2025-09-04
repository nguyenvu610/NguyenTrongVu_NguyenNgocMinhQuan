/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tai Smart PC
 */
public class DichVuGiat {

    private int maDichVu;
    private String tenDichVu;
    private double giaTien;
    private String donViTinh;

    // Constructor đầy đủ
    public DichVuGiat(int maDichVu, String tenDichVu, double giaTien, String donViTinh) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaTien = giaTien;
        this.donViTinh = donViTinh;
    }

    // Constructor không có mã (dùng khi thêm mới)
    public DichVuGiat(String tenDichVu, double giaTien, String donViTinh) {
        this.tenDichVu = tenDichVu;
        this.giaTien = giaTien;
        this.donViTinh = donViTinh;
    }

    // Getter & Setter
    public int getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    @Override
    public String toString() {
        return tenDichVu + " - " + giaTien + " / " + donViTinh;
    }

    public static void main(String[] args) {
        DichVuGiat dv1 = new DichVuGiat(1, "Giặt khô", 50000, "kg");
        DichVuGiat dv2 = new DichVuGiat(2, "Giặt ướt", 30000, "kg");
        DichVuGiat dv3 = new DichVuGiat("Giặt sấy", 70000, "kg");

        System.out.println("Danh sách dịch vụ giặt:");
        System.out.println(dv1);
        System.out.println(dv2);
        System.out.println(dv3);
    }
}
