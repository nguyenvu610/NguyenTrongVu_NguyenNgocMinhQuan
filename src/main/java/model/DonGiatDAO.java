/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tai Smart PC
 */
public class DonGiatDAO {

    // Lấy toàn bộ đơn giặt
    public List<DonGiat> getAll() {
        List<DonGiat> list = new ArrayList<>();
        String sql = "SELECT MaDon, MaKhachHang, NgayNhan, NgayTra, TrangThai, DaThanhToan, TongTien FROM DonGiat";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new DonGiat(
                        rs.getInt("MaDon"),
                        rs.getInt("MaKhachHang"),
                        rs.getDate("NgayNhan"),
                        rs.getDate("NgayTra"),
                        rs.getString("TrangThai"),
                        rs.getBoolean("DaThanhToan"),
                        rs.getDouble("TongTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm đơn giặt
    public int insert(DonGiat d) {
        String sql = "INSERT INTO DonGiat(MaKhachHang, NgayNhan, NgayTra, TrangThai, DaThanhToan, TongTien) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, d.getMaKhachHang());
            ps.setDate(2, d.getNgayNhan());
            ps.setDate(3, d.getNgayTra());
            ps.setString(4, d.getTrangThai());
            ps.setBoolean(5, d.isDaThanhToan());
            ps.setDouble(6, d.getTongTien());

            int r = ps.executeUpdate();
            if (r > 0) {
                try (ResultSet gk = ps.getGeneratedKeys()) {
                    if (gk.next()) {
                        return gk.getInt(1); // Trả về mã đơn mới
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Cập nhật đơn giặt
    public boolean update(DonGiat d) {
        String sql = "UPDATE DonGiat SET MaKhachHang=?, NgayNhan=?, NgayTra=?, TrangThai=?, DaThanhToan=?, TongTien=? WHERE MaDon=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getMaKhachHang());
            ps.setDate(2, d.getNgayNhan());
            ps.setDate(3, d.getNgayTra());
            ps.setString(4, d.getTrangThai());
            ps.setBoolean(5, d.isDaThanhToan());
            ps.setDouble(6, d.getTongTien());
            ps.setInt(7, d.getMaDon());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
