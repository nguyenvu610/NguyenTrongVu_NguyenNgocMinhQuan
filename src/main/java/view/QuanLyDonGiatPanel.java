/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import model.DonGiat;
import model.DonGiatDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tai Smart PC
 */
public class QuanLyDonGiatPanel extends javax.swing.JPanel {

    private DefaultTableModel model;
    private DonGiatDAO donGiatDAO = new DonGiatDAO();

    /**
     * Creates new form QuanLyDonGiatPanel
     */
    public QuanLyDonGiatPanel() {
        initComponents();// Khởi tạo giao diện ,vẽ from
        setupTable();//tạo cột ,cột,định dạng JTable
        setupComboBox();//đổ dự liệu cho combobox
        loadData();//lấy dữ liệu từ CSDL hiển lên bảng
        addTableClickEvent();//thêm sự kiện click vào bảng để xử lý
    }

    //tạo cột ,cột,định dạng JTable
    private void setupTable() {
        model = (DefaultTableModel) tblDonHang.getModel();
        model.setColumnIdentifiers(new String[]{
            "Mã Đơn", "Tên Khách Hàng", "Ngày Nhận", "Ngày Trả", "Trạng Thái"
        });
    }

    //đổ dự liệu cho combobox
    private void setupComboBox() {
        
        cboTrangThai.removeAllItems();
        cboTrangThai.addItem("Chờ xử lý");
        cboTrangThai.addItem("Đang giặt");
        cboTrangThai.addItem("Hoàn thành");
        cboTrangThai.addItem("Đã giao");
        cboTrangThai.setSelectedIndex(0);
    }

    //lấy dữ liệu từ CSDL hiển lên bảng
    private void loadData() {
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            for (DonGiat dg : donGiatDAO.getAll()) {
                model.addRow(new Object[]{
                    dg.getMaDon(),
                    dg.getTenKhachHang(),
                    dg.getNgayNhan() != null ? sdf.format(dg.getNgayNhan()) : "",
                    dg.getNgayTra() != null ? sdf.format(dg.getNgayTra()) : "",
                    dg.getTrangThai()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //thêm sự kiện click vào bảng để xử lý
    private void addTableClickEvent() {
        tblDonHang.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });
    }

    //dùng để lấy dự liệu từ dòng được chọn trong bảng và đổ lên vào các ô nhập liệu form
    private void fillFormFromTable() {
        int selectedRow = tblDonHang.getSelectedRow();
        if (selectedRow != -1) {
            try {
                txtKhachHang.setText(model.getValueAt(selectedRow, 1).toString());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String ngayNhanStr = model.getValueAt(selectedRow, 2).toString();
                String ngayTraStr = model.getValueAt(selectedRow, 3).toString();

                if (!ngayNhanStr.isEmpty()) {
                    Date ngayNhan = sdf.parse(ngayNhanStr);
                    txtNgayNhan.setDate(ngayNhan);
                } else {
                    txtNgayNhan.setDate(null);
                }

                if (!ngayTraStr.isEmpty()) {
                    Date ngayTra = sdf.parse(ngayTraStr);
                    txtNgayTra.setDate(ngayTra);
                } else {
                    txtNgayTra.setDate(null);
                }

                cboTrangThai.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị dữ liệu: " + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    //kiểm tra dữ liệu người dùng nhập vào trước khi lưu hoặc xử lý
    private boolean validateInput() {
        if (txtKhachHang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng!");
            txtKhachHang.requestFocus();
            return false;
        }

        if (txtNgayNhan.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày nhận!");
            txtNgayNhan.requestFocus();
            return false;
        }

        // Kiểm tra ngày trả phải sau ngày nhận
        if (txtNgayTra.getDate() != null && txtNgayNhan.getDate() != null) {
            if (txtNgayTra.getDate().before(txtNgayNhan.getDate())) {
                JOptionPane.showMessageDialog(this, "Ngày trả phải sau ngày nhận!");
                txtNgayTra.requestFocus();
                return false;
            }
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtNgayNhan = new com.toedter.calendar.JDateChooser();
        txtNgayTra = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        btnThemDG = new javax.swing.JButton();
        btnXoaDG = new javax.swing.JButton();
        btnSuaDG = new javax.swing.JButton();
        btnLamMoiDG = new javax.swing.JButton();

        tblDonHang.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDonHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblDonHangFocusGained(evt);
            }
        });
        tblDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDonHang);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ ĐƠN GIẶT ỦI");

        jLabel2.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel2.setText("Khách Hàng");

        txtKhachHang.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel3.setText("Ngày Nhận");

        jLabel4.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ngày Trả");

        cboTrangThai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel6.setText("Trạng Thái");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addComponent(txtNgayNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKhachHang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnThemDG.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        btnThemDG.setText("Thêm");
        btnThemDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDGActionPerformed(evt);
            }
        });

        btnXoaDG.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        btnXoaDG.setText("Xóa");
        btnXoaDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDGActionPerformed(evt);
            }
        });

        btnSuaDG.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        btnSuaDG.setText("Sửa");
        btnSuaDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDGActionPerformed(evt);
            }
        });

        btnLamMoiDG.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        btnLamMoiDG.setText("Làm Mới");
        btnLamMoiDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSuaDG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemDG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiDG, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemDG, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiDG, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDGActionPerformed
        if (!validateInput()) {
            return;
        }

        try {
            String tenKH = txtKhachHang.getText().trim();
            Date ngayNhan = txtNgayNhan.getDate();
            Date ngayTra = txtNgayTra.getDate();
            String trangThai = cboTrangThai.getSelectedItem().toString();

            // Tạo đối tượng DonGiat với constructor java.util.Date
            DonGiat dg = new DonGiat(0, tenKH, ngayNhan, ngayTra, trangThai);

            if (donGiatDAO.insert(dg)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                lamMoiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemDGActionPerformed

    private void btnXoaDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDGActionPerformed
        int selectedRow = tblDonHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một đơn để xóa!");
            return;
        }

        int maDon = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa đơn giặt này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (donGiatDAO.delete(maDon)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            loadData();
            lamMoiForm();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }//GEN-LAST:event_btnXoaDGActionPerformed

    private void btnSuaDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDGActionPerformed
        int selectedRow = tblDonHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một đơn để sửa!",
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validateInput()) {
            return;
        }

        try {
            int maDon = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            String tenKH = txtKhachHang.getText().trim();
            Date ngayNhan = txtNgayNhan.getDate();
            Date ngayTra = txtNgayTra.getDate();
            String trangThai = cboTrangThai.getSelectedItem().toString();

            DonGiat dg = new DonGiat(maDon, tenKH, ngayNhan, ngayTra, trangThai);

            if (donGiatDAO.update(dg)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                lamMoiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaDGActionPerformed

    private void btnLamMoiDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDGActionPerformed
        lamMoiForm();
    }

    private void lamMoiForm() {
        txtKhachHang.setText("");
        txtNgayNhan.setDate(null);
        txtNgayTra.setDate(null);
        cboTrangThai.setSelectedIndex(0);
        tblDonHang.clearSelection();
    }//GEN-LAST:event_btnLamMoiDGActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void tblDonHangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblDonHangFocusGained

    }//GEN-LAST:event_tblDonHangFocusGained

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked
        int row = tblDonHang.getSelectedRow();
        if (row >= 0) {
            // Lấy tên khách hàng
            txtKhachHang.setText(tblDonHang.getValueAt(row, 1).toString());

            // Lấy ngày nhận
            String ngayNhanStr = tblDonHang.getValueAt(row, 2).toString();
            if (!ngayNhanStr.isEmpty()) {
                try {
                    Date ngayNhan = new SimpleDateFormat("dd/MM/yyyy").parse(ngayNhanStr);
                    txtNgayNhan.setDate(ngayNhan);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtNgayNhan.setDate(null);
            }

            // Lấy ngày trả
            String ngayTraStr = tblDonHang.getValueAt(row, 3).toString();
            if (!ngayTraStr.isEmpty()) {
                try {
                    Date ngayTra = new SimpleDateFormat("dd/MM/yyyy").parse(ngayTraStr);
                    txtNgayTra.setDate(ngayTra);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtNgayTra.setDate(null);
            }

            // Lấy trạng thái
            cboTrangThai.setSelectedItem(tblDonHang.getValueAt(row, 4).toString());
        }
    }//GEN-LAST:event_tblDonHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiDG;
    private javax.swing.JButton btnSuaDG;
    private javax.swing.JButton btnThemDG;
    private javax.swing.JButton btnXoaDG;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDonHang;
    private javax.swing.JTextField txtKhachHang;
    private com.toedter.calendar.JDateChooser txtNgayNhan;
    private com.toedter.calendar.JDateChooser txtNgayTra;
    // End of variables declaration//GEN-END:variables
}
