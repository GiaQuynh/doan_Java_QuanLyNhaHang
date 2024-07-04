/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.thucdon;

import static DAO.SharedPreferences.clearCredentials;
import DAO.ThucDonDao;
import GUI.taikhoan.Login;
import POJO.DanhMucMonAn;
import POJO.NguyenLieu;
import POJO.NguyenLieu_MonAn;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ThemMon extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
//    String thumuc = "F:\\Code\\Java\\BaoCao\\Main\\APK_QLNhaHang-main\\src\\folder\\";
    String thumuc = "C:\\Users\\Quin Quin\\Desktop\\APK_QLNhaHang-main\\APK_QLNhaHang-main\\src\\folder\\";
    String tenFileGlobal = "";
    File selectLuuFolder;

    /**
     * Creates new form ThemMon
     */
    public ThemMon() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Thêm món ăn");
        setResizable(false);
        //Tab đơn chung
        loadDanhMucMonAn();
//        HienThiHinhAnh("BanhCanh.jpg");
        //Tab nguyên liệu
        String[] tieuDe = {"Mã nguyên liệu", "Tên nguyên liệu", "Đơn vị", "Số lượng"};
        dtm.setColumnIdentifiers(tieuDe);
        tableNguyenLieu.setModel(dtm);
        tableNguyenLieu.setRowHeight(30);

        loadNguyenLieu();

        // Add an ItemListener to the ComboBox
        cbNguyenlieu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    addSelectedNguyenLieuToTable();
                }
            }
        });
    }

    private void HienThiHinhAnh(String filename) throws IOException {
        // Đường dẫn tương đối của hình ảnh
        String relativePath = "\\folder\\" + filename;
        String projectPath = System.getProperty("user.dir"); // Lấy đường dẫn của thư mục dự án
        String absolutePath = projectPath + "\\src" + relativePath; // Tạo đường dẫn tuyệt đối dựa trên thư mục src
        File file = new File(absolutePath);
        if (file.exists()) {
            BufferedImage image = ImageIO.read(file.getAbsoluteFile());
            JLabel label = new JLabel(new ImageIcon(image));
            label.setSize(panelAnh.getWidth(), panelAnh.getHeight());
            panelAnh.removeAll();
            panelAnh.add(label);
            panelAnh.revalidate();
            panelAnh.repaint();

        } else {
            System.err.println("Không thể tìm thấy hình ảnh: " + absolutePath);
        }
    }

    private void addSelectedNguyenLieuToTable() {
        String selectedItem = (String) cbNguyenlieu.getSelectedItem();
        ThucDonDao thucdon = new ThucDonDao();
        ArrayList<NguyenLieu> listNguyenLieu = thucdon.loadMenuNguyenLieu();
        NguyenLieu selectedNguyenLieu = null;
        for (NguyenLieu nl : listNguyenLieu) {
            if (nl.getTennguyenlieu().equals(selectedItem)) {
                selectedNguyenLieu = nl;
                break;
            }
        }
        if (selectedNguyenLieu != null) {
            int rowIndex = tableNguyenLieu.getModel().getRowCount();
            dtm.addRow(new Object[]{selectedNguyenLieu.getId(), selectedNguyenLieu.getTennguyenlieu(), selectedNguyenLieu.getDonvi(), "0"});
        }
    }

    private void loadNguyenLieu() {
        ThucDonDao thucdon = new ThucDonDao();
        ArrayList<NguyenLieu> listNguyenLieu = thucdon.loadMenuNguyenLieu();
        cbNguyenlieu.removeAllItems(); // Remove any existing items in the ComboBox
        for (NguyenLieu nl : listNguyenLieu) {
            cbNguyenlieu.addItem(nl.getTennguyenlieu());
        }
    }

    private void loadDanhMucMonAn() {
        ThucDonDao thucdon = new ThucDonDao();
        ArrayList<DanhMucMonAn> listNguyenLieu = thucdon.loadMenuDanhmuc();
        cbDanhMuc.removeAllItems(); // Remove any existing items in the ComboBox
        for (DanhMucMonAn nl : listNguyenLieu) {
            cbDanhMuc.addItem(nl.getTendanhmuc());
        }
    }

    //Hàm lấy giá trị từ đơn chung
    private void LayGiaTriChung() {
        String selectedItem = (String) cbDanhMuc.getSelectedItem();
        ThucDonDao thucdon = new ThucDonDao();
        ArrayList<DanhMucMonAn> listNguyenLieu = thucdon.loadMenuDanhmuc();
        DanhMucMonAn selectedDanhMuc = null;
        for (DanhMucMonAn nl : listNguyenLieu) {
            if (nl.getTendanhmuc().equals(selectedItem)) {
                selectedDanhMuc = nl;
                break;
            }
        }
        int iddanhmuc = 0;
        if (selectedDanhMuc != null) {
            iddanhmuc = selectedDanhMuc.getId();
        }

        String tenmon = txtTenMon.getText();
        String mota = txtMoTa.getText();
        String gia = txtGia.getText();
        String tenfileluudb = tenFileGlobal;

        try {
            BufferedImage image = ImageIO.read(selectLuuFolder);
            File destinationFile = new File(thumuc + tenFileGlobal);
            ImageIO.write(image, "jpg", destinationFile);
            JOptionPane.showMessageDialog(null, "Image added and saved successfully!");
            dispose();
        } catch (IOException ex) {
            Logger.getLogger(ThemMon.class.getName()).log(Level.SEVERE, null, ex);
        }

        int idMonAn = thucdon.Insert(iddanhmuc, tenmon, gia, mota, tenFileGlobal);
//        System.out.println("ID danh mục vừa tạo : " + iddanhmuc);
//        System.out.println("Tên món vừa tạo : " + tenmon);
//        System.out.println("Giá vừa tạo : " + gia);
//        System.out.println("Mô tả vừa tạo : " + mota);
//        System.out.println("Tên file vừa tạo : " + tenFileGlobal);
//        System.out.println("Id vừa tạo : " + idMonAn);
        String idmon = String.valueOf(idMonAn);
        ArrayList<NguyenLieu_MonAn> listmon = NguyenLieu_MonAn_Default(idmon);
        for (NguyenLieu_MonAn nguyenLieu_MonAn : listmon) {
            thucdon.InsertNguyenLieu_MonAn(nguyenLieu_MonAn);
        }
    }

    //Lấy dữ liệu từ Nguyên liệu để lưu
    private ArrayList<ArrayList<Object>> getDataFromTable() {
        DefaultTableModel model = (DefaultTableModel) tableNguyenLieu.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();
        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }
        ArrayList<ArrayList<Object>> dataList = new ArrayList<>();
        for (Object[] row : data) {
            ArrayList<Object> rowList = new ArrayList<>(Arrays.asList(row));
            dataList.add(rowList);
        }
        return dataList;
    }

    private ArrayList<NguyenLieu_MonAn> NguyenLieu_MonAn_Default(String id_Mon) {
        ArrayList<NguyenLieu_MonAn> list = new ArrayList<>();

        ArrayList<ArrayList<Object>> dataList = getDataFromTable();
        for (ArrayList<Object> row : dataList) {
            Object firstElement0 = row.get(0);
            Object firstElement1 = row.get(1); //Tên nguyên liệu
            Object firstElement2 = row.get(2); // Đơn vị
            Object firstElement3 = row.get(3); // Số lượng

            NguyenLieu_MonAn nguyenlieu = new NguyenLieu_MonAn();

            nguyenlieu.setIdmon(Integer.parseInt(id_Mon.toString()));
            nguyenlieu.setIdnguyenlieu(Integer.parseInt(firstElement0.toString()));
            nguyenlieu.setTennguyenlieu(firstElement1.toString());
            nguyenlieu.setDonvi(firstElement2.toString());
            nguyenlieu.setSoluong(Float.parseFloat(firstElement3.toString()));
            list.add(nguyenlieu);
        }
        return list;
    }

    // Phương thức để hiển thị ảnh trên JPanel
    private void displayImageOnPanel(BufferedImage image) {
        JLabel label = new JLabel(new ImageIcon(image));
        // Đặt kích thước cho JLabel tương tự như kích thước của hình ảnh
//        label.setSize(image.getWidth(), image.getHeight());
        label.setSize(panelAnh.getWidth(), panelAnh.getHeight());
        // Đưa JLabel vào JPanel đã chọn
        panelAnh.removeAll(); // Xóa bất kỳ thành phần nào đã được hiển thị trước đó trên JPanel
        panelAnh.add(label); // Thêm JLabel chứa hình ảnh vào JPanel
        panelAnh.revalidate(); // Cập nhật JPanel để hiển thị lại
        panelAnh.repaint(); // Vẽ lại JPanel
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbDanhMuc = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenMon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnAddAnh = new javax.swing.JButton();
        btnRemoveAnh = new javax.swing.JButton();
        panelAnh = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cbNguyenlieu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNguyenLieu = new javax.swing.JTable();
        btnThemMonAn = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Loại");

        cbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Tên món");

        txtTenMon.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Mô tả");

        txtMoTa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Giá");

        txtGia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenMon)
                    .addComponent(txtMoTa)
                    .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                .addContainerGap(289, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)), "Ảnh đại diện", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        btnAddAnh.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnAddAnh.setText("...");
        btnAddAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAnhActionPerformed(evt);
            }
        });

        btnRemoveAnh.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnRemoveAnh.setForeground(new java.awt.Color(255, 0, 0));
        btnRemoveAnh.setText("X");
        btnRemoveAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAnhLayout = new javax.swing.GroupLayout(panelAnh);
        panelAnh.setLayout(panelAnhLayout);
        panelAnhLayout.setHorizontalGroup(
            panelAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelAnhLayout.setVerticalGroup(
            panelAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Chọn các ảnh có định dạng");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("(.jpg, png)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel5)
                        .addGap(0, 169, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnAddAnh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveAnh)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thông tin chung", jPanel3);

        cbNguyenlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        tableNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableNguyenLieu);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(cbNguyenlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cbNguyenlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Định lượng nguyên vật liệu", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        btnThemMonAn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemMonAn.setText("Thêm");
        btnThemMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonAnActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemMonAn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMonAn)
                    .addComponent(btnHuy))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonAnActionPerformed
        String tenmon = txtTenMon.getText();
        String mota = txtMoTa.getText();
        String gia = txtGia.getText();
        String tenfileluudb = tenFileGlobal;

        // Check if any of the fields are empty or null
        if (tenmon.isEmpty() || mota.isEmpty() || gia.isEmpty() || tenfileluudb.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method early
        }
        int rowCount = tableNguyenLieu.getRowCount(); // Số lượng dòng trong bảng
        if (rowCount <= 0) {
            JOptionPane.showMessageDialog(this, "Dữ liệu định lượng đang trống.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method early
        }
        // Check if dataList is null

        LayGiaTriChung();
        this.dispose();
    }//GEN-LAST:event_btnThemMonAnActionPerformed

    private void btnAddAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAnhActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
//            duongdananh = selectedFile.getAbsoluteFile();
            tenFileGlobal = selectedFile.getName();
            selectLuuFolder = selectedFile;
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                // Hiển thị ảnh trên JPanel
                displayImageOnPanel(image);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error adding image: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnAddAnhActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btnRemoveAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAnhActionPerformed
        Component[] components = panelAnh.getComponents(); // Lấy tất cả các thành phần con của panelAnh
        for (Component component : components) {
            if (component instanceof JLabel) { // Kiểm tra xem thành phần có phải là JLabel chứa hình ảnh không
                panelAnh.remove(component); // Nếu là JLabel, xóa nó khỏi panelAnh
                break; // Sau khi tìm và xóa thành phần đầu tiên, thoát khỏi vòng lặp
            }
        }
        panelAnh.revalidate(); // Cập nhật panelAnh để hiển thị lại
        panelAnh.repaint(); // Vẽ lại panelAnh
    }//GEN-LAST:event_btnRemoveAnhActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThemMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThemMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThemMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThemMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new ThemMon().setVisible(true);
//                } catch (IOException ex) {
//                    Logger.getLogger(ThemMon.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAnh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnRemoveAnh;
    private javax.swing.JButton btnThemMonAn;
    private javax.swing.JComboBox<String> cbDanhMuc;
    private javax.swing.JComboBox<String> cbNguyenlieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelAnh;
    private javax.swing.JTable tableNguyenLieu;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtTenMon;
    // End of variables declaration//GEN-END:variables
}
