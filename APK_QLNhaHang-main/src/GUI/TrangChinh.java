/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.AccountDAO;
import DAO.Connect;
import DAO.Order_Load;
import DAO.Order_XuLy;

import GUI.nhanvien.LichLam;
import GUI.nhanvien.ThongTinNhanVien;
import GUI.nhanvien.TinhLuong;
import GUI.quanlykho.HoaDonNhapKho;

import GUI.taikhoan.Login;

import GUI.hoadon.QuanLyHoaDon;
import GUI.doanhthu.BaoCaoDoanhThu;

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
import static DAO.SharedPreferences.getPassword;
import static DAO.SharedPreferences.getUser;
import static DAO.SharedPreferences.clearCredentials;
import static DAO.SharedPreferences.getRole;
import GUI.quanlykho.KhoNL;
import GUI.quanlykho.NhapKho;
import GUI.taikhoan.SignUp;

import GUI.thanhtoan.model.ItemMonAn_report;
import GUI.thanhtoan.model.Parameter_ThanhToan;
import GUI.thanhtoan.model.ReportManager;
import GUI.thucdon.ThucDon;
import POJO.BanAn;
import POJO.DanhMucMonAn;
import POJO.MonAn;
import POJO.MonAnListModel;
import POJO.MonAn_Temp;
import POJO.NguyenLieu;
import POJO.NguyenLieu_MonAn;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import POJO.Order;
import POJO.Order_Detail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Admin
 */
public class TrangChinh extends javax.swing.JFrame {

    Connect connect = new Connect();

    private JButton selectedButton;
    private JButton ButtonOld;
    private int selectedTableId = -1;

//    String thumuc = "F:\\Code\\Java\\BaoCao\\Main\\APK_QLNhaHang-main\\src\\folder\\";
    String thumuc = "C:\\Users\\Quin Quin\\Desktop\\APK_QLNhaHang-main\\APK_QLNhaHang-main\\src\\folder\\";

    public int idBanAnTrangChu = 0;
    public int idBanAnTrangChu_OLD = -1;
    public ArrayList<Order> CheckTinhTrang = new ArrayList<>();
    private ArrayList<JCheckBox> selectedCheckOrder = new ArrayList<>(); //Dùng để thêm vào table 
    private ArrayList<MonAn_Temp> ArrayMonAn = new ArrayList<>(); //Song song với checkbox , dùng để gom dữ liệu đem đi order
    DefaultTableModel tableModel = new DefaultTableModel();

    /**
     * Creates new form TrangChinh
     */
    public TrangChinh() {
        initComponents();
        setTitle("Quản lý nhà hàng");
        ImageIcon icon = new ImageIcon(thumuc + "logo.png");
        Image img = icon.getImage();
        setIconImage(img);

        String[] tieuDe = {"ID", "Tên món", "Số lượng", "Giá tiền"};
        tableModel.setColumnIdentifiers(tieuDe);
        tableOrder.setRowHeight(40);
        tableOrder.setModel(tableModel);
        int columnIndex = 2; // chỉ số của cột bạn muốn thiết lập chiều rộng
        int preferredWidth = 20; // chiều rộng mong muốn
        tableOrder.getColumnModel().getColumn(columnIndex).setPreferredWidth(preferredWidth);
        tableOrder.getColumnModel().getColumn(0).setPreferredWidth(preferredWidth);

        addThucDon();
        addBanAn();
        addThucUong();
        setLocationRelativeTo(null);

        //lưu trữ đám mây dữ liệu
        String user = getUser();
        String pass = getPassword();
        getFullName(user, pass);
        // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        LoadMenu();
        KiemTraBan();

        String role = getRole();
        if (role.equalsIgnoreCase("admin")) {
            itemDangKy.setEnabled(true);
            itemKetCa.setEnabled(true);
            menuDoanhThu.setEnabled(true);
            menuHoaDon.setEnabled(true);
            menuKho.setEnabled(true);
            menuKiemKho.setEnabled(true);

            menuNhanvien.setEnabled(true);
            menuLuongNhanVien.setEnabled(true);
            menuHoaDon.setEnabled(true);
        } else {
//            btnAdd.setEnabled(false);
//            btnDeleteMon.setEnabled(false);
//            btnUpdate.setEnabled(false);
//            btnUpdateMonAn.setEnabled(false);
            itemDangKy.setEnabled(false);
            itemKetCa.setEnabled(false);
            menuDoanhThu.setEnabled(false);
            menuHoaDon.setEnabled(false);
            menuKho.setEnabled(false);
            menuKiemKho.setEnabled(false);

            menuNhanvien.setEnabled(false);
            menuLuongNhanVien.setEnabled(false);
            menuHoaDon.setEnabled(false);
//            menuKiemKho.setEnabled(false);
        }

    }

    void getFullName(String user, String pass) {
        AccountDAO account = new AccountDAO();
        ArrayList<String> fullNames = account.getName(user, pass);
        for (String fullName : fullNames) {
            txtNhanVien.setText(fullName);
        }
    }

    public void addBanAn() {
        panelBanAn.removeAll();
        panelBanAn.revalidate();
        panelBanAn.repaint();

        panelBanAn.setLayout(new GridBagLayout());
        Order_Load load = new Order_Load();
        ArrayList<BanAn> listban = load.getBanAn();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Thiết lập margin giữa các phần tử
        // Khai báo một Map để lưu trữ trạng thái của các button đã chọn
        Map<Integer, JButton> selectedButtonsMap = new HashMap<>();

        for (int i = 0; i < listban.size(); i++) {
            final BanAn ban = listban.get(i); // Đặt biến ban là final
            int idBan = ban.getIdban();
            String tenBan = ban.getTenban();
            String tinhTrang = ban.getTrangthai();

            JButton button = new JButton("<html>" + tenBan + "<br>Trạng thái : " + tinhTrang + "</html>");
            button.setPreferredSize(new Dimension(180, 80)); // Thiết lập kích thước cho nút
            button.setHorizontalAlignment(JButton.CENTER);

            // Thiết lập màu nền cho nút dựa trên tình trạng
            if (tinhTrang.equals("Có người")) {
                button.setBackground(Color.YELLOW); // Màu đỏ cho tình trạng có người

            } else {
                Color lightGreen = new Color(163, 240, 182); // RGB của màu xanh nhạt
                button.setBackground(lightGreen);

            }

            panelBanAn.add(button, gbc);

            gbc.gridx++;
            if (gbc.gridx >= 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
            Order_XuLy xuly2 = new Order_XuLy();
            // Thêm ActionListener cho button
            button.addActionListener(e -> {
                idBanAnTrangChu = idBan;
                //nếu đúng là cái table có dữ liệu và bàn cũ không có dữ liệu
                //table chắc chắn đúng
                //Kiểm tra bàn trống ?
//                boolean checkt = CheckTable();
//                boolean ktban = KiemTraBanTrong_Load(idBanAnTrangChu_OLD);
//                System.out.println(checkt);
//                System.out.println(ktban);

                if (CheckTable() == true && KiemTraBanTrong_Load(idBanAnTrangChu_OLD) == true) {

                    int result = JOptionPane.showConfirmDialog(this, "Bảng hiện có dữ liệu. Bạn có muốn tiếp tục?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {

                        JButton clickedButton = (JButton) e.getSource();
                        selectedButton = clickedButton;
                        idBanAnTrangChu_OLD = idBanAnTrangChu;
                        txtBan.setText(tenBan);

                        Timestamp getTime = ban.getNgayGioDat();
                        if (getTime != null) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String timeOder = dateFormat.format(getTime);
                            txtDateOrder.setText(timeOder);
                        } else {
                            txtDateOrder.setText("0");
                        }

                        Order_XuLy xuly = new Order_XuLy();
                        ArrayList<Order> listorder = xuly.getOrderByBan(idBanAnTrangChu);

                        if (listorder.isEmpty()) {
                            selectedCheckOrder.clear();
                            ArrayMonAn.clear();
                            txtBill.setText("0");
                            txtTongBill.setText("0");
                            txtTongTienPhaiTra.setText("0");
                            tableModel.setRowCount(0);
                            tableOrder.removeAll();
                        } else {

                            for (Order list : listorder) {
                                int idOrder = list.getOrder_id();
                                float giagiam = list.getGiamgia();
                                float tongbillGoc = list.getTongbill();
                                float tongtien = list.getThanhtien();
                                String trangthai = list.getTrangthai();

                                DecimalFormat formatter = new DecimalFormat("#,###");
                                String formattedGiamBill = formatter.format(giagiam);
                                txtGiamBill.setText(formattedGiamBill);

                                String formattedTongBillGoc = formatter.format(tongbillGoc);
                                txtBill.setText(formattedTongBillGoc);
                                txtTongBill.setText(formattedTongBillGoc);

                                String formattedTongTien = formatter.format(tongtien);
                                txtTongTienPhaiTra.setText(formattedTongTien);

                                ArrayList<Order_Detail> listOrderDetail = xuly.getOrderDetail(idOrder);
                                tableModel.setRowCount(0);
                                tableOrder.removeAll();
                                for (Order_Detail itemOrder : listOrderDetail) {
                                    Object[] rowData = {itemOrder.getIdMonan(), itemOrder.getTenMonan(), itemOrder.getSoLuong(), itemOrder.getDonGia()};
                                    tableModel.addRow(rowData);
                                }
                            }

                        }
                    } else {
                        return; // Không làm gì cả nếu người dùng không xác nhận
                    }
                } else {
                    JButton clickedButton = (JButton) e.getSource();
                    selectedButton = clickedButton;
                    idBanAnTrangChu_OLD = idBanAnTrangChu;
                    txtBan.setText(tenBan);

//                    Timestamp getTime = ban.getNgayGioDat();
//                    if (getTime != null) {
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                        String timeOder = dateFormat.format(getTime);
//
//                        txtDateOrder.setText(timeOder);
//                    } else {
//                        txtDateOrder.setText("0");
//                    }
                    Order_XuLy xuly = new Order_XuLy();
                    ArrayList<Order> listorder = xuly.getOrderByBan(idBanAnTrangChu);

                    if (listorder.isEmpty()) {
                        selectedCheckOrder.clear();
                        ArrayMonAn.clear();
                        txtBill.setText("0");
                        txtTongBill.setText("0");
                        txtTongTienPhaiTra.setText("0");
                        txtDateOrder.setText("0");
                        tableModel.setRowCount(0);
                        tableOrder.removeAll();
                    } else {

                        for (Order list : listorder) {
                            int idOrder = list.getOrder_id();
                            float giagiam = list.getGiamgia();
                            float tongbillGoc = list.getTongbill();
                            float tongtien = list.getThanhtien();
                            String trangthai = list.getTrangthai();

                            Timestamp getTime = list.getNgayGioDat();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String timeOder = dateFormat.format(getTime);
                            txtDateOrder.setText(timeOder);

                            DecimalFormat formatter = new DecimalFormat("#,###");
                            String formattedGiamBill = formatter.format(giagiam);
                            txtGiamBill.setText(formattedGiamBill);

                            String formattedTongBillGoc = formatter.format(tongbillGoc);
                            txtBill.setText(formattedTongBillGoc);
                            txtTongBill.setText(formattedTongBillGoc);

                            String formattedTongTien = formatter.format(tongtien);
                            txtTongTienPhaiTra.setText(formattedTongTien);

                            ArrayList<Order_Detail> listOrderDetail = xuly.getOrderDetail(idOrder);
                            tableModel.setRowCount(0);
                            tableOrder.removeAll();
                            for (Order_Detail itemOrder : listOrderDetail) {
                                Object[] rowData = {itemOrder.getIdMonan(), itemOrder.getTenMonan(), itemOrder.getSoLuong(), itemOrder.getDonGia()};
                                tableModel.addRow(rowData);
                            }
                        }

                    }
                }

            });

        }

        scrollBanAn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollBanAn.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    private void addThucDon() {
        panelThucDon.setLayout(new GridBagLayout());
        Order_Load load = new Order_Load();
        ArrayList<MonAn> listmon = load.getThucDon();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Thiết lập margin giữa các phần tử

        for (int i = 0; i < listmon.size(); i++) {

            MonAn mon = listmon.get(i);
            int idMon = mon.getId();
            String hinhanh = mon.getHinhanh();
            String tenmon = mon.getTenmon();
            String giaca = mon.getGiamon();
            ImageIcon icon = new ImageIcon(thumuc + hinhanh);
            Image scaledImage = icon.getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JCheckBox checkBox = new JCheckBox("<html>Món " + tenmon + "<br>(" + giaca + ")</html>", scaledIcon);
            checkBox.setPreferredSize(new Dimension(150, 100));
            checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);

            MonAn_Temp mon_tmp = new MonAn_Temp();

            mon_tmp.setId(idMon);
            mon_tmp.setTenmon(tenmon);
            mon_tmp.setSoluong(1);
            mon_tmp.setGiamon(giaca);

            panelThucDon.add(checkBox, gbc);

            gbc.gridx++;
            if (gbc.gridx >= 5) {
                gbc.gridx = 0;
                gbc.gridy++;
            }

            // Thêm ActionListener cho checkbox
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    System.out.println(idBanAnTrangChu);
                    if (idBanAnTrangChu == 0) {
                        // Hiển thị thông báo popup khi không chọn bàn
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi thêm món vào", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        checkBox.setSelected(false); // Bỏ chọn ô kiểm
                    } else {
                        if (!KiemTraBanTrong(idBanAnTrangChu)) {
                            // Kiểm tra xem ô kiểm đã tồn tại trong danh sách chưa
                            if (!isCheckBoxDuplicate(checkBox)) {
                                // Nếu chưa tồn tại, thêm ô kiểm vào danh sách và thêm món
                                selectedCheckOrder.add(checkBox);
                                ArrayMonAn.add(mon_tmp);
                                LoadTableOrder();
                                checkBox.setBackground(Color.GREEN);
                                UpdateGiaTienTXT();
                            } else {

                                // Nếu đã tồn tại, hiển thị thông báo đã có
                                JOptionPane.showMessageDialog(null, "Món đã được chọn", "Thông báo", JOptionPane.WARNING_MESSAGE);
                                checkBox.setSelected(false); // Bỏ chọn ô kiểm
                            }
                        }

                    }
                } else {
                    if (!KiemTraBanTrong(idBanAnTrangChu)) {
                        ArrayMonAn.remove(mon_tmp);
                        selectedCheckOrder.remove(checkBox);
                        LoadTableOrder();
                        checkBox.setBackground(null);
                        UpdateGiaTienTXT();
                    }
                }
            });

        }
        scrollThucDon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollThucDon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    private void LoadMenu() {
        Order_Load load = new Order_Load();
        ArrayList<DanhMucMonAn> listdanhmuc = load.getMenu();
        cbLoadMenu.removeAllItems();
        for (DanhMucMonAn nl : listdanhmuc) {
            cbLoadMenu.addItem(nl.getTendanhmuc());
        }
        cbLoadMenu.addItem("Tất cả");
    }

    private void addThucUong() {
        panelThucUong.setLayout(new GridBagLayout());
        Order_Load load = new Order_Load();
        ArrayList<MonAn> listmon = load.getThucUong();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        for (int i = 0; i < listmon.size(); i++) {
            MonAn mon = listmon.get(i);
            int idMon = mon.getId();
            String hinhanh = mon.getHinhanh();
            String tenmon = mon.getTenmon();
            String giaca = mon.getGiamon();
            ImageIcon icon = new ImageIcon(thumuc + hinhanh);
            Image scaledImage = icon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JCheckBox checkBox = new JCheckBox("<html>" + tenmon + "<br>(" + giaca + ")</html>", scaledIcon);
            checkBox.setPreferredSize(new Dimension(150, 100));
            checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);

            panelThucUong.add(checkBox, gbc);

            MonAn_Temp mon_tmp = new MonAn_Temp();

            mon_tmp.setId(idMon);
            mon_tmp.setTenmon(tenmon);
            mon_tmp.setSoluong(1);
            mon_tmp.setGiamon(giaca);

            gbc.gridx++;
            if (gbc.gridx >= 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }

            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    if (idBanAnTrangChu == 0) {
                        // Hiển thị thông báo popup khi không chọn bàn
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi thêm món vào", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        checkBox.setSelected(false); // Bỏ chọn ô kiểm
                    } else {
                        System.out.println(idBanAnTrangChu);
                        if (!KiemTraBanTrong(idBanAnTrangChu)) {

                            // Kiểm tra xem ô kiểm đã tồn tại trong danh sách chưa
                            if (!isCheckBoxDuplicate(checkBox)) {
                                // Nếu chưa tồn tại, thêm ô kiểm vào danh sách và thêm món
                                selectedCheckOrder.add(checkBox);
                                ArrayMonAn.add(mon_tmp);
                                LoadTableOrder();
                                checkBox.setBackground(Color.GREEN);
                                UpdateGiaTienTXT();
                            } else {
                                // Nếu đã tồn tại, hiển thị thông báo đã có
                                JOptionPane.showMessageDialog(null, "Món đã được chọn", "Thông báo", JOptionPane.WARNING_MESSAGE);
                                checkBox.setSelected(false); // Bỏ chọn ô kiểm

                            }
                        }

                    }
                } else {
                    if (!KiemTraBanTrong(idBanAnTrangChu)) {
                        ArrayMonAn.remove(mon_tmp);
                        selectedCheckOrder.remove(checkBox);
                        LoadTableOrder();
                        checkBox.setBackground(null);
                        UpdateGiaTienTXT();
                    }
                }
            });
        }
        scrollThucUong.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollThucUong.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    public void LoadTableOrder() {
        // Xóa sạch dữ liệu của tableModel
        tableModel.setRowCount(0);

        // Xóa sạch dữ liệu của danh sách danhsachorder
        tableOrder.removeAll();
        ArrayList<MonAn_Temp> danhsachorder = getArrayList();

//        MonAnListModel listModel = new MonAnListModel(danhsachorder);
        // Thêm dữ liệu từ danh sách món ăn vào DefaultTableModel
        for (MonAn_Temp monAn : danhsachorder) {
            Object[] row = {monAn.getId(), monAn.getTenmon(), monAn.getSoluong(), monAn.getGiamon()};
            tableModel.addRow(row);
        }
        // Gán renderer cho ô chứa panel
//      tableOrder.getColumnModel().getColumn(2).setCellRenderer(new PanelRenderer());
        tableOrder.getColumnModel().getColumn(2).setCellEditor(new PanelEditor());

        tableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 2) { // Check if quantity column is updated
                    int newValue = (int) tableModel.getValueAt(row, column);
                    danhsachorder.get(row).setSoluong(newValue);
                    UpdateGiaTienTXT();
                }
            }

        });

    }

    // Phương thức để lấy danh sách các checkbox đã chọn
    public ArrayList<JCheckBox> getSelectedCheckboxes() {
        return selectedCheckOrder;
    }

    public ArrayList<MonAn_Temp> getArrayList() {
        return ArrayMonAn;
    }

    public boolean KiemTraBanTrong(int idban) {
        Order_XuLy xuly1 = new Order_XuLy();
        boolean check = xuly1.KiemTraBanTrong(idban);
        return check;
    }

    public boolean KiemTraBanTrong_Load(int idban) {
        Order_XuLy xuly1 = new Order_XuLy();
        boolean check = xuly1.KiemTraBanTrongLoad(idban);
        return check;
    }

    public void KiemTraBan() {
        Order_XuLy xuly1 = new Order_XuLy();
        ArrayList<Order> check = xuly1.KiemTraBan();
        for (Order item : check) {
            CheckTinhTrang.add(item);
        }
    }

    private void refreshCheckBoxes() {
        // Duyệt qua danh sách tạm thời và bỏ chọn các ô kiểm
        for (JCheckBox checkBox : selectedCheckOrder) {
            checkBox.setSelected(false);
        }
        // Xóa danh sách tạm thời
        selectedCheckOrder.clear();
    }

    private boolean isCheckBoxDuplicate(JCheckBox checkBox) {
        for (JCheckBox existingCheckBox : selectedCheckOrder) {
            if (existingCheckBox.getText().equals(checkBox.getText())) {
                return true; // Đã tồn tại checkbox với nội dung tương tự
            }
        }
        return false; // Không tồn tại checkbox với nội dung tương tự
    }

    private boolean CheckTable() {
        int rowCount = tableModel.getRowCount();
        return rowCount > 0;
    }

    private void UpdateGiaTienTXT() {
        int rowCount = tableOrder.getRowCount();
        ArrayList<Order_Detail> listAddMonAn = new ArrayList<>();
        float tongbill = 0;

        // Kiểm tra xem bảng có dữ liệu không
        if (rowCount > 0) {
            for (int i = 0; i < rowCount; i++) {
                Order_Detail item = new Order_Detail();

                // Lấy dữ liệu từ bảng
                Object idMonan = tableOrder.getValueAt(i, 0);
                Object tenMonan = tableOrder.getValueAt(i, 1);
                Object soLuong = tableOrder.getValueAt(i, 2);
                Object dongia = tableOrder.getValueAt(i, 3);

                // Kiểm tra null trước khi sử dụng
                if (idMonan != null && tenMonan != null && soLuong != null && dongia != null) {
                    // Chuyển đổi và tính tổng giá tiền
                    try {
                        float soLuongFloat = Float.parseFloat(soLuong.toString());
                        float dongiaFloat = Float.parseFloat(dongia.toString());
                        float thanhtienItem = soLuongFloat * dongiaFloat;
                        tongbill += thanhtienItem;
                    } catch (NumberFormatException ex) {
                        // Xử lý ngoại lệ nếu không thể chuyển đổi
                        ex.printStackTrace(); // Hoặc hiển thị cảnh báo cho người dùng
                    }
                }
                listAddMonAn.add(item);
            }
        }

        // Cập nhật giá trị lên các trường văn bản
        txtBill.setText(String.format("%.1f", tongbill)); // Định dạng số với 2 số thập phân
        txtTongBill.setText(String.format("%.1f", tongbill));
        txtTongTienPhaiTra.setText(String.format("%.1f", tongbill));
    }

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
        txtBan = new javax.swing.JLabel();
        txtDateOrder = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBill = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNhanVien = new javax.swing.JLabel();
        panelLeft_Center = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtTongBill = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtGiamBill = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTongTienPhaiTra = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnOrderMon = new javax.swing.JButton();
        btnxuatbill = new javax.swing.JButton();
        btnTachBan = new javax.swing.JButton();
        btnchuyenban = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        panelRight = new javax.swing.JPanel();
        panelTab = new javax.swing.JTabbedPane();
        panelChuaBanAn = new javax.swing.JPanel();
        scrollBanAn = new javax.swing.JScrollPane();
        panelBanAn = new javax.swing.JPanel();
        panelChua = new javax.swing.JPanel();
        scrollThucDon = new javax.swing.JScrollPane();
        panelThucDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        cbLoadMenu = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        panelChuaThucUong = new javax.swing.JPanel();
        scrollThucUong = new javax.swing.JScrollPane();
        panelThucUong = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtThucUong = new javax.swing.JTextField();
        btnTimKiemThucUong = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuTrangchu = new javax.swing.JMenu();
        itemKetCa = new javax.swing.JMenuItem();
        itemDangKy = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenuItem();
        menuThucdon = new javax.swing.JMenu();
        menuLichlam = new javax.swing.JMenu();
        menuLuongNhanVien = new javax.swing.JMenu();
        menuNhanvien = new javax.swing.JMenu();
        menuKho = new javax.swing.JMenu();
        menuNhapKho = new javax.swing.JMenuItem();
        menuKiemKho = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuHoaDon = new javax.swing.JMenu();
        menuDoanhThu = new javax.swing.JMenu();

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

        txtBan.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txtBan.setText("1");

        txtDateOrder.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txtDateOrder.setText("1");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setText("Tiền thanh bill: ");

        txtBill.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txtBill.setText("1");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Nhân viên: ");

        txtNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNhanVien.setText("Trống");

        javax.swing.GroupLayout panelLeft_TopLayout = new javax.swing.GroupLayout(panelLeft_Top);
        panelLeft_Top.setLayout(panelLeft_TopLayout);
        panelLeft_TopLayout.setHorizontalGroup(
            panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeft_TopLayout.createSequentialGroup()
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLeft_TopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLeft_TopLayout.createSequentialGroup()
                                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(22, 22, 22)
                                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDateOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(txtBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelLeft_TopLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(80, 80, 80)
                                .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelLeft_TopLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelLeft_TopLayout.setVerticalGroup(
            panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeft_TopLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBan)
                    .addComponent(jLabel1)
                    .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDateOrder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLeft_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtBill))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Tổng bill");

        txtTongBill.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTongBill.setText("0");
        txtTongBill.setToolTipText("");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Giảm bill");

        txtGiamBill.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtGiamBill.setText("0");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Tổng tiền trả");

        txtTongTienPhaiTra.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTongTienPhaiTra.setText("0");

        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnOrderMon.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnOrderMon.setText("Đặt món");
        btnOrderMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderMonActionPerformed(evt);
            }
        });

        btnxuatbill.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnxuatbill.setText("Xuất Bill");
        btnxuatbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatbillActionPerformed(evt);
            }
        });

        btnTachBan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTachBan.setText("Tách bàn");
        btnTachBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTachBanActionPerformed(evt);
            }
        });

        btnchuyenban.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnchuyenban.setText("Chuyển bàn");
        btnchuyenban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchuyenbanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnchuyenban)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnxuatbill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOrderMon, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(86, 86, 86)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTongBill)
                                    .addComponent(txtGiamBill)
                                    .addComponent(txtTongTienPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                            .addComponent(jLabel17))
                        .addGap(28, 28, 28)
                        .addComponent(btnTachBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTongBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiamBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTachBan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnOrderMon)
                    .addComponent(btnxuatbill)
                    .addComponent(btnchuyenban))
                .addContainerGap())
        );

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableOrder);

        javax.swing.GroupLayout panelLeft_CenterLayout = new javax.swing.GroupLayout(panelLeft_Center);
        panelLeft_Center.setLayout(panelLeft_CenterLayout);
        panelLeft_CenterLayout.setHorizontalGroup(
            panelLeft_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeft_CenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeft_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelLeft_CenterLayout.setVerticalGroup(
            panelLeft_CenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeft_CenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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

        javax.swing.GroupLayout panelBanAnLayout = new javax.swing.GroupLayout(panelBanAn);
        panelBanAn.setLayout(panelBanAnLayout);
        panelBanAnLayout.setHorizontalGroup(
            panelBanAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        panelBanAnLayout.setVerticalGroup(
            panelBanAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 764, Short.MAX_VALUE)
        );

        scrollBanAn.setViewportView(panelBanAn);

        javax.swing.GroupLayout panelChuaBanAnLayout = new javax.swing.GroupLayout(panelChuaBanAn);
        panelChuaBanAn.setLayout(panelChuaBanAnLayout);
        panelChuaBanAnLayout.setHorizontalGroup(
            panelChuaBanAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuaBanAnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollBanAn, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        panelChuaBanAnLayout.setVerticalGroup(
            panelChuaBanAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuaBanAnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollBanAn)
                .addContainerGap())
        );

        panelTab.addTab("BÀN ĂN", panelChuaBanAn);

        javax.swing.GroupLayout panelThucDonLayout = new javax.swing.GroupLayout(panelThucDon);
        panelThucDon.setLayout(panelThucDonLayout);
        panelThucDonLayout.setHorizontalGroup(
            panelThucDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        panelThucDonLayout.setVerticalGroup(
            panelThucDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );

        scrollThucDon.setViewportView(panelThucDon);

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnTim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        cbLoadMenu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnLoc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(cbLoadMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLoadMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelChuaLayout = new javax.swing.GroupLayout(panelChua);
        panelChua.setLayout(panelChuaLayout);
        panelChuaLayout.setHorizontalGroup(
            panelChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChuaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollThucDon))
                .addContainerGap())
        );
        panelChuaLayout.setVerticalGroup(
            panelChuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTab.addTab("THỨC ĂN", panelChua);

        javax.swing.GroupLayout panelThucUongLayout = new javax.swing.GroupLayout(panelThucUong);
        panelThucUong.setLayout(panelThucUongLayout);
        panelThucUongLayout.setHorizontalGroup(
            panelThucUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        panelThucUongLayout.setVerticalGroup(
            panelThucUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );

        scrollThucUong.setViewportView(panelThucUong);

        txtThucUong.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnTimKiemThucUong.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTimKiemThucUong.setText("Tìm kiếm");
        btnTimKiemThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemThucUongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtThucUong, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemThucUong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThucUong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemThucUong))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelChuaThucUongLayout = new javax.swing.GroupLayout(panelChuaThucUong);
        panelChuaThucUong.setLayout(panelChuaThucUongLayout);
        panelChuaThucUongLayout.setHorizontalGroup(
            panelChuaThucUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuaThucUongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChuaThucUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollThucUong)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelChuaThucUongLayout.setVerticalGroup(
            panelChuaThucUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChuaThucUongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollThucUong, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTab.addTab("THỨC UỐNG", panelChuaThucUong);

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

        itemKetCa.setText("Kết ca");
        itemKetCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemKetCaActionPerformed(evt);
            }
        });
        menuTrangchu.add(itemKetCa);

        itemDangKy.setText("Đăng kí tài khoản");
        itemDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDangKyActionPerformed(evt);
            }
        });
        menuTrangchu.add(itemDangKy);

        menuLogout.setText("Đăng xuất");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        menuTrangchu.add(menuLogout);

        menuBar.add(menuTrangchu);

        menuThucdon.setText("Thực đơn");
        menuThucdon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuThucdonMouseClicked(evt);
            }
        });
        menuThucdon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuThucdonActionPerformed(evt);
            }
        });
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

        menuKho.setText("Kho");

        menuNhapKho.setText("Nhập kho");
        menuNhapKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuNhapKhoMouseClicked(evt);
            }
        });
        menuNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNhapKhoActionPerformed(evt);
            }
        });
        menuKho.add(menuNhapKho);

        menuKiemKho.setText("Kiểm kho");
        menuKiemKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuKiemKhoMouseClicked(evt);
            }
        });
        menuKiemKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKiemKhoActionPerformed(evt);
            }
        });
        menuKho.add(menuKiemKho);

        jMenuItem4.setText("Hóa đơn kho");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseClicked(evt);
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuKho.add(jMenuItem4);

        menuBar.add(menuKho);

        menuHoaDon.setText("Hóa đơn");
        menuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHoaDonMouseClicked(evt);
            }
        });
        menuBar.add(menuHoaDon);

        menuDoanhThu.setText("Doanh thu");
        menuDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDoanhThuMouseClicked(evt);
            }
        });
        menuBar.add(menuDoanhThu);

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
        KhoNL kho = new KhoNL();
        kho.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        kho.setVisible(true);
    }//GEN-LAST:event_menuKiemKhoActionPerformed

    private void menuLichlamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLichlamMouseClicked
        LichLam lichlam = new LichLam();
        lichlam.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lichlam.setVisible(true);
    }//GEN-LAST:event_menuLichlamMouseClicked

    private void menuNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNhanvienMouseClicked
        ThongTinNhanVien nhanvien = new ThongTinNhanVien();
        nhanvien.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nhanvien.setVisible(true);
    }//GEN-LAST:event_menuNhanvienMouseClicked

    private void menuLuongNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLuongNhanVienMouseClicked
        TinhLuong luong = new TinhLuong();
        luong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        luong.setVisible(true);
    }//GEN-LAST:event_menuLuongNhanVienMouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
//        DishReport baocaomon = new DishReport();
//        baocaomon.setVisible(true);
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked

    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked

    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void itemKetCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemKetCaActionPerformed
        KetCaDoanhThu ketca = new KetCaDoanhThu();
        ketca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ketca.setVisible(true);
    }//GEN-LAST:event_itemKetCaActionPerformed

    private void itemDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDangKyActionPerformed
        SignUp sale = new SignUp();
        sale.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sale.setVisible(true);
    }//GEN-LAST:event_itemDangKyActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
//        TheIngredientsReport baocao = new TheIngredientsReport();
//        baocao.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void menuNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNhapKhoActionPerformed
        NhapKho nhapkho = new NhapKho();
        nhapkho.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nhapkho.setVisible(true);
    }//GEN-LAST:event_menuNhapKhoActionPerformed

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        int choi = JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choi == JOptionPane.YES_OPTION) {
            // close the current form
            this.dispose();
            // open the Login form
            Login.main(null);
            clearCredentials();
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
            clearCredentials();
        } else {
            setDefaultCloseOperation(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void menuThucdonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuThucdonActionPerformed

    }//GEN-LAST:event_menuThucdonActionPerformed

    private void menuThucdonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuThucdonMouseClicked
        ThucDon order = new ThucDon();
        order.setVisible(true);
        order.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_menuThucdonMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String tenmontimkiem = txtTimKiem.getText();
        Order_Load timkiem = new Order_Load();
        ArrayList<MonAn> list = timkiem.TimKiemMonAn(tenmontimkiem);
        // Xóa các mục hiện tại trên giao diện
        panelThucDon.removeAll();
        // Hiển thị kết quả tìm kiếm mới
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Thiết lập margin giữa các phần tử
        for (int i = 0; i < list.size(); i++) {
            MonAn mon = list.get(i);
            int idMon = mon.getId();
            String hinhanh = mon.getHinhanh();
            String tenmon = mon.getTenmon();
            String giaca = mon.getGiamon();
            ImageIcon icon = new ImageIcon(thumuc + hinhanh);
            Image scaledImage = icon.getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JCheckBox checkBox = new JCheckBox("<html>Món " + tenmon + "<br>(" + giaca + ")</html>", scaledIcon);
            checkBox.setPreferredSize(new Dimension(150, 100));
            checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);
            panelThucDon.add(checkBox, gbc);

            MonAn_Temp mon_tmp = new MonAn_Temp();

            mon_tmp.setId(idMon);
            mon_tmp.setTenmon(tenmon);
            mon_tmp.setSoluong(1);
            mon_tmp.setGiamon(giaca);

            gbc.gridx++;
            if (gbc.gridx >= 5) {
                gbc.gridx = 0;
                gbc.gridy++;
            }

            // Thêm ActionListener cho checkbox
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    if (idBanAnTrangChu == 0) {
                        // Hiển thị thông báo popup khi không chọn bàn
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi thêm món vào", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        checkBox.setSelected(false); // Bỏ chọn ô kiểm
                    } else {
                        if (!KiemTraBanTrong(idBanAnTrangChu)) {
                            // Kiểm tra xem ô kiểm đã tồn tại trong danh sách chưa
                            if (!isCheckBoxDuplicate(checkBox)) {
                                // Nếu chưa tồn tại, thêm ô kiểm vào danh sách và thêm món
                                selectedCheckOrder.add(checkBox);
                                ArrayMonAn.add(mon_tmp);
                                LoadTableOrder();
                                checkBox.setBackground(Color.GREEN);
                                UpdateGiaTienTXT();
                            } else {

                                // Nếu đã tồn tại, hiển thị thông báo đã có
                                JOptionPane.showMessageDialog(null, "Món đã được chọn", "Thông báo", JOptionPane.WARNING_MESSAGE);
                                checkBox.setSelected(false); // Bỏ chọn ô kiểm
                            }
                        }

                    }
                } else {
                    if (!KiemTraBanTrong(idBanAnTrangChu)) {
                        ArrayMonAn.remove(mon_tmp);
                        selectedCheckOrder.remove(checkBox);
                        LoadTableOrder();
                        checkBox.setBackground(null);
                        UpdateGiaTienTXT();
                    }
                }
            });

        }
        // Cập nhật giao diện
        panelThucDon.revalidate();
        panelThucDon.repaint();
        // Scroll Bar
        scrollThucDon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollThucDon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        String tendanhmuc = (String) cbLoadMenu.getSelectedItem();
        if (tendanhmuc == "Tất cả") {
            panelThucDon.removeAll();
            addThucDon();

        } else {
            Order_Load timkiem = new Order_Load();
            ArrayList<DanhMucMonAn> list = timkiem.getMenu();
            DanhMucMonAn selectDanhMuc = null;
            for (DanhMucMonAn nl : list) {
                if (nl.getTendanhmuc().equals(tendanhmuc)) {
                    selectDanhMuc = nl;
                    break;
                }
            }
            int idDanhMuc = 1;
            if (selectDanhMuc != null) {
                idDanhMuc = selectDanhMuc.getId();
            }

            ArrayList<MonAn> listMonAn = timkiem.GetMonAnByidDanhMuc(idDanhMuc);

            // Xóa các mục hiện tại trên giao diện
            panelThucDon.removeAll();

            // Hiển thị kết quả tìm kiếm mới
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10); // Thiết lập margin giữa các phần tử

            for (int i = 0; i < listMonAn.size(); i++) {
                MonAn mon = listMonAn.get(i);
                int idMon = mon.getId();
                String hinhanh = mon.getHinhanh();
                String tenmon = mon.getTenmon();
                String giaca = mon.getGiamon();
                ImageIcon icon = new ImageIcon(thumuc + hinhanh);
                Image scaledImage = icon.getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JCheckBox checkBox = new JCheckBox("<html>Món " + tenmon + "<br>(" + giaca + ")</html>", scaledIcon);
                checkBox.setPreferredSize(new Dimension(150, 100));
                checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);
                panelThucDon.add(checkBox, gbc);

                MonAn_Temp mon_tmp = new MonAn_Temp();

                mon_tmp.setId(idMon);
                mon_tmp.setTenmon(tenmon);
                mon_tmp.setSoluong(1);
                mon_tmp.setGiamon(giaca);

                gbc.gridx++;
                if (gbc.gridx >= 5) {
                    gbc.gridx = 0;
                    gbc.gridy++;
                }

                // Thêm ActionListener cho checkbox
                checkBox.addActionListener(e -> {
                    if (checkBox.isSelected()) {
                        if (idBanAnTrangChu == 0) {
                            // Hiển thị thông báo popup khi không chọn bàn
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi thêm món vào", "Thông báo", JOptionPane.WARNING_MESSAGE);
                            checkBox.setSelected(false); // Bỏ chọn ô kiểm
                        } else {
                            if (!KiemTraBanTrong(idBanAnTrangChu)) {
                                // Kiểm tra xem ô kiểm đã tồn tại trong danh sách chưa
                                if (!isCheckBoxDuplicate(checkBox)) {
                                    // Nếu chưa tồn tại, thêm ô kiểm vào danh sách và thêm món
                                    selectedCheckOrder.add(checkBox);
                                    ArrayMonAn.add(mon_tmp);
                                    LoadTableOrder();
                                    checkBox.setBackground(Color.GREEN);
                                    UpdateGiaTienTXT();
                                } else {

                                    // Nếu đã tồn tại, hiển thị thông báo đã có
                                    JOptionPane.showMessageDialog(null, "Món đã được chọn", "Thông báo", JOptionPane.WARNING_MESSAGE);
                                    checkBox.setSelected(false); // Bỏ chọn ô kiểm
                                }
                            }

                        }
                    } else {
                        if (!KiemTraBanTrong(idBanAnTrangChu)) {
                            ArrayMonAn.remove(mon_tmp);
                            selectedCheckOrder.remove(checkBox);
                            LoadTableOrder();
                            checkBox.setBackground(null);
                            UpdateGiaTienTXT();
                        }
                    }
                });

            }
            // Cập nhật giao diện
            panelThucDon.revalidate();
            panelThucDon.repaint();
            // Scroll Bar
            scrollThucDon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollThucDon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        }

    }//GEN-LAST:event_btnLocActionPerformed

    private void btnTimKiemThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemThucUongActionPerformed
        String tenthucuong = txtThucUong.getText();
        Order_Load timkiem = new Order_Load();
        ArrayList<MonAn> listthucuong = timkiem.TimKiemThucUong(tenthucuong);

        panelThucUong.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        for (int i = 0; i < listthucuong.size(); i++) {
            MonAn mon = listthucuong.get(i);
            int idMon = mon.getId();
            String hinhanh = mon.getHinhanh();
            String tenmon = mon.getTenmon();
            String giaca = mon.getGiamon();
            ImageIcon icon = new ImageIcon(thumuc + hinhanh);
            Image scaledImage = icon.getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JCheckBox checkBox = new JCheckBox("<html>Món " + tenmon + "<br>(" + giaca + ")</html>", scaledIcon);
            checkBox.setPreferredSize(new Dimension(150, 100));
            checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);
            panelThucUong.add(checkBox, gbc);

            MonAn_Temp mon_tmp = new MonAn_Temp();

            mon_tmp.setId(idMon);
            mon_tmp.setTenmon(tenmon);
            mon_tmp.setSoluong(1);
            mon_tmp.setGiamon(giaca);

            gbc.gridx++;
            if (gbc.gridx >= 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    if (idBanAnTrangChu == 0) {

                        JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn trước khi thêm món vào", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        checkBox.setSelected(false); // Bỏ chọn ô kiểm
                    } else {
                        if (!KiemTraBanTrong(idBanAnTrangChu)) {
                            if (!isCheckBoxDuplicate(checkBox)) {
                                selectedCheckOrder.add(checkBox);
                                ArrayMonAn.add(mon_tmp);
                                LoadTableOrder();
                                checkBox.setBackground(Color.GREEN);
                                UpdateGiaTienTXT();
                            } else {
                                JOptionPane.showMessageDialog(null, "Món đã được chọn", "Thông báo", JOptionPane.WARNING_MESSAGE);
                                checkBox.setSelected(false);
                            }
                        }
                    }
                } else {
                    if (!KiemTraBanTrong(idBanAnTrangChu)) {
                        ArrayMonAn.remove(mon_tmp);
                        selectedCheckOrder.remove(checkBox);
                        LoadTableOrder();
                        checkBox.setBackground(null);
                        UpdateGiaTienTXT();
                    }
                }
            });

        }
        // Cập nhật giao diện
        panelThucUong.revalidate();
        panelThucUong.repaint();
        // Scroll Bar
        scrollThucUong.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollThucUong.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }//GEN-LAST:event_btnTimKiemThucUongActionPerformed
    public void XuatBill(int id) {
        try {
            Hashtable<String, Object> map = new Hashtable<>();
            JasperReport report = JasperCompileManager.compileReport("src/GUI/doanhthu/rpBill.jrxml");
            map.put("order_id", id);
            JasperPrint p = JasperFillManager.fillReport(report, map, connect.ketNoiCSDL());
            JasperViewer.viewReport(p, false);
            JasperExportManager.exportReportToPdfFile(p, "test.pdf");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getLatestOrderId() {
        int latestOrderId = 0;
        Connect provider = new Connect();
        provider.ketNoiCSDL();
        ResultSet resultSet = provider.executeQuery("SELECT MAX(order_id) AS latest_order_id FROM DonHang");

        try {
            // Lấy giá trị id_order mới nhất từ kết quả truy vấn
            if (resultSet.next()) {
                latestOrderId = resultSet.getInt("latest_order_id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                // Đóng tài nguyên liên quan
                resultSet.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return latestOrderId;
    }
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thanh toán?", "Xác nhận thanh toán", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            Order_XuLy xuly = new Order_XuLy();
            int idBan = idBanAnTrangChu;

            if (idBan == 0) {
                JOptionPane.showMessageDialog(this, "Bạn này không có người!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                int id_order = xuly.GetOrderIDByIdban(idBan);

                if (id_order != 0) {
                    boolean donhang = xuly.UpdateDonHang(id_order);
                    boolean ban = xuly.UpdateBan(idBan);
                    if (donhang && ban) {
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        Color lightGreen = new Color(163, 240, 182);
                        selectedButton.setBackground(lightGreen);
                        tableModel.setRowCount(0);

                        tableOrder.removeAll();
                        txtTongTienPhaiTra.setText("");
                        txtTongBill.setText("");
                        String tenNhanVien = getUser();
                        XuatHoaDon(id_order, tenNhanVien);
                        addBanAn();
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể thanh toán", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Đơn hàng bị lỗi!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

//        System.out.println("ID hóa đơn : " + id_order);

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnOrderMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderMonActionPerformed

        // Kiểm tra xem idBan có hợp lệ không
        if (idBanAnTrangChu == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn để đặt món.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return; // Dừng hàm nếu idBan không hợp lệ
        }
        // Kiểm tra xem bàn có người không
        if (KiemTraBanTrong(idBanAnTrangChu)) {
            JOptionPane.showMessageDialog(this, "Bàn đã có người, không thể đặt món.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return; // Dừng hàm nếu bàn đã có người
        }
        // Kiểm tra xem tableOrder có dữ liệu không
        if (tableOrder.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa thêm món ăn vào bảng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; // Dừng hàm nếu tableOrder trống
        }

        Order_XuLy add = new Order_XuLy();
        int idban = idBanAnTrangChu;
        // float giagiam = Float.parseFloat(txtGiamBill.getText());
        float tongbill_onlyMon = Float.parseFloat(txtTongBill.getText());
        float thanhtoan = Float.parseFloat(txtTongTienPhaiTra.getText());
        String trangthai = "unsuccessful";

        Order item1 = new Order();
        item1.setId_ban(idban);
        item1.setGiamgia(0);
        item1.setTongbill(tongbill_onlyMon);
        item1.setTrangthai(trangthai);
        item1.setThanhtien((float) thanhtoan);

        Date currentDate = new Date();

        boolean themOrder = add.InsertOrder(item1, currentDate);
        int order_id = add.GetIdOrder();

        boolean themOrder_detail = false;

//        boolean update = 
        int rowCount = tableOrder.getRowCount();
        ArrayList<Order_Detail> listAddMonAn = new ArrayList<>();

        if (rowCount > 0) {
            //Thêm list món vào 1 danh sách để thêm vô chi tiết
            for (int i = 0; i < rowCount; i++) {
                Order_Detail item = new Order_Detail();

                Object idMonan = tableOrder.getValueAt(i, 0);
                Object tenMonan = tableOrder.getValueAt(i, 1);
                Object soLuong = tableOrder.getValueAt(i, 2);
                Object dongia = tableOrder.getValueAt(i, 3);

                item.setOrderId(order_id);
                item.setIdMonan((int) idMonan);

                item.setTenMonan((String) tenMonan);

                int soLuongInt = Integer.parseInt(soLuong.toString());
                double dongiaitem = Double.parseDouble(dongia.toString());

                item.setSoLuong(soLuongInt);
                item.setDonGia(dongiaitem);
                double thanhtien = soLuongInt * dongiaitem;
                item.setThanhTien(thanhtien);
                listAddMonAn.add(item);
            }
            //Xử lý cảnh báo kho
            //Danh sách 1 món có nhiều nguyên liệu
            boolean canhbao_mon = false;
            String tenmon = "";
            for (Order_Detail itemDT : listAddMonAn) {
                //Đây là 1 list nguyên liệu của 1 món ăn
                ArrayList<NguyenLieu_MonAn> listds = add.getListNguyenLieu(itemDT.getIdMonan());

                for (NguyenLieu_MonAn item2 : listds) {
                    //Số lượng nguyên liệu cần làm của nguyên liệu cho món đó
                    float soluongCanLam = add.SoLuongNguyenLieuCanLam(item2.getIdnguyenlieu(), itemDT.getIdMonan());
                    //Số lượng tồn của kho của nguyên liệu đó
                    float soluongTon = add.kiemTraSoLuongTrongKho(item2.getIdnguyenlieu());
                    if (soluongTon <= 0) {
                        JOptionPane.showMessageDialog(this, "Cảnh báo món hết nguyên liệu!\n Món hết là " + tenmon, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    float soluongConLai = soluongConLai = soluongTon - (soluongCanLam * (int) itemDT.getSoLuong());

//                    System.out.println("Soluong con : " + soluongConLai);
//                    System.out.println("So luong mua con : " + item2.getSoluong());
                    if (soluongTon < soluongCanLam) {
                        canhbao_mon = true;
                        tenmon = item2.getTennguyenlieu();
                        break;
                    } else {
                        add.UpdateKho(item2.getIdnguyenlieu(), soluongConLai);
                    }
                }
            }
            if (!canhbao_mon) {
                for (Order_Detail itemDT : listAddMonAn) {
                    if (add.InsertOrderDetail(itemDT)) {
                        themOrder_detail = true;
                    } else {
                        themOrder_detail = false;
                    }
                }
                add.UpDateTrangThaiBan(idban, currentDate);
                selectedButton.setBackground(Color.YELLOW);

                Order_Load load = new Order_Load();
                BanAn listban = load.getBanAnByID(idban);

                Timestamp getTime = listban.getNgayGioDat();
                if (getTime != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String timeOder = dateFormat.format(getTime);

                    txtDateOrder.setText(timeOder);
                } else {
                    txtDateOrder.setText("0");
                }
                addBanAn();
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int deleteBill = add.DeleteBill(order_id);
                add.UpDateTrangThaiBanCancel(idban);
                JOptionPane.showMessageDialog(this, "Cảnh báo món hết nguyên liệu!\n Món hết là " + tenmon, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Chưa thêm món ăn vào bảng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnOrderMonActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        HoaDonNhapKho hoadon = new HoaDonNhapKho();
        hoadon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        hoadon.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void menuNhapKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNhapKhoMouseClicked

    }//GEN-LAST:event_menuNhapKhoMouseClicked

    private void menuKiemKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKiemKhoMouseClicked

    }//GEN-LAST:event_menuKiemKhoMouseClicked

    private void jMenuItem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseClicked

    }//GEN-LAST:event_jMenuItem4MouseClicked

    private void menuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHoaDonMouseClicked
        QuanLyHoaDon qlhd = new QuanLyHoaDon();
        qlhd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        qlhd.setVisible(true);    }//GEN-LAST:event_menuHoaDonMouseClicked

    private void menuDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDoanhThuMouseClicked
        BaoCaoDoanhThu baocao = new BaoCaoDoanhThu();
        baocao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        baocao.setVisible(true);
    }//GEN-LAST:event_menuDoanhThuMouseClicked

    private void btnXuatBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void btnxuatbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatbillActionPerformed
        Order_XuLy xuly = new Order_XuLy();
        int idBan = idBanAnTrangChu;

        if (idBan == 0) {
            JOptionPane.showMessageDialog(this, "Bạn này không có người!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            int id_order = xuly.GetOrderIDByIdban(idBan);
            String tenNhanVien = getUser();
            XuatHoaDon(id_order, tenNhanVien);
        }


    }//GEN-LAST:event_btnxuatbillActionPerformed

    private void btnTachBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTachBanActionPerformed
        Order_XuLy xuly = new Order_XuLy();
        int idBan = idBanAnTrangChu;
        int id_order = xuly.GetOrderIDByIdban(idBan);

        if (id_order > 0) {
            TachBan tb = new TachBan(id_order);
            tb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tb.setVisible(true);
            addBanAn();
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn cần tách !", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTachBanActionPerformed

    private void btnchuyenbanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchuyenbanActionPerformed
        Order_XuLy xuly = new Order_XuLy();
        int idBan = idBanAnTrangChu;
        int id_order = xuly.GetOrderIDByIdban(idBan);

        if (id_order > 0) {
            ChuyenBan tb = new ChuyenBan(idBan, id_order);
            tb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tb.setVisible(true);
            this.dispose();
//            addBanAn();
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn cần chuyển !", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnchuyenbanActionPerformed
    public void XuatHoaDon(int idhd, String tenNhanVien) {
        try {
            Hashtable map = new Hashtable();
            map.put("order_id", idhd);
            map.put("employee_name", tenNhanVien);
            JasperReport report = JasperCompileManager.compileReport("src/GUI/doanhthu/rpChiTietHoaDon.jrxml");
            JasperPrint p = JasperFillManager.fillReport(report, map, connect.ketNoiCSDL());
            JasperViewer.viewReport(p, false);
            JasperExportManager.exportReportToPdfFile(p, "test.pdf");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

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
            java.util.logging.Logger.getLogger(TrangChinh.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChinh.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChinh.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChinh.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnOrderMon;
    private javax.swing.JButton btnTachBan;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimKiemThucUong;
    private javax.swing.JButton btnchuyenban;
    private javax.swing.JButton btnxuatbill;
    private javax.swing.JComboBox<String> cbLoadMenu;
    private javax.swing.JMenuItem itemDangKy;
    private javax.swing.JMenuItem itemKetCa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDoanhThu;
    private javax.swing.JMenu menuHoaDon;
    private javax.swing.JMenu menuKho;
    private javax.swing.JMenuItem menuKiemKho;
    private javax.swing.JMenu menuLichlam;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenu menuLuongNhanVien;
    private javax.swing.JMenu menuNhanvien;
    private javax.swing.JMenuItem menuNhapKho;
    private javax.swing.JMenu menuThucdon;
    private javax.swing.JMenu menuTrangchu;
    private javax.swing.JPanel panelBanAn;
    private javax.swing.JPanel panelChua;
    private javax.swing.JPanel panelChuaBanAn;
    private javax.swing.JPanel panelChuaThucUong;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelLeft_Center;
    private javax.swing.JPanel panelLeft_Top;
    private javax.swing.JPanel panelRight;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JPanel panelThucDon;
    private javax.swing.JPanel panelThucUong;
    private javax.swing.JScrollPane scrollBanAn;
    private javax.swing.JScrollPane scrollThucDon;
    private javax.swing.JScrollPane scrollThucUong;
    private javax.swing.JTable tableOrder;
    private javax.swing.JLabel txtBan;
    private javax.swing.JLabel txtBill;
    private javax.swing.JLabel txtDateOrder;
    private javax.swing.JTextField txtGiamBill;
    private javax.swing.JLabel txtNhanVien;
    private javax.swing.JTextField txtThucUong;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongBill;
    private javax.swing.JTextField txtTongTienPhaiTra;
    // End of variables declaration//GEN-END:variables
}
