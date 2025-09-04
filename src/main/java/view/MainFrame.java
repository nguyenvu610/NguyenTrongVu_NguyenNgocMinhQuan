/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Quản Lý Tiệm Giặt Ủi");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        // Quản lý Menu
        JMenu menuQuanLyMenu = new JMenu("Quản Lý Menu");
        JMenuItem tkKhanhHang = new JMenuItem("Quản Lý Khách Hàng");
        JMenuItem tkDichVu = new JMenuItem("Quản Lý Dịch Vụ");

        // Đơn Giặt Ủi
        JMenu menuDonGiat = new JMenu("Đơn Giặt Ủi");
        JMenuItem qlDonGiat = new JMenuItem("Quản Lý Đơn Giặt");

        // Quản lý Đơn Hàng 
        JMenu menuQuanLyDonHang = new JMenu("Quản Lý Đơn Hàng");
        JMenuItem tkThanhToan = new JMenuItem("Hóa Đơn Giặt Ủi");
        JMenuItem tkTimKiem = new JMenuItem("Tìm Kiếm Đơn Hàng");

        // Thống kê - Báo cáo
        JMenu menuThongKe = new JMenu("Thống Kê - Báo Cáo");
        JMenuItem tkTongHop = new JMenuItem("Thống Kê Tổng Hợp");

        // Gắn item vào menu
        menuQuanLyMenu.add(tkKhanhHang);
        menuQuanLyMenu.add(tkDichVu);
        menuDonGiat.add(qlDonGiat);
        menuQuanLyDonHang.add(tkThanhToan);
        menuQuanLyDonHang.add(tkTimKiem);
        menuThongKe.add(tkTongHop);

        // Thêm tất cả menu vào MenuBar
        menuBar.add(menuQuanLyMenu);
        menuBar.add(menuDonGiat);
        menuBar.add(menuQuanLyDonHang);
        menuBar.add(menuThongKe);

        setJMenuBar(menuBar);

        // Panel chính dùng CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Thêm các panel chức năng (bạn mở comment nếu panel đã tạo)
        mainPanel.add(new QuanLyKhachHangPanel(), "QL_KHANHHANG");
        mainPanel.add(new QuanLyDichVuPanel(), "QL_DICHVU");
        mainPanel.add(new QuanLyDonGiatPanel(), "QL_DONGIATUI");
        mainPanel.add(new QuanLyHoaDonPanel(), "QL_HOADON");
        mainPanel.add(new TimKiemDonHangPanel(), "QL_TIMKIEM");    
        mainPanel.add(new ThongKeVaBaoCaoPanel(), "QL_THONGKE");

        add(mainPanel, BorderLayout.CENTER);

        // Xử lý sự kiện menu
        tkKhanhHang.addActionListener(e -> cardLayout.show(mainPanel, "QL_KHANHHANG"));
        tkDichVu.addActionListener(e -> cardLayout.show(mainPanel, "QL_DICHVU"));
        qlDonGiat.addActionListener(e -> cardLayout.show(mainPanel, "QL_DONGIATUI"));
        tkThanhToan.addActionListener(e -> cardLayout.show(mainPanel, "QL_HOADON"));
        tkTimKiem.addActionListener(e -> cardLayout.show(mainPanel, "QL_TIMKIEM"));
        tkTongHop.addActionListener(e -> cardLayout.show(mainPanel, "QL_THONGKE"));
    }

    // Hàm main để chạy chương trình
    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
