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
        String sql = "SELECT * FROM HoaDonGiat";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonGiat hd = new HoaDonGiat(
                        rs.getInt("maHoaDon"),
                        rs.getInt("maKhachHang"),
                        rs.getString("trangThai"),
                        rs.getDouble("tongTien")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm mới hóa đơn
    public int insert(HoaDonGiat hd) {
        String sql = "INSERT INTO HoaDonGiat(maKhachHang, trangThai, tongTien) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, hd.getMaKhachHang());
            ps.setString(2, hd.getTrangThai());
            ps.setDouble(3, hd.getTongTien());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // trả về id tự tăng
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Cập nhật hóa đơn
    public boolean update(HoaDonGiat hd) {
        String sql = "UPDATE HoaDonGiat SET maKhachHang=?, trangThai=?, tongTien=? WHERE maHoaDon=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hd.getMaKhachHang());
            ps.setString(2, hd.getTrangThai());
            ps.setDouble(3, hd.getTongTien());
            ps.setInt(4, hd.getMaHoaDon());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa hóa đơn
    public boolean delete(int id) {
        String sql = "DELETE FROM HoaDonGiat WHERE maHoaDon=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
