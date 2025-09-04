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
public class ChiTietDonGiatDAO {

    public boolean insert(ChiTietDonGiat ct) {
        String sql = "INSERT INTO ChiTietDonGiat(MaDon, MaDichVu, SoLuong, MoTa) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, ct.getMaDon());
            ps.setInt(2, ct.getMaDichVu());
            ps.setInt(3, ct.getSoLuong());
            ps.setString(4, ct.getMoTa());

            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        ct.setMaChiTiet(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ChiTietDonGiat> getByMaDon(int maDon) {
        List<ChiTietDonGiat> list = new ArrayList<>();
        String sql = "SELECT MaChiTiet, MaDon, MaDichVu, SoLuong, MoTa FROM ChiTietDonGiat WHERE MaDon = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDon);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ChiTietDonGiat(
                            rs.getInt("MaChiTiet"),
                            rs.getInt("MaDon"),
                            rs.getInt("MaDichVu"),
                            rs.getInt("SoLuong"),
                            rs.getString("MoTa")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteByMaDon(int maDon) {
        String sql = "DELETE FROM ChiTietDonGiat WHERE MaDon = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDon);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int maChiTiet) {
        String sql = "DELETE FROM ChiTietDonGiat WHERE MaChiTiet = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maChiTiet);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double tinhTongTien(int maDon) {
        String sql = "SELECT SUM(ct.SoLuong * dv.GiaTien) AS tongTien "
                + "FROM ChiTietDonGiat ct "
                + "JOIN DichVuGiat dv ON ct.MaDichVu = dv.MaDichVu "
                + "WHERE ct.MaDon = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDon);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("tongTien");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
