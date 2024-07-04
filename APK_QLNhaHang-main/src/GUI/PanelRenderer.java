/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class PanelRenderer extends DefaultTableCellRenderer {

    private JPanel panel;
    private JButton button1;
    private JButton button2;
    private JLabel textField;

    public PanelRenderer() {
        panel = new JPanel();
        button1 = new JButton("+");
        button2 = new JButton("-");
        textField = new JLabel("1");

        panel.add(button1);
        panel.add(textField);
        panel.add(button2);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return panel;
    }
}
