/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.DonGiat;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tai Smart PC
 */
public class DonGiatDAO {

    public List<DonGiat> getAll() {
        List<DonGiat> list = new ArrayList<>();
        String sql = "SELECT MaDon, TenKhachHang, NgayNhan, NgayTra, TrangThai FROM DonGiat";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DonGiat dg = new DonGiat(
                        rs.getInt("MaDon"),
                        rs.getString("TenKhachHang"),
                        rs.getDate("NgayNhan"),
                        rs.getDate("NgayTra"),
                        rs.getString("TrangThai")
                );
                list.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm đơn giặt
    public boolean insert(DonGiat dg) {
        String sql = "INSERT INTO DonGiat (TenKhachHang, NgayNhan, NgayTra, TrangThai) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dg.getTenKhachHang());
            ps.setDate(2, new java.sql.Date(dg.getNgayNhan().getTime()));
            ps.setDate(3, dg.getNgayTra() != null ? new java.sql.Date(dg.getNgayTra().getTime()) : null);
            ps.setString(4, dg.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật đơn giặt
    public boolean update(DonGiat dg) {
        String sql = "UPDATE DonGiat SET TenKhachHang=?, NgayNhan=?, NgayTra=?, TrangThai=? WHERE MaDon=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dg.getTenKhachHang());
            ps.setDate(2, new java.sql.Date(dg.getNgayNhan().getTime()));
            ps.setDate(3, dg.getNgayTra() != null ? new java.sql.Date(dg.getNgayTra().getTime()) : null);
            ps.setString(4, dg.getTrangThai());
            ps.setInt(5, dg.getMaDon());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa đơn giặt
    public boolean delete(int maDon) {
        String sql = "DELETE FROM DonGiat WHERE MaDon=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
