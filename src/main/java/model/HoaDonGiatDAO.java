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
public class HoaDonGiatDAO {

    // Lấy toàn bộ hóa đơn
    public List<HoaDonGiat> getAll() {
        List<HoaDonGiat> list = new ArrayList<>();
        String sql = "SELECT MaHoaDon, TenKhachHang, TrangThai, TongTien FROM HoaDonGiat ORDER BY MaHoaDon DESC";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonGiat hd = new HoaDonGiat(
                        rs.getInt("MaHoaDon"),
                        rs.getString("TenKhachHang"),
                        rs.getString("TrangThai"),
                        rs.getDouble("TongTien")
                );
                list.add(hd);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // Thêm hóa đơn mới
    public boolean insert(HoaDonGiat hd) {
        if (hd == null) {
            System.err.println("Hóa đơn null, không thể thêm");
            return false;
        }

        String sql = "INSERT INTO HoaDonGiat (TenKhachHang, TrangThai, TongTien) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, hd.getTenKhachHang());
            ps.setString(2, hd.getTrangThai());
            ps.setDouble(3, hd.getTongTien());

            int result = ps.executeUpdate();
            if (result > 0) {
                // Lấy ID vừa được tạo
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    hd.setMaHoaDon(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật hóa đơn
    public boolean update(HoaDonGiat hd) {
        if (hd == null || hd.getMaHoaDon() <= 0) {
            System.err.println("Dữ liệu hóa đơn không hợp lệ để cập nhật");
            return false;
        }

        String sql = "UPDATE HoaDonGiat SET TenKhachHang=?, TrangThai=?, TongTien=? WHERE MaHoaDon=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hd.getTenKhachHang());
            ps.setString(2, hd.getTrangThai());
            ps.setDouble(3, hd.getTongTien());
            ps.setInt(4, hd.getMaHoaDon());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Xóa hóa đơn
    public boolean delete(int maHoaDon) {
        if (maHoaDon <= 0) {
            System.err.println("Mã hóa đơn không hợp lệ: " + maHoaDon);
            return false;
        }

        String sql = "DELETE FROM HoaDonGiat WHERE MaHoaDon=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maHoaDon);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Lấy hóa đơn theo ID
    public HoaDonGiat getById(int maHoaDon) {
        if (maHoaDon <= 0) {
            return null;
        }

        String sql = "SELECT MaHoaDon, TenKhachHang, TrangThai, TongTien FROM HoaDonGiat WHERE MaHoaDon = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maHoaDon);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new HoaDonGiat(
                        rs.getInt("MaHoaDon"),
                        rs.getString("TenKhachHang"),
                        rs.getString("TrangThai"),
                        rs.getDouble("TongTien")
                );
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy hóa đơn theo ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Tìm kiếm hóa đơn
    public List<HoaDonGiat> search(String keyword) {
        List<HoaDonGiat> list = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAll();
        }

        String sql = "SELECT MaHoaDon, TenKhachHang, TrangThai, TongTien FROM HoaDonGiat "
                + "WHERE TenKhachHang LIKE ? OR TrangThai LIKE ? OR CAST(MaHoaDon AS NVARCHAR) LIKE ? "
                + "ORDER BY MaHoaDon DESC";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchPattern = "%" + keyword.trim() + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HoaDonGiat(
                        rs.getInt("MaHoaDon"),
                        rs.getString("TenKhachHang"),
                        rs.getString("TrangThai"),
                        rs.getDouble("TongTien")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
