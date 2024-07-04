/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
//
//public class PanelEditor extends AbstractCellEditor implements TableCellEditor {
//    private JPanel panel;
//    private JButton congButton;
//    private JButton truButton;
//    private JLabel label;
//    private int currentValue;
//
//    public PanelEditor() {
//        panel = new JPanel();
//        congButton = new JButton("+");
//        truButton = new JButton("-");
//        label = new JLabel("1");
//
//        panel.add(congButton);
//        panel.add(label);
//        panel.add(truButton);
//
//        congButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentValue = Integer.parseInt(label.getText());
//                label.setText(String.valueOf(currentValue + 1));
//            }
//        });
//
//        truButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentValue = Integer.parseInt(label.getText());
//                if (currentValue > 1) {
//                    label.setText(String.valueOf(currentValue - 1));
//                }
//            }
//        });
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        currentValue = Integer.parseInt(value.toString());
//        label.setText(String.valueOf(currentValue));
//        return panel;
//    }
//
//    @Override
//    public Object getCellEditorValue() {
//        return label.getText();
//    }
//
//    @Override
//    public boolean stopCellEditing() {
//        fireEditingStopped(); // Bắt buộc cập nhật giá trị khi kết thúc chỉnh sửa
//        return super.stopCellEditing();
//    }
//}
public class PanelEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton congButton;
    private JButton truButton;
    private JLabel label;
    private int currentValue;
    private int row; // Store the row index

    public PanelEditor() {
        panel = new JPanel();
        congButton = new JButton("+");
        truButton = new JButton("-");
        label = new JLabel("1");

        panel.add(congButton);
        panel.add(label);
        panel.add(truButton);

        congButton.addActionListener(e -> {
            currentValue = Integer.parseInt(label.getText());
            currentValue++;
            label.setText(String.valueOf(currentValue));
            fireEditingStopped(); // Notify that editing has stopped
        });

        truButton.addActionListener(e -> {
            currentValue = Integer.parseInt(label.getText());
            if (currentValue > 1) {
                currentValue--;
                label.setText(String.valueOf(currentValue));
                fireEditingStopped(); // Notify that editing has stopped
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentValue = Integer.parseInt(value.toString());
        label.setText(String.valueOf(currentValue));
        this.row = row; // Store the row index
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped(); // Notify that editing has stopped
        return super.stopCellEditing();
    }

    // Add a method to get the row index
    public int getRow() {
        return row;
    }
}
