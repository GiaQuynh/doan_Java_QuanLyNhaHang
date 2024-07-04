/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.thucdon;

import DAO.AccountDAO;
import static DAO.SharedPreferences.getPassword;
import static DAO.SharedPreferences.getUser;
import static DAO.SharedPreferences.getRole;
import DAO.ThucDonDao;
import POJO.MonAn;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ThucDon extends javax.swing.JFrame {

//    String thumuc = "F:\\Code\\Java\\BaoCao\\Main\\APK_QLNhaHang-main\\src\\folder\\";
    String thumuc = "C:\\Users\\Quin Quin\\Desktop\\APK_QLNhaHang-main\\APK_QLNhaHang-main\\src\\folder\\";
    DefaultTableModel dtm = new DefaultTableModel();
    JMenuItem menuItem1 = new JMenuItem("Sửa");
    JMenuItem menuItem2 = new JMenuItem("Xóa");
    JMenuItem menuItem3 = new JMenuItem("Cập nhật giá");

    /**
     * Creates new form ThucDon
     */
    public ThucDon() {
        initComponents();
        setTitle("Quản lý thực đơn");
        ImageIcon icon = new ImageIcon(thumuc + "logo.png");
        Image img = icon.getImage();
        setIconImage(img);
        setLocationRelativeTo(null);
        String[] tieuDe = {"Mã món", "Tên món", "Mã danh mục", "Giá", "Mô tả", "Hình ảnh"};
        dtm.setColumnIdentifiers(tieuDe);
        loadThucDon(new ThucDonDao().laydanhsachthucdon());

        tableDanhSach.setRowHeight(30);
        tableDanhSach.getColumnModel().getColumn(0).setPreferredWidth(30); // Mã món
        tableDanhSach.getColumnModel().getColumn(1).setPreferredWidth(200); // Tên món
        tableDanhSach.getColumnModel().getColumn(2).setPreferredWidth(100); // Mã danh mục
        tableDanhSach.getColumnModel().getColumn(3).setPreferredWidth(100); // Giá
        tableDanhSach.getColumnModel().getColumn(4).setPreferredWidth(300); // Mô tả
        tableDanhSach.getColumnModel().getColumn(5).setPreferredWidth(150); // 

        String role = getRole();
        if (role.equalsIgnoreCase("admin")) {
            btnAdd.setEnabled(true);
            btnDeleteMon.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnUpdateMonAn.setEnabled(true);
        } else {
            btnAdd.setEnabled(false);
            btnDeleteMon.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnUpdateMonAn.setEnabled(false);
        }

        tableDanhSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    // Lấy dòng và cột được click
                    int row = tableDanhSach.rowAtPoint(e.getPoint());
                    int col = tableDanhSach.columnAtPoint(e.getPoint());
                    showContextMenu(e.getX(), e.getY());
                }
            }
        });
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableDanhSach.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ThucDon.this, "Vui lòng chọn một dòng để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
                    int idMonAn = (int) model.getValueAt(selectedRow, 0);
                    SuaMon update = new SuaMon(idMonAn);
                    update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    update.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ThucDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index
                int selectedRow = tableDanhSach.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ThucDon.this, "Vui lòng chọn một hàng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Display a confirmation dialog
                int confirm = JOptionPane.showConfirmDialog(ThucDon.this, "Bạn có chắc muốn xóa hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
                    int idMon = (int) model.getValueAt(selectedRow, 0); // Assuming the first column contains the ID
                    try {
                        // Attempt to delete the row from the database
                        ThucDonDao object = new ThucDonDao();
                        if (object.DeleteMonAn(idMon)) {

                            model.removeRow(selectedRow);
                            tableDanhSach.repaint();
                            JOptionPane.showMessageDialog(ThucDon.this, "Đã xóa dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Log the exception
                        JOptionPane.showMessageDialog(ThucDon.this, "Đã xảy ra lỗi khi xóa dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index
                int selectedRow = tableDanhSach.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ThucDon.this, "Vui lòng chọn một hàng để sửa giá.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Display a confirmation dialog
                //int confirm = JOptionPane.showConfirmDialog(ThucDon.this, "Bạn có chắc muốn xóa hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
                int idMon = (int) model.getValueAt(selectedRow, 0); // Assuming the first column contains the ID
                try {
                    UpdateGiaMon update = new UpdateGiaMon(idMon);
                    // Thiết lập tham chiếu đến form ThucDon
                    update.setParentForm(ThucDon.this);
                    update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    update.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log the exception
                    JOptionPane.showMessageDialog(ThucDon.this, "Đã xảy ra lỗi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

    private void showContextMenu(int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        // Tạo các mục menu
        // Thêm các mục menu vào menu ngữ cảnh
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.add(menuItem3);
        // Hiển thị menu ngữ cảnh tại vị trí được chỉ định bởi tọa độ x và y
        popupMenu.show(tableDanhSach, x, y);
    }

    public void loadThucDon(ArrayList<MonAn> danhSach) {
        dtm.setRowCount(0);
        for (MonAn pb : danhSach) {
            Vector<Object> v = new Stack<>();
            v.add(pb.getId());
            v.add(pb.getTenmon());
            v.add(pb.getIddanhmuc());

            v.add(pb.getGiamon());
            v.add(pb.getMota());
            v.add(pb.getHinhanh());
            dtm.addRow(v);
        }
        tableDanhSach.setModel(dtm);

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
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDeleteMon = new javax.swing.JButton();
        btnUpdateMonAn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDanhSach = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1440, 800));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Thực đơn không dành cho nhà hàng cao cấp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDeleteMon.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDeleteMon.setText("Xóa");
        btnDeleteMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMonActionPerformed(evt);
            }
        });

        btnUpdateMonAn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnUpdateMonAn.setText("Cập nhật giá");
        btnUpdateMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMonAnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAdd)
                .addGap(38, 38, 38)
                .addComponent(btnUpdate)
                .addGap(35, 35, 35)
                .addComponent(btnDeleteMon)
                .addGap(53, 53, 53)
                .addComponent(btnUpdateMonAn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDeleteMon)
                    .addComponent(btnUpdateMonAn))
                .addContainerGap())
        );

        tableDanhSach.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableDanhSach);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1256, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        ThemMon them;
        try {
            them = new ThemMon();

            them.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            them.setVisible(true);
            loadThucDon(new ThucDonDao().laydanhsachthucdon());
        } catch (IOException ex) {
            Logger.getLogger(ThucDon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMonActionPerformed
        int selectedRow = tableDanhSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dòng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Lấy model của bảng
            DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
            int idMonAn = (int) model.getValueAt(selectedRow, 0);
            ThucDonDao object = new ThucDonDao();
            if (object.DeleteMonAn(idMonAn)) {
                // If deletion from the database is successful, remove the row from the table
                model.removeRow(selectedRow);
                tableDanhSach.repaint();
                JOptionPane.showMessageDialog(ThucDon.this, "Đã xóa dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(ThucDon.this, "Xóa dữ liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteMonActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tableDanhSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
            int idMonAn = (int) model.getValueAt(selectedRow, 0);
            SuaMon update = new SuaMon(idMonAn);
            update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            update.setVisible(true);
            loadThucDon(new ThucDonDao().laydanhsachthucdon());
        } catch (IOException ex) {
            Logger.getLogger(ThucDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdateMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMonAnActionPerformed
        // Get the selected row index
        int selectedRow = tableDanhSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(ThucDon.this, "Vui lòng chọn một hàng để sửa giá.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Display a confirmation dialog
        //int confirm = JOptionPane.showConfirmDialog(ThucDon.this, "Bạn có chắc muốn xóa hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
        int idMon = (int) model.getValueAt(selectedRow, 0); // Assuming the first column contains the ID
        try {
            UpdateGiaMon update = new UpdateGiaMon(idMon);
            // Thiết lập tham chiếu đến form ThucDon
            update.setParentForm(this);
            update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            update.setVisible(true);
            loadThucDon(new ThucDonDao().laydanhsachthucdon());
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception
            JOptionPane.showMessageDialog(ThucDon.this, "Đã xảy ra lỗi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateMonAnActionPerformed

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
            java.util.logging.Logger.getLogger(ThucDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThucDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThucDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThucDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThucDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDeleteMon;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateMonAn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDanhSach;
    // End of variables declaration//GEN-END:variables
}
