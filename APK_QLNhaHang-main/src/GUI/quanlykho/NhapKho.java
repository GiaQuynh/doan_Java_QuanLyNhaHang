/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.quanlykho;

import DAO.ChitietPhieuNhapDAO;
import DAO.KhoDAO;
import DAO.NguyenLieuDAO;
import DAO.PhieuNhapDAO;
import static DAO.SharedPreferences.getUser;
import POJO.ChiTietPhieuNhap_item;
import POJO.ChitietPhieuNhapPOJO;
import POJO.KhoPOJO;
import POJO.NguyenLieu;
import POJO.NhaCungCapNguyenLieu;
import POJO.Order_Detail;
import POJO.PhieuNhapPOJO;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhapKho extends javax.swing.JFrame {

    /**
     * Creates new form NhapKho
     */
    DefaultTableModel tbm = new DefaultTableModel();
    DefaultTableModel tbm_chitietphieunhap = new DefaultTableModel();
    DefaultTableModel tbm_danhsachphieunhap = new DefaultTableModel();

    ArrayList<ChiTietPhieuNhap_item> listdanhsach = new ArrayList<>();

    public NhapKho() {
        initComponents();
        String[] tieude = new String[]{"ID Nguyên Liệu", "Tên Nguyên Liệu", "Đơn Vị Tính"};
        tbm.setColumnIdentifiers(tieude);
        loadNguyenLieu();
        LoadNhaCungCap();
        Disable();

        txtSLNhap.setText("0");
        txtDonGia.setText("0");
        txtNguoiThucHien.setText(getUser());

        String[] tieudechitiet = new String[]{"IDNL", "Tên NL", "Số lượng", "Đơn giá", "Đơn vị", "Thành tiền"};
        tbm_chitietphieunhap.setColumnIdentifiers(tieudechitiet);
        tableChiTietPhieu.setRowHeight(30);

        String[] tieudedanhsach = new String[]{"ID", "Tên phiếu", "Tên NCC", "Tổng tiền", "Ngày nhập"};
        tbm_danhsachphieunhap.setColumnIdentifiers(tieudedanhsach);
        tableDanhSachPhieu.setRowHeight(30);
        LoadDanhSachPhieu();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        tbTTNguyenLieu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbTTNguyenLieu.getSelectedRow();
                if (selectedRow != -1) {
                    txtIDnguyenlieu.setText(tbm.getValueAt(selectedRow, 0).toString());
                    txtTenNguyenLieu.setText(tbm.getValueAt(selectedRow, 1).toString());
                    txtDonViTinh.setText(tbm.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        addDocumentListener(txtDonGia);
        addDocumentListener(txtSLNhap);

    }

    private void calculateThanhTien() {
        try {
            float dongia = Float.parseFloat(txtDonGia.getText());
            int soluong = Integer.parseInt(txtSLNhap.getText());
            float thanhtien = dongia * soluong;
            txtThanhTien.setText(String.valueOf(thanhtien));
        } catch (NumberFormatException e) {
            txtThanhTien.setText("0");
        }
    }

    private void LoadNhaCungCap() {
        PhieuNhapDAO pn = new PhieuNhapDAO();
        ArrayList<NhaCungCapNguyenLieu> loadCombo = new ArrayList<>();
        loadCombo = pn.GetDanhSach();
        cbNhaCungCap.removeAllItems();
        for (NhaCungCapNguyenLieu ncc : loadCombo) {
            cbNhaCungCap.addItem(ncc.getTenNCC());
        }
    }

    private void addDocumentListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                calculateThanhTien();
            }

            public void removeUpdate(DocumentEvent e) {
                calculateThanhTien();
            }

            public void changedUpdate(DocumentEvent e) {
                calculateThanhTien();
            }
        });
    }

    void Disable() {
        txtIDnguyenlieu.enable(false);
        txtTenNguyenLieu.enable(false);
        txtDonViTinh.enable(false);
        txtThanhTien.enable(false);
        txtMaPhieu.enable(false);
        txtTongTien.enable(false);
        tableChiTietPhieu.setDefaultEditor(Object.class, null);
    }

    public void loadNguyenLieu() {
        tbm.setRowCount(0);
        ArrayList<NguyenLieu> dsNL = NguyenLieuDAO.getNguyenLieuData();
        for (NguyenLieu nl : dsNL) {
            Object[] row = new Object[]{nl.getId(), nl.getTennguyenlieu(), nl.getDonvi()};
            tbm.addRow(row);
        }
        tbTTNguyenLieu.setModel(tbm);
    }

     public void AddTableChiTietNhapKho() {
        tbm_chitietphieunhap.setRowCount(0);

        for (ChiTietPhieuNhap_item item : listdanhsach) {

            Object[] rowData = {item.getIdNguyenLieu(), item.getTenNguyenLieu(), item.getSoLuongNhap(), item.getDonGia(), item.getDonvitinh(), item.getThanhTien()};
            tbm_chitietphieunhap.addRow(rowData);

        }
        tableChiTietPhieu.setModel(tbm_chitietphieunhap);
    }

    public void LoadDanhSachPhieu() {
        tbm_danhsachphieunhap.setRowCount(0);
        ArrayList<PhieuNhapPOJO> dsPhieuNhap = PhieuNhapDAO.getPhieuNhap();
        for (PhieuNhapPOJO item : dsPhieuNhap) {

            Object[] rowData = {item.getIdphieunhap(), item.getTenPhieu(), item.getTenNhaCungCap(), item.getTongTienNhap(), item.getNgayNhapNguyenLieu()};
            tbm_danhsachphieunhap.addRow(rowData);

        }
        tableDanhSachPhieu.setModel(tbm_danhsachphieunhap);
    }

    private void updateTotalCost() {
        float totalCost = 0;
        for (ChiTietPhieuNhap_item item : listdanhsach) {
            totalCost += item.getThanhTien();
        }
        txtTongTien.setText(String.valueOf(totalCost));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTTNguyenLieu = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        txtMaPhieu = new javax.swing.JTextField();
        txtNguoiThucHien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenPhieuNhap = new javax.swing.JTextField();
        txtNgayNhap = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnThemPhieuNhap = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbNhaCungCap = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        txtIDnguyenlieu = new javax.swing.JTextField();
        txtTenNguyenLieu = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSLNhap = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnThemCTPN = new javax.swing.JButton();
        btnChinhSuaCTPN = new javax.swing.JButton();
        btnLuutam = new javax.swing.JButton();
        btnXoatam = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtDonViTinh = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChiTietPhieu = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDanhSachPhieu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Phiếu nhập Nguyên Liệu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nguyên liệu ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tbTTNguyenLieu.setBackground(new java.awt.Color(55, 80, 118));
        tbTTNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        tbTTNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTTNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTTNguyenLieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTTNguyenLieu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin phiếu nhập"));

        txtMaPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuActionPerformed(evt);
            }
        });

        txtNguoiThucHien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNguoiThucHienActionPerformed(evt);
            }
        });

        jLabel5.setText("Người thực hiện");

        jLabel7.setText("Mã phiếu");

        jLabel8.setText("Tổng tiền ");

        jLabel9.setText("Tên phiếu");

        txtTenPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPhieuNhapActionPerformed(evt);
            }
        });

        txtNgayNhap.setDateFormatString("d MMM, yyyy");

        jLabel13.setText("Ngày nhập");

        btnThemPhieuNhap.setBackground(new java.awt.Color(51, 204, 255));
        btnThemPhieuNhap.setText("Add");
        btnThemPhieuNhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnThemPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPhieuNhapActionPerformed(evt);
            }
        });

        jLabel10.setText("Nhà cung cấp");

        cbNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnThemPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 8, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cbNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNguoiThucHien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguoiThucHien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cbNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết phiếu nhập"));

        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });
        txtThanhTien.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtThanhTienPropertyChange(evt);
            }
        });

        jLabel3.setText("Số lượng nhập");

        jLabel6.setText("Đơn giá");

        txtSLNhap.setPreferredSize(new java.awt.Dimension(60, 30));

        jLabel4.setText("ID nguyên liệu");

        jLabel11.setText("Tên nguyên liệu");

        jLabel12.setText("Thành tiền");

        jPanel8.setBackground(new java.awt.Color(0, 0, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemCTPN.setBackground(new java.awt.Color(51, 204, 255));
        btnThemCTPN.setText("Thêm CTPN");
        btnThemCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTPNActionPerformed(evt);
            }
        });

        btnChinhSuaCTPN.setBackground(new java.awt.Color(51, 204, 255));
        btnChinhSuaCTPN.setText("Cập nhật bảng");
        btnChinhSuaCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaCTPNActionPerformed(evt);
            }
        });

        btnLuutam.setBackground(new java.awt.Color(51, 204, 255));
        btnLuutam.setText("Mở cập nhật");
        btnLuutam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuutamActionPerformed(evt);
            }
        });

        btnXoatam.setBackground(new java.awt.Color(51, 204, 255));
        btnXoatam.setText("Xóa");
        btnXoatam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoatamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChinhSuaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLuutam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoatam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChinhSuaCTPN, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(btnLuutam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemCTPN, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnXoatam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel15.setText("Đơn vị tính");

        txtDonViTinh.setPreferredSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSLNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDnguyenlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDonGia))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 155, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtIDnguyenlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSLNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(0, 41, Short.MAX_VALUE))
        );

        tableChiTietPhieu.setBackground(new java.awt.Color(55, 80, 118));
        tableChiTietPhieu.setForeground(new java.awt.Color(255, 255, 255));
        tableChiTietPhieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tableChiTietPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableChiTietPhieuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableChiTietPhieu);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tableDanhSachPhieu.setBackground(new java.awt.Color(55, 80, 118));
        tableDanhSachPhieu.setForeground(new java.awt.Color(255, 255, 255));
        tableDanhSachPhieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDanhSachPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDanhSachPhieuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableDanhSachPhieu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbTTNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTTNguyenLieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTTNguyenLieuMouseClicked

    private void txtMaPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhieuActionPerformed

    private void txtNguoiThucHienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNguoiThucHienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNguoiThucHienActionPerformed

    private void txtTenPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenPhieuNhapActionPerformed

    private void btnThemCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTPNActionPerformed

        int selectedRow = tbTTNguyenLieu.getSelectedRow();
        if (selectedRow != -1) {
            if (txtDonGia.getText().isEmpty() || txtSLNhap.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int idnguyenlieu = Integer.parseInt(txtIDnguyenlieu.getText());
                String tennguyenlieu = txtTenNguyenLieu.getText();
                float thanhtien = Float.parseFloat(txtThanhTien.getText());
                int soluong = Integer.parseInt(txtSLNhap.getText());
                float dongia = Float.parseFloat(txtDonGia.getText());
                String donvi = txtDonViTinh.getText();

                ChiTietPhieuNhap_item item = new ChiTietPhieuNhap_item();
                item.setIdNguyenLieu(idnguyenlieu);
                item.setTenNguyenLieu(tennguyenlieu);
                item.setSoLuongNhap(soluong);
                item.setDonGia(dongia);
                item.setDonvitinh(donvi);
                item.setThanhTien(thanhtien);

                boolean foundDuplicate = false;
                for (ChiTietPhieuNhap_item item1 : listdanhsach) {
                    if (item1.getIdNguyenLieu() == idnguyenlieu) {
                        item1.setSoLuongNhap(item1.getSoLuongNhap() + soluong);
                        item1.setThanhTien(item1.getThanhTien() + thanhtien);
                        foundDuplicate = true;
                        break;
                    }
                }

                if (!foundDuplicate) {

                    listdanhsach.add(item);

                }
                updateTotalCost();
                AddTableChiTietNhapKho();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for Đơn Giá, Số Lượng.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn nguyên liệu !", "Thông báo !", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnThemCTPNActionPerformed

    private void btnChinhSuaCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaCTPNActionPerformed
        // TODO add your handling code here:

        listdanhsach.clear();
        for (int i = 0; i < tableChiTietPhieu.getRowCount(); i++) {
            Object idNguyenlieu = tableChiTietPhieu.getValueAt(i, 0);
            Object tenNguyenlieu = tableChiTietPhieu.getValueAt(i, 1);
            Object soluongChuoi = tableChiTietPhieu.getValueAt(i, 2);
            Object dongia = tableChiTietPhieu.getValueAt(i, 3);
            Object donvi = tableChiTietPhieu.getValueAt(i, 4);
            Object thanhtien = tableChiTietPhieu.getValueAt(i, 5);

            int id = Integer.parseInt(idNguyenlieu.toString());
            String ten = tenNguyenlieu.toString();
            String donvitinh = donvi.toString();
            float dongiatien = Float.parseFloat(dongia.toString());
            int soluong = Integer.parseInt(soluongChuoi.toString());
            float thanhtienT = dongiatien * soluong;

            ChiTietPhieuNhap_item newItem = new ChiTietPhieuNhap_item(id, ten, soluong, dongiatien, donvitinh, thanhtienT);
            listdanhsach.add(newItem);
        }
        updateTotalCost();
        AddTableChiTietNhapKho();
        JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật");


    }//GEN-LAST:event_btnChinhSuaCTPNActionPerformed

    private void btnLuutamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuutamActionPerformed
//        for (ChiTietPhieuNhap_item item1 : listdanhsach) {
//            System.out.println(item1.getTenNguyenLieu() + " - " + item1.getSoLuongNhap() + "\n");
//
//        }
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 30));
        DefaultCellEditor editor = new DefaultCellEditor(textField);

        // Mở lại khả năng chỉnh sửa trong bảng
        tableChiTietPhieu.setDefaultEditor(Object.class, editor);

//        if (checkEmptyCTPN()) {
//            if (Action.equals("ThemCTPN")) {
//
//                float dongia = Float.parseFloat(txtDonGia.getText());
//                int soluong = Integer.parseInt(txtSLNhap.getText());
//                int maCTPN = Integer.parseInt(txtIDPhieuNhap.getText());
//                int idNLieu = Integer.parseInt(txtIDNguyenLieu.getText());
//                // Tính toán thành tiền
//                float thanhTien = tinhThanhTien(soluong, dongia);
//                txtThanhTien.setText(Float.toString(thanhTien));
//                ChitietPhieuNhapPOJO phieuNhap = new ChitietPhieuNhapPOJO();
//                phieuNhap.setId(maCTPN);
//                phieuNhap.setIdNguyenLieu(idNLieu);
//                phieuNhap.setSoLuongNhap(soluong);
//                phieuNhap.setDonGia(dongia);
//                phieuNhap.setThanhTien(thanhTien);
//                // Thêm chi tiết phiếu nhập và kiểm tra thành công
//                boolean success = DAO.ChitietPhieuNhapDAO.themCTPhieuNhap(phieuNhap);
//                if (success) {
//                    JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    loadChitietPhieuNhap();
//                    // Tính tổng tiền dựa trên tổng thành tiền của tất cả các chi tiết phiếu nhập của cùng một phiếu nhập
//                    float tongTien = (tinhTongTienPhieuNhapCong(maCTPN));
//                    tongTien = Math.abs(tongTien);
//                    // Cập nhật tổng tiền vào giao diện
//                    txtTongTien.setText(Float.toString(tongTien));
//                    // Cập nhật tổng tiền vào đối tượng PhieuNhapPOJO và cơ sở dữ liệu
//                    PhieuNhapPOJO pn = new PhieuNhapPOJO(maCTPN, tongTien);
//                    pn.setId(maCTPN);
//                    pn.setTongTienNhap(tongTien);
//                    boolean sucupdate = DAO.PhieuNhapDAO.capNhatPhieuNhap_TongTien(pn);
//                    loadPhieuNhap();
//                    if (sucupdate) {
//                        JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                        loadPhieuNhap();
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Lưu thất bại ", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                    }
//                    loadPhieuNhap();
//                    loadChitietPhieuNhap();
//                    resetCTPhieuNhap();
//                } else {
//                    JOptionPane.showMessageDialog(this, "Thêm thất bại,  (ID mã phiếu hoặc ID nguyên liệu hoặc ID nguyên liệu) đã tồn tại trong phiếu nhập này!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                }
//
//            }
//
//            if (Action.equals("ChinhSuaCTPN")) {
//                int maCTPN = Integer.parseInt(txtIDPhieuNhap.getText());
//                float dongia = Float.parseFloat(txtDonGia.getText());
//                int soluong = Integer.parseInt(txtSLNhap.getText());
//                int idNLieu = Integer.parseInt(txtIDNguyenLieu.getText());
//                // Tính toán lại thành tiền cho chi tiết phiếu nhập
//                float thanhTien = tinhThanhTien(soluong, dongia);
//                txtThanhTien.setText(Float.toString(thanhTien));
//                ChitietPhieuNhapPOJO phieuNhap = new ChitietPhieuNhapPOJO(maCTPN, idNLieu, soluong, dongia, thanhTien);
//                boolean success = ChitietPhieuNhapDAO.capNhatCTPhieuNhap_ThanhTien_Dongia_Soluong(phieuNhap);
//
//                if (success) {
//                    // Cập nhật lại thành tiền cho chi tiết phiếu nhập thành công
//
//                    // Tính toán lại tổng tiền của phiếu nhập
//                    float tongTien = tinhTongTienPhieuNhapCong(maCTPN);
//                    tongTien = Math.abs(tongTien);
//                    // Cập nhật tổng tiền của phiếu nhập vào giao diện
//                    txtTongTien.setText(Float.toString(tongTien));
//                    // Cập nhật tổng tiền của phiếu nhập vào đối tượng PhieuNhapPOJO và cơ sở dữ liệu
//                    PhieuNhapPOJO pn = new PhieuNhapPOJO(maCTPN, tongTien);
//                    pn.setId(maCTPN);
//                    pn.setTongTienNhap(tongTien);
//                    boolean sucUpdatePhieuNhap = DAO.PhieuNhapDAO.capNhatPhieuNhap_TongTien(pn);
//
//                    if (sucUpdatePhieuNhap) {
//                        JOptionPane.showMessageDialog(this, "Update tổng tiền thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                        loadChitietPhieuNhap();
//                        resetCTPhieuNhap();
//                        setEnableCTPhieuNhap();
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Update tổng tiền thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "Update thành tiền thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                }
//
//            } else {
//                return;
//            }
//        }
    }//GEN-LAST:event_btnLuutamActionPerformed
    private void refreshTable() {
        tbm_chitietphieunhap.setRowCount(0); // Clear the table
        for (ChiTietPhieuNhap_item item : listdanhsach) {
            tbm_chitietphieunhap.addRow(new Object[]{item.getIdNguyenLieu(), item.getTenNguyenLieu(), item.getSoLuongNhap(), item.getDonGia(), item.getDonvitinh(), item.getThanhTien()});
        }
    }
    private void btnXoatamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoatamActionPerformed
        int selectedRow = tableChiTietPhieu.getSelectedRow();
        if (selectedRow != -1) {
            // Remove the item from the list
            int idnguyenlieu = (int) tbm_chitietphieunhap.getValueAt(selectedRow, 0);
            listdanhsach.removeIf(item -> item.getIdNguyenLieu() == idnguyenlieu);

            tbm_chitietphieunhap.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn nguyên liệu !", "Thông báo !", JOptionPane.WARNING_MESSAGE);
        }
//            JOptionPane.showMessageDialog(this, "Chọn dòng dữ liệu cần xóa");
//
//        } else {
//            int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_CANCEL_OPTION);
//            int maCTPN = Integer.parseInt(txtIDPhieuNhap.getText());
//            int maNL = Integer.parseInt(txtIDNguyenLieu.getText());
//            // Lấy thành tiền của chi tiết phiếu nhập cần xóa
//            float thanhTien = Float.parseFloat(txtThanhTien.getText());
//            if (option == JOptionPane.YES_OPTION) {
//                boolean kt = ChitietPhieuNhapDAO.xoaCTPhieuNhap(maCTPN, maNL);
//                {
//                    if (kt) {
//                        int maPN = Integer.parseInt(txtIDPhieuNhap.getText());
//                        float tongTien = tinhTongTienPhieuNhapCong(maCTPN);
//                        // Cập nhật tổng tiền vào giao diện
//                        txtTongTien.setText(Float.toString(tongTien));
//                        // Cập nhật tổng tiền vào đối tượng PhieuNhapPOJO và cơ sở dữ liệu
//                        PhieuNhapPOJO pn = new PhieuNhapPOJO(maPN, tongTien);
//                        pn.setId(maPN);
//                        pn.setTongTienNhap(tongTien);
//                        boolean sucupdate = DAO.PhieuNhapDAO.capNhatPhieuNhap_TongTien(pn);
//                        if (sucupdate) {
//                            JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                            loadPhieuNhap();
//                        } else {
//                            JOptionPane.showMessageDialog(this, "Cập nhật thất bại ", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                        }
//
//                        resetCTPhieuNhap();
//
//                        JOptionPane.showMessageDialog(this, "Xóa  thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Xóa thất bại ", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                    }
//
//                }
//                //            JOptionPane.showMessageDialog(this, "Xóa thành công");
//                loadChitietPhieuNhap();
//            }
//        }
    }//GEN-LAST:event_btnXoatamActionPerformed

    private void tableChiTietPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableChiTietPhieuMouseClicked
        // TODO add your handling code here:
//        int row = tblPhieuNhap.getSelectedRow();
//        txtMaPhieu.setText(String.valueOf(tblPhieuNhap.getValueAt(row, 0)));
//        txtTenPhieu1.setText((String) tblPhieuNhap.getValueAt(row, 1));
//        txtNguoiThucHien.setText(String.valueOf(tblPhieuNhap.getValueAt(row, 2)));
//        txtNgayNhap.setDate((Date) tblPhieuNhap.getValueAt(row, 3));
//        txtTongTien.setText(String.valueOf(tblPhieuNhap.getValueAt(row, 4)));
//        setEnablePhieuNhap();
//        btnLuuPhieuNhap.setEnabled(false);
        //        txtIDPhieuNhap.setText(String.valueOf(tblPhieuNhap.getValueAt(row, 0)));
    }//GEN-LAST:event_tableChiTietPhieuMouseClicked

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void txtThanhTienPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtThanhTienPropertyChange
//        try {
//            float dongia = Float.parseFloat(txtDonGia.getText());
//            int soluong = Integer.parseInt(txtSLNhap.getText());
//
//            float thanhtien = dongia * soluong;
//            txtThanhTien.setText(String.valueOf(thanhtien));
//        } catch (NumberFormatException e) {
//            // Handle the case where txtDonGia or txtSLNhap contains invalid numbers
//            JOptionPane.showMessageDialog(null, "Please enter valid numbers for Don Gia and So Luong.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        }

    }//GEN-LAST:event_txtThanhTienPropertyChange

    private void tableDanhSachPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDanhSachPhieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDanhSachPhieuMouseClicked

    private void btnThemPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPhieuNhapActionPerformed

        String tenphieunhap = txtTenPhieuNhap.getText();
        String nguoithuchien = txtNguoiThucHien.getText();

        Date selectedDate = txtNgayNhap.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày nhập phiếu.", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        String timeNhap = dateFormat.format(selectedDate);

        String tongtien = txtTongTien.getText();

        int idNhaCungCap = 0;
        PhieuNhapDAO pn = new PhieuNhapDAO();
        ArrayList<NhaCungCapNguyenLieu> loadCombo = pn.GetDanhSach();
        Object nhacungcap = cbNhaCungCap.getSelectedItem();
        String nhacc = (String) nhacungcap.toString();
        for (NhaCungCapNguyenLieu item : loadCombo) {
            if (item.getTenNCC().equals(nhacc)) {
                idNhaCungCap = item.getIdNhaCungCap();
                break;
            }
        }

        if (tenphieunhap.isEmpty() || nguoithuchien.isEmpty() || tongtien.isEmpty() || nhacc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ các trường.", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean insertPhieu = pn.insertPhieuNhap(tenphieunhap, nguoithuchien, selectedDate, tongtien, idNhaCungCap, nhacc);

        boolean kqchitiet = false;
        if (insertPhieu == true) {
            int idPhieuNhap = pn.GetIdPhieu();
            System.out.println("ID phiếu : " + idPhieuNhap);
            ChitietPhieuNhapDAO chitiet = new ChitietPhieuNhapDAO();
            for (ChiTietPhieuNhap_item item : listdanhsach) {

                ChitietPhieuNhapPOJO phieu = new ChitietPhieuNhapPOJO();
                phieu.setIdPhieuNhap(idPhieuNhap);
                phieu.setIdNguyenLieu(item.getIdNguyenLieu());
                phieu.setTenNguyenLieu(item.getTenNguyenLieu());
                phieu.setSoLuongNhap(item.getSoLuongNhap());
                phieu.setDonGia(item.getDonGia());
                phieu.setThanhTien(item.getThanhTien());

                boolean insertChiTiet = chitiet.insertChiTietPhieuNhap(phieu);
                if (insertChiTiet == true) {
                    kqchitiet = true;

                    int idNguyenLieu = item.getIdNguyenLieu();
                    boolean checkID = KhoDAO.KiemTraIdNguyenLieu(idNguyenLieu);
                    if (checkID == false) {
                        KhoPOJO kho = new KhoPOJO();
                        kho.setIdnguyenlieu(idNguyenLieu);
                        kho.setTen_nguyenlieu(item.getTenNguyenLieu());
                        kho.setSoluong(item.getSoLuongNhap());
                        kho.setDonvitinh(item.getDonvitinh());
                        boolean insertKho = KhoDAO.insertKho(kho);
                    } else {

                        float soluongTong = KhoDAO.GetSoLuongByIDNguyenLieu(idNguyenLieu);
                        boolean update = KhoDAO.updateKho(soluongTong + item.getSoLuongNhap(), idNguyenLieu);
                    }

                }
            }
            if (kqchitiet == true) {
                JOptionPane.showMessageDialog(this, "Thêm thành công !", "Thông báo !", JOptionPane.INFORMATION_MESSAGE);
                listdanhsach.clear();
                txtTenPhieuNhap.setText("");
                txtTongTien.setText("");
                txtNgayNhap.setDate(null);

                AddTableChiTietNhapKho();
                LoadDanhSachPhieu();
            }
        }

    }//GEN-LAST:event_btnThemPhieuNhapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhapKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhapKho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChinhSuaCTPN;
    private javax.swing.JButton btnLuutam;
    private javax.swing.JButton btnThemCTPN;
    private javax.swing.JButton btnThemPhieuNhap;
    private javax.swing.JButton btnXoatam;
    private javax.swing.JComboBox<String> cbNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tableChiTietPhieu;
    private javax.swing.JTable tableDanhSachPhieu;
    private javax.swing.JTable tbTTNguyenLieu;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonViTinh;
    private javax.swing.JTextField txtIDnguyenlieu;
    private javax.swing.JTextField txtMaPhieu;
    private com.toedter.calendar.JDateChooser txtNgayNhap;
    private javax.swing.JTextField txtNguoiThucHien;
    private javax.swing.JTextField txtSLNhap;
    private javax.swing.JTextField txtTenNguyenLieu;
    private javax.swing.JTextField txtTenPhieuNhap;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
