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

    // Lấy tất cả hóa đơn - sử dụng view v_HoaDonGiat có sẵn
    public List<HoaDonGiat> getAll() {
        List<HoaDonGiat> list = new ArrayList<>();
        String sql = "SELECT MaHoaDon, TenKhachHang, TrangThai, TongTien FROM v_HoaDonGiat ORDER BY MaHoaDon DESC";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
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

    // Thêm hóa đơn mới - tìm hoặc tạo khách hàng
    public boolean insert(HoaDonGiat hd) {
        if (hd == null) {
            System.err.println("Hóa đơn null, không thể thêm");
            return false;
        }
        
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // Bắt đầu transaction
            
            // Tìm hoặc tạo khách hàng
            int maKhachHang = findOrCreateKhachHang(con, hd.getTenKhachHang());
            if (maKhachHang <= 0) {
                con.rollback();
                return false;
            }
            
            // Thêm hóa đơn
            String sql = "INSERT INTO HoaDonGiat (MaKhachHang, TrangThai, TongTien) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, maKhachHang);
                ps.setString(2, hd.getTrangThai());
                ps.setDouble(3, hd.getTongTien());
                
                int result = ps.executeUpdate();
                if (result > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        hd.setMaHoaDon(generatedKeys.getInt(1));
                    }
                    con.commit();
                    return true;
                }
            }
            
            con.rollback();
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // Cập nhật hóa đơn
    public boolean update(HoaDonGiat hd) {
        if (hd == null || hd.getMaHoaDon() <= 0) {
            System.err.println("Dữ liệu hóa đơn không hợp lệ để cập nhật");
            return false;
        }
        
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            // Tìm hoặc tạo khách hàng
            int maKhachHang = findOrCreateKhachHang(con, hd.getTenKhachHang());
            if (maKhachHang <= 0) {
                con.rollback();
                return false;
            }
            
            String sql = "UPDATE HoaDonGiat SET MaKhachHang=?, TrangThai=?, TongTien=? WHERE MaHoaDon=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, maKhachHang);
                ps.setString(2, hd.getTrangThai());
                ps.setDouble(3, hd.getTongTien());
                ps.setInt(4, hd.getMaHoaDon());
                
                int result = ps.executeUpdate();
                if (result > 0) {
                    con.commit();
                    return true;
                }
            }
            
            con.rollback();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật hóa đơn: " + e.getMessage());
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, maHoaDon);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Tìm hoặc tạo khách hàng
    private int findOrCreateKhachHang(Connection con, String tenKhachHang) throws SQLException {
        if (tenKhachHang == null || tenKhachHang.trim().isEmpty()) {
            return 0;
        }
        
        tenKhachHang = tenKhachHang.trim();
        
        // Tìm khách hàng có sẵn
        String sqlFind = "SELECT MaKhachHang FROM KhachHang WHERE TenKhachHang = ?";
        try (PreparedStatement ps = con.prepareStatement(sqlFind)) {
            ps.setString(1, tenKhachHang);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("MaKhachHang");
            }
        }
        
        // Nếu không tìm thấy, tạo mới
        String sqlInsert = "INSERT INTO KhachHang (TenKhachHang, SoDienThoai, DiaChi) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tenKhachHang);
            ps.setString(2, ""); // SĐT trống
            ps.setString(3, ""); // Địa chỉ trống
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return 1; // Fallback
    }

    // Lấy hóa đơn theo ID
    public HoaDonGiat getById(int maHoaDon) {
        if (maHoaDon <= 0) return null;
        
        String sql = "SELECT MaHoaDon, TenKhachHang, TrangThai, TongTien FROM v_HoaDonGiat WHERE MaHoaDon = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
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
        
        String sql = "SELECT MaHoaDon, TenKhachHang, TrangThai, TongTien FROM v_HoaDonGiat " +
                    "WHERE TenKhachHang LIKE ? OR TrangThai LIKE ? OR CAST(MaHoaDon AS NVARCHAR) LIKE ? " +
                    "ORDER BY MaHoaDon DESC";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
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
