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
public class DichVuGiatDAO {

    public List<DichVuGiat> getAll() {
        List<DichVuGiat> list = new ArrayList<>();
        String sql = "SELECT MaDichVu, TenDichVu, GiaTien, DonViTinh FROM DichVuGiat";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new DichVuGiat(
                        rs.getInt("MaDichVu"),
                        rs.getString("TenDichVu"),
                        rs.getDouble("GiaTien"),
                        rs.getString("DonViTinh")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(DichVuGiat dv) {
        String sql = "INSERT INTO DichVuGiat(TenDichVu, GiaTien, DonViTinh) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dv.getTenDichVu());
            ps.setDouble(2, dv.getGiaTien());
            ps.setString(3, dv.getDonViTinh());
            int r = ps.executeUpdate();
            if (r > 0) {
                try (ResultSet gk = ps.getGeneratedKeys()) {
                    if (gk.next()) {
                        dv.setMaDichVu(gk.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(DichVuGiat dv) {
        String sql = "UPDATE DichVuGiat SET TenDichVu=?, GiaTien=?, DonViTinh=? WHERE MaDichVu=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dv.getTenDichVu());
            ps.setDouble(2, dv.getGiaTien());
            ps.setString(3, dv.getDonViTinh());
            ps.setInt(4, dv.getMaDichVu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int maDV) {
        String sql = "DELETE FROM DichVuGiat WHERE MaDichVu = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
