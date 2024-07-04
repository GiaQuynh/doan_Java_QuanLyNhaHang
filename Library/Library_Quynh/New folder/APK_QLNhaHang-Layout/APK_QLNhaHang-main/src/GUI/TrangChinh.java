/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.doanhthu.BaoCaoDoanhThu;
import GUI.nhanvien.LichLam;
import GUI.nhanvien.ThongTinNhanVien;
import GUI.nhanvien.TinhLuong;
import GUI.quanlykho.HoaDonNhapKho;
import GUI.quanlykho.NhapKho;
import GUI.taikhoan.Login;
import GUI.taikhoan.ProfileAccount;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class TrangChinh extends javax.swing.JFrame {

    /**
     * Creates new form TrangChinh
     */
    public TrangChinh() {
        initComponents();
        setTitle("Quản lý nhà hàng");
        ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\UIAPP\\src\\folder\\logo.png");
        Image img = icon.getImage();
        setIconImage(img);
        addThucDon();
        addBanAn();
        addThucUong();
        setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addBanAn() {
        JButton button1 = new JButton("Bàn 1");
        JButton button2 = new JButton("Bàn 2");
        panelBanAn.setLayout(new FlowLayout());
        // Thêm các checkbox vào panel
        panelBanAn.add(button1);
        panelBanAn.add(button2);
    }

    private void addThucDon() {
        panelThucDon.setLayout(new GridLayout(0, 5)); // GridLayout với 5 cột, số dòng sẽ tự điều chỉnh dựa vào số lượng components

        ImageIcon icon = new ImageIcon("C:\\Users\\ASUS\\Downloads\\APK_QLNhaHang-main\\APK_QLNhaHang-main\\src\\folder\\BanhCanh.jpg"); // Đường dẫn của hình ảnh
        Image scaledImage = icon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH); // Thiết lập kích thước mới
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        for (int i = 1; i <= 20; i++) {
            JCheckBox checkBox = new JCheckBox("<html>Món " + i + "<br>(250.000đ)</html>", scaledIcon);
            checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);
            panelThucDon.add(checkBox);
        }
        panelThucDon.setBackground(Color.WHITE);
        // Thêm padding vào panel
        int padding = 15;
        panelThucDon.setBorder(new EmptyBorder(padding - padding, padding + 5, padding, padding));
    }

    private void addThucUong() {
        panelThucUong.setLayout(new GridLayout(0, 5)); // GridLayout với 5 cột, số dòng sẽ tự điều chỉnh dựa vào số lượng components

        ImageIcon icon = new ImageIcon("C:\\Users\\ASUS\\Downloads\\APK_QLNhaHang-main\\APK_QLNhaHang-main\\src\\folder\\BanhCanh.jpg"); // Đường dẫn của hình ảnh
        Image scaledImage = icon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH); // Thiết lập kích thước mới
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        for (int i = 1; i <= 20; i++) {
            JCheckBox checkBox = new JCheckBox("<html>Đồ uống " + i + "<br>(250.000đ)</html>", scaledIcon);
            checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);
            panelThucUong.add(checkBox);
        }
        panelThucUong.setBackground(Color.WHITE);
        // Thêm padding vào panel
        int padding = 15;
        panelThucUong.setBorder(new EmptyBorder(padding - padding, padding + 5, padding, padding));
    }
//    // Tạo một JLabel để hiển thị thông tin các checkbox đã chọn
//    JLabel selectedCheckBoxLabel = new JLabel("CheckBox đã chọn: ");
//
//    // Tạo ActionListener cho từng checkbox
//    ActionListener actionListener = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            StringBuilder selectedCheckBoxes = new StringBuilder();
//            if (checkBox1.isSelected()) {
//                selectedCheckBoxes.append(checkBox1.getText()).append(", ");
//            }
//            if (checkBox2.isSelected()) {
//                selectedCheckBoxes.append(checkBox2.getText()).append(", ");
//            }
//            String selectedText = selectedCheckBoxes.toString();
//            // Loại bỏ dấu phẩy và khoảng trắng cuối cùng nếu có
//            if (!selectedText.isEmpty()) {
//                selectedText = selectedText.substring(0, selectedText.length() - 2);
//            }
//            selectedCheckBoxLabel.setText("CheckBox đã chọn: " + selectedText);
//        }
//    };
//
//    // Đăng ký ActionListener cho các checkbox
//    checkBox1.addActionListener(actionListener);
//    checkBox2.addActionListener(actionListener);
    // Thêm JLabel vào panel
//    panelThucDon.add(selectedCheckBoxLabel);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLeft = new javax.swing.JPanel();
        panelLeft_Top = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelLeft_Center = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        panelRight = new javax.swing.JPanel();
        panelTab = new javax.swing.JTabbedPane();
        panelThucDon = new javax.swing.JPanel();
        panelThucUong = new javax.swing.JPanel();
        panelBanAn = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuTrangchu = new javax.swing.JMenu();
        menuKetca = new javax.swing.JMenuItem();
        menuProfile = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenuItem();
        menuThucdon = new javax.swing.JMenu();
        menuLichlam = new javax.swing.JMenu();
        menuLuongNhanVien = new javax.swing.JMenu();
        menuNhanvien = new javax.swing.JMenu();
        menuDoanhthu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuKho = new javax.swing.JMenu();
        menuNhapKho = new javax.swing.JMenuItem();
        menuKiemKho = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(44, 170, 110));
        jLabel3.setText("Nhà Hàng Một Không Hai");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setText("Bàn: ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setText("Ngày vào: ");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setText("1");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setText("1");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setText("Tiền thanh bill: ");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setText("1");

        javax.swing.GroupLayout panelLeft_TopLayout = new javax.swing.GroupLayout(panelLeft_Top);
        panelLeft_Top.setLayout(panelLeft_TopLayout);
        panelLeft_TopLayout.setHorizontalGroup(
            panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeft_TopLayout.createSequentialGroup()
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLeft_TopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLeft_TopLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLeft_TopLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLeft_TopLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(80, 80, 80)
                                .addComponent(jLabel10))))
                    .addGroup(panelLeft_TopLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        panelLeft_TopLayout.setVerticalGroup(
            panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeft_TopLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Nhập SDT");

        jTextField4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField4.setText("0");

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("Áp dụng");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Tổng bill");

        jTextField6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField6.setText("0");
        jTextField6.setToolTipText("");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Giảm bill");

        jTextField7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField7.setText("0");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Tổng tiền trả");

        jTextField8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField8.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLeft_CenterLayout = new javax.swing.GroupLayout(panelLeft_Center);
        panelLeft_Center.setLayout(panelLeft_CenterLayout);
        panelLeft_CenterLayout.setHorizontalGroup(
            panelLeft_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLeft_CenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeft_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelLeft_CenterLayout.setVerticalGroup(
            panelLeft_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeft_CenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLeft_Top, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLeftLayout.createSequentialGroup()
                        .addComponent(panelLeft_Center, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLeft_Top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLeft_Center, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        panelTab.setBackground(new java.awt.Color(125, 213, 222));
        panelTab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255)));
        panelTab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelThucDonLayout = new javax.swing.GroupLayout(panelThucDon);
        panelThucDon.setLayout(panelThucDonLayout);
        panelThucDonLayout.setHorizontalGroup(
            panelThucDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 796, Short.MAX_VALUE)
        );
        panelThucDonLayout.setVerticalGroup(
            panelThucDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );

        panelTab.addTab("THỨC ĂN", panelThucDon);

        javax.swing.GroupLayout panelThucUongLayout = new javax.swing.GroupLayout(panelThucUong);
        panelThucUong.setLayout(panelThucUongLayout);
        panelThucUongLayout.setHorizontalGroup(
            panelThucUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 796, Short.MAX_VALUE)
        );
        panelThucUongLayout.setVerticalGroup(
            panelThucUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );

        panelTab.addTab("THỨC UỐNG", panelThucUong);

        javax.swing.GroupLayout panelBanAnLayout = new javax.swing.GroupLayout(panelBanAn);
        panelBanAn.setLayout(panelBanAnLayout);
        panelBanAnLayout.setHorizontalGroup(
            panelBanAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 796, Short.MAX_VALUE)
        );
        panelBanAnLayout.setVerticalGroup(
            panelBanAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );

        panelTab.addTab("BÀN ĂN", panelBanAn);

        javax.swing.GroupLayout panelRightLayout = new javax.swing.GroupLayout(panelRight);
        panelRight.setLayout(panelRightLayout);
        panelRightLayout.setHorizontalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTab)
        );
        panelRightLayout.setVerticalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTab)
        );

        menuBar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        menuTrangchu.setText("Trang chủ");

        menuKetca.setText("Kết ca");
        menuKetca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKetcaActionPerformed(evt);
            }
        });
        menuTrangchu.add(menuKetca);

        menuProfile.setText("Thông tin cá nhân");
        menuProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProfileActionPerformed(evt);
            }
        });
        menuTrangchu.add(menuProfile);

        menuLogout.setText("Đăng xuất");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        menuTrangchu.add(menuLogout);

        menuBar.add(menuTrangchu);

        menuThucdon.setText("Thực đơn");
        menuBar.add(menuThucdon);

        menuLichlam.setText("Lịch làm");
        menuLichlam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLichlamMouseClicked(evt);
            }
        });
        menuBar.add(menuLichlam);

        menuLuongNhanVien.setText("Lương");
        menuLuongNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLuongNhanVienMouseClicked(evt);
            }
        });
        menuBar.add(menuLuongNhanVien);

        menuNhanvien.setText("Nhân viên");
        menuNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuNhanvienMouseClicked(evt);
            }
        });
        menuBar.add(menuNhanvien);

        menuDoanhthu.setText("Doanh thu");

        jMenuItem1.setText("Báo cáo doanh thu món");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuDoanhthu.add(jMenuItem1);

        jMenuItem2.setText("Báo cáo sale");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuDoanhthu.add(jMenuItem2);

        jMenuItem3.setText("Báo cáo thành phần");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuDoanhthu.add(jMenuItem3);

        menuBar.add(menuDoanhthu);

        menuKho.setText("Kho");

        menuNhapKho.setText("Nhập kho");
        menuNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNhapKhoActionPerformed(evt);
            }
        });
        menuKho.add(menuNhapKho);

        menuKiemKho.setText("Kiểm kho");
        menuKiemKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKiemKhoActionPerformed(evt);
            }
        });
        menuKho.add(menuKiemKho);

        menuBar.add(menuKho);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuKiemKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKiemKhoActionPerformed
        HoaDonNhapKho bill = new HoaDonNhapKho();
        bill.setVisible(true);
    }//GEN-LAST:event_menuKiemKhoActionPerformed

    private void menuLichlamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLichlamMouseClicked
        LichLam lichlam = new LichLam();
        lichlam.setVisible(true);
    }//GEN-LAST:event_menuLichlamMouseClicked

    private void menuNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNhanvienMouseClicked
        ThongTinNhanVien nhanvien = new ThongTinNhanVien();
        nhanvien.setVisible(true);
    }//GEN-LAST:event_menuNhanvienMouseClicked

    private void menuLuongNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLuongNhanVienMouseClicked
        TinhLuong luong = new TinhLuong();
        luong.setVisible(true);
    }//GEN-LAST:event_menuLuongNhanVienMouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked

    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked

    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        BaoCaoDoanhThu sale = new BaoCaoDoanhThu();
        sale.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
;
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void menuNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNhapKhoActionPerformed
        NhapKho input = new NhapKho();
        input.setVisible(true);
    }//GEN-LAST:event_menuNhapKhoActionPerformed

    private void menuKetcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKetcaActionPerformed
       
    }//GEN-LAST:event_menuKetcaActionPerformed

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        int choi = JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choi == JOptionPane.YES_OPTION) {
            // close the current form
            this.dispose();

            // open the Login form
            Login.main(null);
        } else {
            setDefaultCloseOperation(0);
        }
    }//GEN-LAST:event_menuLogoutActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int choi = JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choi == JOptionPane.YES_OPTION) {
            // close the current form
            this.dispose();

            // open the Login form
            Login.main(null);
        } else {
            setDefaultCloseOperation(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void menuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProfileActionPerformed
        ProfileAccount pf = new ProfileAccount();
        pf.setVisible(true);
    }//GEN-LAST:event_menuProfileActionPerformed

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
            java.util.logging.Logger.getLogger(TrangChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDoanhthu;
    private javax.swing.JMenuItem menuKetca;
    private javax.swing.JMenu menuKho;
    private javax.swing.JMenuItem menuKiemKho;
    private javax.swing.JMenu menuLichlam;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenu menuLuongNhanVien;
    private javax.swing.JMenu menuNhanvien;
    private javax.swing.JMenuItem menuNhapKho;
    private javax.swing.JMenuItem menuProfile;
    private javax.swing.JMenu menuThucdon;
    private javax.swing.JMenu menuTrangchu;
    private javax.swing.JPanel panelBanAn;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelLeft_Center;
    private javax.swing.JPanel panelLeft_Top;
    private javax.swing.JPanel panelRight;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JPanel panelThucDon;
    private javax.swing.JPanel panelThucUong;
    // End of variables declaration//GEN-END:variables
}
