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
        String sql = "SELECT dg.MaDon, kh.TenKhachHang, dg.NgayNhan, dg.NgayTra, dg.TrangThai "
                + "FROM DonGiat dg "
                + "JOIN KhachHang kh ON dg.MaKhachHang = kh.MaKhachHang "
                + "ORDER BY dg.MaDon DESC";

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

    public boolean insert(DonGiat dg) {
        // Trước tiên cần tìm hoặc tạo khách hàng
        int maKhachHang = findOrCreateKhachHang(dg.getTenKhachHang());

        String sql = "INSERT INTO DonGiat (MaKhachHang, NgayNhan, NgayTra, TrangThai) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachHang);
            ps.setDate(2, dg.getNgayNhan());
            ps.setDate(3, dg.getNgayTra());
            ps.setString(4, dg.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(DonGiat dg) {
        // Tìm hoặc tạo khách hàng
        int maKhachHang = findOrCreateKhachHang(dg.getTenKhachHang());

        String sql = "UPDATE DonGiat SET MaKhachHang=?, NgayNhan=?, NgayTra=?, TrangThai=? WHERE MaDon=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachHang);
            ps.setDate(2, dg.getNgayNhan());
            ps.setDate(3, dg.getNgayTra());
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

    // THÊM MỚI: Phương thức tìm hoặc tạo khách hàng
    private int findOrCreateKhachHang(String tenKhachHang) {
        // Tìm khách hàng có sẵn
        String sqlFind = "SELECT MaKhachHang FROM KhachHang WHERE TenKhachHang = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sqlFind)) {

            ps.setString(1, tenKhachHang);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("MaKhachHang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Nếu không tìm thấy, tạo mới
        String sqlInsert = "INSERT INTO KhachHang (TenKhachHang) VALUES (?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tenKhachHang);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1; // Fallback
    }

    // THÊM MỚI: Tìm đơn giặt theo mã
    public DonGiat getById(int maDon) {
        String sql = "SELECT dg.MaDon, kh.TenKhachHang, dg.NgayNhan, dg.NgayTra, dg.TrangThai "
                + "FROM DonGiat dg "
                + "JOIN KhachHang kh ON dg.MaKhachHang = kh.MaKhachHang "
                + "WHERE dg.MaDon = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDon);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new DonGiat(
                        rs.getInt("MaDon"),
                        rs.getString("TenKhachHang"),
                        rs.getDate("NgayNhan"),
                        rs.getDate("NgayTra"),
                        rs.getString("TrangThai")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
