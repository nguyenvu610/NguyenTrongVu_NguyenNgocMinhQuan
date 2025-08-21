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
        setTitle("Quản Lý Thư Viện");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();

       // Menu Độc giả
        JMenu menuDocGia = new JMenu("Độc giả");
        JMenuItem qlDocGia = new JMenuItem("Quản lý độc giả");

       // Menu Sách
        JMenu menuSach = new JMenu("Sách");
        JMenuItem qlSach = new JMenuItem("Quản lý sách");

        // Menu Mượn trả
        JMenu menuMuonTra = new JMenu("Mượn trả");
        JMenuItem qlPhieuMuon = new JMenuItem("Quản lý phiếu mượn");

        // Menu Thống kê
        JMenu menuThongKe = new JMenu("Thống kê");
        JMenuItem tkSachDangMuon = new JMenuItem("Sách đang mượn");
        JMenuItem tkDocGiaNhieu = new JMenuItem("Độc giả mượn nhiều sách");
        JMenuItem tkSachQuaHan = new JMenuItem("Sách quá hạn");

       // Gắn item vào menu
        menuDocGia.add(qlDocGia);
        menuSach.add(qlSach);
        menuMuonTra.add(qlPhieuMuon);
        menuThongKe.add(tkSachDangMuon);
        menuThongKe.add(tkDocGiaNhieu);
        menuThongKe.add(tkSachQuaHan);

// Gắn menu vào menu bar
        menuBar.add(menuDocGia);
        menuBar.add(menuSach);
        menuBar.add(menuMuonTra);
        menuBar.add(menuThongKe);
        setJMenuBar(menuBar);
        
//        JToolBar toolBar = new JToolBar();
//        JButton btQL_DocGia = new JButton("Quản lý độc giả");
//        JButton btQL_Sach = new JButton("Quản lý sách");
//        toolBar.add(btQL_DocGia);
//        toolBar.add(btQL_Sach);
//        
//        add(toolBar, BorderLayout.NORTH);
        
        

      // Panel chính dùng CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

     // Thêm các panel chức năng
        mainPanel.add(new QuanLyDocGiaPanel(), "QL_DOCGIA");
        mainPanel.add(new QuanLySachPanel(), "QL_SACH");
//        mainPanel.add(new QuanLyPhieuMuonPanel(), "QL_PHIEUMUON");
//        mainPanel.add(new ThongKeSachDangMuonPanel(), "TK_DANGMUON");
//        mainPanel.add(new ThongKeDocGiaNhieuPanel(), "TK_DOCGIA");
//        mainPanel.add(new ThongKeSachQuaHanPanel(), "TK_QUAHAN");

        add(mainPanel,BorderLayout.CENTER);

       // Xử lý sự kiện menu
        qlDocGia.addActionListener(e -> cardLayout.show(mainPanel, "QL_DOCGIA"));
        qlSach.addActionListener(e -> cardLayout.show(mainPanel, "QL_SACH"));
        qlPhieuMuon.addActionListener(e -> cardLayout.show(mainPanel, "QL_PHIEUMUON"));
        tkSachDangMuon.addActionListener(e -> cardLayout.show(mainPanel, "TK_DANGMUON"));
        tkDocGiaNhieu.addActionListener(e -> cardLayout.show(mainPanel, "TK_DOCGIA"));
        tkSachQuaHan.addActionListener(e -> cardLayout.show(mainPanel, "TK_QUAHAN"));
        
        //// Xử lý sự kiện Toolbar
//        btQL_DocGia.addActionListener(e->cardLayout.show(mainPanel, "QL_DOCGIA"));
//        
//        btQL_Sach.addActionListener(e-> cardLayout.show(mainPanel, "QL_SACH"));
        
        //show Panel mac dinh
    //    cardLayout.show(mainPanel, "QL_SACH");    
    
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
